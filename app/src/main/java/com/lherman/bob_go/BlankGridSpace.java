package com.lherman.bob_go;

public class BlankGridSpace implements GridImageThing
{
    @Override
    public void checkCollision()
    {
        //  Do nothing! Can't collide with blank space.
    }
    //  Have white outlines for now for testing, will eventually just match the background
}
