package com.yussefsaidi.gymroutine.network.ExerciseList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ExerciseListApi {

    @GET
    Call<ResponseBody> getExerciseList();
}
