/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.leakcanarylite;

import android.app.Application;

import com.squareup.leakcanarylite.HeapDump;
import com.squareup.leakcanarylite.LeakCanaryLite;

public class ExampleApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    setupLeakCanary();
  }

  protected void setupLeakCanary() {
    LeakCanaryLite.install(this);

    LeakCanaryLite.setHeadDumpListener(new HeapDump.Listener() {
      @Override
      public void analyze(HeapDump heapDump) {

      }

      @Override
      public void upload(HeapDump heapDump) {

      }
    });
  }

}
