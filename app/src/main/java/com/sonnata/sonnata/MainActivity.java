package com.sonnata.sonnata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    PopupWindow popupWindow;
    LinearLayout linearLayout2;

    ImageView logo = null;
    ImageView info = null;
    ImageView plus = null;
    Button bout1 = null;
    Button bout2 = null;
    Button bout3 = null;
    Button bout4 = null;
    Button boutFermer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bout1 = (Button) findViewById(R.id.bouton1);
        bout2 = (Button) findViewById(R.id.bouton2);
        bout3 = (Button) findViewById(R.id.bouton3);
        bout4 = (Button) findViewById(R.id.bouton4);
        logo = (ImageView) findViewById(R.id.imageViewLogo);
        info = (ImageView) findViewById(R.id.imageViewInfo);
        plus = (ImageView) findViewById(R.id.imageViewPlus);

        //On attribut un listener adapte aux vue qui en ont besoin
        bout1.setOnClickListener(bout1Listener);
        bout2.setOnClickListener(bout2Listener);
        bout3.setOnClickListener(bout3Listener);
        bout4.setOnClickListener(bout4Listener);
        logo.setOnClickListener(imageClickListener);
        info.setOnClickListener(imageInfoClickListener);
        plus.setOnClickListener(imagePlusClickListener);

        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayoutMainActi);
    }

    protected void showPopup() {
        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_window_bienvenu, null);

        boutFermer = (Button) customView.findViewById(R.id.boutonFermerBienvenu);
        //textePopup = (TextView) customView.findViewById(R.id.textPopupExoReadNote);

        enableButtons(false);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.showAtLocation(linearLayout2, Gravity.CENTER, 0, 0);

        //close the popup window on button click
        boutFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButtons(true);
                popupWindow.dismiss();
            }
        });

    }

    protected void showPopupInfo() {
        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_info_app, null);

        boutFermer = (Button) customView.findViewById(R.id.boutonFermerBienvenu);
        //textePopup = (TextView) customView.findViewById(R.id.textPopupExoReadNote);

        enableButtons(false);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.showAtLocation(linearLayout2, Gravity.CENTER, 0, 0);

        //close the popup window on button click
        boutFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButtons(true);
                popupWindow.dismiss();
            }
        });

    }

    private void enableButtons(boolean autorise){
        bout1.setEnabled(autorise);
        bout2.setEnabled(autorise);
        bout3.setEnabled(autorise);
        bout4.setEnabled(autorise);
        logo.setClickable(autorise);
        info.setClickable(autorise);
        plus.setClickable(autorise);
    }


    //Uniquement si le bouton 1 est appuie
    // /* Reagir au clic*/
    private OnClickListener bout1Listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent readNote = new Intent(MainActivity.this, ReadNote.class);
            startActivity(readNote);

        }
    };

    //Uniquement si le bouton 2 est appuie
    // /* Reagir au clic*/
    private OnClickListener bout2Listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent ecouteNote = new Intent(MainActivity.this, EcouteNote.class);
            startActivity(ecouteNote);
        }
    };

    //Uniquement si le bouton 3 est appuie
    // /* Reagir au clic*/
    private OnClickListener bout3Listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent rythme = new Intent(MainActivity.this, Rythme.class);
            startActivity(rythme);
        }
    };

    //Uniquement si le bouton 4 est appuie
    // /* Reagir au clic*/
    private OnClickListener bout4Listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent jouer = new Intent(MainActivity.this, MenuJouer.class);
            startActivity(jouer);
        }
    };

    //Uniquement si le logo est appuie
    // /* Reagir au clic*/
    private OnClickListener imageClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopup();
        }
    };

    //Uniquement si l'icon info est appuie
    // /* Reagir au clic*/
    private OnClickListener imageInfoClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupInfo();
        }
    };

    //Uniquement si l'icon plus est appuie
    // /* Reagir au clic*/
    private OnClickListener imagePlusClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cours = new Intent(MainActivity.this, MenuCours.class);
            startActivity(cours);
        }
    };

}
