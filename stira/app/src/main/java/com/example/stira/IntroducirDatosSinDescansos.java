package com.example.stira;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        activarEscuchadores();

    }

    private void asignarUI(){

        asignar = findViewById(R.id.validar2);

        texto = findViewById(R.id.textoAsignar);

    }

    private void activarEscuchadores(){

        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(!String.valueOf(asignar.getText()).isEmpty() && 0 >= Integer.valueOf(String.valueOf(asignar.getText()))) {

                    // cambio de pantalla

                    Intent cambioPantalla = new Intent(view.getContext(), ContadorSinDescanso.class);
                    cambioPantalla.putExtra("tiempo", Integer.valueOf(String.valueOf(texto.getText())));
                    startActivity(cambioPantalla);

                }
                else{

                    texto.setText("");

                    Toast.makeText(IntroducirDatosSinDescansos.this,R.string.inserteValor,Toast.LENGTH_SHORT).show();


                }

            }
        });

    }
}