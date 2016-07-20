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
package com.txusballesteros.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.txusballesteros.R;

public abstract class AbsActivity extends AppCompatActivity {
  @BindView(R.id.content_place_holder) ViewGroup contentPlaceHolder;
  @BindView(R.id.loading_place_holder) ViewGroup loadingPlaceHolder;
  @BindView(R.id.fab) FloatingActionButton fabView;
  @BindView(R.id.toolbar) Toolbar toolbar;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    injectViews();
    initializeFragment();
    initializeToolbar();
  }

  private void initializeToolbar() {
    setSupportActionBar(toolbar);
  }

  private void injectViews() {
    ButterKnife.bind(this);
  }

  private void initializeFragment() {
    final Fragment fragment = onRequestFragment();
    getSupportFragmentManager().beginTransaction()
            .add(R.id.content_place_holder, fragment)
            .commit();
  }

  public void showLoading() {
    contentPlaceHolder.setVisibility(View.GONE);
    loadingPlaceHolder.setVisibility(View.VISIBLE);
  }

  public void hideLoading() {
    contentPlaceHolder.setVisibility(View.VISIBLE);
    loadingPlaceHolder.setVisibility(View.GONE);
  }

  public void showFabButton(View.OnClickListener listener) {
    fabView.setOnClickListener(listener);
    fabView.setVisibility(View.VISIBLE);
  }

  @NonNull
  abstract Fragment onRequestFragment();
}
