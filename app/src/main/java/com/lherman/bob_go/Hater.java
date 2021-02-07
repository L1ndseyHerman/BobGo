package com.lherman.bob_go;

import android.widget.ImageView;

public class Hater
{
    //  The red frowny-face enemies. If Bob touches one, he will become too upset to win the level,
    //  and get a Game Over.
    private ImageView haterImage, bobImage;
    private int xMoveSpeedScreen;
    private Bob bob;

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
}
