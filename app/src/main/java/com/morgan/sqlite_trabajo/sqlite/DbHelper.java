package com.morgan.sqlite_trabajo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    protected static final String DB_NAME = "TiendAndroid";
    protected static final int DB_VERSION = 7;
    protected Context contexto;

    //DEFINIR LAS TABLAS DE LA BASE DE DATOS
    protected static final String DB_TABLE_USERS = "usuarios";
    protected static final String DB_TABLE_CATEGORIES = "categorias";
    protected static final String DB_TABLE_PRODUCTOS = "productos";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_USERS+"("+
                "Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Nombres TEXT NOT NULL,"+
                "Apellidos TEXT NOT NULL,"+
                "Email TEXT NOT NULL,"+
                "Clave TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_CATEGORIES+"("+
                "Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_PRODUCTOS+"("+
                "Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Nombre TEXT NOT NULL,"+
                "Marca TEXT NOT NULL,"+
                "Modelo TEXT NOT NULL,"+
                "Precio INT NOT NULL,"+
                "Stock INT NOT NULL,"+
                "Categoria INTEGER NOT NULL,"+
                "FOREIGN KEY (Categoria) REFERENCES categorias(id))");

    }

    //SI LA VERSION SE CAMBIA(DB_VERSION = 2) SE EJECUTA EL ONUPGRADE PARA ACTUALIZAR LA INFO DE LA TABLA
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_CATEGORIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_PRODUCTOS);
        onCreate(sqLiteDatabase);
    }

}
