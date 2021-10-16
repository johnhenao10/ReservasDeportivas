package com.example.reservasdeportivas;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class ReservaFinalCancha extends AppCompatActivity implements View.OnClickListener {

    private Button bfecha;
    private Button bhora_inicio;
    private Button bhora_fin;
    private Button reservaFinal;
    private TextView mfecha;
    private TextView mhora_inicio;
    private TextView mhora_fin;
    private EditText ingresa_nombreFinal;
    private int dia, mes, ano, hora, minutos;
    private ProgressDialog Cargando;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_final_cancha);

        bfecha = (Button) findViewById(R.id.seleccionar_fecha);
        bhora_inicio = (Button) findViewById(R.id.seleccionar_hora_inicio);
        bhora_fin = (Button) findViewById(R.id.seleccionar_hora_fin);
        reservaFinal = (Button) findViewById(R.id.RealizarReserva);
        mfecha = (TextView) findViewById(R.id.mostrar_fecha);
        mhora_inicio = (TextView) findViewById(R.id.mostrar_hora_inicio);
        mhora_fin = (TextView) findViewById(R.id.mostrar_hora_fin);
        ingresa_nombreFinal =  (EditText) findViewById(R.id.Ingresar_nombre_final);

        Cargando = new ProgressDialog (this);
        bfecha.setOnClickListener(this);
        bhora_inicio.setOnClickListener(this);
        bhora_fin.setOnClickListener(this);

        reservaFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReservaEscenarioFinal();

            }
        });



    }



//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public void onClick(View v) {
//
//        if(v==bfecha)
//        {
//            final Calendar c= Calendar.getInstance();
//            dia=c.get(Calendar.DAY_OF_MONTH);
//            mes=c.get(Calendar.MONTH);
//            ano=c.get(Calendar.YEAR);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                    mfecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
//
//                }
//            }
//            ,ano, mes, dia);
//            datePickerDialog.show();
//
//
//        }
//
//        if(v==bhora)
//        {
//            final Calendar c= Calendar.getInstance();
//            hora=c.get(Calendar.HOUR);
//            minutos=c.get(Calendar.MINUTE);
//
//            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//                @Override
//                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute)
//                {
//
//                    mhora.setText(hourOfDay+":"+minute);
//
//                }
//            }
//            , hora, minutos, false);
//            timePickerDialog.show();
//
//
//
//        }

//    }
    private void ReservaEscenarioFinal() {
        String FechaReservaFinal = mfecha.getText().toString();
        String HoraReservaInicial = mhora_inicio.getText().toString();
        String HoraReservaFinal = mhora_fin.getText().toString();
        String NombreFinal = ingresa_nombreFinal.getText().toString();


        if (TextUtils.isEmpty(FechaReservaFinal)) {
            Toast.makeText(this, "Por favor selecciona la fecha...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(HoraReservaInicial)) {
            Toast.makeText(this, "Por favor selecciona la hora de inicio de la reserva...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(HoraReservaFinal)) {
            Toast.makeText(this, "Por favor selecciona la hora de fin de la reserva...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(NombreFinal)) {
            Toast.makeText(this, "Por favor ingresa tu nombre...", Toast.LENGTH_SHORT).show();
        }else {
            Cargando.setTitle("");
            Cargando.setMessage("Por favor espere, estamos comprobando tu informacion");
            Cargando.setCanceledOnTouchOutside(false);
            Cargando.show();

            ValidarReservaFinal(FechaReservaFinal, HoraReservaInicial ,HoraReservaFinal, NombreFinal);

        }


    }

    private void ValidarReservaFinal(String fechaReservaFinal, String horaReservaInicial, String horaReservaFinal, String nombreFinal)


    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("ReservaFinal").child(horaReservaInicial).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("Fecha", fechaReservaFinal);
                    userdataMap.put("HoraInicio", horaReservaInicial);
                    userdataMap.put("HoraFin", horaReservaFinal);
                    userdataMap.put("NombreUsuario", nombreFinal);



                    RootRef.child("ReservaFinal").child(horaReservaFinal).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(ReservaFinalCancha.this, "Tu reserva se ha realizado con exito...", Toast.LENGTH_SHORT).show();
                                        Cargando.dismiss();

                                        Intent intent = new Intent(ReservaFinalCancha.this, Reservar_escenario.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Cargando.dismiss();
                                        Toast.makeText(ReservaFinalCancha.this, "Por favor intentalo mas tarde...", Toast.LENGTH_SHORT).show();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        if(v==bfecha)
        {
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    mfecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                }
            }
                    ,ano, mes, dia);
            datePickerDialog.show();


        }

        if(v==bhora_inicio)
        {
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute)
                {

                    mhora_inicio.setText(hourOfDay+":"+minute);

                }
            }
                    , hora, minutos, false);
            timePickerDialog.show();



        }

        if (v==bhora_fin)
        {
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute)
                {

                    mhora_fin.setText(hourOfDay+":"+minute);

                }
            }
                    , hora, minutos, false);
            timePickerDialog.show();



        }



    }
}

