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
package com.txusballesteros.labs.data.di;

import com.txusballesteros.labs.data.notes.datasource.NotesApiCloudDataSource;
import com.txusballesteros.labs.data.notes.datasource.NotesCloudDataSource;
import com.txusballesteros.labs.data.notes.datasource.NotesLocalDataSource;
import com.txusballesteros.labs.data.notes.datasource.NotesInMemoryLocalDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DataSourcesModule {
  @Singleton @Provides
  NotesLocalDataSource provideActorsDataSource(NotesInMemoryLocalDataSource dataSource) {
    return dataSource;
  }

  @Singleton @Provides
  NotesCloudDataSource provideNotesCloudDataSource(NotesApiCloudDataSource dataSource) {
    return dataSource;
  }
}