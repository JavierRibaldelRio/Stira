package com.example.stira;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


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

        segundos = sacarTiempo();

        getUI();

        activarEscuchadores();

        Toast.makeText(ContadorSinDescanso.this,String.valueOf(segundos),Toast.LENGTH_SHORT).show();

    }

    private int sacarTiempo(){

       return getIntent().getIntExtra("g", -1);
    }

    private void getUI(){

        barra = findViewById(R.id.barra);

        retroceder = findViewById(R.id.atras);

        pause = findViewById(R.id.pauseplay);

        texto = findViewById(R.id.textoTemporizadorDescanso);

    }

    private void activarEscuchadores(){

        retroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(),MainActivity.class)); //cambiar de actividad

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pausado = !pausado; //cambiar pause al reves

                if(pausado == true){

                    //Detener hilo

                    //cambiar imagen

                    pause.setImageResource(R.drawable.play);



                }
                else{
                    //Reanudar hilo

                    //Cambiar imagen

                    pause.setImageResource(R.drawable.pause);
                }
            }
        });

    }


}
