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

public class LevelSevenActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[5][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[1];
    private Coin[] coins = new Coin[1];
    private Button beginButton;
    private GameLogic gameLogic;
    private BrightenUpPowerUp[] powerUps = new BrightenUpPowerUp[1];
    private ImageView[] powerUpBarsUpTop = new ImageView[12];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_seven);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setxMoveSpeedScreen(screenWidth/168);

        ImageView bobImage = findViewById(R.id.bob7);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob7_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob7_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob7_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob7_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob7_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob7_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob7_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle7));
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreText7);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        powerUps = placePowerUps(powerUps);
        ImageView brightenedUpBobImage = findViewById(R.id.brightenedUpBob7);

        powerUpBarsUpTop[0] = findViewById(R.id.brightenedUpTopSun7);
        powerUpBarsUpTop[1] = findViewById(R.id.brightenedUpTopBar7_0);
        powerUpBarsUpTop[2] = findViewById(R.id.brightenedUpTopBar7_1);
        powerUpBarsUpTop[3] = findViewById(R.id.brightenedUpTopBar7_2);
        powerUpBarsUpTop[4] = findViewById(R.id.brightenedUpTopBar7_3);
        powerUpBarsUpTop[5] = findViewById(R.id.brightenedUpTopBar7_4);
        powerUpBarsUpTop[6] = findViewById(R.id.brightenedUpTopBar7_5);
        powerUpBarsUpTop[7] = findViewById(R.id.brightenedUpTopBar7_6);
        powerUpBarsUpTop[8] = findViewById(R.id.brightenedUpTopBar7_7);
        powerUpBarsUpTop[9] = findViewById(R.id.brightenedUpTopBar7_8);
        powerUpBarsUpTop[10] = findViewById(R.id.brightenedUpTopBar7_9);
        powerUpBarsUpTop[11] = findViewById(R.id.brightenedUpTopBar7_10);

        gameLogic.setPowerUpLogic(powerUps, brightenedUpBobImage, powerUpBarsUpTop);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton7);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelSevenHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton7);
        gameLogic.setEndButtonLogic(endButton);
        endButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), LevelSelectPageTwoActivity.class);
                startActivity(startIntent);
            }
        });

    }

    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x4));
        daGrid[4][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_4x5), screenWidth, screenHeight);

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin7_0));
        coins[0].setImageX(daGrid[0][0].getImageX());
        coins[0].setImageY(daGrid[0][0].getImageY());

        return coins;
    }

    public BrightenUpPowerUp[] placePowerUps(BrightenUpPowerUp[] powerUps)
    {
        powerUps[0] = new BrightenUpPowerUp((ImageView) findViewById(R.id.powerUp7_0));
        powerUps[0].setImageX(daGrid[0][0].getImageX());
        powerUps[0].setImageY(daGrid[0][0].getImageY());

        return powerUps;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[0][0];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater7_0));
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