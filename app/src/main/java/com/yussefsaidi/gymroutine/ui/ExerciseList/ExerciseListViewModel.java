package com.yussefsaidi.gymroutine.ui.ExerciseList;

import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.yussefsaidi.gymroutine.models.Exercise;
import com.yussefsaidi.gymroutine.network.ExerciseList.ExerciseListApi;
import com.yussefsaidi.gymroutine.network.ExerciseList.ExerciseSearchResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ExerciseListViewModel extends ViewModel {

    private static final String TAG = "ExerciseListViewModel";

    private final ExerciseListApi exerciseListApi;

    private MediatorLiveData<ExerciseSearchResponse> exerciseList = new MediatorLiveData<>();

    @Inject
    public ExerciseListViewModel(ExerciseListApi exerciseListApi){
        this.exerciseListApi = exerciseListApi;
        Log.d(TAG, "ExerciseListViewModel: viewmodel is working");
    }

    public void getExerciseList(){
        final LiveData<ExerciseSearchResponse> source = LiveDataReactiveStreams.fromPublisher(
                exerciseListApi.getExerciseList()
                .subscribeOn(Schedulers.io())
        );

        exerciseList.addSource(source, new Observer<ExerciseSearchResponse>() {
            @Override
            public void onChanged(ExerciseSearchResponse exerciseSearchResponse) {
                exerciseList.setValue(exerciseSearchResponse);
                exerciseList.removeSource(source);
            }
        });

    }

    public LiveData<ExerciseSearchResponse> observeExerciseList(){
        return exerciseList;
    }
}
