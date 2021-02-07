package com.lherman.bob_go;

import android.widget.ImageView;

public class Hater
{
    //  The red frowny-face enemies. If Bob touches one, he will become too upset to win the level,
    //  and get a Game Over.
    private ImageView haterImage;
    private int xMoveSpeedScreen;

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
    public void move()
    {
        haterImage.setX(haterImage.getX() - xMoveSpeedScreen);
    }
    public void move(float amount)
    {
        haterImage.setX(haterImage.getX() - amount);
    }
}
