package com.example.reservasdeportivas.pojo;

public class Reserva {

    private String NombreUsuario, HoraFin, HoraInicio, Fecha;

    public Reserva() {
    }

    public Reserva(String nombreUsuario, String horaFin, String horaInicio, String fecha) {
        NombreUsuario = nombreUsuario;
        HoraFin = horaFin;
        HoraInicio = horaInicio;
        Fecha = fecha;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(String horaFin) {
        HoraFin = horaFin;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}