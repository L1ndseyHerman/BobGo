package com.lherman.bob_go;

import android.widget.ImageView;

public interface GridImageThing
{
    /*  Everything in "daGrid" in the level activities will start off as this before getting cast to
        a SquareObstacle or BlankGridSpace.
     */

    boolean checkCollision();

    void move();
    //  This one is for moving just a little bit, like the gap amount:
    void move(float amount);

    float getImageX();
    void setImageX(float xImage);
    void setImageY(float yImage);
    float getImageY();
    void setImageWidth(int widthImage);
    void setImageHeight(int heightImage);

    void setBob(Bob bob);
    void setBobImage(ImageView bobImage);
    void setXMoveSpeedScreen(int xMoveSpeedScreen);
}
