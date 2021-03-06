package com.lherman.bob_go;

import android.widget.ImageView;

public class Hater
{
    //  The red frowny-face enemies. If Bob gets too close to one, he will become too upset to continue the level,
    //  and gets a Game Over.
    private final ImageView haterImage;
    private ImageView bobImage;
    private int xMoveSpeedScreen, pathIndex;
    private float xHaterMoveSpeed, yHaterMoveSpeed;
    private float[] xHaterMoveSpeeds, yHaterMoveSpeeds;
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
        return (bobImage.getX() + bobImage.getLayoutParams().width > haterImage.getX()
                && bobImage.getX() < haterImage.getX()
                && bobImage.getY() < haterImage.getY() + haterImage.getLayoutParams().height
                && bobImage.getY() + bobImage.getLayoutParams().height > haterImage.getY())
                //  Bob's left colliding with Hater's right:
                || (bobImage.getX() < haterImage.getX() + haterImage.getLayoutParams().width
                && bobImage.getX() > haterImage.getX()
                && bobImage.getY() < haterImage.getY() + haterImage.getLayoutParams().height
                && bobImage.getY() + bobImage.getLayoutParams().height > haterImage.getY())
                //  Bob's bottom colliding with Hater's top:
                || (bobImage.getY() + bobImage.getLayoutParams().height > haterImage.getY()
                && bobImage.getY() < haterImage.getY()
                && bobImage.getX() < haterImage.getX() + haterImage.getWidth()
                && bobImage.getX() + bobImage.getLayoutParams().width > haterImage.getX())
                //  Bob's top colliding with Hater's bottom:
                || (bobImage.getY() < haterImage.getY() + haterImage.getLayoutParams().height
                && bobImage.getY() > haterImage.getY()
                && bobImage.getX() < haterImage.getX() + haterImage.getLayoutParams().width
                && bobImage.getX() + bobImage.getLayoutParams().width > haterImage.getX());
    }

    //  1 = the same speed as the level, 2 = twice as fast, 0.5 = half as fast, etc.
    public void setPath(GridImageThing[] thePath, float[] xHaterMoveSpeeds, float[] yHaterMoveSpeeds)
    {
        this.thePath = thePath;
        this.xHaterMoveSpeeds = xHaterMoveSpeeds;
        this.yHaterMoveSpeeds = yHaterMoveSpeeds;

        //  Might as well start the path in this method instead of LevelOneActivity:
        pathIndex = 0;
        //nextGridImageThing = thePath[pathIndex];
        //  NOPE! Needs to be the next one! Bob should start at pathIndex 0, but move TOWARDS pathIndex1!
        if (pathIndex != thePath.length-1)
        {
            nextGridImageThing = thePath[pathIndex+1];
        }
        //  If there is only one place on the path (stationary enemy), start (and stay) there.
        else
        {
            nextGridImageThing = thePath[pathIndex];
        }
        xHaterMoveSpeed = xHaterMoveSpeeds[pathIndex];
        yHaterMoveSpeed = yHaterMoveSpeeds[pathIndex];
    }

    //  This makes the enemy move at a constant speed on whatever path it should take, or remain stationary.
    public void movePath()
    {
        boolean isDoneMovingX = false;
        boolean isDoneMovingY = false;

        //  Hater needs to move to the right.
        if (nextGridImageThing.getImageX() - haterImage.getX() > 0)
        {
            //  1st one about moving the full amount
            if (haterImage.getX() + (xMoveSpeedScreen*xHaterMoveSpeed) <= nextGridImageThing.getImageX())
            {
                haterImage.setX(haterImage.getX()+(xMoveSpeedScreen*xHaterMoveSpeed));
            }
            //  2nd one about moving the little amount.
            else if (haterImage.getX() < nextGridImageThing.getImageX())
            {
                haterImage.setX(haterImage.getX() + (nextGridImageThing.getImageX()-haterImage.getX()));
            }
        }
        //  Hater needs to move to the left.
        else if (nextGridImageThing.getImageX() - haterImage.getX() < 0)
        {
            if (haterImage.getX() - (xMoveSpeedScreen*xHaterMoveSpeed) >= nextGridImageThing.getImageX())
            {
                haterImage.setX(haterImage.getX()-(xMoveSpeedScreen*xHaterMoveSpeed));
            }
            else if (haterImage.getX() > nextGridImageThing.getImageX())
            {
                haterImage.setX(haterImage.getX() - (haterImage.getX()-nextGridImageThing.getImageX()));
            }
        }
        //  Hater is either done moving horizontally or didn't need to in the first place.
        else {
            isDoneMovingX = true;
        }

        //  Hater needs to move down.
        if (nextGridImageThing.getImageY() - haterImage.getY() > 0)
        {
            if (haterImage.getY() + (xMoveSpeedScreen*yHaterMoveSpeed) <= nextGridImageThing.getImageY())
            {
                haterImage.setY(haterImage.getY()+(xMoveSpeedScreen*yHaterMoveSpeed));
            }
            else if (haterImage.getY() < nextGridImageThing.getImageY())
            {
                haterImage.setY(haterImage.getY() + (nextGridImageThing.getImageY()-haterImage.getY()));
            }
        }
        //  Hater needs to move up.
        else if (nextGridImageThing.getImageY() - haterImage.getY() < 0)
        {
            if (haterImage.getY() - (xMoveSpeedScreen*yHaterMoveSpeed) >= nextGridImageThing.getImageY())
            {
                haterImage.setY(haterImage.getY()-(xMoveSpeedScreen*yHaterMoveSpeed));
            }
            else if (haterImage.getY() > nextGridImageThing.getImageY())
            {
                haterImage.setY(haterImage.getY() - (haterImage.getY()-nextGridImageThing.getImageY()));
            }
        }
        //  Hater is either done moving vertically or didn't need to in the first place.
        else {
            isDoneMovingY = true;
        }

        //  Hater has arrived at his destination, so move on to the nextGridImageThing on the path.
        if (isDoneMovingX && isDoneMovingY)
        {
            //  If the Hater is on the last GridImageThing of its path, start over at the first one.
            if (pathIndex == thePath.length-1)
            {
                pathIndex = 0;
            }
            else {
                pathIndex++;
            }
            //  Need to check thePath separately since its index is 1 more than for the x and y speeds.
            if (pathIndex == thePath.length-1)
            {
                nextGridImageThing = thePath[0];
            }
            else
            {
                nextGridImageThing = thePath[pathIndex+1];
            }

            xHaterMoveSpeed = xHaterMoveSpeeds[pathIndex];
            yHaterMoveSpeed = yHaterMoveSpeeds[pathIndex];

        }

    }
}
