package com.jga.jumper.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import com.jga.jumper.config.GameConfig;

import javax.print.DocFlavor;

public class FloatingScore implements Pool.Poolable {

    // == attributes ==
    private Color color = Color.WHITE.cpy();

    private int score;
    private float startX;
    private float startY;
    private float x;
    private float y;
    private float timer;

    // == constructor ==
    public FloatingScore() {

    }

    // == public methods==
    public void update(float delta){
        if(isFinished()){
            return;
        }

        timer += delta;

        float percentage = timer / GameConfig.FLOATING_DURATION;
        float alpha = MathUtils.clamp(1.0f - percentage, 0, 1f);

        x = startX;
        y = startY + (percentage) * 60;
        color.a = alpha;
    }

    public boolean isFinished(){
        return timer >= GameConfig.FLOATING_DURATION;
    }

    public void setStartPosition(float startX, float startY){
        this.startX = startX;
        this.startY = startY;
    }

    public void setScore(int score){
        this.score = score;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public String getScoreString(){
        return Integer.toString(score);
    }

    @Override
    public void reset() {
        score= 0;
        startX = 0;
        startY = 0;
        timer = 0;
        x = 0;
        y = 0;
        color = Color.WHITE.cpy();
    }
}
