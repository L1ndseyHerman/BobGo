package com.lherman.bob_go;

import android.widget.ImageView;

//  Blue square to maneuver around
public class SquareObstacle implements GridImageThing
{
    //  Images from "activity_level_one.xml", or whatever future levels use.
    private ImageView square, bobImage;
    //  These get declared in "LevelOneActivity.java" and passed in here
    private int xMoveSpeedScreen, screenWidth, screenHeight;
    //  The Bob object:
    private Bob bob;

    public SquareObstacle(ImageView square, int screenWidth, int screenHeight, ImageView bobImage, int xMoveSpeedScreen, Bob bob)
    {
        this.square = square;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.bobImage = bobImage;
        this.xMoveSpeedScreen = xMoveSpeedScreen;
        this.bob = bob;
    }

    @Override
    public boolean checkCollision()
    {

        if (bob.getJumpingNow() == true)
        {
            //  Bob jumps
            //bobImage.setY(bobImage.getY()-bob.getBobJumpSpeed());

            //  If Bob's current y-value <= where Bob started jumping from minus where he's allowed to jump to || the next timer call the square's bottom side will be >= Bob's top side && the next timer call the square's top side will be <= Bob's bottom side             and the square's left side is currently <= Bob's right side            and the square's right side is currently >= Bob's left side
            if ((bobImage.getY() <= bob.getStartHeightBob()-bob.getJumpHeightBob()) || ((square.getY()+square.getLayoutParams().height+bob.getBobJumpSpeed() >= bobImage.getY()) && (square.getY()+bob.getBobJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) && (square.getX() <= bobImage.getX()+bobImage.getLayoutParams().width) && (square.getX()+square.getLayoutParams().width >= bobImage.getX())))
            {
                bob.setJumpingNow(false);
                bob.setFallingNow(true);
            }
        }


        if (bob.getFallingNow() == true)
        {
            //  Bob falls
            //bobImage.setY(bobImage.getY()+bob.getBobJumpSpeed());

            //  Summary: If Bob is falling off the bottom of the screen, or lands on a Square, stop falling.
            //  If Bob's current y-value >= the lowest y-value before he starts to go off the screen || the square's top side - Bob's y-value the next timer call <= Bob's bottom side && the square's bottom side - Bob's y-value the next timer call >= Bob's top side && the square's left side <= Bob's right side     and the square's right side >= Bob's left side
            if ((bobImage.getY() >= bob.getLowestBobY()) || ((square.getY()-bob.getBobJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) && (square.getY()+square.getLayoutParams().height-bob.getBobJumpSpeed() >= bobImage.getY()) && (square.getX() <= bobImage.getX()+bobImage.getLayoutParams().width) && (square.getX()+square.getLayoutParams().width >= bobImage.getX())))
            {
                bob.setFallingNow(false);
                //  If Bob didn't land on the bottom of the screen, then he must have landed on a square.
                if (bobImage.getY() < bob.getLowestBobY())
                {
                    bob.setOnTopOfSquare(true);
                }
            }
        }

        //  Right collision code if Bob is jumping:
        if (bob.getJumpingNow() == true)
        {


            //  If the square's left side is less than Bob's right side                               or the square's top side is >= Bob's bottom side                          or the square's bottom side <= Bob's top side
            //if ((square.getX() > (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height-bob.getBobJumpSpeed())) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY()-bob.getBobJumpSpeed())))
            //  If the square's left side is >  Bob's right side                                        DON'T FORGET THE RIGHT SIDE!!                                                        or the square's top side is >= Bob's bottom side                                                    or the square's bottom side <= Bob's top side
            if ((square.getX() > (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || ((square.getX()+square.getLayoutParams().width) < (bobImage.getX()+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height-bob.getBobJumpSpeed())) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY()-bob.getBobJumpSpeed())))
            {
                //  If Bob is coasting to the right on top of a square             and the next time the square moves, its right side will be <= Bob's left side,
                if ((bob.getOnTopOfSquare()==true) && (bob.getJumpingNow()==false) && (square.getX()+square.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
                {
                    //  Make Bob fall down instead of walking on air.
                    bob.setFallingNow(true);
                    bob.setOnTopOfSquare(false);
                }
            }
            else
            {
                //  This is where Bob collides w something on his right, right?
                System.out.println("Right collision jumping");
                return false;
            }
        }

        //  Right collision code if Bob is falling:
        else if (bob.getFallingNow() == true)
        {
            //  If the square's left side is less than Bob's right side                               or the square's top side is >= Bob's bottom side                          or the square's bottom side <= Bob's top side
            //  If the square's left side is >  Bob's right side                                        DON'T FORGET THE RIGHT SIDE!!                                                        or the square's top side is >= Bob's bottom side                                                    or the square's bottom side <= Bob's top side
            if ((square.getX() > (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || ((square.getX()+square.getLayoutParams().width) < (bobImage.getX()+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height+bob.getBobJumpSpeed())) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY()+bob.getBobJumpSpeed())))
            {
                //  If Bob is coasting to the right on top of a square             and the next time the square moves, its right side will be <= Bob's left side,
                if ((bob.getOnTopOfSquare()==true) && (bob.getJumpingNow()==false) && (square.getX()+square.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
                {
                    //  Make Bob fall down instead of walking on air.
                    bob.setFallingNow(true);
                    bob.setOnTopOfSquare(false);
                }
            }
            else
            {
                //  This is where Bob collides w something on his right, right?
                System.out.println("Right collision falling");
                return false;
            }
        }

        //  Not jumping or falling
        else
        {
            //  If the square's left side is less than Bob's right side                               or the square's top side is >= Bob's bottom side                          or the square's bottom side <= Bob's top side
            //  If the square's left side is >  Bob's right side                                        DON'T FORGET THE RIGHT SIDE!!                                                        or the square's top side is >= Bob's bottom side                                                    or the square's bottom side <= Bob's top side
            if ((square.getX() > (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || ((square.getX()+square.getLayoutParams().width) < (bobImage.getX()+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height)) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY())))
            {
                //  If Bob is coasting to the right on top of a square             and the next time the square moves, its right side will be <= Bob's left side,
                if ((bob.getOnTopOfSquare()==true) && (bob.getJumpingNow()==false) && (square.getX()+square.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
                {
                    //  Make Bob fall down instead of walking on air.
                    bob.setFallingNow(true);
                    bob.setOnTopOfSquare(false);
                }
            }
            else
            {
                //  This is where Bob collides w something on his right, right?
                System.out.println("Right collision no height");
                return false;
            }
        }




        return true;
    }

    @Override
    public void move()
    {
        square.setX(square.getX() - xMoveSpeedScreen);
    }


    @Override
    public float getImageX() {
        return square.getX();
    }

    @Override
    public void setImageX(float xImage) {
        square.setX(xImage);
    }

    @Override
    public float getImageY() {
        return square.getY();
    }

    @Override
    public void setImageY(float yImage) {
        square.setY(yImage);
    }

    @Override
    public int getImageWidth() {
        return square.getLayoutParams().width;
    }

    @Override
    public void setImageWidth(int widthImage) {
        square.getLayoutParams().width = widthImage;
    }

    @Override
    public int getImageHeight() {
        return square.getLayoutParams().height;
    }

    @Override
    public void setImageHeight(int heightImage) {
        square.getLayoutParams().height = heightImage;
    }

}
