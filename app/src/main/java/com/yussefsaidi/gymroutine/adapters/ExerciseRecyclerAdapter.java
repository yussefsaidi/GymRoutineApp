package com.yussefsaidi.gymroutine.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.persistence.ExerciseRepository;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ExerciseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // ViewHolders
    private static final int CATEGORY_TYPE = 0;
    private static final int EXERCISE_TYPE = 1;

    // vars
    private List<Exercise> mExercises = new ArrayList<>();
    private static final String TAG = "ExerciseRecyclerAdapter";
    private ExerciseRepository exerciseRepository;

    @Inject
    public ExerciseRecyclerAdapter(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // For editing
        Log.d(TAG, "onCreateViewHolder: repository instance null? " + exerciseRepository.toString());
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_exercise_list_item, parent, false);
            return new ExerciseViewHolder(view, exerciseRepository);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ExerciseViewHolder) holder).mExercise = mExercises.get(position);
        if(((ExerciseViewHolder)holder).mExercise.isCategory() == true){
            ((ExerciseViewHolder)holder).exerciseItem.setVisibility(View.GONE);
            ((ExerciseViewHolder)holder).categoryItem.setVisibility(View.VISIBLE);
            ((ExerciseViewHolder)holder).viewCategoryName.setText(mExercises.get(position).getName());
            ((ExerciseViewHolder)holder).editCategoryName.setText(mExercises.get(position).getName());

        }

        if(((ExerciseViewHolder)holder).mExercise.isCategory() == false) {
            ((ExerciseViewHolder)holder).exerciseItem.setVisibility(View.VISIBLE);
            ((ExerciseViewHolder)holder).categoryItem.setVisibility(View.GONE);
            ((ExerciseViewHolder) holder).viewExerciseName.setText(mExercises.get(position).getName());
            ((ExerciseViewHolder) holder).editExerciseName.setText(mExercises.get(position).getName());
            ((ExerciseViewHolder) holder).viewSets.setText(mExercises.get(position).getSets());
            ((ExerciseViewHolder) holder).editSets.setText(mExercises.get(position).getSets());
            ((ExerciseViewHolder) holder).viewReps.setText(mExercises.get(position).getRepetitions());
            ((ExerciseViewHolder) holder).editReps.setText(mExercises.get(position).getRepetitions());
            // Send exercise item reference to viewholder to update it
        }
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }


    public void setExercises(List<Exercise> exercises) {
        mExercises = exercises;
        notifyDataSetChanged();
    }
}
