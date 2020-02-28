package com.yussefsaidi.gymroutine.ui.ExerciseList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.RequestManager;
import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ExerciseListActivity extends DaggerAppCompatActivity {

    private static final String TAG = "ExerciseListActivity";

    private ExerciseListViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    RequestManager requestManager;

    // ui
    RecyclerView mRecyclerView;

    // vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_exercise_list);

        viewModel = new ViewModelProvider(this, providerFactory).get(ExerciseListViewModel.class);
    }


}
