package com.yussefsaidi.gymroutine.di.ExerciseList;

import com.yussefsaidi.gymroutine.network.ExerciseList.ExerciseListApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ExerciseListModule {

    @Provides
    static ExerciseListApi provideExerciseListApi(Retrofit retrofit){
        return retrofit.create(ExerciseListApi.class);
    }

}
