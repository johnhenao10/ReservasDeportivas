package com.example.reservasdeportivas.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservasdeportivas.R;
import com.example.reservasdeportivas.pojo.Reserva;

import java.util.List;

public class AdapterReserva extends RecyclerView.Adapter<AdapterReserva.viewholderreservas> {

    List<Reserva> reservaList;

    public AdapterReserva(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @NonNull
    @Override
    public viewholderreservas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reservas, parent, false);
        viewholderreservas holder = new viewholderreservas(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderreservas holder, int position) {

        Reserva rv = reservaList.get(position);

        holder.tv_nombre.setText(rv.getNombreUsuario());
        holder.tv_fecha.setText(rv.getFecha());
        holder.tv_hora_inicio.setText(rv.getHoraInicio());
        holder.tv_hora_final.setText(rv.getHoraFin());

    }

    @Override
    public int getItemCount() {
        return reservaList.size();
    }

    public class viewholderreservas extends RecyclerView.ViewHolder {

        TextView tv_nombre, tv_fecha, tv_hora_inicio, tv_hora_final;

        public viewholderreservas(@NonNull View itemView) {
            super(itemView);

            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_fecha = itemView.findViewById(R.id.tv_fecha);
            tv_hora_inicio = itemView.findViewById(R.id.tv_hora_inicio);
            tv_hora_final = itemView.findViewById(R.id.tv_hora_final);
        }
    }
}