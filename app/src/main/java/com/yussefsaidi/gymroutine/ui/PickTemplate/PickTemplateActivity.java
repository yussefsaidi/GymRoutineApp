package com.yussefsaidi.gymroutine.ui.PickTemplate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.yussefsaidi.gymroutine.R;
import com.yussefsaidi.gymroutine.ui.ExerciseList.ExerciseListActivity;

public class PickTemplateActivity extends AppCompatActivity {

    String prevStarted = "prevStarted";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_picktemplate_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if(!prefs.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        }
        else {
            startActivity(new Intent(PickTemplateActivity.this, ExerciseListActivity.class));
        }
    }

    public void startFullBody(View view) {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        intent.putExtra(getString(R.string.template), getString(R.string.template_fullbody));
        startActivity(intent);
    }

    public void startUl(View view) {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        intent.putExtra(getString(R.string.template), getString(R.string.template_upperlower));
        startActivity(intent);
    }

    public void startPpl(View view) {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        intent.putExtra(getString(R.string.template), getString(R.string.template_ppl));
        startActivity(intent);
    }
}
