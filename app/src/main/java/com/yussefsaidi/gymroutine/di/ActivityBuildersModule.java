package com.yussefsaidi.gymroutine.di;

import com.yussefsaidi.gymroutine.di.ExerciseList.ExerciseListModule;
import com.yussefsaidi.gymroutine.di.ExerciseList.ExerciseListViewModelsModule;
import com.yussefsaidi.gymroutine.ui.ExerciseList.ExerciseListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {ExerciseListViewModelsModule.class, ExerciseListModule.class}
    )
    abstract ExerciseListActivity contributeExerciseListActivity();

}
