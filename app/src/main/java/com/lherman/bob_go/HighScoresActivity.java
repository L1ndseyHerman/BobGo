package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

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

        int halfWidth = size.x/2;
        int halfHeight = size.y/2;

        //  Is this showing up anywhere?
        //Log.e("Width", "" + width);
        //Log.e("height", "" + height);
        String stringWidth = Integer.toString(width);
        String stringHeight = Integer.toString(height);

        String halfStringWidth = Integer.toString(halfWidth);
        String halfStringHeight = Integer.toString(halfHeight);

        //  Resizing square based on screen size.
        ImageView squareLeft = findViewById(R.id.squareLeft);
        ImageView squareRight = findViewById(R.id.squareRight);
        squareLeft.getLayoutParams().height = height/2;
        squareLeft.getLayoutParams().width = width/2;
        squareRight.getLayoutParams().height = height/2;
        squareRight.getLayoutParams().width = width/2;

        TextView theText = findViewById(R.id.textViewTest);
        int x = (int) squareRight.getX();
        String xString = Integer.toString(x);

        //theText.setText(theText.getText() + stringWidth + "," + stringHeight + "," + xString + "," + squareRight.getY() + "," + squareRight.getWidth() + "," + squareRight.getHeight());
        theText.setText("squareRightLeft = " + halfStringWidth + " squareRightRight = " + stringWidth);
    }


}