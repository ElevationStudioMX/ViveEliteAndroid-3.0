package com.example.desarrollo_elevation.viveelite.json;

/**
 * Created by Desarrollo_Elevation on 08/05/17.
 */

public class model_json_contry {

    public static final int TYPE=1;



    // public  static final  int VIDEO_TYPE=2;




    public int type;

    public String name;
    public String alpha2_code;
    public String alpha3_code;



    public model_json_contry(int type, String name, String alpha2_code, String alpha3_code) {
        this.type = type;
        this.name = name;
        this.alpha2_code = alpha2_code;
        this.alpha3_code = alpha3_code;
    }
}
