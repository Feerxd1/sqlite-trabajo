package com.morgan.sqlite_trabajo.models;

public class Usuario {

    private int Id;
    private String Nombres;
    private String Apellidos;
    private String Email;
    private String Clave;

    @Override
    public String toString() {
        return this.Nombres+ " "+this.Apellidos;
    }


    public Usuario() {
    }


    public Usuario(String nombres, String apellidos, String email, String clave) {
        Nombres = nombres;
        Apellidos = apellidos;
        Email = email;
        Clave = clave;
    }


    public Usuario(int id, String nombres, String apellidos, String email, String clave) {
        Id = id;
        Nombres = nombres;
        Apellidos = apellidos;
        Email = email;
        Clave = clave;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

}
