/**
 * Copyright 2004-present, Facebook, Inc.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.profilo.core;

import android.util.LongSparseArray;
import com.facebook.profilo.ipc.TraceContext;

public class TraceConditionManager {

  // traceId -> TraceCondition
  private LongSparseArray<TraceCondition> mConditions;

  public TraceConditionManager() {
    mConditions = new LongSparseArray<>();
  }

  public synchronized boolean registerTrace(TraceContext context) {
    TraceCondition cond = new TraceCondition(context);
    if (cond.isConditionMalformed()) {
      return false;
    }

    if (cond.hasConditions()) {
      mConditions.put(context.traceId, cond);
    }
    return true;
  }

  public synchronized void unregisterTrace(TraceContext context) {
    mConditions.delete(context.traceId);
  }

  public synchronized void processDurationEvent(long traceId, long duration) {
    TraceCondition cond = mConditions.get(traceId);
    if (cond == null) {
      return;
    }
    cond.processDurationEvent(duration);
  }

  public synchronized void processAnnotationEvent(long traceId, String annotation) {
    TraceCondition cond = mConditions.get(traceId);
    if (cond == null) {
      return;
    }
    cond.processAnnotationEvent(annotation);
  }

  public synchronized int getUploadSampleRate(long traceId) {
    TraceCondition cond = mConditions.get(traceId);
    if (cond == null) {
      // No trace conditions for this trace, so upload unconditionally
      return ProfiloConstants.UPLOAD_SAMPLE_RATE_ALWAYS;
    }
    return cond.getUploadSampleRate();
  }
}
