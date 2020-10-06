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


        //  Below just has to be there for some reason:
        return true;
    }




}
