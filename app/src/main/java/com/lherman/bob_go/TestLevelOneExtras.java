package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TestLevelOneExtras extends AppCompatActivity {

    //  The grid of SquareObstacles and BlankGridSpaces
    private GridImageThing[][] daGrid = new GridImageThing[59][6];
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
    private Hater[] haters = new Hater[6];
    private Coin[] coins = new Coin[4];
    private TextView scoreText;
    int theScore;

    //  Android Studio's Main Method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_level_one_extras);

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
        bobImage = findViewById(R.id.b);
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

        theScore = 0;
        scoreText = findViewById(R.id.s);
        coins = placeCoins(coins);

        for (int index=0; index<coins.length; index++)
        {
            coins[index].setImageHeight(screenHeight/7);
            coins[index].setImageWidth(screenWidth/12);
            coins[index].setXMoveSpeedScreen(xMoveSpeedScreen);
            coins[index].setBob(bob);
            coins[index].setBobImage(bobImage);
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



        final Button beginButton = findViewById(R.id.bu);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);
                //  Runs the timer once every 0.35 of a second or something, idk, 500 would be once every 0.5 s
                //  A timer can also have a delay.
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
                },0, 35);
                //},1000, 35);
                //},1000, 70);
            }
        });

    }

    //  Decided to separate the grid placement that will change with each level from the things that will stay the same each level.
    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        //  The number in the left brackets is the x and the right is the y,
        //  so like "daGrid[0][3]" is all the way to the left, but three squares down.
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.g0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.g0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.g0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.g0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.g0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.g0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.g1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.g1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.g1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.g1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.g1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.g1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.g2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.g2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.g2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.g2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.g2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.g2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.g3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.g3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.g3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.g3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.g3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.g3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.g4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.g4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.g4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.g4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.g4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.g4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.g5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.g5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.g5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.g5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.g5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.g5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.g6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.g6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.g6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.g6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.g6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.g6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.g7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.g7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.g7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.g7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.g7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.g7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.g8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.g8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.g8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.g8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.g8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.g8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.g9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.g9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.g9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.g9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.g9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.g9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.g10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.g10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.g10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.g10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.g10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.g10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.g11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.g11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.g11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.g11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.g11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.g11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.g12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.g12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.g12x2));
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.g12x3));
        daGrid[12][4] = new SquareObstacle((ImageView) findViewById(R.id.g12x4), screenWidth, screenHeight);
        daGrid[12][5] = new BlankGridSpace((ImageView) findViewById(R.id.g12x5));

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.g13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.g13x1));
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.g13x2));
        daGrid[13][3] = new SquareObstacle((ImageView) findViewById(R.id.g13x3), screenWidth, screenHeight);
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.g13x4));
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.g13x5));

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.g14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.g14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.g14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.g14x3));
        daGrid[14][4] = new BlankGridSpace((ImageView) findViewById(R.id.g14x4));
        daGrid[14][5] = new SquareObstacle((ImageView) findViewById(R.id.g14x5), screenWidth, screenHeight);

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.g15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.g15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.g15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.g15x3));
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.g15x4));
        daGrid[15][5] = new BlankGridSpace((ImageView) findViewById(R.id.g15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.g16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.g16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.g16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.g16x3));
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.g16x4));
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.g16x5));

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.g17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.g17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.g17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.g17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.g17x4));
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.g17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.g18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.g18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.g18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.g18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.g18x4));
        daGrid[18][5] = new SquareObstacle((ImageView) findViewById(R.id.g18x5), screenWidth, screenHeight);

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.g19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.g19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.g19x2));
        daGrid[19][3] = new SquareObstacle((ImageView) findViewById(R.id.g19x3), screenWidth, screenHeight);
        daGrid[19][4] = new SquareObstacle((ImageView) findViewById(R.id.g19x4), screenWidth, screenHeight);
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.g19x5), screenWidth, screenHeight);

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.g20x0));
        daGrid[20][1] = new BlankGridSpace((ImageView) findViewById(R.id.g20x1));
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.g20x2));
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.g20x3));
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.g20x4));
        daGrid[20][5] = new SquareObstacle((ImageView) findViewById(R.id.g20x5), screenWidth, screenHeight);

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.g21x0));
        daGrid[21][1] = new BlankGridSpace((ImageView) findViewById(R.id.g21x1));
        daGrid[21][2] = new SquareObstacle((ImageView) findViewById(R.id.g21x2), screenWidth, screenHeight);
        daGrid[21][3] = new SquareObstacle((ImageView) findViewById(R.id.g21x3), screenWidth, screenHeight);
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.g21x4));
        daGrid[21][5] = new SquareObstacle((ImageView) findViewById(R.id.g21x5), screenWidth, screenHeight);

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.g22x0));
        daGrid[22][1] = new BlankGridSpace((ImageView) findViewById(R.id.g22x1));
        daGrid[22][2] = new BlankGridSpace((ImageView) findViewById(R.id.g22x2));
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.g22x3));
        daGrid[22][4] = new BlankGridSpace((ImageView) findViewById(R.id.g22x4));
        daGrid[22][5] = new SquareObstacle((ImageView) findViewById(R.id.g22x5), screenWidth, screenHeight);

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.g23x0));
        daGrid[23][1] = new BlankGridSpace((ImageView) findViewById(R.id.g23x1));
        daGrid[23][2] = new BlankGridSpace((ImageView) findViewById(R.id.g23x2));
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.g23x3));
        daGrid[23][4] = new BlankGridSpace((ImageView) findViewById(R.id.g23x4));
        daGrid[23][5] = new SquareObstacle((ImageView) findViewById(R.id.g23x5), screenWidth, screenHeight);

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.g24x0));
        daGrid[24][1] = new BlankGridSpace((ImageView) findViewById(R.id.g24x1));
        daGrid[24][2] = new BlankGridSpace((ImageView) findViewById(R.id.g24x2));
        daGrid[24][3] = new BlankGridSpace((ImageView) findViewById(R.id.g24x3));
        daGrid[24][4] = new BlankGridSpace((ImageView) findViewById(R.id.g24x4));
        daGrid[24][5] = new BlankGridSpace((ImageView) findViewById(R.id.g24x5));

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.g25x0));
        daGrid[25][1] = new BlankGridSpace((ImageView) findViewById(R.id.g25x1));
        daGrid[25][2] = new BlankGridSpace((ImageView) findViewById(R.id.g25x2));
        daGrid[25][3] = new BlankGridSpace((ImageView) findViewById(R.id.g25x3));
        daGrid[25][4] = new BlankGridSpace((ImageView) findViewById(R.id.g25x4));
        daGrid[25][5] = new BlankGridSpace((ImageView) findViewById(R.id.g25x5));

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.g26x0));
        daGrid[26][1] = new BlankGridSpace((ImageView) findViewById(R.id.g26x1));
        daGrid[26][2] = new BlankGridSpace((ImageView) findViewById(R.id.g26x2));
        daGrid[26][3] = new BlankGridSpace((ImageView) findViewById(R.id.g26x3));
        daGrid[26][4] = new BlankGridSpace((ImageView) findViewById(R.id.g26x4));
        daGrid[26][5] = new SquareObstacle((ImageView) findViewById(R.id.g26x5), screenWidth, screenHeight);

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.g27x0));
        daGrid[27][1] = new BlankGridSpace((ImageView) findViewById(R.id.g27x1));
        daGrid[27][2] = new BlankGridSpace((ImageView) findViewById(R.id.g27x2));
        daGrid[27][3] = new BlankGridSpace((ImageView) findViewById(R.id.g27x3));
        daGrid[27][4] = new SquareObstacle((ImageView) findViewById(R.id.g27x4), screenWidth, screenHeight);
        daGrid[27][5] = new SquareObstacle((ImageView) findViewById(R.id.g27x5), screenWidth, screenHeight);

        daGrid[28][0] = new BlankGridSpace((ImageView) findViewById(R.id.g28x0));
        daGrid[28][1] = new BlankGridSpace((ImageView) findViewById(R.id.g28x1));
        daGrid[28][2] = new BlankGridSpace((ImageView) findViewById(R.id.g28x2));
        daGrid[28][3] = new SquareObstacle((ImageView) findViewById(R.id.g28x3), screenWidth, screenHeight);
        daGrid[28][4] = new SquareObstacle((ImageView) findViewById(R.id.g28x4), screenWidth, screenHeight);
        daGrid[28][5] = new SquareObstacle((ImageView) findViewById(R.id.g28x5), screenWidth, screenHeight);

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.g29x0));
        daGrid[29][1] = new BlankGridSpace((ImageView) findViewById(R.id.g29x1));
        daGrid[29][2] = new SquareObstacle((ImageView) findViewById(R.id.g29x2), screenWidth, screenHeight);
        daGrid[29][3] = new SquareObstacle((ImageView) findViewById(R.id.g29x3), screenWidth, screenHeight);
        daGrid[29][4] = new SquareObstacle((ImageView) findViewById(R.id.g29x4), screenWidth, screenHeight);
        daGrid[29][5] = new SquareObstacle((ImageView) findViewById(R.id.g29x5), screenWidth, screenHeight);

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.g30x0));
        daGrid[30][1] = new BlankGridSpace((ImageView) findViewById(R.id.g30x1));
        daGrid[30][2] = new SquareObstacle((ImageView) findViewById(R.id.g30x2), screenWidth, screenHeight);
        daGrid[30][3] = new SquareObstacle((ImageView) findViewById(R.id.g30x3), screenWidth, screenHeight);
        daGrid[30][4] = new SquareObstacle((ImageView) findViewById(R.id.g30x4), screenWidth, screenHeight);
        daGrid[30][5] = new SquareObstacle((ImageView) findViewById(R.id.g30x5), screenWidth, screenHeight);

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.g31x0));
        daGrid[31][1] = new BlankGridSpace((ImageView) findViewById(R.id.g31x1));
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.g31x2));
        daGrid[31][3] = new SquareObstacle((ImageView) findViewById(R.id.g31x3), screenWidth, screenHeight);
        daGrid[31][4] = new SquareObstacle((ImageView) findViewById(R.id.g31x4), screenWidth, screenHeight);
        daGrid[31][5] = new SquareObstacle((ImageView) findViewById(R.id.g31x5), screenWidth, screenHeight);

        daGrid[32][0] = new BlankGridSpace((ImageView) findViewById(R.id.g32x0));
        daGrid[32][1] = new SquareObstacle((ImageView) findViewById(R.id.g32x1), screenWidth, screenHeight);
        daGrid[32][2] = new BlankGridSpace((ImageView) findViewById(R.id.g32x2));
        daGrid[32][3] = new BlankGridSpace((ImageView) findViewById(R.id.g32x3));
        daGrid[32][4] = new SquareObstacle((ImageView) findViewById(R.id.g32x4), screenWidth, screenHeight);
        daGrid[32][5] = new SquareObstacle((ImageView) findViewById(R.id.g32x5), screenWidth, screenHeight);

        daGrid[33][0] = new BlankGridSpace((ImageView) findViewById(R.id.g33x0));
        daGrid[33][1] = new BlankGridSpace((ImageView) findViewById(R.id.g33x1));
        daGrid[33][2] = new BlankGridSpace((ImageView) findViewById(R.id.g33x2));
        daGrid[33][3] = new BlankGridSpace((ImageView) findViewById(R.id.g33x3));
        daGrid[33][4] = new BlankGridSpace((ImageView) findViewById(R.id.g33x4));
        daGrid[33][5] = new SquareObstacle((ImageView) findViewById(R.id.g33x5), screenWidth, screenHeight);

        daGrid[34][0] = new BlankGridSpace((ImageView) findViewById(R.id.g34x0));
        daGrid[34][1] = new BlankGridSpace((ImageView) findViewById(R.id.g34x1));
        daGrid[34][2] = new BlankGridSpace((ImageView) findViewById(R.id.g34x2));
        daGrid[34][3] = new BlankGridSpace((ImageView) findViewById(R.id.g34x3));
        daGrid[34][4] = new BlankGridSpace((ImageView) findViewById(R.id.g34x4));
        daGrid[34][5] = new SquareObstacle((ImageView) findViewById(R.id.g34x5), screenWidth, screenHeight);

        daGrid[35][0] = new BlankGridSpace((ImageView) findViewById(R.id.g35x0));
        daGrid[35][1] = new BlankGridSpace((ImageView) findViewById(R.id.g35x1));
        daGrid[35][2] = new BlankGridSpace((ImageView) findViewById(R.id.g35x2));
        daGrid[35][3] = new BlankGridSpace((ImageView) findViewById(R.id.g35x3));
        daGrid[35][4] = new BlankGridSpace((ImageView) findViewById(R.id.g35x4));
        daGrid[35][5] = new SquareObstacle((ImageView) findViewById(R.id.g35x5), screenWidth, screenHeight);

        daGrid[36][0] = new BlankGridSpace((ImageView) findViewById(R.id.g36x0));
        daGrid[36][1] = new BlankGridSpace((ImageView) findViewById(R.id.g36x1));
        daGrid[36][2] = new BlankGridSpace((ImageView) findViewById(R.id.g36x2));
        daGrid[36][3] = new BlankGridSpace((ImageView) findViewById(R.id.g36x3));
        daGrid[36][4] = new SquareObstacle((ImageView) findViewById(R.id.g36x4), screenWidth, screenHeight);
        daGrid[36][5] = new SquareObstacle((ImageView) findViewById(R.id.g36x5), screenWidth, screenHeight);

        daGrid[37][0] = new BlankGridSpace((ImageView) findViewById(R.id.g37x0));
        daGrid[37][1] = new BlankGridSpace((ImageView) findViewById(R.id.g37x1));
        daGrid[37][2] = new BlankGridSpace((ImageView) findViewById(R.id.g37x2));
        daGrid[37][3] = new SquareObstacle((ImageView) findViewById(R.id.g37x3), screenWidth, screenHeight);
        daGrid[37][4] = new SquareObstacle((ImageView) findViewById(R.id.g37x4), screenWidth, screenHeight);
        daGrid[37][5] = new SquareObstacle((ImageView) findViewById(R.id.g37x5), screenWidth, screenHeight);

        daGrid[38][0] = new BlankGridSpace((ImageView) findViewById(R.id.g38x0));
        daGrid[38][1] = new BlankGridSpace((ImageView) findViewById(R.id.g38x1));
        daGrid[38][2] = new SquareObstacle((ImageView) findViewById(R.id.g38x2), screenWidth, screenHeight);
        daGrid[38][3] = new SquareObstacle((ImageView) findViewById(R.id.g38x3), screenWidth, screenHeight);
        daGrid[38][4] = new SquareObstacle((ImageView) findViewById(R.id.g38x4), screenWidth, screenHeight);
        daGrid[38][5] = new SquareObstacle((ImageView) findViewById(R.id.g38x5), screenWidth, screenHeight);

        daGrid[39][0] = new BlankGridSpace((ImageView) findViewById(R.id.g39x0));
        daGrid[39][1] = new BlankGridSpace((ImageView) findViewById(R.id.g39x1));
        daGrid[39][2] = new BlankGridSpace((ImageView) findViewById(R.id.g39x2));
        daGrid[39][3] = new BlankGridSpace((ImageView) findViewById(R.id.g39x3));
        daGrid[39][4] = new BlankGridSpace((ImageView) findViewById(R.id.g39x4));
        daGrid[39][5] = new BlankGridSpace((ImageView) findViewById(R.id.g39x5));

        daGrid[40][0] = new BlankGridSpace((ImageView) findViewById(R.id.g40x0));
        daGrid[40][1] = new BlankGridSpace((ImageView) findViewById(R.id.g40x1));
        daGrid[40][2] = new BlankGridSpace((ImageView) findViewById(R.id.g40x2));
        daGrid[40][3] = new BlankGridSpace((ImageView) findViewById(R.id.g40x3));
        daGrid[40][4] = new BlankGridSpace((ImageView) findViewById(R.id.g40x4));
        daGrid[40][5] = new BlankGridSpace((ImageView) findViewById(R.id.g40x5));

        daGrid[41][0] = new BlankGridSpace((ImageView) findViewById(R.id.g41x0));
        daGrid[41][1] = new BlankGridSpace((ImageView) findViewById(R.id.g41x1));
        daGrid[41][2] = new BlankGridSpace((ImageView) findViewById(R.id.g41x2));
        daGrid[41][3] = new BlankGridSpace((ImageView) findViewById(R.id.g41x3));
        daGrid[41][4] = new BlankGridSpace((ImageView) findViewById(R.id.g41x4));
        daGrid[41][5] = new BlankGridSpace((ImageView) findViewById(R.id.g41x5));

        daGrid[42][0] = new BlankGridSpace((ImageView) findViewById(R.id.g42x0));
        daGrid[42][1] = new BlankGridSpace((ImageView) findViewById(R.id.g42x1));
        daGrid[42][2] = new BlankGridSpace((ImageView) findViewById(R.id.g42x2));
        daGrid[42][3] = new BlankGridSpace((ImageView) findViewById(R.id.g42x3));
        daGrid[42][4] = new BlankGridSpace((ImageView) findViewById(R.id.g42x4));
        daGrid[42][5] = new BlankGridSpace((ImageView) findViewById(R.id.g42x5));

        daGrid[43][0] = new BlankGridSpace((ImageView) findViewById(R.id.g43x0));
        daGrid[43][1] = new BlankGridSpace((ImageView) findViewById(R.id.g43x1));
        daGrid[43][2] = new BlankGridSpace((ImageView) findViewById(R.id.g43x2));
        daGrid[43][3] = new BlankGridSpace((ImageView) findViewById(R.id.g43x3));
        daGrid[43][4] = new BlankGridSpace((ImageView) findViewById(R.id.g43x4));
        daGrid[43][5] = new SquareObstacle((ImageView) findViewById(R.id.g43x5), screenWidth, screenHeight);

        daGrid[44][0] = new BlankGridSpace((ImageView) findViewById(R.id.g44x0));
        daGrid[44][1] = new BlankGridSpace((ImageView) findViewById(R.id.g44x1));
        daGrid[44][2] = new BlankGridSpace((ImageView) findViewById(R.id.g44x2));
        daGrid[44][3] = new BlankGridSpace((ImageView) findViewById(R.id.g44x3));
        daGrid[44][4] = new BlankGridSpace((ImageView) findViewById(R.id.g44x4));
        daGrid[44][5] = new BlankGridSpace((ImageView) findViewById(R.id.g44x5));

        daGrid[45][0] = new BlankGridSpace((ImageView) findViewById(R.id.g45x0));
        daGrid[45][1] = new BlankGridSpace((ImageView) findViewById(R.id.g45x1));
        daGrid[45][2] = new BlankGridSpace((ImageView) findViewById(R.id.g45x2));
        daGrid[45][3] = new BlankGridSpace((ImageView) findViewById(R.id.g45x3));
        daGrid[45][4] = new BlankGridSpace((ImageView) findViewById(R.id.g45x4));
        daGrid[45][5] = new BlankGridSpace((ImageView) findViewById(R.id.g45x5));

        daGrid[46][0] = new BlankGridSpace((ImageView) findViewById(R.id.g46x0));
        daGrid[46][1] = new BlankGridSpace((ImageView) findViewById(R.id.g46x1));
        daGrid[46][2] = new BlankGridSpace((ImageView) findViewById(R.id.g46x2));
        daGrid[46][3] = new BlankGridSpace((ImageView) findViewById(R.id.g46x3));
        daGrid[46][4] = new BlankGridSpace((ImageView) findViewById(R.id.g46x4));
        daGrid[46][5] = new BlankGridSpace((ImageView) findViewById(R.id.g46x5));

        daGrid[47][0] = new BlankGridSpace((ImageView) findViewById(R.id.g47x0));
        daGrid[47][1] = new BlankGridSpace((ImageView) findViewById(R.id.g47x1));
        daGrid[47][2] = new BlankGridSpace((ImageView) findViewById(R.id.g47x2));
        daGrid[47][3] = new BlankGridSpace((ImageView) findViewById(R.id.g47x3));
        daGrid[47][4] = new BlankGridSpace((ImageView) findViewById(R.id.g47x4));
        daGrid[47][5] = new BlankGridSpace((ImageView) findViewById(R.id.g47x5));

        daGrid[48][0] = new BlankGridSpace((ImageView) findViewById(R.id.g48x0));
        daGrid[48][1] = new BlankGridSpace((ImageView) findViewById(R.id.g48x1));
        daGrid[48][2] = new BlankGridSpace((ImageView) findViewById(R.id.g48x2));
        daGrid[48][3] = new BlankGridSpace((ImageView) findViewById(R.id.g48x3));
        daGrid[48][4] = new BlankGridSpace((ImageView) findViewById(R.id.g48x4));
        daGrid[48][5] = new BlankGridSpace((ImageView) findViewById(R.id.g48x5));

        daGrid[49][0] = new BlankGridSpace((ImageView) findViewById(R.id.g49x0));
        daGrid[49][1] = new BlankGridSpace((ImageView) findViewById(R.id.g49x1));
        daGrid[49][2] = new BlankGridSpace((ImageView) findViewById(R.id.g49x2));
        daGrid[49][3] = new BlankGridSpace((ImageView) findViewById(R.id.g49x3));
        daGrid[49][4] = new BlankGridSpace((ImageView) findViewById(R.id.g49x4));
        daGrid[49][5] = new BlankGridSpace((ImageView) findViewById(R.id.g49x5));

        daGrid[50][0] = new BlankGridSpace((ImageView) findViewById(R.id.g50x0));
        daGrid[50][1] = new BlankGridSpace((ImageView) findViewById(R.id.g50x1));
        daGrid[50][2] = new BlankGridSpace((ImageView) findViewById(R.id.g50x2));
        daGrid[50][3] = new BlankGridSpace((ImageView) findViewById(R.id.g50x3));
        daGrid[50][4] = new BlankGridSpace((ImageView) findViewById(R.id.g50x4));
        daGrid[50][5] = new BlankGridSpace((ImageView) findViewById(R.id.g50x5));

        daGrid[51][0] = new BlankGridSpace((ImageView) findViewById(R.id.g51x0));
        daGrid[51][1] = new BlankGridSpace((ImageView) findViewById(R.id.g51x1));
        daGrid[51][2] = new BlankGridSpace((ImageView) findViewById(R.id.g51x2));
        daGrid[51][3] = new BlankGridSpace((ImageView) findViewById(R.id.g51x3));
        daGrid[51][4] = new BlankGridSpace((ImageView) findViewById(R.id.g51x4));
        daGrid[51][5] = new SquareObstacle((ImageView) findViewById(R.id.g51x5), screenWidth, screenHeight);

        daGrid[52][0] = new BlankGridSpace((ImageView) findViewById(R.id.g52x0));
        daGrid[52][1] = new BlankGridSpace((ImageView) findViewById(R.id.g52x1));
        daGrid[52][2] = new BlankGridSpace((ImageView) findViewById(R.id.g52x2));
        daGrid[52][3] = new BlankGridSpace((ImageView) findViewById(R.id.g52x3));
        daGrid[52][4] = new BlankGridSpace((ImageView) findViewById(R.id.g52x4));
        daGrid[52][5] = new BlankGridSpace((ImageView) findViewById(R.id.g52x5));

        daGrid[53][0] = new BlankGridSpace((ImageView) findViewById(R.id.g53x0));
        daGrid[53][1] = new BlankGridSpace((ImageView) findViewById(R.id.g53x1));
        daGrid[53][2] = new BlankGridSpace((ImageView) findViewById(R.id.g53x2));
        daGrid[53][3] = new BlankGridSpace((ImageView) findViewById(R.id.g53x3));
        daGrid[53][4] = new BlankGridSpace((ImageView) findViewById(R.id.g53x4));
        daGrid[53][5] = new BlankGridSpace((ImageView) findViewById(R.id.g53x5));

        daGrid[54][0] = new BlankGridSpace((ImageView) findViewById(R.id.g54x0));
        daGrid[54][1] = new BlankGridSpace((ImageView) findViewById(R.id.g54x1));
        daGrid[54][2] = new BlankGridSpace((ImageView) findViewById(R.id.g54x2));
        daGrid[54][3] = new BlankGridSpace((ImageView) findViewById(R.id.g54x3));
        daGrid[54][4] = new BlankGridSpace((ImageView) findViewById(R.id.g54x4));
        daGrid[54][5] = new BlankGridSpace((ImageView) findViewById(R.id.g54x5));

        daGrid[55][0] = new BlankGridSpace((ImageView) findViewById(R.id.g55x0));
        daGrid[55][1] = new BlankGridSpace((ImageView) findViewById(R.id.g55x1));
        daGrid[55][2] = new BlankGridSpace((ImageView) findViewById(R.id.g55x2));
        daGrid[55][3] = new BlankGridSpace((ImageView) findViewById(R.id.g55x3));
        daGrid[55][4] = new BlankGridSpace((ImageView) findViewById(R.id.g55x4));
        daGrid[55][5] = new BlankGridSpace((ImageView) findViewById(R.id.g55x5));

        daGrid[56][0] = new BlankGridSpace((ImageView) findViewById(R.id.g56x0));
        daGrid[56][1] = new BlankGridSpace((ImageView) findViewById(R.id.g56x1));
        daGrid[56][2] = new BlankGridSpace((ImageView) findViewById(R.id.g56x2));
        daGrid[56][3] = new BlankGridSpace((ImageView) findViewById(R.id.g56x3));
        daGrid[56][4] = new BlankGridSpace((ImageView) findViewById(R.id.g56x4));
        daGrid[56][5] = new BlankGridSpace((ImageView) findViewById(R.id.g56x5));

        daGrid[57][0] = new BlankGridSpace((ImageView) findViewById(R.id.g57x0));
        daGrid[57][1] = new BlankGridSpace((ImageView) findViewById(R.id.g57x1));
        daGrid[57][2] = new BlankGridSpace((ImageView) findViewById(R.id.g57x2));
        daGrid[57][3] = new BlankGridSpace((ImageView) findViewById(R.id.g57x3));
        daGrid[57][4] = new BlankGridSpace((ImageView) findViewById(R.id.g57x4));
        daGrid[57][5] = new WinCircle((ImageView) findViewById(R.id.g57x5), timer);

        daGrid[58][0] = new SquareObstacle((ImageView) findViewById(R.id.g58x0), screenWidth, screenHeight);
        daGrid[58][1] = new SquareObstacle((ImageView) findViewById(R.id.g58x1), screenWidth, screenHeight);
        daGrid[58][2] = new SquareObstacle((ImageView) findViewById(R.id.g58x2), screenWidth, screenHeight);
        daGrid[58][3] = new SquareObstacle((ImageView) findViewById(R.id.g58x3), screenWidth, screenHeight);
        daGrid[58][4] = new SquareObstacle((ImageView) findViewById(R.id.g58x4), screenWidth, screenHeight);
        daGrid[58][5] = new SquareObstacle((ImageView) findViewById(R.id.g58x5), screenWidth, screenHeight);

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.c0));
        coins[0].setImageX(daGrid[15][3].getImageX());
        coins[0].setImageY(daGrid[15][3].getImageY());

        coins[1] = new Coin((ImageView) findViewById(R.id.c1));
        coins[1].setImageX(daGrid[21][0].getImageX());
        coins[1].setImageY(daGrid[21][0].getImageY());

        coins[2] = new Coin((ImageView) findViewById(R.id.c2));
        coins[2].setImageX(daGrid[32][0].getImageX());
        coins[2].setImageY(daGrid[32][0].getImageY());

        coins[3] = new Coin((ImageView) findViewById(R.id.c3));
        coins[3].setImageX(daGrid[42][5].getImageX());
        coins[3].setImageY(daGrid[42][5].getImageY());

        return coins;
    }

    //  Also separating enemy placement:
    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[34][2];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.h0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        GridImageThing[] thePath1 = new GridImageThing[2];
        thePath1[0] = daGrid[39][2];
        thePath1[1] = daGrid[39][5];

        float[] xHaterMoveSpeeds1 = new float[2];
        xHaterMoveSpeeds1[0] = 0;
        xHaterMoveSpeeds1[1] = 0;

        float[] yHaterMoveSpeeds1 = new float[2];
        yHaterMoveSpeeds1[0] = 1;
        yHaterMoveSpeeds1[1] = 1;

        haters[1] = new Hater((ImageView) findViewById(R.id.h1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, xHaterMoveSpeeds1, yHaterMoveSpeeds1);

        GridImageThing[] thePath2 = new GridImageThing[2];
        thePath2[0] = daGrid[44][5];
        thePath2[1] = daGrid[50][5];

        float[] xHaterMoveSpeeds2 = new float[2];
        xHaterMoveSpeeds2[0] = (float) 0.5;
        xHaterMoveSpeeds2[1] = (float) 0.5;

        float[] yHaterMoveSpeeds2 = new float[2];
        yHaterMoveSpeeds2[0] = 0;
        yHaterMoveSpeeds2[1] = 0;

        haters[2] = new Hater((ImageView) findViewById(R.id.h2));
        haters[2].setImageX(thePath2[0].getImageX());
        haters[2].setImageY(thePath2[0].getImageY());
        haters[2].setPath(thePath2, xHaterMoveSpeeds2, yHaterMoveSpeeds2);

        GridImageThing[] thePath3 = new GridImageThing[2];
        thePath3[0] = daGrid[44][0];
        thePath3[1] = daGrid[50][0];

        float[] xHaterMoveSpeeds3 = new float[2];
        xHaterMoveSpeeds3[0] = 2;
        xHaterMoveSpeeds3[1] = 2;

        float[] yHaterMoveSpeeds3 = new float[2];
        yHaterMoveSpeeds3[0] = 0;
        yHaterMoveSpeeds3[1] = 0;

        haters[3] = new Hater((ImageView) findViewById(R.id.h3));
        haters[3].setImageX(thePath3[0].getImageX());
        haters[3].setImageY(thePath3[0].getImageY());
        haters[3].setPath(thePath3, xHaterMoveSpeeds3, yHaterMoveSpeeds3);

        GridImageThing[] thePath4 = new GridImageThing[2];
        thePath4[0] = daGrid[48][3];
        thePath4[1] = daGrid[54][5];

        float[] xHaterMoveSpeeds4 = new float[2];
        xHaterMoveSpeeds4[0] = (float)1.5;
        xHaterMoveSpeeds4[1] = (float)1.5;

        float[] yHaterMoveSpeeds4 = new float[2];
        yHaterMoveSpeeds4[0] = (float)0.5;
        yHaterMoveSpeeds4[1] = (float)0.5;

        haters[4] = new Hater((ImageView) findViewById(R.id.h4));
        haters[4].setImageX(thePath4[0].getImageX());
        haters[4].setImageY(thePath4[0].getImageY());
        haters[4].setPath(thePath4, xHaterMoveSpeeds4, yHaterMoveSpeeds4);

        GridImageThing[] thePath5 = new GridImageThing[4];

        //  7 used to be 57, 5 55.
        //thePath5[0] = daGrid[7][0];
        //thePath5[1] = daGrid[7][2];
        //thePath5[2] = daGrid[5][2];
        //thePath5[3] = daGrid[5][0];

        thePath5[0] = daGrid[11][0];
        thePath5[1] = daGrid[13][0];
        thePath5[2] = daGrid[13][2];
        thePath5[3] = daGrid[11][2];

        float[] xHaterMoveSpeeds5 = new float[4];
        xHaterMoveSpeeds5[0] = 1;
        xHaterMoveSpeeds5[1] = 0;
        xHaterMoveSpeeds5[2] = (float)2.5;
        xHaterMoveSpeeds5[3] = 0;

        float[] yHaterMoveSpeeds5 = new float[4];
        yHaterMoveSpeeds5[0] = 0;
        yHaterMoveSpeeds5[1] = (float)0.75;
        yHaterMoveSpeeds5[2] = 0;
        yHaterMoveSpeeds5[3] = (float)0.50;

        haters[5] = new Hater((ImageView) findViewById(R.id.h5));
        haters[5].setImageX(thePath5[0].getImageX());
        haters[5].setImageY(thePath5[0].getImageY());
        haters[5].setPath(thePath5, xHaterMoveSpeeds5, yHaterMoveSpeeds5);

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

        //  Now check if Bob collided w a Coin:
        for (int index=0; index<coins.length; index++)
        {
            //  DON'T RECOUNT COINS THAT ARE INVISIBLE (ALREADY GOTTEN)!!
            if (coins[index].isColliding() && coins[index].IsNotInvisible())
            {
                coins[index].setInvisible();
                theScore++;
                scoreText.setText("Score: " + theScore);
            }
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


            for (int index=0; index<coins.length; index++)
            {
                coins[index].move();
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

            for (int index=0; index<coins.length; index++)
            {
                coins[index].move(bob.getXLittleAmount());
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