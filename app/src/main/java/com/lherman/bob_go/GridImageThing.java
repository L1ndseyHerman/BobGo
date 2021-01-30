package com.lherman.bob_go;

import android.widget.ImageView;

public interface GridImageThing
{
    /*  Everything in "daGrid" in LevelOneActivity will start off as this before getting cast to
        a Square, WinCircle, or whatever I call empty spaces. Idk if Bob and enemies should be
        separate yet or not.
     */


    /*
        I want to do something like daGrid in LevelOneActivity does a for-loop that calls a method.
    In BlankGridSpace, the method does nothing. In SquareObstacle, that individual square will check
    to see if it's colliding with Bob. Depending on what direction, set Bob's variables, like
    "jumpingUp = false" or "onTopOfSquare = true", or whatevs. And then WinCircle will use that
    method to stop the timer and do some win thing.
     */

    //  Deleted the "public" in front of all these when the commit warnings said it was redundant.
    boolean checkCollision();
    void move();
    //  This one is for moving just a little bit, like the gap amount:
    void move(float amount);


    //  Oh shit, need all these now that daGrid isn't of type ImageView:
    float getImageX();
    void setImageX(float xImage);

    void setImageY(float yImage);

    void setImageWidth(int widthImage);

    void setImageHeight(int heightImage);

    //  Making constructors 1 param:
    void setBob(Bob bob);
    void setBobImage(ImageView bobImage);
    void setXMoveSpeedScreen(int xMoveSpeedScreen);


}
