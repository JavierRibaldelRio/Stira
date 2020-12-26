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

public class ContadorSinDescanso extends AppCompatActivity {

    ProgressBar barra;

    int segundos;

    boolean pausado = false;

    ImageButton retroceder, pause;

    TextView texto;

    CountDownTimer reloj;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_sin_descanso);

        segundos = sacarTiempo();

        getUI();

        activarEscuchadores();

        reloj = prepararTemporizador(segundos);

        reloj.start();

    }

    private int sacarTiempo(){

       return getIntent().getIntExtra("p_valores", -1);
    }

    //Asignar los objetos a sus variables

    private void getUI(){

        barra = findViewById(R.id.barra);

        retroceder = findViewById(R.id.atras);

        pause = findViewById(R.id.pauseplay);

        texto = findViewById(R.id.textoTemporizadorDescanso);

    }

    // activar los escuchadores

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

                    reloj.cancel();

                    reloj = prepararTemporizador(Integer.valueOf(String.valueOf(texto.getText())));

                    //cambiar imagen
                    pause.setImageResource(R.drawable.play);
                }
                else{
                    //Reanudar hilo
                    reloj.start();
                    //Cambiar imagen

                    pause.setImageResource(R.drawable.pause);
                }
            }
        });

    }


    private void actualizaBarra(ProgressBar p_barra, int p_segundos, long p_tiempo ){

        p_barra.setProgress((int) (100 * p_tiempo / p_segundos ));
    }

    private CountDownTimer prepararTemporizador(final int segundosRestantes){

        return new CountDownTimer((segundosRestantes * 1000),1000) {
            @Override
            public void onTick(long l) {

                texto.setText(String.valueOf(l/1000));

                actualizaBarra(barra,segundos,l/1000);

            }

            @Override
            public void onFinish() {

                Toast.makeText(ContadorSinDescanso.this, getString(R.string.tiempoFin),Toast.LENGTH_LONG).show();

                sonidoFin().start();

                Intent intent = new Intent(ContadorSinDescanso.this, MainActivity.class);

                intent.putExtra("ultimosIntroducidos",combertirEnArray(segundos));

                startActivity(intent);

            }
        };


    }

    //Función que prepara los datos para enviarlos a otras actividades
    private int[] combertirEnArray(int variable){

        //Crea un array con las variables, para entender que la ultima vez a sido encontador sin descanso le paso el valor de -2
        int[] array = {variable,-2,-2};

        return array;
    }

    private MediaPlayer sonidoFin(){
        return MediaPlayer.create(this,R.raw.sonido_fin_deporte);
    }


}
