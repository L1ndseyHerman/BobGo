package com.lherman.bob_go;

import android.widget.ImageView;

//  A green circle. If Bob touches it, he wins the level.
public class WinCircle
{
    private final ImageView circleImage;
    private ImageView bobImage;
    private int xMoveSpeedScreen;

    public WinCircle(ImageView circleImage)
    {
        this.circleImage = circleImage;
    }

    public boolean isColliding()
    {
        //  Bob's right colliding with WinCircle's left:
        return (bobImage.getX() + bobImage.getLayoutParams().width > circleImage.getX()
                && bobImage.getX() < circleImage.getX()
                && bobImage.getY() < circleImage.getY() + circleImage.getLayoutParams().height
                && bobImage.getY() + bobImage.getLayoutParams().height > circleImage.getY())
                //  Bob's left colliding with WinCircle's right:
                || (bobImage.getX() < circleImage.getX() + circleImage.getLayoutParams().width
                && bobImage.getX() > circleImage.getX()
                && bobImage.getY() < circleImage.getY() + circleImage.getLayoutParams().height
                && bobImage.getY() + bobImage.getLayoutParams().height > circleImage.getY())
                //  Bob's bottom colliding with WinCircle's top:
                || (bobImage.getY() + bobImage.getLayoutParams().height > circleImage.getY()
                && bobImage.getY() < circleImage.getY()
                && bobImage.getX() < circleImage.getX() + circleImage.getWidth()
                && bobImage.getX() + bobImage.getLayoutParams().width > circleImage.getX())
                //  Bob's top colliding with WinCircle's bottom:
                || (bobImage.getY() < circleImage.getY() + circleImage.getLayoutParams().height
                && bobImage.getY() > circleImage.getY()
                && bobImage.getX() < circleImage.getX() + circleImage.getLayoutParams().width
                && bobImage.getX() + bobImage.getLayoutParams().width > circleImage.getX());
    }

    public void move() {
        circleImage.setX(circleImage.getX() - xMoveSpeedScreen);
    }

    public void move(float amount) {
        circleImage.setX(circleImage.getX() - amount);
    }

    public float getImageX() {
        return circleImage.getX();
    }

    public void setImageX(float xImage)
    {
        circleImage.setX(xImage);
    }

    public void setImageY(float yImage) {
        circleImage.setY(yImage);
    }

    public float getImageY() {
        return circleImage.getY();
    }

    public void setImageWidth(int widthImage) {
        circleImage.getLayoutParams().width = widthImage;
    }

    public void setImageHeight(int heightImage) {
        circleImage.getLayoutParams().height = heightImage;
    }

    public void setBobImage(ImageView bobImage) {
        this.bobImage = bobImage;
    }

    public void setXMoveSpeedScreen(int xMoveSpeedScreen) {
        this.xMoveSpeedScreen = xMoveSpeedScreen;
    }

}
