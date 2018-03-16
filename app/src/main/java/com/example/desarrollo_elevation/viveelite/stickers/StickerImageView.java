package com.example.desarrollo_elevation.viveelite.stickers;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class StickerImageView extends StickerView {

    private String owner_id;
    private ImageView iv_main;
    public StickerImageView(Context context) {
        super(context);
    }

    public StickerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOwnerId(String owner_id){
        this.owner_id = owner_id;
    }

    public String getOwnerId(){
        return this.owner_id;
    }

    @Override
    public View getMainView() {
        if(this.iv_main == null) {
            this.iv_main = new ImageView(getContext());
           // this.iv_main.setScaleType(ImageView.ScaleType.FIT_XY);
         //   int color = Color.parseColor("#FFFFFF");
           // this.iv_main.setColorFilter(color);


        }
        return iv_main;
    }
    public void setImageBitmap(Bitmap bmp){
        this.iv_main.setImageBitmap(bmp);
    }

    public void setPicasso(String picasso){
        {
            Picasso.with(getContext()).load(picasso).resize(400,0).into(this.iv_main);
        }
    }

    public void setImageResource(int res_id)
    {
        //this.iv_main.setImageResource(res_id);
        Picasso.with(getContext()).load(res_id).resize(500, 0).into(this.iv_main);

    }

    public void setImageDrawable(Drawable drawable){ this.iv_main.setImageDrawable(drawable); }

    public void setColorFilter(int colorFilter) {this.iv_main.setColorFilter(colorFilter);}

   // public void SetCololFilter(ColorFilter colorFilter){this.iv_main.setColorFilter(colorFilter);}

    public Bitmap getImageBitmap(){ return ((BitmapDrawable)this.iv_main.getDrawable()).getBitmap() ; }

}
