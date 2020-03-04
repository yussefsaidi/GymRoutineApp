package com.yussefsaidi.gymroutine.ui.ExerciseList;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.yussefsaidi.gymroutine.persistence.ExerciseRepository;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class ExerciseListViewModel extends ViewModel {

    private static final String TAG = "ExerciseListViewModel";
    private MutableLiveData<List<Exercise>> exerciseListLiveData;


    @Inject
    ExerciseRepository exerciseRepository;

    @Inject
    public ExerciseListViewModel(){

        Log.d(TAG, "ExerciseListViewModel: viewmodel is working");

        exerciseListLiveData = new MutableLiveData<>();
    }


    public Flowable<List<Exercise>> getAllExercises(){
        return exerciseRepository.getAllExercises();

                        //new DisposableSingleObserver<List<Exercise>>() {
                    /*@Override
                    public void onSuccess(List<Exercise> exercises) {
                        try{
                            Log.d(TAG, "onSuccess: Exercises Retrieved from Database");
                            Log.d(TAG, "onSuccess: " + exercises.toString());
                            exerciseListLiveData.setValue(exercises);
                        } catch(Throwable ex){
                            dispose();
                            onError(ex);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });*/
    }

    public LiveData<List<Exercise>> returnExercises(){
        return exerciseListLiveData;
    }

    /*private void onExercisesFetched(List<Exercise> allExercises){
        exerciseListLiveData.setValue(allExercises);
    }*/

    private void onError(Throwable throwable){
        Log.d(TAG, "onError: " + throwable.getMessage());
    }

    public void insertExercises(Exercise... exercises){

        exerciseRepository.insertExercises(exercises);
    }
}
