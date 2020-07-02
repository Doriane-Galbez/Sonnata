package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuCours extends AppCompatActivity {
    Button boutRetour = null;
    Button boutAlterations = null;
    Button boutTons = null;
    Button boutMesure = null;
    Button boutSilences = null;
    Button boutNuances = null;
    Button boutTempi = null;
    Button boutVarTemp = null;
    Button boutArti = null;
    Button boutReprises = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cours);

        boutRetour = (Button) findViewById(R.id.buttonRetour);
        boutAlterations = (Button) findViewById(R.id.buttonAlterations);
        boutTons = (Button) findViewById(R.id.buttonTons);
        boutMesure = (Button) findViewById(R.id.buttonMesure);
        boutSilences = (Button) findViewById(R.id.buttonSilences);
        boutNuances = (Button) findViewById(R.id.buttonNuances);
        boutTempi = (Button) findViewById(R.id.buttonTempi);
        boutVarTemp = (Button) findViewById(R.id.buttonVarTemp);
        boutArti = (Button) findViewById(R.id.buttonArticulations);
        boutReprises = (Button) findViewById(R.id.buttonReprises);

        boutRetour.setOnClickListener(boutRetourListener);
        boutAlterations.setOnClickListener(boutAlterationsListener);
        boutTons.setOnClickListener(boutTonsListener);
        boutMesure.setOnClickListener(boutMesureListener);
        boutSilences.setOnClickListener(boutSilencesListener);
        boutNuances.setOnClickListener(boutNuancesListener);
        boutTempi.setOnClickListener(boutTempiListener);
        boutVarTemp.setOnClickListener(boutVarTempListener);
        boutArti.setOnClickListener(boutArtiListener);
        boutReprises.setOnClickListener(boutReprisesListener);
    }
    //Uniquement si le bouton Retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent menu = new Intent(MenuCours.this, MainActivity.class);
            startActivity(menu);
            finish();
        }
    };

    //Uniquement si le bouton Alterations est appuie
    private View.OnClickListener boutAlterationsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MenuCours.this, CoursSupAlterations.class);
            startActivity(cours);
        }
    };

    //Uniquement si le bouton Tons est appuie
    private View.OnClickListener boutTonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MenuCours.this, CoursSupTons.class);
            startActivity(cours);
        }
    };

    //Uniquement si le bouton Mesure est appuie
    private View.OnClickListener boutMesureListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MenuCours.this, CoursSupMesure.class);
            startActivity(cours);
        }
    };

    //Uniquement si le bouton Silences est appuie
    private View.OnClickListener boutSilencesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MenuCours.this, CoursSupSilences.class);
            startActivity(cours);
        }
    };

    //Uniquement si le bouton Nuances est appuie
    private View.OnClickListener boutNuancesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MenuCours.this, CoursSupNuances.class);
            startActivity(cours);
        }
    };

    //Uniquement si le bouton Tempi est appuie
    private View.OnClickListener boutTempiListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MenuCours.this, CoursSupTempi.class);
            startActivity(cours);
        }
    };

    //Uniquement si le bouton VarTemp est appuie
    private View.OnClickListener boutVarTempListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MenuCours.this, CoursSUPVarTemp.class);
            startActivity(cours);
        }
    };

    //Uniquement si le bouton Articulations est appuie
    private View.OnClickListener boutArtiListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MenuCours.this, CoursSupArticulations.class);
            startActivity(cours);
        }
    };

    //Uniquement si le bouton Reprises est appuie
    private View.OnClickListener boutReprisesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MenuCours.this, CoursSupReprises.class);
            startActivity(cours);
        }
    };
}
