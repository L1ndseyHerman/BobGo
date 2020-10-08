package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TutorialActivity extends AppCompatActivity
{

    private Handler handler4 = new Handler();
    private Timer timer4 = new Timer();

    //private int minEnemyX;
    //private int maxEnemyX;

    //  168\12 = 14, means 14 timer calls till cross 1/12 of x-direction of screen.


    //  28 means the Hater will go 2 grid spaces in front of the square, then come back under the square
    //  once the square moves two grid spaces.
    private int timerCallsTillSwitchLeft = 28;
    private int timerCallsTillSwitchRight = timerCallsTillSwitchLeft/2;
    private int timerCounter = 0;
    private String currentDirection = "left";

    private int enemySpeedX;
    private int gridSpeedX;

    ImageView enemyEnemyEnemy;
    ImageView duhSquare;
    TextView enemyTextStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //  Should hide border at top:
        getSupportActionBar().hide();

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //int width = size.x;
        int width4 = size.x;
        int height4 = size.y;

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
        timerCounter++;
        enemyTextStuff.setText("timerCounter=" + timerCounter);

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
            enemySpeedX = -gridSpeedX;
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
    }

}