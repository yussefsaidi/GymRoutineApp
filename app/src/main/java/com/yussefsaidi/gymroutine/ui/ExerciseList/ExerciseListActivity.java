package com.yussefsaidi.gymroutine.ui.ExerciseList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.RequestManager;
import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;
import com.yussefsaidi.gymroutine.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

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
    LiveData<List<Exercise>> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_exercise_list);

        viewModel = new ViewModelProvider(this, providerFactory).get(ExerciseListViewModel.class);
        addExercises();
        viewModel.getAllExercises();
        exerciseList = viewModel.getExercisesLiveData();
        Log.d(TAG, "onCreate: ExerciseList " + exerciseList.getValue());
    }

    public void addExercises(){


        Exercise exercise = new Exercise("TEST TEST", "5", "5");
        viewModel.insertExercises(exercise);

    }


}
