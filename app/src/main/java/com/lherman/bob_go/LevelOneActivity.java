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
    private GridImageThing[][] daGrid = new GridImageThing[40][6];
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
    private Hater[] haters = new Hater[2];

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
        bobImage = findViewById(R.id.bob);
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
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x2));
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x3));
        daGrid[12][4] = new SquareObstacle((ImageView) findViewById(R.id.grid12x4), screenWidth, screenHeight);
        daGrid[12][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid12x5));

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x1));
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x2));
        daGrid[13][3] = new SquareObstacle((ImageView) findViewById(R.id.grid13x3), screenWidth, screenHeight);
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x4));
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid13x5));

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x3));
        daGrid[14][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid14x4));
        daGrid[14][5] = new SquareObstacle((ImageView) findViewById(R.id.grid14x5), screenWidth, screenHeight);

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x3));
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x4));
        daGrid[15][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x3));
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x4));
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid16x5));

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x4));
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid18x4));
        daGrid[18][5] = new SquareObstacle((ImageView) findViewById(R.id.grid18x5), screenWidth, screenHeight);

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid19x2));
        daGrid[19][3] = new SquareObstacle((ImageView) findViewById(R.id.grid19x3), screenWidth, screenHeight);
        daGrid[19][4] = new SquareObstacle((ImageView) findViewById(R.id.grid19x4), screenWidth, screenHeight);
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.grid19x5), screenWidth, screenHeight);

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x0));
        daGrid[20][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x1));
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x2));
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x3));
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid20x4));
        daGrid[20][5] = new SquareObstacle((ImageView) findViewById(R.id.grid20x5), screenWidth, screenHeight);

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid21x0));
        daGrid[21][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid21x1));
        daGrid[21][2] = new SquareObstacle((ImageView) findViewById(R.id.grid21x2), screenWidth, screenHeight);
        daGrid[21][3] = new SquareObstacle((ImageView) findViewById(R.id.grid21x3), screenWidth, screenHeight);
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid21x4));
        daGrid[21][5] = new SquareObstacle((ImageView) findViewById(R.id.grid21x5), screenWidth, screenHeight);

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x0));
        daGrid[22][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x1));
        daGrid[22][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x2));
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x3));
        daGrid[22][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid22x4));
        daGrid[22][5] = new SquareObstacle((ImageView) findViewById(R.id.grid22x5), screenWidth, screenHeight);

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x0));
        daGrid[23][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x1));
        daGrid[23][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x2));
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x3));
        daGrid[23][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid23x4));
        daGrid[23][5] = new SquareObstacle((ImageView) findViewById(R.id.grid23x5), screenWidth, screenHeight);

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid24x0));
        daGrid[24][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid24x1));
        daGrid[24][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid24x2));
        daGrid[24][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid24x3));
        daGrid[24][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid24x4));
        daGrid[24][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid24x5));

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid25x0));
        daGrid[25][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid25x1));
        daGrid[25][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid25x2));
        daGrid[25][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid25x3));
        daGrid[25][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid25x4));
        daGrid[25][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid25x5));

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid26x0));
        daGrid[26][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid26x1));
        daGrid[26][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid26x2));
        daGrid[26][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid26x3));
        daGrid[26][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid26x4));
        daGrid[26][5] = new SquareObstacle((ImageView) findViewById(R.id.grid26x5), screenWidth, screenHeight);

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid27x0));
        daGrid[27][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid27x1));
        daGrid[27][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid27x2));
        daGrid[27][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid27x3));
        daGrid[27][4] = new SquareObstacle((ImageView) findViewById(R.id.grid27x4), screenWidth, screenHeight);
        daGrid[27][5] = new SquareObstacle((ImageView) findViewById(R.id.grid27x5), screenWidth, screenHeight);

        daGrid[28][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid28x0));
        daGrid[28][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid28x1));
        daGrid[28][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid28x2));
        daGrid[28][3] = new SquareObstacle((ImageView) findViewById(R.id.grid28x3), screenWidth, screenHeight);
        daGrid[28][4] = new SquareObstacle((ImageView) findViewById(R.id.grid28x4), screenWidth, screenHeight);
        daGrid[28][5] = new SquareObstacle((ImageView) findViewById(R.id.grid28x5), screenWidth, screenHeight);

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid29x0));
        daGrid[29][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid29x1));
        daGrid[29][2] = new SquareObstacle((ImageView) findViewById(R.id.grid29x2), screenWidth, screenHeight);
        daGrid[29][3] = new SquareObstacle((ImageView) findViewById(R.id.grid29x3), screenWidth, screenHeight);
        daGrid[29][4] = new SquareObstacle((ImageView) findViewById(R.id.grid29x4), screenWidth, screenHeight);
        daGrid[29][5] = new SquareObstacle((ImageView) findViewById(R.id.grid29x5), screenWidth, screenHeight);

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid30x0));
        daGrid[30][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid30x1));
        daGrid[30][2] = new SquareObstacle((ImageView) findViewById(R.id.grid30x2), screenWidth, screenHeight);
        daGrid[30][3] = new SquareObstacle((ImageView) findViewById(R.id.grid30x3), screenWidth, screenHeight);
        daGrid[30][4] = new SquareObstacle((ImageView) findViewById(R.id.grid30x4), screenWidth, screenHeight);
        daGrid[30][5] = new SquareObstacle((ImageView) findViewById(R.id.grid30x5), screenWidth, screenHeight);

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid31x0));
        daGrid[31][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid31x1));
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid31x2));
        daGrid[31][3] = new SquareObstacle((ImageView) findViewById(R.id.grid31x3), screenWidth, screenHeight);
        daGrid[31][4] = new SquareObstacle((ImageView) findViewById(R.id.grid31x4), screenWidth, screenHeight);
        daGrid[31][5] = new SquareObstacle((ImageView) findViewById(R.id.grid31x5), screenWidth, screenHeight);

        daGrid[32][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid32x0));
        daGrid[32][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid32x1));
        daGrid[32][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid32x2));
        daGrid[32][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid32x3));
        daGrid[32][4] = new SquareObstacle((ImageView) findViewById(R.id.grid32x4), screenWidth, screenHeight);
        daGrid[32][5] = new SquareObstacle((ImageView) findViewById(R.id.grid32x5), screenWidth, screenHeight);

        daGrid[33][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid33x0));
        daGrid[33][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid33x1));
        daGrid[33][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid33x2));
        daGrid[33][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid33x3));
        daGrid[33][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid33x4));
        daGrid[33][5] = new SquareObstacle((ImageView) findViewById(R.id.grid33x5), screenWidth, screenHeight);

        daGrid[34][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid34x0));
        daGrid[34][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid34x1));
        daGrid[34][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid34x2));
        daGrid[34][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid34x3));
        daGrid[34][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid34x4));
        daGrid[34][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid34x5));

        daGrid[35][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid35x0));
        daGrid[35][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid35x1));
        daGrid[35][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid35x2));
        daGrid[35][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid35x3));
        daGrid[35][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid35x4));
        daGrid[35][5] = new SquareObstacle((ImageView) findViewById(R.id.grid35x5), screenWidth, screenHeight);

        daGrid[36][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid36x0));
        daGrid[36][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid36x1));
        daGrid[36][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid36x2));
        daGrid[36][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid36x3));
        daGrid[36][4] = new SquareObstacle((ImageView) findViewById(R.id.grid36x4), screenWidth, screenHeight);
        daGrid[36][5] = new SquareObstacle((ImageView) findViewById(R.id.grid36x5), screenWidth, screenHeight);

        daGrid[37][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid37x0));
        daGrid[37][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid37x1));
        daGrid[37][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid37x2));
        daGrid[37][3] = new SquareObstacle((ImageView) findViewById(R.id.grid37x3), screenWidth, screenHeight);
        daGrid[37][4] = new SquareObstacle((ImageView) findViewById(R.id.grid37x4), screenWidth, screenHeight);
        daGrid[37][5] = new SquareObstacle((ImageView) findViewById(R.id.grid37x5), screenWidth, screenHeight);

        daGrid[38][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid38x0));
        daGrid[38][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid38x1));
        daGrid[38][2] = new SquareObstacle((ImageView) findViewById(R.id.grid38x2), screenWidth, screenHeight);
        daGrid[38][3] = new SquareObstacle((ImageView) findViewById(R.id.grid38x3), screenWidth, screenHeight);
        daGrid[38][4] = new SquareObstacle((ImageView) findViewById(R.id.grid38x4), screenWidth, screenHeight);
        daGrid[38][5] = new SquareObstacle((ImageView) findViewById(R.id.grid38x5), screenWidth, screenHeight);

        daGrid[39][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid39x0));
        daGrid[39][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid39x1));
        daGrid[39][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid39x2));
        daGrid[39][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid39x3));
        daGrid[39][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid39x4));
        daGrid[39][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid39x5));

        return daGrid;
    }

    //  Also separating enemy placement:
    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath = new GridImageThing[1];
        thePath[0] = daGrid[34][2];

        int[] xHaterMoveSpeeds = new int[1];
        xHaterMoveSpeeds[0] = 0;

        int[] yHaterMoveSpeeds = new int[1];
        yHaterMoveSpeeds[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater1));
        haters[0].setImageX(thePath[0].getImageX());
        haters[0].setImageY(thePath[0].getImageY());
        //haters[0].setImageX(34*screenWidth/12);
        //haters[0].setImageY(5*screenHeight/14);
        haters[0].setPath(thePath, xHaterMoveSpeeds, yHaterMoveSpeeds);

        GridImageThing[] thePath2 = new GridImageThing[2];
        thePath2[0] = daGrid[39][2];
        thePath2[1] = daGrid[39][5];

        int[] xHaterMoveSpeeds2 = new int[2];
        xHaterMoveSpeeds2[0] = 0;
        xHaterMoveSpeeds2[1] = 0;

        int[] yHaterMoveSpeeds2 = new int[2];
        yHaterMoveSpeeds2[0] = 1;
        yHaterMoveSpeeds2[1] = 1;

        haters[1] = new Hater((ImageView) findViewById(R.id.hater2));
        haters[1].setImageX(thePath2[0].getImageX());
        haters[1].setImageY(thePath2[0].getImageY());
        haters[1].setPath(thePath2, xHaterMoveSpeeds2, yHaterMoveSpeeds2);

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