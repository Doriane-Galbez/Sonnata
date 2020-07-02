package com.sonnata.sonnata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class CoursSupNuances extends AppCompatActivity {
    Button boutRetour = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours_sup_nuances);
        boutRetour = (Button) findViewById(R.id.buttonRetour);
        boutRetour.setOnClickListener(boutRetourListener);
    }
    //Uniquement si le bouton Retour est appuie
    private View.OnClickListener boutRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent menu = new Intent(CoursSupNuances.this, MainActivity.class);
            startActivity(menu);
            finish();
        }
    };
}
