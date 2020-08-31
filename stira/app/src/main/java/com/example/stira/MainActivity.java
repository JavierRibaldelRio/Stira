package com.example.stira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private Button temporizador, ultimaVez;

    private int[] ultimosDatos = {12,20,5};

    Context contexto = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("archivoSP", contexto.MODE_PRIVATE);

        ultimosDatos = getIntent().getIntArrayExtra("ultimosIntroducidos");

        getUI();

        activarEscuchadores();



        if (getIntent().getIntArrayExtra("ultimosIntroducidos") == null) {




        } else {

                ultimosDatos = getIntent().getIntArrayExtra("ultimosIntroducidos");

                guardarUltimosValores(ultimosDatos);
        }


    }

    private void getUI() {

        temporizador = findViewById(R.id.temporizador);

        ultimaVez = findViewById(R.id.ultimo);

    }

    private int[] sacarUltimosDatos() {

        int[] ultimos = new int[3];

        SharedPreferences sp = getPreferences(contexto.MODE_PRIVATE);

        ultimos[0] = sp.getInt("rondas", -1);

        ultimos[1] = sp.getInt("segundos", -1);

        ultimos[2] = sp.getInt("temporizador", -1);


        return ultimos;

    }

    private void activarEscuchadores() {

        temporizador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent cambioPantalla = new Intent(view.getContext(), IntroducirDatosTemporizador.class);

                startActivity(cambioPantalla);

            }

        });

        ultimaVez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), Contador.class);

                intent.putExtra("p_valores", sacarUltimosDatos());

                startActivity(intent);

            }
        });


    }

    private void guardarUltimosValores(int[] valores) {


        SharedPreferences sp = getPreferences(contexto.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();


        editor.putInt("rondas", valores[0]); //Añadir

        editor.putInt("segundos", valores[1]);

        editor.putInt("temporizador", valores[2]);


        editor.commit(); // Aplicar Cambios

    }

}

