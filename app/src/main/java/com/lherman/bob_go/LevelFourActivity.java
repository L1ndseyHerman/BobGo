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

public class LevelFourActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[22][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[1];
    private Coin[] coins = new Coin[1];
    private Button beginButton;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_four);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setxMoveSpeedScreen(screenWidth/168);

        ImageView bobImage = findViewById(R.id.bob4);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob4_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob4_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob4_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob4_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob4_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob4_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob4_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle4));
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreText4);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton4);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelFourHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton4);
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

    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_12x2));
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_12x3));
        daGrid[12][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_12x4), screenWidth, screenHeight);
        daGrid[12][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_12x5), screenWidth, screenHeight);

        daGrid[13][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x0), screenWidth, screenHeight);
        daGrid[13][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x1), screenWidth, screenHeight);
        daGrid[13][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x2), screenWidth, screenHeight);
        daGrid[13][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_13x3));
        daGrid[13][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x4), screenWidth, screenHeight);
        daGrid[13][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x5), screenWidth, screenHeight);

        daGrid[14][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x0), screenWidth, screenHeight);
        daGrid[14][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x1), screenWidth, screenHeight);
        daGrid[14][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x2), screenWidth, screenHeight);
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_14x3));
        daGrid[14][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x4), screenWidth, screenHeight);
        daGrid[14][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x5), screenWidth, screenHeight);

        daGrid[15][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x0), screenWidth, screenHeight);
        daGrid[15][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x1), screenWidth, screenHeight);
        daGrid[15][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x2), screenWidth, screenHeight);
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_15x3));
        daGrid[15][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x4), screenWidth, screenHeight);
        daGrid[15][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x5), screenWidth, screenHeight);

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_16x3));
        daGrid[16][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_16x4), screenWidth, screenHeight);
        daGrid[16][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_16x5), screenWidth, screenHeight);

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x4));
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x4));
        daGrid[18][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x2));
        daGrid[19][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x3));
        daGrid[19][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_19x4), screenWidth, screenHeight);
        daGrid[19][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x5));

        daGrid[20][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_20x0), screenWidth, screenHeight);
        daGrid[20][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_20x1), screenWidth, screenHeight);
        daGrid[20][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_20x2), screenWidth, screenHeight);
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_20x3));
        daGrid[20][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_20x4), screenWidth, screenHeight);
        daGrid[20][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_20x5));

        daGrid[21][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_21x0), screenWidth, screenHeight);
        daGrid[21][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_21x1), screenWidth, screenHeight);
        daGrid[21][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_21x2), screenWidth, screenHeight);
        daGrid[21][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_21x3));
        daGrid[21][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_21x4), screenWidth, screenHeight);
        daGrid[21][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_21x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin4_0));
        coins[0].setImageX(daGrid[12][0].getImageX());
        coins[0].setImageY(daGrid[12][0].getImageY());

        return coins;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[0][0];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater4_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}