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
package com.txusballesteros.labs.view.di;

import com.txusballesteros.labs.domain.interactor.di.UseCasesModule;
import com.txusballesteros.labs.presentation.CreateNotePresenter;
import com.txusballesteros.labs.presentation.NoteDetailPresenter;
import com.txusballesteros.labs.presentation.NotesListPresenter;
import com.txusballesteros.labs.presentation.di.PresentersModule;
import dagger.Module;
import dagger.Provides;

@Module( includes = {
    PresentersModule.class,
    UseCasesModule.class
})
public class ViewModule {
  private NotesListPresenter.View actorsListPresenterView;
  private CreateNotePresenter.View createNotePresenterView;
  private NoteDetailPresenter.View  noteDetailPresenterView;

  public ViewModule(NotesListPresenter.View view) {
    this.actorsListPresenterView = view;
  }

  public ViewModule(CreateNotePresenter.View view) {
    this.createNotePresenterView = view;
  }

  public ViewModule(NoteDetailPresenter.View view) {
    this.noteDetailPresenterView = view;
  }

  @Provides
  NotesListPresenter.View provideActorsListPresenterView() {
    return actorsListPresenterView;
  }

  @Provides
  CreateNotePresenter.View provideCreateNotePresenterView() {
    return createNotePresenterView;
  }

  @Provides
  NoteDetailPresenter.View provideNoteDetailPresenterView() {
    return noteDetailPresenterView;
  }
}
