package com.squareup.leakcanarylite.tests;

import android.app.Application;
import com.squareup.leakcanarylite.InstrumentationLeakDetector;

public class InstrumentationTestApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    InstrumentationLeakDetector.instrumentationRefWatcher(this)
        .buildAndInstall();
  }
}
