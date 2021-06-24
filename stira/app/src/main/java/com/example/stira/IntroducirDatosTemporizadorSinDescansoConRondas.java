package com.example.stira;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntroducirDatosTemporizadorSinDescansoConRondas extends AppCompatActivity {


    //Botón de asignar
    Button asignar;

    //El texto para insertar numeros
    EditText entrada;

    //Almacena los datos a pasar a el contador
    int valores[] = new int[2];

    //rondas
    int rondas;

    //Segundos
    int segundos;

    //Almacena en que estado esta el programa
    boolean repeticiones = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getUI();

    }

    void getUI() {

        asignar = findViewById(R.id.validar3);

        entrada = findViewById(R.id.textoAsignar2);
    }

    void activarEscuchadores() {
        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (false == String.valueOf(entrada.getText()).isEmpty() && Integer.valueOf(String.valueOf(entrada.getText())) > 0) {

                    if (repeticiones) {

                        rondas = Integer.valueOf(String.valueOf(entrada.getText()));

                        repeticiones = false;

                        asignar.setText(getString(R.string.inserteSegundos));

                    } else {

                        segundos = Integer.valueOf(String.valueOf(entrada.getText()));

                    }
                }else {



                }


            }
        });
    }
}
