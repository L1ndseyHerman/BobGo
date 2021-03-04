package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LevelTwoActivity extends AppCompatActivity
{

    //  The grid of SquareObstacles and BlankGridSpaces
    private GridImageThing[][] daGrid = new GridImageThing[12][6];
    //  The one and only Image of Bob! :D
    private ImageView bobImage;
    //  The amount that everything in daGrid and the enemies move every timer call.
    private int xMoveSpeedScreen;
    //  A Timer needs a Handler in Android Studio
    private Handler levelHandler = new Handler();
    //  Moves the level each time it gets called:
    private Timer levelTimer = new Timer();
    //  Will be the width and height of the user's phone/tablet screen, decided at runtime.
    private int screenWidth, screenHeight;
    //  The one and only object of Bob! :D
    private Bob bob;
    private Hater[] haters = new Hater[1];
    private Coin[] coins = new Coin[1];
    private TextView scoreText;
    private int theScore;
    private Button beginButton, endButton;
    private WinCircle winCircle;
    private ImageView endBobImage0, badEndBobImage1, badEndBobImage2, badEndBobImage3,
            goodEndBobImage1, goodEndBobImage2, goodEndBobImage3;

    private Handler looseHandler = new Handler();
    private Timer looseTimer = new Timer();
    private int looseTimerCounter = 0;

    private Handler winHandler = new Handler();
    private Timer winTimer = new Timer();
    private int winTimerCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

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
        bobImage = findViewById(R.id.bob2);
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

        //  For the ending animations:
        endBobImage0 = findViewById(R.id.winLooseBob2_0);

        badEndBobImage1 = findViewById(R.id.looseBob2_1);
        badEndBobImage2 = findViewById(R.id.looseBob2_2);
        badEndBobImage3 = findViewById(R.id.looseBob2_3);

        goodEndBobImage1 = findViewById(R.id.winBob2_1);
        goodEndBobImage2 = findViewById(R.id.winBob2_2);
        goodEndBobImage3 = findViewById(R.id.winBob2_3);

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

        winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle2));
        winCircle.setBob(bob);
        winCircle.setBobImage(bobImage);
        winCircle.setXMoveSpeedScreen(xMoveSpeedScreen);

        winCircle.setImageHeight(screenHeight/7);
        winCircle.setImageWidth(screenWidth/12);
        winCircle.setImageX(daGrid[0][0].getImageX());
        winCircle.setImageY(daGrid[0][0].getImageY());

        theScore = 0;
        scoreText = findViewById(R.id.scoreText2);
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

        beginButton = findViewById(R.id.startButton2);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);
                //  Runs the timer once every 0.35 of a second or something, idk, 500 would be once every 0.5 s
                //  A timer can also have a delay.
                levelTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        levelHandler.post(new Runnable() {
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

        endButton = findViewById(R.id.endButton2);
        endButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(startIntent);
            }
        });

    }

    //  Decided to separate the grid placement that will change with each level from the things that will stay the same each level.
    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        //  The number in the left brackets is the x and the right is the y,
        //  so like "daGrid[0][3]" is all the way to the left, but three squares down.
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin2_0));
        coins[0].setImageX(daGrid[0][1].getImageX());
        coins[0].setImageY(daGrid[0][1].getImageY());

        return coins;
    }

    //  Also separating enemy placement:
    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[0][2];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater2_0));
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
                levelTimer.cancel();

                looseTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        looseHandler.post(new Runnable() {
                            //levelHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                looseTimerStuff();
                            }
                        });
                    }
                },500, 500);

                break;
            }
        }

        //  If no game over, always move enemies no matter what:
        for (int index=0; index<haters.length; index++)
        {
            haters[index].movePath();
        }

        if (winCircle.checkCollision() == true)
        {
            levelTimer.cancel();

            //  Save the score:
            //  BUT ONLY IF IT'S HIGHER THAN THE EXISTING SCORE! FACEPALM!
            SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            int defaultValue = 0;
            int levelTwoHighScore = sharedPrefReturn.getInt("levelTwoHighScore", defaultValue);
            //  If the current high score is greater than the existing one,
            //  or if there is no high score yet (first time level beaten),
            //  save the score.
            if (theScore > levelTwoHighScore)
            {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("levelTwoHighScore", theScore);
                editor.apply();
            }

            winTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    winHandler.post(new Runnable() {
                        //levelHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            winTimerStuff();
                        }
                    });
                }
            },500, 500);

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

            winCircle.move();

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

            winCircle.move(bob.getXLittleAmount());

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

    public void looseTimerStuff()
    {
        if (looseTimerCounter == 0)
        {
            bobImage.setVisibility(ImageView.INVISIBLE);
            endBobImage0.setVisibility(ImageView.VISIBLE);
        }
        else if (looseTimerCounter == 1)
        {
            endBobImage0.setVisibility(ImageView.INVISIBLE);
            badEndBobImage1.setVisibility(ImageView.VISIBLE);
        }
        else if (looseTimerCounter == 2)
        {
            badEndBobImage1.setVisibility(ImageView.INVISIBLE);
            badEndBobImage2.setVisibility(ImageView.VISIBLE);
        }
        else if (looseTimerCounter == 3)
        {
            badEndBobImage2.setVisibility(ImageView.INVISIBLE);
            badEndBobImage3.setVisibility(ImageView.VISIBLE);
        }
        else if (looseTimerCounter == 4)
        {
            scoreText.setText("Game Over.");
            endButton.setVisibility(View.VISIBLE);
            looseTimer.cancel();
        }

        looseTimerCounter++;
    }

    public void winTimerStuff()
    {
        if (winTimerCounter == 0)
        {
            bobImage.setVisibility(ImageView.INVISIBLE);
            endBobImage0.setVisibility(ImageView.VISIBLE);
        }
        else if (winTimerCounter == 1)
        {
            endBobImage0.setVisibility(ImageView.INVISIBLE);
            goodEndBobImage1.setVisibility(ImageView.VISIBLE);
        }
        else if (winTimerCounter == 2)
        {
            goodEndBobImage1.setVisibility(ImageView.INVISIBLE);
            goodEndBobImage2.setVisibility(ImageView.VISIBLE);
        }
        else if (winTimerCounter == 3)
        {
            goodEndBobImage2.setVisibility(ImageView.INVISIBLE);
            goodEndBobImage3.setVisibility(ImageView.VISIBLE);
        }
        else if (winTimerCounter == 4)
        {
            scoreText.setText("You Win!");
            endButton.setVisibility(View.VISIBLE);
            winTimer.cancel();
        }

        winTimerCounter++;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        //  Checks to see if Bob can jump or if there's like a SquareObstacle or something in the way.
        bob.startJumpMaybe();
        //  Below just has to be there for some reason:
        return true;
    }


}