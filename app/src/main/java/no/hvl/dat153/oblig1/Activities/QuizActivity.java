package no.hvl.dat153.oblig1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.Collections;

import no.hvl.dat153.oblig1.R;

public class QuizActivity extends AppCompatActivity {

    private ArrayList<Person> personList;

    private ImageView guessImage;
    private Button buttonGuess;
    private EditText guessText;


    private TextView infoText;
    private TextView scoreText;
    private TextView counterText;


    ArrayList<Person> pList;//Kopi av databasen slik at vi har oversikt over hvilken som er svart og ikke svart


    private int index;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        personList = (ArrayList<Person>) Database.getPersonList();

        pList = new ArrayList<Person>(personList); //Kopi av database
        Collections.shuffle(pList); //stokker listen slik at den blir tilfeldig

        guessImage = (ImageView) findViewById(R.id.imageGuess);
        buttonGuess = (Button) findViewById(R.id.guessButton);
        guessText = (EditText) findViewById(R.id.guessText);


        infoText = (TextView) findViewById(R.id.textView3);

        scoreText = (TextView) findViewById(R.id.textViewScore);
        counterText = (TextView) findViewById(R.id.textViewCounter);
        ;

        index = 0;

        scoreText.setText("Score: " + score + "/" + pList.size());
        counterText.setText("Persons left: " + (pList.size() - index));


        nextPerson();


        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guessText.getText().toString().equals("")) {
                    Toast.makeText(QuizActivity.this, "Skriv inn navn før du gjetter!", Toast.LENGTH_SHORT).show();
                } else {
                    checkCorrect();
                }

            }


        });

    }


    public void checkCorrect() {

        Person p = pList.get(index);
        index++;
        if (p.getName().equals(guessText.getEditableText().toString())) {
            score++;
            Toast.makeText(QuizActivity.this, "Du gjettet riktig! Navn: " + p.getName() + " Din Score: " + score, Toast.LENGTH_SHORT).show();
            scoreText.setText("Score: " + score + "/" + pList.size());
            counterText.setText("Persons left: " + (pList.size() - index));


        } else {
            Toast.makeText(QuizActivity.this, "Du gjettet FEIL, Riktig navn var: " + p.getName(), Toast.LENGTH_SHORT).show();
            counterText.setText("Persons left: " + (pList.size() - index));
        }

        guessText.setText("");
        nextPerson();

    }


    public void nextPerson() {
        if (pList.size() > index) {
            Person pers = pList.get(index);
            guessImage.setImageBitmap(pers.getImg());
        } else {

            infoText.setText("SPILL FERDIG, TRYKK RESET KNAPP FOR Å STARTE PÅ NY");


        }


    }


    public void resetQuiz(View View) {
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);
    }


    public void goMenu(View View) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}