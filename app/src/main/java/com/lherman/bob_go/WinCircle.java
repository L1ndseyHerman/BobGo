package com.lherman.bob_go;

import android.widget.ImageView;

//  A green circle. If Bob touches it, he wins the level.
public class WinCircle implements GridImageThing
{
    @Override
    public boolean checkCollision()
    {
        //  Do the code for stopping the timer and winning the game later.
        return true;
    }

    //  Whoa! It put it above the other methods like where I have this in the other classes! :D
    @Override
    public void move() {

    }

    @Override
    public void move(float amount) {

    }

    @Override
    public float getImageX() {
        return 0;
    }

    @Override
    public void setImageX(float xImage) {

    }

    @Override
    public void setImageY(float yImage) {

    }

    @Override
    public void setImageWidth(int widthImage) {

    }

    @Override
    public void setImageHeight(int heightImage) {

    }

    @Override
    public void setBob(Bob bob) {

    }

    @Override
    public void setBobImage(ImageView bobImage) {

    }

    @Override
    public void setXMoveSpeedScreen(int xMoveSpeedScreen) {

    }

}
