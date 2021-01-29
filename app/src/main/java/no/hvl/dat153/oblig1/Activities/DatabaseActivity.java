package no.hvl.dat153.oblig1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import no.hvl.dat153.oblig1.R;

public class DatabaseActivity extends AppCompatActivity {

    private ArrayList<Person> pList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonDelete;
    private EditText editTextDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


        pList = (ArrayList<Person>) Database.getPersonList();

        buildRecyclerView();

        buttonDelete = findViewById(R.id.button_delete);
        editTextDelete = findViewById(R.id.edittext_delete);


        //Sletter posisjonen som bruker skriver inn
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(editTextDelete.getText().toString());
                if (pos >= 1 && pos <= pList.size()) {
                    deleteItem(pos);
                    resetView(findViewById(R.id.RelativeLayoutDatabase)); //Dette ble gjort for å fikse en bug som gjorde at recycler viewet ble feil i forhold til databasen, slett linjen dersom du vil se bug
                } else {
                    Toast.makeText(DatabaseActivity.this, "Skriv gyldig posisjon!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void deleteItem(int pos) {
//pos-1 slik at posisjon 1 for bruker blir 1 for program, listen starter fra 1 osv...
        Database.removePerson(pList.get(pos - 1)); //Her sletter vi personen fra databasen, er en bug hvor "Gray blir lagt til i recycler view, men ikke i databasen.... DETTE ER BUGGEN SOM STÅR OVER I LINJE 52
        mAdapter.notifyItemRemoved(pos - 1);
    }

    public void goAdd(View View) {
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }

    public void goMenu(View View) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    public void resetView(View View) {
        Intent i = new Intent(this, DatabaseActivity.class);
        startActivity(i);
    }


    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setHasFixedSize(true);  //wont change in size
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new AdapterDatabas(pList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


}