package com.example.reservasdeportivas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.reservasdeportivas.fragment.Adapter.AdapterBannner;

import me.relex.circleindicator.CircleIndicator;


public class HomeActivity extends AppCompatActivity
{
    public ViewPager viewPager;
    AdapterBannner adapterBannner;
   // private Button BotonCerrarSesion;
    private Button reserva_escenario;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.viewpager);

        CircleIndicator indicator = findViewById(R.id.indicator);

        adapterBannner = new AdapterBannner(getSupportFragmentManager());

        viewPager.setAdapter(adapterBannner);

        indicator.setViewPager(viewPager);

        adapterBannner.registerDataSetObserver(indicator.getDataSetObserver());

        reserva_escenario = (Button) findViewById(R.id.agregar_cancha_escenario);

        reserva_escenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Reservar_escenario.class);
                startActivity(intent);
            }
        });

//       // BotonCerrarSesion = (Button) findViewById(R.id.boton_cerrar_sesion);
//
//        BotonCerrarSesion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(HomeActivity.this, RegistrarseActivity.class);
//                startActivity(intent);
//
//            }
//        });


    }

}