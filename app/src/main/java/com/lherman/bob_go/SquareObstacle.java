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
    //  daGrid from LevelOneActivity
    //private ImageView[][] daGrid;

    //public SquareObstacle(ImageView square, int screenWidth, int screenHeight, ImageView bobImage, int xMoveSpeedScreen, Bob bob, ImageView[][] daGrid)
    public SquareObstacle(ImageView square, int screenWidth, int screenHeight, ImageView bobImage, int xMoveSpeedScreen, Bob bob)
    {
        this.square = square;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.bobImage = bobImage;
       //xMoveSpeedScreen = screenWidth/168;
        this.xMoveSpeedScreen = xMoveSpeedScreen;
        this.bob = bob;
        //  Why are both "bob" and "bobImage" NULL here?!
        //this.daGrid = daGrid;
        //System.out.println("Created Square");
        //System.out.println(square.getX() + " " + square.getY() + " " + square.getLayoutParams().width + " " + square.getLayoutParams().height);
    }

    @Override
    public void checkCollision()
    {
        //System.out.println(square.getX() + " " + square.getY() + " " + square.getLayoutParams().width + " " + square.getLayoutParams().height);
        //System.out.println(square.getImageX() + " " + square.getY() + " " + square.getLayoutParams().width + " " + square.getLayoutParams().height);
        //  If the square's left side is less than Bob's right side                               or the square's top side is >= Bob's bottom side                          or the square's bottom side <= Bob's top side
        if ((square.getX() > (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height)) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY())))
        {
            //  If Bob is coasting to the right on top of a square             and the next time the square moves, its right side will be <= Bob's left side,
            if ((bob.getOnTopOfSquare()==true) && (bob.getJumpingNow()==false) && (square.getX()+square.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
            {
                //  Make Bob fall down instead of walking on air.
                bob.setFallingNow(true);
                bob.setOnTopOfSquare(false);
                System.out.println("Not walking on sunshine");
            }
            //  Nope! The array is already moving the squares, want the empty grid spaces to move too.
            //  But wait! This decides whether to keep moving the square or stop bec Bob collided w it! Need to do entire grid here!
            square.setX(square.getX() - xMoveSpeedScreen);
            //moveDaGrid();
        }
        else
        {
            //  Collision test code text thing
        }

        if (bob.getJumpingNow() == true)
        {
            //  Bob jumps
            bobImage.setY(bobImage.getY()-bob.getBobJumpSpeed());

            //  If Bob's current y-value <= where Bob started jumping from minus where he's allowed to jump to || the next timer call the square's bottom side will be >= Bob's top side && the next timer call the square's top side will be <= Bob's bottom side             and the square's left side is currently <= Bob's right side            and the square's right side is currently >= Bob's left side
            if ((bobImage.getY() <= bob.getStartHeightBob()-bob.getJumpHeightBob()) || ((square.getY()+square.getLayoutParams().height+bob.getBobJumpSpeed() >= bobImage.getY()) && (square.getY()+bob.getBobJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) && (square.getX() <= bobImage.getX()+bobImage.getLayoutParams().width) && (square.getX()+square.getLayoutParams().width >= bobImage.getX())))
            {
                System.out.println("Falling down");
                bob.setJumpingNow(false);
                bob.setFallingNow(true);
            }
        }


        if (bob.getFallingNow() == true)
        {
            //  Bob falls
            bobImage.setY(bobImage.getY()+bob.getBobJumpSpeed());

            //  Summary: If Bob is falling off the bottom of the screen, or lands on a Square, stop falling.
            //  If Bob's current y-value >= the lowest y-value before he starts to go off the screen || the square's top side - Bob's y-value the next timer call <= Bob's bottom side && the square's bottom side - Bob's y-value the next timer call >= Bob's top side && the square's left side <= Bob's right side     and the square's right side >= Bob's left side
            if ((bobImage.getY() >= bob.getLowestBobY()) || ((square.getY()-bob.getBobJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) && (square.getY()+square.getLayoutParams().height-bob.getBobJumpSpeed() >= bobImage.getY()) && (square.getX() <= bobImage.getX()+bobImage.getLayoutParams().width) && (square.getX()+square.getLayoutParams().width >= bobImage.getX())))
            {
                bob.setFallingNow(false);
                //  If Bob didn't land on the bottom of the screen, then he must have landed on a square.
                if (bobImage.getY() < bob.getLowestBobY())
                {
                    bob.setOnTopOfSquare(true);
                    System.out.println("Landed on square");
                }
                System.out.println("Landed on square or screen bottom");
            }
        }


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


    //  Unlike the grid placement double for-loop, this one moves the grid.
    /*private void moveDaGrid()
    {
        for (int index=0; index<daGrid.length; index++)
        {
            for (int index2=0; index2<daGrid[index].length; index2++)
            {
                daGrid[index][index2].setX(daGrid[index][index2].getX() - xMoveSpeedScreen);
            }
        }
    }*/




}
