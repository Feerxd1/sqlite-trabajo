package com.morgan.sqlite_trabajo.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.morgan.sqlite.models.Categoria;

import java.util.ArrayList;

public class DBCategorias extends DbHelper {

    Context context;

    public DBCategorias(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Categoria obtenerCategoriaPorId(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getReadableDatabase();
        Categoria categoria = null;
        Cursor cursor;
        //OBTENER LA CATEGORIA CON EL ID ENTREGADO
        cursor = bd.rawQuery("SELECT * FROM "+DB_TABLE_CATEGORIES+" WHERE id = ?",
                new String[]{String.valueOf(id) });
        //VERIFICAR SI LA CONSULTA DEVOLVIO ALGUN DATO
        if (cursor.moveToFirst()){
            //DEVUELVE UNA CATEGORIA
            categoria = new Categoria();
            categoria.setId(cursor.getInt(0));
            categoria.setNombre(cursor.getString(1));
        }
        return categoria;
    }

    public long insertarCategoria(String Nombre){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nombre", Nombre );

        return bd.insert(DB_TABLE_CATEGORIES, null, values);

    }

    public ArrayList<Categoria> SelectAll(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getReadableDatabase();
        ArrayList<Categoria> arrayCAtegorias = new ArrayList<>();
        Categoria catDB;
        Cursor cursor;

        cursor = bd.rawQuery("SELECT * FROM "+DB_TABLE_CATEGORIES, null);

        if (cursor.moveToFirst()){ //SI HAY REGISTROS
            do {
                catDB = new Categoria();
                catDB.setId(cursor.getInt(0));
                catDB.setNombre(cursor.getString(1));
                arrayCAtegorias.add(catDB);
            }while (cursor.moveToNext());

            return arrayCAtegorias;

        }else{  //NO HAY REGISTROS EN LA TABLA
            return null;
        }

    }

}
