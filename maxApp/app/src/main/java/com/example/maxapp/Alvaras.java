package com.example.maxapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Alvaras extends AppCompatActivity {

    private Button tbnDados, btnHisPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alvaras);

        eventoClick();
    }

    private void eventoClick() {

        tbnDados = (Button) findViewById(R.id.btnDados);
        btnHisPedidos = (Button) findViewById(R.id.btnHisPedidos);

        tbnDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Dados_Cliente.class));
                finish();
            }
        });

        btnHisPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Hist_Pedidos.class));
                finish();
            }
        });

    }
}