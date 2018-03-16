package com.example.desarrollo_elevation.viveelite.json;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.Model;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.web_api_spotify.models.Image;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import static android.R.id.list;
import static com.example.desarrollo_elevation.viveelite.R.id.list_item;
import static com.example.desarrollo_elevation.viveelite.R.id.txtMoviName;
import static com.example.desarrollo_elevation.viveelite.R.id.txtstart;
import static com.example.desarrollo_elevation.viveelite.R.id.view;

public class MainActivity_prueba_json extends AppCompatActivity {
Button hit;
ListView listView;
    private String url = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt";
   // private String url = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoItem.txt";
    private static  TextView tvjsom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_prueba_json);
        listView = (ListView)findViewById(R.id.listviwer);

       // tvjsom = (TextView) findViewById(R.id.tvjson);
        hit = (Button)findViewById(R.id.btnhit);

       // new JsonTask().execute(url);


        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new JsonTask().execute(url);

                Log.v("Muestra", url);



            }
        });
    }

    public  class  JsonTask extends AsyncTask<String, String, List<MovieModel> >{

        @Override
        protected List<MovieModel> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader( new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();




                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    buffer.append(line);
                    //tvjsom.setText(buffer.toString());
                }

        //        Log.v("buffer", buffer.toString());
//                Log.v("que es line", line);

                String finaljson = buffer.toString();

                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("movies");




           // StringBuffer finalbuferString = new StringBuffer();


List<MovieModel> movieModelList = new ArrayList<>();

                for( int  i = 0; i < jsarreglo.length(); i++ ) {
                    MovieModel m = new MovieModel();

                    JSONObject finalobject = jsarreglo.getJSONObject(i);

                    m.setMovie(finalobject.getString("movie"));
                    m.setYear(finalobject.getInt("year"));
                    m.setReating((float)finalobject.getDouble("rating"));
                    m.setDirector(finalobject.getString("director"));
                    m.setDuration(finalobject.getString("duration"));
                    m.setTagline(finalobject.getString("tagline"));
                    m.setImage(finalobject.getString("image"));
                    m.setStory(finalobject.getString("story"));
                    m.setDuration(finalobject.getString("duration"));

                    List<MovieModel.Cast> castlist = new ArrayList<>();
                    for(int j =0; j<finalobject.getJSONArray("cast").length(); j++)
                    {
                      //  JSONObject castobject =
                        MovieModel.Cast cast = new MovieModel.Cast();

                        cast.SetName(finalobject.getJSONArray("cast").getJSONObject(j).getString("name"));
                        castlist.add(cast);
                    }

                    m.setCastList(castlist);

                    movieModelList.add(m);
               //     String moviname = finalobject.getString("movie");
                 //   int año = Integer.parseInt(finalobject.getString("year"));

                //    finalbuferString.append(moviname +" "+ año +"\n");
                }
                return   movieModelList;   //finalbuferString.toString(); //moviname +" "+año; //buffer.toString();

            }


            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            /*catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                if(connection != null) {connection.disconnect();}
                try {
                    if(reader != null)
                    {reader.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/

            return null;//"entro en esta condicion";
        }



        @Override
        protected void onPostExecute(List<MovieModel>  result) {
            super.onPostExecute(result);

            MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), R.layout.item_json, result);
            listView.setAdapter(movieAdapter);
           // tvjsom.setText(result);
            //Log.v("json datos", result);
        }


    }


    private class  MovieAdapter extends ArrayAdapter {

        public List<MovieModel> movieModels;
        private  int resoucer;
        private LayoutInflater inflater;


        public MovieAdapter(Context context, int resource, List<MovieModel> objects) {
            super(context, resource, objects);

            movieModels = objects;
            this.resoucer = resource;
            inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if(convertView == null)
            {
                convertView = inflater.inflate(R.layout.item_json, null);

            }

            ImageView imgcomic;
            TextView movie;
            TextView tagline;
            TextView year;
            TextView duration;
            TextView Director;
            RatingBar ratingBar;

            TextView cast;
            TextView story;


            imgcomic = (ImageView)convertView.findViewById(R.id.imgconic);
            movie = (TextView)convertView.findViewById(R.id.txtMoviName);
            tagline = (TextView)convertView.findViewById(R.id.txttagline);
            year = (TextView)convertView.findViewById(R.id.txtano);
            duration = (TextView)convertView.findViewById(R.id.txtduration);
            Director = (TextView)convertView.findViewById(R.id.txtdirector);

            ratingBar = (RatingBar)convertView.findViewById(R.id.ratingBar);
            cast = (TextView)convertView.findViewById(R.id.txtcast);
            story = (TextView)convertView.findViewById(R.id.txtstory);

            movie.setText(movieModels.get(position).getMovie());
            year.setText("year: "+movieModels.get(position).getYear());
            tagline.setText(movieModels.get(position).getTagline());
            //year.setText(movieModels.get(position).getYear());
            duration.setText(movieModels.get(position).getDuration());
            Director.setText(movieModels.get(position).getDirector());


            StringBuffer stringBuffer = new StringBuffer();
            for(MovieModel.Cast cast1: movieModels.get(position).getCastList())
            {
                stringBuffer.append(cast1.getName());
            }

            cast.setText(stringBuffer);
            story.setText(movieModels.get(position).getStory());

            Picasso.with(getContext()).load(movieModels.get(position).getImage()).resize(500,0).into(imgcomic);

            ratingBar.setRating(movieModels.get(position).getReating());






            return  convertView;
           // return  super.getView(position, convertView, parent);
        }

    }
}
