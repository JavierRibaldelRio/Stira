package com.example.stira;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class Contador extends AppCompatActivity {


    public TextView textoTemporizador, textoTemporizadorDescanso, textoRonda;

    private int numeroRonda = 1, segundos, numeroRondas, segundosDescanso;

    int[] parametros;

    private MediaPlayer sonidoFinDeporte, sonidoFinDescanso; // Crear el Media Player

    private ImageButton volver;

    CountDownTimer contarDescanso, contar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contodor);



        getUI();

        asignarMusica();

        activarEscuchadorBoton();

        parametros = getIntent().getIntArrayExtra("p_valores");

        asignarVariables(parametros);

        prepararPantalla();

        prepararTemporizadores();

        sonidoFinDescanso.start();

        contar.start();


    }

    public void getUI() {

        textoTemporizador = findViewById(R.id.tiempo);

        textoTemporizadorDescanso = findViewById(R.id.tiempoDescanso);

        textoRonda = findViewById(R.id.ronda);

        volver = findViewById(R.id.botonRetroceder);


    }

    // frase de las Rondas{Ronda 2/4}

    public String hacerFraseRondas() {

        String frase;

        frase = getString(R.string.ronda) + " " + String.valueOf(numeroRonda) + "/" + String.valueOf(numeroRondas);

        return frase;

    }

    private void asignarMusica() {

        //Asignar la pista al objeto

        sonidoFinDeporte = MediaPlayer.create(this, R.raw.sonido_fin_deporte);

        sonidoFinDescanso = MediaPlayer.create(this, R.raw.sonido_fin_descanso);

    }

    private void activarTemporizadorSegundos() {

        long valor = segundos * 1000;


        contar = new CountDownTimer(valor, 1000) {
            @Override
            public void onTick(long l) {

                long tiempo = l / 1000;

                textoTemporizador.setText(String.valueOf(tiempo));

            }

            @Override
            public void onFinish() {

                textoTemporizador.setText(String.valueOf(segundos));

                sonidoFinDeporte.start();

                contarDescanso.start();


            }


        };



    }

    private void activarTemporizadorDescanso() {

        long valor = segundosDescanso * 1000;

         contarDescanso = new CountDownTimer(valor, 1000) {
            @Override
            public void onTick(long l) {

                long tiempo = l / 1000;

                textoTemporizadorDescanso.setText(String.valueOf(tiempo));

            }

            @Override
            public void onFinish() {



                if (numeroRonda == numeroRondas) {



                    Toast.makeText(Contador.this, getString(R.string.tiempoFin),Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Contador.this, MainActivity.class);

                    intent.putExtra("ultimosIntroducidos",parametros);

                    startActivity(intent);

                }else{
                    textoTemporizadorDescanso.setText(String.valueOf(segundosDescanso));


                    numeroRonda++;

                    String frase = hacerFraseRondas();

                    textoRonda.setText(String.valueOf(frase));

                    sonidoFinDescanso.start();

                    contar.start();
                }

            }


        };




    }

    // funacijon para asignar a las variables las casillas del array

    private void asignarVariables(int[] array){

        numeroRondas = array[0];

        segundos = array[1];

        segundosDescanso = array[2];

    }

    // función para asignar al principio todos los valore

    private void prepararPantalla() {

        textoTemporizador.setText(String.valueOf(segundos));

        textoTemporizadorDescanso.setText(String.valueOf(segundosDescanso));

        textoRonda.setText("Ronda 1/" + String.valueOf(numeroRondas));

    }

    private void activarEscuchadorBoton(){

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contar.cancel();

                contarDescanso.cancel();

                Intent pasoPantalla = new Intent(view.getContext(),MainActivity.class);

                startActivity(pasoPantalla);

            }
        });

    }

    private void prepararTemporizadores (){

        activarTemporizadorSegundos();

        activarTemporizadorDescanso();

    }

}
