package com.example.reservasdeportivas.Model;

public class Escenario
{
    private String nombreEscenario, direccionEscenario, horaApertura, horaFin;

    public Escenario()
    {

    }

    public Escenario(String nombreEscenario, String direccionEscenario, String horaApertura, String horaFin) {
        this.nombreEscenario = nombreEscenario;
        this.direccionEscenario = direccionEscenario;
        this.horaApertura = horaApertura;
        this.horaFin = horaFin;
    }

    public String getNombreEscenario() {
        return nombreEscenario;
    }

    public void setNombreEscenario(String nombreEscenario) {
        this.nombreEscenario = nombreEscenario;
    }

    public String getDireccionEscenario() {
        return direccionEscenario;
    }

    public void setDireccionEscenario(String direccionEscenario) {
        this.direccionEscenario = direccionEscenario;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
