package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LevelOneActivity extends AppCompatActivity
{
    //  The grid of SquareObstacles and BlankGridSpaces
    private GridImageThing[][] daGrid = new GridImageThing[24][6];
    //  The one and only Image of Bob! :D
    private ImageView bobImage;
    //  The amount that everything in daGrid and the enemies move every timer call.
    private int xLevelMove;
    //  A Timer needs a Handler in Android Studio
    private Handler handler = new Handler();
    //  Moves the level each time it gets called:
    private Timer timer = new Timer();
    //  Will be the width and height of the user's phone/tablet screen, decided at runtime.
    private int screenWidth, screenHeight;
    //  The one and only object of Bob! :D
    private Bob bob;
    //  Either delete or make it about coins or something.
    private TextView someText;

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
        xLevelMove = screenWidth/168;

        //  Stuff for the one and only Bob
        //  REALLY DON'T FORGET TO PUT IT ABOVE THE SQUAREOBSTACLES THAT REFERENCE IT!!
        bobImage = findViewById(R.id.bob);
        bob = new Bob(bobImage);

        bob.setScreenWidth(screenWidth);
        bob.setScreenHeight(screenHeight);
        //bobImage.setX(0);
        //  2 GridImageThings to the right
        bobImage.setX(2*screenWidth/12);
        bob.setDaGridX(2);
        //  This is the "ground" in the game, lowest place Bob can fall to.
        bob.setLowestBobY(11*screenHeight/14);
        bobImage.setY(11*screenHeight/14);
        bobImage.getLayoutParams().height = screenHeight/7;
        bobImage.getLayoutParams().width = screenWidth/12;
        //  Half speed:
        //yJumpSpeedBob = screenHeight/196;
        //  Same proportion for y-direction, 7*14=98
        bob.setBobJumpSpeed(screenHeight/98);
        //  How high Bob will jump before he starts falling (2.5 Square Obstacles):
        bob.setJumpHeightBob(5*screenHeight/14);

        someText = findViewById(R.id.levelOneTestText);

        //  The number in the left brackets is the x and the right is the y,
        //  so like "daGrid[0][3]" is all the way to the left, but three squares down.

        //  Let's start with only constructing BlankGridSpaces with the Image (like grid0x0); 1 param.
        //  Square Obstacle also has those three extra params, so make setters in GridImageThing.
        //  Also, should only need bobImage as a param for Bob. Rest can be setters.
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x0), xLevelMove, bobImage, bob);
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x1), xLevelMove, bobImage, bob);
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x2), xLevelMove, bobImage, bob);
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x3), xLevelMove, bobImage, bob);
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x4), xLevelMove, bobImage, bob);
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x5), xLevelMove, bobImage, bob);

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x0), xLevelMove, bobImage, bob);
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x1), xLevelMove, bobImage, bob);
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x2), xLevelMove, bobImage, bob);
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x3), xLevelMove, bobImage, bob);
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x4), xLevelMove, bobImage, bob);
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x5), xLevelMove, bobImage, bob);

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x0), xLevelMove, bobImage, bob);
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x1), xLevelMove, bobImage, bob);
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x2), xLevelMove, bobImage, bob);
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x3), xLevelMove, bobImage, bob);
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x4), xLevelMove, bobImage, bob);
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x5), xLevelMove, bobImage, bob);

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x0), xLevelMove, bobImage, bob);
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x1), xLevelMove, bobImage, bob);
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x2), xLevelMove, bobImage, bob);
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x3), xLevelMove, bobImage, bob);
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x4), xLevelMove, bobImage, bob);
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x5), xLevelMove, bobImage, bob);

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x0), xLevelMove, bobImage, bob);
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x1), xLevelMove, bobImage, bob);
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x2), xLevelMove, bobImage, bob);
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x3), xLevelMove, bobImage, bob);
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x4), xLevelMove, bobImage, bob);
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x5), xLevelMove, bobImage, bob);

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x0), xLevelMove, bobImage, bob);
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x1), xLevelMove, bobImage, bob);
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x2), xLevelMove, bobImage, bob);
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x3), xLevelMove, bobImage, bob);
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x4), xLevelMove, bobImage, bob);
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x5), xLevelMove, bobImage, bob);

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x0), xLevelMove, bobImage, bob);
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x1), xLevelMove, bobImage, bob);
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x2), xLevelMove, bobImage, bob);
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x3), xLevelMove, bobImage, bob);
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x4), xLevelMove, bobImage, bob);
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x5), xLevelMove, bobImage, bob);

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x0), xLevelMove, bobImage, bob);
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x1), xLevelMove, bobImage, bob);
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x2), xLevelMove, bobImage, bob);
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x3), xLevelMove, bobImage, bob);
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x4), xLevelMove, bobImage, bob);
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x5), xLevelMove, bobImage, bob);

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x0), xLevelMove, bobImage, bob);
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x1), xLevelMove, bobImage, bob);
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x2), xLevelMove, bobImage, bob);
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x3), xLevelMove, bobImage, bob);
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x4), xLevelMove, bobImage, bob);
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x5), xLevelMove, bobImage, bob);

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x0), xLevelMove, bobImage, bob);
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x1), xLevelMove, bobImage, bob);
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x2), xLevelMove, bobImage, bob);
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x3), xLevelMove, bobImage, bob);
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x4), xLevelMove, bobImage, bob);
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x5), xLevelMove, bobImage, bob);

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x0), xLevelMove, bobImage, bob);
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x1), xLevelMove, bobImage, bob);
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x2), xLevelMove, bobImage, bob);
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x3), xLevelMove, bobImage, bob);
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x4), xLevelMove, bobImage, bob);
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x5), xLevelMove, bobImage, bob);

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x0), xLevelMove, bobImage, bob);
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x1), xLevelMove, bobImage, bob);
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x2), xLevelMove, bobImage, bob);
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x3), xLevelMove, bobImage, bob);
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x4), xLevelMove, bobImage, bob);
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x5), xLevelMove, bobImage, bob);

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x0), xLevelMove, bobImage, bob);
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x1), xLevelMove, bobImage, bob);
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x2), xLevelMove, bobImage, bob);
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x3), xLevelMove, bobImage, bob);
        //  daGrid[12][4] is a SquareObstacle:
        daGrid[12][4] = new SquareObstacle((ImageView) findViewById(R.id.grid12x4), screenWidth, screenHeight, bobImage, xLevelMove, bob, 1, someText);
        daGrid[12][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x5), xLevelMove, bobImage, bob);

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x0), xLevelMove, bobImage, bob);
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x1), xLevelMove, bobImage, bob);
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x2), xLevelMove, bobImage, bob);
        //daGrid[13][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x3), xLevelMove);
        //  Another SquareObstacle:
        daGrid[13][3] = new SquareObstacle((ImageView) findViewById(R.id.grid13x3), screenWidth, screenHeight, bobImage, xLevelMove, bob, 2, someText);
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x4), xLevelMove, bobImage, bob);
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x5), xLevelMove, bobImage, bob);

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x0), xLevelMove, bobImage, bob);
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x1), xLevelMove, bobImage, bob);
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x2), xLevelMove, bobImage, bob);
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x3), xLevelMove, bobImage, bob);
        daGrid[14][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x4), xLevelMove, bobImage, bob);
        //  3rd SquareObstacle:
        daGrid[14][5] = new SquareObstacle((ImageView) findViewById(R.id.grid14x5), screenWidth, screenHeight, bobImage, xLevelMove, bob, 3, someText);

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x0), xLevelMove, bobImage, bob);
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x1), xLevelMove, bobImage, bob);
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x2), xLevelMove, bobImage, bob);
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x3), xLevelMove, bobImage, bob);
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x4), xLevelMove, bobImage, bob);
        daGrid[15][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x5), xLevelMove, bobImage, bob);

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x0), xLevelMove, bobImage, bob);
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x1), xLevelMove, bobImage, bob);
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x2), xLevelMove, bobImage, bob);
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x3), xLevelMove, bobImage, bob);
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x4), xLevelMove, bobImage, bob);
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x5), xLevelMove, bobImage, bob);

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x0), xLevelMove, bobImage, bob);
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x1), xLevelMove, bobImage, bob);
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x2), xLevelMove, bobImage, bob);
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x3), xLevelMove, bobImage, bob);
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x4), xLevelMove, bobImage, bob);
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x5), xLevelMove, bobImage, bob);

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x0), xLevelMove, bobImage, bob);
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x1), xLevelMove, bobImage, bob);
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x2), xLevelMove, bobImage, bob);
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x3), xLevelMove, bobImage, bob);
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x4), xLevelMove, bobImage, bob);
        //  4th SquareObstacle:
        daGrid[18][5] = new SquareObstacle((ImageView) findViewById(R.id.grid18x5), screenWidth, screenHeight, bobImage, xLevelMove, bob, 4, someText);

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid19x0), xLevelMove, bobImage, bob);
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid19x1), xLevelMove, bobImage, bob);
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid19x2), xLevelMove, bobImage, bob);
        daGrid[19][3] = new SquareObstacle((ImageView) findViewById(R.id.grid19x3), screenWidth, screenHeight, bobImage, xLevelMove, bob, 5, someText);
        daGrid[19][4] = new SquareObstacle((ImageView) findViewById(R.id.grid19x4), screenWidth, screenHeight, bobImage, xLevelMove, bob, 6, someText);
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.grid19x5), screenWidth, screenHeight, bobImage, xLevelMove, bob, 7, someText);

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x0), xLevelMove, bobImage, bob);
        daGrid[20][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x1), xLevelMove, bobImage, bob);
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x2), xLevelMove, bobImage, bob);
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x3), xLevelMove, bobImage, bob);
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x4), xLevelMove, bobImage, bob);
        daGrid[20][5] = new SquareObstacle((ImageView) findViewById(R.id.grid20x5), screenWidth, screenHeight, bobImage, xLevelMove, bob, 8, someText);

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid21x0), xLevelMove, bobImage, bob);
        daGrid[21][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid21x1), xLevelMove, bobImage, bob);
        daGrid[21][2] = new SquareObstacle((ImageView) findViewById(R.id.grid21x2), screenWidth, screenHeight, bobImage, xLevelMove, bob, 9, someText);
        daGrid[21][3] = new SquareObstacle((ImageView) findViewById(R.id.grid21x3), screenWidth, screenHeight, bobImage, xLevelMove, bob, 10, someText);
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid21x4), xLevelMove, bobImage, bob);
        daGrid[21][5] = new SquareObstacle((ImageView) findViewById(R.id.grid21x5), screenWidth, screenHeight, bobImage, xLevelMove, bob, 11, someText);

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x0), xLevelMove, bobImage, bob);
        daGrid[22][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x1), xLevelMove, bobImage, bob);
        daGrid[22][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x2), xLevelMove, bobImage, bob);
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x3), xLevelMove, bobImage, bob);
        daGrid[22][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x4), xLevelMove, bobImage, bob);
        daGrid[22][5] = new SquareObstacle((ImageView) findViewById(R.id.grid22x5), screenWidth, screenHeight, bobImage, xLevelMove, bob, 12, someText);

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x0), xLevelMove, bobImage, bob);
        daGrid[23][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x1), xLevelMove, bobImage, bob);
        daGrid[23][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x2), xLevelMove, bobImage, bob);
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x3), xLevelMove, bobImage, bob);
        daGrid[23][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x4), xLevelMove, bobImage, bob);
        daGrid[23][5] = new SquareObstacle((ImageView) findViewById(R.id.grid23x5), screenWidth, screenHeight, bobImage, xLevelMove, bob, 13, someText);

        //  Loops through everything in daGrid and decides where to put it on the screen...
        //  or off of the screen! (stuff to the right that will gradually move left
        //  onto the screen)
        for (int index=0; index<daGrid.length; index++)
        {
            for (int index2=0; index2<daGrid[index].length; index2++)
            {
                daGrid[index][index2].setImageHeight(screenHeight/7);
                daGrid[index][index2].setImageWidth(screenWidth/12);
                daGrid[index][index2].setImageX(index*screenWidth/12);
                daGrid[index][index2].setImageY((screenHeight / 14) + (index2*screenHeight/7));
            }
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


    public void levelMoveStuff()
    {
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
                if (gridShouldMove == false)
                {
                    break;
                }
            }
            //  Whoops, and one for the outer loop too!
            if (gridShouldMove == false)
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

            //  Declare that Bob moved one GridImageThing:                                 // Doesn't matter what the y is.
            if (bobImage.getX()+bobImage.getLayoutParams().width > daGrid[bob.getDaGridX()+1][0].getImageX())
            {
                bob.setDaGridX(bob.getDaGridX()+1);
            }
        }
        else if (bob.getRightLittleNow() == true)
        {
            for (int index=0; index<daGrid.length; index++)
            {
                for (int index2=0; index2<daGrid[index].length; index2++)
                {
                    daGrid[index][index2].move(bob.getXLittleAmount());
                }
            }
            bob.setRightLittleNow(false);
        }
        //  Check to see if Bob should move ONE TIME HERE instead of in every SquareObstacle!
        if (bob.getJumpingNow() == true)
        {
            //  Bob jumps
            bobImage.setY(bobImage.getY() - bob.getBobJumpSpeed());
        }
        //  This is for if Bob needs to jump less than his jump speed, but more than 0:
        else if (bob.getJumpingLittleNow() == true)
        {
            //  LittleAmount is positive here (2dp on tablet), so subtract it to make Bob jump up.
            bobImage.setY(bobImage.getY()-bob.getLittleAmount());
            //  Should only happen one time
            bob.setJumpingLittleNow(false);
            bob.setJumpingNow(false);
            bob.setFallingNow(true);
        }
        else if (bob.getFallingLittleNow() == true)
        {
            bobImage.setY(bobImage.getY()+bob.getLittleAmount());
            //  Should only happen one time
            bob.setFallingLittleNow(false);
            bob.setFallingNow(false);
            if (bobImage.getY() < bob.getLowestBobY())
            {
                bob.setOnTopOfSquare(true);
            }
        }
        else if (bob.getFallingNow() == true)
        {
            //  Bob falls
            bobImage.setY(bobImage.getY() + bob.getBobJumpSpeed());
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