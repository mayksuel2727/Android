package com.example.maxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Principal extends AppCompatActivity {

    Button cliete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__principal);

        inicializarComponentes();
        eventoClick();
    }

    private void eventoClick() {
        cliete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getBaseContext(), Dados_Cliente.class));
            }
        });
    }

    private void inicializarComponentes() {
        cliete = (Button) findViewById(R.id.btnClintes);
    }
}