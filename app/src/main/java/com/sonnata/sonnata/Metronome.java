package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class Metronome extends AppCompatActivity {

    int bpm = 60;
    int tempsMes = 4;
    int compteur = 0; //valeur du compteur
    int numTemps = 1;

    Button plusBpm = null;
    Button minusBpm = null;
    Button plusTemps = null;
    Button minusTemps = null;
    Button boutRetour = null;

    RadioGroup radioPulse = null;

    RadioButton radioPulse1 = null;
    RadioButton radioPulse2 = null;
    RadioButton radioPulse3 = null;
    RadioButton radioPulse4 = null;

    SeekBar barProgress = null;

    TextView texteBpm = null;
    TextView texteTempsMes = null;
    TextView texteTempo = null;
    Switch actif = null;

    MediaPlayer son = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);

        plusBpm = (Button) findViewById(R.id.buttonPlus);
        minusBpm = (Button) findViewById(R.id.buttonMinus);
        plusTemps = (Button) findViewById(R.id.buttonPlusT);
        minusTemps = (Button) findViewById(R.id.buttonMinusT);
        boutRetour = (Button) findViewById(R.id.boutonRetour);

        radioPulse1 = (RadioButton) findViewById(R.id.radioPulse1);
        radioPulse2 = (RadioButton) findViewById(R.id.radioPulse2);
        radioPulse3 = (RadioButton) findViewById(R.id.radioPulse3);
        radioPulse4 = (RadioButton) findViewById(R.id.radioPulse4);

        barProgress = (SeekBar) findViewById(R.id.seekBar);

        actif = (Switch) findViewById(R.id.switchActif);

        texteBpm = (TextView) findViewById(R.id.textViewNbBpm);
        texteTempsMes = (TextView) findViewById(R.id.textViewNbT);
        texteTempo = (TextView) findViewById(R.id.textViewTempo);

        //On attribut un listener adapte aux vue qui en ont besoin
        plusBpm.setOnClickListener(boutPlusBpmListener);
        minusBpm.setOnClickListener(boutMinusBpmListener);
        plusTemps.setOnClickListener(boutPlusTempsListener);
        minusTemps.setOnClickListener(boutMinusTempsListener);
        boutRetour.setOnClickListener(boutRetourListener);

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


        actif.setOnClickListener(switchActifListener);
        son = MediaPlayer.create(Metronome.this,R.raw.enr_tic_metro_v1);  //initialisation de son (le son n'est pas lance) / permet d'eviter les problemes avec .release()

    }

    /* Fait demarrer le timer - prend en parametre le temps de fin en millisencondes */
    private void startTimer(int endTimeMillis){
        compteur = 0;
        if (actif.isChecked()){
            new CountDownTimer(60000/endTimeMillis,36000/endTimeMillis){
                public void onTick(long millisUntilFinished){
                    compteur=compteur+1;
                }
                public void onFinish(){
                    if (numTemps==1) { playTic();}
                    else { playTac();}
                    turnRadioBout(numTemps);
                    if (numTemps>=tempsMes) {numTemps=1;}
                    else{ numTemps = numTemps + 1;}
                    startTimer(bpm);
                }
            }.start();
        }else {
            son.release();
        }
    }


    private void playTic() {
        son.release();
        son = MediaPlayer.create(Metronome.this,R.raw.enr_tic_metro_v1);
        son.start();
    }
    private void playTac() {
        son.release();
        son = MediaPlayer.create(Metronome.this,R.raw.enr_tac_metro_v1);
        son.start();
    }

    //permet de faire "avancer" visuellement le radio bouton allume (le 1 puis le 2 puis le 3 puis le 4 et on repart du 1)
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


    //Uniquement si le switch actif est appuie
    private View.OnClickListener switchActifListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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

    //Uniquement si le bouton plusTemps est appuie
    private View.OnClickListener boutPlusTempsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (tempsMes<4){
                tempsMes = tempsMes+1;
                texteTempsMes.setText(String.valueOf(tempsMes));
            }
        }
    };

    //Uniquement si le bouton minusTemps est appuie
    private View.OnClickListener boutMinusTempsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (tempsMes>1){
                tempsMes = tempsMes-1;
                texteTempsMes.setText(String.valueOf(tempsMes));
            }
        }
    };

    //Uniquement si le bouton Retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actif.setChecked(false);
            son.release();
            Intent menu = new Intent(Metronome.this, MenuJouer.class);
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


