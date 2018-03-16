package com.example.desarrollo_elevation.viveelite.Adapter;

import static android.R.attr.data;
import static android.R.attr.id;
import static android.R.attr.thickness;

/**
 * Created by Desarrollo_Elevation on 17/05/17.
 */

public class Model_categorias {

    public static final int categoria=1;


    public  int type;
    public  int id_categoria;
    public String name;
    public  String data;

    public Model_categorias(int type, int id_categoria,String data, String name) {


        this.type = type;
        this.id_categoria = id_categoria;
        this.name = name;
        this.data = data;
    }
}
