package com.lherman.bob_go;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

//  A slightly different version of GameLogic for RandomLevelActivity.
public class RandomGameLogic
{
    private ImageView bobImage;
    //  The amount that everything in daGrid and the enemies move every timer call.
    private int xMoveSpeedScreen;
    //  A Timer needs a Handler in Android Studio
    private final Handler levelHandler = new Handler();
    //  Moves the level each time it gets called:
    private final Timer levelTimer = new Timer();

    private int gameOverTimerCounter = 0;
    private int gameOverSquaresUpTopLeft = 3;
    private boolean gameOverBarsAreVisible = false;

    //  Will be the width and height of the user's phone/tablet screen, decided at runtime.
    private int screenWidth, screenHeight;
    private Bob bob;

    //  Best to leave the endButton logic in the Activity, having trouble coding this here:
    private Button endButton;
    private WinCircle winCircle;
    private ImageView endBobImage0, badEndBobImage1, badEndBobImage2, badEndBobImage3,
            goodEndBobImage1, goodEndBobImage2, goodEndBobImage3;

    private final Handler looseHandler = new Handler();
    private final Timer looseTimer = new Timer();
    private int looseTimerCounter = 0;

    private final Handler winHandler = new Handler();
    private final Timer winTimer = new Timer();
    private int winTimerCounter = 0;

    //  The sizes get passed in from the Activities now:
    private GridImageThing[][] daGrid;
    private Hater[] haters;

    //  Changing power-up code to game over code:
    private ImageView[] gameOverBarsUpTop;

    //  Empty constructor, passing image names and key name in by setters.
    public RandomGameLogic()
    {
    }

    public void setScreenWidth(int screenWidth)
    {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight)
    {
        this.screenHeight = screenHeight;
    }

    public void setXMoveSpeedScreen(int xMoveSpeedScreen)
    {
        this.xMoveSpeedScreen = xMoveSpeedScreen;
    }

    public void setBobLogic(ImageView bobImage)
    {
        this.bobImage = bobImage;
        bob = new Bob(bobImage);
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
    }

    public void setEndBobImage0(ImageView endBobImage0)
    {
        this.endBobImage0 = endBobImage0;
    }

    public void setBadEndBobImage1(ImageView badEndBobImage1)
    {
        this.badEndBobImage1 = badEndBobImage1;
    }

    public void setBadEndBobImage2(ImageView badEndBobImage2)
    {
        this.badEndBobImage2 = badEndBobImage2;
    }

    public void setBadEndBobImage3(ImageView badEndBobImage3)
    {
        this.badEndBobImage3 = badEndBobImage3;
    }

    public void setGoodEndBobImage1(ImageView goodEndBobImage1)
    {
        this.goodEndBobImage1 = goodEndBobImage1;
    }

    public void setGoodEndBobImage2(ImageView goodEndBobImage2)
    {
        this.goodEndBobImage2 = goodEndBobImage2;
    }

    public void setGoodEndBobImage3(ImageView goodEndBobImage3)
    {
        this.goodEndBobImage3 = goodEndBobImage3;
    }

    public void setDaGridLogic(GridImageThing[][] daGrid)
    {
        this.daGrid = daGrid;
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
    }

    public void setWinCircleLogic(WinCircle winCircle, GridImageThing theGridSpaceItsOn)
    {
        this.winCircle = winCircle;
        winCircle.setBobImage(bobImage);
        winCircle.setXMoveSpeedScreen(xMoveSpeedScreen);
        winCircle.setImageHeight(screenHeight/7);
        winCircle.setImageWidth(screenWidth/12);
        winCircle.setImageX(theGridSpaceItsOn.getImageX());
        winCircle.setImageY(theGridSpaceItsOn.getImageY());
    }

    public void setGameOverBarsLogic(ImageView[] gameOverBarsUpTop)
    {
        this.gameOverBarsUpTop = gameOverBarsUpTop;
    }

    public void setHaterLogic(Hater[] haters)
    {
        this.haters = haters;
        for (Hater hater: haters)
        {
            hater.setImageHeight(screenHeight/7);
            hater.setImageWidth(screenWidth/12);
            hater.setXMoveSpeedScreen(xMoveSpeedScreen);
            hater.setBobImage(bobImage);
        }
    }

    public void setEndButtonLogic(Button endButton)
    {
        this.endButton = endButton;
    }

    //  Includes the Handler:
    public void setLevelTimerLogic()
    {
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
    }

