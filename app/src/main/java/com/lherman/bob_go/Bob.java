package com.lherman.bob_go;

import android.widget.ImageView;

//  The main character that the user controls.
public class Bob
{

    //  The ImageView of Bob from activity_level_one.xml, or whatever other levels use.
    private ImageView bobImage;
    //  These will change in the SquareObstacle class when Bob lands on squares and things.
    private boolean IsJumping, IsJumpingLittle, IsFalling, IsFallingLittle, IsOnTopOfSquare, IsMovingRightLittle;
    private boolean isPoweredUp;
    //  These should always stay the same after they get set the first time.
    private int yJumpSpeed, jumpHeight, lowestY, daGridX;
    //  startHeight changes every time Bob.startJumpMaybe(); , it's his current y-value when the jump starts.
    private float startHeight, yLittleAmount, xLittleAmount;

    public Bob(ImageView bobImage)
    {
        this.bobImage = bobImage;
        IsJumping = false;
        IsJumpingLittle = false;
        IsFallingLittle = false;
        IsFalling = false;
        IsOnTopOfSquare = false;
        IsMovingRightLittle = false;
    }

    public void startJumpMaybe()
    {
        if ((!IsJumping) && (!IsFalling))
        {
            IsJumping = true;
            startHeight = bobImage.getY();
        }
    }

    public int getDaGridX()
    {
        return daGridX;
    }
    public void setDaGridX(int daGridX)
    {
        this.daGridX = daGridX;
    }

    //  Ones with "Is" are boolean getters.
    public boolean IsJumping()
    {
        return IsJumping;
    }
    public void setJumping(boolean IsJumping)
    {
        this.IsJumping = IsJumping;
    }

    public boolean IsJumpingLittle()
    {
        return IsJumpingLittle;
    }
    public void setJumpingLittle(boolean IsJumpingLittle)
    {
        this.IsJumpingLittle = IsJumpingLittle;
    }

    public boolean IsFalling()
    {
        return IsFalling;
    }
    public void setFalling(boolean IsFalling)
    {
        this.IsFalling = IsFalling;
    }

    public boolean IsFallingLittle()
    {
        return IsFallingLittle;
    }
    public void setFallingLittle(boolean IsFallingLittle)
    {
        this.IsFallingLittle = IsFallingLittle;
    }

    public boolean IsMovingRightLittle()
    {
        return IsMovingRightLittle;
    }
    public void setMovingRightLittle(boolean IsMovingRightLittle)
    {
        this.IsMovingRightLittle = IsMovingRightLittle;
    }


    public float getYLittleAmount()
    {
        return yLittleAmount;
    }
    public void setYLittleAmount(float yLittleAmount)
    {
        this.yLittleAmount = yLittleAmount;
    }

    public float getXLittleAmount()
    {
        return xLittleAmount;
    }
    public void setXLittleAmount(float xLittleAmount)
    {
        this.xLittleAmount = xLittleAmount;
    }




    public boolean IsOnTopOfSquare()
    {
        return IsOnTopOfSquare;
    }
    public void setOnTopOfSquare(boolean IsOnTopOfSquare)
    {
        this.IsOnTopOfSquare = IsOnTopOfSquare;
    }

    public int getJumpSpeed()
    {
        return yJumpSpeed;
    }
    public void setJumpSpeed(int yJumpSpeed)
    {
        this.yJumpSpeed = yJumpSpeed;
    }

    public float getStartHeight()
    {
        return startHeight;
    }
    //  No setter. Bob finds out his own height when the user taps screen.

    public int getJumpHeight()
    {
        return jumpHeight;
    }
    public void setJumpHeight(int jumpHeight)
    {
        this.jumpHeight = jumpHeight;
    }

    //  This is where Bob should stop falling to avoid going off the bottom of the screen:
    public int getLowestY()
    {
        return lowestY;
    }
    public void setLowestY(int lowestY)
    {
        this.lowestY = lowestY;
    }

    public void setBobImage(ImageView bobImage)
    {
        this.bobImage = bobImage;
    }
    //  This gets Bob's CURRENT image, which may be the sparkly powered-up one, or the regular one.
    public ImageView getBobImage()
    {
        return bobImage;
    }

    public void setIsPoweredUp(boolean isPoweredUp)
    {
        this.isPoweredUp = isPoweredUp;
    }
    public boolean getIsPoweredUp()
    {
        return isPoweredUp;
    }

}
