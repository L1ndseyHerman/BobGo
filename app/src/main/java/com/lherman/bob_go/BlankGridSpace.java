package com.lherman.bob_go;

import android.widget.ImageView;

//  Have white outlines for now for testing, will eventually just match the background
public class BlankGridSpace implements GridImageThing
{
    private ImageView space;
    private int xMoveSpeedScreen;

    public BlankGridSpace(ImageView space, int xMoveSpeedScreen)
    {
        this.space = space;
        this.xMoveSpeedScreen = xMoveSpeedScreen;
    }

    @Override
    public void checkCollision()
    {
        //  Do nothing! Can't collide with blank space.
        //  Actually, do need to move these still:
        space.setX(space.getX() - xMoveSpeedScreen);
    }

    @Override
    public float getImageX() {
        return space.getX();
    }

    @Override
    public void setImageX(float xImage) {
        space.setX(xImage);
    }

    @Override
    public float getImageY() {
        return space.getY();
    }

    @Override
    public void setImageY(float yImage) {
        space.setY(yImage);
    }

    @Override
    public int getImageWidth() {
        return space.getLayoutParams().width;
    }

    @Override
    public void setImageWidth(int widthImage) {
        space.getLayoutParams().width = widthImage;
    }

    @Override
    public int getImageHeight() {
        return space.getLayoutParams().height;
    }

    @Override
    public void setImageHeight(int heightImage) {
        space.getLayoutParams().height = heightImage;
    }

}
