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

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import com.txusballesteros.R;
import com.txusballesteros.di.ApplicationComponent;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.instrumentation.ImageDownloader;
import com.txusballesteros.presentation.NotesListPresenter;
import com.txusballesteros.view.activity.NotesListActivity;
import com.txusballesteros.view.adapter.NotesListAdapter;
import com.txusballesteros.view.di.DaggerViewComponent;
import com.txusballesteros.view.di.ViewModule;
import java.util.List;
import javax.inject.Inject;

public class NotesListFragment extends AbsFragment implements NotesListPresenter.View {
  private NotesListAdapter adapter;
  @Inject NotesListPresenter presenter;
  @Inject ImageDownloader imageDownloader;
  @BindView(R.id.list) RecyclerView listView;

  public static Fragment newInstance() {
    return new NotesListFragment();
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
    return R.layout.fragment_notes_list;
  }

  @Override
  public void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override
  public void onViewReady() {
    setRetainInstance(true);
    initializeFabButton();
  }

  @Override
  protected int onRequestMenuResourceId() {
    return R.menu.notes_list_menu;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean result = true;
    switch(item.getItemId()) {
      case R.id.presentation_mode:
        presenter.onRequestChangePresentationMode();
        break;
      case R.id.about:
        presenter.onRequestAbout();
        break;
      default:
        result = super.onOptionsItemSelected(item);
        break;
    }
    return result;
  }

  @Override
  public void renderNotesList(List<Note> notes) {
    adapter.clear();
    adapter.addAll(notes);
    adapter.notifyDataSetChanged();
  }

  private void initializeFabButton() {
    if (isAdded()) {
      ((NotesListActivity) getActivity()).showFabButton(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          presenter.onAddNewNoteClick(getActivity());
        }
      });
    }
  }

  @Override
  public void showLoading() {
    if (isAdded()) {
      ((NotesListActivity) getActivity()).showLoading();
    }
  }

  @Override
  public void hideLoading() {
    if (isAdded()) {
      ((NotesListActivity) getActivity()).hideLoading();
    }
  }

  @Override
  public void showPresentationModeList() {
    final RecyclerView.LayoutManager layoutManager = buildListLayoutManager();
    replaceLayoutManager(layoutManager);
    changePresentationModeIcon(R.drawable.ic_mode_grid_white_24dp);
  }

  @Override
  public void showPresentationModeGrid() {
    final RecyclerView.LayoutManager layoutManager = buildGridLayoutManager();
    replaceLayoutManager(layoutManager);
    changePresentationModeIcon(R.drawable.ic_mode_list_white_24dp);
  }

  private void changePresentationModeIcon(@DrawableRes int iconResourceId) {
    Menu menu = getMenu();
    if (menu != null) {
      MenuItem menuItem = menu.findItem(R.id.presentation_mode);
      menuItem.setIcon(iconResourceId);
    }
  }

  private void replaceLayoutManager(RecyclerView.LayoutManager layoutManager) {
    if (adapter == null) {
      adapter = new NotesListAdapter(imageDownloader);

    }
    adapter.setOnNoteClickListener(new NotesListAdapter.OnNoteClickListener() {
      @Override
      public void onNoteClick(@NonNull Note note) {
        presenter.onRequestNoteDetail(getActivity(), note);
      }
    });
    listView.setLayoutManager(layoutManager);
    listView.setHasFixedSize(true);
    listView.setAdapter(adapter);
  }

  private RecyclerView.LayoutManager buildListLayoutManager() {
    return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
  }

  private RecyclerView.LayoutManager buildGridLayoutManager() {
    return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
  }
}
