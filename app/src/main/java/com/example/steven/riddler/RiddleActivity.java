package com.example.steven.riddler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RiddleActivity extends AppCompatActivity {


    boolean pass = false;
    int level;
    int maxLevel = 5;

    public String[] questions = new String[]{
            "What has a face and two hands but no arms or legs?",
            "What is the easiest way to double your money?",
            "What has a thumb and four fingers but is not alive?",
            "What has to be broken before you can use it?",
            "What has a neck but no head?"};

    public String[] answers = new String[]{
            "clock",
            "mirror",
            "glove",
            "egg",
            "bottle"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle);
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
        level = intent.getIntExtra("LEVEL", -1);
        if(level != -1)
            setRiddle(questions[level-1]);
        else
            setRiddle("error");

    }

    public void checkAnswer(View view){
        Intent intent = new Intent(this, TransitionActivity.class);
        EditText editText = (EditText) findViewById(R.id.answerArea);
        String toCheck = editText.getText().toString();
        if(level != -1 && toCheck.equals(answers[level-1]))
            intent.putExtra("SUCCESS", true);
        else
            intent.putExtra("SUCCESS", false);

        if(level == maxLevel)
            intent.putExtra("GAME_OVER", true);

        intent.putExtra("LEVEL", level);
        startActivity(intent);

    }

    public void setRiddle(String riddle){

        TextView layout = (TextView) findViewById(R.id.riddleArea);
        layout.setText(riddle);
    }

}
