package com.lherman.bob_go;

import android.widget.ImageView;

public class Coin
{
    private ImageView coinImage, bobImage;
    private int xMoveSpeedScreen;
    private Bob bob;

    public Coin(ImageView coinImage)
    {
        this.coinImage = coinImage;
    }

    public boolean isColliding()
    {
        //  Set the coin's visibility to false and inc the score by 1 in the level based on if this returns t/f.

        //  Bob's right colliding with Coin's left:
        if ((bobImage.getX()+bobImage.getLayoutParams().width > coinImage.getX()
                && bobImage.getX() < coinImage.getX()
                && bobImage.getY() < coinImage.getY()+coinImage.getLayoutParams().height
                && bobImage.getY()+bobImage.getLayoutParams().height > coinImage.getY())
                //  Bob's left colliding with Coin's right:
                || (bobImage.getX() < coinImage.getX()+coinImage.getLayoutParams().width
                && bobImage.getX() > coinImage.getX()
                && bobImage.getY() < coinImage.getY()+coinImage.getLayoutParams().height
                && bobImage.getY()+bobImage.getLayoutParams().height > coinImage.getY())
                //  Bob's bottom colliding with Coin's top:
                || (bobImage.getY()+bobImage.getLayoutParams().height > coinImage.getY()
                && bobImage.getY() < coinImage.getY()
                && bobImage.getX() < coinImage.getX()+coinImage.getWidth()
                && bobImage.getX()+bobImage.getLayoutParams().width > coinImage.getX())
                //  Bob's top colliding with Coin's bottom:
                || (bobImage.getY() < coinImage.getY()+coinImage.getLayoutParams().height
                && bobImage.getY() > coinImage.getY()
                && bobImage.getX() < coinImage.getX()+coinImage.getLayoutParams().width
                && bobImage.getX()+bobImage.getLayoutParams().width > coinImage.getX()))
        {
            //  Do score + invisibility in the Level, maybe?
            return true;
        }

        return false;
    }

    public void move() {
        coinImage.setX(coinImage.getX() - xMoveSpeedScreen);
    }
    public void move(float amount) {
        coinImage.setX(coinImage.getX() - amount);
    }
    public float getImageX() {
        return coinImage.getX();
    }
    public void setImageX(float xImage)
    {
        coinImage.setX(xImage);
    }
    public void setImageY(float yImage) {
        coinImage.setY(yImage);
    }
    public float getImageY() {
        return coinImage.getY();
    }
    public void setImageWidth(int widthImage) {
        coinImage.getLayoutParams().width = widthImage;
    }
    public void setImageHeight(int heightImage) {
        coinImage.getLayoutParams().height = heightImage;
    }
    public void setBob(Bob bob) {
        this.bob = bob;
    }
    public void setBobImage(ImageView bobImage) {
        this.bobImage = bobImage;
    }
    public void setXMoveSpeedScreen(int xMoveSpeedScreen) {
        this.xMoveSpeedScreen = xMoveSpeedScreen;
    }
    public void setInvisible()
    {
        coinImage.setVisibility(ImageView.INVISIBLE);
    }
}
