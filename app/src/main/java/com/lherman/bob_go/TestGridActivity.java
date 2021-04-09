package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

public class TestGridActivity extends AppCompatActivity {

    //  This class could be helpful for finding out if the game has problems on phones with larger screen sizes than the one I own
    //  (Samsung Galaxy S6).
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_grid);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        TextView screenSizeText = findViewById(R.id.screenSizeText);
        screenSizeText.setText("The width of your screen is " + width + " density pixels, and the height of your screen is "
                + height + " density pixels.");

        ImageView[][] theSquares = new ImageView[12][6];

        //  The number in the left brackets is the x and the right is the y,
        //  so like "theSquares[0][3]" is all the way to the left, but three squares down.
        theSquares[0][0] = findViewById(R.id.square0x0);
        theSquares[0][1] = findViewById(R.id.square0x1);
        theSquares[0][2] = findViewById(R.id.square0x2);
        theSquares[0][3] = findViewById(R.id.square0x3);
        theSquares[0][4] = findViewById(R.id.square0x4);
        theSquares[0][5] = findViewById(R.id.square0x5);

        theSquares[1][0] = findViewById(R.id.square1x0);
        theSquares[1][1] = findViewById(R.id.square1x1);
        theSquares[1][2] = findViewById(R.id.square1x2);
        theSquares[1][3] = findViewById(R.id.square1x3);
        theSquares[1][4] = findViewById(R.id.square1x4);
        theSquares[1][5] = findViewById(R.id.square1x5);

        theSquares[2][0] = findViewById(R.id.square2x0);
        theSquares[2][1] = findViewById(R.id.square2x1);
        theSquares[2][2] = findViewById(R.id.square2x2);
        theSquares[2][3] = findViewById(R.id.square2x3);
        theSquares[2][4] = findViewById(R.id.square2x4);
        theSquares[2][5] = findViewById(R.id.square2x5);

        theSquares[3][0] = findViewById(R.id.square3x0);
        theSquares[3][1] = findViewById(R.id.square3x1);
        theSquares[3][2] = findViewById(R.id.square3x2);
        theSquares[3][3] = findViewById(R.id.square3x3);
        theSquares[3][4] = findViewById(R.id.square3x4);
        theSquares[3][5] = findViewById(R.id.square3x5);

        theSquares[4][0] = findViewById(R.id.square4x0);
        theSquares[4][1] = findViewById(R.id.square4x1);
        theSquares[4][2] = findViewById(R.id.square4x2);
        theSquares[4][3] = findViewById(R.id.square4x3);
        theSquares[4][4] = findViewById(R.id.square4x4);
        theSquares[4][5] = findViewById(R.id.square4x5);

        theSquares[5][0] = findViewById(R.id.square5x0);
        theSquares[5][1] = findViewById(R.id.square5x1);
        theSquares[5][2] = findViewById(R.id.square5x2);
        theSquares[5][3] = findViewById(R.id.square5x3);
        theSquares[5][4] = findViewById(R.id.square5x4);
        theSquares[5][5] = findViewById(R.id.square5x5);

        theSquares[6][0] = findViewById(R.id.square6x0);
        theSquares[6][1] = findViewById(R.id.square6x1);
        theSquares[6][2] = findViewById(R.id.square6x2);
        theSquares[6][3] = findViewById(R.id.square6x3);
        theSquares[6][4] = findViewById(R.id.square6x4);
        theSquares[6][5] = findViewById(R.id.square6x5);

        theSquares[7][0] = findViewById(R.id.square7x0);
        theSquares[7][1] = findViewById(R.id.square7x1);
        theSquares[7][2] = findViewById(R.id.square7x2);
        theSquares[7][3] = findViewById(R.id.square7x3);
        theSquares[7][4] = findViewById(R.id.square7x4);
        theSquares[7][5] = findViewById(R.id.square7x5);

        theSquares[8][0] = findViewById(R.id.square8x0);
        theSquares[8][1] = findViewById(R.id.square8x1);
        theSquares[8][2] = findViewById(R.id.square8x2);
        theSquares[8][3] = findViewById(R.id.square8x3);
        theSquares[8][4] = findViewById(R.id.square8x4);
        theSquares[8][5] = findViewById(R.id.square8x5);

        theSquares[9][0] = findViewById(R.id.square9x0);
        theSquares[9][1] = findViewById(R.id.square9x1);
        theSquares[9][2] = findViewById(R.id.square9x2);
        theSquares[9][3] = findViewById(R.id.square9x3);
        theSquares[9][4] = findViewById(R.id.square9x4);
        theSquares[9][5] = findViewById(R.id.square9x5);

        theSquares[10][0] = findViewById(R.id.square10x0);
        theSquares[10][1] = findViewById(R.id.square10x1);
        theSquares[10][2] = findViewById(R.id.square10x2);
        theSquares[10][3] = findViewById(R.id.square10x3);
        theSquares[10][4] = findViewById(R.id.square10x4);
        theSquares[10][5] = findViewById(R.id.square10x5);

        theSquares[11][0] = findViewById(R.id.square11x0);
        theSquares[11][1] = findViewById(R.id.square11x1);
        theSquares[11][2] = findViewById(R.id.square11x2);
        theSquares[11][3] = findViewById(R.id.square11x3);
        theSquares[11][4] = findViewById(R.id.square11x4);
        theSquares[11][5] = findViewById(R.id.square11x5);

        for (int index=0; index<theSquares.length; index++)
        {
            for (int index2=0; index2<theSquares[index].length; index2++)
            {
                theSquares[index][index2].getLayoutParams().height = height / 7;
                theSquares[index][index2].getLayoutParams().width = width / 12;
                theSquares[index][index2].setX(index*width/12);
                theSquares[index][index2].setY((height / 14) + (index2 * height / 7));
            }
        }

    }
}