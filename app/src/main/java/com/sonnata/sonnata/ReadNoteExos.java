package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import java.util.Random;

public class ReadNoteExos extends AppCompatActivity {

    int nbImage = 42;
    int nbReponse = 0;
    int nbBonneReponse = 0;
    int compteur = 0;
    int niveau = 0;
    int dureeTimer = 90000; //duree du timer en millisecondes -> 1 min 30 s

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

    Button niv1 = null;
    Button niv2 = null;
    Button niv3 = null;

    ImageView image = null;
    TextView texte = null;
    TextView timer = null;
    TextView texteScore = null;
    TextView texteScoreFinal = null;
    TextView textePopup = null;

    Random nbRandom = null;

    String nomImage = null;

    String[] nomsImages = {"cle_de_sol_do_aigu",
                            "cle_de_sol_do_grave",
                            "cle_de_sol_do_med",
                            "cle_de_sol_fa_aigu",
                            "cle_de_sol_fa_med",
                            "cle_de_sol_fa_suraigu",
                            "cle_de_sol_la_aigu",
                            "cle_de_sol_la_grave",
                            "cle_de_sol_la_med",
                            "cle_de_sol_mi_aigu",
                            "cle_de_sol_mi_med",
                            "cle_de_sol_mi_suraigu",
                            "cle_de_sol_re_aigu",
                            "cle_de_sol_re_med",
                            "cle_de_sol_re_suraigu",
                            "cle_de_sol_si_aigu",
                            "cle_de_sol_si_grave",
                            "cle_de_sol_si_med",
                            "cle_de_sol_sol_aigu",
                            "cle_de_sol_sol_grave",
                            "cle_de_sol_sol_med",
                            "cle_de_fa_do_aigu",
                            "cle_de_fa_do_grave",
                            "cle_de_fa_do_med",
                            "cle_de_fa_fa_aigu",
                            "cle_de_fa_fa_grave",
                            "cle_de_fa_fa_med",
                            "cle_de_fa_la_aigu",
                            "cle_de_fa_la_grave",
                            "cle_de_fa_la_med",
                            "cle_de_fa_mi_aigu",
                            "cle_de_fa_mi_grave",
                            "cle_de_fa_mi_med",
                            "cle_de_fa_re_aigu",
                            "cle_de_fa_re_grave",
                            "cle_de_fa_re_med",
                            "cle_de_fa_si_aigu",
                            "cle_de_fa_si_grave",
                            "cle_de_fa_si_med",
                            "cle_de_fa_sol_aigu",
                            "cle_de_fa_sol_grave",
                            "cle_de_fa_sol_med"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note_exos);

        boutDO = (Button) findViewById(R.id.buttonDO);
        boutRE = (Button) findViewById(R.id.buttonRE);
        boutMI = (Button) findViewById(R.id.buttonMI);
        boutFA = (Button) findViewById(R.id.buttonFA);
        boutSO = (Button) findViewById(R.id.buttonSO);
        boutLA = (Button) findViewById(R.id.buttonLA);
        boutSI = (Button) findViewById(R.id.buttonSI);
        boutRetour = (Button) findViewById(R.id.buttonBack);


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


