package com.example.desarrollo_elevation.viveelite.stickers;


import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * Created by cheungchingai on 6/15/15.
 */
public class StickerTextView extends StickerViewtext{
    private AutoResizeTextView tv_main;
    public StickerTextView(Context context) {
        super(context);
    }

    public StickerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public View getMainView() {
        if(tv_main != null)
            return tv_main;

        tv_main = new AutoResizeTextView(getContext());
        String azul = "GREEN";

        //tv_main.setTextSize(22);
        tv_main.setTextColor(Color.BLACK);
        tv_main.setGravity(Gravity.CENTER);



String font = "fonts/SIXTY.TTF";
        String font1 = "fonts/contrast.ttf";
        String font2 = "fonts/leadcoat.ttf";
        String font3 = "fonts/stocky.ttf";
        String font4 = "fonts/OpenSans-Regular.ttf";
        String font5 = "fonts/OpenSans-Light.ttf";
        String font6 ="fonts/OpenSans-Bold.ttf";
        //String font5 = "fonts/SIXTY.TTF";
       Typeface face= Typeface.createFromAsset(getContext().getAssets(),font4);
        tv_main.setTypeface(face);
       // tv_main.setTypeface(face);
       //tv_main.setTypeface(face);
        //tv_main.setShadowLayer(4, 0, 0, Color.BLACK);

        tv_main.setMaxLines(1);
        tv_main.setSingleLine(true);
        tv_main.setTextSize(500);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        //ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(V)

        Log.v("parametros", ""+ViewGroup.LayoutParams.MATCH_PARENT);

        params.gravity = Gravity.CENTER;
        tv_main.setLayoutParams(params);
        if(getImageViewFlip()!=null)
            getImageViewFlip().setVisibility(View.GONE);

        return tv_main;
    }



    public void setText(String text){
        if(tv_main!=null)
            tv_main.setText(text);
    }

    public String getText(){
        if(tv_main!=null)
            return tv_main.getText().toString();

        return null;
    }

    public static float pixelsToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px/scaledDensity;
    }

    public void setTextColor(int Color) {this.tv_main.setTextColor(Color);}



    @Override
    protected void onScaling(boolean scaleUp) {
        super.onScaling(scaleUp);
    }
}
