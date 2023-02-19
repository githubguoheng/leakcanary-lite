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
package com.squareup.leakcanarylite;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bytedance.tailor.Tailor;
import com.squareup.leakcanarylite.internal.ActivityLifecycleCallbacksAdapter;

import java.io.File;

public final class AndroidHeapDumper implements HeapDumper {

  private final LeakDirectoryProvider leakDirectoryProvider;

  private Activity resumedActivity;

  public AndroidHeapDumper(@NonNull Context context,
      @NonNull LeakDirectoryProvider leakDirectoryProvider) {
    this.leakDirectoryProvider = leakDirectoryProvider;

    Application application = (Application) context.getApplicationContext();
    application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksAdapter() {
      @Override public void onActivityResumed(Activity activity) {
        resumedActivity = activity;
      }

      @Override public void onActivityPaused(Activity activity) {
        if (resumedActivity == activity) {
          resumedActivity = null;
        }
      }
    });
  }

  public void tailor_for_hook(String target) {
    try {
      long t = System.currentTimeMillis();
      Tailor.dumpHprofData(target, true);
//      Debug.dumpHprofData(target);

      CanaryLog.d(">>>>>>>> tailor_for_hook cost : %d >>>>>>>>>>>>> target %s",(System.currentTimeMillis() - t), target);
    } catch (Exception e) {

//      e.printStackTrace();
    }
  }

  @SuppressWarnings("ReferenceEquality") // Explicitly checking for named null.
  @Override @Nullable
  public File dumpHeap() {
    File heapDumpFile = leakDirectoryProvider.newHeapDumpFile();

    if (heapDumpFile == RETRY_LATER) {
      return RETRY_LATER;
    }

    try {

      tailor_for_hook(heapDumpFile.getAbsolutePath());

      return heapDumpFile;
    } catch (Exception e) {
      CanaryLog.d(e, "Could not dump heap " + e.toString());
      // Abort heap dump
      return RETRY_LATER;
    }
  }
}
