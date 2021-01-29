package no.hvl.dat153.oblig1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import no.hvl.dat153.oblig1.Activities.Database;

import no.hvl.dat153.oblig1.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void goDatabase(View View) {
        Intent i = new Intent(this, DatabaseActivity.class);
        startActivity(i);
    }

    public void goQuiz(View View) {
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);
    }

    public void goAdd(View View) {
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }


}