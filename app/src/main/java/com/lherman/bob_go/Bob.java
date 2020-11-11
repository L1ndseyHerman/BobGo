package com.lherman.bob_go;

import android.widget.ImageView;

//  The adorable main character that the user controls.
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

    //  The ImageView of Bob from activity_level_one.xml, or whatever other levels use.
    private ImageView bobImage;
    //  These will change in the SquareObstacle class when Bob lands on squares and things.
    private boolean jumpingNow, fallingNow, onTopOfSquare;
    //  These always stay the same after they get declared in the constructor
    private int yJumpSpeedBob, screenWidth, screenHeight, jumpHeightBob, lowestBobY;
    //  This changes every time Bob.startJumpMaybe(); , it's his current y-value when the jump starts.
    private float startHeightBob;

    public Bob(ImageView bobImage, int screenWidth, int screenHeight)
    {
        this.bobImage = bobImage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        //  Most of this stuff can use the pre-coded Image View methods, don't need to
        //  write my own getters and setters.

        //bob = findViewById(R.id.bob);
        bobImage.setX(0);
        //bob.setY(11*screenHeight/14);
        //  Put the number for the bottom of the screen in one place:
        lowestBobY = 11*screenHeight/14;
        bobImage.setY(lowestBobY);
        bobImage.getLayoutParams().height = screenHeight/7;
        bobImage.getLayoutParams().width = screenWidth/12;

        yJumpSpeedBob = screenHeight/98;

        jumpingNow = false;
        fallingNow = false;
        onTopOfSquare = false;

        //  How high Bob will jump before he starts falling:
        jumpHeightBob = 5*screenHeight/14;
    }


    public void startJumpMaybe()
    {
        if (jumpingNow==false && fallingNow==false)
        {
            jumpingNow = true;
            startHeightBob = bobImage.getY();
        }
    }

    //  Made some changes when coding SquareObstacle:
    /*public void midJumpStuff()
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

    }*/

    public boolean getJumpingNow()
    {
        return jumpingNow;
    }
    public void setJumpingNow(boolean jumpingNow)
    {
        this.jumpingNow = jumpingNow;
    }


    public boolean getFallingNow()
    {
        return fallingNow;
    }
    public void setFallingNow(boolean fallingNow)
    {
        this.fallingNow = fallingNow;
    }

    public boolean getOnTopOfSquare()
    {
        return onTopOfSquare;
    }
    public void setOnTopOfSquare(boolean onTopOfSquare)
    {
        this.onTopOfSquare = onTopOfSquare;
    }

    public int getBobJumpSpeed()
    {
        return yJumpSpeedBob;
    }
    //  Should never need to change the jump speed from a SquareObstacle, so no setter.

    //  Whoa! The IDE suggested this method name! How did it know I'd do a getter for that?!
    public float getStartHeightBob()
    {
        return startHeightBob;
    }
    //  Once again, no setter. Bob finds out his own height when the user taps screen.

    public int getJumpHeightBob()
    {
        return jumpHeightBob;
    }

    //  AGAIN! This is where Bob should stop falling to avoid going off the bottom of the screen:
    public int getLowestBobY()
    {
        return lowestBobY;
    }




}
