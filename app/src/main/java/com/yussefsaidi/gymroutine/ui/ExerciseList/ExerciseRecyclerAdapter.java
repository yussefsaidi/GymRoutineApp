package com.yussefsaidi.gymroutine.ui.ExerciseList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;

import java.util.ArrayList;

public class ExerciseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Exercise> mExercises;
    private static final String TAG = "ExerciseRecyclerAdapter";

    public ExerciseRecyclerAdapter(ArrayList<Exercise> exercises) {
        this.mExercises = exercises;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_exercise_list_item, parent, false);
        final ExerciseViewHolder holder = new ExerciseViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ExerciseViewHolder)holder).mViewName.setText(mExercises.get(position).getName());
        ((ExerciseViewHolder)holder).mEditName.setText(mExercises.get(position).getName());
        ((ExerciseViewHolder)holder).mViewSets.setText(mExercises.get(position).getSets());
        ((ExerciseViewHolder)holder).mEditSets.setText(mExercises.get(position).getSets());
        ((ExerciseViewHolder)holder).mViewReps.setText(mExercises.get(position).getRepetitions());
        ((ExerciseViewHolder)holder).mEditReps.setText(mExercises.get(position).getRepetitions());

        // Send exercise item reference to viewholder to update it
        ((ExerciseViewHolder)holder).mExercise = mExercises.get(position);
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }
}