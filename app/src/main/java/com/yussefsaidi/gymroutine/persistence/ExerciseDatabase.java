package com.yussefsaidi.gymroutine.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yussefsaidi.gymroutine.persistence.models.Exercise;

@Database(entities = {Exercise.class}, version = ExerciseDatabase.VERSION)
public abstract class ExerciseDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract ExerciseDao getExerciseDao();

}
