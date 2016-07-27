package com.example.steven.riddler;

/**
 * Created by Steven on 17/06/2016.
 */
public class Riddle {

    public String riddle;
    private String answer;

    public Riddle(String riddle, String ans){
        this.riddle = riddle;
        this.answer = ans;

    }

    public String returnAnswer(){
        return answer;
    }
}
