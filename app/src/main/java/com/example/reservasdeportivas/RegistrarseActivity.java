package com.example.reservasdeportivas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class RegistrarseActivity extends AppCompatActivity {
    private Button Boton_CrearCuenta;
    private EditText IngresarUsuario, IngresarNumeroTelefono, IngresarContraseña;
    private ProgressDialog Cargando;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);


        Boton_CrearCuenta = (Button) findViewById(R.id.registrarse_boton);
        IngresarUsuario = (EditText) findViewById(R.id.registrar_usuario_ingresar);
        IngresarNumeroTelefono = (EditText) findViewById(R.id.registrar_numero_telefono);
        IngresarContraseña = (EditText) findViewById(R.id.registrar_contraseña);
        Cargando = new ProgressDialog(this);

        Boton_CrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CrearCuenta();
            }
        });

        changeStatusBarColor();

    }




    private void CrearCuenta() {
        String Usuario = IngresarUsuario.getText().toString();
        String NumeroTelefono = IngresarNumeroTelefono.getText().toString();
        String Contraseña = IngresarContraseña.getText().toString();

        if (TextUtils.isEmpty(Usuario)) {
            Toast.makeText(this, "Por favor ingresa un nombre de Usuario", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(NumeroTelefono)) {
            Toast.makeText(this, "Por favor ingresa un numero telefonico", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Contraseña)) {
            Toast.makeText(this, "Por favor ingresa una contraseña", Toast.LENGTH_SHORT).show();
        } else {
            Cargando.setTitle("");
            Cargando.setMessage("Por favor espere, estamos comprobando tu informacion");
            Cargando.setCanceledOnTouchOutside(false);
            Cargando.show();

            ValidacionNumeroTelefonico(Usuario, NumeroTelefono, Contraseña);

        }
    }

    private void ValidacionNumeroTelefonico(final String Usuario, final String NumeroTelefono, final String Contraseña) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Usuario").child(NumeroTelefono).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("usuario", Usuario);
                    userdataMap.put("telefono", NumeroTelefono);
                    userdataMap.put("contraseña", Contraseña);

                    RootRef.child("Usuario").child(NumeroTelefono).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistrarseActivity.this, "Felicitaciones, tu cuenta ha sido creada con exito", Toast.LENGTH_SHORT).show();
                                        Cargando.dismiss();

                                        Intent intent = new Intent(RegistrarseActivity.this, Ingresar_activity.class);
                                        startActivity(intent);
                                    } else {
                                        Cargando.dismiss();
                                        Toast.makeText(RegistrarseActivity.this, "Por favor intentalo mas tarde...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else {
                    Toast.makeText(RegistrarseActivity.this, "Este numero" + NumeroTelefono + "ya existe", Toast.LENGTH_SHORT).show();
                    Cargando.dismiss();
                    Toast.makeText(RegistrarseActivity.this, "Por favor ingrese otro numero telefonico", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(RegistrarseActivity.this, MainActivity.class);
//                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, Ingresar_activity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}