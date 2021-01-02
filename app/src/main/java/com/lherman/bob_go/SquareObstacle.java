package com.lherman.bob_go;

import android.widget.ImageView;

//  Blue square to maneuver around
public class SquareObstacle implements GridImageThing
{
    //  Images from "activity_level_one.xml", or whatever future levels use.
    private ImageView square, bobImage;
    //  These get declared in "LevelOneActivity.java" and passed in here
    private int xMoveSpeedScreen, screenWidth, screenHeight, tempN;
    //  The Bob object:
    private Bob bob;

    public SquareObstacle(ImageView square, int screenWidth, int screenHeight, ImageView bobImage, int xMoveSpeedScreen, Bob bob, int tempN)
    {
        this.square = square;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.bobImage = bobImage;
        this.xMoveSpeedScreen = xMoveSpeedScreen;
        this.bob = bob;
        this.tempN = tempN;
    }

    @Override
    public boolean checkCollision()
    {
        System.out.println(tempN);




        if (bob.getJumpingNow() == true)
        {

            //  If Bob's current y-value <= where Bob started jumping from minus where he's allowed to jump to || the next timer call the square's bottom side will be >= Bob's top side && the next timer call the square's top side will be <= Bob's bottom side             and the square's left side is currently <= Bob's right side            and the square's right side is currently >= Bob's left side
            if ((bobImage.getY() <= bob.getStartHeightBob()-bob.getJumpHeightBob()) || ((square.getY()+square.getLayoutParams().height+bob.getBobJumpSpeed() >= bobImage.getY()) && (square.getY()+bob.getBobJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) && (square.getX() < bobImage.getX()+bobImage.getLayoutParams().width) && (square.getX()+square.getLayoutParams().width > bobImage.getX())))
            {
                //  Before deciding Bob definitely needs to stop jumping, decide if he can still jump an amount of pixels less than his jump speed,
                //  but greater than 0.
                if (square.getY()+square.getLayoutParams().height < bobImage.getY())
                {
                    System.out.println("Jumping a little more");
                    bob.setJumpingLittleNow(true);
                    //  Gets 3.0 wo -1
                    bob.setLittleAmount(bobImage.getY()-(square.getY()+square.getLayoutParams().height)-1);
                    System.out.println("Jumping Little amount = " + bob.getLittleAmount());
                }
                else
                {
                    bob.setJumpingNow(false);
                    bob.setFallingNow(true);
                    System.out.println("Jumping to falling.");
                }
            }
        }



        if (bob.getFallingNow() == true)
        {
            System.out.println("Fell after jumped");
            //  Summary: If Bob is falling off the bottom of the screen, or lands on a Square, stop falling.
            //  If Bob's current y-value >= the lowest y-value before he starts to go off the screen || the square's top side - Bob's y-value the next timer call <= Bob's bottom side && the square's bottom side - Bob's y-value the next timer call >= Bob's top side && the square's left side <= Bob's right side     and the square's right side >= Bob's left side
            if ((bobImage.getY() >= bob.getLowestBobY()) || ((square.getY()-bob.getBobJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) && (square.getY()+square.getLayoutParams().height-bob.getBobJumpSpeed() >= bobImage.getY()) && (square.getX() < bobImage.getX()+bobImage.getLayoutParams().width) && (square.getX()+square.getLayoutParams().width > bobImage.getX())))
            {

                /*if (square.getY() > bobImage.getY()+bobImage.getLayoutParams().height)
                {
                    System.out.println("Falling a little more");

                    bob.setFallingLittleNow(true);
                    //  6.0
                    //bob.setLittleAmount(square.getY()-(bobImage.getY()+bobImage.getLayoutParams().height));
                    bob.setLittleAmount(square.getY()-(bobImage.getY()+bobImage.getLayoutParams().height)-1);

                    System.out.println("Falling Little amount = " + bob.getLittleAmount());
                }
                else
                    {*/
                    System.out.println("Not falling anymore.");
                    bob.setFallingNow(false);
                    System.out.println("Square top side = " + square.getY() + " Bob bottom side = " + bobImage.getY() + bobImage.getLayoutParams().height + "Screen height = " + screenHeight);
                    //  If Bob didn't land on the bottom of the screen, then he must have landed on a square.
                    if (bobImage.getY() < bob.getLowestBobY())
                    {
                        bob.setOnTopOfSquare(true);
                        System.out.println("ON TOP OF SQUARE! :D");
                    }
                //}
            }
        }


        //  Right collision code if Bob is jumping:
        if (bob.getJumpingNow() == true)
        {
            //  If the square's left side is >  Bob's right side                                        DON'T FORGET THE RIGHT SIDE!!                                                        or the square's top side is >= Bob's bottom side                                                    or the square's bottom side <= Bob's top side
            if ((square.getX() >= (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || ((square.getX()+square.getLayoutParams().width) <= (bobImage.getX()+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height-bob.getBobJumpSpeed())) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY()-bob.getBobJumpSpeed())))
            {
                //  If Bob is coasting to the right on top of a square             and the next time the square moves, its right side will be <= Bob's left side,
                if ((bob.getOnTopOfSquare()==true) && (bob.getJumpingNow()==false) && (square.getX()+square.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
                {
                    //  Make Bob fall down instead of walking on air.
                    bob.setFallingNow(true);
                    bob.setOnTopOfSquare(false);
                }
            }
            //  Code RightLittle here:
            else if (square.getX() > bobImage.getX()+bobImage.getLayoutParams().width)
            {
                System.out.println("Right Little");

                bob.setRightLittleNow(true);
                //  Should be 6 or 5:
                //  Gets 0 for the square where it's 1 away, so that works, so why does it move 6pixels?
                bob.setXLittleAmount(square.getX()-(bobImage.getX()+bobImage.getLayoutParams().width)-1);
                System.out.println("Right Little amount = " + bob.getXLittleAmount());
                return false;
            }
            else
            {
                //  This is where Bob collides w something on his right
                System.out.println("Right collision jumping");
                return false;
            }
        }

        //  Right collision code if Bob is falling:
        else if (bob.getFallingNow() == true)
        {
            //  If the square's left side is >  Bob's right side                                        DON'T FORGET THE RIGHT SIDE!!                                                        or the square's top side is >= Bob's bottom side                                                    or the square's bottom side <= Bob's top side
            if ((square.getX() >= (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || ((square.getX()+square.getLayoutParams().width) <= (bobImage.getX()+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height+bob.getBobJumpSpeed())) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY()+bob.getBobJumpSpeed())))
            {
                //  If Bob is coasting to the right on top of a square             and the next time the square moves, its right side will be <= Bob's left side,
                if ((bob.getOnTopOfSquare()==true) && (bob.getJumpingNow()==false) && (square.getX()+square.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
                {
                    //  Make Bob fall down instead of walking on air.
                    bob.setFallingNow(true);
                    bob.setOnTopOfSquare(false);
                }
            }
            //  Code RightLittle here:
            else if (square.getX() > bobImage.getX()+bobImage.getLayoutParams().width)
            {
                System.out.println("Right Little");

                bob.setRightLittleNow(true);
                //  Should be 6 or 5:
                //  Gets 0 for the square where it's 1 away, so that works, so why does it move 6pixels?
                bob.setXLittleAmount(square.getX()-(bobImage.getX()+bobImage.getLayoutParams().width)-1);
                System.out.println("Right Little amount = " + bob.getXLittleAmount());
                return false;
            }
            else
            {
                //  This is where Bob collides w something on his right
                System.out.println("Right collision falling");
                return false;
            }
        }

        //  Not jumping or falling
        else
        {
            //  If the square's left side is >  Bob's right side                                        DON'T FORGET THE RIGHT SIDE!!                                                        or the square's top side is >= Bob's bottom side                                                    or the square's bottom side <= Bob's top side
            if ((square.getX() >= (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || ((square.getX()+square.getLayoutParams().width) <= (bobImage.getX()+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height)) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY())))
            {
                //  If Bob is coasting to the right on top of a square             and the next time the square moves, its right side will be <= Bob's left side,
                if ((bob.getOnTopOfSquare()==true) && (bob.getJumpingNow()==false) && (square.getX()+square.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
                {
                    //  Make Bob fall down instead of walking on air.
                    bob.setFallingNow(true);
                    bob.setOnTopOfSquare(false);
                }
            }
            //  Code RightLittle here:
            else if (square.getX() > bobImage.getX()+bobImage.getLayoutParams().width)
            {
                System.out.println("Right Little");

                bob.setRightLittleNow(true);
                //  Should be 6 or 5:
                //  Gets 0 for the square where it's 1 away, so that works, so why does it move 6pixels?
                bob.setXLittleAmount(square.getX()-(bobImage.getX()+bobImage.getLayoutParams().width)-1);
                System.out.println("Right Little amount = " + bob.getXLittleAmount());
                return false;
            }

            else
            {
                //  This is where Bob collides w something on his right
                System.out.println("Right collision no height");
                //System.out.println("SquareY = " + square.getY() + " SquareHeight = " + square.getLayoutParams().height + " Bob height = " + bobImage.getLayoutParams().height + "Bob top side = " + bobImage.getY());
                System.out.println("SquareX = " + square.getX() + " Bob width = " + bobImage.getLayoutParams().width + "Bob right side = " + bobImage.getX());
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
    public void move(float amount)
    {
        square.setX(square.getX() - amount);
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