        nbRandom = new Random();
        nomImage = nomsImages[nbRandom.nextInt(nbImage-1)];
        afficheImage(nomImage);

        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                showPopup();
            }
        },100);
    }


    /* Affiche le pop up permettant de declencher le debut du timer */
    protected void showPopup() {
        LayoutInflater layoutInflater = (LayoutInflater) ReadNoteExos.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_window_start_timer, null);

        //startTime = (Button) customView.findViewById(R.id.buttonStartExoReadNote);

        niv1 = (Button) customView.findViewById(R.id.buttonExoReadNoteN1);
        niv2 = (Button) customView.findViewById(R.id.buttonExoReadNoteN2);
        niv3 = (Button) customView.findViewById(R.id.buttonExoReadNoteN3);
        texteScoreFinal = (TextView) customView.findViewById(R.id.textScoreFinal);
        textePopup = (TextView) customView.findViewById(R.id.textPopupExoReadNote);

        enableButtons(false);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.showAtLocation(linearLayout2, Gravity.CENTER, 0, 0);

        //close the popup window on button click
        niv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                niveau = 1;
                startTimer(dureeTimer);
            }
        });

        niv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                niveau = 2;
                startTimer(dureeTimer);
            }
        });

        niv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                niveau = 3;
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
        selectImage(niveau);
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
            Intent mainActi = new Intent(ReadNoteExos.this, ReadNote.class);
            startActivity(mainActi);
            finish();
        }
    };

    private void actionBoutonNote(String nomNoteBout){
        String nomNoteImage = new String();
        nomNoteImage = nomImage.split("_")[3];  //recupere le nom de la note de l'image
        if (nomNoteBout.equals(nomNoteImage)){
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
        //nomImage = nomsImages[nbRandom.nextInt(nbImage-1)];
        //afficheImage(nomImage);
        selectImage(niveau);
    }

    private void selectImage(int niveau){
        switch (niveau){
            case 1:
                nomImage = nomsImages[nbRandom.nextInt(nbImage/2-1)];
                break;
            case 2:
                nomImage = nomsImages[nbImage/2+nbRandom.nextInt(nbImage/2-1)];
                break;
            case 3:
                nomImage = nomsImages[nbRandom.nextInt(nbImage-1)];
                break;
        }
        afficheImage(nomImage);
    }

    private void afficheImage (String nomImage){

        switch (nomImage){
            case "cle_de_sol_do_aigu":
                image.setImageResource(R.drawable.cle_de_sol_do_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_do_grave":
                image.setImageResource(R.drawable.cle_de_sol_do_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_do_med":
                image.setImageResource(R.drawable.cle_de_sol_do_med);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_fa_aigu":
                image.setImageResource(R.drawable.cle_de_sol_fa_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_fa_med":
                image.setImageResource(R.drawable.cle_de_sol_fa_med);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_fa_suraigu":
                image.setImageResource(R.drawable.cle_de_sol_fa_suraigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_la_aigu":
                image.setImageResource(R.drawable.cle_de_sol_la_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_la_grave":
                image.setImageResource(R.drawable.cle_de_sol_la_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_la_med":
                image.setImageResource(R.drawable.cle_de_sol_la_med);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_mi_aigu":
                image.setImageResource(R.drawable.cle_de_sol_mi_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_mi_med":
                image.setImageResource(R.drawable.cle_de_sol_mi_med);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_mi_suraigu":
                image.setImageResource(R.drawable.cle_de_sol_mi_suraigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_re_aigu":
                image.setImageResource(R.drawable.cle_de_sol_re_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_re_med":
                image.setImageResource(R.drawable.cle_de_sol_re_med);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_re_suraigu":
                image.setImageResource(R.drawable.cle_de_sol_re_suraigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_si_aigu":
                image.setImageResource(R.drawable.cle_de_sol_si_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_si_grave":
                image.setImageResource(R.drawable.cle_de_sol_si_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_si_med":
                image.setImageResource(R.drawable.cle_de_sol_si_med);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_sol_aigu":
                image.setImageResource(R.drawable.cle_de_sol_sol_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_sol_grave":
                image.setImageResource(R.drawable.cle_de_sol_sol_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_sol_sol_med":
                image.setImageResource(R.drawable.cle_de_sol_sol_med);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_do_aigu":
                image.setImageResource(R.drawable.cle_de_fa_do_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_do_grave":
                image.setImageResource(R.drawable.cle_de_fa_do_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_do_med":
                image.setImageResource(R.drawable.cle_de_fa_do_med);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_fa_aigu":
                image.setImageResource(R.drawable.cle_de_fa_fa_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_fa_grave":
                image.setImageResource(R.drawable.cle_de_fa_fa_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_fa_med":
                image.setImageResource(R.drawable.cle_de_fa_fa_med);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_la_aigu":
                image.setImageResource(R.drawable.cle_de_fa_la_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_la_grave":
                image.setImageResource(R.drawable.cle_de_fa_la_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_la_med":
                image.setImageResource(R.drawable.cle_de_fa_la_med);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_mi_aigu":
                image.setImageResource(R.drawable.cle_de_fa_mi_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_mi_grave":
                image.setImageResource(R.drawable.cle_de_fa_mi_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_mi_med":
                image.setImageResource(R.drawable.cle_de_fa_mi_med);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_re_aigu":
                image.setImageResource(R.drawable.cle_de_fa_re_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_re_grave":
                image.setImageResource(R.drawable.cle_de_fa_re_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_re_med":
                image.setImageResource(R.drawable.cle_de_fa_re_med);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_si_aigu":
                image.setImageResource(R.drawable.cle_de_fa_si_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_si_grave":
                image.setImageResource(R.drawable.cle_de_fa_si_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_si_med":
                image.setImageResource(R.drawable.cle_de_fa_si_med);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_sol_aigu":
                image.setImageResource(R.drawable.cle_de_fa_sol_aigu);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_sol_grave":
                image.setImageResource(R.drawable.cle_de_fa_sol_grave);  //permet de changer l'image affichee
                break;
            case "cle_de_fa_sol_med":
                image.setImageResource(R.drawable.cle_de_fa_sol_med);  //permet de changer l'image affichee
                break;
            default:
                break;
        }
    }
}
