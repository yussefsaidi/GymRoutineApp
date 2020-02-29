package com.yussefsaidi.gymroutine.di;

import android.app.Application;

import androidx.room.Room;

import com.yussefsaidi.gymroutine.persistence.ExerciseDao;
import com.yussefsaidi.gymroutine.persistence.ExerciseDatabase;
import com.yussefsaidi.gymroutine.persistence.ExerciseRepository;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private ExerciseDatabase exerciseDatabase;
    public static final String DATABASE_NAME = "exercises";


    @Singleton
    @Provides
    ExerciseDatabase providesRoomDatabase(Application application){
        return Room.databaseBuilder(application, ExerciseDatabase.class, DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    ExerciseDao providesExerciseDao(ExerciseDatabase exerciseDatabase){
        return exerciseDatabase.getExerciseDao();
    }

    @Singleton
    @Provides
    ExerciseRepository provideRepository(ExerciseDao exerciseDao){
        return new ExerciseRepository(exerciseDao);
    }
}
