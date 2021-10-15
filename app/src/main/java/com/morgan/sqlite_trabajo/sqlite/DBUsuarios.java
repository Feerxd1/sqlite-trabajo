package com.morgan.sqlite_trabajo.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.morgan.sqlite.models.Usuario;

import java.util.ArrayList;

public class DBUsuarios extends DbHelper {

    DbHelper helper;

    public DBUsuarios(@Nullable Context context) {
        super(context);
        helper = new DbHelper(context);
    }

    public Usuario login(String email, String clave){
        //OBTENER AL USUARIO POR MEDIO DEL EMAIL Y CLAVE INGRESADA
        DbHelper helper = new DbHelper(contexto);  //PARA INTERACTUAR CON LA BASE DE DATOS
        SQLiteDatabase bd = helper.getReadableDatabase();
        Usuario objUsuario = null;
        Cursor cursor;

        cursor= bd.rawQuery("SELECT * FROM "+DB_TABLE_USERS+" WHERE email=? AND clave=?", new String[ ]{email, clave});
        //ES UNA CONSULTA CON DOS RESULTADOS POSIBLES, - NOS DEVUELVA UNA FILA(REGISTRO)->OBTIENE OBJETO TIPO USUARIO - NO DEVUELVE NADA
        if (cursor.moveToFirst()){
            objUsuario = new Usuario();
            objUsuario.setId(cursor.getInt(0));
            objUsuario.setNombres(cursor.getString(1));
            objUsuario.setApellidos(cursor.getString(2));
            objUsuario.setEmail(cursor.getString(3));
            objUsuario.setClave(cursor.getString(4));
        }
        return objUsuario;

    }

    //LONG ES COMO INT PERO ALMACENA NUMEROS MAS GRANDES
    public long insertar(Usuario user){
        SQLiteDatabase bd = helper.getWritableDatabase();
        //GUARDA LOS DATOS QUE SE QUIERES INSERTAR EN UN CONTENT VALUES
        ContentValues datos = new ContentValues();
        datos.put("Nombres", user.getNombres());
        datos.put("Apellidos", user.getApellidos());
        datos.put("Email", user.getEmail());
        datos.put("Clave", user.getClave());
        //PASAR EL NOMBRE DE LA TABLA, LAS COLUMNAS NULAS Y EL CONTENT VALUES CON LOS DATOS
        long id = bd.insert(DB_TABLE_USERS, null, datos);
        return id;
    }

    public ArrayList<Usuario> selectAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Usuario> arrayUsers = new ArrayList<>();
        Usuario user = null;
        Cursor cursor = null; //PARA MOVERSE ENTRE LOS REGISTROS DE LA BASE
        //GUARDAR LOS REGISTROS EN EL CURSOR
        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_USERS, null);
        if (cursor.moveToFirst()){
            do {
                user = new Usuario();
                user.setId(cursor.getInt(0));
                user.setNombres(cursor.getString(1));
                user.setApellidos(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setClave(cursor.getString(4));
                arrayUsers.add(user);
            }while (cursor.moveToNext());
        }
        return arrayUsers;
    }

}
