package com.yussefsaidi.gymroutine.di;

import android.app.Application;

import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.persistence.ExerciseDao;
import com.yussefsaidi.gymroutine.persistence.ExerciseDatabase;
import com.yussefsaidi.gymroutine.util.Constants;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    public static final String DATABASE_NAME = "exercises";
    @Singleton
    @Provides
    public ExerciseDatabase provideRoomDatabase(Application application){
        return Room.databaseBuilder(application, ExerciseDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    public ExerciseDao provideExerciseDao(ExerciseDatabase exerciseDatabase){
        return exerciseDatabase.getExerciseDao();
    }

    /*@Singleton
    @Provides
    ExerciseRepository provideRepository(ExerciseDao exerciseDao){
        return new ExerciseRepository(exerciseDao);
    }*/


}
