package com.yussefsaidi.gymroutine.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
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

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = "CategoryViewHolder";
    private static final int EDIT_MODE_DISABLED = 0;
    private static final int EDIT_MODE_ENABLED = 1;



    ExerciseRepository exerciseRepository;


    // For editing
    TextView viewCategoryName;
    EditText editCategoryName;
    private LinearLayout categorySubInfo;
    private Button editButton;
    private ImageButton checkContainer;
    Exercise category;
    View categoryItem;
    private int mMode = EDIT_MODE_DISABLED;

    @Inject
    public CategoryViewHolder(@NonNull View itemView, ExerciseRepository exerciseRepository) {
        super(itemView);
        this.exerciseRepository = exerciseRepository;

        // For creating or editing a category
        viewCategoryName = itemView.findViewById(R.id.category_text_name);
        editCategoryName = itemView.findViewById(R.id.category_edit_name);
        categorySubInfo = itemView.findViewById(R.id.category_subinfo);
        editButton = itemView.findViewById(R.id.edit_category_button);
        checkContainer = itemView.findViewById(R.id.category_check_container);
        categoryItem = itemView.findViewById(R.id.category_item);

        categoryItem.setOnClickListener(this);
        editButton.setOnClickListener(this);
        checkContainer.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == categoryItem.getId() && mMode != EDIT_MODE_ENABLED) {
            if (categorySubInfo.getVisibility() == View.VISIBLE) {
                categorySubInfo.setVisibility(View.GONE);
            } else
                categorySubInfo.setVisibility(View.VISIBLE);
        }

        if (view.getId() == editButton.getId()) {
            enableEditMode();
        }
        // Check pressed in edit mode
        if (view.getId() == checkContainer.getId()) {
            saveChanges();
            disableEditMode();
        }
        // When edit mode is enabled, clicking anywhere results in disabling edit mode
    }



    private void enableEditMode() {
        //Make edit text and checkmark visible
        mMode = EDIT_MODE_ENABLED;
        viewCategoryName.setVisibility(View.GONE);
        editCategoryName.setVisibility(View.VISIBLE);
        checkContainer.setVisibility(View.VISIBLE);
        editButton.setVisibility(View.GONE);
        editCategoryName.requestFocus();
    }

    private void disableEditMode() {
        mMode = EDIT_MODE_DISABLED;

        editCategoryName.setVisibility(View.GONE);
        checkContainer.setVisibility(View.GONE);

        viewCategoryName.setVisibility(View.VISIBLE);
        editButton.setVisibility(View.VISIBLE);
        categorySubInfo.setVisibility(View.GONE);

        //hideKeyboard(activity);
    }

    private void saveChanges() {
        category.setName(editCategoryName.getText().toString());
        updateCategory(category);
        Log.d(TAG, "saveChanges: UPDATE CATEGORY");
    }

    private void updateCategory(Exercise category){
        exerciseRepository.updateExercises(category)
                .subscribeOn(Schedulers.io())
                .subscribe();

    }
}
