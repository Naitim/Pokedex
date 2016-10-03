package com.example.tim.pokedexv3;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import com.example.tim.pokedexv3.R;
import com.example.tim.pokedexv3.AppController;

public class MainActivity extends AppCompatActivity {

    private List<Pokemon> pokemonList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PokemonAdapter mAdapter;
    private static final String TAG = "MainActivity";
    private static final String pokeAPIURL = "http://pokeapi.co/api/v2/pokemon/";
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerDecoration(this));

        mAdapter = new PokemonAdapter(pokemonList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        for (int i = 1; i < 152; i++) {
            makeJsonObjectRequest(i);
        }
    }

    /**
     * Method to make json object request where json response starts wtih {
     */
    private void makeJsonObjectRequest(int i) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                pokeAPIURL + i + "/", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {


                    String name =  response.getString("name");
                    String number =  response.getString("id");
                    //String types = "placeholder";
                    JSONArray typesAry = (JSONArray) response.getJSONArray("types");
                    JSONObject typesObj = (JSONObject)typesAry.get(0);
                    JSONObject typesObj2 = (JSONObject) typesObj.get("type");
                    String types = typesObj2.getString("name");
                    if(typesAry.length()== 2){
                         typesObj = (JSONObject)typesAry.get(1);
                         typesObj2 = (JSONObject) typesObj.get("type");
                         types = types + ", " + typesObj2.getString("name");
                    }
                    String height = response.getString("height");
                    String weight =  response.getString("weight");
                    String basexp =  response.getString("base_experience");
                    JSONObject sprites = (JSONObject) response.get("sprites");
                    String sprite = sprites.getString("front_default");

                    Pokemon pokemon = new Pokemon(name, number, basexp, types, height, weight, sprite);
                    pokemonList.add(pokemon);
                    mAdapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void preparePokemonData() {


        Pokemon pokemon = new Pokemon("Charmander", "4", "Fire");
        pokemonList.add(pokemon);

        pokemon = new Pokemon("Bulbasaur", "1", "Grass");
        pokemonList.add(pokemon);

        pokemon = new Pokemon("Squirtle", "7", "Water");
        pokemonList.add(pokemon);

        mAdapter.notifyDataSetChanged();
    }
}
