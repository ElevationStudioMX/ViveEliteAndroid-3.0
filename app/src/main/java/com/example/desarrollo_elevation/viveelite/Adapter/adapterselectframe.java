package com.example.desarrollo_elevation.viveelite.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.desarrollo_elevation.viveelite.MainActivity_Editarphoto;
import com.example.desarrollo_elevation.viveelite.Modelselectframe;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.photogesturelibrary.PhotoView;
import com.example.photogesturelibrary.PhotoViewAttacher;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 30/01/17.
 */

public class adapterselectframe extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Modelselectframe> dataSet;
    private static float densityDpi;
    Context mContext;
    int total_types;

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView marco;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.imagemarcosparaseleccionar);
            this.marco = (ImageView) itemView.findViewById(R.id.imagenviewmarco);
        }
    }

    public adapterselectframe(ArrayList<Modelselectframe> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Modelselectframe.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marcos, parent, false);
                return new adapterselectframe.ImageTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).type) {
            case 0:
                return Modelselectframe.IMAGE_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
        final Modelselectframe object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case Modelselectframe.IMAGE_TYPE:
                    Picasso.with(mContext)
                            .load(object.img)
                            .resize(250, 0)
                            .into(((ImageTypeViewHolder) holder).image);

                    ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View view) {
                            final MainActivity_Editarphoto editarphoto = new MainActivity_Editarphoto();
                            final ImageView imgenes = editarphoto.retornartext();
                            try {
                                URL newurl = new URL(object.img);
                                Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                                imgenes.setImageBitmap(mIcon_val);
                            } catch (IOException e) {
                                Log.v("psao por aqui ", "Liena 177");
                            }
                            editarphoto.retornoposicionmarco(listPosition);
                            final PhotoView photoView = editarphoto.getImageView();
                            final RelativeLayout vista = editarphoto.getCamView();
                            final PhotoViewAttacher attacher = editarphoto.getA();
                            ViewFlipper viewFlipper = editarphoto.getViewFlipper();
                            vista.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                            int vh = vista.getMeasuredHeight();
                            int vw = vista.getMeasuredWidth();
                            Log.e("DV-vista", " --------> " + vh + "___" + vw);

                            int h = imgenes.getDrawable().getIntrinsicHeight();
                            int w = imgenes.getDrawable().getIntrinsicWidth();
                            DisplayMetrics displayMetrics = new DisplayMetrics();
                            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                            int height = displayMetrics.heightPixels;
                            final int width = displayMetrics.widthPixels;
                            DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
                            densityDpi = metrics.density;
                            final int alturaimagen = photoView.getDrawable().getIntrinsicHeight();
                            final int anchuraimagen = photoView.getDrawable().getIntrinsicWidth();
                            final int heigthview = editarphoto.getHeigthview();
                            if (object.img.equals("http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers/no-marco.png")) {
                                imgenes.setVisibility(View.INVISIBLE);
                                viewFlipper.setVisibility(View.INVISIBLE);
                                float proporcionimage = (float) alturaimagen / anchuraimagen;
                                float proporcionview = (float) heigthview / width;
                                float anchoimag = (float) heigthview / proporcionimage;
                                final float alturaimagenes = (float) proporcionimage * width;

                                if (proporcionview < proporcionimage) {
                                    Log.e("DV-vista1", " -------> " + vista.getLayoutParams().height);
                                    vista.getLayoutParams().height = heigthview;
                                    vista.getLayoutParams().width = (int) anchoimag;
                                    photoView.getLayoutParams().height = heigthview;
                                } else {
                                    Log.e("DV-01_altura de iamgen", " -----> " + alturaimagenes);
                                    photoView.getLayoutParams().height = (int) alturaimagenes;
                                    vista.getLayoutParams().height = (int) alturaimagenes;
                                    Log.e("Condicionesvictorianav2", "view altura " + vista.getLayoutParams().height + " imagen altura" + photoView.getLayoutParams().height);
                                }
                                Log.v("DV-01vista2", "" + vista.getLayoutParams().height);
                                Log.v("DV-01heigt ", "" + vista.getHeight());
                            } else {
                                viewFlipper.setVisibility(View.VISIBLE);
                                imgenes.setVisibility(View.VISIBLE);
                                final float proporcion = (float) h / w;
                                final int alturadispositovo = (int) (proporcion * width);
                                final float proporcion2 = (float) w / h;
                                final float proporcionimagen = (float) anchuraimagen / alturaimagen;
                                vista.getLayoutParams().height = alturadispositovo;
                                ViewTreeObserver b = photoView.getViewTreeObserver();
                                b.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                    @Override
                                    public void onGlobalLayout() {
                                        photoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                        if (proporcionimagen < proporcion2) {
                                            float segundaanchira = proporcion2 * alturaimagen;
                                            final float scalahorizontal = segundaanchira / anchuraimagen;
                                            PhotoViewAttacher attacher = editarphoto.getA();
                                            attacher.setScale(scalahorizontal);
                                        } else {
                                            float alturareal = proporcion * anchuraimagen;
                                            final float scalvertical = alturareal / alturaimagen;
                                            final PhotoViewAttacher attacher = editarphoto.getA();
                                            attacher.setScale(scalvertical);
                                        }
                                    }
                                });
                            }
                            //  OnLayoutChangeListener();
                            // vista.OnLayoutChangeListener;
                            /*vista.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                                @Override
                                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                                    Log.v("impri", ""+view.getLayoutParams().height+" "+vista.getLayoutParams().height+" "+i1+" "+i2+" "+i3+" "+i4+" "+i5+" "+i6+" "+i7);
                                 if(vista.getLayoutParams().height == view.getLayoutParams().height)
                                 {
                                    PhotoView photoView = editarphoto.getImageView();
                                    PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);
                                    attacher.setScale(1.85f);
                                    Log.v("scala attacher", ""+attacher.getScale());
                                    }
                                }
                            });*/
                           /* new View().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                                @Override
                                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft,
                                                           int oldTop, int oldRight, int oldBottom) {
                                    // TODO Auto-generated method stub
                                }
                            });*/

                         /* new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            Log.i("tag", "This'll run 300 milliseconds later");
                                            PhotoView photoView = editarphoto.getImageView();
                                            PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);
                                            attacher.setScale(1.85f);
                                            Log.v("scala attacher", ""+attacher.getScale());
                                        }
                                    },
                                    1);*/
                          /*  Log.v("Datos de la h y w", "Height "+h+" Whidth"+w);
                            Log.v("Datos del dispositvo", "Height "+height+" Whidth"+width);
                            Log.v("proporcion; ",""+proporcion);
                            Log.v("altura dsp;", ""+alturadispositovo);*/
                            //((ImageTypeViewHolder)holder).image.setBackgroundColor(Color.parseColor("#d5bcf6"));
                            ///   int pos = editarphoto.getposition(listPosition);
                            //int pos = editarphoto.retornoposicionmarco();
                            //imgenes.setImageResource(object.img);

                          /*  Intent intent = new Intent(mContext, MainActivity_Editarphoto.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("dato", object.img);
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);*/
                            // mContext..startService(intent);
                            // MainActivity_Editarphoto a = new MainActivity_Editarphoto();
                            // a.cambio_imagen(object.img);
                            // Toast.makeText(mContext, "dato= "+dato, Toast.LENGTH_LONG).show();
                            // mar =(ImageView)a.findViewById(R.id.imagenviewmarco);
                            // mar.setImageResource(dato);
                            // Picasso.with(mContext).load(object.img).resize(500, 0).into(((adapterselectframe.ImageTypeViewHolder) holder).marco);
                            //((adapterselectframe.ImageTypeViewHolder) holder).marco.setImageResource(R.drawable.marco2);
                            //   Toast.makeText(mContext, "pulsastes este mono"+ listPosition, Toast.LENGTH_LONG).show();
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}