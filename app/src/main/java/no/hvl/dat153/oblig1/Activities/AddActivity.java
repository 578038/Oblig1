package no.hvl.dat153.oblig1.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

import no.hvl.dat153.oblig1.R;

public class AddActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText imageText;
    private Button buttonBrowse;
    private Button buttonCamera;
    private Button buttonAdd;

    private Button buttonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        buttonAdd = (Button) findViewById(R.id.addButton);
        buttonMenu = (Button) findViewById(R.id.buttonMenu2);
        imageView = (ImageView) findViewById(R.id.imagePerson); //Bilde paa ny person
        imageText = (EditText) findViewById(R.id.newPerson); //Navn paa ny person
        buttonBrowse = (Button) findViewById(R.id.browseButton); //knapp for aa se gjennom bilder


        buttonBrowse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (imageText.getText().toString().equals("")) {
                    Toast.makeText(AddActivity.this, "skriv navn før valg av bilde", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 3);
                }
            }
        });


        buttonCamera = (Button) findViewById(R.id.cameraButton); //knapp for aa TA  bilder
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageText.getText().toString().equals("")) {
                    Toast.makeText(AddActivity.this, "skriv navn før valg av bilde", Toast.LENGTH_SHORT).show();
                } else {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//gallery
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && data != null && resultCode == RESULT_OK) { //

            final Uri selectedImage = data.getData();

            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor c = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            c.moveToFirst();
            c.close();


            imageView.setImageURI(selectedImage);

            addPicture(selectedImage);

        }
        // KAMERA
        else if (requestCode == 0 && resultCode == RESULT_OK && data != null) {

            Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(selectedImage);


            addCamera(selectedImage);

        }


    }

    private void addPicture(final Uri valgtImage) {


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = imageText.getText().toString();

                Bitmap personImage = null;
                try {
                    personImage = MediaStore.Images.Media.getBitmap(getContentResolver(), valgtImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;

                Person p = new Person(name, personImage);

                Database.addPerson(p);

                Toast.makeText(AddActivity.this, "Person: " + p.getName() + " was added to the LIST! Click DATABASE button to see changes", Toast.LENGTH_SHORT).show();


            }
        });


    }


    private void addCamera(Bitmap bm) {


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = imageText.getText().toString();


                Person p = new Person(name, bm);

                Database.addPerson(p);

                Toast.makeText(AddActivity.this, "Person: " + p.getName() + " was added to the LIST! Click DATABASE button to see changes", Toast.LENGTH_SHORT).show();


            }
        });


    }


    public void goDatabase(View View) {
        Intent i = new Intent(this, DatabaseActivity.class);
        startActivity(i);
    }

    public void goMenu(View View) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}