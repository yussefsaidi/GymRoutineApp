package com.yussefsaidi.gymroutine.ui.ExerciseList;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.yussefsaidi.gymroutine.persistence.ExerciseRepository;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Flowable;
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
    }

    public void insertExercises(Exercise... exercises){

        exerciseRepository.insertExercises(exercises);
    }

    public void deleteExercise(Exercise exercise){
        exerciseRepository.deleteExercises(exercise)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

}
