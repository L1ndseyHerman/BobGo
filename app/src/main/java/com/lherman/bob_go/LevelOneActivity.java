package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class LevelOneActivity extends AppCompatActivity
{
    private ImageView[][] daGrid = new ImageView[12][6];
    private ImageView bob;
    private int xLevelMove;
    private int yBobJump;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private boolean jumpingNow = false;
    private boolean fallingNow = false;
    int width;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        //  14 timer calls per one grid square crossing, 12*14=168
        xLevelMove = width/168;
        //  Same proportion for y-direction, 7*14=98
        yBobJump = height/98;

        //  The number in the left brackets is the x and the right is the y,
        //  so like "theSquares[0][3]" is all the way to the left, but three squares down.
        daGrid[0][0] = findViewById(R.id.grid0x0);
        daGrid[0][1] = findViewById(R.id.grid0x1);
        daGrid[0][2] = findViewById(R.id.grid0x2);
        daGrid[0][3] = findViewById(R.id.grid0x3);
        daGrid[0][4] = findViewById(R.id.grid0x4);
        daGrid[0][5] = findViewById(R.id.grid0x5);

        daGrid[1][0] = findViewById(R.id.grid1x0);
        daGrid[1][1] = findViewById(R.id.grid1x1);
        daGrid[1][2] = findViewById(R.id.grid1x2);
        daGrid[1][3] = findViewById(R.id.grid1x3);
        daGrid[1][4] = findViewById(R.id.grid1x4);
        daGrid[1][5] = findViewById(R.id.grid1x5);

        daGrid[2][0] = findViewById(R.id.grid2x0);
        daGrid[2][1] = findViewById(R.id.grid2x1);
        daGrid[2][2] = findViewById(R.id.grid2x2);
        daGrid[2][3] = findViewById(R.id.grid2x3);
        daGrid[2][4] = findViewById(R.id.grid2x4);
        daGrid[2][5] = findViewById(R.id.grid2x5);

        daGrid[3][0] = findViewById(R.id.grid3x0);
        daGrid[3][1] = findViewById(R.id.grid3x1);
        daGrid[3][2] = findViewById(R.id.grid3x2);
        daGrid[3][3] = findViewById(R.id.grid3x3);
        daGrid[3][4] = findViewById(R.id.grid3x4);
        daGrid[3][5] = findViewById(R.id.grid3x5);

        daGrid[4][0] = findViewById(R.id.grid4x0);
        daGrid[4][1] = findViewById(R.id.grid4x1);
        daGrid[4][2] = findViewById(R.id.grid4x2);
        daGrid[4][3] = findViewById(R.id.grid4x3);
        daGrid[4][4] = findViewById(R.id.grid4x4);
        daGrid[4][5] = findViewById(R.id.grid4x5);

        daGrid[5][0] = findViewById(R.id.grid5x0);
        daGrid[5][1] = findViewById(R.id.grid5x1);
        daGrid[5][2] = findViewById(R.id.grid5x2);
        daGrid[5][3] = findViewById(R.id.grid5x3);
        daGrid[5][4] = findViewById(R.id.grid5x4);
        daGrid[5][5] = findViewById(R.id.grid5x5);

        daGrid[6][0] = findViewById(R.id.grid6x0);
        daGrid[6][1] = findViewById(R.id.grid6x1);
        daGrid[6][2] = findViewById(R.id.grid6x2);
        daGrid[6][3] = findViewById(R.id.grid6x3);
        daGrid[6][4] = findViewById(R.id.grid6x4);
        daGrid[6][5] = findViewById(R.id.grid6x5);

        daGrid[7][0] = findViewById(R.id.grid7x0);
        daGrid[7][1] = findViewById(R.id.grid7x1);
        daGrid[7][2] = findViewById(R.id.grid7x2);
        daGrid[7][3] = findViewById(R.id.grid7x3);
        daGrid[7][4] = findViewById(R.id.grid7x4);
        daGrid[7][5] = findViewById(R.id.grid7x5);

        daGrid[8][0] = findViewById(R.id.grid8x0);
        daGrid[8][1] = findViewById(R.id.grid8x1);
        daGrid[8][2] = findViewById(R.id.grid8x2);
        daGrid[8][3] = findViewById(R.id.grid8x3);
        daGrid[8][4] = findViewById(R.id.grid8x4);
        daGrid[8][5] = findViewById(R.id.grid8x5);

        daGrid[9][0] = findViewById(R.id.grid9x0);
        daGrid[9][1] = findViewById(R.id.grid9x1);
        daGrid[9][2] = findViewById(R.id.grid9x2);
        daGrid[9][3] = findViewById(R.id.grid9x3);
        daGrid[9][4] = findViewById(R.id.grid9x4);
        daGrid[9][5] = findViewById(R.id.grid9x5);

        daGrid[10][0] = findViewById(R.id.grid10x0);
        daGrid[10][1] = findViewById(R.id.grid10x1);
        daGrid[10][2] = findViewById(R.id.grid10x2);
        daGrid[10][3] = findViewById(R.id.grid10x3);
        daGrid[10][4] = findViewById(R.id.grid10x4);
        daGrid[10][5] = findViewById(R.id.grid10x5);

        daGrid[11][0] = findViewById(R.id.grid11x0);
        daGrid[11][1] = findViewById(R.id.grid11x1);
        daGrid[11][2] = findViewById(R.id.grid11x2);
        daGrid[11][3] = findViewById(R.id.grid11x3);
        daGrid[11][4] = findViewById(R.id.grid11x4);
        daGrid[11][5] = findViewById(R.id.grid11x5);


        for (int index=0; index<daGrid.length; index++)
        {
            for (int index2=0; index2<daGrid[index].length; index2++)
            {
                daGrid[index][index2].getLayoutParams().height = height / 7;
                daGrid[index][index2].getLayoutParams().width = width / 12;
                daGrid[index][index2].setX(index*width/12);
                //daGrid[index][index2].setX((width)+(index*width/12));
                daGrid[index][index2].setY((height / 14) + (index2 * height / 7));
            }
        }


        bob = findViewById(R.id.bob);
        bob.setX(0);
        bob.setY(11*height/14);
        bob.getLayoutParams().height = height/7;
        bob.getLayoutParams().width = width/12;


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        levelMoveStuff();
                    }
                });
            }
            //},0, 20);
        },1000, 35);

    }




    public void levelMoveStuff()
    {
        for (int index=0; index<daGrid.length; index++)
        {
            for (int index2=0; index2<daGrid[index].length; index2++)
            {
                daGrid[index][index2].setX(daGrid[index][index2].getX() - xLevelMove);
            }
        }

        //  Bob needs to redraw even though staying still to be over top of the grid backgrounds.
        //bob.setX(bob.getX());
        //  Never mind, that doesn't work at all! Apparently, the image at the bottom of the Component Tree
        //  is the foreground/forwardmost, so moved bob to bottom, and now he appears on top of grid.



        if (jumpingNow == true)
        {
            //bobY = bobY - height/98;
            bob.setY(bob.getY()-yBobJump);
            //if (bobY > 151)
            //if (bobY < 3*height/7)
            //if (bobY <= 7*height/14)
            if (bob.getY() <= 6*height/14)
            {
                jumpingNow = false;
                fallingNow = true;
            }
        }
        if (fallingNow == true)
        {
            //bobY = bobY + height/98;
            bob.setY(bob.getY()+yBobJump);
            //if (bobY > 5*height/7)
            if (bob.getY() >= 11*height/14)
            {
                fallingNow = false;
            }
        }
    }



    public boolean onTouchEvent(MotionEvent event)
    {

        //if (bobY > 151)
        if (jumpingNow==false && fallingNow==false)
        {
            jumpingNow = true;
        }
        //  Below just has to be there for some reason:
        return true;
    }

}