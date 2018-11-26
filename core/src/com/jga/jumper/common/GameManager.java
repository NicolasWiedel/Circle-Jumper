package com.jga.jumper.common;

import com.badlogic.gdx.math.MathUtils;

public class GameManager {

    // == constants ==
    public static final GameManager INSTANCE = new GameManager();

    // == attributes ==
    private int score;
    private int displayScore;
    private int highScore;
    private int displayHighScore;

    // == constructor ==
    private GameManager(){

    }

    // == public methods ==
    public void reset(){
        score = 0;
        displayScore = 0;
    }

    public void addScore( int amount){
        score += amount;
        if (score > highScore){
            highScore = score;
        }
    }

    public void updateDisplayScore(float delta){
        if(displayScore < score){
            displayScore = Math.min(score, displayScore + (int)(100 * delta));

        }
        if (displayHighScore < highScore){
            displayHighScore = Math.min(highScore, displayHighScore + (int)(100 * delta));

        }
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }
}
