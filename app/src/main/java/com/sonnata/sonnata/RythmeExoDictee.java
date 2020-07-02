package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

public class RythmeExoDictee extends AppCompatActivity {
    String[] suiteRep = new String[3];
    String[] suiteSol = new String[3];

    int numForNiv = 5;    //entier permettant "d'activer" plus ou moins de bouton suivant le niveau
    int niv = 1;          //numero du niveau
    int cptRep = 0;      //compteur permettant de se reperer dans l'ordre des reponses donnees
    int nbBonneRep = 0;  //nombre de bonnes reponses donnees par l'utilisateur
    int nbRep = 0;
    int cptSeq = 0;      //compteur permettant de se reperer pour savoir si le nombre max de sequence a ete atteind
    int nbMaxSeq = 10;   //nombre de sequences qui seront definit

    Random nbRandom = null;
    PopupWindow popupWindow;
    ScrollView linearLayout2;

    MediaPlayer son = null;

    Button boutNiv1 = null;   //bouton du popup
    Button boutNiv2 = null;   //bouton du popup
    Button boutNiv3 = null;   //bouton du popup

    Button boutRetour = null;
    Button boutReplay = null;
    ImageButton boutRonde = null;
    ImageButton boutBlanche = null;
    ImageButton boutNoire = null;
    ImageButton bout2croches = null;
    ImageButton bout4doublesCroches = null;
    ImageButton boutNoirePoint = null;
    ImageButton boutCrocheP = null;
    ImageButton boutDoubleCrocheP = null;
    ImageButton boutTriolet = null;
    ImageButton boutCroche2doubles = null;
    ImageButton bout2doublesCroche = null;
    ImageButton boutSyncope = null;
    ImageButton boutSyncopette = null;

    TextView texteScore = null;
    TextView texteScoreFinal = null;
    TextView textePopup = null;
    TextView texteNumSequence = null;

    String[] bibliSound = {
            "piano_la_440_ronde",
            "piano_la_440_blanche",
            "piano_la_440_noire",
            "piano_la_440_deux_croches",
            "piano_la_440_quatre_double_croches",
            "piano_la_440_noire_pointee_croche",
            "piano_la_440_croche_pointee_double",
            "piano_la_440_croche_deux_doubles",
            "piano_la_440_syncope",
            "piano_la_440_double_croche_pointee",
            "piano_la_440_triolet",
            "piano_la_440_deux_doubles_croche",
            "piano_la_440_syncopette"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rythme_exo_dictee);

        boutRetour = (Button) findViewById(R.id.buttonRetour);
        boutReplay = (Button) findViewById(R.id.buttonReplay);
        boutRonde = (ImageButton) findViewById(R.id.imageButtonRonde);
        boutBlanche = (ImageButton) findViewById(R.id.imageButtonBlanche);
        boutNoire = (ImageButton) findViewById(R.id.imageButtonNoire);
        bout2croches = (ImageButton) findViewById(R.id.imageButton2Croches);
        bout4doublesCroches = (ImageButton) findViewById(R.id.imageButton4DoublesCroches);
        boutNoirePoint = (ImageButton) findViewById(R.id.imageButtonNoirePoint);
        boutCrocheP = (ImageButton) findViewById(R.id.imageButtonCrochePoint);
        boutDoubleCrocheP = (ImageButton) findViewById(R.id.imageButtonDoubleCrochePoint);
        boutTriolet = (ImageButton) findViewById(R.id.imageButtonTriolet);
        boutCroche2doubles = (ImageButton) findViewById(R.id.imageButtonCroche2doubles);
        bout2doublesCroche = (ImageButton) findViewById(R.id.imageButton2doublesCroche);
        boutSyncope = (ImageButton) findViewById(R.id.imageButtonSyncope);
        boutSyncopette = (ImageButton) findViewById(R.id.imageButtonSyncopette);

