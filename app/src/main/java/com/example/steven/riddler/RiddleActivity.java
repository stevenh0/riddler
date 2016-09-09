package com.example.steven.riddler;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RiddleActivity extends AppCompatActivity {


    boolean pass = false;
    int level;
    int maxLevel;

    public Riddle[] riddles = new Riddle[maxLevel];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        retrieveRiddles();

        maxLevel = riddles.length - 1;
        Intent intent = getIntent();
        level = intent.getIntExtra(getString(R.string.intent_level), -1);
        if(level != -1)
            setRiddle(riddles[level]);
        else
            setRiddle(-1);


        // set listeners to invoke checkAnswer on button click and on enter key pressed
        EditText editText = (EditText) findViewById(R.id.answerArea);
        editText.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    checkAnswer();
                    return true;
                }
                return false;
            }
        });

        Button button = (Button) findViewById((R.id.submitAnswer));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        // set button to display hint
        Button hint_button = (Button) findViewById((R.id.hintButton));
        hint_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHint(riddles[level-1]);
            }
        });


    }


    // read riddle info txt file located in assets folder
    // TODO: better to load this elsewhere? loads everytime riddleActivity is called
    // TODO: put in startup activity? how to save info then

    private void retrieveRiddles(){

        String riddleInfo = "";

        // try to read riddle information file and return file in string format
        try {
            riddleInfo = loadFileFromAssets("riddleInfo.txt");
        }
        catch (IOException e){
            buildErrorMessage("setRiddles", e.getMessage());
        }

        // parse info file - information about each different riddle is seperated by three dashes
        String riddleStuff[] = riddleInfo.split("---");

        for(int i = 0 ; i < riddleStuff.length; i ++) {
            //information about each specific riddle should be written as riddle;answer;hint
            //intialize riddles array by parsing this way
            String[] info = riddleStuff[i].split(";");
            try {
                riddles[i] = new Riddle(info[0], info[1], info[2]);
            }
            catch (NullPointerException e){
                Log.e("ERROR", "Improper riddle information format");
            }
        }


    }


    // load file from assets, helper method
    public String loadFileFromAssets (String fileName) throws IOException {

        //get input stream from asset file

        Resources resources = getResources();
        InputStream iS = resources.getAssets().open(fileName);
        byte[] buffer = new byte[iS.available()];
        iS.read(buffer);
        ByteArrayOutputStream oS = new ByteArrayOutputStream();
        oS.write(buffer);
        oS.close();
        iS.close();

        //return the output stream as a String
        return oS.toString();
    }


    public void checkAnswer(){
        if(level == -1)
            buildErrorMessage("checkAnswer", "level not detected");

        Intent intent = new Intent(this, TransitionActivity.class);
        EditText editText = (EditText) findViewById(R.id.answerArea);
        String toCheck = editText.getText().toString();

        if(toCheck.equals(riddles[level].returnAnswer()))
            intent.putExtra(getString(R.string.intent_success), true);
        else
            intent.putExtra(getString(R.string.intent_success), false);

        if(level == maxLevel)
            intent.putExtra(getString(R.string.intent_gameover), true);

        intent.putExtra(getString(R.string.intent_level), level);
        startActivity(intent);

    }

    public void showHint(Riddle riddle){
        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle("Hint");
        messageBox.setMessage(riddle.returnHint());
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

    public void setRiddle(Riddle riddle){
        TextView layout = (TextView) findViewById(R.id.riddleArea);
        layout.setText(riddle.returnRiddle());
    }

    public void setRiddle(Integer i){
        buildErrorMessage("SetRiddle", "Invalid level");
    }


    public void buildErrorMessage(String method, String message){
        Log.d("EXCEPTION: " + method,  message);

        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();

    }





}
