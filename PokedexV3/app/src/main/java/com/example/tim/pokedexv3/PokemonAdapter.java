package com.example.tim.pokedexv3;

/**
 * Created by Tim on 03-Oct-16.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.MyViewHolder>{

    private List<Pokemon> pokemonsList;
    private static final String TAG = "Pokemon Adapter";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView number;
        public TextView types;
        public TextView baseaxp;
        public TextView weight;
        public TextView height;
        public ImageView spriteURL;
        public Button moves;
        public Button utube;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name_textView);
            number = (TextView) view.findViewById(R.id.number_textView);
            types = (TextView) view.findViewById(R.id.types_textView);
            baseaxp = (TextView) view.findViewById(R.id.basexp_textView);
            weight = (TextView) view.findViewById(R.id.weight_textView);
            height = (TextView) view.findViewById(R.id.height_textView);
            spriteURL = (ImageView) view.findViewById(R.id.pokemon_imageView);
           /* moves = (Button) view.findViewById(R.id.info_button);
            moves.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    Intent internetIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/results?search_query="+name.getText()));
                    internetIntent.setComponent(new ComponentName("com.android.browser","com.android.browser.BrowserActivity"));
                    internetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(internetIntent);
                    }
            }*/



        }
    }


    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonsList = pokemonList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Pokemon pokemon = pokemonsList.get(position);
        holder.name.setText("Name: " + pokemon.getName());
        holder.number.setText("No. " + pokemon.getNumber());
        holder.types.setText("Type: " + pokemon.getTypes());
        holder.baseaxp.setText("BaseXP: " +pokemon.getBasexp());
        holder.weight.setText("Weight(KG): " +pokemon.getWeight());
        holder.height.setText("Height(M): " + pokemon.getHeight());
        Context context = holder.spriteURL.getContext();
        Picasso.with(context).load(pokemon.getSpriteURL()).into(holder.spriteURL);
    }

    @Override
    public int getItemCount() {
        return pokemonsList.size();
    }
}
