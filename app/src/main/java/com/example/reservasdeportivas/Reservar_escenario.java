package com.example.reservasdeportivas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private DatabaseReference sacarInfo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escenarios_items_reserva);

        nombre_Escenario = (TextView) findViewById(R.id.direccion_cancha);

        sacarInfo = FirebaseDatabase.getInstance().getReference();

        sacarInfo.child("Escenarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists()){
                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    nombre_Escenario.setText("El nombre es el " + nombre);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


}


