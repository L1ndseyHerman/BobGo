package com.lherman.bob_go;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;

//  A green circle. If Bob touches it, he wins the level.
public class WinCircle
{
    private ImageView circleImage, bobImage;
    private int xMoveSpeedScreen;
    private Bob bob;

    private Timer timer;

    public WinCircle(ImageView circleImage)
    {
        this.circleImage = circleImage;
        this.timer = timer;
    }

    public boolean checkCollision()
    {
        //  Stop the timer and beat the level.

        //  Bob's right colliding with WinCircle's left:
        if ((bobImage.getX()+bobImage.getLayoutParams().width > circleImage.getX()
                && bobImage.getX() < circleImage.getX()
                && bobImage.getY() < circleImage.getY()+circleImage.getLayoutParams().height
                && bobImage.getY()+bobImage.getLayoutParams().height > circleImage.getY())
                //  Bob's left colliding with WinCircle's right:
                || (bobImage.getX() < circleImage.getX()+circleImage.getLayoutParams().width
                && bobImage.getX() > circleImage.getX()
                && bobImage.getY() < circleImage.getY()+circleImage.getLayoutParams().height
                && bobImage.getY()+bobImage.getLayoutParams().height > circleImage.getY())
                //  Bob's bottom colliding with WinCircle's top:
                || (bobImage.getY()+bobImage.getLayoutParams().height > circleImage.getY()
                && bobImage.getY() < circleImage.getY()
                && bobImage.getX() < circleImage.getX()+circleImage.getWidth()
                && bobImage.getX()+bobImage.getLayoutParams().width > circleImage.getX())
                //  Bob's top colliding with WinCircle's bottom:
                || (bobImage.getY() < circleImage.getY()+circleImage.getLayoutParams().height
                && bobImage.getY() > circleImage.getY()
                && bobImage.getX() < circleImage.getX()+circleImage.getLayoutParams().width
                && bobImage.getX()+bobImage.getLayoutParams().width > circleImage.getX()))
        {
            return true;

            //  STOP THE TIMER!!!
            //timer.cancel();
            //return false;
        }

        return false;
        //return true;
    }

    public void move() {
        circleImage.setX(circleImage.getX() - xMoveSpeedScreen);
    }

    public void move(float amount) {
        circleImage.setX(circleImage.getX() - amount);
    }

    public float getImageX() {
        return circleImage.getX();
    }

    public void setImageX(float xImage)
    {
        circleImage.setX(xImage);
    }

    public void setImageY(float yImage) {
        circleImage.setY(yImage);
    }

    public float getImageY() {
        return circleImage.getY();
    }

    public void setImageWidth(int widthImage) {
        circleImage.getLayoutParams().width = widthImage;
    }

    public void setImageHeight(int heightImage) {
        circleImage.getLayoutParams().height = heightImage;
    }

    public void setBob(Bob bob) {
        this.bob = bob;
    }

    public void setBobImage(ImageView bobImage) {
        this.bobImage = bobImage;
    }

    public void setXMoveSpeedScreen(int xMoveSpeedScreen) {
        this.xMoveSpeedScreen = xMoveSpeedScreen;
    }

}
