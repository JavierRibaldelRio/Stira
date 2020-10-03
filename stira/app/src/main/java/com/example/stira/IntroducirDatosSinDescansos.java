package com.example.stira;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IntroducirDatosSinDescansos extends AppCompatActivity {

    Button asignar;

    EditText texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_datos_sin_descansos);

        asignarUI();

        activarEscuchadores();

    }

    private void asignarUI() {

        asignar = findViewById(R.id.validar2);

        texto2 = findViewById(R.id.textoAsignar);

    }

    private void activarEscuchadores() {

        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (String.valueOf(texto2.getText()).isEmpty()) {

                    Toast.makeText(IntroducirDatosSinDescansos.this, getString(R.string.inserteValor), Toast.LENGTH_LONG).show();


                } else {
                // cambio de pantalla

                    int ints = Integer.valueOf(String.valueOf(texto2.getText()));

                    Intent cambioPantalla = new Intent(view.getContext(), ContadorSinDescanso.class);
                    cambioPantalla.putExtra("p_valores",ints);
                    startActivity(cambioPantalla);


                }

            }
        });

    }
}