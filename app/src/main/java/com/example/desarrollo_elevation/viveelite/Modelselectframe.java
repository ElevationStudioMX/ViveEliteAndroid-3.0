package com.example.desarrollo_elevation.viveelite;

/**
 * Created by Desarrollo_Elevation on 30/01/17.
 */

public class Modelselectframe  {



    public static final int IMAGE_TYPE=0;

    public  static final  int VIDEO_TYPE=1;

    public int type;
    //public String data;
   // public String text;
    public String img;




    public Modelselectframe(int type,/* String text, String data,*/ String img)
    {
        this.type=type;
     //   this.data=data;
      //  this.text=text;
        this.img=img;

    }


}