package com.lherman.bob_go;

import android.widget.ImageView;


/*
    This power-up makes Bob so happy that he is temporarily immune to the effects of Haters
    (can go right through Haters without getting a Game Over.)
 */
public class BrightenUpPowerUp
{
    private final ImageView powerUpImage;
    private ImageView bobImage;
    private int xMoveSpeedScreen;

    public BrightenUpPowerUp(ImageView powerUpImage)
    {
        this.powerUpImage = powerUpImage;
    }

    public boolean isColliding()
    {
        //  Bob's right colliding with PowerUp's left:
        return (bobImage.getX() + bobImage.getLayoutParams().width > powerUpImage.getX()
                && bobImage.getX() < powerUpImage.getX()
                && bobImage.getY() < powerUpImage.getY() + powerUpImage.getLayoutParams().height
                && bobImage.getY() + bobImage.getLayoutParams().height > powerUpImage.getY())
                //  Bob's left colliding with PowerUp's right:
                || (bobImage.getX() < powerUpImage.getX() + powerUpImage.getLayoutParams().width
                && bobImage.getX() > powerUpImage.getX()
                && bobImage.getY() < powerUpImage.getY() + powerUpImage.getLayoutParams().height
                && bobImage.getY() + bobImage.getLayoutParams().height > powerUpImage.getY())
                //  Bob's bottom colliding with PowerUp's top:
                || (bobImage.getY() + bobImage.getLayoutParams().height > powerUpImage.getY()
                && bobImage.getY() < powerUpImage.getY()
                && bobImage.getX() < powerUpImage.getX() + powerUpImage.getWidth()
                && bobImage.getX() + bobImage.getLayoutParams().width > powerUpImage.getX())
                //  Bob's top colliding with PowerUp's bottom:
                || (bobImage.getY() < powerUpImage.getY() + powerUpImage.getLayoutParams().height
                && bobImage.getY() > powerUpImage.getY()
                && bobImage.getX() < powerUpImage.getX() + powerUpImage.getLayoutParams().width
                && bobImage.getX() + bobImage.getLayoutParams().width > powerUpImage.getX());
    }

    public void move() {
        powerUpImage.setX(powerUpImage.getX() - xMoveSpeedScreen);
    }
    public void move(float amount) {
        powerUpImage.setX(powerUpImage.getX() - amount);
    }
    public float getImageX() {
        return powerUpImage.getX();
    }
    public void setImageX(float xImage)
    {
        powerUpImage.setX(xImage);
    }
    public void setImageY(float yImage) {
        powerUpImage.setY(yImage);
    }
    public float getImageY() {
        return powerUpImage.getY();
    }
    public void setImageWidth(int widthImage) {
        powerUpImage.getLayoutParams().width = widthImage;
    }
    public void setImageHeight(int heightImage) {
        powerUpImage.getLayoutParams().height = heightImage;
    }
    public void setBobImage(ImageView bobImage) {
        this.bobImage = bobImage;
    }
    public void setXMoveSpeedScreen(int xMoveSpeedScreen) {
        this.xMoveSpeedScreen = xMoveSpeedScreen;
    }
    public void setInvisible()
    {
        powerUpImage.setVisibility(ImageView.INVISIBLE);
    }
    public boolean IsNotInvisible()
    {
        return powerUpImage.getVisibility() == ImageView.VISIBLE;
    }

}
