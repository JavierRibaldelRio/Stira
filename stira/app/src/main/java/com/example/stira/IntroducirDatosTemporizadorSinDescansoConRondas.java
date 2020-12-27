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

    //rondas
    int rondas;

    //Segundos
    float segundos;

    short contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getUI();



    }

    void getUI(){

        asignar = findViewById(R.id.validar3);

        entrada = findViewById(R.id.textoAsignar2);
    }

    void activarEscuchadores(){
        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (contador){
                    case 0:
                        rondas = EditText.

                }





            }
        });
    }
}
