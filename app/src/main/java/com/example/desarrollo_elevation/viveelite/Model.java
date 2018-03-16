package com.example.desarrollo_elevation.viveelite;

import android.net.Uri;

import static android.R.attr.data;

/**
 * Created by Desarrollo_Elevation on 21/12/16.
 */

public class Model {



    public static final int IMAGE_TYPE=1;



    public  static final  int VIDEO_TYPE=2;




    public int type;
    public String data;
    public String titulo;
    public String text;
    public int img;
    public String colores;
    public int favoritos;
    public String idlink;
    public String link;
    public String preparacion;
    public String duracion;
    public int id_tipo;






    public Model(int type, String text, String titulo,String data, int img, String colores, int favoritos, String idlink, String link, String preparacion, String duracion, int id_tipo)
    {
        this.type=type;
        this.data=data;
        this.text=text;
        this.titulo= titulo;
        this.img=img;
        this.colores= colores;
        this.favoritos = favoritos;
        this.idlink = idlink;
        this.link = link;
        this.preparacion = preparacion;
        this.duracion = duracion;
        this.id_tipo = id_tipo;

    }
}
