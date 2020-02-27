package com.yussefsaidi.gymroutine.di.ExerciseList;

import androidx.lifecycle.ViewModel;

import com.yussefsaidi.gymroutine.di.ViewModelKey;
import com.yussefsaidi.gymroutine.ui.ExerciseList.ExerciseListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ExerciseListViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseListViewModel.class)
    public abstract ViewModel bindExerciseListViewModel(ExerciseListViewModel viewModel);
}
