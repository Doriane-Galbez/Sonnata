package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MiniPiano extends AppCompatActivity {

    int numOctave = 4;  //octave numero 4 par defaut

    CountDownTimer monTimer = null;

    Button boutRetour = null;

    RadioGroup groupeOctave = null;
    RadioButton octave1 = null;
    RadioButton octave2 = null;
    RadioButton octave3 = null;
    RadioButton octave4 = null;
    RadioButton octave5 = null;
    RadioButton octave6 = null;
    RadioButton octave7 = null;

    ImageView toucheDO1 = null;
    ImageView toucheDOd = null;
    ImageView toucheRE = null;
    ImageView toucheREd = null;
    ImageView toucheMI = null;
    ImageView toucheFA = null;
    ImageView toucheFAd = null;
    ImageView toucheSO = null;
    ImageView toucheSOd = null;
    ImageView toucheLA = null;
    ImageView toucheLAd = null;
    ImageView toucheSI = null;
    ImageView toucheDO2 = null;

    MediaPlayer son = null;

    TextView texteNote = null;
    TextView texteFreq = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_piano);

        groupeOctave = (RadioGroup) findViewById(R.id.radioGroupOctaves);
        octave1 = (RadioButton) findViewById(R.id.radioButton1);
        octave2 = (RadioButton) findViewById(R.id.radioButton2);
        octave3 = (RadioButton) findViewById(R.id.radioButton3);
        octave4 = (RadioButton) findViewById(R.id.radioButton4);
        octave5 = (RadioButton) findViewById(R.id.radioButton5);
        octave6 = (RadioButton) findViewById(R.id.radioButton6);
        octave7 = (RadioButton) findViewById(R.id.radioButton7);

        boutRetour = (Button) findViewById(R.id.buttonRetour);
        toucheDO1 = (ImageView) findViewById(R.id.imageViewDO1);
        toucheDOd = (ImageView) findViewById(R.id.imageViewDOd);
        toucheRE = (ImageView) findViewById(R.id.imageViewRE);
        toucheREd = (ImageView) findViewById(R.id.imageViewREd);
        toucheMI = (ImageView) findViewById(R.id.imageViewMI);
        toucheFA = (ImageView) findViewById(R.id.imageViewFA);
        toucheFAd = (ImageView) findViewById(R.id.imageViewFAd);
        toucheSO = (ImageView) findViewById(R.id.imageViewSO);
        toucheSOd = (ImageView) findViewById(R.id.imageViewSOd);
        toucheLA = (ImageView) findViewById(R.id.imageViewLA);
        toucheLAd = (ImageView) findViewById(R.id.imageViewLAd);
        toucheSI = (ImageView) findViewById(R.id.imageViewSI);
        toucheDO2 = (ImageView) findViewById(R.id.imageViewDO2);

        texteNote = (TextView) findViewById(R.id.textViewNote);
        texteFreq = (TextView) findViewById(R.id.textViewFreq);


        //affectation des Listeners

        boutRetour.setOnClickListener(boutRetourListener);

        toucheDO1.setOnTouchListener(toucheDO1Listener);
        toucheRE.setOnTouchListener(toucheREListener);
        toucheMI.setOnTouchListener(toucheMIListener);
        toucheFA.setOnTouchListener(toucheFAListener);
        toucheSO.setOnTouchListener(toucheSOListener);
        toucheLA.setOnTouchListener(toucheLAListener);
        toucheSI.setOnTouchListener(toucheSIListener);
        toucheDO2.setOnTouchListener(toucheDO2Listener);

        toucheDOd.setOnTouchListener(toucheDOdListener);
        toucheREd.setOnTouchListener(toucheREdListener);
        toucheFAd.setOnTouchListener(toucheFAdListener);
        toucheSOd.setOnTouchListener(toucheSOdListener);
        toucheLAd.setOnTouchListener(toucheLAdListener);


        groupeOctave.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton1:
                        numOctave = 1;
                        break;
                    case R.id.radioButton2:
                        numOctave = 2;
                        break;
                    case R.id.radioButton3:
                        numOctave = 3;
                        break;
                    case R.id.radioButton4:
                        numOctave = 4;
                        break;
                    case R.id.radioButton5:
                        numOctave = 5;
                        break;
                    case R.id.radioButton6:
                        numOctave = 6;
                        break;
                    case R.id.radioButton7:
                        numOctave = 7;
                        break;
                    default:
                        break;
                }
            }
        });

        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_49_la_4);

    }

    /* Fait demarrer le timer - prend en parametre le temps de fin en millisencondes */
    private void startTimer(int endTimeMillis){
        if(monTimer!=null) {
            monTimer.cancel();
        }

        monTimer = new CountDownTimer(1000,10){
            public void onTick(long millisUntilFinished){

            }
            public void onFinish(){
                son.release();
            }
        };
        monTimer.start();
    }

    private void resetTimer(){
        startTimer(1000);
    }

    //Uniquement si le bouton Retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            Intent menu = new Intent(MiniPiano.this, MenuJouer.class);
            startActivity(menu);
            finish();
        }
    };

    private View.OnTouchListener toucheDO1Listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheDO1.setImageResource(R.drawable.piano_touche_blanche);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheDO1.setImageResource(R.drawable.piano_touche_blanche_foncee);
                playNote("DO1");
                texteNote.setText("DO");
            }
            else {
                toucheDO1.setImageResource(R.drawable.piano_touche_blanche_foncee);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheREListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheRE.setImageResource(R.drawable.piano_touche_blanche);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheRE.setImageResource(R.drawable.piano_touche_blanche_foncee);
                playNote("RE");
                texteNote.setText("RE");
            }
            else {
                toucheRE.setImageResource(R.drawable.piano_touche_blanche_foncee);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheMIListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheMI.setImageResource(R.drawable.piano_touche_blanche);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheMI.setImageResource(R.drawable.piano_touche_blanche_foncee);
                playNote("MI");
                texteNote.setText("MI");
            }
            else {
                toucheMI.setImageResource(R.drawable.piano_touche_blanche_foncee);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheFAListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheFA.setImageResource(R.drawable.piano_touche_blanche);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheFA.setImageResource(R.drawable.piano_touche_blanche_foncee);
                playNote("FA");
                texteNote.setText("FA");
            }
            else {
                toucheFA.setImageResource(R.drawable.piano_touche_blanche_foncee);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheSOListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheSO.setImageResource(R.drawable.piano_touche_blanche);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheSO.setImageResource(R.drawable.piano_touche_blanche_foncee);
                playNote("SOL");
                texteNote.setText("SOL");
            }
            else {
                toucheSO.setImageResource(R.drawable.piano_touche_blanche_foncee);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheLAListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheLA.setImageResource(R.drawable.piano_touche_blanche);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheLA.setImageResource(R.drawable.piano_touche_blanche_foncee);
                playNote("LA");
                texteNote.setText("LA");
            }
            else {
                toucheLA.setImageResource(R.drawable.piano_touche_blanche_foncee);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheSIListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheSI.setImageResource(R.drawable.piano_touche_blanche);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheSI.setImageResource(R.drawable.piano_touche_blanche_foncee);
                playNote("SI");
                texteNote.setText("SI");
            }
            else {
                toucheSI.setImageResource(R.drawable.piano_touche_blanche_foncee);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheDO2Listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheDO2.setImageResource(R.drawable.piano_touche_blanche);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheDO2.setImageResource(R.drawable.piano_touche_blanche_foncee);
                playNote("DO2");
                texteNote.setText("DO");
            }
            else {
                toucheDO2.setImageResource(R.drawable.piano_touche_blanche_foncee);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheDOdListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheDOd.setImageResource(R.drawable.piano_touche_noire);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheDOd.setImageResource(R.drawable.piano_touche_noire_claire);
                playNote("DOd");
                texteNote.setText("DO# / RE♭");
            }
            else {
                toucheDOd.setImageResource(R.drawable.piano_touche_noire_claire);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheREdListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheREd.setImageResource(R.drawable.piano_touche_noire);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheREd.setImageResource(R.drawable.piano_touche_noire_claire);
                playNote("REd");
                texteNote.setText("RE# / MI♭");
            }
            else {
                toucheREd.setImageResource(R.drawable.piano_touche_noire_claire);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheFAdListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheFAd.setImageResource(R.drawable.piano_touche_noire);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheFAd.setImageResource(R.drawable.piano_touche_noire_claire);
                playNote("FAd");
                texteNote.setText("FA# / SOL♭");
            }
            else {
                toucheFAd.setImageResource(R.drawable.piano_touche_noire_claire);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheSOdListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheSOd.setImageResource(R.drawable.piano_touche_noire);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheSOd.setImageResource(R.drawable.piano_touche_noire_claire);
                playNote("SOLd");
                texteNote.setText("SOL# / LA♭");
            }
            else {
                toucheSOd.setImageResource(R.drawable.piano_touche_noire_claire);
            }
            return true;
        }
    };

    private View.OnTouchListener toucheLAdListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP){
                toucheLAd.setImageResource(R.drawable.piano_touche_noire);
            }
            else if (event.getAction() == MotionEvent.ACTION_DOWN){
                resetTimer();
                toucheLAd.setImageResource(R.drawable.piano_touche_noire_claire);
                playNote("LAd");
                texteNote.setText("LA# / SI♭");
            }
            else {
                toucheLAd.setImageResource(R.drawable.piano_touche_noire_claire);
            }
            return true;
        }
    };



    private void playNote(String nomNote){
        switch (numOctave){
            case 1:
                switch (nomNote){
                    case "DO1":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_04_do_1);
                        texteFreq.setText("32,703 Hz");
                        break;
                    case "DOd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_05_do_1_diese);
                        texteFreq.setText("34,648 Hz");
                        break;
                    case "RE":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_06_re_1);
                        texteFreq.setText("36,708 Hz");
                        break;
                    case "REd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_07_re_1_diese);
                        texteFreq.setText("38,891 Hz");
                        break;
                    case "MI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_08_mi_1);
                        texteFreq.setText("41,203 Hz");
                        break;
                    case "FA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_09_fa_1);
                        texteFreq.setText("43,654 Hz");
                        break;
                    case "FAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_10_fa_1_diese);
                        texteFreq.setText("46,249 Hz");
                        break;
                    case "SOL":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_11_sol_1);
                        texteFreq.setText("49 Hz");
                        break;
                    case "SOLd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_12_sol_1_diese);
                        texteFreq.setText("51,913 Hz");
                        break;
                    case "LA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_13_la_1);
                        texteFreq.setText("55 Hz");
                        break;
                    case "LAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_14_la_1_diese);
                        texteFreq.setText("58,27 Hz");
                        break;
                    case "SI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_15_si_1);
                        texteFreq.setText("61,735 Hz");
                        break;
                    case "DO2":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_16_do_2);
                        texteFreq.setText("65,406 Hz");
                        break;
                }
                break;
            case 2:
                switch (nomNote){
                    case "DO1":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_16_do_2);
                        texteFreq.setText("65,406 Hz");
                        break;
                    case "DOd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_17_do_2_diese);
                        texteFreq.setText("69,296 Hz");
                        break;
                    case "RE":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_18_re_2);
                        texteFreq.setText("73,416 Hz");
                        break;
                    case "REd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_19_re_2_diese);
                        texteFreq.setText("77,782 Hz");
                        break;
                    case "MI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_20_mi_2);
                        texteFreq.setText("82,407 Hz");
                        break;
                    case "FA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_21_fa_2);
                        texteFreq.setText("87,307 Hz");
                        break;
                    case "FAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_22_fa_2_diese);
                        texteFreq.setText("92,499 Hz");
                        break;
                    case "SOL":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_23_sol_2);
                        texteFreq.setText("98 Hz");
                        break;
                    case "SOLd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_24_sol_2_diese);
                        texteFreq.setText("103,826 Hz");
                        break;
                    case "LA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_25_la_2);
                        texteFreq.setText("110 Hz");
                        break;
                    case "LAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_26_la_2_diese);
                        texteFreq.setText("116,541 Hz");
                        break;
                    case "SI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_27_si_2);
                        texteFreq.setText("123,471 Hz");
                        break;
                    case "DO2":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_28_do_3);
                        texteFreq.setText("130,813 Hz");
                        break;
                }
                break;
            case 3:
                switch (nomNote){
                    case "DO1":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_28_do_3);
                        texteFreq.setText("130,813 Hz");
                        break;
                    case "DOd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_29_do_3_diese);
                        texteFreq.setText("138,591 Hz");
                        break;
                    case "RE":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_30_re_3);
                        texteFreq.setText("146,832 Hz");
                        break;
                    case "REd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_31_re_3_diese);
                        texteFreq.setText("155,563 Hz");
                        break;
                    case "MI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_32_mi_3);
                        texteFreq.setText("164,814 Hz");
                        break;
                    case "FA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_33_fa_3);
                        texteFreq.setText("174,614 Hz");
                        break;
                    case "FAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_34_fa_3_diese);
                        texteFreq.setText("185 Hz");
                        break;
                    case "SOL":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_35_sol_3);
                        texteFreq.setText("196 Hz");
                        break;
                    case "SOLd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_36_sol_3_diese);
                        texteFreq.setText("207,652 Hz");
                        break;
                    case "LA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_37_la_3);
                        texteFreq.setText("220 Hz");
                        break;
                    case "LAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_38_la_3_diese);
                        texteFreq.setText("233,082 Hz");
                        break;
                    case "SI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_39_si_3);
                        texteFreq.setText("246,942 Hz");
                        break;
                    case "DO2":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_40_do_4);
                        texteFreq.setText("261,626 Hz");
                        break;
                }
                break;
            case 4:
                switch (nomNote){
                    case "DO1":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_40_do_4);
                        texteFreq.setText("261,626 Hz");
                        break;
                    case "DOd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_41_do_4_diese);
                        texteFreq.setText("277,183 Hz");
                        break;
                    case "RE":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_42_re_4);
                        texteFreq.setText("293,665 Hz");
                        break;
                    case "REd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_43_re_4_diese);
                        texteFreq.setText("311,127 Hz");
                        break;
                    case "MI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_44_mi_4);
                        texteFreq.setText("329,628 Hz");
                        break;
                    case "FA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_45_fa_4);
                        texteFreq.setText("349,228 Hz");
                        break;
                    case "FAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_46_fa_4_diese);
                        texteFreq.setText("369,994 Hz");
                        break;
                    case "SOL":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_47_sol_4);
                        texteFreq.setText("391,995 Hz");
                        break;
                    case "SOLd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_48_sol_4_diese);
                        texteFreq.setText("413,305 Hz");
                        break;
                    case "LA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_49_la_4);
                        texteFreq.setText("440 Hz");
                        break;
                    case "LAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_50_la_4_diese);
                        texteFreq.setText("466,164 Hz");
                        break;
                    case "SI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_51_si_4);
                        texteFreq.setText("493,883 Hz");
                        break;
                    case "DO2":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_52_do_5);
                        texteFreq.setText("523,251 Hz");
                        break;
                }
                break;
            case 5:
                switch (nomNote){
                    case "DO1":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_52_do_5);
                        texteFreq.setText("523,251 Hz");
                        break;
                    case "DOd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_53_do_5_diese);
                        texteFreq.setText("554,365 Hz");
                        break;
                    case "RE":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_54_re_5);
                        texteFreq.setText("587,33 Hz");
                        break;
                    case "REd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_55_re_5_diese);
                        texteFreq.setText("622,254 Hz");
                        break;
                    case "MI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_56_mi_5);
                        texteFreq.setText("659,255 Hz");
                        break;
                    case "FA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_57_fa_5);
                        texteFreq.setText("698,456 Hz");
                        break;
                    case "FAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_58_fa_5_diese);
                        texteFreq.setText("739,989 Hz");
                        break;
                    case "SOL":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_59_sol_5);
                        texteFreq.setText("783,991 Hz");
                        break;
                    case "SOLd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_60_sol_5_diese);
                        texteFreq.setText("830,609 Hz");
                        break;
                    case "LA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_61_la_5);
                        texteFreq.setText("880 Hz");
                        break;
                    case "LAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_62_la_5_diese);
                        texteFreq.setText("932,328 Hz");
                        break;
                    case "SI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_63_si_5);
                        texteFreq.setText("987,767 Hz");
                        break;
                    case "DO2":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_64_do_6);
                        texteFreq.setText("1046,5 Hz");
                        break;
                }
                break;
            case 6:
                switch (nomNote){
                    case "DO1":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_64_do_6);
                        texteFreq.setText("1046,5 Hz");
                        break;
                    case "DOd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_65_do_6_diese);
                        texteFreq.setText("1108,73 Hz");
                        break;
                    case "RE":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_66_re_6);
                        texteFreq.setText("1174,66 Hz");
                        break;
                    case "REd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_67_re_6_diese);
                        texteFreq.setText("1244,51 Hz");
                        break;
                    case "MI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_68_mi_6);
                        texteFreq.setText("1318,51 Hz");
                        break;
                    case "FA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_69_fa_6);
                        texteFreq.setText("1396,91 Hz");
                        break;
                    case "FAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_70_fa_6_diese);
                        texteFreq.setText("1479,98 Hz");
                        break;
                    case "SOL":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_71_sol_6);
                        texteFreq.setText("1567,98 Hz");
                        break;
                    case "SOLd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_72_sol_6_diese);
                        texteFreq.setText("1661,22 Hz");
                        break;
                    case "LA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_73_la_6);
                        texteFreq.setText("1760 Hz");
                        break;
                    case "LAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_74_la_6_diese);
                        texteFreq.setText("1864,66 Hz");
                        break;
                    case "SI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_75_si_6);
                        texteFreq.setText("1975,53 Hz");
                        break;
                    case "DO2":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_76_do_7);
                        texteFreq.setText("2093 Hz");
                        break;
                }
                break;
            case 7:
                switch (nomNote){
                    case "DO1":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_76_do_7);
                        texteFreq.setText("2093 Hz");
                        break;
                    case "DOd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_77_do_7_diese);
                        texteFreq.setText("2217,46 Hz");
                        break;
                    case "RE":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_78_re_7);
                        texteFreq.setText("2349,32 Hz");
                        break;
                    case "REd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_79_re_7_diese);
                        texteFreq.setText("2489,02 Hz");
                        break;
                    case "MI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_80_mi_7);
                        texteFreq.setText("2637,02 Hz");
                        break;
                    case "FA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_81_fa_7);
                        texteFreq.setText("2793,83 Hz");
                        break;
                    case "FAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_82_fa_7_diese);
                        texteFreq.setText("2959,96 Hz");
                        break;
                    case "SOL":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_83_sol_7);
                        texteFreq.setText("3135,96 Hz");
                        break;
                    case "SOLd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_84_sol_7_diese);
                        texteFreq.setText("3322,44 Hz");
                        break;
                    case "LA":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_85_la_7);
                        texteFreq.setText("3520 Hz");
                        break;
                    case "LAd":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_86_la_7_diese);
                        texteFreq.setText("3729,31 Hz");
                        break;
                    case "SI":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_87_si_7);
                        texteFreq.setText("3951,07 Hz");
                        break;
                    case "DO2":
                        son = MediaPlayer.create(MiniPiano.this,R.raw.piano_88_do_8);
                        texteFreq.setText("4186,01 Hz");
                        break;
                }
                break;
            default :
                break;
        }

        son.start();  //joue le son
    }


}
