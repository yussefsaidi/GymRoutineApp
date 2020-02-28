package com.yussefsaidi.gymroutine.network.ExerciseList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yussefsaidi.gymroutine.models.Exercise;

import java.util.List;

public class ExerciseSearchResponse {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("results")
    @Expose
    private List<Exercise> exercises;

    public int getCount(){
        return count;
    }

    public List<Exercise> getExercises(){
        return exercises;
    }


}
