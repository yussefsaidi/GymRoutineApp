package com.yussefsaidi.gymroutine.network.ExerciseList;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ExerciseListApi {

    @GET("exercise/?language=2&status=2")
    Flowable<ExerciseSearchResponse> getExerciseList();
}
