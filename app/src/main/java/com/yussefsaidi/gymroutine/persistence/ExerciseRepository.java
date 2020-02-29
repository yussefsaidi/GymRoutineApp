package com.yussefsaidi.gymroutine.persistence;

import androidx.lifecycle.LiveData;

import com.yussefsaidi.gymroutine.persistence.models.Exercise;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;


@Singleton
public class ExerciseRepository {

    private final ExerciseDao exerciseDao;

    @Inject
    public ExerciseRepository(ExerciseDao exerciseDao){
        this.exerciseDao = exerciseDao;
    }


   public Single<List<Exercise>> getAllExercises(){
        return exerciseDao.getAllExercises();
    }

    public Completable insertExercises(Exercise... exercises){
        return exerciseDao.insertExercises(exercises);
    }

}
