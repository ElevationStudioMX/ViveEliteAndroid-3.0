package com.example.desarrollo_elevation.viveelite.Adapter;

/**
 * Created by Desarrollo_Elevation on 27/03/17.
 */

public class Model_BD {

    public static final int IMAGE_TYPE=1;



    public  static final  int VIDEO_TYPE=2;




    public int type;
    public String data;
    public String text;
    //public String img;




    public Model_BD(int type, String text, String data /*String img*/)
    {
        this.type=type;
        this.data=data;
        this.text=text;
      //  this.img=img;

    }
}
