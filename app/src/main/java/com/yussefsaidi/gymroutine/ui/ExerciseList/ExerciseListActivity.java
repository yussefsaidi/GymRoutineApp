package com.yussefsaidi.gymroutine.ui.ExerciseList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.RequestManager;
import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.adapters.ExerciseRecyclerAdapter;
import com.yussefsaidi.gymroutine.adapters.ExerciseViewHolder;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;
import com.yussefsaidi.gymroutine.util.VerticalSpacingItemDecorator;
import com.yussefsaidi.gymroutine.viewmodels.ExerciseListViewModel;
import com.yussefsaidi.gymroutine.viewmodels.ViewModelProviderFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class ExerciseListActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ExerciseListActivity";

    @Inject
    ExerciseRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    RequestManager requestManager;

    // vars
    List<Exercise> exerciseList = new ArrayList<>();
    private ExerciseListViewModel viewModel;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_exercise_list);
        mRecyclerView = findViewById(R.id.recyclerView);
        viewModel = new ViewModelProvider(this, providerFactory).get(ExerciseListViewModel.class);
        initRecyclerView();
        subscribeObservers();
        findViewById(R.id.fab_add_exercise).setOnClickListener(this);
        findViewById(R.id.fab_add_category).setOnClickListener(this);
        initTemplate();
    }

    private void initTemplate() {
        Intent intent = getIntent();

        if(intent.hasExtra(getString(R.string.template))){
        String template = intent.getStringExtra(getString(R.string.template));
        if(template.equals(getString(R.string.template_ppl))){
            viewModel.createTemplatePpl();
        }
        else if(template.equals(getString(R.string.template_upperlower))) {
            viewModel.createTemplateUl();
        }
        else if(template.equals(getString(R.string.template_fullbody))){
            viewModel.createTemplateFullbody();
        }
        }
    }

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Exercise fromExercise = exerciseList.get(fromPosition);
            Exercise toExercise = exerciseList.get(toPosition);

            //Swap exercise ids
            int tempId = fromExercise.getId();
            fromExercise.setId(toExercise.getId());
            toExercise.setId(tempId);

            Collections.swap(exerciseList, fromPosition, toPosition);
            adapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteExercise(exerciseList.get(viewHolder.getAdapterPosition()));
        }

        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            //super.clearView(recyclerView, viewHolder);
            Exercise[] exercises = exerciseList.toArray(new Exercise[exerciseList.size()]);
            viewModel.updateExercises(exercises);
        }
    });

    public void subscribeObservers() {
        viewModel.getAllExercises()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<List<Exercise>>() {
                    @Override
                    public void onNext(List<Exercise> exercises) {
                        exerciseList.clear();
                        exerciseList.addAll(exercises);
                        adapter.setExercises(exercises);
                    }
                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "onError: CANT GET EXERCISES FROM DATABASE");
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        VerticalSpacingItemDecorator verticalSpacingItemDecorator = new VerticalSpacingItemDecorator(3);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(verticalSpacingItemDecorator);
        mRecyclerView.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void deleteExercise(Exercise exercise){
        viewModel.deleteExercise(exercise);
    }

    //On Click of the FAB
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab_add_exercise){
            viewModel.insertNewExercise();
        }
        else if(view.getId() == R.id.fab_add_category){
            viewModel.insertNewCategory();
        }
    }
}
