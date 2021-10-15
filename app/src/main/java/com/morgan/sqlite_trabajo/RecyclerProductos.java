package com.morgan.sqlite_trabajo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.morgan.sqlite_trabajo.adapters.ProductoAdapter;
import com.morgan.sqlite_trabajo.models.Categoria;
import com.morgan.sqlite_trabajo.models.Producto;
import com.morgan.sqlite_trabajo.sqlite.DBCategorias;

import java.util.ArrayList;

public class RecyclerProductos extends AppCompatActivity {

    Spinner spRCategorias;
    RecyclerView recyclerProducto;
    public static ArrayList<Producto> arrayProductos = new ArrayList<>();

    public void cargarSpRCategorias(){
        DBCategorias bd = new DBCategorias(this);
        ArrayList<Categoria> misCategorias = bd.SelectAll();

        if (misCategorias != null){
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, misCategorias);
            spRCategorias.setAdapter(adapter);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_productos);
        //DbHelper helper = new DbHelper(this);


        spRCategorias = findViewById(R.id.spRCategorias);
        recyclerProducto = findViewById(R.id.recyclerProducto);
        recyclerProducto.setLayoutManager(new LinearLayoutManager(RecyclerProductos.this));

        ProductoAdapter adapter = new ProductoAdapter(arrayProductos);
        recyclerProducto.setAdapter(adapter);

        cargarSpRCategorias();

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
            Intent intent_formCategoria = new Intent(RecyclerProductos.this, FormCategoria.class);
            startActivity(intent_formCategoria);
        }
        if (item.getItemId() == R.id.mFormProducto){
            Intent intent_formProducto = new Intent(RecyclerProductos.this, FormProducto.class);
            startActivity(intent_formProducto);
        }
        if (item.getItemId() == R.id.mLogin){
            Intent intent_login = new Intent(RecyclerProductos.this, LoginActivity.class);
            startActivity(intent_login);
        }
        if (item.getItemId() == R.id.mRegistrarse){
            Intent intent_registrarse = new Intent(RecyclerProductos.this, MainActivity.class);
            startActivity(intent_registrarse);
        }
        if (item.getItemId() == R.id.mListaProductos){
            Intent intent_listaProductos = new Intent(RecyclerProductos.this, RecyclerProductos.class);
            startActivity(intent_listaProductos);
        }

        return super.onOptionsItemSelected(item);

    }

}