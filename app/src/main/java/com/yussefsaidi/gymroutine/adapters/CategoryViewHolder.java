package com.yussefsaidi.gymroutine.adapters;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.persistence.ExerciseRepository;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "CategoryViewHolder";

    TextView categoryName;
    private RelativeLayout categoryLayout;
    ExerciseRepository exerciseRepository;
    Exercise exercise;

    public CategoryViewHolder(@NonNull View itemView, ExerciseRepository exerciseRepository) {
        super(itemView);
        this.exerciseRepository = exerciseRepository;

        // For creating or editing a category
        categoryName = itemView.findViewById(R.id.exercise_category_name);
        categoryLayout = itemView.findViewById(R.id.exercise_category);
    }
}
