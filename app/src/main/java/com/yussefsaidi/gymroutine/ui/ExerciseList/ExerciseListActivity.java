package com.yussefsaidi.gymroutine.ui.ExerciseList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;
import com.yussefsaidi.gymroutine.viewmodels.ViewModelProviderFactory;
import java.util.List;
import javax.inject.Inject;
import javax.xml.validation.SchemaFactoryLoader;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class ExerciseListActivity extends DaggerAppCompatActivity {

    private static final int EDIT_MODE_DISABLED = 0;
    private static final int EDIT_MODE_ENABLED = 1;
    private static final String TAG = "ExerciseListActivity";

    @Inject
    ExerciseRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    RequestManager requestManager;

    // ui


    // vars
    LiveData<List<Exercise>> exerciseList;
    private ExerciseListViewModel viewModel;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_exercise_list);
        mRecyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
        viewModel = new ViewModelProvider(this, providerFactory).get(ExerciseListViewModel.class);
        subscribeObservers();
    }

    public void subscribeObservers() {
        viewModel.getAllExercises()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<List<Exercise>>() {
                    @Override
                    public void onNext(List<Exercise> exercises) {
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
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    public void addExercises(){
        Exercise exercise = new Exercise("TEST TEST", "5", "5");
        viewModel.insertExercises(exercise);
    }

}
