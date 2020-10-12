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

public class TutorialActivity extends AppCompatActivity
{

    private Handler handler4 = new Handler();
    private Timer timer4 = new Timer();

    private boolean jumpingNow4 = false;
    private boolean fallingNow4 = false;

    private int height4 = 0;
    private int width4 = 0;

    //private int minEnemyX;
    //private int maxEnemyX;

    //  168\12 = 14, means 14 timer calls till cross 1/12 of x-direction of screen.


    //  28 means the Hater will go 2 grid spaces in front of the square, then come back under the square
    //  once the square moves two grid spaces.
    private int timerCallsTillSwitchLeft = 28;
    //private int timerCallsTillSwitchRight = timerCallsTillSwitchLeft/2;
    private int timerCallsTillSwitchRight = timerCallsTillSwitchLeft;
    private int timerCounter = 0;
    private String currentDirection = "left";

    private int enemySpeedX;
    private int gridSpeedX;

    private int bobSpeedY;

    ImageView enemyEnemyEnemy;
    ImageView duhSquare;
    TextView enemyTextStuff;
    ImageView tutorialBob;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //int width = size.x;
        width4 = size.x;
        height4 = size.y;

        //ImageView duhSquare = findViewById(R.id.duhSquare);
        duhSquare = findViewById(R.id.duhSquare);
        duhSquare.setX(12*width4/12);
        duhSquare.setY((height4 / 14) + (height4 / 7));
        duhSquare.getLayoutParams().height = height4 / 7;
        duhSquare.getLayoutParams().width = width4 / 12;

        //ImageView enemyEnemyEnemy = findViewById(R.id.enemyEnemyEnemy);
        enemyEnemyEnemy = findViewById(R.id.enemyEnemyEnemy);
        enemyEnemyEnemy.setX(12*width4/12);
        enemyEnemyEnemy.setY((height4 / 14) + (4 * height4 / 7));
        enemyEnemyEnemy.getLayoutParams().height = height4 / 7;
        enemyEnemyEnemy.getLayoutParams().width = width4 / 12;

        //minEnemyX = 5*width4/12;
        //maxEnemyX = 7*width4/12;

        tutorialBob = findViewById(R.id.tutorialBob);
        tutorialBob.setX(0);
        tutorialBob.setY((height4 / 14) + (5 * height4 / 7));
        tutorialBob.getLayoutParams().height = height4 / 7;
        tutorialBob.getLayoutParams().width = width4 / 12;

        //  Same proportion for y-direction, 7*14=98
        bobSpeedY = height4/98;


        //maneuverSquare1.setX(maneuverSquare1.getX() - width2 / 166);
        //enemySpeedX = -(width4/168);
        enemyTextStuff = findViewById(R.id.enemyTextStuff);

        gridSpeedX = -(width4/168);
        enemySpeedX = gridSpeedX * 2;


