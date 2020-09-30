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

        ImageView iv1 = findViewById(R.id.iv1);
        iv1.setX(0);
        iv1.setY(0);

        ImageView iv2 = findViewById(R.id.iv2);
        iv2.setX(width/12);
        iv2.setY(height/7);
    }
}