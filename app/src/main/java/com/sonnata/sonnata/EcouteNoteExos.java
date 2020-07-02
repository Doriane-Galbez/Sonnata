package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Random;

public class EcouteNoteExos extends AppCompatActivity {

    int nbSound = 33;
    int nbReponse = 0;
    int nbBonneReponse = 0;
    int compteur = 0;
    int dureeTimer = 120000; //duree du timer en millisecondes -> 2 min

    PopupWindow popupWindow;
    LinearLayout linearLayout2;

    Button boutDO = null;
    Button boutRE = null;
    Button boutMI = null;
    Button boutFA = null;
    Button boutSO = null;
    Button boutLA = null;
    Button boutSI = null;
    Button boutRetour = null;
    Button boutReplay = null;

    Button start = null; //bouton du popup

    ImageView image = null;
    TextView texte = null;
    TextView timer = null;
    TextView texteScore = null;
    TextView texteScoreFinal = null;
    TextView textePopup = null;

    Random nbRandom = null;

    MediaPlayer son = null;

    String nomSon = null;
    String[] nomsSons = { //ensemble de notes du SI1 au FA6
            "piano_15_si_1",
            "piano_16_do_2",
            "piano_18_re_2",
            "piano_20_mi_2",
            "piano_21_fa_2",
            "piano_23_sol_2",
            "piano_25_la_2",
            "piano_27_si_2",
            "piano_28_do_3",
            "piano_30_re_3",
            "piano_32_mi_3",
            "piano_33_fa_3",
            "piano_35_sol_3",
            "piano_37_la_3",
            "piano_39_si_3",
            "piano_40_do_4",
            "piano_42_re_4",
            "piano_44_mi_4",
            "piano_45_fa_4",
            "piano_47_sol_4",
            "piano_49_la_4",
            "piano_51_si_4",
            "piano_52_do_5",
            "piano_54_re_5",
            "piano_56_mi_5",
            "piano_57_fa_5",
            "piano_59_sol_5",
            "piano_61_la_5",
            "piano_63_si_5",
            "piano_64_do_6",
            "piano_66_re_6",
            "piano_68_mi_6",
            "piano_69_fa_6"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecoute_note_exos);

        boutDO = (Button) findViewById(R.id.buttonDO);
        boutRE = (Button) findViewById(R.id.buttonRE);
        boutMI = (Button) findViewById(R.id.buttonMI);
        boutFA = (Button) findViewById(R.id.buttonFA);
        boutSO = (Button) findViewById(R.id.buttonSO);
        boutLA = (Button) findViewById(R.id.buttonLA);
        boutSI = (Button) findViewById(R.id.buttonSI);
        boutRetour = (Button) findViewById(R.id.buttonBack);
        boutReplay = (Button) findViewById(R.id.buttonReplay);


        image = (ImageView) findViewById(R.id.imageView);
        texte = (TextView) findViewById(R.id.textReponse);
        timer = (TextView) findViewById(R.id.textTimer);
        texteScore = (TextView) findViewById(R.id.textViewScore);


        //On attribut un listener adapte aux vue qui en ont besoin
        boutDO.setOnClickListener(boutDOListener);
        boutRE.setOnClickListener(boutREListener);
        boutMI.setOnClickListener(boutMIListener);
        boutFA.setOnClickListener(boutFAListener);
        boutSO.setOnClickListener(boutSOListener);
        boutLA.setOnClickListener(boutLAListener);
        boutSI.setOnClickListener(boutSIListener);
        boutRetour.setOnClickListener(boutRetourListener);
        boutReplay.setOnClickListener(boutReplayListener);


