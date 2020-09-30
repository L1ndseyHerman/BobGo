package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity
{
    boolean jumpingUp = false;
    boolean fallingDown = false;
    //TextView clickText = findViewById(R.id.clickText);
    //ImageView bobImage = findViewById(R.id.bobImage);
    //private TranslateAnimation movingBob;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //  Should hide border at top:
        getSupportActionBar().hide();

        //TextView clickText = findViewById(R.id.clickText);

        /*TranslateAnimation moveDownwards;



        moveDownwards = new TranslateAnimation(0, 0, -100, 1000);
        moveDownwards.setDuration(3000);
        moveDownwards.setFillAfter(true);
        moveDownwards.setRepeatCount(-1);
        findViewById(R.id.bobImage).startAnimation(moveDownwards);*/
    }

    //  This method recognises a tap gesture anywhere on the current screen (Activity).
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //  Where tf is the console?! Doesn't show anywhere....
        //System.out.println("A tap!");

        TextView clickText = findViewById(R.id.clickText);
        //clickText.setText("You clicked the screen!");

        //ImageView bobImage = findViewById(R.id.bobImage);
        TranslateAnimation movingBob;


        //bobImage.setX(bobImage.getX() + 30);
        if (jumpingUp == false && fallingDown == false) {
            jumpingUp = true;
            clickText.setText("Jumping up.");

            movingBob = new TranslateAnimation(0, 0, 0, 1000);
            movingBob.setDuration(3000);
            movingBob.setFillAfter(true);
            movingBob.setRepeatCount(-1);
            findViewById(R.id.bobImage).startAnimation(movingBob);
            //bobImage.setX(bobImage.getX() + 30);
        }
        else
        {
            clickText.setText("Falling down.");
        }

        //  Below just has to be there for some reason:
        return true;
    }




}
