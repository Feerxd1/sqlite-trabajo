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
import com.morgan.sqlite.models.Producto;
import com.morgan.sqlite.sqlite.DBCategorias;
import com.morgan.sqlite.sqlite.DBProductos;
import com.morgan.sqlite.sqlite.DbHelper;

import java.util.ArrayList;

public class FormProducto extends AppCompatActivity {

    EditText etxtPNombre, etxtPMarca, etxtPModelo, etxtPPrecio, etxtPStock;
    Button btnProducto;
    Spinner spProductos, spCategoriasP;

    public void cargarSpProductos(){
        DBProductos bd = new DBProductos(this);
        ArrayList<Producto> productos =bd.obtenerProductos();

        if (productos != null){
            ArrayAdapter<Producto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, productos);
            spProductos.setAdapter(adapter);
        }

    }
    //CARGA LAS CATEGORIAS INGRESADAS EN LA BASE DE DATOS
    public void cargarSpCategoriasP(){
        DBCategorias bd = new DBCategorias(this);
        ArrayList<Categoria> categorias = bd.SelectAll();

        if (categorias != null){
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categorias);
            spCategoriasP.setAdapter(adapter);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_producto);
        DbHelper helper = new DbHelper(this);

        etxtPNombre = findViewById(R.id.etxtPNombre);
        etxtPMarca = findViewById(R.id.etxtPMarca);
        etxtPModelo = findViewById(R.id.etxtPModelo);
        etxtPPrecio = findViewById(R.id.etxtPPrecio);
        etxtPStock = findViewById(R.id.etxtPStock);
        btnProducto = findViewById(R.id.btnProducto);
        spProductos = findViewById(R.id.spProductos);
        spCategoriasP = findViewById(R.id.spCategoriasP);

        cargarSpCategoriasP();
        cargarSpProductos();

        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = etxtPNombre.getText().toString();
                String Marca = etxtPMarca.getText().toString();
                String Modelo = etxtPModelo.getText().toString();
                int Precio = Integer.parseInt(etxtPPrecio.getText().toString());
                int Stock = Integer.parseInt(etxtPStock.getText().toString());

                Categoria cat = (Categoria) spCategoriasP.getSelectedItem();
                int idcat = cat.getId();


                DBProductos bd = new DBProductos(FormProducto.this);
                Producto producto = new Producto(Nombre,Marca,Modelo,Precio,Stock, cat);
                long id = bd.insertarProducto(producto);
                if (id >0){
                    Toast.makeText(FormProducto.this, "Registrado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FormProducto.this, "No Registrado", Toast.LENGTH_SHORT).show();
                }
                cargarSpProductos();

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
            Intent intent_formCategoria = new Intent(FormProducto.this, FormCategoria.class);
            startActivity(intent_formCategoria);
        }
        if (item.getItemId() == R.id.mFormProducto){
            Intent intent_formProducto = new Intent(FormProducto.this, FormProducto.class);
            startActivity(intent_formProducto);
        }
        if (item.getItemId() == R.id.mLogin){
            Intent intent_login = new Intent(FormProducto.this, LoginActivity.class);
            startActivity(intent_login);
        }
        if (item.getItemId() == R.id.mRegistrarse){
            Intent intent_registrarse = new Intent(FormProducto.this, MainActivity.class);
            startActivity(intent_registrarse);
        }
        if (item.getItemId() == R.id.mListaProductos){
            Intent intent_listaProductos = new Intent(FormProducto.this, RecyclerProductos.class);
            startActivity(intent_listaProductos);
        }

        return super.onOptionsItemSelected(item);

    }


}