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

        //  Should hide border at top:
        getSupportActionBar().hide();

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

        ImageView[] theSquares = new ImageView[6];

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
        }
    }
}