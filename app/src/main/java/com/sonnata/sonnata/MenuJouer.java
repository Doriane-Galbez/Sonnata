package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuJouer extends AppCompatActivity {

    Button bout2 = null;
    Button bout3 = null;
    Button bout4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jouer);

        bout2 = (Button) findViewById(R.id.boutonMetro);
        bout3 = (Button) findViewById(R.id.boutonParti);
        bout4 = (Button) findViewById(R.id.boutonRetour);

        //On attribut un listener adapte aux vue qui en ont besoin
        bout2.setOnClickListener(boutMetroListener);
        bout3.setOnClickListener(boutPartiListener);
        bout4.setOnClickListener(boutRetourListener);
        bout2.setOnClickListener(boutMetroListener);

    }

    //Uniquement si le bouton 2 est appuie
    private View.OnClickListener boutMetroListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent metro = new Intent(MenuJouer.this, Metronome.class);
            startActivity(metro);
        }
    };

    //Uniquement si le bouton 3 est appuie
    private View.OnClickListener boutPartiListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent partition = new Intent(MenuJouer.this, MiniPiano.class);
            startActivity(partition);
        }
    };

    //Uniquement si le bouton retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent main = new Intent(MenuJouer.this, MainActivity.class);
            startActivity(main);
            finish();
        }
    };
}
