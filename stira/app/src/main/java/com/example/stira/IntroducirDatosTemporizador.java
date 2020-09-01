package com.example.stira;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class IntroducirDatosTemporizador extends AppCompatActivity {


    private String[] preguntas = new String[2]; //Aquí se almacenan las preguntas

    private int[] valores = new int[3]; //

    /**
     * 0: Rondas
     *
     * 1: Tiempo
     *
     * 2: Tiempo Descanso
     */

    short contador = 0;     //Para saber el numero de pregunta

    Button validar;

    EditText texto;

    Context contexto = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_datos_temporizador);

        SharedPreferences sharedPreferences = getSharedPreferences("ultimosIntroducidos", contexto.MODE_PRIVATE);


        rellenarArray();

        getUI();

        activarEscuchador();




    }


    private void rellenarArray() {
        preguntas[0] = getString(R.string.ejer);

        preguntas[1] = getString(R.string.desc);
    }

    private void getUI() {

        texto = findViewById(R.id.texto);

        validar = findViewById(R.id.validar);

    }

    private void activarEscuchador() {

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //comprovar que el texto no esta vacio y es mayor que cero

                if (false == String.valueOf(texto.getText()).isEmpty() && Integer.valueOf(String.valueOf(texto.getText())) > 0) {

                    switch (contador) {

                        case 0:
                        case 1:


                            valores[contador] = Integer.valueOf(String.valueOf(texto.getText()));

                            contador++;

                            texto.setText("");

                            texto.setHint(preguntas[contador - 1]);

                            break;

                        case 2:

                            valores[2] = Integer.valueOf(String.valueOf(texto.getText()));

                            cambiarPantalla(view);

                            break;


                    }

                } else {

                    Toast.makeText(IntroducirDatosTemporizador.this, getString(R.string.inserteValor), Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void cambiarPantalla(View v) {

        Intent paso = new Intent(v.getContext(), Contador.class);

        paso.putExtra("p_valores", valores); //Añadir el array al valor

        startActivity(paso);

    }


}
