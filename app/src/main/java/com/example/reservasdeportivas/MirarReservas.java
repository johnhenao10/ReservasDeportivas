package com.example.reservasdeportivas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;

import com.example.reservasdeportivas.adapter.AdapterReserva;
import com.example.reservasdeportivas.pojo.Reserva;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;

public class MirarReservas<DataBaseReference> extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<Reserva> list;
    RecyclerView rv;
    AdapterReserva adapter;
    LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirar_reservas);



        rv = findViewById(R.id.rv);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterReserva(list);
        rv.setAdapter(adapter);

        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference();

        ref.child("ReservaFinal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                        Reserva rv = datasnapshot.getValue(Reserva.class);
                        list.add(rv);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}