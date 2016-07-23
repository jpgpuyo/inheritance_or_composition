/*
 * Copyright Txus Ballesteros 2016 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.labs.navigation.command;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class NavigationCommand {
  private final Context context;

  protected Context getContext() {
    return context;
  }

  public NavigationCommand(Context context) {
    this.context = context;
  }

  public void execute() {
    Intent intent = onRequestIntent(context);
    Bundle activityOptions = onRequestActivityOptions();
    if (activityOptions != null) {
      context.startActivity(intent, activityOptions);
    } else {
      context.startActivity(intent);
    }
  }

  @NonNull abstract Intent onRequestIntent(Context context);

  @Nullable
  protected Bundle onRequestActivityOptions() { return null; }
}
