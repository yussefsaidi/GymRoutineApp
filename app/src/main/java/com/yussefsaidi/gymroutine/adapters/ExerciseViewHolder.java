package com.yussefsaidi.gymroutine.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.persistence.ExerciseRepository;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;
import javax.inject.Inject;
import io.reactivex.schedulers.Schedulers;

public class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = "ExerciseViewHolder";
    private static final int EDIT_MODE_DISABLED = 0;
    private static final int EDIT_MODE_ENABLED = 1;


    TextView viewExerciseName, viewSets, viewReps;
    EditText editExerciseName, editSets, editReps;
    ExerciseRepository exerciseRepository;

    Exercise mExercise;
    private LinearLayout exerciseSubInfo;
    private Button editButton;
    private ImageButton checkContainer;


    View exerciseItem;
    private int mMode = EDIT_MODE_DISABLED;
    Activity activity;

    @Inject
    public ExerciseViewHolder(@NonNull View itemView, ExerciseRepository exerciseRepository) {

        super(itemView);
        this.exerciseRepository = exerciseRepository;

        // For editing

        viewExerciseName = itemView.findViewById(R.id.exercise_text_name);
        editExerciseName = itemView.findViewById(R.id.exercise_edit_name); // GONE by default
        viewSets = itemView.findViewById(R.id.exercise_sets);
        editSets = itemView.findViewById(R.id.exercise_edit_sets); // GONE by default
        viewReps = itemView.findViewById(R.id.exercise_reps);
        editReps = itemView.findViewById(R.id.exercise_edit_reps); // GONE by default


        exerciseSubInfo = itemView.findViewById(R.id.exercise_subinfo);
        editButton = itemView.findViewById(R.id.edit_name_button);
        checkContainer = itemView.findViewById(R.id.check_container);
        exerciseItem = itemView.findViewById(R.id.exercise_item);
        exerciseItem.setOnClickListener(this);
        editButton.setOnClickListener(this);
        checkContainer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        activity = getActivity(view);
        if (view.getId() == exerciseItem.getId() && mMode != EDIT_MODE_ENABLED) {
            if (exerciseSubInfo.getVisibility() == View.VISIBLE) {
                exerciseSubInfo.setVisibility(View.GONE);
            } else
                exerciseSubInfo.setVisibility(View.VISIBLE);
        }

        if (view.getId() == editButton.getId()) {
            enableEditMode(activity);
        }
        // Check pressed in edit mode
        if (view.getId() == checkContainer.getId()) {
            saveChanges();
            disableEditMode(activity);
        }
        // When edit mode is enabled, clicking anywhere results in disabling edit mode
    }


    private void enableEditMode(Activity activity) {
        //Make all 3 edit texts visible
        mMode = EDIT_MODE_ENABLED;
        viewExerciseName.setVisibility(View.GONE);
        viewReps.setVisibility(View.GONE);
        viewSets.setVisibility(View.GONE);
        editExerciseName.setVisibility(View.VISIBLE);
        editSets.setVisibility(View.VISIBLE);
        editReps.setVisibility(View.VISIBLE);


        checkContainer.setVisibility(View.VISIBLE);
        editButton.setVisibility(View.GONE);
        editExerciseName.requestFocus();
    }

    private void disableEditMode(Activity activity) {
        mMode = EDIT_MODE_DISABLED;

        editExerciseName.setVisibility(View.GONE);
        editSets.setVisibility(View.GONE);
        editReps.setVisibility(View.GONE);
        checkContainer.setVisibility(View.GONE);

        viewExerciseName.setVisibility(View.VISIBLE);
        viewSets.setVisibility(View.VISIBLE);
        viewReps.setVisibility(View.VISIBLE);
        editButton.setVisibility(View.VISIBLE);
        exerciseSubInfo.setVisibility(View.GONE);

        //hideKeyboard(activity);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private void onBackPressed(Activity activity) {
        if (mMode == EDIT_MODE_ENABLED) {
            onClick(checkContainer);
        } else {
            activity.onBackPressed();
        }
    }

    private void saveChanges() {
        mExercise.setName(editExerciseName.getText().toString());
        mExercise.setSets(editSets.getText().toString());
        mExercise.setRepetitions(editReps.getText().toString());
        updateExercises(mExercise);
        Log.d(TAG, "saveChanges: UPDATE ITEM");
    }

    private void updateExercises(Exercise mExercise){
        exerciseRepository.updateExercises(mExercise)
                .subscribeOn(Schedulers.io())
                .subscribe();

    }
}
