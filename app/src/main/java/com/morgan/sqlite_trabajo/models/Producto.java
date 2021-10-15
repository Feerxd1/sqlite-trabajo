package com.morgan.sqlite_trabajo.models;

import java.io.Serializable;

public class Producto implements Serializable {
    private int Id;
    private String Nombre;
    private String Marca;
    private String Modelo;
    private int Precio;
    private int Stock;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String nombre, String marca, String modelo, int precio, int stock, Categoria categoria) {
        Nombre = nombre;
        Marca = marca;
        Modelo = modelo;
        Precio = precio;
        Stock = stock;
        this.categoria = categoria;
    }

    public Producto(int id, String nombre, String marca, String modelo, int precio, int stock, Categoria categoria) {
        Id = id;
        Nombre = nombre;
        Marca = marca;
        Modelo = modelo;
        Precio = precio;
        Stock = stock;
        this.categoria = categoria;
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

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString(){
        return this.Nombre+"\n"+this.Marca+"\n"+this.Precio;
    }

}
