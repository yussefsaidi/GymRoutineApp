package com.yussefsaidi.gymroutine.ui.ExerciseList;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.persistence.ExerciseRepository;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;
import com.yussefsaidi.gymroutine.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ExerciseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
        final ExerciseViewHolder holder = new ExerciseViewHolder(view, exerciseRepository);
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


    public void setExercises(List<Exercise> exercises){
        mExercises = exercises;
        notifyDataSetChanged();
    }
}
