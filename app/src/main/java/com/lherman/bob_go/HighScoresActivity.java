package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

public class HighScoresActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        //  Will this work on a tablet?
        //  Should hide border at top:
        getSupportActionBar().hide();

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //  Is this showing up anywhere?
        //Log.e("Width", "" + width);
        //Log.e("height", "" + height);

        //  Resizing square based on screen size.
        ImageView squareLeft = findViewById(R.id.squareLeft);
        ImageView squareRight = findViewById(R.id.squareRight);
        squareLeft.getLayoutParams().height = height/2;
        squareLeft.getLayoutParams().width = width/2;
        squareRight.getLayoutParams().height = height/2;
        squareRight.getLayoutParams().width = width/2;

    }
}