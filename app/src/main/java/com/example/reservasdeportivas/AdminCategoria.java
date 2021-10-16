package com.example.reservasdeportivas;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reservasdeportivas.fragment.Adapter.AdapterBannner;

import me.relex.circleindicator.CircleIndicator;

public class AdminCategoria extends AppCompatActivity
{
    public ViewPager viewPager;
    AdapterBannner adapterBannner;
    private Button Agregar_escenario_futbol;
    private Button MirarReservaAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_categoria);

        viewPager = findViewById(R.id.viewpager);

        CircleIndicator indicator = findViewById(R.id.indicator);

        adapterBannner = new AdapterBannner(getSupportFragmentManager());

        viewPager.setAdapter(adapterBannner);

        indicator.setViewPager(viewPager);

        adapterBannner.registerDataSetObserver(indicator.getDataSetObserver());

        Agregar_escenario_futbol = (Button) findViewById(R.id.agregar_cancha_sintetica);
        MirarReservaAdmin = (Button) findViewById(R.id.gestionar_reserva);



        Agregar_escenario_futbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoria.this, AdminAgregarEscenario.class);
                intent.putExtra("Categoria", "Cancha futbol");
                startActivity(intent);
            }
        });

        MirarReservaAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoria.this, MirarReservas.class);
                startActivity(intent);
            }
        });



    }

}