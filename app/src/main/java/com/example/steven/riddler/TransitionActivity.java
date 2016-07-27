package com.example.steven.riddler;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        boolean pass = intent.getBooleanExtra("SUCCESS", false);
        done = intent.getBooleanExtra("GAME_OVER", false);

        if(pass && done)
            winMessage();
        else if(pass)
            successMessage();
        else
            failMessage();




    }

    public void nextLevel(View view){

        Intent intent = getIntent();
        int thisLevel = intent.getIntExtra("LEVEL", -1);
        Intent newIntent = new Intent(this, RiddleActivity.class);

        if(intent.getBooleanExtra("SUCCESS", false))
            thisLevel += 1;
        if(done)
            thisLevel = 1;


        newIntent.putExtra("LEVEL", thisLevel);

        SharedPreferences settings = getSharedPreferences("level_settings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("current_level", thisLevel);
        editor.commit();

        startActivity(newIntent);
    }

    public void winMessage() {
        TextView text = (TextView) findViewById(R.id.nextLevelMessage);
        text.setText("Congratulations, you have finished Riddler!");
    }

    public void successMessage(){
        TextView text = (TextView) findViewById(R.id.nextLevelMessage);
        text.setText("Congratulations, you got it!");

    }

    public void failMessage(){
        TextView text = (TextView) findViewById(R.id.nextLevelMessage);
        text.setText("Not quite, try again");
    }

}
