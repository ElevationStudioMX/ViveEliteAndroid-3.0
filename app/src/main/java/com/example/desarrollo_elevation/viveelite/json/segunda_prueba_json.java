package com.example.desarrollo_elevation.viveelite.json;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.Adapter.Model_BD;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.desarrollo_elevation.viveelite.prueba_recycleviwer_base_datos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class segunda_prueba_json extends AppCompatActivity {

    TextView textprueba;
//private  String url = "http://api.geonames.org/postalCodeLookupJSON?postalcode=6600&country=AT&username=demo";
    private  String url = "http://api.geonames.org/postalCodeLookupJSON?postalcode=78745&country=MX&username=demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_prueba_json);




    //textprueba =(TextView)findViewById(R.id.txtjtson);


        new JsonTask().execute(url);



    }



    public  class  JsonTask extends AsyncTask<String, String, ArrayList<Model_Json>> {

        @Override
        protected ArrayList<Model_Json> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();


                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                    //tvjsom.setText(buffer.toString());
                }

                //        Log.v("buffer", buffer.toString());
//                Log.v("que es line", line);

                String finaljson = buffer.toString();

                JSONObject parentjson = new JSONObject(finaljson);
                JSONArray jsarreglo = parentjson.getJSONArray("postalcodes");

               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/


                ArrayList<Model_Json> list = new ArrayList<>();


                for (int i = 0; i< jsarreglo.length(); i++)
                {
                    JSONObject ob = jsarreglo.getJSONObject(i);
                    int admincode2 = ob.getInt("adminCode2");
                    int admincode3 = ob.getInt("adminCode3");
                    String adminName3 = ob.getString("adminName3");
                    String admincode1 = ob.getString("adminCode1");
                    String adminName2 = ob.getString("adminName2");
                    Double lng = ob.getDouble("lng");
                    String countrycode = ob.getString("countryCode");
                    int postalcode = ob.getInt("postalcode");
                    String adminName1 = ob.getString("adminName1");
                    String placeName = ob.getString("placeName");
                    Double lat = ob.getDouble("lat");

                   Model_Json model_json = new Model_Json(1, admincode2, admincode3, adminName3, admincode1, adminName2, lng, countrycode, postalcode, adminName1, placeName, lat);

                    list.add(model_json);

                }

                return list;



                // StringBuffer finalbuferString = new StringBuffer();


              /*  List<MovieModel> movieModelList = new ArrayList<>();

                for (int i = 0; i < jsarreglo.length(); i++) {
                    MovieModel m = new MovieModel();

                    JSONObject finalobject = jsarreglo.getJSONObject(i);

                    m.setMovie(finalobject.getString("movie"));
                    m.setYear(finalobject.getInt("year"));
                    m.setReating((float) finalobject.getDouble("rating"));
                    m.setDirector(finalobject.getString("director"));
                    m.setDuration(finalobject.getString("duration"));
                    m.setTagline(finalobject.getString("tagline"));
                    m.setImage(finalobject.getString("image"));
                    m.setStory(finalobject.getString("story"));
                    m.setDuration(finalobject.getString("duration"));

                    List<MovieModel.Cast> castlist = new ArrayList<>();
                    for (int j = 0; j < finalobject.getJSONArray("cast").length(); j++) {
                        //  JSONObject castobject =
                        MovieModel.Cast cast = new MovieModel.Cast();

                        cast.SetName(finalobject.getJSONArray("cast").getJSONObject(j).getString("name"));
                        castlist.add(cast);
                    }

                    m.setCastList(castlist);

                    movieModelList.add(m);*/
                    //     String moviname = finalobject.getString("movie");
                    //   int año = Integer.parseInt(finalobject.getString("year"));

                    //    finalbuferString.append(moviname +" "+ año +"\n");

                //return movieModelList;   //finalbuferString.toString(); //moviname +" "+año; //buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
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
        protected void onPostExecute(ArrayList<Model_Json> result) {
            super.onPostExecute(result);

            //  MainActivity_prueba_json.MovieAdapter movieAdapter = new MainActivity_prueba_json.MovieAdapter(getApplicationContext(), R.layout.item_json, result);
            //   listView.setAdapter(movieAdapter);

            Adapter_Json adapter_json = new Adapter_Json(result, segunda_prueba_json.this);



            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(segunda_prueba_json.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewer_json);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(segunda_prueba_json.this, 1));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter_json);


            //textprueba.setText(result);
            //Log.v("json datos", result);
        }

    }






}
