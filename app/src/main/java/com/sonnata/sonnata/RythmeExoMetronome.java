package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class RythmeExoMetronome extends AppCompatActivity {
    int bpm = 60;
    int numTemps = 1;
    int compteur = 0;
    int tempsMes = 4;
    int nbClickGood = 0;
    int nbClickPassed = 0;
    boolean firstClick = false;

    Button boutRetour = null;
    Button boutAppuyez = null;
    Button boutPlus = null;
    Button boutMinus = null;

    RadioButton radioPulse1 = null;
    RadioButton radioPulse2 = null;
    RadioButton radioPulse3 = null;
    RadioButton radioPulse4 = null;

    Switch actif = null;

    CheckBox good = null;

    SeekBar barProgress = null;

    TextView texteTempo = null;
    TextView texteScore = null;
    TextView texteBpm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rythme_exo_metronome);

        boutRetour = (Button) findViewById(R.id.buttonRetour);
        boutAppuyez = (Button) findViewById(R.id.buttonAppuyez);
        boutPlus = (Button) findViewById(R.id.buttonPlus);
        boutMinus = (Button) findViewById(R.id.buttonMinus);

        radioPulse1 = (RadioButton) findViewById(R.id.radioPulse1);
        radioPulse2 = (RadioButton) findViewById(R.id.radioPulse2);
        radioPulse3 = (RadioButton) findViewById(R.id.radioPulse3);
        radioPulse4 = (RadioButton) findViewById(R.id.radioPulse4);

        actif = (Switch) findViewById(R.id.switchActif);

        good = (CheckBox) findViewById(R.id.checkBox);

        barProgress = (SeekBar) findViewById(R.id.seekBar);

        texteTempo = (TextView) findViewById(R.id.textViewTempo);
        texteScore = (TextView) findViewById(R.id.textViewScore);
        texteBpm = (TextView) findViewById(R.id.textViewNbBpm);


        boutRetour.setOnClickListener(boutRetourListener);
        boutPlus.setOnClickListener(boutPlusBpmListener);
        boutMinus.setOnClickListener(boutMinusBpmListener);
        boutAppuyez.setOnClickListener(appuieListener);

        actif.setOnClickListener(switchActifListener);

        barProgress.setProgress(bpm);

        barProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean b) {
                progress = progresValue;
                bpm = progresValue;
                texteBpm.setText(String.valueOf(bpm));
                findTempo(bpm);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        boutAppuyez.setClickable(false);
        findTempo(bpm);
    }


    /* Fait demarrer le timer - prend en parametre le temps de fin en millisencondes */
    private void startTimer(int endTimeMillis){
        compteur = 0;
        firstClick = true;
        texteScore.setText(getResources().getString(R.string.texteScore,nbClickGood,nbClickPassed));
        if (actif.isChecked()){
            new CountDownTimer(60000/endTimeMillis,36000/endTimeMillis){
                public void onTick(long millisUntilFinished){
                    compteur=compteur+1;
                    //texteScore.setText(String.valueOf(compteur));
                }
                public void onFinish(){
                    turnRadioBout(numTemps);
                    //texteScore.setText(String.valueOf(compteur));
                    if (numTemps>=tempsMes) {numTemps=1;}
                    else{ numTemps = numTemps + 1;}
                    if (actif.isChecked()){nbClickPassed = nbClickPassed+1;}
                    startTimer(bpm);
                }
            }.start();
        }
        else {
            boutAppuyez.setClickable(false);
        }
    }


    private void turnRadioBout(int numT){
        switch (numT){
            case 1:
                radioPulse1.setChecked(true);
                radioPulse2.setChecked(false);
                radioPulse3.setChecked(false);
                radioPulse4.setChecked(false);
                break;
            case 2:
                radioPulse1.setChecked(false);
                radioPulse2.setChecked(true);
                radioPulse3.setChecked(false);
                radioPulse4.setChecked(false);
                break;
            case 3:
                radioPulse1.setChecked(false);
                radioPulse2.setChecked(false);
                radioPulse3.setChecked(true);
                radioPulse4.setChecked(false);
                break;
            case 4:
                radioPulse1.setChecked(false);
                radioPulse2.setChecked(false);
                radioPulse3.setChecked(false);
                radioPulse4.setChecked(true);
                break;
            default:
                break;
        }
    }



    //Uniquement si le bouton Appuyez est appuie
    private View.OnClickListener appuieListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (compteur==1) {
                good.setChecked(true);
                if (firstClick==true){
                    nbClickGood = nbClickGood+1;
                    firstClick = false;
                }
                texteScore.setText(getResources().getString(R.string.texteScore,nbClickGood,nbClickPassed));
            }else
            {
                good.setChecked(false);
                texteScore.setText(getResources().getString(R.string.texteScore,nbClickGood,nbClickPassed));
            }
        }
    };

    //Uniquement si le switch actif est modifie
    private View.OnClickListener switchActifListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boutAppuyez.setClickable(true);
            startTimer(bpm);
        }
    };

    //Uniquement si le bouton plusBpm est appuie
    private View.OnClickListener boutPlusBpmListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (bpm<300){
                bpm = bpm+1;
                texteBpm.setText(String.valueOf(bpm));
                barProgress.setProgress(bpm);
                findTempo(bpm);
            }
        }
    };

    //Uniquement si le bouton minusBpm est appuie
    private View.OnClickListener boutMinusBpmListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (bpm>40){
                bpm = bpm-1;
                texteBpm.setText(String.valueOf(bpm));
                barProgress.setProgress(bpm);
                findTempo(bpm);
            }
        }
    };


    //Uniquement si le bouton Retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actif.setChecked(false);
            Intent menu = new Intent(RythmeExoMetronome.this, Rythme.class);
            startActivity(menu);
            finish();
        }
    };

    private void findTempo (int BPM) {
        if (BPM<=55){
            texteTempo.setText("Largo");
        }else if (BPM<=65 && BPM>=56){
            texteTempo.setText("Lento");
        }else if (BPM<=77 && BPM>=66){
            texteTempo.setText("Adagio");
        }else if (BPM<=90 && BPM>=78){
            texteTempo.setText("Andante");
        }else if (BPM<=105 && BPM>=91){
            texteTempo.setText("Moderato");
        }else if (BPM<=115 && BPM>=106){
            texteTempo.setText("Allegretto");
        }else if (BPM<=140 && BPM>=116){
            texteTempo.setText("Allegro");
        }else if (BPM<=160 && BPM>=141){
            texteTempo.setText("Vivace");
        }else if (BPM<=200 && BPM>=161){
            texteTempo.setText("Presto");
        }else if (BPM>=201){
            texteTempo.setText("Prestissimo");
        }
    }
}
