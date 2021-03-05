package com.lherman.bob_go;

//  This is sort of the "Model" part of the "Model-View-Controller" design pattern. Each "activity_level_" is the "View", and then
//  each "Level_Activity" is the "Controller".

import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

//  Each "Level_Activity" will have exactly one instance of this class and one "activity_level_" in the "layout" folder.
public class GameLogic
{
    private ImageView bobImage;
    //  The amount that everything in daGrid and the enemies move every timer call.
    private int xMoveSpeedScreen;
    //  A Timer needs a Handler in Android Studio
    private Handler levelHandler = new Handler();
    //  Moves the level each time it gets called:
    private Timer levelTimer = new Timer();
    //  Will be the width and height of the user's phone/tablet screen, decided at runtime.
    private int screenWidth, screenHeight;
    private Bob bob;
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

    //  Empty constructor, passing image names and key name in by setters.
    public GameLogic()
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

    public void setBobLogic(ImageView bobImage)
    {
        this.bobImage = bobImage;
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



}
