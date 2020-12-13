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
    private GridImageThing[][] daGrid = new GridImageThing[14][6];
    //  The one and only Image of Bob! :D
    private ImageView bobImage;
    //  The amount that everything in daGrid and the enemies move every timer call.
    private int xLevelMove;
    //  The height Bob can jump (2.5 SquareObstacles high).
    private int yBobJump;
    //  A Timer needs a Handler in Android Studio
    private Handler handler = new Handler();
    //  Moves the level each time it gets called:
    private Timer timer = new Timer();
    //  For Bob:
    private boolean jumpingNow = false;
    private boolean fallingNow = false;
    //  Will be the width and height of the user's phone/tablet screen, decided at runtime.
    int width;
    int height;
    //  The one and only object of Bob! :D
    Bob bob;

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
        width = size.x;
        height = size.y;

        //  14 timer calls per one grid square crossing, 12*14=168
        xLevelMove = width/168;
        //  Same proportion for y-direction, 7*14=98
        yBobJump = height/98;

        //  Stuff for the one and only Bob
        //  REALLY DON'T FORGET TO PUT IT ABOVE THE SQUAREOBSTACLES THAT REFERENCE IT!!
        bobImage = findViewById(R.id.bob);
        bob = new Bob(bobImage, width, height);

        //  The number in the left brackets is the x and the right is the y,
        //  so like "daGrid[0][3]" is all the way to the left, but three squares down.
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x0), xLevelMove);
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x1), xLevelMove);
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x2), xLevelMove);
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x3), xLevelMove);
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x4), xLevelMove);
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x5), xLevelMove);

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x0), xLevelMove);
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x1), xLevelMove);
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x2), xLevelMove);
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x3), xLevelMove);
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x4), xLevelMove);
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x5), xLevelMove);

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x0), xLevelMove);
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x1), xLevelMove);
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x2), xLevelMove);
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x3), xLevelMove);
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x4), xLevelMove);
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x5), xLevelMove);

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x0), xLevelMove);
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x1), xLevelMove);
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x2), xLevelMove);
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x3), xLevelMove);
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x4), xLevelMove);
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x5), xLevelMove);

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x0), xLevelMove);
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x1), xLevelMove);
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x2), xLevelMove);
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x3), xLevelMove);
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x4), xLevelMove);
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x5), xLevelMove);

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x0), xLevelMove);
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x1), xLevelMove);
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x2), xLevelMove);
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x3), xLevelMove);
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x4), xLevelMove);
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x5), xLevelMove);

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x0), xLevelMove);
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x1), xLevelMove);
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x2), xLevelMove);
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x3), xLevelMove);
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x4), xLevelMove);
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x5), xLevelMove);

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x0), xLevelMove);
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x1), xLevelMove);
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x2), xLevelMove);
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x3), xLevelMove);
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x4), xLevelMove);
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x5), xLevelMove);

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x0), xLevelMove);
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x1), xLevelMove);
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x2), xLevelMove);
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x3), xLevelMove);
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x4), xLevelMove);
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x5), xLevelMove);

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x0), xLevelMove);
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x1), xLevelMove);
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x2), xLevelMove);
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x3), xLevelMove);
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x4), xLevelMove);
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x5), xLevelMove);

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x0), xLevelMove);
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x1), xLevelMove);
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x2), xLevelMove);
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x3), xLevelMove);
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x4), xLevelMove);
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x5), xLevelMove);

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x0), xLevelMove);
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x1), xLevelMove);
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x2), xLevelMove);
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x3), xLevelMove);
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x4), xLevelMove);
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x5), xLevelMove);

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x0), xLevelMove);
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x1), xLevelMove);
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x2), xLevelMove);
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x3), xLevelMove);
        //  daGrid[12][4] is a SquareObstacle: 
        daGrid[12][4] = new SquareObstacle((ImageView) findViewById(R.id.grid12x4), width, height, bobImage, xLevelMove, bob);
        daGrid[12][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x5), xLevelMove);

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x0), xLevelMove);
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x1), xLevelMove);
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x2), xLevelMove);
        //  Another SquareObstacle:
        daGrid[13][3] = new SquareObstacle((ImageView) findViewById(R.id.grid13x3), width, height, bobImage, xLevelMove, bob);
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x4), xLevelMove);
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x5), xLevelMove);




        //  Loops through everything in daGrid and decides where to put it on the screen...
        //  or off of the screen! (stuff to the right that will gradually move left
        //  onto the screen)
        for (int index=0; index<daGrid.length; index++)
        {
            for (int index2=0; index2<daGrid[index].length; index2++)
            {
                daGrid[index][index2].setImageHeight(height/7);
                daGrid[index][index2].setImageWidth(width/12);
                daGrid[index][index2].setImageX(index*width/12);
                daGrid[index][index2].setImageY((height / 14) + (index2*height/7));
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
            //},0, 20);
        },1000, 35);

    }


    public void levelMoveStuff()
    {
        boolean gridShouldMove = true;
        for (int index=0; index<daGrid.length; index++)
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