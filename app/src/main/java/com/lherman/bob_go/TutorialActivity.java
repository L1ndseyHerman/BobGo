package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class TutorialActivity extends AppCompatActivity
{

    private Handler handler4 = new Handler();
    private Timer timer4 = new Timer();

    //private int minEnemyX = 6*width4/12
    private int minEnemyX;
    private int maxEnemyX;
    private int enemySpeedX;

    ImageView enemyEnemyEnemy;

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

        ImageView duhSquare = findViewById(R.id.duhSquare);
        duhSquare.setX(6*width4/12);
        duhSquare.setY((height4 / 14) + (height4 / 7));
        duhSquare.getLayoutParams().height = height4 / 7;
        duhSquare.getLayoutParams().width = width4 / 12;

        //ImageView enemyEnemyEnemy = findViewById(R.id.enemyEnemyEnemy);
        enemyEnemyEnemy = findViewById(R.id.enemyEnemyEnemy);
        enemyEnemyEnemy.setX(6*width4/12);
        enemyEnemyEnemy.setY((height4 / 14) + (4 * height4 / 7));
        enemyEnemyEnemy.getLayoutParams().height = height4 / 7;
        enemyEnemyEnemy.getLayoutParams().width = width4 / 12;

        minEnemyX = 5*width4/12;
        maxEnemyX = 7*width4/12;
        //maneuverSquare1.setX(maneuverSquare1.getX() - width2 / 166);
        enemySpeedX = -(width4/166);


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
        if ((enemyEnemyEnemy.getX()+enemySpeedX >= maxEnemyX) || (enemyEnemyEnemy.getX()+enemySpeedX <= minEnemyX))
        {
            enemySpeedX = enemySpeedX * -1;
        }
        enemyEnemyEnemy.setX(enemyEnemyEnemy.getX() + enemySpeedX);
    }

}