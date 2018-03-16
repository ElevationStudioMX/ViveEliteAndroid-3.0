package com.example.desarrollo_elevation.viveelite.Adapter;

/**
 * Created by Desarrollo_Elevation on 09/05/17.
 */

public class Model_productos {

    public static final int IMAGE_TYPE=1;
    public int type;
    public int imagen;
    public String titulo;
    public String contenido;

    public Model_productos(int type, int imagen, String titulo, String contenido) {
        this.type = type;
        this.imagen = imagen;
        this.titulo = titulo;
        this.contenido = contenido;
    }
    /*  public Model_productos(int type, int imagen, int numbero, String titulo) {
        this.type = type;
        this.imagen = imagen;
        this.numbero = numbero;
        this.titulo = titulo;
    }*/
}
