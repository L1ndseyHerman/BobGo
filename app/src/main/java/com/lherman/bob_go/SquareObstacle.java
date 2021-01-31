package com.lherman.bob_go;

import android.widget.ImageView;
import android.widget.TextView;

//  Blue square to maneuver around
public class SquareObstacle implements GridImageThing
{
    //  Images from "activity_level_one.xml", or whatever future levels use.
    private ImageView squareImage, bobImage;
    //  These get declared in "LevelOneActivity.java" and passed in here
    private int xMoveSpeedScreen, screenWidth, screenHeight;
    //  The Bob object:
    private Bob bob;

    public SquareObstacle(ImageView squareImage, int screenWidth, int screenHeight)
    {
        this.squareImage = squareImage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public boolean checkCollision()
    {
        if (bob.IsJumping())
        {
            checkTopCollision();
        }
        if (bob.IsFalling())
        {
            checkBottomCollision();
        }

        boolean isNotColliding;

        if (bob.IsJumping())
        {
            isNotColliding = checkRightCollision(-bob.getJumpSpeed());
        }
        else if (bob.IsFalling())
        {
            isNotColliding = checkRightCollision(bob.getJumpSpeed());
        }
        else
        {
            isNotColliding = checkRightCollision(0);
        }

        return isNotColliding;
    }

    public void checkTopCollision()
    {
        //  If Bob's current y-value <= where Bob started jumping from minus where he's allowed to jump to ||
        if ((bobImage.getY() <= bob.getStartHeight()-bob.getJumpHeight()) ||
                //  the next timer call the square's bottom side will be >= Bob's top side  &&
                ((squareImage.getY()+squareImage.getLayoutParams().height+bob.getJumpSpeed() >= bobImage.getY()) &&
                        //  the next timer call the square's top side will be <= Bob's bottom side &&
                        (squareImage.getY()+bob.getJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) &&
                        //  the square's left side is currently < Bob's right side &&
                        (squareImage.getX() < bobImage.getX()+bobImage.getLayoutParams().width) &&
                        //  the square's right side is currently > Bob's left side
                        (squareImage.getX()+squareImage.getLayoutParams().width > bobImage.getX())))
        {
            checkJumpingLittle();
        }
    }

    public void checkJumpingLittle()
    {
        //  Before deciding Bob definitely needs to stop jumping, decide if he can still jump an amount of pixels
        //  less than his jump speed but greater than 0.
        if (squareImage.getY()+squareImage.getLayoutParams().height < bobImage.getY()-1)
        {
            bob.setJumpingLittle(true);
            //  NEEDS THE -1 !
            bob.setYLittleAmount(bobImage.getY()-(squareImage.getY()+squareImage.getLayoutParams().height)-1);
        }
        else
        {
            bob.setJumping(false);
            bob.setFalling(true);
        }
    }

    public void checkBottomCollision()
    {
        //  Summary: If Bob is falling off the bottom of the screen, or lands on a Square, stop falling.
        //  If Bob's current y-value >= the lowest y-value before he starts to go off the screen ||
        if ((bobImage.getY() >= bob.getLowestY()) ||
                //  the square's top side - Bob's y-value the next timer call <= Bob's bottom side &&
                ((squareImage.getY()-bob.getJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) &&
                        //  the square's bottom side - Bob's y-value the next timer call >= Bob's top side &&
                        (squareImage.getY()+squareImage.getLayoutParams().height-bob.getJumpSpeed() >= bobImage.getY()) &&
                        //  the square's left side < Bob's right side &&
                        (squareImage.getX() < bobImage.getX()+bobImage.getLayoutParams().width) &&
                        //  the square's right side > Bob's left side
                        (squareImage.getX()+squareImage.getLayoutParams().width > bobImage.getX())))
        {
            checkFallingLittle();
        }
    }

    public void checkFallingLittle()
    {
        if (squareImage.getY() > bobImage.getY()+bobImage.getLayoutParams().height)
        {
            bob.setFallingLittle(true);
            bob.setYLittleAmount(squareImage.getY()-(bobImage.getY()+bobImage.getLayoutParams().height));
        }
        else
        {
            bob.setFalling(false);
            checkLandedOnSquare();
        }
    }

    public void checkLandedOnSquare()
    {
        //  If Bob didn't land on the bottom of the screen, then he must have landed on a square.
        if (bobImage.getY() < bob.getLowestY())
        {
            bob.setOnTopOfSquare(true);
        }
    }

    public boolean checkRightCollision(int yChange)
    {
        //  This one is flipped. The "if" means Bob can move, "else if" means move little, "else" not at all.
        //  If the square's left side is >=  Bob's right side the next timer call ||
        if ((squareImage.getX() >= (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) ||
                //  the square's right side <= Bob's left side the next timer call ||
                ((squareImage.getX()+squareImage.getLayoutParams().width) <= (bobImage.getX()+xMoveSpeedScreen)) ||
                //  the square's top side is >= Bob's bottom side + maybe some pos or neg yChange ||
                (squareImage.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height+yChange)) ||
                //  the square's bottom side <= Bob's top side + maybe some pos or neg yChange
                ((squareImage.getY()+squareImage.getLayoutParams().height) <= (bobImage.getY()+yChange)))
        {
            checkFallOffSquare();
        }
        else if (squareImage.getX() > bobImage.getX()+bobImage.getLayoutParams().width)
        {
            bob.setMovingRightLittle(true);
            bob.setXLittleAmount(squareImage.getX()-(bobImage.getX()+bobImage.getLayoutParams().width));
            return false;
        }
        else
        {
            //  This is where Bob collides w something on his right
            return false;
        }

        return true;
    }

    public void checkFallOffSquare()
    {
        //  If Bob is coasting to the right on top of a square
        if ((bob.IsOnTopOfSquare()) && (!bob.IsJumping()) &&
                //  and the next time the square moves, its right side will be <= Bob's left side,
                (squareImage.getX()+squareImage.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
        {
            //  Make Bob fall down instead of walking on air.
            bob.setFalling(true);
            bob.setOnTopOfSquare(false);
        }
    }

    @Override
    public void move()
    {
        squareImage.setX(squareImage.getX() - xMoveSpeedScreen);
    }

    @Override
    public void move(float amount)
    {
        squareImage.setX(squareImage.getX() - amount);
    }


    @Override
    public float getImageX() {
        return squareImage.getX();
    }

    @Override
    public void setImageX(float xImage) {
        squareImage.setX(xImage);
    }

    @Override
    public void setImageY(float yImage) {
        squareImage.setY(yImage);
    }

    @Override
    public void setImageWidth(int widthImage) {
        squareImage.getLayoutParams().width = widthImage;
    }

    @Override
    public void setImageHeight(int heightImage) {
        squareImage.getLayoutParams().height = heightImage;
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
