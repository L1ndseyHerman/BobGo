package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class RandomActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        //  Should hide border at top:
        getSupportActionBar().hide();

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //ImageView square1 = new ImageView(this);

        ImageView square1 = findViewById(R.id.square1);
        //square1.setImageResource(R.drawable.square);
        square1.setX(0);
        square1.setY(0);
        square1.getLayoutParams().height = height/7;
        square1.getLayoutParams().width = width/12;
        //square1.setMaxWidth(width/12);
        //square1.setMaxHeight(height/7);
        /*LinearLayout.LayoutParams params =  new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        square1.setLayoutParams(params);*/

        //LinearLayout myLayout = (LinearLayout)findViewById(R.id.aLayout);
        //myLayout.addView(square1);
        //ConstraintLayout theConstraintLayout = findViewById(R.id.theConstraintLayout);
        //theConstraintLayout.addView(square1);

        ImageView square2 = findViewById(R.id.square2);
        //ImageView square2 = new ImageView(this);
        //square1.setImageResource(R.drawable.square);
        square2.setX(0);
        square2.setY(height/7);
        square2.getLayoutParams().height = height/7;
        square2.getLayoutParams().width = width/12;
        //theConstraintLayout.addView(square2);

        ImageView square3 = findViewById(R.id.square3);
        //ImageView square3 = new ImageView(this);
        //square1.setImageResource(R.drawable.square);
        square3.setX(width/12);
        square3.setY(0);
        square3.getLayoutParams().height = height/7;
        square3.getLayoutParams().width = width/12;
        //theConstraintLayout.addView(square3);


        ImageView square4 = findViewById(R.id.square4);
        //ImageView square3 = new ImageView(this);
        //square1.setImageResource(R.drawable.square);
        square4.setX(width/12);
        square4.setY(height/7);
        square4.getLayoutParams().height = height/7;
        square4.getLayoutParams().width = width/12;
        //theConstraintLayout.addView(square3);


       /* ImageView square4 = new ImageView(this);

        //ImageView square1 = findViewById(R.id.square1);
        square4.setImageResource(R.drawable.square);
        //square4.setX(width/12);
        //square4.setY(height/7);
        //square4.layout(140, 140, 210, 210);
        square4.getLayoutParams().height = height/7;
        square4.getLayoutParams().width = width/12;
        //square4.setMaxWidth(width/12);
        //square4.setMaxHeight(height/7);


        //LinearLayout myLayout = (LinearLayout)findViewById(R.id.aLayout);
        //myLayout.addView(square1);
        ConstraintLayout theConstraintLayout = findViewById(R.id.theConstraintLayout);
        theConstraintLayout.addView(square4); */
    }
}