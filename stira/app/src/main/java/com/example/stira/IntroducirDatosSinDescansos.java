package com.example.stira;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntroducirDatosSinDescansos extends AppCompatActivity {

    Button asignar;

    EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_datos_sin_descansos);

        asignarUI();

    }

    private void asignarUI(){

        asignar = findViewById(R.id.validar2);

        texto = findViewById(R.id.textoAsignar);

    }

    private void activarEscuchadores(){

        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(!String.valueOf(asignar.getText()).isEmpty() && 0 >= Integer.valueOf(String.valueOf(asignar.getText()))){

                    // cambio de pantalla

                    /*Intent cambioPantalla = new (view.getContext(),)
                    Integer.valueOf(String.valueOf(asignar.getText()));*/



                }

            }
        });

    }
}