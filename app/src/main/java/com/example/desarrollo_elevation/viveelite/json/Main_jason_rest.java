package com.example.desarrollo_elevation.viveelite.json;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.desarrollo_elevation.viveelite.DB.DB_prueba_json;
import com.example.desarrollo_elevation.viveelite.Model;
import com.example.desarrollo_elevation.viveelite.R;


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

import static android.R.id.list;

public class Main_jason_rest extends AppCompatActivity {

    private String url = "http://services.groupkt.com/country/search?text=mx" +
            "";

  private static Cursor cursor;
    private static ArrayList<model_json_contry> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jason_rest);

        new JsonTask().execute(url);













    }


    public class JsonTask extends AsyncTask<String, String, ArrayList<model_json_contry>> {

        @Override
        protected ArrayList<model_json_contry> doInBackground(String... urls) {
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

                Log.v("json", parentjson.getString("RestResponse"));
                Log.v("json", parentjson.getJSONObject("RestResponse").getString("result"));

                JSONArray jsarreglo = parentjson.getJSONObject("RestResponse").getJSONArray("result");



               /* JSONObject object = jsarreglo.getJSONObject(3);
                StringBuffer stringBuffer = new StringBuffer();
                String dato = object.getString("adminName3");
                stringBuffer.append(dato);*/


                DB_prueba_json db = new DB_prueba_json(Main_jason_rest.this, "json_contry3", null,1);

                SQLiteDatabase database  = db.getWritableDatabase();


              ContentValues contentValues = new ContentValues();

                ArrayList<model_json_contry> list = new ArrayList<>();


                for (int i = 0; i < jsarreglo.length(); i++) {
                    JSONObject ob = jsarreglo.getJSONObject(i);
                    String name = ob.getString("name");
                    String alpha2_code = ob.getString("alpha2_code");
                    String alpha3_code = ob.getString("alpha3_code");

                    contentValues.put("name", name);
                    contentValues.put("alpha2_code", alpha2_code);
                    contentValues.put("alpha3_code", alpha3_code);

                    database.insert("contry", null, contentValues);



                  /*  String admincode1 = ob.getString("adminCode1");
                    String adminName2 = ob.getString("adminName2");
                    Double lng = ob.getDouble("lng");
                    String countrycode = ob.getString("countryCode");
                    int postalcode = ob.getInt("postalcode");
                    String adminName1 = ob.getString("adminName1");
                    String placeName = ob.getString("placeName");
                    Double lat = ob.getDouble("lat");*/

                    model_json_contry model_json = new model_json_contry(1, name, alpha2_code, alpha3_code);

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
        protected void onPostExecute(ArrayList<model_json_contry> result) {
            super.onPostExecute(result);

            //  MainActivity_prueba_json.MovieAdapter movieAdapter = new MainActivity_prueba_json.MovieAdapter(getApplicationContext(), R.layout.item_json, result);
            //   listView.setAdapter(movieAdapter);

            /*adapter_jason_contry adapter_json = new adapter_jason_contry(result, Main_jason_rest.this);



            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_jason_rest.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerjsoncontry);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(Main_jason_rest.this, 1));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter_json);*/


            //textprueba.setText(result);
            //Log.v("json datos", result);



            DB_prueba_json json = new DB_prueba_json(Main_jason_rest.this, "json_contry3", null, 1);

            SQLiteDatabase database = json.getWritableDatabase();

            list = new ArrayList<>();

            String query = "select name, alpha2_code, alpha3_code from contry";


            cursor = database.rawQuery(query, null);

            while (cursor.moveToNext())
            {
                String name = cursor.getString(0);
                String alpha2_code = cursor.getString(1);
                String alpha3_code = cursor.getString(2);

                model_json_contry model_json_contry = new model_json_contry(1, name, alpha2_code, alpha3_code);
                list.add(model_json_contry);

            }

            adapter_jason_contry adapter_json = new adapter_jason_contry(list, Main_jason_rest.this);



            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_jason_rest.this, OrientationHelper.VERTICAL, false);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recicleviwerjsoncontry);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(Main_jason_rest.this, 1));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter_json);







        }




    }











}