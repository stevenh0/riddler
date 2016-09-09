package com.example.steven.riddler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class TransitionActivity extends AppCompatActivity {

    boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        boolean pass = intent.getBooleanExtra(getString(R.string.intent_success), false);
        done = intent.getBooleanExtra(getString(R.string.intent_gameover), false);

        if(pass && done)
            setMessage(getString(R.string.win_message));
        else if(pass)
            setMessage(getString(R.string.pass_message));
        else
            setMessage(getString(R.string.fail_message));




    }

    public void nextLevel(View view){

        Intent intent = getIntent();
        int thisLevel = intent.getIntExtra(getString(R.string.intent_level), -1);
        Intent newIntent = new Intent(this, RiddleActivity.class);

        if(intent.getBooleanExtra(getString(R.string.intent_level), false))
            thisLevel += 1;
        if(done)
            thisLevel = 0;


        newIntent.putExtra(getString(R.string.intent_level), thisLevel);

        SharedPreferences settings = getSharedPreferences(getString(R.string.pref_key_id), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(getString(R.string.pref_level), thisLevel);
        editor.commit();

        startActivity(newIntent);
    }

    public void setMessage(String msg) {
        TextView text = (TextView) findViewById(R.id.nextLevelMessage);
        text.setText(msg);
    }



}
