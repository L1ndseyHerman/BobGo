package com.lherman.bob_go;

import android.view.View;
import android.widget.ImageView;

//  Have white outlines for now for testing, will eventually just match the background
public class BlankGridSpace implements GridImageThing
{
    private ImageView space, bobImage;
    private int xMoveSpeedScreen;
    private Bob bob;

    public BlankGridSpace(ImageView space, int xMoveSpeedScreen, ImageView bobImage, Bob bob)
    {
        this.space = space;
        this.xMoveSpeedScreen = xMoveSpeedScreen;
        this.bobImage = bobImage;
        this.bob = bob;
        //  Uncomment to turn BlankGridSpaces invisible when done w level testing:
        //space.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean checkCollision()
    {
        if (bob.IsJumping())
        {
            if (bobImage.getY() <= bob.getStartHeightBob()-bob.getJumpHeightBob())
            {
                bob.setJumping(false);
                bob.setFalling(true);
            }
        }
        if (bob.IsFalling())
        {
            if (bobImage.getY() >= bob.getLowestBobY())
            {
                bob.setFalling(false);
            }
        }

        //  Wait... should it be true that Bob collides w nothing? Maybe switch the trues and falses?
        return true;
    }

    @Override
    public void move()
    {
        space.setX(space.getX() - xMoveSpeedScreen);
    }

    @Override
    public void move(float amount)
    {
        space.setX(space.getX() - amount);
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
