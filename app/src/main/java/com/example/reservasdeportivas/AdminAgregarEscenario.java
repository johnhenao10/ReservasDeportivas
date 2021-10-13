package com.example.reservasdeportivas;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.sax.RootElement;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminAgregarEscenario extends AppCompatActivity {

    private String Nombre_Categoria;
    private Button AgregarEscenario_Boton;
    private EditText Ingresar_nombre_escenario, Ingresar_direccion_escenario, Hora_Apertura, Hora_Cierre;
    private ImageView Ingresar_foto_escenario;
    private ProgressDialog Cargando;
    ActivityResultLauncher<String> mGetContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_agregar_escenario);


        /* Toast.makeText(this, "Bienvenido, has ingresado como administrador", Toast.LENGTH_SHORT).show(); */

        Nombre_Categoria = getIntent().getExtras().get("Categoria").toString();

        Ingresar_foto_escenario = (ImageView) findViewById(R.id.agregar_imagen_escenario);
        AgregarEscenario_Boton = (Button) findViewById(R.id.agregar_nuevo_escenario);
        Ingresar_nombre_escenario = (EditText) findViewById(R.id.nombre_escenario);
        Ingresar_direccion_escenario = (EditText) findViewById(R.id.direccion_escenario);
        Hora_Apertura = (EditText) findViewById(R.id.hora_apertura);
        Hora_Cierre = (EditText) findViewById(R.id.hora_cierre);
        Cargando = new ProgressDialog (this);

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Ingresar_foto_escenario.setImageURI(result);
            }
        });

        Ingresar_foto_escenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");

            }
        });

        AgregarEscenario_Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearEscenario();

            }
        });







        /* Toast.makeText(this, Nombre_Categoria, Toast.LENGTH_SHORT).show(); */


    }

    private void CrearEscenario() {
        String Nombre_Escenario = Ingresar_nombre_escenario.getText().toString();
        String Direccion_Escenario = Ingresar_direccion_escenario.getText().toString();
        String Hora_Inicio = Hora_Apertura.getText().toString();
        String Hora_Fin = Hora_Cierre.getText().toString();

        if (TextUtils.isEmpty(Nombre_Escenario)) {
            Toast.makeText(this, "Por favor ingresa un nombre de Usuario", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Direccion_Escenario)) {
            Toast.makeText(this, "Por favor ingresa un numero telefonico", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Hora_Inicio)) {
            Toast.makeText(this, "Por favor ingresa una contraseña", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Hora_Fin)) {
            Toast.makeText(this, "Por favor ingresa una contraseña", Toast.LENGTH_SHORT).show();
        } else {
            Cargando.setTitle("");
            Cargando.setMessage("Por favor espere, estamos comprobando tu informacion");
            Cargando.setCanceledOnTouchOutside(false);
            Cargando.show();

            ValidacionEscenario (Nombre_Escenario, Direccion_Escenario, Hora_Inicio, Hora_Fin);

        }


    }

    private void ValidacionEscenario(String nombre_escenario, String direccion_escenario, String hora_inicio, String hora_fin)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("Escenarios").child(nombre_escenario).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("nombre", nombre_escenario);
                    userdataMap.put("direccion", direccion_escenario);
                    userdataMap.put("horaapertura", hora_inicio);
                    userdataMap.put("horacierre", hora_fin);

                    RootRef.child("Escenarios").child("").updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(AdminAgregarEscenario.this, "Felicitaciones, tu cuenta ha sido creada con exito", Toast.LENGTH_SHORT).show();
                                        Cargando.dismiss();

                                        Intent intent = new Intent(AdminAgregarEscenario.this, HomeActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Cargando.dismiss();
                                        Toast.makeText(AdminAgregarEscenario.this, "Por favor intentalo mas tarde...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}