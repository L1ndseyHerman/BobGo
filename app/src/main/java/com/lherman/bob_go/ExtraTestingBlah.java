package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class ExtraTestingBlah extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_testing_blah);

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        /*ImageView s1 = findViewById(R.id.s1);
        s1.setX(0);
        s1.setY(0);
        s1.getLayoutParams().height = height/7;
        s1.getLayoutParams().width = width/12;*/



        //  Works
        /*ImageView[] theSquares = new ImageView[6];

        theSquares[0] = findViewById(R.id.s1);
        theSquares[1] = findViewById(R.id.s2);
        theSquares[2] = findViewById(R.id.s3);
        theSquares[3] = findViewById(R.id.s4);
        theSquares[4] = findViewById(R.id.s5);
        theSquares[5] = findViewById(R.id.s6);
        //theSquares[6] = findViewById(R.id.s7);

        for (int index=0; index<theSquares.length; index++)
        {
            theSquares[index].getLayoutParams().height = height/7;
            theSquares[index].getLayoutParams().width = width/12;
            //  Later....
            //theSquares[index].setX(index*width/12);
            theSquares[index].setX(0);
            //theSquares[index].setY(index*height/7);
            theSquares[index].setY((height/14)+(index*height/7));
        }*/


        ImageView[][] theSquares = new ImageView[12][6];

        //  The number in the left brackets is the x and the right is the y,
        //  so like "theSquares[0][3]" is all the way to the left, but three squares down.
        theSquares[0][0] = findViewById(R.id.s0x0);
        theSquares[0][1] = findViewById(R.id.s0x1);
        theSquares[0][2] = findViewById(R.id.s0x2);
        theSquares[0][3] = findViewById(R.id.s0x3);
        theSquares[0][4] = findViewById(R.id.s0x4);
        theSquares[0][5] = findViewById(R.id.s0x5);

        theSquares[1][0] = findViewById(R.id.s1x0);
        theSquares[1][1] = findViewById(R.id.s1x1);
        theSquares[1][2] = findViewById(R.id.s1x2);
        theSquares[1][3] = findViewById(R.id.s1x3);
        theSquares[1][4] = findViewById(R.id.s1x4);
        theSquares[1][5] = findViewById(R.id.s1x5);

        theSquares[2][0] = findViewById(R.id.s2x0);
        theSquares[2][1] = findViewById(R.id.s2x1);
        theSquares[2][2] = findViewById(R.id.s2x2);
        theSquares[2][3] = findViewById(R.id.s2x3);
        theSquares[2][4] = findViewById(R.id.s2x4);
        theSquares[2][5] = findViewById(R.id.s2x5);

        theSquares[3][0] = findViewById(R.id.s3x0);
        theSquares[3][1] = findViewById(R.id.s3x1);
        theSquares[3][2] = findViewById(R.id.s3x2);
        theSquares[3][3] = findViewById(R.id.s3x3);
        theSquares[3][4] = findViewById(R.id.s3x4);
        theSquares[3][5] = findViewById(R.id.s3x5);

        theSquares[4][0] = findViewById(R.id.s4x0);
        theSquares[4][1] = findViewById(R.id.s4x1);
        theSquares[4][2] = findViewById(R.id.s4x2);
        theSquares[4][3] = findViewById(R.id.s4x3);
        theSquares[4][4] = findViewById(R.id.s4x4);
        theSquares[4][5] = findViewById(R.id.s4x5);

        theSquares[5][0] = findViewById(R.id.s5x0);
        theSquares[5][1] = findViewById(R.id.s5x1);
        theSquares[5][2] = findViewById(R.id.s5x2);
        theSquares[5][3] = findViewById(R.id.s5x3);
        theSquares[5][4] = findViewById(R.id.s5x4);
        theSquares[5][5] = findViewById(R.id.s5x5);

        theSquares[6][0] = findViewById(R.id.s6x0);
        theSquares[6][1] = findViewById(R.id.s6x1);
        theSquares[6][2] = findViewById(R.id.s6x2);
        theSquares[6][3] = findViewById(R.id.s6x3);
        theSquares[6][4] = findViewById(R.id.s6x4);
        theSquares[6][5] = findViewById(R.id.s6x5);

        theSquares[7][0] = findViewById(R.id.s7x0);
        theSquares[7][1] = findViewById(R.id.s7x1);
        theSquares[7][2] = findViewById(R.id.s7x2);
        theSquares[7][3] = findViewById(R.id.s7x3);
        theSquares[7][4] = findViewById(R.id.s7x4);
        theSquares[7][5] = findViewById(R.id.s7x5);

        theSquares[8][0] = findViewById(R.id.s8x0);
        theSquares[8][1] = findViewById(R.id.s8x1);
        theSquares[8][2] = findViewById(R.id.s8x2);
        theSquares[8][3] = findViewById(R.id.s8x3);
        theSquares[8][4] = findViewById(R.id.s8x4);
        theSquares[8][5] = findViewById(R.id.s8x5);

        theSquares[9][0] = findViewById(R.id.s9x0);
        theSquares[9][1] = findViewById(R.id.s9x1);
        theSquares[9][2] = findViewById(R.id.s9x2);
        theSquares[9][3] = findViewById(R.id.s9x3);
        theSquares[9][4] = findViewById(R.id.s9x4);
        theSquares[9][5] = findViewById(R.id.s9x5);

        theSquares[10][0] = findViewById(R.id.s10x0);
        theSquares[10][1] = findViewById(R.id.s10x1);
        theSquares[10][2] = findViewById(R.id.s10x2);
        theSquares[10][3] = findViewById(R.id.s10x3);
        theSquares[10][4] = findViewById(R.id.s10x4);
        theSquares[10][5] = findViewById(R.id.s10x5);

        theSquares[11][0] = findViewById(R.id.s11x0);
        theSquares[11][1] = findViewById(R.id.s11x1);
        theSquares[11][2] = findViewById(R.id.s11x2);
        theSquares[11][3] = findViewById(R.id.s11x3);
        theSquares[11][4] = findViewById(R.id.s11x4);
        theSquares[11][5] = findViewById(R.id.s11x5);



        for (int index=0; index<theSquares.length; index++)
        {
            for (int index2=0; index2<theSquares[index].length; index2++)
            {
                theSquares[index][index2].getLayoutParams().height = height / 7;
                theSquares[index][index2].getLayoutParams().width = width / 12;
                //  Later....
                theSquares[index][index2].setX(index*width/12);
                //theSquares[index][index2].setX(0);
                //theSquares[index].setY(index*height/7);
                theSquares[index][index2].setY((height / 14) + (index2 * height / 7));
            }
        }



    }
}