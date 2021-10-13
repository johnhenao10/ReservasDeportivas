package com.example.reservasdeportivas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservasdeportivas.Model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ingresar_activity extends AppCompatActivity
{
    private String BaseDatosNombre = "Usuario";
    private ProgressDialog Cargando;
    private EditText IngresarNumeroTelefono, IngresarContraseña;
    private Button Boton_Ingresar;
    private TextView Ingresar_Administrador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        Boton_Ingresar = (Button) findViewById(R.id.ingresar_boton);
        IngresarNumeroTelefono = (EditText) findViewById(R.id.ingresar_numero_telefono);
        IngresarContraseña = (EditText) findViewById(R.id.ingresar_contraseña);
        Ingresar_Administrador = (TextView) findViewById(R.id.administrador_panel);
        Cargando = new ProgressDialog(this);

        Boton_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Ingresar();
            }
        });

        Ingresar_Administrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boton_Ingresar.setText("Ingresar como administrador");
                Ingresar_Administrador.setVisibility(View.INVISIBLE);
                BaseDatosNombre = "Administradores";



            }
        });

    }

    private void Ingresar()
    {
        String NumeroTelefono = IngresarNumeroTelefono.getText().toString();
        String Contraseña = IngresarContraseña.getText().toString();

        if (TextUtils.isEmpty(NumeroTelefono))
        {

            Toast.makeText(this, "Por favor ingresa un numero telefonico", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Contraseña))
        {

            Toast.makeText(this, "Por favor ingresa una contraseña", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Cargando.setTitle("");
            Cargando.setMessage("Por favor espere, estamos comprobando tu informacion");
            Cargando.setCanceledOnTouchOutside(false);
            Cargando.show();


            ValidarIngresoCuenta(NumeroTelefono, Contraseña);



        }

    }

    private void ValidarIngresoCuenta( final String NumeroTelefono, final String Contraseña)
    {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                if (dataSnapshot.child(BaseDatosNombre).child(NumeroTelefono).exists())
                {
                    Usuario UsuarioData = dataSnapshot.child(BaseDatosNombre).child(NumeroTelefono).getValue(Usuario.class);

                    if (UsuarioData.getTelefono().equals(NumeroTelefono))
                    {
                        if (UsuarioData.getContraseña().equals(Contraseña))
                        {
                           /* Toast.makeText(Ingresar_activity.this, "Has ingresado con exito", Toast.LENGTH_SHORT).show();
                            Cargando.dismiss();

                            Intent intent = new Intent(Ingresar_activity.this, HomeActivity.class);
                            startActivity(intent); */
                            if (BaseDatosNombre.equals("Administradores"))
                            {
                                Toast.makeText(Ingresar_activity.this, "Has ingresado con exito", Toast.LENGTH_SHORT).show();
                                Cargando.dismiss();

                                Intent intent = new Intent(Ingresar_activity.this, AdminCategoria.class);
                                startActivity(intent);

                            }
                            else if (BaseDatosNombre.equals("Usuario"))
                            {
                                Toast.makeText(Ingresar_activity.this, "Has ingresado con exito", Toast.LENGTH_SHORT).show();
                                Cargando.dismiss();

                                Intent intent = new Intent(Ingresar_activity.this, HomeActivity.class);
                                startActivity(intent);
                            }

                        }
                        else
                        {
                            Cargando.dismiss();
                            Toast.makeText(Ingresar_activity.this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else
                {
                    Toast.makeText( Ingresar_activity.this, "La cuenta que ingresaste con numero"+ NumeroTelefono + "no existe", Toast.LENGTH_SHORT).show();
                    Cargando.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}