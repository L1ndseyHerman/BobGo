package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class RandomActivity extends AppCompatActivity
{

    private Handler handler3 = new Handler();
    private Timer timer3 = new Timer();
    private ImageView[][] squares = new ImageView[12][6];
    private int width3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //int width = size.x;
        width3 = size.x;
        int height = size.y;



        //ImageView[][] squares = new ImageView[12][6];

        //  The number in the left brackets is the x and the right is the y,
        //  so like "theSquares[0][3]" is all the way to the left, but three squares down.
        squares[0][0] = findViewById(R.id.sq0x0);
        squares[0][1] = findViewById(R.id.sq0x1);
        squares[0][2] = findViewById(R.id.sq0x2);
        squares[0][3] = findViewById(R.id.sq0x3);
        squares[0][4] = findViewById(R.id.sq0x4);
        squares[0][5] = findViewById(R.id.sq0x5);

        squares[1][0] = findViewById(R.id.sq1x0);
        squares[1][1] = findViewById(R.id.sq1x1);
        squares[1][2] = findViewById(R.id.sq1x2);
        squares[1][3] = findViewById(R.id.sq1x3);
        squares[1][4] = findViewById(R.id.sq1x4);
        squares[1][5] = findViewById(R.id.sq1x5);

        squares[2][0] = findViewById(R.id.sq2x0);
        squares[2][1] = findViewById(R.id.sq2x1);
        squares[2][2] = findViewById(R.id.sq2x2);
        squares[2][3] = findViewById(R.id.sq2x3);
        squares[2][4] = findViewById(R.id.sq2x4);
        squares[2][5] = findViewById(R.id.sq2x5);

        squares[3][0] = findViewById(R.id.sq3x0);
        squares[3][1] = findViewById(R.id.sq3x1);
        squares[3][2] = findViewById(R.id.sq3x2);
        squares[3][3] = findViewById(R.id.sq3x3);
        squares[3][4] = findViewById(R.id.sq3x4);
        squares[3][5] = findViewById(R.id.sq3x5);

        squares[4][0] = findViewById(R.id.sq4x0);
        squares[4][1] = findViewById(R.id.sq4x1);
        squares[4][2] = findViewById(R.id.sq4x2);
        squares[4][3] = findViewById(R.id.sq4x3);
        squares[4][4] = findViewById(R.id.sq4x4);
        squares[4][5] = findViewById(R.id.sq4x5);

        squares[5][0] = findViewById(R.id.sq5x0);
        squares[5][1] = findViewById(R.id.sq5x1);
        squares[5][2] = findViewById(R.id.sq5x2);
        squares[5][3] = findViewById(R.id.sq5x3);
        squares[5][4] = findViewById(R.id.sq5x4);
        squares[5][5] = findViewById(R.id.sq5x5);

        squares[6][0] = findViewById(R.id.sq6x0);
        squares[6][1] = findViewById(R.id.sq6x1);
        squares[6][2] = findViewById(R.id.sq6x2);
        squares[6][3] = findViewById(R.id.sq6x3);
        squares[6][4] = findViewById(R.id.sq6x4);
        squares[6][5] = findViewById(R.id.sq6x5);

        squares[7][0] = findViewById(R.id.sq7x0);
        squares[7][1] = findViewById(R.id.sq7x1);
        squares[7][2] = findViewById(R.id.sq7x2);
        squares[7][3] = findViewById(R.id.sq7x3);
        squares[7][4] = findViewById(R.id.sq7x4);
        squares[7][5] = findViewById(R.id.sq7x5);

        squares[8][0] = findViewById(R.id.sq8x0);
        squares[8][1] = findViewById(R.id.sq8x1);
        squares[8][2] = findViewById(R.id.sq8x2);
        squares[8][3] = findViewById(R.id.sq8x3);
        squares[8][4] = findViewById(R.id.sq8x4);
        squares[8][5] = findViewById(R.id.sq8x5);

        squares[9][0] = findViewById(R.id.sq9x0);
        squares[9][1] = findViewById(R.id.sq9x1);
        squares[9][2] = findViewById(R.id.sq9x2);
        squares[9][3] = findViewById(R.id.sq9x3);
        squares[9][4] = findViewById(R.id.sq9x4);
        squares[9][5] = findViewById(R.id.sq9x5);

        squares[10][0] = findViewById(R.id.sq10x0);
        squares[10][1] = findViewById(R.id.sq10x1);
        squares[10][2] = findViewById(R.id.sq10x2);
        squares[10][3] = findViewById(R.id.sq10x3);
        squares[10][4] = findViewById(R.id.sq10x4);
        squares[10][5] = findViewById(R.id.sq10x5);

        squares[11][0] = findViewById(R.id.sq11x0);
        squares[11][1] = findViewById(R.id.sq11x1);
        squares[11][2] = findViewById(R.id.sq11x2);
        squares[11][3] = findViewById(R.id.sq11x3);
        squares[11][4] = findViewById(R.id.sq11x4);
        squares[11][5] = findViewById(R.id.sq11x5);



        for (int index=0; index<squares.length; index++)
        {
            for (int index2=0; index2<squares[index].length; index2++)
            {
                squares[index][index2].getLayoutParams().height = height / 7;
                squares[index][index2].getLayoutParams().width = width3 / 12;
                //squares[index][index2].setX(index*width/12);
                squares[index][index2].setX((width3)+(index*width3/12));
                squares[index][index2].setY((height / 14) + (index2 * height / 7));
            }
        }


        timer3.schedule(new TimerTask() {
            @Override
            public void run() {
                handler3.post(new Runnable() {
                    @Override
                    public void run() {
                        methodName3();
                    }
                });
            }
            //},0, 20);
        },0, 35);

    }



    public void methodName3()
    {
        //bobX = bobX + width/166;
        //bobSeventy.setX(bobX);
        for (int index=0; index<squares.length; index++)
        {
            for (int index2=0; index2<squares[index].length; index2++)
            {
                squares[index][index2].setX(squares[index][index2].getX() - width3/166);
            }
        }
    }
}