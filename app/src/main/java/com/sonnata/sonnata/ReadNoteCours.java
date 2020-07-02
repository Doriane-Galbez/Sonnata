package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReadNoteCours extends AppCompatActivity {

    Button boutRetour = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note_cours);
        boutRetour = (Button) findViewById(R.id.boutonRetour);
        boutRetour.setOnClickListener(boutRetourListener);
    }
    //Uniquement si le bouton Retour est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent readNote = new Intent(ReadNoteCours.this, ReadNote.class);
            startActivity(readNote);
            finish();
        }
    };
}
