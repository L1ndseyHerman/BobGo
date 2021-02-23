package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TestingActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        //  Goes to TestGridActivity:
        Button gridButton = findViewById(R.id.testGridButton);
        gridButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), TestGridActivity.class);
                startActivity(startIntent);
            }
        });

        //  Goes to TestGridActivity:
        Button levelOneExtrasButton = findViewById(R.id.testLevelOneExtrasButton);
        levelOneExtrasButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), TestLevelOneExtras.class);
                startActivity(startIntent);
            }
        });

    }

}