package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RandomLevelActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[6][6];
    private Hater[] haters = new Hater[1];
    private Coin[] coins = new Coin[1];
    private Button beginButton;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_level);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setXMoveSpeedScreen(screenWidth /168);

        ImageView bobImage = findViewById(R.id.bobR);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBobR_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBobR_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBobR_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBobR_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBobR_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBobR_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBobR_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircleR));
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreTextR);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButtonR);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                //  Not saving the score for this, in fact the Coin is only there bec I'm too lazy to code if-statements
                //  around everything coin-related the way I did for if the powerUps are present vs absent in that level.

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButtonR);
        gameLogic.setEndButtonLogic(endButton);
        endButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

    }

    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x5));

        /*
        //  2 means 0 or 1.
        //Randomly choosing either the BlankGridSpace or Square to go there.
        int theRandomNumber;

        //  This is annoying, there's got to be some way to do this with a loop, but the R.ids won't work....
        theRandomNumber = (int)(2*Math.random());
        //  BlankGridSpace
        if (theRandomNumber == 0)
        {
            daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_4x0_B));
            findViewById(R.id.gridR_4x0_S).setVisibility(ImageView.INVISIBLE);
        }
        //  SquareObstacle
        else
        {
            daGrid[4][0] = new SquareObstacle((ImageView) findViewById(R.id.gridR_4x0_S));
            findViewById(R.id.gridR_4x0_B).setVisibility(ImageView.INVISIBLE);
        }

        theRandomNumber = (int)(2*Math.random());
        if (theRandomNumber == 0)
        {
            daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_4x1_B));
            findViewById(R.id.gridR_4x1_S).setVisibility(ImageView.INVISIBLE);
        }
        else
        {
            daGrid[4][1] = new SquareObstacle((ImageView) findViewById(R.id.gridR_4x1_S));
            findViewById(R.id.gridR_4x1_B).setVisibility(ImageView.INVISIBLE);
        }

        theRandomNumber = (int)(2*Math.random());
        if (theRandomNumber == 0)
        {
            daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_4x2_B));
            findViewById(R.id.gridR_4x2_S).setVisibility(ImageView.INVISIBLE);
        }
        else
        {
            daGrid[4][2] = new SquareObstacle((ImageView) findViewById(R.id.gridR_4x2_S));
            findViewById(R.id.gridR_4x2_B).setVisibility(ImageView.INVISIBLE);
        }

        theRandomNumber = (int)(2*Math.random());
        if (theRandomNumber == 0)
        {
            daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_4x3_B));
            findViewById(R.id.gridR_4x3_S).setVisibility(ImageView.INVISIBLE);
        }
        else
        {
            daGrid[4][3] = new SquareObstacle((ImageView) findViewById(R.id.gridR_4x3_S));
            findViewById(R.id.gridR_4x3_B).setVisibility(ImageView.INVISIBLE);
        }

        theRandomNumber = (int)(2*Math.random());
        if (theRandomNumber == 0)
        {
            daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_4x4_B));
            findViewById(R.id.gridR_4x4_S).setVisibility(ImageView.INVISIBLE);
        }
        else
        {
            daGrid[4][4] = new SquareObstacle((ImageView) findViewById(R.id.gridR_4x4_S));
            findViewById(R.id.gridR_4x4_B).setVisibility(ImageView.INVISIBLE);
        }

        theRandomNumber = (int)(2*Math.random());
        if (theRandomNumber == 0)
        {
            daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_4x5_B));
            findViewById(R.id.gridR_4x5_S).setVisibility(ImageView.INVISIBLE);
        }
        else
        {
            daGrid[4][5] = new SquareObstacle((ImageView) findViewById(R.id.gridR_4x5_S));
            findViewById(R.id.gridR_4x5_B).setVisibility(ImageView.INVISIBLE);
        }
        */

        daGrid[4][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x0_B), (ImageView)findViewById(R.id.gridR_4x0_S));
        daGrid[4][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x1_B), (ImageView)findViewById(R.id.gridR_4x1_S));
        daGrid[4][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x2_B), (ImageView)findViewById(R.id.gridR_4x2_S));
        daGrid[4][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x3_B), (ImageView)findViewById(R.id.gridR_4x3_S));
        daGrid[4][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x4_B), (ImageView)findViewById(R.id.gridR_4x4_S));
        daGrid[4][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x5_B), (ImageView)findViewById(R.id.gridR_4x5_S));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_5x5));

        return daGrid;
    }

    public GridImageThing chooseSpaceOrSquare(ImageView spaceImage, ImageView squareImage)
    {
        //  Gets 0 or 1
        int theRandomNumber = (int)(2*Math.random());

        //  BlankGridSpace
        if (theRandomNumber == 0)
        {
            squareImage.setVisibility(ImageView.INVISIBLE);
            return new BlankGridSpace(spaceImage);
        }
        //  SquareObstacle
        spaceImage.setVisibility(ImageView.INVISIBLE);
        return new SquareObstacle(squareImage);
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coinR_0));
        coins[0].setImageX(daGrid[0][0].getImageX());
        coins[0].setImageY(daGrid[0][0].getImageY());

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

        haters[0] = new Hater((ImageView) findViewById(R.id.haterR_0));
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