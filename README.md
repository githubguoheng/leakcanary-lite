# LeakCanaryLite

## 背景
客户端的内存优化以及稳定性治理是一个需长期投入的工作。通常大型项目会呈现出内存此消彼长的特点，而线下测试通常依赖于测试人员的内存专项测试进行发现，且目前大部分厂商测试人员为外包居多，只具备初步的发现能力，不具备问题的深入分析和解决能力。所以需要一个能够长期的发现线上内存水位以及快速分析和解决问题的方案，降低发现问题和分析解决问题的难度，提升线上稳定性并将其维持在合理的水位。

## 方案

**本项目提供了一个快速搭建线上安卓内存泄漏以及OOM的监控和分析的实现方案，用于治理客户端的内存和提升稳定性。**

**解决了现有安卓内存泄漏和OOM检测方案的几个痛点**
- **空间占用问题:** `生成的有效内存快照为原生的方案约1/5,空间和效率提升明显。目前大多数开源的类似解决方案都是基于内存快照的二次裁减，只是解决了上传文件的大小，并没有解决生成的快照文件大小，甚至要比原生的内存快照占用空间还要大`
- **线上生产环境部署能力:** `原生LeakCanary方案主要面向线下单机测试，无法部署到线上获取线上的情况。本项目做的最大的工作就是精简和改造LeakCanary原生方案，将检测能力和分析能力分离，并在内存快照生成期间进行裁减优化。客户端主要做泄漏检测，捕获内存快照后回传服务端，在服务端进行内存泄漏分析和聚合。使该方案具备了部署到客户端线上生成环境的能力`
- **有效的内存快照** `分析和解决内存泄漏依赖于有效的内存快照，能够快速的定位泄漏位置，否则只是能发现问题，不具备解决问题的能力`


**本项目共分两个部分，此部分为客户端的部分**

`项目中仍在持续完善中，如果你也对性能和稳定性有兴趣，欢迎共同探讨 hitwh_guoheng@hotmail.com`

**本项目基于以下开源项目进行深度改造，且遵循以下项目的开源规则**

- [**tailor**](https://github.com/bytedance/tailor)
- [**leakcanary**](https://github.com/square/leakcanary)

## Getting started

In your `build.gradle`:

```groovy
dependencies {
    debugImplementation 'com.squareup.leakcanary:leakcanary-android-lite:1.6.3'
    // Optional, if you use support library fragments:
    debugImplementation 'com.squareup.leakcanary:leakcanary-support-fragment-lite:1.6.3'
}
```

In your `Application` class:

```java
public class ExampleApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        LeakCanaryLite.install(this);
        // Normal app init code...

        LeakCanaryLite.setHeadDumpListener(new HeapDump.Listener() {
            @Override
            public void analyze(HeapDump heapDump) {
                // do something to analyze the heap dump file in local
            }

            @Override
            public void upload(HeapDump heapDump) {
                // upload the head dump file to server for analyze in cloud
            }
        });

    }



}
```


## License

    Copyright 2015 Square, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
