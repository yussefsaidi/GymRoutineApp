package com.yussefsaidi.gymroutine.ui.PickTemplate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yussefsaidi.gymroutine.BuildConfig;
import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.ui.ExerciseList.ExerciseListActivity;

public class PickTemplateActivity extends AppCompatActivity {

    private static final String TAG = "PickTemplateActivity";
    SharedPreferences prefs = null;
    String prevStarted = "prevStarted";
    boolean templateSelected = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_picktemplate_activity);
        prefs = getSharedPreferences("com.yussefsaidi.gymRoutine", MODE_PRIVATE);
        Log.d(TAG, "onCreate: templateSelected: " + templateSelected);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
        }
        else{
            startActivity(new Intent(this, ExerciseListActivity.class));
        }
    }

    public void startFullBody(View view) {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        intent.putExtra(getString(R.string.template), getString(R.string.template_fullbody));
        templateSelected = true;
        startActivity(intent);

    }

    public void startUl(View view) {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        intent.putExtra(getString(R.string.template), getString(R.string.template_upperlower));
        templateSelected = true;
        startActivity(intent);
    }

    public void startPpl(View view) {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        intent.putExtra(getString(R.string.template), getString(R.string.template_ppl));
        templateSelected = true;
        startActivity(intent);
    }
}
