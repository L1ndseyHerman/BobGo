package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//  Each Level_Activity is the "Controller" part of the "Model-View-Controller" design pattern.
public class LevelOneActivity extends AppCompatActivity
{
    //  The grid of SquareObstacles and BlankGridSpaces
    private GridImageThing[][] daGrid = new GridImageThing[50][6];
    private Hater[] haters = new Hater[3];
    private Coin[] coins = new Coin[2];
    private Button beginButton;

    //  The "Model" part of the "Model-View-Controller" design pattern:
    private GameLogic gameLogic;

    //  Android Studio's Main Method:
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //  The "View" part of the "Model-View-Controller" design pattern:
        setContentView(R.layout.activity_level_one);

        gameLogic = new GameLogic();

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //  Will be the width and height of the user's phone/tablet screen, decided at runtime.
        int screenWidth = size.x;
        int screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        //  14 timer calls per one grid square crossing, 12*14=168
        gameLogic.setXMoveSpeedScreen(screenWidth /168);

        //  REALLY DON'T FORGET TO PUT BOB'S STUFF ABOVE THE SQUAREOBSTACLES THAT REFERENCE IT!!
        ImageView bobImage = findViewById(R.id.bob1);
        gameLogic.setBobLogic(bobImage);

        //  For the ending animations:
        ImageView endBobImage0 = findViewById(R.id.winLooseBob1_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob1_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob1_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob1_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob1_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob1_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob1_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle1));
        gameLogic.setWinCircleLogic(winCircle, daGrid[48][5]);

        TextView scoreText = findViewById(R.id.scoreText1);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton1);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelOneHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton1);
        gameLogic.setEndButtonLogic(endButton);
        endButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), LevelSelectPageOneActivity.class);
                startActivity(startIntent);
            }
        });

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
        daGrid[12][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_12x5));

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x1));
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x2));
        daGrid[13][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x3));
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x4));
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_13x5));

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_14x3));
        daGrid[14][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_14x4));
        daGrid[14][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_14x5));

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x3));
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x4));
        daGrid[15][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x3));
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x4));
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_16x5));

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
        daGrid[18][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x2));
        daGrid[19][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x3));
        daGrid[19][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x4));
        daGrid[19][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_19x5));

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_20x0));
        daGrid[20][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_20x1));
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_20x2));
        daGrid[20][3] = new SquareObstacle((ImageView) findViewById(R.id.grid1_20x3));
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_20x4));
        daGrid[20][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_20x5));

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_21x0));
        daGrid[21][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_21x1));
        daGrid[21][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_21x2));
        daGrid[21][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_21x3));
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_21x4));
        daGrid[21][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_21x5));

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_22x0));
        daGrid[22][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_22x1));
        daGrid[22][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_22x2));
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_22x3));
        daGrid[22][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_22x4));
        daGrid[22][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_22x5));

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_23x0));
        daGrid[23][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_23x1));
        daGrid[23][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_23x2));
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_23x3));
        daGrid[23][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_23x4));
        daGrid[23][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_23x5));

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_24x0));
        daGrid[24][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_24x1));
        daGrid[24][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_24x2));
        daGrid[24][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_24x3));
        daGrid[24][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_24x4));
        daGrid[24][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_24x5));

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_25x0));
        daGrid[25][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_25x1));
        daGrid[25][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_25x2));
        daGrid[25][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_25x3));
        daGrid[25][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_25x4));
        daGrid[25][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_25x5));

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_26x0));
        daGrid[26][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_26x1));
        daGrid[26][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_26x2));
        daGrid[26][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_26x3));
        daGrid[26][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_26x4));
        daGrid[26][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_26x5));

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_27x0));
        daGrid[27][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_27x1));
        daGrid[27][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_27x2));
        daGrid[27][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_27x3));
        daGrid[27][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_27x4));
        daGrid[27][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_27x5));

        daGrid[28][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_28x0));
        daGrid[28][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_28x1));
        daGrid[28][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_28x2));
        daGrid[28][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_28x3));
        daGrid[28][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_28x4));
        daGrid[28][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_28x5));

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_29x0));
        daGrid[29][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_29x1));
        daGrid[29][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_29x2));
        daGrid[29][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_29x3));
        daGrid[29][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_29x4));
        daGrid[29][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_29x5));

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_30x0));
        daGrid[30][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_30x1));
        daGrid[30][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_30x2));
        daGrid[30][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_30x3));
        daGrid[30][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_30x4));
        daGrid[30][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_30x5));

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_31x0));
        daGrid[31][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_31x1));
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_31x2));
        daGrid[31][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_31x3));
        daGrid[31][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_31x4));
        daGrid[31][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_31x5));

        daGrid[32][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_32x0));
        daGrid[32][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_32x1));
        daGrid[32][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_32x2));
        daGrid[32][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_32x3));
        daGrid[32][4] = new SquareObstacle((ImageView) findViewById(R.id.grid1_32x4));
        daGrid[32][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_32x5));

        daGrid[33][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_33x0));
        daGrid[33][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_33x1));
        daGrid[33][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_33x2));
        daGrid[33][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_33x3));
        daGrid[33][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_33x4));
        daGrid[33][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_33x5));

        daGrid[34][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_34x0));
        daGrid[34][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_34x1));
        daGrid[34][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_34x2));
        daGrid[34][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_34x3));
        daGrid[34][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_34x4));
        daGrid[34][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_34x5));

        daGrid[35][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_35x0));
        daGrid[35][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_35x1));
        daGrid[35][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_35x2));
        daGrid[35][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_35x3));
        daGrid[35][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_35x4));
        daGrid[35][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_35x5));

        daGrid[36][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_36x0));
        daGrid[36][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_36x1));
        daGrid[36][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_36x2));
        daGrid[36][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_36x3));
        daGrid[36][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_36x4));
        daGrid[36][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_36x5));

        daGrid[37][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_37x0));
        daGrid[37][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_37x1));
        daGrid[37][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_37x2));
        daGrid[37][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_37x3));
        daGrid[37][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_37x4));
        daGrid[37][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_37x5));

        daGrid[38][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_38x0));
        daGrid[38][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_38x1));
        daGrid[38][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_38x2));
        daGrid[38][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_38x3));
        daGrid[38][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_38x4));
        daGrid[38][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_38x5));

        daGrid[39][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_39x0));
        daGrid[39][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_39x1));
        daGrid[39][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_39x2));
        daGrid[39][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_39x3));
        daGrid[39][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_39x4));
        daGrid[39][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_39x5));

        daGrid[40][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_40x0));
        daGrid[40][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_40x1));
        daGrid[40][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_40x2));
        daGrid[40][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_40x3));
        daGrid[40][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_40x4));
        daGrid[40][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_40x5));

        daGrid[41][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_41x0));
        daGrid[41][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_41x1));
        daGrid[41][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_41x2));
        daGrid[41][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_41x3));
        daGrid[41][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_41x4));
        daGrid[41][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_41x5));

        daGrid[42][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_42x0));
        daGrid[42][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_42x1));
        daGrid[42][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_42x2));
        daGrid[42][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_42x3));
        daGrid[42][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_42x4));
        daGrid[42][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_42x5));

        daGrid[43][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_43x0));
        daGrid[43][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_43x1));
        daGrid[43][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_43x2));
        daGrid[43][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_43x3));
        daGrid[43][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_43x4));
        daGrid[43][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_43x5));

        daGrid[44][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_44x0));
        daGrid[44][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_44x1));
        daGrid[44][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_44x2));
        daGrid[44][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_44x3));
        daGrid[44][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_44x4));
        daGrid[44][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_44x5));

        daGrid[45][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_45x0));
        daGrid[45][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_45x1));
        daGrid[45][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_45x2));
        daGrid[45][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_45x3));
        daGrid[45][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_45x4));
        daGrid[45][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_45x5));

        daGrid[46][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_46x0));
        daGrid[46][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_46x1));
        daGrid[46][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_46x2));
        daGrid[46][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_46x3));
        daGrid[46][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_46x4));
        daGrid[46][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_46x5));

        daGrid[47][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_47x0));
        daGrid[47][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_47x1));
        daGrid[47][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_47x2));
        daGrid[47][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_47x3));
        daGrid[47][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_47x4));
        daGrid[47][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_47x5));

        daGrid[48][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_48x0));
        daGrid[48][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_48x1));
        daGrid[48][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_48x2));
        daGrid[48][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_48x3));
        daGrid[48][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_48x4));
        daGrid[48][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid1_48x5));

        daGrid[49][0] = new SquareObstacle((ImageView) findViewById(R.id.grid1_49x0));
        daGrid[49][1] = new SquareObstacle((ImageView) findViewById(R.id.grid1_49x1));
        daGrid[49][2] = new SquareObstacle((ImageView) findViewById(R.id.grid1_49x2));
        daGrid[49][3] = new SquareObstacle((ImageView) findViewById(R.id.grid1_49x3));
        daGrid[49][4] = new SquareObstacle((ImageView) findViewById(R.id.grid1_49x4));
        daGrid[49][5] = new SquareObstacle((ImageView) findViewById(R.id.grid1_49x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin1_0));
        coins[0].setImageX(daGrid[12][4].getImageX());
        coins[0].setImageY(daGrid[12][4].getImageY());

        coins[1] = new Coin((ImageView) findViewById(R.id.coin1_1));
        coins[1].setImageX(daGrid[20][2].getImageX());
        coins[1].setImageY(daGrid[20][2].getImageY());

        return coins;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[28][5];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater1_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        GridImageThing[] thePath1 = new GridImageThing[2];
        thePath1[0] = daGrid[33][0];
        thePath1[1] = daGrid[33][5];

        float[] xHaterMoveSpeeds1 = new float[2];
        xHaterMoveSpeeds1[0] = 0;
        xHaterMoveSpeeds1[1] = 0;

        float[] yHaterMoveSpeeds1 = new float[2];
        yHaterMoveSpeeds1[0] = (float)0.5;
        yHaterMoveSpeeds1[1] = (float)0.5;

        haters[1] = new Hater((ImageView) findViewById(R.id.hater1_1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, xHaterMoveSpeeds1, yHaterMoveSpeeds1);

        GridImageThing[] thePath2 = new GridImageThing[2];
        thePath2[0] = daGrid[38][5];
        thePath2[1] = daGrid[44][5];

        float[] xHaterMoveSpeeds2 = new float[2];
        xHaterMoveSpeeds2[0] = (float)0.5;
        xHaterMoveSpeeds2[1] = (float)0.5;

        float[] yHaterMoveSpeeds2 = new float[2];
        yHaterMoveSpeeds2[0] = 0;
        yHaterMoveSpeeds2[1] = 0;

        haters[2] = new Hater((ImageView) findViewById(R.id.hater1_2));
        haters[2].setImageX(thePath2[0].getImageX());
        haters[2].setImageY(thePath2[0].getImageY());
        haters[2].setPath(thePath2, xHaterMoveSpeeds2, yHaterMoveSpeeds2);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        //  Below just has to be there for some reason:
        return true;
    }

}