        timer4.schedule(new TimerTask() {
            @Override
            public void run() {
                handler4.post(new Runnable() {
                    @Override
                    public void run() {
                        enemyMoveStuff();
                    }
                });
            }
            //},0, 40);
        },0, 35);
    }

    public void enemyMoveStuff()
    {
        //  Bob code from previous tests:
        if (jumpingNow4 == true)
        {
            //bobY = bobY - 7;
            //  Same proportion for y-direction, 7*14=98
            //bobY = bobY - height/98;
            tutorialBob.setY(tutorialBob.getY()-bobSpeedY);
            //if (bobY > 151)
            //if (bobY < 3*height/7)
            if (tutorialBob.getY() <= 6*height4/14)
            {
                jumpingNow4 = false;
                fallingNow4 = true;
            }
        }
        if (fallingNow4 == true)
        {
            //bobY = bobY + 7;
            //  Same proportion for y-direction, 7*14=98
            //bobY = bobY + height/98;
            tutorialBob.setY(tutorialBob.getY()+bobSpeedY);
            //if (bobY > 5*height/7)
            if (tutorialBob.getY() >= 11*height4/14)
            {
                fallingNow4 = false;
            }
        }


        //  New enemy code below:
        timerCounter++;
        //enemyTextStuff.setText("timerCounter=" + timerCounter);

        /*if ((enemyEnemyEnemy.getX()+enemySpeedX >= maxEnemyX) || (enemyEnemyEnemy.getX()+enemySpeedX <= minEnemyX))
        {
            enemySpeedX = enemySpeedX * -1;
        }*/

        /*if (timerCounter >= timerCallsTillSwitch)
        {
            if (enemySpeedX == gridSpeedX*2)
            {
                enemySpeedX = -gridSpeedX;
            }
            else
            {
                enemySpeedX = gridSpeedX*2;
            }
            //enemySpeedX = -enemySpeedX;*/
        if (currentDirection=="left" && timerCounter>=timerCallsTillSwitchLeft)
        {
            //enemySpeedX = -gridSpeedX;
            enemySpeedX = 0;
            currentDirection = "right";
            timerCounter = 0;
        }
        else if (currentDirection=="right" && timerCounter>=timerCallsTillSwitchRight)
        {
            enemySpeedX = gridSpeedX*2;
            currentDirection = "left";
            timerCounter = 0;
        }


        //timerCounter = 0;

        enemyEnemyEnemy.setX(enemyEnemyEnemy.getX() + enemySpeedX);
        //enemyEnemyEnemy.setX(enemyEnemyEnemy.getX() + enemySpeedX + gridSpeedX);
        duhSquare.setX(duhSquare.getX() + gridSpeedX);


        if ((tutorialBob.getX()+tutorialBob.getLayoutParams().width > enemyEnemyEnemy.getX() && tutorialBob.getX() < enemyEnemyEnemy.getX() && tutorialBob.getY() < enemyEnemyEnemy.getY()+enemyEnemyEnemy.getLayoutParams().height && tutorialBob.getY()+tutorialBob.getLayoutParams().height > enemyEnemyEnemy.getY())
        || (tutorialBob.getX() < enemyEnemyEnemy.getX()+enemyEnemyEnemy.getLayoutParams().width && tutorialBob.getX() > enemyEnemyEnemy.getX() && tutorialBob.getY() < enemyEnemyEnemy.getY()+enemyEnemyEnemy.getLayoutParams().height && tutorialBob.getY()+tutorialBob.getLayoutParams().height > enemyEnemyEnemy.getY())
        || (tutorialBob.getY()+tutorialBob.getLayoutParams().height > enemyEnemyEnemy.getY() && tutorialBob.getY() < enemyEnemyEnemy.getY() && tutorialBob.getX() < enemyEnemyEnemy.getX()+enemyEnemyEnemy.getWidth() && tutorialBob.getX()+tutorialBob.getLayoutParams().width > enemyEnemyEnemy.getX())
        || (tutorialBob.getY() < enemyEnemyEnemy.getY()+enemyEnemyEnemy.getLayoutParams().height && tutorialBob.getY() > enemyEnemyEnemy.getY() && tutorialBob.getX() < enemyEnemyEnemy.getX()+enemyEnemyEnemy.getLayoutParams().width && tutorialBob.getX()+tutorialBob.getLayoutParams().width > enemyEnemyEnemy.getX()))
        {
            //enemyTextStuff.setText("Game over!");
            enemyTextStuff.setText("BobX=" + tutorialBob.getX() + " BobW=" + tutorialBob.getLayoutParams().width +
                    " BobY=" + tutorialBob.getY() + " BobH=" + tutorialBob.getLayoutParams().height +
                    " HaterX=" + enemyEnemyEnemy.getX() + " HaterW=" + enemyEnemyEnemy.getLayoutParams().width +
                    " HaterY=" + enemyEnemyEnemy.getY() + " HaterH=" + enemyEnemyEnemy.getLayoutParams().height +
            "                                            GAME OVER! TIMER CANCELLED!!");

            timer4.cancel();
        }

    }




    public boolean onTouchEvent(MotionEvent event)
    {
        //System.out.println("BobY = " + bobY + " Bob Height = " + height);

        //if (bobY > 151)
        if (jumpingNow4==false && fallingNow4==false)
        {
            jumpingNow4 = true;
        }
        //  Below just has to be there for some reason:
        return true;
    }

}