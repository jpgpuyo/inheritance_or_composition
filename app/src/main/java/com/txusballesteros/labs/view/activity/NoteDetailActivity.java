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
package com.txusballesteros.labs.view.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import com.txusballesteros.labs.domain.model.NoteType;
import com.txusballesteros.labs.view.behavior.ToolbarImageBehavior;
import com.txusballesteros.labs.view.fragment.NoteDetailFragment;

public class NoteDetailActivity extends AbsActivity {
  public static final String EXTRA_NOTE_TYPE = "note:type";
  public static final String EXTRA_NOTE_ID = "note:id";

  @Override
  protected void onRequestBehaviors(View rootView) {
    NoteType type = getNoteType();
    if (type == NoteType.IMAGE) {
      initializeBehaviors(rootView);
    } else {
      super.onRequestBehaviors(rootView);
    }
  }

  private void initializeBehaviors(View rootView) {
    new ToolbarImageBehavior(this).inject(rootView);
  }

  @NonNull @Override
  Fragment onRequestFragment() {
    long noteId = getNoteId();
    NoteType noteType = getNoteType();
    return NoteDetailFragment.newInstance(noteId, noteType);
  }

  private long getNoteId() {
    return getIntent().getExtras().getLong(EXTRA_NOTE_ID);
  }

  private NoteType getNoteType() {
    int noteType = getIntent().getExtras().getInt(EXTRA_NOTE_TYPE);
    return NoteType.fromInt(noteType);
  }
}
