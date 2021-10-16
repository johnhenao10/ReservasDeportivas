package com.example.reservasdeportivas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.reservasdeportivas.Model.Escenario;
import com.example.reservasdeportivas.ViewHolder.EscenarioViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Reservar_escenario extends AppCompatActivity
{

    private TextView nombre_Escenario;
    private TextView direccion_Cancha;
    private TextView HoraAbreCancha;
    private TextView HoraCierreCancha;
    private DatabaseReference sacarInfo;
    private Button RealizarReserva;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escenarios_items_reserva);

        nombre_Escenario = (TextView) findViewById(R.id.nombre_cancha);
        direccion_Cancha = (TextView) findViewById(R.id.direccion_cancha);
        HoraAbreCancha = (TextView) findViewById(R.id.horaApertura_cancha);
        HoraCierreCancha = (TextView) findViewById(R.id.horaCierre_cancha);
        RealizarReserva = (Button) findViewById(R.id.separar_cancha);

        RealizarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reservar_escenario.this, ReservaFinalCancha.class);
                startActivity(intent);
            }
        });


        sacarInfo = FirebaseDatabase.getInstance().getReference();

        sacarInfo.child("Escenarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists()){
                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    nombre_Escenario.setText((nombre));

                    String direccion = dataSnapshot.child("direccion").getValue().toString();
                    direccion_Cancha.setText((direccion));

                    String horaapertura = dataSnapshot.child("horaapertura").getValue().toString();
                    HoraAbreCancha.setText("Abierto desde las " + horaapertura);

                    String horacierre = dataSnapshot.child("horacierre").getValue().toString();
                    HoraCierreCancha.setText("Cierra a las " + horacierre);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


}