        texteScore = (TextView) findViewById(R.id.textViewScore);
        texteNumSequence = (TextView) findViewById(R.id.textViewNumSeq);


        boutRetour.setOnClickListener(boutRetourListener);
        boutReplay.setOnClickListener(boutReplayListener);
        boutRonde.setOnClickListener(boutRondeListener);
        boutBlanche.setOnClickListener(boutBlancheListener);
        boutNoire.setOnClickListener(boutNoireListener);
        bout2croches.setOnClickListener(bout2crochesListener);
        bout4doublesCroches.setOnClickListener(bout4doublesCrochesListener);
        boutNoirePoint.setOnClickListener(boutNoirePointListener);
        boutCrocheP.setOnClickListener(boutCrochePointListener);
        boutDoubleCrocheP.setOnClickListener(boutDoubleCrochePointListener);
        boutTriolet.setOnClickListener(boutTrioletListener);
        boutCroche2doubles.setOnClickListener(boutCroche2doublesListener);
        bout2doublesCroche.setOnClickListener(bout2doublesCrocheListener);
        boutSyncope.setOnClickListener(boutSyncopeListener);
        boutSyncopette.setOnClickListener(boutSyncopetteListener);

        nbRandom = new Random();

        linearLayout2 = (ScrollView) findViewById(R.id.linearLayout2);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                showPopup();
            }
        },100);

    }


    /* Affiche le pop up permettant de declencher le debut de la premiere sequence */
    protected void showPopup() {
        LayoutInflater layoutInflater = (LayoutInflater) RythmeExoDictee.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_rythme_exo_dictee, null);

        //startTime = (Button) customView.findViewById(R.id.buttonStartExoReadNote);

        boutNiv1 = (Button) customView.findViewById(R.id.buttonRythmeExoDicteeN1);
        boutNiv2 = (Button) customView.findViewById(R.id.buttonRythmeExoDicteeN2);
        boutNiv3 = (Button) customView.findViewById(R.id.buttonRythmeExoDicteeN3);
        texteScoreFinal = (TextView) customView.findViewById(R.id.textScoreFinal);
        textePopup = (TextView) customView.findViewById(R.id.textPopupExoEcouteNote);

        enableButtons(false,true);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.showAtLocation(linearLayout2, Gravity.CENTER, 0, 0);


        //close the popup window on button click
        boutNiv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                niv = 1;
                numForNiv = 5;
                start();
            }
        });
        boutNiv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                niv = 2;
                numForNiv = 9;
                start();
            }
        });
        boutNiv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                niv = 3;
                numForNiv = 13;
                start();
            }
        });

    }

    private void start(){
        cptRep = 0;      //compteur permettant de se reperer dans l'ordre des reponses donnees
        nbBonneRep = 0;  //nombre de bonnes reponses donnees par l'utilisateur
        nbRep = 0;
        cptSeq = 0;      //compteur permettant de se reperer pour savoir si le nombre max de sequence a ete atteind
        texteScore.setText("Score");   //affiche le score
        texteNumSequence.setText("Séquence n°1");
        popupWindow.dismiss();
        enableButtons(true, false);
        createSequence();
        playSeq();
    }

    private void enableButtons (boolean autorise, boolean all){  //all=true => sur tous les boutons
        if (all==true){
            boutReplay.setEnabled(autorise);
            boutRonde.setEnabled(autorise);
            boutBlanche.setEnabled(autorise);
            boutNoire.setEnabled(autorise);
            bout2croches.setEnabled(autorise);
            bout4doublesCroches.setEnabled(autorise);
            boutNoirePoint.setEnabled(autorise);
            boutCrocheP.setEnabled(autorise);
            boutDoubleCrocheP.setEnabled(autorise);
            boutTriolet.setEnabled(autorise);
            boutCroche2doubles.setEnabled(autorise);
            bout2doublesCroche.setEnabled(autorise);
            boutSyncope.setEnabled(autorise);
            boutSyncopette.setEnabled(autorise);

            if (autorise==false){
                boutRonde.setImageResource(R.drawable.figure_rythmique_ronde_clair);
                boutBlanche.setImageResource(R.drawable.figure_rythmique_blanche_clair);
                boutNoire.setImageResource(R.drawable.figure_rythmique_noire_clair);
                bout2croches.setImageResource(R.drawable.figure_rythmique_deux_croches_clair);
                bout4doublesCroches.setImageResource(R.drawable.figure_rythmique_quatre_double_croches_clair);
                boutNoirePoint.setImageResource(R.drawable.figure_rythmique_noire_pointee_croche_claire);
                boutCrocheP.setImageResource(R.drawable.figure_rythmique_croche_pointee_double_claire);
                boutDoubleCrocheP.setImageResource(R.drawable.figure_rythmique_double_croche_pointee_claire);
                boutTriolet.setImageResource(R.drawable.figure_rythmique_triolet_claire);
                boutCroche2doubles.setImageResource(R.drawable.figure_rythmique_croche_deux_doubles_claire);
                bout2doublesCroche.setImageResource(R.drawable.figure_rythmique_deux_doubles_croche_claire);
                boutSyncope.setImageResource(R.drawable.figure_rythmique_syncope_claire);
                boutSyncopette.setImageResource(R.drawable.figure_rythmique_syncopette_claire);
            }else{
                boutRonde.setImageResource(R.drawable.figure_rythmique_ronde);
                boutBlanche.setImageResource(R.drawable.figure_rythmique_blanche);
                boutNoire.setImageResource(R.drawable.figure_rythmique_noire);
                bout2croches.setImageResource(R.drawable.figure_rythmique_deux_croches);
                bout4doublesCroches.setImageResource(R.drawable.figure_rythmique_quatre_double_croches);
                boutNoirePoint.setImageResource(R.drawable.figure_rythmique_noire_pointee_croche);
                boutCrocheP.setImageResource(R.drawable.figure_rythmique_croche_pointee_double);
                boutDoubleCrocheP.setImageResource(R.drawable.figure_rythmique_double_croche_pointee);
                boutTriolet.setImageResource(R.drawable.figure_rythmique_triolet);
                boutCroche2doubles.setImageResource(R.drawable.figure_rythmique_croche_deux_doubles);
                bout2doublesCroche.setImageResource(R.drawable.figure_rythmique_deux_doubles_croche);
                boutSyncope.setImageResource(R.drawable.figure_rythmique_syncope);
                boutSyncopette.setImageResource(R.drawable.figure_rythmique_syncopette);
            }
        }
        else{
            boutReplay.setEnabled(autorise);
            boutRonde.setEnabled(autorise);
            boutBlanche.setEnabled(autorise);
            boutNoire.setEnabled(autorise);
            bout2croches.setEnabled(autorise);
            bout4doublesCroches.setEnabled(autorise);

            if (niv!=1){
                boutNoirePoint.setEnabled(autorise);
                boutCrocheP.setEnabled(autorise);
                boutCroche2doubles.setEnabled(autorise);
                boutSyncope.setEnabled(autorise);

                if (niv==3){
                    boutDoubleCrocheP.setEnabled(autorise);
                    boutTriolet.setEnabled(autorise);
                    bout2doublesCroche.setEnabled(autorise);
                    boutSyncopette.setEnabled(autorise);
                }
            }

            if (autorise==false){
                boutRonde.setImageResource(R.drawable.figure_rythmique_ronde_clair);
                boutBlanche.setImageResource(R.drawable.figure_rythmique_blanche_clair);
                boutNoire.setImageResource(R.drawable.figure_rythmique_noire_clair);
                bout2croches.setImageResource(R.drawable.figure_rythmique_deux_croches_clair);
                bout4doublesCroches.setImageResource(R.drawable.figure_rythmique_quatre_double_croches_clair);

                if (niv!=1) {
                    boutNoirePoint.setImageResource(R.drawable.figure_rythmique_noire_pointee_croche_claire);
                    boutCrocheP.setImageResource(R.drawable.figure_rythmique_croche_pointee_double_claire);
                    boutCroche2doubles.setImageResource(R.drawable.figure_rythmique_croche_deux_doubles_claire);
                    boutSyncope.setImageResource(R.drawable.figure_rythmique_syncope_claire);

                    if (niv==3){
                        boutDoubleCrocheP.setImageResource(R.drawable.figure_rythmique_double_croche_pointee_claire);
                        boutTriolet.setImageResource(R.drawable.figure_rythmique_triolet_claire);
                        bout2doublesCroche.setImageResource(R.drawable.figure_rythmique_deux_doubles_croche_claire);
                        boutSyncopette.setImageResource(R.drawable.figure_rythmique_syncopette_claire);
                    }
                }
            }else{
                boutRonde.setImageResource(R.drawable.figure_rythmique_ronde);
                boutBlanche.setImageResource(R.drawable.figure_rythmique_blanche);
                boutNoire.setImageResource(R.drawable.figure_rythmique_noire);
                bout2croches.setImageResource(R.drawable.figure_rythmique_deux_croches);
                bout4doublesCroches.setImageResource(R.drawable.figure_rythmique_quatre_double_croches);

                if (niv!=1){
                    boutNoirePoint.setImageResource(R.drawable.figure_rythmique_noire_pointee_croche);
                    boutCrocheP.setImageResource(R.drawable.figure_rythmique_croche_pointee_double);
                    boutCroche2doubles.setImageResource(R.drawable.figure_rythmique_croche_deux_doubles);
                    boutSyncope.setImageResource(R.drawable.figure_rythmique_syncope);

                    if (niv==3){
                        boutDoubleCrocheP.setImageResource(R.drawable.figure_rythmique_double_croche_pointee);
                        boutTriolet.setImageResource(R.drawable.figure_rythmique_triolet);
                        bout2doublesCroche.setImageResource(R.drawable.figure_rythmique_deux_doubles_croche);
                        boutSyncopette.setImageResource(R.drawable.figure_rythmique_syncopette);
                    }
                }
            }
        }

    }

    // cree une liste aleatoire permettant de definir une sequence de sons
    private void createSequence(){
        for (int i=0; i<3;i++){
            suiteSol[i] = bibliSound[nbRandom.nextInt(numForNiv)];
        }
    }

    // cree le son correspondant au nom du fichier audio en parametre
    private MediaPlayer createSon(String soundName){
        switch (soundName){
            case "piano_la_440_ronde":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_ronde));
            case "piano_la_440_blanche":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_blanche));
            case "piano_la_440_noire":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_noire));
            case "piano_la_440_deux_croches":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_deux_croches));
            case "piano_la_440_quatre_double_croches":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_quatre_double_croches));
            case "piano_la_440_noire_pointee_croche":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_noire_pointee_croche));
            case "piano_la_440_croche_pointee_double":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_croche_pointee_double));
            case "piano_la_440_double_croche_pointee":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_double_croche_pointee));
            case "piano_la_440_triolet":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_triolet));
            case "piano_la_440_croche_deux_doubles":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_croche_deux_doubles));
            case "piano_la_440_deux_doubles_croche":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_deux_doubles_croche));
            case "piano_la_440_syncope":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_syncope));
            case "piano_la_440_syncopette":
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_syncopette));
            default:
                return (MediaPlayer.create( RythmeExoDictee.this, R.raw.piano_la_440_noire));  //retourne un son par defaut si aucune correspondance n'est trouvee pour eviter le plantage
        }
    }

    //joue une sequence de sons les uns apres les autres
    private void playSeq(){
                enableButtons(false,false);
                son = createSon(suiteSol[0]);
                son.start();
                son.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer son) {
                        son.release();
                        son = createSon(suiteSol[1]);
                        son.start();
                        son.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer son) {
                                son.release();
                                son = createSon(suiteSol[2]);
                                son.start();
                                son.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer son) {
                                        son.release();
                                        son = null;
                                        enableButtons(true,false);
                                    }
                                });
                            }
                        });
                    }
                });
    }

    private void checkFin () {
        cptSeq = cptSeq + 1;
        texteNumSequence.setText("Séquence n°"+(cptSeq+1));

        if (cptSeq>=nbMaxSeq){
            showPopup();
            texteScoreFinal.setText(getResources().getString(R.string.texteScore,nbBonneRep,nbRep));
            if (nbBonneRep==nbRep && nbBonneRep!=0){
                textePopup.setText(getResources().getString(R.string.textePopupParfaitRythmeExoDictee));
            }else{
                textePopup.setText(getResources().getString(R.string.textePopupEndRythmeExoDictee));
            }
        }
        else {
            createSequence();
            playSeq();
        }
    }

    private void seqSuivante () {
        if (cptRep>=3) {
            checkFin();
        }
    }

    //Uniquement si le bouton Retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent menu = new Intent(RythmeExoDictee.this, Rythme.class);
            startActivity(menu);
            finish();
        }
    };

    //Uniquement si le bouton Replay est appuie
    private View.OnClickListener boutReplayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playSeq();
        }
    };

    // definit les actions a effetcuer en cas d'appuie sur un des boutons reponse
    private void actionBouton(String nomRythme){
        if (cptRep>=3){
            cptRep = 0;
        }
        suiteRep[cptRep] = nomRythme;
        if (suiteRep[cptRep].equals(suiteSol[cptRep])){
            nbBonneRep = nbBonneRep + 1;
        }
        nbRep = nbRep + 1;
        texteScore.setText(getResources().getString(R.string.texteScore,nbBonneRep,nbRep));   //affiche le score
        cptRep = cptRep + 1;
        seqSuivante();
    }

    //Uniquement si le bouton Ronde est appuie
    private View.OnClickListener boutRondeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_ronde");
        }
    };


    //Uniquement si le bouton Blanche est appuie
    private View.OnClickListener boutBlancheListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_blanche");
        }
    };


    //Uniquement si le bouton Noire est appuie
    private View.OnClickListener boutNoireListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_noire");
        }
    };


    //Uniquement si le bouton 2croches est appuie
    private View.OnClickListener bout2crochesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_deux_croches");
        }
    };


    //Uniquement si le bouton 4doubles_croches est appuie
    private View.OnClickListener bout4doublesCrochesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_quatre_double_croches");
        }
    };


    //Uniquement si le bouton 4doubles_croches est appuie
    private View.OnClickListener boutNoirePointListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_noire_pointee_croche");
        }
    };


    //Uniquement si le bouton croche_pointee_double est appuie
    private View.OnClickListener boutCrochePointListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_croche_pointee_double");
        }
    };


    //Uniquement si le bouton double_croche_pointee est appuie
    private View.OnClickListener boutDoubleCrochePointListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_double_croche_pointee");
        }
    };


    //Uniquement si le bouton triolet est appuie
    private View.OnClickListener boutTrioletListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_triolet");
        }
    };


    //Uniquement si le bouton croche_2doubles est appuie
    private View.OnClickListener boutCroche2doublesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_croche_deux_doubles");
        }
    };


    //Uniquement si le bouton 2doubles_croche est appuie
    private View.OnClickListener bout2doublesCrocheListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_deux_doubles_croche");
        }
    };


    //Uniquement si le bouton syncope est appuie
    private View.OnClickListener boutSyncopeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_syncope");
        }
    };


    //Uniquement si le bouton syncopette est appuie
    private View.OnClickListener boutSyncopetteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actionBouton("piano_la_440_syncopette");
        }
    };

}
