package com.example.steven.riddler;

/**
 * Created by Steven on 17/06/2016.
 */
public class Riddle {

    private String riddle;
    private String answer;
    private String hint;

    public Riddle(){
        riddle = "";
        answer = "";
        hint = "";

    }

    public Riddle(String r, String ans, String h){
        this.riddle = r;
        this.answer = ans;
        this.hint = h;
    }

    public String returnAnswer(){
        return answer;
    }

    public String returnRiddle(){
        return riddle;
    }

    public String returnHint(){
        return hint;
    }


    public void setAnswer(String ans){
        this.answer = ans;
    }

    public void setRiddle(String rid){
        this.riddle = rid;
    }

    public void setHint(String h){
        this.hint = h;
    }


}
