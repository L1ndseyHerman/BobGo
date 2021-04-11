package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RandomLevelActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[7][6];
    private Hater[] haters = new Hater[1];
    private Coin[] coins = new Coin[1];
    private Button beginButton;
    private GameLogic gameLogic;

    private int randomBlankGridSpaceRow = -1;
    private int previousRandomBlankGridSpaceRow = -1;
    private boolean choseRandomBlankGridSpaceRowThisColumn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_level);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setXMoveSpeedScreen(screenWidth /168);

        ImageView bobImage = findViewById(R.id.bobR);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBobR_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBobR_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBobR_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBobR_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBobR_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBobR_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBobR_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircleR));
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreTextR);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButtonR);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                //  Not saving the score for this, in fact the Coin is only there bec I'm too lazy to code if-statements
                //  around everything coin-related the way I did for if the powerUps are present vs absent in that level.

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButtonR);
        gameLogic.setEndButtonLogic(endButton);
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

        return daGrid;
    }

    public GridImageThing chooseSpaceOrSquare(ImageView spaceImage, ImageView squareImage, int columnNumber, int rowNumber)
    {
        if (columnNumber == 4)
        {
            return chooseSpaceOrSquareFirstColumn(spaceImage, squareImage, rowNumber);
        }
        else if (columnNumber % 2 == 0)
        {
            return  chooseSpaceOrSquareEvenColumn(spaceImage, squareImage, rowNumber);
        }
        return  chooseSpaceOrSquareOddColumn(spaceImage, squareImage, rowNumber);
    }

    public GridImageThing chooseSpaceOrSquareFirstColumn(ImageView spaceImage, ImageView squareImage, int rowNumber)
    {

        //  One of the bottom GridImageThings (3, 4, or 5) needs to be a BlankGridSpace, so Bob has somewhere to move.
        //  If it's 4 or 3, the one above it must be a BlankGridSpace too, so Bob can jump to land on it.
        //int randomBlankGridSpaceRow = (int)(3*Math.random()+3);
        //  AND MAKE SURE THE NUMBER ONLY GETS SET THE FIRST METHOD CALL!
        if (randomBlankGridSpaceRow == -1)
        {
            randomBlankGridSpaceRow = (int)(3*Math.random()+3);
        }
        boolean needsBlankSpaceAboveIt;
        needsBlankSpaceAboveIt = randomBlankGridSpaceRow < 5;

        if ((rowNumber == randomBlankGridSpaceRow) || (needsBlankSpaceAboveIt && rowNumber == randomBlankGridSpaceRow - 1))
        {
            //System.out.println("Row number = " + rowNumber);
            squareImage.setVisibility(ImageView.INVISIBLE);
            return new BlankGridSpace(spaceImage);
        }
        else
        {
            //  Gets 0 or 1
            int theRandomNumber = (int) (2 * Math.random());

            //  BlankGridSpace
            if (theRandomNumber == 0) {
                squareImage.setVisibility(ImageView.INVISIBLE);
                return new BlankGridSpace(spaceImage);
            }
            //  SquareObstacle
            spaceImage.setVisibility(ImageView.INVISIBLE);
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
            //System.out.println("Row number = " + rowNumber);
            squareImage.setVisibility(ImageView.INVISIBLE);
            return new BlankGridSpace(spaceImage);
        }
        //  If Bob jumped last column, need a SquareObstacle for him to land on in this column:
        else if (needsBlankSpaceAboveIt && rowNumber == randomBlankGridSpaceRow + 1)
        {
            //System.out.println("Row number = " + rowNumber);
            spaceImage.setVisibility(ImageView.INVISIBLE);
            return new SquareObstacle(squareImage);
        }
        else
        {
            int theRandomNumber = (int) (2 * Math.random());

            if (theRandomNumber == 0)
            {
                squareImage.setVisibility(ImageView.INVISIBLE);
                return new BlankGridSpace(spaceImage);
            }
            spaceImage.setVisibility(ImageView.INVISIBLE);
            return new SquareObstacle(squareImage);
        }
    }

    public GridImageThing chooseSpaceOrSquareEvenColumn(ImageView spaceImage, ImageView squareImage, int rowNumber)
    {
        if (!choseRandomBlankGridSpaceRowThisColumn)
        {
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
            System.out.println("randomBlankGridSpaceRow = " + randomBlankGridSpaceRow);
            System.out.println("previousRandomBlankGridSpaceRow = " + previousRandomBlankGridSpaceRow);
            System.out.println("Row number = " + rowNumber);
            squareImage.setVisibility(ImageView.INVISIBLE);
            return new BlankGridSpace(spaceImage);
        }
        else
        {
            int theRandomNumber = (int) (2 * Math.random());

            if (theRandomNumber == 0) {
                squareImage.setVisibility(ImageView.INVISIBLE);
                return new BlankGridSpace(spaceImage);
            }
            spaceImage.setVisibility(ImageView.INVISIBLE);
            return new SquareObstacle(squareImage);
        }

    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coinR_0));
        coins[0].setImageX(daGrid[0][0].getImageX());
        coins[0].setImageY(daGrid[0][0].getImageY());

        return coins;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[0][0];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.haterR_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}