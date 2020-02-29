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
    Single<List<Exercise>> getAllExercises();

    @Delete
    int delete(Exercise... exercises);

    @Update
    int update(Exercise... exercises);

}
