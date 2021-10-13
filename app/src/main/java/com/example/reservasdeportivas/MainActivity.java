package com.example.reservasdeportivas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button boton_registrarse, boton_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton_registrarse = (Button) findViewById(R.id.boton_registrarse);
        boton_ingresar = (Button) findViewById(R.id.boton_ingresar);

        boton_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Ingresar_activity.class);
                startActivity(intent);
            }
        });

        boton_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegistrarseActivity.class);
                startActivity(intent);
            }
        });
    }
}