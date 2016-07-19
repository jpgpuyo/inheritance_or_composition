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

public abstract class NoteDataModel {
  private final long id;
  private final String title;
  private final String description;

  NoteDataModel(Builder builder) {
    this.id = builder.id;
    this.title = builder.title;
    this.description = builder.description;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public abstract NoteTypeDataModel getType();

  public static abstract class Builder {
    private long id;
    private String title;
    private String description;

    public Builder setId(long id) {
      this.id = id;
      return this;
    }

    public Builder setTitle(@NonNull String title) {
      this.title = title;
      return this;
    }

    public Builder setDescription(@NonNull String description) {
      this.description = description;
      return this;
    }

    public abstract NoteDataModel build();
  }
}
