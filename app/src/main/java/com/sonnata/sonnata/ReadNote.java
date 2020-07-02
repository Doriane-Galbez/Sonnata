package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReadNote extends AppCompatActivity {

    Button boutCours = null;
    Button boutExo = null;
    Button boutRetour = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        boutCours = (Button) findViewById(R.id.bouton1);
        boutExo = (Button) findViewById(R.id.bouton2);
        boutRetour = (Button) findViewById(R.id.bouton3);

        //On attribut un listener adapte aux vue qui en ont besoin
        boutCours.setOnClickListener(boutCoursListener);
        boutExo.setOnClickListener(boutExoListener);
        boutRetour.setOnClickListener(boutRetourListener);
    }

    //Uniquement si le bouton Cours est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutCoursListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent readNote = new Intent(ReadNote.this, ReadNoteCours.class);
            startActivity(readNote);
        }
    };

    //Uniquement si le bouton Exo est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutExoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent readNoteExo = new Intent(ReadNote.this, ReadNoteExos.class);
            startActivity(readNoteExo);
        }
    };

    //Uniquement si le bouton Retour est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActi = new Intent(ReadNote.this, MainActivity.class);
            startActivity(mainActi);
            finish();
        }
    };
}
