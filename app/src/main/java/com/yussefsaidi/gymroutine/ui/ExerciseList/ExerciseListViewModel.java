package com.yussefsaidi.gymroutine.ui.ExerciseList;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.yussefsaidi.gymroutine.network.ExerciseList.ExerciseListApi;

import javax.inject.Inject;

public class ExerciseListViewModel extends ViewModel {

    private static final String TAG = "ExerciseListViewModel";

    private final ExerciseListApi exerciseListApi;

    @Inject
    public ExerciseListViewModel(ExerciseListApi exerciseListApi){
        this.exerciseListApi = exerciseListApi;
        Log.d(TAG, "ExerciseListViewModel: viewmodel is working");
        
        if(this.exerciseListApi == null){
            Log.d(TAG, "ExerciseListViewModel: auth api is NULL");
        } else {
            Log.d(TAG, "ExerciseListViewModel: auth api NOT null");
        }
        
    }
}
