package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RandomLevelActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[51][6];
    private Hater[] haters = new Hater[22];
    private Button beginButton;
    private RandomGameLogic randomGameLogic;

    private int randomBlankGridSpaceRow = -1;
    private int previousRandomBlankGridSpaceRow = -1;
    private boolean choseRandomBlankGridSpaceRowThisColumn = false;
    private final int[][] haterCoordinates = new int[haters.length][2];
    private boolean columnHasHaterAlready = false;
    private int haterCounter = 0;
    private final int[][] winCircleCoordinates = new int[1][2];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_level);

        randomGameLogic = new RandomGameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        randomGameLogic.setScreenWidth(screenWidth);
        randomGameLogic.setScreenHeight(screenHeight);

        randomGameLogic.setXMoveSpeedScreen(screenWidth /168);

        ImageView bobImage = findViewById(R.id.bobR);
        randomGameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBobR_0);
        randomGameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBobR_1);
        randomGameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBobR_2);
        randomGameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBobR_3);
        randomGameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBobR_1);
        randomGameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBobR_2);
        randomGameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBobR_3);
        randomGameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        randomGameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircleR));
        randomGameLogic.setWinCircleLogic(winCircle, daGrid[winCircleCoordinates[0][0]][winCircleCoordinates[0][1]]);

        ImageView[] gameOverBarsUpTop = new ImageView[4];
        //  Hater image is included, bars move down by 1:
        gameOverBarsUpTop[0] = findViewById(R.id.haterUpTopR);
        gameOverBarsUpTop[1] = findViewById(R.id.haterUpTopBarR_0);
        gameOverBarsUpTop[2] = findViewById(R.id.haterUpTopBarR_1);
        gameOverBarsUpTop[3] = findViewById(R.id.haterUpTopBarR_2);

        randomGameLogic.setGameOverBarsLogic(gameOverBarsUpTop);

        haters = placeEnemies(haters);
        randomGameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButtonR);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                //  No high score for Random mode.

                randomGameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButtonR);
        randomGameLogic.setEndButtonLogic(endButton);
        endButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

    }

    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.gridR_3x5));

        daGrid[4][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x0_B), (ImageView)findViewById(R.id.gridR_4x0_S),
                4, 0);
        daGrid[4][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x1_B), (ImageView)findViewById(R.id.gridR_4x1_S),
                4, 1);
        daGrid[4][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x2_B), (ImageView)findViewById(R.id.gridR_4x2_S),
                4, 2);
        daGrid[4][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x3_B), (ImageView)findViewById(R.id.gridR_4x3_S),
                4, 3);
        daGrid[4][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x4_B), (ImageView)findViewById(R.id.gridR_4x4_S),
                4, 4);
        daGrid[4][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_4x5_B), (ImageView)findViewById(R.id.gridR_4x5_S),
                4, 5);

        daGrid[5][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_5x0_B), (ImageView)findViewById(R.id.gridR_5x0_S),
                5, 0);
        daGrid[5][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_5x1_B), (ImageView)findViewById(R.id.gridR_5x1_S),
                5, 1);
        daGrid[5][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_5x2_B), (ImageView)findViewById(R.id.gridR_5x2_S),
                5, 2);
        daGrid[5][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_5x3_B), (ImageView)findViewById(R.id.gridR_5x3_S),
                5, 3);
        daGrid[5][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_5x4_B), (ImageView)findViewById(R.id.gridR_5x4_S),
                5, 4);
        daGrid[5][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_5x5_B), (ImageView)findViewById(R.id.gridR_5x5_S),
                5, 5);

        daGrid[6][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_6x0_B), (ImageView)findViewById(R.id.gridR_6x0_S),
                6, 0);
        daGrid[6][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_6x1_B), (ImageView)findViewById(R.id.gridR_6x1_S),
                6, 1);
        daGrid[6][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_6x2_B), (ImageView)findViewById(R.id.gridR_6x2_S),
                6, 2);
        daGrid[6][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_6x3_B), (ImageView)findViewById(R.id.gridR_6x3_S),
                6, 3);
        daGrid[6][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_6x4_B), (ImageView)findViewById(R.id.gridR_6x4_S),
                6, 4);
        daGrid[6][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_6x5_B), (ImageView)findViewById(R.id.gridR_6x5_S),
                6, 5);

        daGrid[7][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_7x0_B), (ImageView)findViewById(R.id.gridR_7x0_S),
                7, 0);
        daGrid[7][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_7x1_B), (ImageView)findViewById(R.id.gridR_7x1_S),
                7, 1);
        daGrid[7][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_7x2_B), (ImageView)findViewById(R.id.gridR_7x2_S),
                7, 2);
        daGrid[7][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_7x3_B), (ImageView)findViewById(R.id.gridR_7x3_S),
                7, 3);
        daGrid[7][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_7x4_B), (ImageView)findViewById(R.id.gridR_7x4_S),
                7, 4);
        daGrid[7][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_7x5_B), (ImageView)findViewById(R.id.gridR_7x5_S),
                7, 5);

        daGrid[8][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_8x0_B), (ImageView)findViewById(R.id.gridR_8x0_S),
                8, 0);
        daGrid[8][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_8x1_B), (ImageView)findViewById(R.id.gridR_8x1_S),
                8, 1);
        daGrid[8][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_8x2_B), (ImageView)findViewById(R.id.gridR_8x2_S),
                8, 2);
        daGrid[8][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_8x3_B), (ImageView)findViewById(R.id.gridR_8x3_S),
                8, 3);
        daGrid[8][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_8x4_B), (ImageView)findViewById(R.id.gridR_8x4_S),
                8, 4);
        daGrid[8][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_8x5_B), (ImageView)findViewById(R.id.gridR_8x5_S),
                8, 5);

        daGrid[9][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_9x0_B), (ImageView)findViewById(R.id.gridR_9x0_S),
                9, 0);
        daGrid[9][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_9x1_B), (ImageView)findViewById(R.id.gridR_9x1_S),
                9, 1);
        daGrid[9][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_9x2_B), (ImageView)findViewById(R.id.gridR_9x2_S),
                9, 2);
        daGrid[9][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_9x3_B), (ImageView)findViewById(R.id.gridR_9x3_S),
                9, 3);
        daGrid[9][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_9x4_B), (ImageView)findViewById(R.id.gridR_9x4_S),
                9, 4);
        daGrid[9][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_9x5_B), (ImageView)findViewById(R.id.gridR_9x5_S),
                9, 5);

        daGrid[10][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_10x0_B), (ImageView)findViewById(R.id.gridR_10x0_S),
                10, 0);
        daGrid[10][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_10x1_B), (ImageView)findViewById(R.id.gridR_10x1_S),
                10, 1);
        daGrid[10][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_10x2_B), (ImageView)findViewById(R.id.gridR_10x2_S),
                10, 2);
        daGrid[10][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_10x3_B), (ImageView)findViewById(R.id.gridR_10x3_S),
                10, 3);
        daGrid[10][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_10x4_B), (ImageView)findViewById(R.id.gridR_10x4_S),
                10, 4);
        daGrid[10][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_10x5_B), (ImageView)findViewById(R.id.gridR_10x5_S),
                10, 5);

        daGrid[11][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_11x0_B), (ImageView)findViewById(R.id.gridR_11x0_S),
                11, 0);
        daGrid[11][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_11x1_B), (ImageView)findViewById(R.id.gridR_11x1_S),
                11, 1);
        daGrid[11][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_11x2_B), (ImageView)findViewById(R.id.gridR_11x2_S),
                11, 2);
        daGrid[11][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_11x3_B), (ImageView)findViewById(R.id.gridR_11x3_S),
                11, 3);
        daGrid[11][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_11x4_B), (ImageView)findViewById(R.id.gridR_11x4_S),
                11, 4);
        daGrid[11][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_11x5_B), (ImageView)findViewById(R.id.gridR_11x5_S),
                11, 5);

        daGrid[12][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_12x0_B), (ImageView)findViewById(R.id.gridR_12x0_S),
                12, 0);
        daGrid[12][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_12x1_B), (ImageView)findViewById(R.id.gridR_12x1_S),
                12, 1);
        daGrid[12][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_12x2_B), (ImageView)findViewById(R.id.gridR_12x2_S),
                12, 2);
        daGrid[12][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_12x3_B), (ImageView)findViewById(R.id.gridR_12x3_S),
                12, 3);
        daGrid[12][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_12x4_B), (ImageView)findViewById(R.id.gridR_12x4_S),
                12, 4);
        daGrid[12][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_12x5_B), (ImageView)findViewById(R.id.gridR_12x5_S),
                12, 5);

        daGrid[13][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_13x0_B), (ImageView)findViewById(R.id.gridR_13x0_S),
                13, 0);
        daGrid[13][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_13x1_B), (ImageView)findViewById(R.id.gridR_13x1_S),
                13, 1);
        daGrid[13][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_13x2_B), (ImageView)findViewById(R.id.gridR_13x2_S),
                13, 2);
        daGrid[13][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_13x3_B), (ImageView)findViewById(R.id.gridR_13x3_S),
                13, 3);
        daGrid[13][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_13x4_B), (ImageView)findViewById(R.id.gridR_13x4_S),
                13, 4);
        daGrid[13][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_13x5_B), (ImageView)findViewById(R.id.gridR_13x5_S),
                13, 5);

        daGrid[14][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_14x0_B), (ImageView)findViewById(R.id.gridR_14x0_S),
                14, 0);
        daGrid[14][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_14x1_B), (ImageView)findViewById(R.id.gridR_14x1_S),
                14, 1);
        daGrid[14][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_14x2_B), (ImageView)findViewById(R.id.gridR_14x2_S),
                14, 2);
        daGrid[14][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_14x3_B), (ImageView)findViewById(R.id.gridR_14x3_S),
                14, 3);
        daGrid[14][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_14x4_B), (ImageView)findViewById(R.id.gridR_14x4_S),
                14, 4);
        daGrid[14][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_14x5_B), (ImageView)findViewById(R.id.gridR_14x5_S),
                14, 5);

        daGrid[15][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_15x0_B), (ImageView)findViewById(R.id.gridR_15x0_S),
                15, 0);
        daGrid[15][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_15x1_B), (ImageView)findViewById(R.id.gridR_15x1_S),
                15, 1);
        daGrid[15][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_15x2_B), (ImageView)findViewById(R.id.gridR_15x2_S),
                15, 2);
        daGrid[15][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_15x3_B), (ImageView)findViewById(R.id.gridR_15x3_S),
                15, 3);
        daGrid[15][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_15x4_B), (ImageView)findViewById(R.id.gridR_15x4_S),
                15, 4);
        daGrid[15][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_15x5_B), (ImageView)findViewById(R.id.gridR_15x5_S),
                15, 5);

        daGrid[16][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_16x0_B), (ImageView)findViewById(R.id.gridR_16x0_S),
                16, 0);
        daGrid[16][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_16x1_B), (ImageView)findViewById(R.id.gridR_16x1_S),
                16, 1);
        daGrid[16][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_16x2_B), (ImageView)findViewById(R.id.gridR_16x2_S),
                16, 2);
        daGrid[16][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_16x3_B), (ImageView)findViewById(R.id.gridR_16x3_S),
                16, 3);
        daGrid[16][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_16x4_B), (ImageView)findViewById(R.id.gridR_16x4_S),
                16, 4);
        daGrid[16][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_16x5_B), (ImageView)findViewById(R.id.gridR_16x5_S),
                16, 5);

        daGrid[17][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_17x0_B), (ImageView)findViewById(R.id.gridR_17x0_S),
                17, 0);
        daGrid[17][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_17x1_B), (ImageView)findViewById(R.id.gridR_17x1_S),
                17, 1);
        daGrid[17][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_17x2_B), (ImageView)findViewById(R.id.gridR_17x2_S),
                17, 2);
        daGrid[17][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_17x3_B), (ImageView)findViewById(R.id.gridR_17x3_S),
                17, 3);
        daGrid[17][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_17x4_B), (ImageView)findViewById(R.id.gridR_17x4_S),
                17, 4);
        daGrid[17][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_17x5_B), (ImageView)findViewById(R.id.gridR_17x5_S),
                17, 5);

        daGrid[18][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_18x0_B), (ImageView)findViewById(R.id.gridR_18x0_S),
                18, 0);
        daGrid[18][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_18x1_B), (ImageView)findViewById(R.id.gridR_18x1_S),
                18, 1);
        daGrid[18][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_18x2_B), (ImageView)findViewById(R.id.gridR_18x2_S),
                18, 2);
        daGrid[18][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_18x3_B), (ImageView)findViewById(R.id.gridR_18x3_S),
                18, 3);
        daGrid[18][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_18x4_B), (ImageView)findViewById(R.id.gridR_18x4_S),
                18, 4);
        daGrid[18][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_18x5_B), (ImageView)findViewById(R.id.gridR_18x5_S),
                18, 5);

        daGrid[19][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_19x0_B), (ImageView)findViewById(R.id.gridR_19x0_S),
                19, 0);
        daGrid[19][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_19x1_B), (ImageView)findViewById(R.id.gridR_19x1_S),
                19, 1);
        daGrid[19][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_19x2_B), (ImageView)findViewById(R.id.gridR_19x2_S),
                19, 2);
        daGrid[19][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_19x3_B), (ImageView)findViewById(R.id.gridR_19x3_S),
                19, 3);
        daGrid[19][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_19x4_B), (ImageView)findViewById(R.id.gridR_19x4_S),
                19, 4);
        daGrid[19][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_19x5_B), (ImageView)findViewById(R.id.gridR_19x5_S),
                19, 5);

        daGrid[20][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_20x0_B), (ImageView)findViewById(R.id.gridR_20x0_S),
                20, 0);
        daGrid[20][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_20x1_B), (ImageView)findViewById(R.id.gridR_20x1_S),
                20, 1);
        daGrid[20][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_20x2_B), (ImageView)findViewById(R.id.gridR_20x2_S),
                20, 2);
        daGrid[20][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_20x3_B), (ImageView)findViewById(R.id.gridR_20x3_S),
                20, 3);
        daGrid[20][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_20x4_B), (ImageView)findViewById(R.id.gridR_20x4_S),
                20, 4);
        daGrid[20][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_20x5_B), (ImageView)findViewById(R.id.gridR_20x5_S),
                20, 5);

        daGrid[21][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_21x0_B), (ImageView)findViewById(R.id.gridR_21x0_S),
                21, 0);
        daGrid[21][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_21x1_B), (ImageView)findViewById(R.id.gridR_21x1_S),
                21, 1);
        daGrid[21][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_21x2_B), (ImageView)findViewById(R.id.gridR_21x2_S),
                21, 2);
        daGrid[21][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_21x3_B), (ImageView)findViewById(R.id.gridR_21x3_S),
                21, 3);
        daGrid[21][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_21x4_B), (ImageView)findViewById(R.id.gridR_21x4_S),
                21, 4);
        daGrid[21][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_21x5_B), (ImageView)findViewById(R.id.gridR_21x5_S),
                21, 5);

        daGrid[22][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_22x0_B), (ImageView)findViewById(R.id.gridR_22x0_S),
                22, 0);
        daGrid[22][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_22x1_B), (ImageView)findViewById(R.id.gridR_22x1_S),
                22, 1);
        daGrid[22][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_22x2_B), (ImageView)findViewById(R.id.gridR_22x2_S),
                22, 2);
        daGrid[22][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_22x3_B), (ImageView)findViewById(R.id.gridR_22x3_S),
                22, 3);
        daGrid[22][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_22x4_B), (ImageView)findViewById(R.id.gridR_22x4_S),
                22, 4);
        daGrid[22][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_22x5_B), (ImageView)findViewById(R.id.gridR_22x5_S),
                22, 5);

        daGrid[23][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_23x0_B), (ImageView)findViewById(R.id.gridR_23x0_S),
                23, 0);
        daGrid[23][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_23x1_B), (ImageView)findViewById(R.id.gridR_23x1_S),
                23, 1);
        daGrid[23][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_23x2_B), (ImageView)findViewById(R.id.gridR_23x2_S),
                23, 2);
        daGrid[23][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_23x3_B), (ImageView)findViewById(R.id.gridR_23x3_S),
                23, 3);
        daGrid[23][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_23x4_B), (ImageView)findViewById(R.id.gridR_23x4_S),
                23, 4);
        daGrid[23][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_23x5_B), (ImageView)findViewById(R.id.gridR_23x5_S),
                23, 5);

        daGrid[24][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_24x0_B), (ImageView)findViewById(R.id.gridR_24x0_S),
                24, 0);
        daGrid[24][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_24x1_B), (ImageView)findViewById(R.id.gridR_24x1_S),
                24, 1);
        daGrid[24][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_24x2_B), (ImageView)findViewById(R.id.gridR_24x2_S),
                24, 2);
        daGrid[24][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_24x3_B), (ImageView)findViewById(R.id.gridR_24x3_S),
                24, 3);
        daGrid[24][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_24x4_B), (ImageView)findViewById(R.id.gridR_24x4_S),
                24, 4);
        daGrid[24][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_24x5_B), (ImageView)findViewById(R.id.gridR_24x5_S),
                24, 5);

        daGrid[25][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_25x0_B), (ImageView)findViewById(R.id.gridR_25x0_S),
                25, 0);
        daGrid[25][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_25x1_B), (ImageView)findViewById(R.id.gridR_25x1_S),
                25, 1);
        daGrid[25][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_25x2_B), (ImageView)findViewById(R.id.gridR_25x2_S),
                25, 2);
        daGrid[25][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_25x3_B), (ImageView)findViewById(R.id.gridR_25x3_S),
                25, 3);
        daGrid[25][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_25x4_B), (ImageView)findViewById(R.id.gridR_25x4_S),
                25, 4);
        daGrid[25][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_25x5_B), (ImageView)findViewById(R.id.gridR_25x5_S),
                25, 5);

        daGrid[26][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_26x0_B), (ImageView)findViewById(R.id.gridR_26x0_S),
                26, 0);
        daGrid[26][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_26x1_B), (ImageView)findViewById(R.id.gridR_26x1_S),
                26, 1);
        daGrid[26][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_26x2_B), (ImageView)findViewById(R.id.gridR_26x2_S),
                26, 2);
        daGrid[26][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_26x3_B), (ImageView)findViewById(R.id.gridR_26x3_S),
                26, 3);
        daGrid[26][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_26x4_B), (ImageView)findViewById(R.id.gridR_26x4_S),
                26, 4);
        daGrid[26][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_26x5_B), (ImageView)findViewById(R.id.gridR_26x5_S),
                26, 5);

        daGrid[27][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_27x0_B), (ImageView)findViewById(R.id.gridR_27x0_S),
                27, 0);
        daGrid[27][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_27x1_B), (ImageView)findViewById(R.id.gridR_27x1_S),
                27, 1);
        daGrid[27][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_27x2_B), (ImageView)findViewById(R.id.gridR_27x2_S),
                27, 2);
        daGrid[27][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_27x3_B), (ImageView)findViewById(R.id.gridR_27x3_S),
                27, 3);
        daGrid[27][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_27x4_B), (ImageView)findViewById(R.id.gridR_27x4_S),
                27, 4);
        daGrid[27][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_27x5_B), (ImageView)findViewById(R.id.gridR_27x5_S),
                27, 5);

        daGrid[28][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_28x0_B), (ImageView)findViewById(R.id.gridR_28x0_S),
                28, 0);
        daGrid[28][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_28x1_B), (ImageView)findViewById(R.id.gridR_28x1_S),
                28, 1);
        daGrid[28][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_28x2_B), (ImageView)findViewById(R.id.gridR_28x2_S),
                28, 2);
        daGrid[28][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_28x3_B), (ImageView)findViewById(R.id.gridR_28x3_S),
                28, 3);
        daGrid[28][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_28x4_B), (ImageView)findViewById(R.id.gridR_28x4_S),
                28, 4);
        daGrid[28][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_28x5_B), (ImageView)findViewById(R.id.gridR_28x5_S),
                28, 5);

        daGrid[29][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_29x0_B), (ImageView)findViewById(R.id.gridR_29x0_S),
                29, 0);
        daGrid[29][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_29x1_B), (ImageView)findViewById(R.id.gridR_29x1_S),
                29, 1);
        daGrid[29][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_29x2_B), (ImageView)findViewById(R.id.gridR_29x2_S),
                29, 2);
        daGrid[29][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_29x3_B), (ImageView)findViewById(R.id.gridR_29x3_S),
                29, 3);
        daGrid[29][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_29x4_B), (ImageView)findViewById(R.id.gridR_29x4_S),
                29, 4);
        daGrid[29][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_29x5_B), (ImageView)findViewById(R.id.gridR_29x5_S),
                29, 5);

        daGrid[30][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_30x0_B), (ImageView)findViewById(R.id.gridR_30x0_S),
                30, 0);
        daGrid[30][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_30x1_B), (ImageView)findViewById(R.id.gridR_30x1_S),
                30, 1);
        daGrid[30][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_30x2_B), (ImageView)findViewById(R.id.gridR_30x2_S),
                30, 2);
        daGrid[30][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_30x3_B), (ImageView)findViewById(R.id.gridR_30x3_S),
                30, 3);
        daGrid[30][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_30x4_B), (ImageView)findViewById(R.id.gridR_30x4_S),
                30, 4);
        daGrid[30][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_30x5_B), (ImageView)findViewById(R.id.gridR_30x5_S),
                30, 5);

        daGrid[31][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_31x0_B), (ImageView)findViewById(R.id.gridR_31x0_S),
                31, 0);
        daGrid[31][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_31x1_B), (ImageView)findViewById(R.id.gridR_31x1_S),
                31, 1);
        daGrid[31][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_31x2_B), (ImageView)findViewById(R.id.gridR_31x2_S),
                31, 2);
        daGrid[31][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_31x3_B), (ImageView)findViewById(R.id.gridR_31x3_S),
                31, 3);
        daGrid[31][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_31x4_B), (ImageView)findViewById(R.id.gridR_31x4_S),
                31, 4);
        daGrid[31][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_31x5_B), (ImageView)findViewById(R.id.gridR_31x5_S),
                31, 5);

        daGrid[32][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_32x0_B), (ImageView)findViewById(R.id.gridR_32x0_S),
                32, 0);
        daGrid[32][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_32x1_B), (ImageView)findViewById(R.id.gridR_32x1_S),
                32, 1);
        daGrid[32][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_32x2_B), (ImageView)findViewById(R.id.gridR_32x2_S),
                32, 2);
        daGrid[32][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_32x3_B), (ImageView)findViewById(R.id.gridR_32x3_S),
                32, 3);
        daGrid[32][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_32x4_B), (ImageView)findViewById(R.id.gridR_32x4_S),
                32, 4);
        daGrid[32][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_32x5_B), (ImageView)findViewById(R.id.gridR_32x5_S),
                32, 5);

        daGrid[33][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_33x0_B), (ImageView)findViewById(R.id.gridR_33x0_S),
                33, 0);
        daGrid[33][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_33x1_B), (ImageView)findViewById(R.id.gridR_33x1_S),
                33, 1);
        daGrid[33][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_33x2_B), (ImageView)findViewById(R.id.gridR_33x2_S),
                33, 2);
        daGrid[33][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_33x3_B), (ImageView)findViewById(R.id.gridR_33x3_S),
                33, 3);
        daGrid[33][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_33x4_B), (ImageView)findViewById(R.id.gridR_33x4_S),
                33, 4);
        daGrid[33][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_33x5_B), (ImageView)findViewById(R.id.gridR_33x5_S),
                33, 5);

        daGrid[34][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_34x0_B), (ImageView)findViewById(R.id.gridR_34x0_S),
                34, 0);
        daGrid[34][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_34x1_B), (ImageView)findViewById(R.id.gridR_34x1_S),
                34, 1);
        daGrid[34][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_34x2_B), (ImageView)findViewById(R.id.gridR_34x2_S),
                34, 2);
        daGrid[34][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_34x3_B), (ImageView)findViewById(R.id.gridR_34x3_S),
                34, 3);
        daGrid[34][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_34x4_B), (ImageView)findViewById(R.id.gridR_34x4_S),
                34, 4);
        daGrid[34][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_34x5_B), (ImageView)findViewById(R.id.gridR_34x5_S),
                34, 5);

        daGrid[35][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_35x0_B), (ImageView)findViewById(R.id.gridR_35x0_S),
                35, 0);
        daGrid[35][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_35x1_B), (ImageView)findViewById(R.id.gridR_35x1_S),
                35, 1);
        daGrid[35][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_35x2_B), (ImageView)findViewById(R.id.gridR_35x2_S),
                35, 2);
        daGrid[35][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_35x3_B), (ImageView)findViewById(R.id.gridR_35x3_S),
                35, 3);
        daGrid[35][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_35x4_B), (ImageView)findViewById(R.id.gridR_35x4_S),
                35, 4);
        daGrid[35][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_35x5_B), (ImageView)findViewById(R.id.gridR_35x5_S),
                35, 5);

        daGrid[36][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_36x0_B), (ImageView)findViewById(R.id.gridR_36x0_S),
                36, 0);
        daGrid[36][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_36x1_B), (ImageView)findViewById(R.id.gridR_36x1_S),
                36, 1);
        daGrid[36][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_36x2_B), (ImageView)findViewById(R.id.gridR_36x2_S),
                36, 2);
        daGrid[36][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_36x3_B), (ImageView)findViewById(R.id.gridR_36x3_S),
                36, 3);
        daGrid[36][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_36x4_B), (ImageView)findViewById(R.id.gridR_36x4_S),
                36, 4);
        daGrid[36][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_36x5_B), (ImageView)findViewById(R.id.gridR_36x5_S),
                36, 5);

        daGrid[37][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_37x0_B), (ImageView)findViewById(R.id.gridR_37x0_S),
                37, 0);
        daGrid[37][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_37x1_B), (ImageView)findViewById(R.id.gridR_37x1_S),
                37, 1);
        daGrid[37][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_37x2_B), (ImageView)findViewById(R.id.gridR_37x2_S),
                37, 2);
        daGrid[37][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_37x3_B), (ImageView)findViewById(R.id.gridR_37x3_S),
                37, 3);
        daGrid[37][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_37x4_B), (ImageView)findViewById(R.id.gridR_37x4_S),
                37, 4);
        daGrid[37][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_37x5_B), (ImageView)findViewById(R.id.gridR_37x5_S),
                37, 5);

        daGrid[38][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_38x0_B), (ImageView)findViewById(R.id.gridR_38x0_S),
                38, 0);
        daGrid[38][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_38x1_B), (ImageView)findViewById(R.id.gridR_38x1_S),
                38, 1);
        daGrid[38][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_38x2_B), (ImageView)findViewById(R.id.gridR_38x2_S),
                38, 2);
        daGrid[38][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_38x3_B), (ImageView)findViewById(R.id.gridR_38x3_S),
                38, 3);
        daGrid[38][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_38x4_B), (ImageView)findViewById(R.id.gridR_38x4_S),
                38, 4);
        daGrid[38][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_38x5_B), (ImageView)findViewById(R.id.gridR_38x5_S),
                38, 5);

        daGrid[39][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_39x0_B), (ImageView)findViewById(R.id.gridR_39x0_S),
                39, 0);
        daGrid[39][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_39x1_B), (ImageView)findViewById(R.id.gridR_39x1_S),
                39, 1);
        daGrid[39][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_39x2_B), (ImageView)findViewById(R.id.gridR_39x2_S),
                39, 2);
        daGrid[39][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_39x3_B), (ImageView)findViewById(R.id.gridR_39x3_S),
                39, 3);
        daGrid[39][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_39x4_B), (ImageView)findViewById(R.id.gridR_39x4_S),
                39, 4);
        daGrid[39][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_39x5_B), (ImageView)findViewById(R.id.gridR_39x5_S),
                39, 5);

        daGrid[40][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_40x0_B), (ImageView)findViewById(R.id.gridR_40x0_S),
                40, 0);
        daGrid[40][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_40x1_B), (ImageView)findViewById(R.id.gridR_40x1_S),
                40, 1);
        daGrid[40][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_40x2_B), (ImageView)findViewById(R.id.gridR_40x2_S),
                40, 2);
        daGrid[40][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_40x3_B), (ImageView)findViewById(R.id.gridR_40x3_S),
                40, 3);
        daGrid[40][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_40x4_B), (ImageView)findViewById(R.id.gridR_40x4_S),
                40, 4);
        daGrid[40][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_40x5_B), (ImageView)findViewById(R.id.gridR_40x5_S),
                40, 5);

        daGrid[41][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_41x0_B), (ImageView)findViewById(R.id.gridR_41x0_S),
                41, 0);
        daGrid[41][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_41x1_B), (ImageView)findViewById(R.id.gridR_41x1_S),
                41, 1);
        daGrid[41][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_41x2_B), (ImageView)findViewById(R.id.gridR_41x2_S),
                41, 2);
        daGrid[41][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_41x3_B), (ImageView)findViewById(R.id.gridR_41x3_S),
                41, 3);
        daGrid[41][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_41x4_B), (ImageView)findViewById(R.id.gridR_41x4_S),
                41, 4);
        daGrid[41][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_41x5_B), (ImageView)findViewById(R.id.gridR_41x5_S),
                41, 5);

        daGrid[42][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_42x0_B), (ImageView)findViewById(R.id.gridR_42x0_S),
                42, 0);
        daGrid[42][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_42x1_B), (ImageView)findViewById(R.id.gridR_42x1_S),
                42, 1);
        daGrid[42][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_42x2_B), (ImageView)findViewById(R.id.gridR_42x2_S),
                42, 2);
        daGrid[42][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_42x3_B), (ImageView)findViewById(R.id.gridR_42x3_S),
                42, 3);
        daGrid[42][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_42x4_B), (ImageView)findViewById(R.id.gridR_42x4_S),
                42, 4);
        daGrid[42][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_42x5_B), (ImageView)findViewById(R.id.gridR_42x5_S),
                42, 5);

        daGrid[43][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_43x0_B), (ImageView)findViewById(R.id.gridR_43x0_S),
                43, 0);
        daGrid[43][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_43x1_B), (ImageView)findViewById(R.id.gridR_43x1_S),
                43, 1);
        daGrid[43][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_43x2_B), (ImageView)findViewById(R.id.gridR_43x2_S),
                43, 2);
        daGrid[43][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_43x3_B), (ImageView)findViewById(R.id.gridR_43x3_S),
                43, 3);
        daGrid[43][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_43x4_B), (ImageView)findViewById(R.id.gridR_43x4_S),
                43, 4);
        daGrid[43][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_43x5_B), (ImageView)findViewById(R.id.gridR_43x5_S),
                43, 5);

        daGrid[44][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_44x0_B), (ImageView)findViewById(R.id.gridR_44x0_S),
                44, 0);
        daGrid[44][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_44x1_B), (ImageView)findViewById(R.id.gridR_44x1_S),
                44, 1);
        daGrid[44][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_44x2_B), (ImageView)findViewById(R.id.gridR_44x2_S),
                44, 2);
        daGrid[44][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_44x3_B), (ImageView)findViewById(R.id.gridR_44x3_S),
                44, 3);
        daGrid[44][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_44x4_B), (ImageView)findViewById(R.id.gridR_44x4_S),
                44, 4);
        daGrid[44][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_44x5_B), (ImageView)findViewById(R.id.gridR_44x5_S),
                44, 5);

        daGrid[45][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_45x0_B), (ImageView)findViewById(R.id.gridR_45x0_S),
                45, 0);
        daGrid[45][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_45x1_B), (ImageView)findViewById(R.id.gridR_45x1_S),
                45, 1);
        daGrid[45][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_45x2_B), (ImageView)findViewById(R.id.gridR_45x2_S),
                45, 2);
        daGrid[45][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_45x3_B), (ImageView)findViewById(R.id.gridR_45x3_S),
                45, 3);
        daGrid[45][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_45x4_B), (ImageView)findViewById(R.id.gridR_45x4_S),
                45, 4);
        daGrid[45][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_45x5_B), (ImageView)findViewById(R.id.gridR_45x5_S),
                45, 5);

        daGrid[46][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_46x0_B), (ImageView)findViewById(R.id.gridR_46x0_S),
                46, 0);
        daGrid[46][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_46x1_B), (ImageView)findViewById(R.id.gridR_46x1_S),
                46, 1);
        daGrid[46][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_46x2_B), (ImageView)findViewById(R.id.gridR_46x2_S),
                46, 2);
        daGrid[46][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_46x3_B), (ImageView)findViewById(R.id.gridR_46x3_S),
                46, 3);
        daGrid[46][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_46x4_B), (ImageView)findViewById(R.id.gridR_46x4_S),
                46, 4);
        daGrid[46][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_46x5_B), (ImageView)findViewById(R.id.gridR_46x5_S),
                46, 5);

        daGrid[47][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_47x0_B), (ImageView)findViewById(R.id.gridR_47x0_S),
                47, 0);
        daGrid[47][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_47x1_B), (ImageView)findViewById(R.id.gridR_47x1_S),
                47, 1);
        daGrid[47][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_47x2_B), (ImageView)findViewById(R.id.gridR_47x2_S),
                47, 2);
        daGrid[47][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_47x3_B), (ImageView)findViewById(R.id.gridR_47x3_S),
                47, 3);
        daGrid[47][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_47x4_B), (ImageView)findViewById(R.id.gridR_47x4_S),
                47, 4);
        daGrid[47][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_47x5_B), (ImageView)findViewById(R.id.gridR_47x5_S),
                47, 5);

        daGrid[48][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_48x0_B), (ImageView)findViewById(R.id.gridR_48x0_S),
                48, 0);
        daGrid[48][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_48x1_B), (ImageView)findViewById(R.id.gridR_48x1_S),
                48, 1);
        daGrid[48][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_48x2_B), (ImageView)findViewById(R.id.gridR_48x2_S),
                48, 2);
        daGrid[48][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_48x3_B), (ImageView)findViewById(R.id.gridR_48x3_S),
                48, 3);
        daGrid[48][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_48x4_B), (ImageView)findViewById(R.id.gridR_48x4_S),
                48, 4);
        daGrid[48][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_48x5_B), (ImageView)findViewById(R.id.gridR_48x5_S),
                48, 5);

        daGrid[49][0] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_49x0_B), (ImageView)findViewById(R.id.gridR_49x0_S),
                49, 0);
        daGrid[49][1] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_49x1_B), (ImageView)findViewById(R.id.gridR_49x1_S),
                49, 1);
        daGrid[49][2] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_49x2_B), (ImageView)findViewById(R.id.gridR_49x2_S),
                49, 2);
        daGrid[49][3] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_49x3_B), (ImageView)findViewById(R.id.gridR_49x3_S),
                49, 3);
        daGrid[49][4] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_49x4_B), (ImageView)findViewById(R.id.gridR_49x4_S),
                49, 4);
        daGrid[49][5] = chooseSpaceOrSquare((ImageView)findViewById(R.id.gridR_49x5_B), (ImageView)findViewById(R.id.gridR_49x5_S),
                49, 5);

        daGrid[50][0] = new SquareObstacle((ImageView) findViewById(R.id.gridR_50x0));
        daGrid[50][1] = new SquareObstacle((ImageView) findViewById(R.id.gridR_50x1));
        daGrid[50][2] = new SquareObstacle((ImageView) findViewById(R.id.gridR_50x2));
        daGrid[50][3] = new SquareObstacle((ImageView) findViewById(R.id.gridR_50x3));
        daGrid[50][4] = new SquareObstacle((ImageView) findViewById(R.id.gridR_50x4));
        daGrid[50][5] = new SquareObstacle((ImageView) findViewById(R.id.gridR_50x5));

        return daGrid;
    }

    public GridImageThing chooseSpaceOrSquare(ImageView spaceImage, ImageView squareImage, int columnNumber, int rowNumber)
    {
        if (columnNumber == 4)
        {
            return chooseSpaceOrSquareFirstColumn(spaceImage, squareImage, rowNumber);
        }
        else if (columnNumber == 49)
        {
            return chooseSpaceOrSquareLastColumn(spaceImage, squareImage, columnNumber, rowNumber);
        }
        else if (columnNumber % 2 == 0)
        {
            return  chooseSpaceOrSquareEvenColumn(spaceImage, squareImage, columnNumber, rowNumber);
        }
        return  chooseSpaceOrSquareOddColumn(spaceImage, squareImage, rowNumber);
    }

    public GridImageThing chooseSpaceOrSquareFirstColumn(ImageView spaceImage, ImageView squareImage, int rowNumber)
    {

        //  One of the bottom GridImageThings (3, 4, or 5) needs to be a BlankGridSpace, so Bob has somewhere to move.
        //  If it's 4 or 3, the one above it must be a BlankGridSpace too, so Bob can jump to land on it.
        if (randomBlankGridSpaceRow == -1)
        {
            randomBlankGridSpaceRow = (int)(3*Math.random()+3);
        }

        boolean needsBlankSpaceAboveIt = randomBlankGridSpaceRow < 5;

        if ((rowNumber == randomBlankGridSpaceRow) || (needsBlankSpaceAboveIt && rowNumber == randomBlankGridSpaceRow - 1))
        {
            squareImage.setVisibility(ImageView.GONE);
            return new BlankGridSpace(spaceImage);
        }
        else
        {
            //  Gets 0 or 1
            int theRandomNumber = (int) (2 * Math.random());

            //  BlankGridSpace
            if (theRandomNumber == 0) {
                squareImage.setVisibility(ImageView.GONE);
                return new BlankGridSpace(spaceImage);
            }
            //  SquareObstacle
            spaceImage.setVisibility(ImageView.GONE);
            return new SquareObstacle(squareImage);
        }
    }

    public GridImageThing chooseSpaceOrSquareOddColumn(ImageView spaceImage, ImageView squareImage, int rowNumber)
    {
        choseRandomBlankGridSpaceRowThisColumn = false;

        boolean needsBlankSpaceAboveIt;
        needsBlankSpaceAboveIt = randomBlankGridSpaceRow < 5;

        if ((rowNumber == randomBlankGridSpaceRow) || (needsBlankSpaceAboveIt && rowNumber == randomBlankGridSpaceRow - 1))
        {
            squareImage.setVisibility(ImageView.GONE);
            return new BlankGridSpace(spaceImage);
        }
        //  If Bob jumped last column, need a SquareObstacle for him to land on in this column:
        else if (needsBlankSpaceAboveIt && rowNumber == randomBlankGridSpaceRow + 1)
        {
            spaceImage.setVisibility(ImageView.GONE);
            return new SquareObstacle(squareImage);
        }
        else
        {
            int theRandomNumber = (int) (2 * Math.random());

            if (theRandomNumber == 0)
            {
                squareImage.setVisibility(ImageView.GONE);
                return new BlankGridSpace(spaceImage);
            }
            spaceImage.setVisibility(ImageView.GONE);
            return new SquareObstacle(squareImage);
        }
    }

    public GridImageThing chooseSpaceOrSquareEvenColumn(ImageView spaceImage, ImageView squareImage, int columnNumber, int rowNumber)
    {
        if (!choseRandomBlankGridSpaceRowThisColumn)
        {
            //  Means can't have chosen Hater coordinates yet either:
            columnHasHaterAlready = false;

            previousRandomBlankGridSpaceRow = randomBlankGridSpaceRow;
            //  Make sure the GridImageThing it chooses is at most two away from the previous one, less if that goes above 0 or below 5.
            if (previousRandomBlankGridSpaceRow == 0)
            {
                randomBlankGridSpaceRow = (int)(3*Math.random());
            }
            else if (previousRandomBlankGridSpaceRow == 1)
            {
                randomBlankGridSpaceRow = (int)(4*Math.random());
            }
            else if (previousRandomBlankGridSpaceRow == 2)
            {
                randomBlankGridSpaceRow = (int)(5*Math.random());
            }
            else if (previousRandomBlankGridSpaceRow == 3)
            {
                randomBlankGridSpaceRow = (int)(5*Math.random()+1);
            }
            else if (previousRandomBlankGridSpaceRow == 4)
            {
                randomBlankGridSpaceRow = (int)(4*Math.random()+2);
            }
            else if (previousRandomBlankGridSpaceRow == 5)
            {
                randomBlankGridSpaceRow = (int)(3*Math.random()+3);
            }

        }
        choseRandomBlankGridSpaceRowThisColumn = true;

        boolean needsBlankSpaceAboveIt;
        needsBlankSpaceAboveIt = (randomBlankGridSpaceRow < 5) && (randomBlankGridSpaceRow > 0) ;

        //if ((rowNumber == randomBlankGridSpaceRow) || (needsBlankSpaceAboveIt && rowNumber == randomBlankGridSpaceRow - 1))
        //  Since the above code doesn't keep track of whether randomBlankGridSpaceRow or previousRandomBlankGridSpaceRow
        //  is larger, need both OR statements.
        if ((rowNumber == randomBlankGridSpaceRow) ||
                (needsBlankSpaceAboveIt && rowNumber == randomBlankGridSpaceRow - 1) ||
                (rowNumber == previousRandomBlankGridSpaceRow) ||
                (rowNumber > randomBlankGridSpaceRow && rowNumber < previousRandomBlankGridSpaceRow) ||
                (rowNumber > previousRandomBlankGridSpaceRow && rowNumber < randomBlankGridSpaceRow))
        {
            squareImage.setVisibility(ImageView.GONE);
            return new BlankGridSpace(spaceImage);
        }
        else
        {
            int theRandomNumber = (int) (2 * Math.random());

            if (theRandomNumber == 0)
            {

                //if ((!columnHasHaterAlready) && (haterCounter < haters.length))
                //  While SquareObstacles can be 2 BlankGridSpaces away from randomBlankGridSpaceRow, Haters need to be 3.
                if ((!columnHasHaterAlready) && (haterCounter < haters.length) &&
                        ((rowNumber < randomBlankGridSpaceRow - 2)||(rowNumber > randomBlankGridSpaceRow + 2)))
                {
                    haterCoordinates[haterCounter][0] = columnNumber;
                    haterCoordinates[haterCounter][1] = rowNumber;
                    haterCounter++;
                    columnHasHaterAlready = true;
                }

                squareImage.setVisibility(ImageView.GONE);
                return new BlankGridSpace(spaceImage);
            }
            spaceImage.setVisibility(ImageView.GONE);
            return new SquareObstacle(squareImage);
        }

    }

    //  Same as an odd-number column, BECAUSE THE ENDING COLUMN SHOULD BE ODD, except place the WinCircle.
    public GridImageThing chooseSpaceOrSquareLastColumn(ImageView spaceImage, ImageView squareImage, int columnNumber, int rowNumber)
    {
        winCircleCoordinates[0][0] = columnNumber;
        winCircleCoordinates[0][1] = randomBlankGridSpaceRow;

        //  Don't need to set this, it's the end of the level.
        //choseRandomBlankGridSpaceRowThisColumn = false;

        boolean needsBlankSpaceAboveIt;
        needsBlankSpaceAboveIt = randomBlankGridSpaceRow < 5;

        if ((rowNumber == randomBlankGridSpaceRow) || (needsBlankSpaceAboveIt && rowNumber == randomBlankGridSpaceRow - 1))
        {
            squareImage.setVisibility(ImageView.GONE);
            return new BlankGridSpace(spaceImage);
        }
        //  If Bob jumped last column, need a SquareObstacle for him to land on in this column:
        else if (needsBlankSpaceAboveIt && rowNumber == randomBlankGridSpaceRow + 1)
        {
            spaceImage.setVisibility(ImageView.GONE);
            return new SquareObstacle(squareImage);
        }
        else
        {
            int theRandomNumber = (int) (2 * Math.random());

            if (theRandomNumber == 0)
            {
                squareImage.setVisibility(ImageView.GONE);
                return new BlankGridSpace(spaceImage);
            }
            spaceImage.setVisibility(ImageView.GONE);
            return new SquareObstacle(squareImage);
        }
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        //  If there are too many SquareObstacles, some Haters might not have gotten coordinates. Make those [0][0].
        if (haterCounter < haters.length - 1)
        {
            for (int index=haterCounter; index<haters.length-1; index++)
            {
                haterCoordinates[index][0] = 0;
                haterCoordinates[index][1] = 0;
            }
        }

        //  Whoops, all Haters are stationary, so only make one xMoveSpeed and one yMoveSpeed of 0:
        float[] allXHaterMoveSpeeds = new float[1];
        allXHaterMoveSpeeds[0] = 0;

        float[] allYHaterMoveSpeeds = new float[1];
        allYHaterMoveSpeeds[0] = 0;


        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[haterCoordinates[0][0]][haterCoordinates[0][1]];

        haters[0] = new Hater((ImageView) findViewById(R.id.haterR_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath1 = new GridImageThing[1];
        thePath1[0] = daGrid[haterCoordinates[1][0]][haterCoordinates[1][1]];

        haters[1] = new Hater((ImageView) findViewById(R.id.haterR_1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath2 = new GridImageThing[1];
        thePath2[0] = daGrid[haterCoordinates[2][0]][haterCoordinates[2][1]];

        haters[2] = new Hater((ImageView) findViewById(R.id.haterR_2));
        haters[2].setImageX(thePath2[0].getImageX());
        haters[2].setImageY(thePath2[0].getImageY());
        haters[2].setPath(thePath2, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath3 = new GridImageThing[1];
        thePath3[0] = daGrid[haterCoordinates[3][0]][haterCoordinates[3][1]];

        haters[3] = new Hater((ImageView) findViewById(R.id.haterR_3));
        haters[3].setImageX(thePath3[0].getImageX());
        haters[3].setImageY(thePath3[0].getImageY());
        haters[3].setPath(thePath3, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath4 = new GridImageThing[1];
        thePath4[0] = daGrid[haterCoordinates[4][0]][haterCoordinates[4][1]];

        haters[4] = new Hater((ImageView) findViewById(R.id.haterR_4));
        haters[4].setImageX(thePath4[0].getImageX());
        haters[4].setImageY(thePath4[0].getImageY());
        haters[4].setPath(thePath4, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath5 = new GridImageThing[1];
        thePath5[0] = daGrid[haterCoordinates[5][0]][haterCoordinates[5][1]];

        haters[5] = new Hater((ImageView) findViewById(R.id.haterR_5));
        haters[5].setImageX(thePath5[0].getImageX());
        haters[5].setImageY(thePath5[0].getImageY());
        haters[5].setPath(thePath5, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath6 = new GridImageThing[1];
        thePath6[0] = daGrid[haterCoordinates[6][0]][haterCoordinates[6][1]];

        haters[6] = new Hater((ImageView) findViewById(R.id.haterR_6));
        haters[6].setImageX(thePath6[0].getImageX());
        haters[6].setImageY(thePath6[0].getImageY());
        haters[6].setPath(thePath6, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath7 = new GridImageThing[1];
        thePath7[0] = daGrid[haterCoordinates[7][0]][haterCoordinates[7][1]];

        haters[7] = new Hater((ImageView) findViewById(R.id.haterR_7));
        haters[7].setImageX(thePath7[0].getImageX());
        haters[7].setImageY(thePath7[0].getImageY());
        haters[7].setPath(thePath7, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath8 = new GridImageThing[1];
        thePath8[0] = daGrid[haterCoordinates[8][0]][haterCoordinates[8][1]];

        haters[8] = new Hater((ImageView) findViewById(R.id.haterR_8));
        haters[8].setImageX(thePath8[0].getImageX());
        haters[8].setImageY(thePath8[0].getImageY());
        haters[8].setPath(thePath8, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath9 = new GridImageThing[1];
        thePath9[0] = daGrid[haterCoordinates[9][0]][haterCoordinates[9][1]];

        haters[9] = new Hater((ImageView) findViewById(R.id.haterR_9));
        haters[9].setImageX(thePath9[0].getImageX());
        haters[9].setImageY(thePath9[0].getImageY());
        haters[9].setPath(thePath9, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath10 = new GridImageThing[1];
        thePath10[0] = daGrid[haterCoordinates[10][0]][haterCoordinates[10][1]];

        haters[10] = new Hater((ImageView) findViewById(R.id.haterR_10));
        haters[10].setImageX(thePath10[0].getImageX());
        haters[10].setImageY(thePath10[0].getImageY());
        haters[10].setPath(thePath10, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath11 = new GridImageThing[1];
        thePath11[0] = daGrid[haterCoordinates[11][0]][haterCoordinates[11][1]];

        haters[11] = new Hater((ImageView) findViewById(R.id.haterR_11));
        haters[11].setImageX(thePath11[0].getImageX());
        haters[11].setImageY(thePath11[0].getImageY());
        haters[11].setPath(thePath11, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath12 = new GridImageThing[1];
        thePath12[0] = daGrid[haterCoordinates[12][0]][haterCoordinates[12][1]];

        haters[12] = new Hater((ImageView) findViewById(R.id.haterR_12));
        haters[12].setImageX(thePath12[0].getImageX());
        haters[12].setImageY(thePath12[0].getImageY());
        haters[12].setPath(thePath12, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath13 = new GridImageThing[1];
        thePath13[0] = daGrid[haterCoordinates[13][0]][haterCoordinates[13][1]];

        haters[13] = new Hater((ImageView) findViewById(R.id.haterR_13));
        haters[13].setImageX(thePath13[0].getImageX());
        haters[13].setImageY(thePath13[0].getImageY());
        haters[13].setPath(thePath13, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath14 = new GridImageThing[1];
        thePath14[0] = daGrid[haterCoordinates[14][0]][haterCoordinates[14][1]];

        haters[14] = new Hater((ImageView) findViewById(R.id.haterR_14));
        haters[14].setImageX(thePath14[0].getImageX());
        haters[14].setImageY(thePath14[0].getImageY());
        haters[14].setPath(thePath14, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath15 = new GridImageThing[1];
        thePath15[0] = daGrid[haterCoordinates[15][0]][haterCoordinates[15][1]];

        haters[15] = new Hater((ImageView) findViewById(R.id.haterR_15));
        haters[15].setImageX(thePath15[0].getImageX());
        haters[15].setImageY(thePath15[0].getImageY());
        haters[15].setPath(thePath15, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath16 = new GridImageThing[1];
        thePath16[0] = daGrid[haterCoordinates[16][0]][haterCoordinates[16][1]];

        haters[16] = new Hater((ImageView) findViewById(R.id.haterR_16));
        haters[16].setImageX(thePath16[0].getImageX());
        haters[16].setImageY(thePath16[0].getImageY());
        haters[16].setPath(thePath16, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath17 = new GridImageThing[1];
        thePath17[0] = daGrid[haterCoordinates[17][0]][haterCoordinates[17][1]];

        haters[17] = new Hater((ImageView) findViewById(R.id.haterR_17));
        haters[17].setImageX(thePath17[0].getImageX());
        haters[17].setImageY(thePath17[0].getImageY());
        haters[17].setPath(thePath17, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath18 = new GridImageThing[1];
        thePath18[0] = daGrid[haterCoordinates[18][0]][haterCoordinates[18][1]];

        haters[18] = new Hater((ImageView) findViewById(R.id.haterR_18));
        haters[18].setImageX(thePath18[0].getImageX());
        haters[18].setImageY(thePath18[0].getImageY());
        haters[18].setPath(thePath18, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath19 = new GridImageThing[1];
        thePath19[0] = daGrid[haterCoordinates[19][0]][haterCoordinates[19][1]];

        haters[19] = new Hater((ImageView) findViewById(R.id.haterR_19));
        haters[19].setImageX(thePath19[0].getImageX());
        haters[19].setImageY(thePath19[0].getImageY());
        haters[19].setPath(thePath19, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath20 = new GridImageThing[1];
        thePath20[0] = daGrid[haterCoordinates[20][0]][haterCoordinates[20][1]];

        haters[20] = new Hater((ImageView) findViewById(R.id.haterR_20));
        haters[20].setImageX(thePath20[0].getImageX());
        haters[20].setImageY(thePath20[0].getImageY());
        haters[20].setPath(thePath20, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        GridImageThing[] thePath21 = new GridImageThing[1];
        thePath21[0] = daGrid[haterCoordinates[21][0]][haterCoordinates[21][1]];

        haters[21] = new Hater((ImageView) findViewById(R.id.haterR_21));
        haters[21].setImageX(thePath21[0].getImageX());
        haters[21].setImageY(thePath21[0].getImageY());
        haters[21].setPath(thePath21, allXHaterMoveSpeeds, allYHaterMoveSpeeds);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        randomGameLogic.bobJumpLogic();
        return true;
    }

}