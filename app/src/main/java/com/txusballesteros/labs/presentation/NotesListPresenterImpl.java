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
package com.txusballesteros.labs.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.txusballesteros.labs.domain.interactor.GetNotesUseCase;
import com.txusballesteros.labs.domain.model.Note;
import com.txusballesteros.labs.navigation.Navigator;
import java.util.List;
import javax.inject.Inject;

public class NotesListPresenterImpl extends AbsPresenter<NotesListPresenter.View> implements NotesListPresenter {
  private final GetNotesUseCase getNotesUseCase;
  private final Navigator navigator;
  private PresentationMode presentationMode = PresentationMode.LIST;
  private boolean loadingInProgress = false;

  @Inject
  public NotesListPresenterImpl(GetNotesUseCase getNotesUseCase,
                                Navigator navigator) {
    this.getNotesUseCase = getNotesUseCase;
    this.navigator = navigator;
  }

  @Override
  public void onRequestNotes() {
    getView().showLoading();
    configurePresentationMode();
    loadingInProgress = true;
    getNotesUseCase.execute(new GetNotesUseCase.Callback() {
      @Override
      public void onActorReady(@NonNull List<Note> notes) {
        loadingInProgress = false;
        getView().hideLoading();
        getView().renderNotesList(notes);
      }
    });
  }

  @Override
  public void onRequestRefreshNotes() {
    if (!loadingInProgress) {
      getNotesUseCase.execute(new GetNotesUseCase.Callback() {
        @Override public void onActorReady(@NonNull List<Note> notes) {
          getView().updateNotesList(notes);
        }
      });
    }
  }

  private void configurePresentationMode() {
    if (presentationMode == PresentationMode.LIST) {
      getView().showPresentationModeList();
    } else {
      getView().showPresentationModeGrid();
    }
  }

  @Override
  public void onAddNewNoteClick(@NonNull Context context) {
    navigator.navigateToCreateNewNote(context);
  }

  @Override
  public void onRequestChangePresentationMode() {
    if (presentationMode == PresentationMode.LIST) {
      presentationMode = PresentationMode.GRID;
      getView().showPresentationModeGrid();
    } else {
      presentationMode = PresentationMode.LIST;
      getView().showPresentationModeList();
    }
  }

  @Override
  public void onRequestAbout(@NonNull Context context) {
    navigator.navigateToAbout(context);
  }

  @Override
  public void onRequestNoteDetail(@NonNull Context context, @NonNull Note note,
                                  @Nullable android.view.View sharedView) {
    navigator.navigateToNoteDetail(context, note, sharedView);
  }
}
