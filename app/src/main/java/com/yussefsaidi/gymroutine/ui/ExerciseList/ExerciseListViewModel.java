package com.yussefsaidi.gymroutine.ui.ExerciseList;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class ExerciseListViewModel extends ViewModel {

    private static final String TAG = "ExerciseListViewModel";

    @Inject
    public ExerciseListViewModel(){
        Log.d(TAG, "ExerciseListViewModel: viewmodel is working");
    }
}
