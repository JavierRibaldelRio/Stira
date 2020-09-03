package com.example.stira;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ElegirEntreTipos extends AppCompatActivity {

    Button sin, con;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_entre_tipos);

        getUI();

        activarEscuchadores();


    }

    private void getUI(){

        sin = findViewById(R.id.sin);

        con = findViewById(R.id.con);

    }

    private void activarEscuchadores(){

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(),IntroducirDatosSinDescansos.class));

            }
        });

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(),IntroducirDatosTemporizador.class));

            }
        });

    }

}
