package com.yussefsaidi.gymroutine.ui.ExerciseList;

import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.yussefsaidi.gymroutine.models.Exercise;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ExerciseListViewModel extends ViewModel {

    private static final String TAG = "ExerciseListViewModel";

    @Inject
    public ExerciseListViewModel(){
        Log.d(TAG, "ExerciseListViewModel: viewmodel is working");
    }
}
