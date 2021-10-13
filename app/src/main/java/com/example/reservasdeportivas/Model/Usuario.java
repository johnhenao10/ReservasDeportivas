package com.example.reservasdeportivas.Model;

public class Usuario {

    private String usuario, telefono, contraseña;

    public Usuario ()
    {

    }

    public Usuario(String usuario, String telefono, String contraseña) {
        this.usuario = usuario;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
