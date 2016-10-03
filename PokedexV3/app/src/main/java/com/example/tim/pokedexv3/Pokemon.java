package com.example.tim.pokedexv3;

/**
 * Created by Tim on 03-Oct-16.
 */
public class Pokemon {

    private String name;
    private String number;
    private String basexp;
    private String types;
    private String height;
    private String weight;
    private String spriteURL;
    //add sprite;


    public Pokemon() {
    }

    public Pokemon(String name, String number, String types) {
        this.name = name;
        this.number = number;
        this.types = types;
    }

    public Pokemon(String name, String number, String basexp, String types, String height, String weight, String spriteURL) {
        this.name = name;
        this.number = number;
        this.basexp = basexp;
        this.types = types;
        this.height = height;
        this.weight = weight;
        this.spriteURL = spriteURL;
    }

    public Pokemon(String name, String number, String basexp, String types, String height, String weight) {
        this.name = name;
        this.number = number;
        this.basexp = basexp;
        this.types = types;

        this.height = height;
        this.weight = weight;
    }

    public String getSpriteURL() {
        return spriteURL;
    }

    public void setSpriteURL(String spriteURL) {
        this.spriteURL = spriteURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBasexp() {
        return basexp;
    }

    public void setBasexp(String basexp) {
        this.basexp = basexp;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
