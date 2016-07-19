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
package com.txusballesteros.data.model;

import android.support.annotation.NonNull;

public final class TaskDataModel {
  private int order;
  private String title;
  private boolean isDone;

  private TaskDataModel() { }

  public int getOrder() {
    return order;
  }

  public String getTitle() {
    return title;
  }

  public boolean isDone() {
    return isDone;
  }

  public static class Builder {
    private final TaskDataModel taskDataModel = new TaskDataModel();

    public Builder setOrder(int order) {
      taskDataModel.order = order;
      return this;
    }

    public Builder setIsDone(boolean isDone) {
      taskDataModel.isDone = isDone;
      return this;
    }

    public Builder setTitle(@NonNull String title) {
      taskDataModel.title = title;
      return this;
    }

    public TaskDataModel build() {
      return taskDataModel;
    }
  }
}
