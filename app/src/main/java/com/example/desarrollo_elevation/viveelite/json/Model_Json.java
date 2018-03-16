package com.example.desarrollo_elevation.viveelite.json;

import static android.R.attr.data;
import static android.R.attr.textSelectHandleWindowStyle;

/**
 * Created by Desarrollo_Elevation on 04/04/17.
 */

public class Model_Json

{

    public static final int TYPE=1;



   // public  static final  int VIDEO_TYPE=2;




    public int type;
    public int admincode2;
    public int admincode3;
    public String adminName3;
    public String admincode1;
    public String adminName2;
    public double lng;
    public String countrycode;
    public int postalcode;
    public String adminName1;
    public String placeName;
    public double lat;




    public Model_Json(int type, int admincode2, int admincode3,String adminName3, String admincode1, String adminName2, double lng, String countrycode,
                      int postalcode, String adminName1, String placeName, double lat)
    {
        this.type=type;
        this.admincode2 = admincode2;
        this.admincode3 = admincode3;
        this.adminName3 = adminName3;
        this.admincode1 = admincode1;
        this.adminName2 = adminName2;
        this.lng = lng;
        this.countrycode = countrycode;
        this.postalcode = postalcode;
        this.adminName1 = adminName1;
        this.placeName = placeName;
        this.lat = lat;

    }


}
