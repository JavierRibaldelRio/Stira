package com.example.stira;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class ContadorSinDescanso extends AppCompatActivity {

    ProgressBar barra;

    int segundos;

    boolean pausado = false;

    ImageButton retroceder, pause;

    TextView texto;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_sin_descanso);


    }

    /*private int sacarTiempo(){

       // return
    }*/


}
