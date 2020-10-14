package com.lherman.bob_go;

import android.widget.ImageView;

public class Bob
{
    /*  Bob needs to jump, so needs to have his y-value updated.
        Bob also needs to stop jumping/falling if he collides with something,
        so need all his other values: x, width, height. Needs to do dif things
        depending on what he is colliding with (square = stop moving in that direction
        (up, down, left, or right), enemy = stop timer and go to game over screen,
        win circle = stop timer and do a win screen and update level saved data to
        unlock the next level. But what he collided with will be decided in the other classes.
     */

    //  Maybe include jump speed
    //  Maybe set image here?
    //  WOW! DON'T FORGET JUMPING AND FALLING!!
    //  And Bob's image


    /*private int xBob, yBob, wBob, hBob, yJumpSpeedBob;
    private boolean jumpingNow, fallingNow;
    private ImageView bob;


    //public Bob(int xBob, int yBob, int wBob, int hBob, int yJumpSpeedBob)
    public Bob(int wBob, int hBob, ImageView bob)
    {
        this.wBob = wBob;
        this.hBob = hBob;
        this.bob = bob;
        xBob = 0;
        jumpingNow = false;
        fallingNow = false;
        yBob = 11*hBob/14;
    }*/

    private ImageView bob;
    private boolean jumpingNow, fallingNow;
    private int yJumpSpeedBob, screenWidth, screenHeight;

    public Bob(ImageView bob, int screenWidth, int screenHeight)
    {
        this.bob = bob;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        //  Most of this stuff can use the pre-coded Image View methods, don't need to
        //  write my own getters and setters.

        //bob = findViewById(R.id.bob);
        bob.setX(0);
        bob.setY(11*screenHeight/14);
        bob.getLayoutParams().height = screenHeight/7;
        bob.getLayoutParams().width = screenWidth/12;

        yJumpSpeedBob = screenHeight/98;
    }


    public void startJumpMaybe()
    {
        if (jumpingNow==false && fallingNow==false)
        {
            jumpingNow = true;
        }
    }

    public void midJumpStuff()
    {

        if (jumpingNow == true)
        {
            //bobY = bobY - height/98;
            bob.setY(bob.getY()-yJumpSpeedBob);
            //if (bobY > 151)
            //if (bobY < 3*height/7)
            //if (bobY <= 7*height/14)
            if (bob.getY() <= 6*screenHeight/14)
            {
                jumpingNow = false;
                fallingNow = true;
            }
        }
        if (fallingNow == true)
        {
            //bobY = bobY + height/98;
            bob.setY(bob.getY()+yJumpSpeedBob);
            //if (bobY > 5*height/7)
            if (bob.getY() >= 11*screenHeight/14)
            {
                fallingNow = false;
            }
        }

    }




}
