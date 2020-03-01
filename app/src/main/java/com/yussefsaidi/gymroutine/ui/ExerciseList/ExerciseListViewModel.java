package com.yussefsaidi.gymroutine.ui.ExerciseList;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.yussefsaidi.gymroutine.persistence.ExerciseRepository;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class ExerciseListViewModel extends ViewModel {

    private static final String TAG = "ExerciseListViewModel";
    private MutableLiveData<List<Exercise>> exerciseListLiveData;
    private CompositeDisposable compositeDisposable;
    @Inject
    ExerciseRepository exerciseRepository;

    @Inject
    public ExerciseListViewModel(){

        Log.d(TAG, "ExerciseListViewModel: viewmodel is working");
        exerciseListLiveData = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }


    public LiveData<List<Exercise>> getAllExercises(){
        exerciseRepository.getAllExercises()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Exercise>>() {
                    @Override
                    public void onSuccess(List<Exercise> exercises) {
                        try{
                            Log.d(TAG, "onSuccess: Exercises Retrieved from Database");
                            Log.d(TAG, "onSuccess: " + exercises.toString());
                            onExercisesFetched(exercises);
                        } catch(Throwable ex){
                            dispose();
                            onError(ex);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });

        return exerciseListLiveData;
    }

    private void onExercisesFetched(List<Exercise> allExercises){
        exerciseListLiveData.setValue(allExercises);
    }

    private void onError(Throwable throwable){
        Log.d(TAG, "onError: " + throwable.getMessage());
    }

    public void insertExercises(Exercise... exercises){

        exerciseRepository.insertExercises(exercises)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
