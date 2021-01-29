package no.hvl.dat153.oblig1.Activities;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;


import no.hvl.dat153.oblig1.R;

public class Database extends Application{  //extends Application??

    private static List<Person> personList = new ArrayList<Person>();



        @Override
        public void onCreate() {

            super.onCreate();
            Person person1 = new Person("Pixel", BitmapFactory.decodeResource(this.getResources(), R.drawable.cat));
            Person person2 = new Person("Bagheera", BitmapFactory.decodeResource(this.getResources(), R.drawable.bagheera));
            Person person3 = new Person("Ginger", BitmapFactory.decodeResource(this.getResources(), R.drawable.ginger));
            Person person4 = new Person("Gray", BitmapFactory.decodeResource(this.getResources(), R.drawable.gray));

            personList.add(person1);
            personList.add(person2);
            personList.add(person3);
            personList.add(person4);


            //format code ctrl+alt+l
        }

    public static void addPerson(Person p){
        personList.add(p);
    }

    public static void removePerson(Person p){
        personList.remove(p);
    }


    public static List<Person> getPersonList() {

            return new ArrayList(personList);
    }
}



