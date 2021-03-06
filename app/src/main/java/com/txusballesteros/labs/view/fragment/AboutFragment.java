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
package com.txusballesteros.labs.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.BindView;
import com.txusballesteros.labs.R;

public class AboutFragment extends AbsFragment {
  @BindView(R.id.about) TextView aboutView;

  public static Fragment newInstance() {
    return new AboutFragment();
  }

  @Override
  protected int onRequestLayoutResourceId() {
    return R.layout.fragment_about;
  }

  @Override
  public void onViewReady() {
    initializeToolbar();
    Spanned content = Html.fromHtml(getString(R.string.about_text));
    aboutView.setText(content);
  }

  private void initializeToolbar() {
    ActionBar actionBar  = ((AppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setHomeButtonEnabled(true);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean result = true;
    if (item.getItemId() == android.R.id.home) {
      closeView();
    } else {
      result = super.onOptionsItemSelected(item);
    }
    return result;
  }

  private void closeView() {
    getActivity().finish();
  }
}
