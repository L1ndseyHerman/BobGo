package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //  Should hide border at top:
        getSupportActionBar().hide();
    }

    //  This method recognises a tap gesture anywhere on the current screen (Activity).
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //  Where tf is the console?! Doesn't show anywhere....
        //System.out.println("A tap!");

        TextView clickText = (TextView)findViewById(R.id.clickText);
        clickText.setText("You clicked the screen!");

        ImageView bobImage = (ImageView)findViewById(R.id.bobImage);
        bobImage.setX(bobImage.getX() + 30);

        //  Below just has to be there for some reason:
        return true;
    }
}