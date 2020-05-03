package com.mission_004.mission004.Advice_Exception;

public class AlbumNotFoundException extends RuntimeException
{
    public AlbumNotFoundException(String id)
    {
        super("could not find album " + id);
    }
}
