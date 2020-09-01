package com.example.stira;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class Contador extends AppCompatActivity {


    public TextView textoTemporizador, textoTemporizadorDescanso, textoRonda;

    private int numeroRonda = 1;

    private int[] parametros;

    private ProgressBar barraDeporte, barraDescanso;

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

        barraDeporte = findViewById(R.id.barraProgresoSegundos);

        barraDescanso = findViewById(R.id.barraProgresoSegundosDescansos);
    }

    // frase de las Rondas{Ronda 2/4}

    public String hacerFraseRondas() {

        String frase;

        frase = getString(R.string.ronda) + " " + numeroRonda + "/" + parametros[0];

        return frase;

    }

    private void asignarMusica() {

        //Asignar la pista al objeto

        sonidoFinDeporte = MediaPlayer.create(this, R.raw.sonido_fin_deporte);

        sonidoFinDescanso = MediaPlayer.create(this, R.raw.sonido_fin_descanso);

    }

    private void activarTemporizadorSegundos() {

        long valor = parametros[1] * 1000;


        contar = new CountDownTimer(valor, 1000) {
            @Override
            public void onTick(long l) {

                long tiempo = l / 1000;

                actualizaBarra(barraDeporte,parametros[1],tiempo);

                textoTemporizador.setText(String.valueOf(tiempo));

            }

            @Override
            public void onFinish() {

                textoTemporizador.setText(String.valueOf(parametros[1]));

                sonidoFinDeporte.start();

                contarDescanso.start();


            }


        };



    }

    private void activarTemporizadorDescanso() {

        final long valor = parametros[2] * 1000;

         contarDescanso = new CountDownTimer(valor, 1000) {
            @Override
            public void onTick(long l) {

                long tiempo = l / 1000;

                actualizaBarra(barraDescanso,parametros[2],tiempo);

                textoTemporizadorDescanso.setText(String.valueOf(tiempo));

            }

            @Override
            public void onFinish() {

                if (numeroRonda == parametros[0]) {

                    Toast.makeText(Contador.this, getString(R.string.tiempoFin),Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Contador.this, MainActivity.class);

                    intent.putExtra("ultimosIntroducidos",parametros);

                    startActivity(intent);

                }else{
                    textoTemporizadorDescanso.setText(String.valueOf(parametros[2]));

                    numeroRonda++;

                    textoRonda.setText(String.valueOf(hacerFraseRondas()));

                    sonidoFinDescanso.start();

                    contar.start();
                }

            }


        };




    }


    // función para asignar al principio todos los valore

    private void prepararPantalla() {

        textoTemporizador.setText(String.valueOf(parametros[1]));

        textoTemporizadorDescanso.setText(String.valueOf(parametros[2]));

        textoRonda.setText(getString(R.string.Ronda1) + (parametros[0]));

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

    private void actualizaBarra(ProgressBar barra, int p_segundos, long p_tiempo ){

        barra.setProgress((int) (100 * p_tiempo / p_segundos ));
    }

}