    public void levelMoveStuff()
    {
        //  If Bob is boxed in by SquareObstacles and has no hope of moving again, start counting down to a Game Over:
        if (bob.isHittingDeadEndInRandomLevel())
        {
            gameOverTimerCounter++;

            //  Get rid of one bar in the power-up thing at the top for every GridImageThing that Bob did/could pass.
            if (gameOverTimerCounter % 14 == 0)
            {
                System.out.println("Got here");
                gameOverBarsUpTop[gameOverSquaresUpTopLeft].setVisibility(ImageView.INVISIBLE);
                gameOverSquaresUpTopLeft--;
            }

            //  This number comes from xMoveSpeedScreen. screenWidth / 12 Squares / 14 timer calls per square = 168.
            //if (gameOverTimerCounter == 168)
            //  14 * 4 = 56.
            if (gameOverTimerCounter == 56)
            {
                //  Game over, you got stuck, or got too distracted to jump, idk.
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
                }, 500, 500);
            }

        }
        else
        {
            gameOverTimerCounter = 0;

            for (ImageView gameOverBar: gameOverBarsUpTop)
            {
                gameOverBar.setVisibility(ImageView.INVISIBLE);
            }
        }

        //  Checking to see if Bob collided with an enemy first to get a Game Over right away:
        for (Hater hater: haters)
        {
            if (hater.isColliding())
            {
                levelTimer.cancel();

                looseTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            looseHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    looseTimerStuff();
                                }
                            });
                        }}, 500, 500);

                    break;
            }
        }


        //  If no game over, always move enemies no matter what:
        for (Hater hater: haters)
        {
            hater.movePath();
        }

        if (winCircle.isColliding())
        {
            levelTimer.cancel();

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

            //  Get rid of gameOverBars:
            bob.setIsHittingDeadEndInRandomLevel(false);
            gameOverBarsAreVisible = false;
            gameOverSquaresUpTopLeft = 3;

            for (GridImageThing[] gridImage: daGrid)
            {
                for (GridImageThing gridImage2: gridImage)
                {
                    gridImage2.move();
                }
            }

            //  New! Also move the Haters:
            for (Hater hater: haters)
            {
                hater.move();
            }

            winCircle.move();

            //  Declare that Bob moved one GridImageThing:                                    Doesn't matter what the y is.
            if (bob.getBobImage().getX()+bob.getBobImage().getLayoutParams().width > daGrid[bob.getDaGridX()+1][0].getImageX())
            {
                bob.setDaGridX(bob.getDaGridX()+1);
            }
        }

        //  Move the grid somewhat, but less than the usual x-amount. The amount will be the size of the gaps (margins)
        //  in the x-direction in-between the images in daGrid.
        else if (bob.IsMovingRightLittle())
        {

            //  Get rid of gameOverBars:
            bob.setIsHittingDeadEndInRandomLevel(false);
            gameOverBarsAreVisible = false;
            gameOverSquaresUpTopLeft = 3;

            for (GridImageThing[] gridImage: daGrid)
            {
                for (GridImageThing gridImage2: gridImage)
                {
                    gridImage2.move(bob.getXLittleAmount());
                }
            }

            for (Hater hater: haters)
            {
                hater.move(bob.getXLittleAmount());
            }

            winCircle.move(bob.getXLittleAmount());

            bob.setMovingRightLittle(false);
        }

        //  Start GameOver bars:
        else
        {
            bob.setIsHittingDeadEndInRandomLevel(true);
            if (!gameOverBarsAreVisible)
            {
                for (ImageView gameOverBar : gameOverBarsUpTop)
                {
                    gameOverBar.setVisibility(ImageView.VISIBLE);
                }
                gameOverBarsAreVisible = true;
            }
        }

        //  Check to see if Bob should move ONE TIME HERE instead of in every SquareObstacle!
        if (bob.IsJumping())
        {
            bob.getBobImage().setY(bob.getBobImage().getY() - bob.getJumpSpeed());
        }
        //  This is for if Bob needs to jump less than his jump speed, but more than 0. The amount is based on the gaps (margins)
        //  in the y-direction between the images in daGrid.
        else if (bob.IsJumpingLittle())
        {
            //  LittleAmount is positive here (2dp on tablet), so subtract it to make Bob jump up.
            bob.getBobImage().setY(bob.getBobImage().getY()-bob.getYLittleAmount());
            //  Should only happen one time
            bob.setJumpingLittle(false);
            bob.setJumping(false);
            bob.setFalling(true);
        }
        //  Falling the gap amount
        else if (bob.IsFallingLittle())
        {
            bob.getBobImage().setY(bob.getBobImage().getY()+bob.getYLittleAmount());
            //  Should only happen one time
            bob.setFallingLittle(false);
            bob.setFalling(false);

            if (bob.getBobImage().getY() < bob.getLowestY())
            {
                bob.setOnTopOfSquare(true);
            }
        }
        else if (bob.IsFalling())
        {
            bob.getBobImage().setY(bob.getBobImage().getY() + bob.getJumpSpeed());
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
            endButton.setVisibility(View.VISIBLE);
            winTimer.cancel();
        }

        winTimerCounter++;
    }

    public void bobJumpLogic()
    {
        //  Checks to see if Bob can jump or if there's like a SquareObstacle or something in the way.
        bob.startJumpMaybe();
    }

}
