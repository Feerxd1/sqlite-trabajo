package com.morgan.sqlite_trabajo.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.morgan.sqlite.models.Categoria;
import com.morgan.sqlite.models.Producto;

import java.util.ArrayList;

public class DBProductos extends DbHelper {

    Context context;

    public DBProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProducto(Producto producto){
        DbHelper ayuda = new DbHelper(context);
        SQLiteDatabase bd = ayuda.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("Nombre", producto.getNombre());
        valores.put("Marca", producto.getMarca());
        valores.put("Modelo", producto.getModelo());
        valores.put("Precio", producto.getPrecio());
        valores.put("Stock", producto.getStock());
        valores.put("Categoria", producto.getCategoria().getId());

        long datos = bd.insert(DB_TABLE_PRODUCTOS, null, valores);
        return datos;

    }

    public ArrayList<Producto> obtenerProductos(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getReadableDatabase();
        ArrayList<Producto> misProductos = new ArrayList<>();
        Producto producto;
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM "+DB_TABLE_PRODUCTOS, null);

        DBCategorias dbCat = new DBCategorias(context);

        if (cursor.moveToFirst()){
            do {
                Categoria cat = dbCat.obtenerCategoriaPorId(cursor.getInt(6));

                producto = new Producto();
                producto.setId(cursor.getInt(0));
                producto.setNombre(cursor.getString(1));
                producto.setMarca(cursor.getString(2));
                producto.setModelo(cursor.getString(3));
                producto.setPrecio(cursor.getInt(4));
                producto.setStock(cursor.getInt(5));
                producto.setCategoria(cat);

                misProductos.add(producto);
            }while (cursor.moveToNext());
                return misProductos;
        }else{
            return null;
        }

    }



}