        nbRandom = new Random();
        //nomSon = nomsSons[nbRandom.nextInt(nbSound-1)];
        //playSound(nomSon);

        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                showPopup();
            }
        },100);
    }


    /* Affiche le pop up permettant de declencher le debut du timer */
    protected void showPopup() {
        LayoutInflater layoutInflater = (LayoutInflater) EcouteNoteExos.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_window_ecoute_note, null);

        //startTime = (Button) customView.findViewById(R.id.buttonStartExoReadNote);

        start = (Button) customView.findViewById(R.id.buttonExoEcouteStart);
        texteScoreFinal = (TextView) customView.findViewById(R.id.textScoreFinal);
        textePopup = (TextView) customView.findViewById(R.id.textPopupExoEcouteNote);

        enableButtons(false);
        son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_15_si_1);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.showAtLocation(linearLayout2, Gravity.CENTER, 0, 0);

        //close the popup window on button click
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                startTimer(dureeTimer);
            }
        });

    }

    /* Fait demarrer le timer - prend en parametre le temps de fin en millisencondes */
    private void startTimer(int endTimeMillis){
        compteur = 0;
        resetGame();
        new CountDownTimer(endTimeMillis,1000){
            public void onTick(long millisUntilFinished){
                timer.setText(String.valueOf(compteur));
                compteur=compteur+1;
            }
            public void onFinish(){
                timer.setText("Temps écoulé !!");
                son.release();
                showPopup();
                String chaine2 = getResources().getString(R.string.texteScore,nbBonneReponse,nbReponse);
                texteScoreFinal.setText(chaine2);
                if (nbBonneReponse==nbReponse && nbBonneReponse!=0){
                    textePopup.setText(getResources().getString(R.string.textePopupScoreParfait));
                }else{
                    textePopup.setText(getResources().getString(R.string.textePopupEndTimer));
                }

            }
        }.start();
    }

    /* Reinitialise les nombres de reponses et de bonnes reponses */
    private void resetGame() {
        nbBonneReponse = 0;
        nbReponse = 0;
        enableButtons(true);
        selectSound();
        texte.setText("");
        texteScore.setText("Score");
    }


    private void enableButtons(boolean autorise){
        boutDO.setEnabled(autorise);
        boutRE.setEnabled(autorise);
        boutMI.setEnabled(autorise);
        boutFA.setEnabled(autorise);
        boutSO.setEnabled(autorise);
        boutLA.setEnabled(autorise);
        boutSI.setEnabled(autorise);
        boutReplay.setEnabled(autorise);
    }



    //Uniquement si le bouton DO est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutDOListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBoutonNote("do");
        }
    };
    //Uniquement si le bouton RE est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutREListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBoutonNote("re");
        }
    };
    //Uniquement si le bouton MI est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutMIListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBoutonNote("mi");
        }
    };
    //Uniquement si le bouton FA est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutFAListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBoutonNote("fa");
        }
    };
    //Uniquement si le bouton SOL est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutSOListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBoutonNote("sol");
        }
    };
    //Uniquement si le bouton LA est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutLAListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBoutonNote("la");
        }
    };
    //Uniquement si le bouton SI est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutSIListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBoutonNote("si");
        }
    };

    //Uniquement si le bouton Retour est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent ecouteNote = new Intent(EcouteNoteExos.this, EcouteNote.class);
            startActivity(ecouteNote);
            finish();
        }
    };

    //Uniquement si le bouton Replay est appuie
    // /* Reagir au clic*/
    private View.OnClickListener boutReplayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playSound(nomSon);
        }
    };

    private void actionBoutonNote(String nomNoteBout){
        String nomNoteSon = new String();
        nomNoteSon = nomSon.split("_")[2];  //recupere le nom de la note du son
        if (nomNoteBout.equals(nomNoteSon)){
            texte.setText("Bravo !");
            texte.setTextColor(Color.GREEN);
            nbBonneReponse = nbBonneReponse+1;
        }else{
            texte.setText("Mauvaise note");
            texte.setTextColor(Color.RED);
        }
        nbReponse = nbReponse+1;
        String chaine = getResources().getString(R.string.texteScore,nbBonneReponse,nbReponse);
        texteScore.setText(chaine);
        selectSound();
    }

    private void selectSound(){
        nomSon = nomsSons[nbRandom.nextInt(nbSound-1)];
        playSound(nomSon);
    }

    private void playSound (String nomImage){
        son.release();

        switch (nomImage){
            case "piano_15_si_1":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_15_si_1);
                son.start();
                break;
            case "piano_16_do_2":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_16_do_2);
                son.start();
                break;
            case "piano_18_re_2":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_18_re_2);
                son.start();
                break;
            case "piano_20_mi_2":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_20_mi_2);
                son.start();
                break;
            case "piano_21_fa_2":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_21_fa_2);
                son.start();
                break;
            case "piano_23_sol_2":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_23_sol_2);
                son.start();
                break;
            case "piano_25_la_2":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_25_la_2);
                son.start();
                break;
            case "piano_27_si_2":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_27_si_2);
                son.start();
                break;
            case "piano_28_do_3":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_28_do_3);
                son.start();
                break;
            case "piano_30_re_3":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_30_re_3);
                son.start();
                break;
            case "piano_32_mi_3":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_32_mi_3);
                son.start();
                break;
            case "piano_33_fa_3":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_33_fa_3);
                son.start();
                break;
            case "piano_35_sol_3":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_35_sol_3);
                son.start();
                break;
            case "piano_37_la_3":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_37_la_3);
                son.start();
                break;
            case "piano_39_si_3":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_39_si_3);
                son.start();
                break;
            case "piano_40_do_4":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_40_do_4);
                son.start();
                break;
            case "piano_42_re_4":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_42_re_4);
                son.start();
                break;
            case "piano_44_mi_4":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_44_mi_4);
                son.start();
                break;
            case "piano_45_fa_4":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_45_fa_4);
                son.start();
                break;
            case "piano_47_sol_4":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_47_sol_4);
                son.start();
                break;
            case "piano_49_la_4":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_49_la_4);
                son.start();
                break;
            case "piano_51_si_4":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_51_si_4);
                son.start();
                break;
            case "piano_52_do_5":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_52_do_5);
                son.start();
                break;
            case "piano_54_re_5":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_54_re_5);
                son.start();
                break;
            case "piano_56_mi_5":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_56_mi_5);
                son.start();
                break;
            case "piano_57_fa_5":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_57_fa_5);
                son.start();
                break;
            case "piano_59_sol_5":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_59_sol_5);
                son.start();
                break;
            case "piano_61_la_5":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_61_la_5);
                son.start();
                break;
            case "piano_63_si_5":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_63_si_5);
                son.start();
                break;
            case "piano_64_do_6":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_64_do_6);
                son.start();
                break;
            case "piano_66_re_6":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_66_re_6);
                son.start();
                break;
            case "piano_68_mi_6":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_68_mi_6);
                son.start();
                break;
            case "piano_69_fa_6":
                son = MediaPlayer.create(EcouteNoteExos.this,R.raw.piano_69_fa_6);
                son.start();
                break;

            default:
                break;
        }
    }
}
