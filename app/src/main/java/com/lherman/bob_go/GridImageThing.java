package com.lherman.bob_go;

import android.widget.ImageView;

public interface GridImageThing
{
    /*  Everything in "daGrid" in LevelOneActivity will start off as this before getting cast to
        a Square, WinCircle, or BlankGridSpace.
     */

    boolean checkCollision();

    void move();
    //  This one is for moving just a little bit, like the gap amount:
    void move(float amount);

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
