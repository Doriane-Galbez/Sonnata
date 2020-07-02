package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class rythmeCours extends AppCompatActivity {

    Button boutRetour = null;
    ImageButton boutLARonde = null;
    ImageButton boutLABlanche = null;
    ImageButton boutLANoire = null;
    ImageButton boutLA2croches = null;
    ImageButton boutLA4doubles = null;
    ImageButton boutLAnoirePoint = null;
    ImageButton boutLAcrochepoint = null;
    ImageButton boutLAdoubleCroche = null;
    ImageButton boutLAtriolet = null;
    ImageButton boutLAcroche2doubles = null;
    ImageButton boutLA2doublesCroche = null;
    ImageButton boutLAsyncope = null;
    ImageButton boutLAsyncopette = null;

    MediaPlayer son = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rythme_cours);

        boutRetour = (Button) findViewById(R.id.buttonRetour);
        boutLARonde = (ImageButton) findViewById(R.id.imageButtonLARonde);
        boutLABlanche = (ImageButton) findViewById(R.id.imageButtonLABlanche);
        boutLANoire = (ImageButton) findViewById(R.id.imageButtonLANoire);
        boutLA2croches = (ImageButton) findViewById(R.id.imageButtonLA2croches);
        boutLA4doubles = (ImageButton) findViewById(R.id.imageButtonLA4doubles);
        boutLAnoirePoint = (ImageButton) findViewById(R.id.imageButtonLANoirePoint);
        boutLAcrochepoint = (ImageButton) findViewById(R.id.imageButtonLACrochePoint);
        boutLAdoubleCroche = (ImageButton) findViewById(R.id.imageButtonLADoubleCrochePoint);
        boutLAtriolet = (ImageButton) findViewById(R.id.imageButtonLATriolet);
        boutLAcroche2doubles = (ImageButton) findViewById(R.id.imageButtonLACroche2doubles);
        boutLA2doublesCroche = (ImageButton) findViewById(R.id.imageButtonLA2doubleCroche);
        boutLAsyncope = (ImageButton) findViewById(R.id.imageButtonLASyncope);
        boutLAsyncopette = (ImageButton) findViewById(R.id.imageButtonLASyncopette);

        boutRetour.setOnClickListener(boutRetourListener);
        boutLARonde.setOnClickListener(boutLARondeListener);
        boutLABlanche.setOnClickListener(boutLABlancheListener);
        boutLANoire.setOnClickListener(boutLANoireListener);
        boutLA2croches.setOnClickListener(boutLA2crochesListener);
        boutLA4doubles.setOnClickListener(boutLA4doublesListener);
        boutLAnoirePoint.setOnClickListener(boutLANoirePointListener);
        boutLAcrochepoint.setOnClickListener(boutLAcrochePointListener);
        boutLAdoubleCroche.setOnClickListener(boutLAdoubleCrochePointListener);
        boutLAtriolet.setOnClickListener(boutLAtrioletListener);
        boutLAcroche2doubles.setOnClickListener(boutLAcroche2doublesListener);
        boutLA2doublesCroche.setOnClickListener(boutLA2doublesCrocheListener);
        boutLAsyncope.setOnClickListener(boutLAsyncopeListener);
        boutLAsyncopette.setOnClickListener(boutLAsyncopetteListener);

        son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_ronde);
    }
    //Uniquement si le bouton Retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            Intent menu = new Intent(rythmeCours.this, Rythme.class);
            startActivity(menu);
            finish();
        }
    };

    private View.OnClickListener boutLARondeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_ronde);
            son.start();
        }
    };

    private View.OnClickListener boutLABlancheListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_blanche);
            son.start();
        }
    };

    private View.OnClickListener boutLANoireListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_noire);
            son.start();
        }
    };

    private View.OnClickListener boutLA2crochesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_deux_croches);
            son.start();
        }
    };

    private View.OnClickListener boutLA4doublesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_quatre_double_croches);
            son.start();
        }
    };

    private View.OnClickListener boutLANoirePointListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_noire_pointee_croche);
            son.start();
        }
    };

    private View.OnClickListener boutLAcrochePointListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_croche_pointee_double);
            son.start();
        }
    };

    private View.OnClickListener boutLAdoubleCrochePointListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_double_croche_pointee);
            son.start();
        }
    };

    private View.OnClickListener boutLAtrioletListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_triolet);
            son.start();
        }
    };

    private View.OnClickListener boutLAcroche2doublesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_croche_deux_doubles);
            son.start();
        }
    };

    private View.OnClickListener boutLA2doublesCrocheListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this, R.raw.piano_la_440_deux_doubles_croche);
            son.start();
        }
    };

    private View.OnClickListener boutLAsyncopeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_syncope);
            son.start();
        }
    };

    private View.OnClickListener boutLAsyncopetteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(rythmeCours.this,R.raw.piano_la_440_syncopette);
            son.start();
        }
    };
}
