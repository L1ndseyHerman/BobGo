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
    private ImageView[][] daGrid;

    public SquareObstacle(ImageView square, int screenWidth, int screenHeight, ImageView bobImage, int xMoveSpeedScreen, Bob bob, ImageView[][] daGrid)
    {
        this.square = square;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.bobImage = bobImage;
       //xMoveSpeedScreen = screenWidth/168;
        this.xMoveSpeedScreen = xMoveSpeedScreen;
        this.bob = bob;
        this.daGrid = daGrid;
    }

    @Override
    public void checkCollision()
    {
        //  If the square's left side is less than Bob's right side                               or the square's top side is >= Bob's bottom side                          or the square's bottom side <= Bob's top side
        if ((square.getX() > (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height)) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY())))
        {
            //  If Bob is coasting to the right on top of a square             and the next time the square moves, its right side will be <= Bob's left side,
            if ((bob.getOnTopOfSquare()==true) && (bob.getJumpingNow()==false) && (square.getX()+square.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
            {
                //  Make Bob fall down instead of walking on air.
                bob.setFallingNow(true);
                bob.setOnTopOfSquare(false);
            }
            //  Nope! The array is already moving the squares, want the empty grid spaces to move too.
            //  But wait! This decides whether to keep moving the square or stop bec Bob collided w it! Need to do entire grid here!
            //square.setX(square.getX() - xMoveSpeedScreen);
            moveDaGrid();
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
                }
            }
        }


    }


    //  Unlike the grid placement double for-loop, this one moves the grid.
    private void moveDaGrid()
    {
        for (int index=0; index<daGrid.length; index++)
        {
            for (int index2=0; index2<daGrid[index].length; index2++)
            {
                daGrid[index][index2].setX(daGrid[index][index2].getX() - xMoveSpeedScreen);
            }
        }
    }




}
