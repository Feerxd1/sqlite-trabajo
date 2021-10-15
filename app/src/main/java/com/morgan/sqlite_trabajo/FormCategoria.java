package com.morgan.sqlite_trabajo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.morgan.sqlite.models.Categoria;
import com.morgan.sqlite.sqlite.DBCategorias;

import java.util.ArrayList;

public class FormCategoria extends AppCompatActivity {

    EditText eTextNombreCat;
    Button btnGuardar;
    Spinner spCategorias;

    public void cargarSpCategorias(){
        DBCategorias bd = new DBCategorias(this);
        ArrayList<Categoria> misCategorias = bd.SelectAll();

        if (misCategorias != null){
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, misCategorias);
            spCategorias.setAdapter(adapter);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_categoria);

        eTextNombreCat = findViewById(R.id.eTextNombreCat);
        btnGuardar = findViewById(R.id.btnCategoria);
        spCategorias = findViewById(R.id.spCategorias);

        cargarSpCategorias();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = eTextNombreCat.getText().toString();
                DBCategorias bd = new DBCategorias(FormCategoria.this);
                long id = bd.insertarCategoria(Nombre);
                if (id>0){
                    Toast.makeText(FormCategoria.this, "Categoria Registrada", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FormCategoria.this, "Categoria no Registrada", Toast.LENGTH_SHORT).show();
                }
                cargarSpCategorias();
            }

        });

    }
    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mFormCategoria){
            Intent intent_formCategoria = new Intent(FormCategoria.this, FormCategoria.class);
            startActivity(intent_formCategoria);
        }
        if (item.getItemId() == R.id.mFormProducto){
            Intent intent_formProducto = new Intent(FormCategoria.this, FormProducto.class);
            startActivity(intent_formProducto);
        }
        if (item.getItemId() == R.id.mLogin){
            Intent intent_login = new Intent(FormCategoria.this, LoginActivity.class);
            startActivity(intent_login);
        }
        if (item.getItemId() == R.id.mRegistrarse){
            Intent intent_registrarse = new Intent(FormCategoria.this, MainActivity.class);
            startActivity(intent_registrarse);
        }
        if (item.getItemId() == R.id.mListaProductos){
            Intent intent_listaProductos = new Intent(FormCategoria.this, RecyclerProductos.class);
            startActivity(intent_listaProductos);
        }

        return super.onOptionsItemSelected(item);

    }

}