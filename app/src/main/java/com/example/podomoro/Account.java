package com.example.podomoro;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cym on 1/17/18.
 */

public class Account {

    public static int score;
    public static HashMap<String, Integer> history = new HashMap<String, Integer>();

    public Account(){

    }


    public static int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static void addScore(){score++;}
}
