package com.lherman.bob_go;

import android.view.View;
import android.widget.ImageView;

//  Have white outlines for now for testing, will eventually just match the background
public class BlankGridSpace implements GridImageThing
{
    private ImageView spaceImage, bobImage;
    private int xMoveSpeedScreen;
    private Bob bob;

    public BlankGridSpace(ImageView spaceImage)
    {
        this.spaceImage = spaceImage;
        //  Uncomment to turn BlankGridSpaces invisible when done w level testing:
        //space.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean checkCollision()
    {
        if (bob.IsJumping())
        {
            if (bobImage.getY() <= bob.getStartHeight()-bob.getJumpHeight())
            {
                bob.setJumping(false);
                bob.setFalling(true);
            }
        }
        if (bob.IsFalling())
        {
            if ((bobImage.getY()+bob.getJumpSpeed() >= bob.getLowestY()) && (bob.getLowestY() > bobImage.getY()))
            {
                bob.setFallingLittle(true);
                bob.setYLittleAmount(bob.getLowestY()-bobImage.getY());
                System.out.println("Falling little bottom BlankSpace");
            }
            //if (bobImage.getY() >= bob.getLowestY())
            else if (bobImage.getY()+bob.getJumpSpeed() >= bob.getLowestY())
            {
                bob.setFalling(false);
                System.out.println("Landed on bottom Blank Space");
            }
        }

        //  Wait... should it be true that Bob collides w nothing? Maybe switch the trues and falses?
        return true;
    }

    //  For moving the full amount
    @Override
    public void move()
    {
        spaceImage.setX(spaceImage.getX() - xMoveSpeedScreen);
    }

    //  For moving xLittle
    @Override
    public void move(float amount)
    {
        spaceImage.setX(spaceImage.getX() - amount);
    }


    @Override
    public float getImageX() {
        return spaceImage.getX();
    }

    @Override
    public void setImageX(float xImage) {
        spaceImage.setX(xImage);
    }

    @Override
    public void setImageY(float yImage) {
        spaceImage.setY(yImage);
    }

    @Override
    public float getImageY() {
        return spaceImage.getY();
    }

    @Override
    public void setImageWidth(int widthImage) {
        spaceImage.getLayoutParams().width = widthImage;
    }

    @Override
    public void setImageHeight(int heightImage) {
        spaceImage.getLayoutParams().height = heightImage;
    }

    @Override
    public void setBob(Bob bob) {
        this.bob = bob;
    }

    @Override
    public void setBobImage(ImageView bobImage) {
        this.bobImage = bobImage;
    }

    @Override
    public void setXMoveSpeedScreen(int xMoveSpeedScreen) {
        this.xMoveSpeedScreen = xMoveSpeedScreen;
    }

}
