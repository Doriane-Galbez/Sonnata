package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EcouteNoteCours extends AppCompatActivity {

    MediaPlayer son = null;

    ImageButton boutFAsigrave = null;
    ImageButton boutFAdograve = null;
    ImageButton boutFAregrave = null;
    ImageButton boutFAmigrave = null;
    ImageButton boutFAfagrave = null;
    ImageButton boutFAsolgrave = null;
    ImageButton boutFAlagrave = null;
    ImageButton boutFAsimed = null;
    ImageButton boutFAdomed = null;
    ImageButton boutFAremed = null;
    ImageButton boutFAmimed = null;
    ImageButton boutFAfamed = null;
    ImageButton boutFAsolmed = null;
    ImageButton boutFAlamed = null;
    ImageButton boutFAsiaigu = null;
    ImageButton boutFAdoaigu = null;
    ImageButton boutSOLremed = null;
    ImageButton boutSOLmimed = null;
    ImageButton boutSOLfamed = null;
    ImageButton boutSOLsolmed = null;
    ImageButton boutSOLlamed = null;
    ImageButton boutSOLsimed = null;
    ImageButton boutSOLdomed = null;
    ImageButton boutSOLreaigu = null;
    ImageButton boutSOLmiaigu = null;
    ImageButton boutSOLfaaigu = null;
    ImageButton boutSOLsolaigu = null;
    ImageButton boutSOLlaaigu = null;
    ImageButton boutSOLsiaigu = null;
    ImageButton boutSOLdoaigu = null;
    ImageButton boutSOLresuraigu = null;
    ImageButton boutSOLmisuraigu = null;
    ImageButton boutSOLfasuraigu = null;

    Button boutRetour = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecoute_note_cours);

        boutFAsigrave = (ImageButton) findViewById(R.id.imBoutFAsigrave);
        boutFAdograve = (ImageButton) findViewById(R.id.imBoutFAdograve);
        boutFAregrave = (ImageButton) findViewById(R.id.imBoutFAregrave);
        boutFAmigrave = (ImageButton) findViewById(R.id.imBoutFAmigrave);
        boutFAfagrave = (ImageButton) findViewById(R.id.imBoutFAfagrave);
        boutFAsolgrave = (ImageButton) findViewById(R.id.imBoutFAsolgrave);
        boutFAlagrave = (ImageButton) findViewById(R.id.imBoutFAlagrave);
        boutFAsimed = (ImageButton) findViewById(R.id.imBoutFAsimed);
        boutFAdomed = (ImageButton) findViewById(R.id.imBoutFAdomed);
        boutFAremed = (ImageButton) findViewById(R.id.imBoutFAremed);
        boutFAmimed = (ImageButton) findViewById(R.id.imBoutFAmimed);
        boutFAfamed = (ImageButton) findViewById(R.id.imBoutFAfamed);
        boutFAsolmed = (ImageButton) findViewById(R.id.imBoutFAsolmed);
        boutFAlamed = (ImageButton) findViewById(R.id.imBoutFAlamed);
        boutFAsiaigu = (ImageButton) findViewById(R.id.imBoutFAsiaigu);
        boutFAdoaigu = (ImageButton) findViewById(R.id.imBoutFAdoaigu);
        boutSOLremed = (ImageButton) findViewById(R.id.imBoutSOLremed);
        boutSOLmimed = (ImageButton) findViewById(R.id.imBoutSOLmimed);
        boutSOLfamed = (ImageButton) findViewById(R.id.imBoutSOLfamed);
        boutSOLsolmed = (ImageButton) findViewById(R.id.imBoutSOLsolmed);
        boutSOLlamed = (ImageButton) findViewById(R.id.imBoutSOLlamed);
        boutSOLsimed = (ImageButton) findViewById(R.id.imBoutSOLsimed);
        boutSOLdomed = (ImageButton) findViewById(R.id.imBoutSOLdomed);
        boutSOLreaigu = (ImageButton) findViewById(R.id.imBoutSOLreaigu);
        boutSOLmiaigu = (ImageButton) findViewById(R.id.imBoutSOLmiaigu);
        boutSOLfaaigu = (ImageButton) findViewById(R.id.imBoutSOLfaaigu);
        boutSOLsolaigu = (ImageButton) findViewById(R.id.imBoutSOLsolaigu);
        boutSOLlaaigu = (ImageButton) findViewById(R.id.imBoutSOLlaaigu);
        boutSOLsiaigu = (ImageButton) findViewById(R.id.imBoutSOLsiaigu);
        boutSOLdoaigu = (ImageButton) findViewById(R.id.imBoutSOLdoaigu);
        boutSOLresuraigu = (ImageButton) findViewById(R.id.imBoutSOLresuraigu);
        boutSOLmisuraigu = (ImageButton) findViewById(R.id.imBoutSOLmisuraigu);
        boutSOLfasuraigu = (ImageButton) findViewById(R.id.imBoutSOLfasuraigu);

        boutRetour = (Button) findViewById(R.id.boutonRetour);


        boutFAsigrave.setOnClickListener(boutFAsigraveListener);
        boutFAdograve.setOnClickListener(boutFAdograveListener);
        boutFAregrave.setOnClickListener(boutFAregraveListener);
        boutFAmigrave.setOnClickListener(boutFAmigraveListener);
        boutFAfagrave.setOnClickListener(boutFAfagraveListener);
        boutFAsolgrave.setOnClickListener(boutFAsolgraveListener);
        boutFAlagrave.setOnClickListener(boutFAlagraveListener);
        boutFAsimed.setOnClickListener(boutFAsimedListener);
        boutFAdomed.setOnClickListener(boutFAdomedListener);
        boutFAremed.setOnClickListener(boutFAremedListener);
        boutFAmimed.setOnClickListener(boutFAmimedListener);
        boutFAfamed.setOnClickListener(boutFAfamedListener);
        boutFAsolmed.setOnClickListener(boutFAsolmedListener);
        boutFAlamed.setOnClickListener(boutFAlamedListener);
        boutFAsiaigu.setOnClickListener(boutFAsiaiguListener);
        boutFAdoaigu.setOnClickListener(boutFAdoaiguListener);
        boutSOLremed.setOnClickListener(boutSOLremedListener);
        boutSOLmimed.setOnClickListener(boutSOLmimedListener);
        boutSOLfamed.setOnClickListener(boutSOLfamedListener);
        boutSOLsolmed.setOnClickListener(boutSOLsolmedListener);
        boutSOLlamed.setOnClickListener(boutSOLlamedListener);
        boutSOLsimed.setOnClickListener(boutSOLsimedListener);
        boutSOLdomed.setOnClickListener(boutSOLdomedListener);
        boutSOLreaigu.setOnClickListener(boutSOLreaiguListener);
        boutSOLmiaigu.setOnClickListener(boutSOLmiaiguListener);
        boutSOLfaaigu.setOnClickListener(boutSOLfaaiguListener);
        boutSOLsolaigu.setOnClickListener(boutSOLsolaiguListener);
        boutSOLlaaigu.setOnClickListener(boutSOLlaaiguListener);
        boutSOLsiaigu.setOnClickListener(boutSOLsiaiguListener);
        boutSOLdoaigu.setOnClickListener(boutSOLdoaiguListener);
        boutSOLresuraigu.setOnClickListener(boutSOLresuraiguListener);
        boutSOLmisuraigu.setOnClickListener(boutSOLmisuraiguListener);
        boutSOLfasuraigu.setOnClickListener(boutSOLfasuraiguListener);

        boutRetour.setOnClickListener(boutRetourListener);
        son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_15_si_1);
    }

    //Uniquement si le bouton Retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            Intent ecouteNote = new Intent(EcouteNoteCours.this, EcouteNote.class);
            startActivity(ecouteNote);
            finish();
        }
    };


    private View.OnClickListener boutFAsigraveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_15_si_1);
            son.start();
        }
    };

    private View.OnClickListener boutFAdograveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_16_do_2);
            son.start();
        }
    };

    private View.OnClickListener boutFAregraveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_18_re_2);
            son.start();
        }
    };

    private View.OnClickListener boutFAmigraveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_20_mi_2);
            son.start();
        }
    };

    private View.OnClickListener boutFAfagraveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_21_fa_2);
            son.start();
        }
    };

    private View.OnClickListener boutFAsolgraveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_23_sol_2);
            son.start();
        }
    };

    private View.OnClickListener boutFAlagraveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_25_la_2);
            son.start();
        }
    };

    private View.OnClickListener boutFAsimedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_27_si_2);
            son.start();
        }
    };

    private View.OnClickListener boutFAdomedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_28_do_3);
            son.start();
        }
    };

    private View.OnClickListener boutFAremedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_30_re_3);
            son.start();
        }
    };

    private View.OnClickListener boutFAmimedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_32_mi_3);
            son.start();
        }
    };

    private View.OnClickListener boutFAfamedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_33_fa_3);
            son.start();
        }
    };

    private View.OnClickListener boutFAsolmedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_35_sol_3);
            son.start();
        }
    };

    private View.OnClickListener boutFAlamedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_37_la_3);
            son.start();
        }
    };

    private View.OnClickListener boutFAsiaiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_39_si_3);
            son.start();
        }
    };

    private View.OnClickListener boutFAdoaiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_40_do_4);
            son.start();
        }
    };

    private View.OnClickListener boutSOLremedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_42_re_4);
            son.start();
        }
    };

    private View.OnClickListener boutSOLmimedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_44_mi_4);
            son.start();
        }
    };

    private View.OnClickListener boutSOLfamedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_45_fa_4);
            son.start();
        }
    };

    private View.OnClickListener boutSOLsolmedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_47_sol_4);
            son.start();
        }
    };

    private View.OnClickListener boutSOLlamedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_49_la_4);
            son.start();
        }
    };

    private View.OnClickListener boutSOLsimedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_51_si_4);
            son.start();
        }
    };

    private View.OnClickListener boutSOLdomedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_52_do_5);
            son.start();
        }
    };

    private View.OnClickListener boutSOLreaiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_54_re_5);
            son.start();
        }
    };

    private View.OnClickListener boutSOLmiaiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_56_mi_5);
            son.start();
        }
    };

    private View.OnClickListener boutSOLfaaiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_57_fa_5);
            son.start();
        }
    };

    private View.OnClickListener boutSOLsolaiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_59_sol_5);
            son.start();
        }
    };

    private View.OnClickListener boutSOLlaaiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_61_la_5);
            son.start();
        }
    };

    private View.OnClickListener boutSOLsiaiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_63_si_5);
            son.start();
        }
    };

    private View.OnClickListener boutSOLdoaiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_64_do_6);
            son.start();
        }
    };

    private View.OnClickListener boutSOLresuraiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_66_re_6);
            son.start();
        }
    };

    private View.OnClickListener boutSOLmisuraiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_68_mi_6);
            son.start();
        }
    };

    private View.OnClickListener boutSOLfasuraiguListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            son.release();
            son = MediaPlayer.create(EcouteNoteCours.this,R.raw.piano_69_fa_6);
            son.start();
        }
    };

}
