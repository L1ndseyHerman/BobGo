package com.lherman.bob_go;

import android.widget.ImageView;

public class SquareObstacle implements GridImageThing
{

    private ImageView square, bobImage;
    private int xMoveSpeedScreen, screenWidth, screenHeight;
    private Bob bob;
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
        if ((square.getX() > (bobImage.getX()+bobImage.getLayoutParams().width+xMoveSpeedScreen)) || (square.getY() >= (bobImage.getY()+bobImage.getLayoutParams().height)) || ((square.getY()+square.getLayoutParams().height) <= (bobImage.getY())))
        {
            if ((bob.getOnTopOfSquare()==true) && (bob.getJumpingNow()==false) && (square.getX()+square.getLayoutParams().width+xMoveSpeedScreen) <= bobImage.getX())
            {
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
            bobImage.setY(bobImage.getY()-bob.getBobJumpSpeed());

            if ((bobImage.getY() <= bob.getStartHeightBob()-bob.getJumpHeightBob()) || ((square.getY()+square.getLayoutParams().height+bob.getBobJumpSpeed() >= bobImage.getY()) && (square.getY()+bob.getBobJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) && (square.getX() <= bobImage.getX()+bobImage.getLayoutParams().width) && (square.getX()+square.getLayoutParams().width >= bobImage.getX())))
            {
                bob.setJumpingNow(false);
                bob.setFallingNow(true);
            }
        }


        if (bob.getFallingNow() == true)
        {
            bobImage.setY(bobImage.getY()+bob.getBobJumpSpeed());

            //  If he's falling off the bottom of the screen, or lands on a Square, stop falling:
            if ((bobImage.getY() >= bob.getLowestBobY()) || ((square.getY()-bob.getBobJumpSpeed() <= bobImage.getY()+bobImage.getLayoutParams().height) && (square.getY()+square.getLayoutParams().height-bob.getBobJumpSpeed() >= bobImage.getY()) && (square.getX() <= bobImage.getX()+bobImage.getLayoutParams().width) && (square.getX()+square.getLayoutParams().width >= bobImage.getX())))
            {
                bob.setFallingNow(false);
                if (bobImage.getY() < bob.getLowestBobY())
                {
                    bob.setOnTopOfSquare(true);
                }
            }
        }


    }
    //  Blue square to maneuver around


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
