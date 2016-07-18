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
package com.txusballesteros.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.txusballesteros.R;
import com.txusballesteros.di.ApplicationComponent;
import com.txusballesteros.domain.model.Actor;
import com.txusballesteros.presentation.ActorsListPresenter;
import com.txusballesteros.view.di.DaggerViewComponent;
import com.txusballesteros.view.di.ViewModule;
import java.util.List;
import javax.inject.Inject;

public class ActorsListFragment extends AbsFragment implements ActorsListPresenter.View {
  private ActorsListAdapter adapter;
  @Inject ActorsListPresenter presenter;
  @BindView(R.id.list) RecyclerView listView;

  public static ActorsListFragment newInstance() {
    return new ActorsListFragment();
  }

  @Override
  void onRequestInjection(ApplicationComponent applicationComponent) {
    DaggerViewComponent.builder()
        .applicationComponent(applicationComponent)
        .viewModule(new ViewModule(this))
        .build()
        .inject(this);
  }

  @Override
  int onRequestLayoutResourceId() {
    return R.layout.fragment_actors_list;
  }

  @Override
  public void onPresenterShouldBeAttached() {
    presenter.onAttach();
  }

  @Override
  public void onViewReady() {
    initializeList();
  }

  private void initializeList() {
    adapter = new ActorsListAdapter();
    listView.setHasFixedSize(true);
    listView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    listView.setAdapter(adapter);
  }

  @Override
  public void renderActorsList(List<Actor> actors) {
    adapter.clear();
    adapter.addAll(actors);
    adapter.notifyDataSetChanged();
  }
}
