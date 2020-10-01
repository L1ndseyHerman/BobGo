package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity
{
    boolean jumpingUp = false;
    boolean fallingDown = false;
    int clickCount = 0;
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
        //final TextView clickText = findViewById(R.id.clickText);
        Timer timer = new Timer();
        TimerTask t = new TimerTask() {
            @Override
            public void run() {

                System.out.println("Woo hoo!");
                //clickText.setText();
            }
        };
        timer.scheduleAtFixedRate(t,1000,1000);
    }

    //  This method recognises a tap gesture anywhere on the current screen (Activity).
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //  Where tf is the console?! Doesn't show anywhere....
        //System.out.println("A tap!");

        TextView clickText = findViewById(R.id.clickText);
        //clickText.setText("You clicked the screen!");

        //ImageView bobImage = findViewById(R.id.bobImage);
        TranslateAnimation jumping;
        TranslateAnimation falling;
        clickCount++;
        jumping = new TranslateAnimation(0, 0, 0, -161);

        //bobImage.setX(bobImage.getX() + 30);
        if (jumpingUp == false && fallingDown == false)
        {
        //if (clickCount == 1)
            jumpingUp = true;
            clickText.setText("Jumping up.");


            jumping.setDuration(1000);
            jumping.setFillAfter(true);
            jumping.setInterpolator(new DecelerateInterpolator(2.f));
            //new DecelerateInterpolator(2);
            //movingBob.setRepeatCount(-1);
            findViewById(R.id.bobImage).startAnimation(jumping);

            //bobImage.setX(bobImage.getX() + 30);
            //jumpingUp = false;
            //fallingDown = true;
        }
        //else if (jumpingUp==false && fallingDown==true)
        /*else if (clickCount == 2)
        {
            clickText.setText("Falling down.");

            falling = new TranslateAnimation(0, 0, 0, 161);
            falling.setDuration(1000);
            falling.setFillAfter(true);
            falling.setInterpolator(new AccelerateInterpolator(2.f));
            //new DecelerateInterpolator(2);
            //movingBob.setRepeatCount(-1);
            findViewById(R.id.bobImage).startAnimation(falling);
            //bobImage.setX(bobImage.getX() + 30);

        }*/
        else if (jumping.hasEnded()) {
            fallingDown = true;
            jumpingUp = false;
            clickText.setText("Falling down");

            falling = new TranslateAnimation(0, 0, 0, 161);
            falling.setDuration(1000);
            falling.setFillAfter(true);
            falling.setInterpolator(new AccelerateInterpolator(2.f));
            //new DecelerateInterpolator(2);
            //movingBob.setRepeatCount(-1);
            findViewById(R.id.bobImage).startAnimation(falling);
            //bobImage.setX(bobImage.getX() + 30);)
        }
        else
        {
            clickText.setText("Extra click.");
            //fallingDown = false;
        }

        //  Below just has to be there for some reason:
        return true;
    }




}
