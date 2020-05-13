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
import android.widget.RelativeLayout;
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

    TextView viewExerciseName, viewSets, viewReps, viewCategoryName;
    private LinearLayout categorySubInfo;
    EditText editExerciseName, editSets, editReps, editCategoryName;
    ExerciseRepository exerciseRepository;
    Exercise mExercise;
    private LinearLayout exerciseSubInfo, exerciseInfo;
    private Button editExerciseButton, editCategoryButton;
    private ImageButton checkExerciseContainer, checkCategoryContainer;
    View exerciseItem, categoryItem, item;
    private int mMode = EDIT_MODE_DISABLED;
    Activity activity;

    @Inject
    public ExerciseViewHolder(@NonNull View itemView, ExerciseRepository exerciseRepository) {

        super(itemView);
        this.exerciseRepository = exerciseRepository;

        // For editing exercises
        exerciseInfo = itemView.findViewById(R.id.exercise_info);
        viewExerciseName = itemView.findViewById(R.id.exercise_text_name);
        editExerciseName = itemView.findViewById(R.id.exercise_edit_name); // GONE by default
        viewSets = itemView.findViewById(R.id.exercise_sets);
        editSets = itemView.findViewById(R.id.exercise_edit_sets); // GONE by default
        viewReps = itemView.findViewById(R.id.exercise_reps);
        editReps = itemView.findViewById(R.id.exercise_edit_reps); // GONE by default
        exerciseSubInfo = itemView.findViewById(R.id.exercise_subinfo);
        editExerciseButton = itemView.findViewById(R.id.edit_name_button);
        editCategoryButton = itemView.findViewById(R.id.edit_category_button);
        checkExerciseContainer = itemView.findViewById(R.id.check_container);
        checkCategoryContainer = itemView.findViewById(R.id.category_check_container);
        item = itemView.findViewById(R.id.item);
        exerciseItem = itemView.findViewById(R.id.exercise_item);


        // For creating or editing a category
        viewCategoryName = itemView.findViewById(R.id.category_text_name);
        editCategoryName = itemView.findViewById(R.id.category_edit_name);
        categorySubInfo = itemView.findViewById(R.id.category_subinfo);

        categoryItem = itemView.findViewById(R.id.category_item);

        item.setOnClickListener(this);
        editCategoryButton.setOnClickListener(this);
        editExerciseButton.setOnClickListener(this);
        checkExerciseContainer.setOnClickListener(this);
        checkCategoryContainer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        activity = getActivity(view);
        if (view.getId() == item.getId() && mMode != EDIT_MODE_ENABLED) {

            if (mExercise.isCategory() == false) {

                if (exerciseSubInfo.getVisibility() == View.VISIBLE) {
                    exerciseSubInfo.setVisibility(View.GONE);
                } else {
                    exerciseSubInfo.setVisibility(View.VISIBLE);
                }
            }
            else if (mExercise.isCategory() == true) {

                if (categorySubInfo.getVisibility() == View.VISIBLE) {
                    categorySubInfo.setVisibility(View.GONE);
                } else {
                    categorySubInfo.setVisibility(View.VISIBLE);
                }
            }
        }

        if (view.getId() == editExerciseButton.getId() || view.getId() == editCategoryButton.getId()) {
            Log.d(TAG, "onClick: ");
            enableEditMode(activity);
        }
        // Check pressed in edit mode
        if (view.getId() == checkExerciseContainer.getId() || view.getId() == checkCategoryContainer.getId()) {
            saveChanges();
            disableEditMode(activity);
        }
        // When edit mode is enabled, clicking anywhere results in disabling edit mode
    }


    private void enableEditMode(Activity activity) {
        //Make all 3 edit texts visible
        mMode = EDIT_MODE_ENABLED;
        if(mExercise.isCategory() == false) {
            viewExerciseName.setVisibility(View.GONE);
            viewReps.setVisibility(View.GONE);
            viewSets.setVisibility(View.GONE);
            editExerciseName.setVisibility(View.VISIBLE);
            editSets.setVisibility(View.VISIBLE);
            editReps.setVisibility(View.VISIBLE);
            checkExerciseContainer.setVisibility(View.VISIBLE);
            editExerciseButton.setVisibility(View.GONE);
            editExerciseName.requestFocus();
        }
        else {
            viewCategoryName.setVisibility(View.GONE);
            editCategoryName.setVisibility(View.VISIBLE);
            checkCategoryContainer.setVisibility(View.VISIBLE);
            editCategoryButton.setVisibility(View.GONE);
            editCategoryName.requestFocus();

        }


    }

    private void disableEditMode(Activity activity) {
        mMode = EDIT_MODE_DISABLED;

        if(mExercise.isCategory() == false){
            editExerciseName.setVisibility(View.GONE);
            editSets.setVisibility(View.GONE);
            editReps.setVisibility(View.GONE);
            checkExerciseContainer.setVisibility(View.GONE);

            viewExerciseName.setVisibility(View.VISIBLE);
            viewSets.setVisibility(View.VISIBLE);
            viewReps.setVisibility(View.VISIBLE);
            editExerciseButton.setVisibility(View.VISIBLE);
            exerciseSubInfo.setVisibility(View.GONE);
        }

        else if (mExercise.isCategory() == true){
            editCategoryName.setVisibility(View.GONE);
            categorySubInfo.setVisibility(View.GONE);
            checkCategoryContainer.setVisibility(View.GONE);

            viewCategoryName.setVisibility(View.VISIBLE);
            editCategoryButton.setVisibility(View.VISIBLE);

        }



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
            if(mExercise.isCategory() == true){
                onClick(checkCategoryContainer);
            }
            if(mExercise.isCategory() == false){
                onClick(checkExerciseContainer);
            }

        } else {
            activity.onBackPressed();
        }
    }

    private void saveChanges() {
        if(mExercise.isCategory() == false){
            mExercise.setName(editExerciseName.getText().toString());
            mExercise.setSets(editSets.getText().toString());
            mExercise.setRepetitions(editReps.getText().toString());
            updateExercises(mExercise);
        }

        else if (mExercise.isCategory() == true){
            mExercise.setName(editCategoryName.getText().toString());
            updateExercises(mExercise);
        }

    }

    private void updateExercises(Exercise mExercise){
        exerciseRepository.updateExercises(mExercise)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
