package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Rythme extends AppCompatActivity {

    Button boutCours = null;
    Button boutExo1 = null;
    Button boutExo2 = null;
    Button boutRetour = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rythme);

        boutCours = (Button) findViewById(R.id.buttonRythmeCours);
        boutExo1 = (Button) findViewById(R.id.buttonExoRythme1);
        boutExo2 = (Button) findViewById(R.id.buttonExoRythme2);
        boutRetour = (Button) findViewById(R.id.buttonRetour);

        //On attribut un listener adapte aux vue qui en ont besoin
        boutCours.setOnClickListener(boutCoursListener);
        boutExo1.setOnClickListener(boutExo1Listener);
        boutExo2.setOnClickListener(boutExo2Listener);
        boutRetour.setOnClickListener(boutRetourListener);
    }
    //Uniquement si le bouton Cours est appuie
    private View.OnClickListener boutCoursListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent rythmeCours = new Intent(Rythme.this, rythmeCours.class);
            startActivity(rythmeCours);

        }
    };

    //Uniquement si le bouton Exo1 est appuie
    private View.OnClickListener boutExo1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent rythmeCoursExo = new Intent(Rythme.this, RythmeExoDictee.class);
            startActivity(rythmeCoursExo);
        }
    };

    //Uniquement si le bouton Exo2 est appuie
    private View.OnClickListener boutExo2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent rythmeCoursExo = new Intent(Rythme.this, RythmeExoMetronome.class);
            startActivity(rythmeCoursExo);
        }
    };

    //Uniquement si le bouton Retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActi = new Intent(Rythme.this, MainActivity.class);
            startActivity(mainActi);
            finish();
        }
    };
}
