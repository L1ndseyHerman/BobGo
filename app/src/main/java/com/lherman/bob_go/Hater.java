package com.lherman.bob_go;

import android.widget.ImageView;

public class Hater
{
    //  The red frowny-face enemies. If Bob touches one, he will become too upset to win the level,
    //  and get a Game Over.
    private ImageView haterImage, bobImage;
    private int xMoveSpeedScreen, xHaterMoveSpeed, yHaterMoveSpeed;
    private Bob bob;
    private GridImageThing[] thePath;
    private GridImageThing nextGridImageThing;

    public Hater(ImageView haterImage) {
        this.haterImage = haterImage;
    }

    public void setImageX(float xImage) {
        haterImage.setX(xImage);
    }
    public void setImageY(float yImage) {
        haterImage.setY(yImage);
    }
    public void setImageWidth(int widthImage) {
        haterImage.getLayoutParams().width = widthImage;
    }
    public void setImageHeight(int heightImage) {
        haterImage.getLayoutParams().height = heightImage;
    }
    public void setXMoveSpeedScreen(int xMoveSpeedScreen) {
        this.xMoveSpeedScreen = xMoveSpeedScreen;
    }

    public void setBob(Bob bob) {
        this.bob = bob;
    }
    public void setBobImage(ImageView bobImage) {
        this.bobImage = bobImage;
    }


    public void move()
    {
        haterImage.setX(haterImage.getX() - xMoveSpeedScreen);
    }
    public void move(float amount)
    {
        haterImage.setX(haterImage.getX() - amount);
    }

    public boolean isColliding()
    {
        //  Bob's right colliding with Hater's left:
        if ((bobImage.getX()+bobImage.getLayoutParams().width > haterImage.getX()
                && bobImage.getX() < haterImage.getX()
                && bobImage.getY() < haterImage.getY()+haterImage.getLayoutParams().height
                && bobImage.getY()+bobImage.getLayoutParams().height > haterImage.getY())
                //  Bob's left colliding with Hater's right:
                || (bobImage.getX() < haterImage.getX()+haterImage.getLayoutParams().width
                && bobImage.getX() > haterImage.getX()
                && bobImage.getY() < haterImage.getY()+haterImage.getLayoutParams().height
                && bobImage.getY()+bobImage.getLayoutParams().height > haterImage.getY())
                //  Bob's bottom colliding with Hater's top:
                || (bobImage.getY()+bobImage.getLayoutParams().height > haterImage.getY()
                && bobImage.getY() < haterImage.getY()
                && bobImage.getX() < haterImage.getX()+haterImage.getWidth()
                && bobImage.getX()+bobImage.getLayoutParams().width > haterImage.getX())
                //  Bob's top colliding with Hater's bottom:
                || (bobImage.getY() < haterImage.getY()+haterImage.getLayoutParams().height
                && bobImage.getY() > haterImage.getY()
                && bobImage.getX() < haterImage.getX()+haterImage.getLayoutParams().width
                && bobImage.getX()+bobImage.getLayoutParams().width > haterImage.getX()))
        {
            return true;
        }

        return false;
    }

    public void setPath(GridImageThing[] thePath)
    {
        this.thePath = thePath;
    }

    //  1 = the same speed as the level, 2 = twice as fast, 0.5 = half as fast, etc.
    public void setXHaterMoveSpeed(int xHaterMoveSpeed)
    {
        this.xHaterMoveSpeed = xHaterMoveSpeed;
    }
    //  Might not need unless for Bob collision, idk.
    /*public int getXHaterMoveSpeed() {
        return xHaterMoveSpeed;
    }*/
    public void setYHaterMoveSpeed(int yHaterMoveSpeed)
    {
        this.yHaterMoveSpeed = yHaterMoveSpeed;
    }
    /*public int getYHaterMoveSpeed() {
        return yHaterMoveSpeed;
    }*/

    public void setNextGridImageThing(GridImageThing nextGridImageThing)
    {
        this.nextGridImageThing = nextGridImageThing;
    }

    public GridImageThing getNextGridImageThing()
    {
        return nextGridImageThing;
    }

    //  This makes the enemy move at a constant speed on whatever path it should take, or remain stationary.
    public void movePath()
    {
        //  If there is only one GridImageThing, means the Hater is stationary, nowhere to move to.
        if (thePath.length != 1)
        {
            //  Hater needs to move to the right.
            if (nextGridImageThing.getImageX() - haterImage.getX() > 0)
            {
                if (haterImage.getX() + (xMoveSpeedScreen*xHaterMoveSpeed) <= nextGridImageThing.getImageX())
                {

                }
            }
            //  Hater needs to move to the left.
            else if (nextGridImageThing.getImageX() - haterImage.getX() < 0)
            {
                if (haterImage.getX() - (xMoveSpeedScreen*xHaterMoveSpeed) >= nextGridImageThing.getImageX())
                {

                }
            }

            //  Hater needs to move down.
            if (nextGridImageThing.getImageY() - haterImage.getY() > 0)
            {
                if (haterImage.getY() + (xMoveSpeedScreen*yHaterMoveSpeed) <= nextGridImageThing.getImageY())
                {

                }
            }
            //  Hater needs to move up.
            else if (nextGridImageThing.getImageY() - haterImage.getY() < 0)
            {
                if (haterImage.getY() - (xMoveSpeedScreen*yHaterMoveSpeed) >= nextGridImageThing.getImageY())
                {

                }
            }

            /*//  xDifference pos if Grid to right of Hater, neg if to left.
            float xDifference = nextGridImageThing.getImageX() - haterImage.getX();
            //  same w yDifference
            float yDifference = nextGridImageThing.getImageY() - haterImage.getY();

            if (xDifference>0 || xDifference<0)
            {

            }*/
        }
    }
}
