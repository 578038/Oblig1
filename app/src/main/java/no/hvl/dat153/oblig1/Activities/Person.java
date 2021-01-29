package no.hvl.dat153.oblig1.Activities;

import android.graphics.Bitmap;

public class Person {

    private String name;
    private Bitmap img;

    public Person(String name, Bitmap img){
        this.name = name;
        this.img = img;
    }


    public String getName() {
        return name;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
