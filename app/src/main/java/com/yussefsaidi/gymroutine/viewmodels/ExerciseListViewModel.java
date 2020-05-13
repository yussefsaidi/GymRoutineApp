package com.yussefsaidi.gymroutine.viewmodels;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.yussefsaidi.gymroutine.persistence.ExerciseRepository;
import com.yussefsaidi.gymroutine.persistence.models.Exercise;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public class ExerciseListViewModel extends ViewModel {

    private static final String TAG = "ExerciseListViewModel";
    private MutableLiveData<List<Exercise>> exerciseListLiveData;


    @Inject
    ExerciseRepository exerciseRepository;

    @Inject
    public ExerciseListViewModel(){

        Log.d(TAG, "ExerciseListViewModel: viewmodel is working");
        exerciseListLiveData = new MutableLiveData<>();
    }


    public Flowable<List<Exercise>> getAllExercises(){
        return exerciseRepository.getAllExercises();
    }

    public void insertNewExercise(){
        Exercise exercise = new Exercise();
        exercise.setName("PlaceHolder");
        exercise.setRepetitions("5");
        exercise.setSets("5");
        exercise.setCategory(false);
        exerciseRepository.insertExercises(exercise)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void insertNewCategory(){
        Exercise exercise = new Exercise();
        exercise.setName("CATEGORY");
        exercise.setRepetitions("0");
        exercise.setSets("20");
        exercise.setCategory(true);
        exerciseRepository.insertExercises(exercise)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void deleteExercise(Exercise exercise){
        exerciseRepository.deleteExercises(exercise)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void updateExercises(Exercise... exercises){
        exerciseRepository.updateExercises(exercises)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void createTemplatePpl(){
        List<Exercise> exerciseList = new ArrayList<Exercise>();

        // Create categories
        Exercise categoryPull = new Exercise();
        Exercise categoryLegs = new Exercise();
        Exercise categoryPush = new Exercise();
        categoryPush.setName("PUSH (DAY 2)");
        categoryPush.setCategory(true);
        categoryPull.setName("PULL (DAY 1)");
        categoryPull.setCategory(true);
        categoryLegs.setName("LEGS (DAY 3)");
        categoryLegs.setCategory(true);

        // Create Pull Exercises
        Exercise pullExercise1 = new Exercise();
        pullExercise1.setName("DeadLift");
        pullExercise1.setSets("2");
        pullExercise1.setRepetitions("5");
        Exercise pullExercise2 = new Exercise();
        pullExercise2.setName("Wide Grip PullUps");
        pullExercise2.setSets("3");
        pullExercise2.setRepetitions("10");
        Exercise pullExercise3 = new Exercise();
        pullExercise3.setName("Rows");
        pullExercise3.setSets("3");
        pullExercise3.setRepetitions("10");
        Exercise pullExercise4 = new Exercise();
        pullExercise4.setName("Dumbbell Curls");
        pullExercise4.setSets("3");
        pullExercise4.setRepetitions("10");
        Exercise pullExercise5 = new Exercise();
        pullExercise5.setName("Sideway Curls");
        pullExercise5.setSets("3");
        pullExercise5.setRepetitions("8");


        // Create Push Exercises
        Exercise pushExercise1 = new Exercise();
        pushExercise1.setName("Flat Barbell BenchPress");
        pushExercise1.setSets("5");
        pushExercise1.setRepetitions("5");
        Exercise pushExercise2 = new Exercise();
        pushExercise2.setName("Incline Dumbbell BenchPress");
        pushExercise2.setSets("3");
        pushExercise2.setRepetitions("8-12");
        Exercise pushExercise3 = new Exercise();
        pushExercise3.setName("Overhead Press");
        pushExercise3.setSets("3");
        pushExercise3.setRepetitions("8-12");
        Exercise pushExercise4 = new Exercise();
        pushExercise4.setName("Triceps PullDowns");
        pushExercise4.setSets("3");
        pushExercise4.setRepetitions("8-12");
        Exercise pushExercise5 = new Exercise();
        pushExercise5.setName("Triceps PushAways");
        pushExercise5.setSets("3");
        pushExercise5.setRepetitions("8-12");

        // Create Legs Exercises
        Exercise legExercise1 = new Exercise();
        legExercise1.setName("Barbell Squat");
        legExercise1.setSets("5");
        legExercise1.setRepetitions("5");

        Exercise legExercise2 = new Exercise();
        legExercise2.setName("Romanian DeadLift");
        legExercise2.setSets("3");
        legExercise2.setRepetitions("8-12");

        Exercise legExercise3 = new Exercise();
        legExercise3.setName("Dumbbell Lunges");
        legExercise3.setSets("3");
        legExercise3.setRepetitions("8-12");

        Exercise legExercise4 = new Exercise();
        legExercise4.setName("Calf Presses");
        legExercise4.setSets("3");
        legExercise4.setRepetitions("8-12");

        // Adding exercises to list
        exerciseList.add(categoryPull);
        exerciseList.add(pullExercise1);
        exerciseList.add(pullExercise2);
        exerciseList.add(pullExercise3);
        exerciseList.add(pullExercise4);
        exerciseList.add(pullExercise5);
        exerciseList.add(categoryPush);
        exerciseList.add(pushExercise1);
        exerciseList.add(pushExercise2);
        exerciseList.add(pushExercise3);
        exerciseList.add(pushExercise4);
        exerciseList.add(pushExercise5);
        exerciseList.add(categoryLegs);
        exerciseList.add(legExercise1);
        exerciseList.add(legExercise2);
        exerciseList.add(legExercise3);
        exerciseList.add(legExercise4);

        Exercise[] exercises = exerciseList.toArray(new Exercise[exerciseList.size()]);
        exerciseRepository.insertExercises(exercises)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void createTemplateUl(){
        List<Exercise> exerciseList = new ArrayList<Exercise>();

        // Create categories
        Exercise categoryUpper = new Exercise();
        Exercise categoryLower = new Exercise();
        categoryUpper.setName("UPPER BODY (DAY 1)");
        categoryUpper.setCategory(true);
        categoryLower.setName("LOWER BODY (DAY 2)");
        categoryLower.setCategory(true);
        // Create Upper Body Exercises
        Exercise upperExercise1 = new Exercise();
        upperExercise1.setName("Flat Barbell Bench Press");
        upperExercise1.setSets("5");
        upperExercise1.setRepetitions("5");
        Exercise upperExercise2 = new Exercise();
        upperExercise2.setName("Overhead Press");
        upperExercise2.setSets("3");
        upperExercise2.setRepetitions("8-12");
        Exercise upperExercise3 = new Exercise();
        upperExercise3.setName("Incline Dumbbell Bench Press");
        upperExercise3.setSets("3");
        upperExercise3.setRepetitions("8-12");
        Exercise upperExercise4 = new Exercise();
        upperExercise4.setName("PullDowns");
        upperExercise4.setSets("3");
        upperExercise4.setRepetitions("8-12");
        Exercise upperExercise5 = new Exercise();
        upperExercise5.setName("Rows");
        upperExercise5.setSets("3");
        upperExercise5.setRepetitions("8-12");
        Exercise upperExercise6 = new Exercise();
        upperExercise6.setName("Biceps Curls");
        upperExercise6.setSets("3");
        upperExercise6.setRepetitions("8-12");
        Exercise upperExercise7 = new Exercise();
        upperExercise7.setName("Biceps Side Curls");
        upperExercise7.setSets("3");
        upperExercise7.setRepetitions("8-12");
        Exercise upperExercise8 = new Exercise();
        upperExercise8.setName("Triceps PushDowns");
        upperExercise8.setSets("3");
        upperExercise8.setRepetitions("8-12");
        Exercise upperExercise9 = new Exercise();
        upperExercise9.setName("Triceps PushAway");
        upperExercise9.setSets("3");
        upperExercise9.setRepetitions("8-12");
        // Create Lower Body Exercises
        Exercise lowerExercise1 = new Exercise();
        lowerExercise1.setName("Squats");
        lowerExercise1.setSets("5");
        lowerExercise1.setRepetitions("5");
        Exercise lowerExercise2 = new Exercise();
        lowerExercise2.setName("Romanian DeadLifts");
        lowerExercise2.setSets("3");
        lowerExercise2.setRepetitions("8-12");
        Exercise lowerExercise3 = new Exercise();
        lowerExercise3.setName("Lunges");
        lowerExercise3.setSets("3");
        lowerExercise3.setRepetitions("8-12");
        Exercise lowerExercise4 = new Exercise();
        lowerExercise4.setName("Calf Raises");
        lowerExercise4.setSets("5");
        lowerExercise4.setRepetitions("8-12");
        // Adding categories and exercises to list
        exerciseList.add(categoryUpper);
        exerciseList.add(upperExercise1);
        exerciseList.add(upperExercise2);
        exerciseList.add(upperExercise3);
        exerciseList.add(upperExercise4);
        exerciseList.add(upperExercise5);
        exerciseList.add(upperExercise6);
        exerciseList.add(upperExercise7);
        exerciseList.add(upperExercise8);
        exerciseList.add(upperExercise9);
        exerciseList.add(categoryLower);
        exerciseList.add(lowerExercise1);
        exerciseList.add(lowerExercise2);
        exerciseList.add(lowerExercise3);
        exerciseList.add(lowerExercise4);

        // Convert to array and insert into database
        Exercise[] exercises = exerciseList.toArray(new Exercise[exerciseList.size()]);
        exerciseRepository.insertExercises(exercises)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void createTemplateFullbody(){

        List<Exercise> exerciseList = new ArrayList<Exercise>();

        Exercise categoryFullBody = new Exercise();
        categoryFullBody.setName("FULL BODY DAY");
        categoryFullBody.setCategory(true);

        Exercise fullExercise1 = new Exercise();
        fullExercise1.setName("Squats");
        fullExercise1.setSets("5");
        fullExercise1.setRepetitions("5");
        Exercise fullExercise2 = new Exercise();
        fullExercise2.setName("Romanian DeadLift");
        fullExercise2.setSets("3");
        fullExercise2.setRepetitions("8-10");
        Exercise fullExercise3 = new Exercise();
        fullExercise3.setName("Calf Raises");
        fullExercise3.setSets("3");
        fullExercise3.setRepetitions("8-10");
        Exercise fullExercise4 = new Exercise();
        fullExercise4.setName("Flat Barbell BenchPress");
        fullExercise4.setSets("3");
        fullExercise4.setRepetitions("8-10");
        Exercise fullExercise5 = new Exercise();
        fullExercise5.setName("Overhead Press");
        fullExercise5.setSets("3");
        fullExercise5.setRepetitions("8-10");
        Exercise fullExercise6 = new Exercise();
        fullExercise6.setName("Pulldown Rows");
        fullExercise6.setSets("3");
        fullExercise6.setRepetitions("8-10");
        Exercise fullExercise7 = new Exercise();
        fullExercise7.setName("Rows");
        fullExercise7.setSets("3");
        fullExercise7.setRepetitions("8-10");
        Exercise fullExercise8 = new Exercise();
        fullExercise8.setName("Triceps PushDowns");
        fullExercise8.setSets("3");
        fullExercise8.setRepetitions("8-10");
        Exercise fullExercise9 = new Exercise();
        fullExercise9.setName("Dumbbell Curls");
        fullExercise9.setSets("3");
        fullExercise9.setRepetitions("8-10");

        // Adding categories and exercises to list
        exerciseList.add(categoryFullBody);
        exerciseList.add(fullExercise1);
        exerciseList.add(fullExercise2);
        exerciseList.add(fullExercise3);
        exerciseList.add(fullExercise4);
        exerciseList.add(fullExercise5);
        exerciseList.add(fullExercise6);
        exerciseList.add(fullExercise7);
        exerciseList.add(fullExercise8);
        exerciseList.add(fullExercise9);

        // Convert to array and insert into database
        Exercise[] exercises = exerciseList.toArray(new Exercise[exerciseList.size()]);
        exerciseRepository.insertExercises(exercises)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
