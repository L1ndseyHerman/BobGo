package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class LevelOneActivity extends AppCompatActivity
{
    //  The grid of SquareObstacles and BlankGridSpaces
    private GridImageThing[][] daGrid = new GridImageThing[20][6];
    //  The one and only Image of Bob! :D
    private ImageView bobImage;
    //  The amount that everything in daGrid and the enemies move every timer call.
    private int xMoveSpeedScreen;
    //  A Timer needs a Handler in Android Studio
    private Handler handler = new Handler();
    //  Moves the level each time it gets called:
    private Timer timer = new Timer();
    //  Will be the width and height of the user's phone/tablet screen, decided at runtime.
    private int screenWidth, screenHeight;
    //  The one and only object of Bob! :D
    private Bob bob;
    private Hater[] haters = new Hater[1];

    //  Android Studio's Main Method:
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        //  14 timer calls per one grid square crossing, 12*14=168
        xMoveSpeedScreen = screenWidth/168;

        //  Stuff for the one and only Bob
        //  REALLY DON'T FORGET TO PUT IT ABOVE THE SQUAREOBSTACLES THAT REFERENCE IT!!
        bobImage = findViewById(R.id.bob1);
        bob = new Bob(bobImage);

        bob.setScreenWidth(screenWidth);
        bob.setScreenHeight(screenHeight);
        //bobImage.setX(0);
        //  2 GridImageThings to the right
        bobImage.setX(2*screenWidth/12);
        bob.setDaGridX(2);
        //  This is the "ground" in the game, lowest place Bob can fall to.
        bob.setLowestY(11*screenHeight/14);
        bobImage.setY(11*screenHeight/14);
        bobImage.getLayoutParams().height = screenHeight/7;
        bobImage.getLayoutParams().width = screenWidth/12;
        //  Half speed:
        //yJumpSpeedBob = screenHeight/196;
        //  Same proportion for y-direction, 7*14=98
        //bob.setJumpSpeed(screenHeight/98);
        //  Double speed:
        bob.setJumpSpeed(screenHeight/49);
        //  How high Bob will jump before he starts falling (2.5 Square Obstacles):
        bob.setJumpHeight(5*screenHeight/14);



        daGrid = placeGridImages(daGrid);

        //  Loops through everything in daGrid and decides where to put it on the screen...
        //  or off of the screen! (stuff to the right that will gradually move left
        //  onto the screen)
        for (int index=0; index<daGrid.length; index++)
        {
            for (int index2=0; index2<daGrid[index].length; index2++)
            {
                daGrid[index][index2].setBob(bob);
                daGrid[index][index2].setBobImage(bobImage);
                daGrid[index][index2].setXMoveSpeedScreen(xMoveSpeedScreen);

                daGrid[index][index2].setImageHeight(screenHeight/7);
                daGrid[index][index2].setImageWidth(screenWidth/12);
                daGrid[index][index2].setImageX(index*screenWidth/12);
                daGrid[index][index2].setImageY((screenHeight / 14) + (index2*screenHeight/7));
            }
        }

        //  Placing enemy:(ImageView) findViewById(R.id.grid0x0));

        haters = placeEnemies(haters);

        for (int index=0; index<haters.length; index++)
        {
            haters[index].setImageHeight(screenHeight/7);
            haters[index].setImageWidth(screenWidth/12);
            haters[index].setXMoveSpeedScreen(xMoveSpeedScreen);
            haters[index].setBob(bob);
            haters[index].setBobImage(bobImage);
        }

        //  Runs the timer once every 0.35 of a second or something, idk, 500 would be once every 0.5 s
        //  but the first timer call isn't until after a second (1,000) of delay.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        levelMoveStuff();
                    }
                });
            }
            },1000, 35);
        //},1000, 70);

    }

    //  Decided to separate the grid placement that will change with each level from the things that will stay the same each level.
    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        //  The number in the left brackets is the x and the right is the y,
        //  so like "daGrid[0][3]" is all the way to the left, but three squares down.
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_12x2));
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_12x3));
        daGrid[12][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_12x4));
        daGrid[12][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_12x5), screenWidth, screenHeight);

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x1));
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x2));
        daGrid[13][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x3));
        daGrid[13][4] = new SquareObstacle((ImageView) findViewById(R.id.grid1_13x4), screenWidth, screenHeight);
        daGrid[13][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_13x5), screenWidth, screenHeight);

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_14x2));
        daGrid[14][3] = new SquareObstacle((ImageView) findViewById(R.id.grid1_14x3), screenWidth, screenHeight);
        daGrid[14][4] = new SquareObstacle((ImageView) findViewById(R.id.grid1_14x4), screenWidth, screenHeight);
        daGrid[14][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_14x5), screenWidth, screenHeight);

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x3));
        daGrid[15][4] = new SquareObstacle((ImageView) findViewById(R.id.grid1_15x4), screenWidth, screenHeight);
        daGrid[15][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_15x5), screenWidth, screenHeight);

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x3));
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x4));
        daGrid[16][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_16x5), screenWidth, screenHeight);

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_17x4));
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_18x4));
        daGrid[18][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x2));
        daGrid[19][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x3));
        daGrid[19][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x4));
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_19x5), screenWidth, screenHeight);

        return daGrid;
    }

    //  Also separating enemy placement:
    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[2][2];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater1_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        return haters;
    }

    public void levelMoveStuff()
    {
        //  Checking to see if Bob collided with an enemy first to get a Game Over right away:
        for (int index=0; index<haters.length; index++)
        {
            if (haters[index].isColliding())
            {
                //  STOP THE TIMER!!!
                timer.cancel();
            }
        }

        //  If no game over, always move enemies no matter what:
        for (int index=0; index<haters.length; index++)
        {
            haters[index].movePath();
        }



        boolean gridShouldMove = true;
        //  Only check the row behind Bob, Bob's row, and the row after Bob, for a total of 18 GridImageThings.
        //  Actually, needs to be 30 GridImageThings (5*6), but close enough.
        for (int index=bob.getDaGridX()-2; index<bob.getDaGridX()+2; index++)
        {
            for (int index2=0; index2<daGrid[index].length; index2++)
            {
                gridShouldMove = daGrid[index][index2].checkCollision();
                //  If Bob is about to crash into even a single square, exit the loop immediately so that this
                //  doesn't get reset to true!
                if (!gridShouldMove)
                {
                    break;
                }
            }
            //  Whoops, and one for the outer loop too!
            if (!gridShouldMove)
            {
                break;
            }
        }

        //  Move all of the grid if Bob isn't colliding w any of the grid.
        if (gridShouldMove)
        {
            for (int index=0; index<daGrid.length; index++)
            {
                for (int index2=0; index2<daGrid[index].length; index2++)
                {
                    daGrid[index][index2].move();
                }
            }

            //  New! Also move the Haters:
            for (int index=0; index<haters.length; index++)
            {
                haters[index].move();
            }

            //  Declare that Bob moved one GridImageThing:                                 // Doesn't matter what the y is.
            if (bobImage.getX()+bobImage.getLayoutParams().width > daGrid[bob.getDaGridX()+1][0].getImageX())
            {
                bob.setDaGridX(bob.getDaGridX()+1);
            }
        }
        //  Move the grid somewhat, but less than the usual x-amount.
        else if (bob.IsMovingRightLittle())
        {
            for (int index=0; index<daGrid.length; index++)
            {
                for (int index2=0; index2<daGrid[index].length; index2++)
                {
                    daGrid[index][index2].move(bob.getXLittleAmount());
                }
            }

            for (int index=0; index<haters.length; index++)
            {
                haters[index].move(bob.getXLittleAmount());
            }

            bob.setMovingRightLittle(false);
        }
        //  Check to see if Bob should move ONE TIME HERE instead of in every SquareObstacle!
        if (bob.IsJumping())
        {
            bobImage.setY(bobImage.getY() - bob.getJumpSpeed());
        }
        //  This is for if Bob needs to jump less than his jump speed, but more than 0:
        else if (bob.IsJumpingLittle())
        {
            //  LittleAmount is positive here (2dp on tablet), so subtract it to make Bob jump up.
            bobImage.setY(bobImage.getY()-bob.getYLittleAmount());
            //  Should only happen one time
            bob.setJumpingLittle(false);
            bob.setJumping(false);
            bob.setFalling(true);
        }
        else if (bob.IsFallingLittle())
        {
            bobImage.setY(bobImage.getY()+bob.getYLittleAmount());
            //  Should only happen one time
            bob.setFallingLittle(false);
            bob.setFalling(false);
            if (bobImage.getY() < bob.getLowestY())
            {
                bob.setOnTopOfSquare(true);
            }
        }
        else if (bob.IsFalling())
        {
            bobImage.setY(bobImage.getY() + bob.getJumpSpeed());
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        //  Checks to see if Bob can jump or if there's like a SquareObstacle or something in the way.
        bob.startJumpMaybe();
        //  Below just has to be there for some reason:
        return true;
    }

}