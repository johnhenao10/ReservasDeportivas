package com.example.reservasdeportivas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoria extends AppCompatActivity
{
    private ImageView Agregar_escenario_futbol;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_categoria);

        Agregar_escenario_futbol = (ImageView) findViewById(R.id.agregar_cancha_sintetica);

        Agregar_escenario_futbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoria.this, AdminAgregarEscenario.class);
                intent.putExtra("Categoria", "Cancha futbol");
                startActivity(intent);
            }
        });


    }
}