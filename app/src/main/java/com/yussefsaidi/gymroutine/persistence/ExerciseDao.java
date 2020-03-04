package com.yussefsaidi.gymroutine.persistence;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yussefsaidi.gymroutine.persistence.models.Exercise;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;


@Dao
public interface ExerciseDao {

    @Insert
    Completable insertExercises(Exercise... exercises);

    @Query("SELECT * FROM exercises")
    Flowable<List<Exercise>> getAllExercises();

    @Delete
    Completable delete(Exercise... exercises);

    @Update
    Completable update(Exercise... exercises);

}
