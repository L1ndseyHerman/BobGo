package com.lherman.bob_go;

//  Have white outlines for now for testing, will eventually just match the background
public class BlankGridSpace implements GridImageThing
{
    @Override
    public void checkCollision()
    {
        //  Do nothing! Can't collide with blank space.
    }

}
