package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class HighScoresActivity extends AppCompatActivity
{

    private Handler handler2 = new Handler();
    private Timer timer2 = new Timer();
    private float bobX2 = 0;
    private float bobY2 = 0;
    private ImageView bobHighScore;

    private ImageView maneuverSquare1;
    private boolean jumpingNow2 = false;
    private boolean fallingNow2 = false;
    private boolean onTopOfSquare2 = false;
    private int height2 = 0;
    private int width2 = 0;
    private float startHeight2 = 0;
    TextView theText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width2 = size.x;
        height2 = size.y;
        //bobY2 = 5*height2/7;
        bobY2 = 11*height2/14;

        maneuverSquare1 = findViewById(R.id.manuverSquare1);

        maneuverSquare1.getLayoutParams().height = height2 / 7;
        maneuverSquare1.getLayoutParams().width = width2 / 12;
        //squares[index][index2].setX(index*width/12);
        maneuverSquare1.setX(6*width2/12);
        maneuverSquare1.setY((height2 / 14) + (4 * height2 / 7));
        //maneuverSquare1.setY((height2 / 14) + (5 * height2 / 7));


        bobHighScore = findViewById(R.id.bobHighScore);
        bobHighScore.setX(bobX2);
        bobHighScore.setY(bobY2);
        bobHighScore.getLayoutParams().height = height2/7;
        bobHighScore.getLayoutParams().width = width2/12;

        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                handler2.post(new Runnable() {
                    @Override
                    public void run() {
                        movementStuff();
                    }
                });
            }
            //},0, 40);
        },0, 35);
    }


    public void movementStuff()
    {
        //  14 timer calls per one grid square crossing, 12*14=166
        //bobX2 = bobX2 + width2/166;
        //bobHighScore.setX(bobX2);
        //  Moving the grid instead of Bob:
        //maneuverSquare1 = maneuverSquare1 + width2/166;

        //  BobX = 0, width = like 55dp or whatever,

        //imageView.setImageResource(R.drawable.ic_left_arrow_blue);
        //maneuverSquare1.setImageResource(R.drawable.squareseventy);
        //  THERE IS NO GET VERSION OF THIS!!
        //if (maneuverSquare1.getImageResource() maneuverSquare1.getX() > (bobHighScore.getX()+bobHighScore.getLayoutParams().width) + (width2/166))




        //if (((maneuverSquare1.getX() > ((bobX2+width2)-(width2/166)))) || ((maneuverSquare1.getY() > bobY2+height2)))


        //if (maneuverSquare1.getX() > ((bobHighScore.getX()+bobHighScore.getLayoutParams().width) + (width2/166)))
        if ((maneuverSquare1.getX()>((bobHighScore.getX()+bobHighScore.getLayoutParams().width)+(width2/166))) || (maneuverSquare1.getY() >= bobHighScore.getY()+bobHighScore.getLayoutParams().height) || (maneuverSquare1.getY()+maneuverSquare1.getLayoutParams().height <= bobHighScore.getY()))
        {
            if ((onTopOfSquare2 == true) && (jumpingNow2 == false) && ((maneuverSquare1.getX()+maneuverSquare1.getLayoutParams().width)+(width2/166) <= bobHighScore.getX()))
            {
                fallingNow2 = true;
                onTopOfSquare2 = false;
            }
            maneuverSquare1.setX(maneuverSquare1.getX() - width2 / 166);
        }
        else
        {
            theText = findViewById(R.id.textViewTest);
            int aNumber = width2/166;
            theText.setText("Bobx=" + bobHighScore.getX() + " Bobw=" + bobHighScore.getLayoutParams().width + " Squarex=" + maneuverSquare1.getX() + " Distancepertimer=" + aNumber + " BobY=" + bobHighScore.getY() + " Bobh=" + bobHighScore.getLayoutParams().height + " Squarey=" + maneuverSquare1.getY() + " Squareh=" + maneuverSquare1.getLayoutParams().height);
        }

        //  BTS Idol :)
        //System.out.println("Woo hoo!");
        if (jumpingNow2 == true)
        {
            //  Same proportion for y-direction, 7*14=98
            bobY2 = bobY2 - height2/98;
            bobHighScore.setY(bobY2);


            //if (bobY2 <= 7*height2/14)
            //  Increasing jump height from 2 grid spaces to 2.5:
            //if (bobY2 <= 6*height2/14)
            //  Or if Bob collides with a Square:
            //if ((bobY2 <= 6*height2/14) || ((maneuverSquare1.getY()+maneuverSquare1.getLayoutParams().height+height2/98 >= bobHighScore.getY()) && (maneuverSquare1.getY()+height2/98 <= bobHighScore.getY()+bobHighScore.getLayoutParams().height) && (maneuverSquare1.getX() <= bobHighScore.getX()+bobHighScore.getLayoutParams().width) && (maneuverSquare1.getX()+maneuverSquare1.getLayoutParams().width >= bobHighScore.getX()) ))
            //  Or if Bob starts his jump ON a Square:
            if ((bobHighScore.getY() <= startHeight2-(5*height2/14)) || ((maneuverSquare1.getY()+maneuverSquare1.getLayoutParams().height+height2/98 >= bobHighScore.getY()) && (maneuverSquare1.getY()+height2/98 <= bobHighScore.getY()+bobHighScore.getLayoutParams().height) && (maneuverSquare1.getX() <= bobHighScore.getX()+bobHighScore.getLayoutParams().width) && (maneuverSquare1.getX()+maneuverSquare1.getLayoutParams().width >= bobHighScore.getX()) ))
            {
                jumpingNow2 = false;
                fallingNow2 = true;
            }
        }
        if (fallingNow2 == true)
        {
            //  Same proportion for y-direction, 7*14=98
            bobY2 = bobY2 + height2/98;
            bobHighScore.setY(bobY2);

            //if (bobY2 >= 11*height2/14)
            //  Or if he lands on a square:
            if ((bobHighScore.getY() >= 11*height2/14) || ((maneuverSquare1.getY()-height2/98 <= bobHighScore.getY()+bobHighScore.getLayoutParams().height) && (maneuverSquare1.getY()+maneuverSquare1.getLayoutParams().height-height2/98 >= bobHighScore.getY()) && (maneuverSquare1.getX() <= bobHighScore.getX()+bobHighScore.getLayoutParams().width) && (maneuverSquare1.getX()+maneuverSquare1.getLayoutParams().width >= bobHighScore.getX()) ))
            {
                fallingNow2 = false;
                if (bobY2 < 11*height2/14)
                {
                    onTopOfSquare2 = true;
                }
            }

            /*if ((bobHighScore.getY() < 11*height2/14) || (maneuverSquare1.getY()-height2/98 > bobHighScore.getY()+bobHighScore.getLayoutParams().height) || (maneuverSquare1.getX() > bobHighScore.getX()+bobHighScore.getLayoutParams().width+(width2/166)) || (maneuverSquare1.getX()+maneuverSquare1.getLayoutParams().width < bobHighScore.getX()+(width2/166)) )
            {
                bobY2 = bobY2 + height2/98;
                bobHighScore.setY(bobY2);
            }
            else
            {
                fallingNow2 = false;
            }*/

        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        //System.out.println("BobY = " + bobY2 + " Bob Height = " + height2);

        //if (bobY > 151)
        if (jumpingNow2==false && fallingNow2==false)
        {
            jumpingNow2 = true;
            //startHeight2 = bobHighScore.getY()+bobHighScore.getLayoutParams().height;
            startHeight2 = bobHighScore.getY();
        }




        //theText.setText(theText.getText() + stringWidth + "," + stringHeight + "," + xString + "," + squareRight.getY() + "," + squareRight.getWidth() + "," + squareRight.getHeight());
        //theText.setText("Some Text");

        //  Below just has to be there for some reason:
        return true;
    }




}