package com.example.reservasdeportivas.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservasdeportivas.Interface.ItemClickListener;
import com.example.reservasdeportivas.R;
import com.rey.material.widget.TextView;

public class EscenarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView nombreCancha, DireccionCancha, HoraInicio, HoraFin;
    public ItemClickListener listener;




    public EscenarioViewHolder(@NonNull View itemView) {
        super(itemView);

        nombreCancha = (TextView) itemView.findViewById(R.id.nombre_cancha);
        DireccionCancha = (TextView) itemView.findViewById(R.id.direccion_cancha);
        HoraInicio = (TextView) itemView.findViewById(R.id.horaApertura_cancha);
        HoraFin = (TextView) itemView.findViewById(R.id.horaCierre_cancha);

    }

    public void setItemClickListener (ItemClickListener listener)
    {
        this.listener = listener;


    }

    @Override
    public void onClick(View view)
    {
        listener.OnClick(view, getAdapterPosition(), false);
    }
}
