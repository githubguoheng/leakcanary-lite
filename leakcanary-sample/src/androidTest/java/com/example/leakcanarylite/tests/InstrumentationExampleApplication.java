package com.example.leakcanarylite.tests;

import com.example.leakcanarylite.ExampleApplication;
import com.squareup.leakcanarylite.InstrumentationLeakDetector;

public class InstrumentationExampleApplication extends ExampleApplication {

  @Override protected void setupLeakCanary() {
    InstrumentationLeakDetector.instrumentationRefWatcher(this)
        .buildAndInstall();
  }
}
