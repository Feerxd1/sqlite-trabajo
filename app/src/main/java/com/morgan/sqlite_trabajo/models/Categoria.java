package com.morgan.sqlite_trabajo.models;

public class Categoria {

    private int Id;
    private String Nombre;

    public Categoria() {
    }

    public Categoria(String nombre) {
        Nombre = nombre;
    }

    public Categoria(int id, String nombre) {
        Id = id;
        Nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString(){
        return this.Nombre;
    }

}
