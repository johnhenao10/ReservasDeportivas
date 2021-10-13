package com.example.reservasdeportivas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class HomeActivity extends AppCompatActivity
{
    private Button BotonCerrarSesion;
    private ImageView reserva_escenario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        reserva_escenario = (ImageView) findViewById(R.id.agregar_cancha_escenario);

        reserva_escenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Reservar_escenario.class);
                startActivity(intent);
            }
        });

        BotonCerrarSesion = (Button) findViewById(R.id.boton_cerrar_sesion);

        BotonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }

}