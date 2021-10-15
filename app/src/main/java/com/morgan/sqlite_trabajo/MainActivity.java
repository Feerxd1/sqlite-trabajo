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
import android.widget.TextView;

import com.morgan.sqlite.models.Usuario;
import com.morgan.sqlite.sqlite.DBUsuarios;

public class MainActivity extends AppCompatActivity {

    TextView txtINombres, txtIApellidos, txtIEmail, txtIClave;
    EditText editTextNombres, editTextApellidos, editTextEmail, editTextClave;
    Button btnGuardar;
    Spinner spUsuarios;

    public void cargarSpinner(){
        DBUsuarios db = new DBUsuarios(this);
        ArrayAdapter<Usuario> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, db.selectAll());

        spUsuarios.setAdapter(adaptador);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombres = findViewById(R.id.eTextNombres);
        editTextApellidos = findViewById(R.id.eTextApellidos);
        editTextEmail = findViewById(R.id.eTextEmail);
        editTextClave = findViewById(R.id.eTextClave);
        btnGuardar = findViewById(R.id.btnGuardar);
        spUsuarios = findViewById(R.id.spUsuarios);

        cargarSpinner();
        //OBTENER LA BASE DE DATOS EN MODO DE LECTURA
        //DbHelper ayudanteBD = new DbHelper(this);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OBTENER LOS DATOS DE LAS CAJAS
                String nombres = editTextNombres.getText().toString();
                String apellidos = editTextApellidos.getText().toString();
                String email = editTextEmail.getText().toString();
                String clave = editTextClave.getText().toString();

                //HACER UN INSERT DE USUARIO
                Usuario user = new Usuario(nombres,apellidos,email,clave);

                DBUsuarios dbusuarios = new DBUsuarios(MainActivity.this);
                dbusuarios.insertar(user);
                cargarSpinner();
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
            Intent intent_formCategoria = new Intent(MainActivity.this, FormCategoria.class);
            startActivity(intent_formCategoria);
        }
        if (item.getItemId() == R.id.mFormProducto){
            Intent intent_formProducto = new Intent(MainActivity.this, FormProducto.class);
            startActivity(intent_formProducto);
        }
        if (item.getItemId() == R.id.mLogin){
            Intent intent_login = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent_login);
        }
        if (item.getItemId() == R.id.mRegistrarse){
            Intent intent_registrarse = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent_registrarse);
        }
        if (item.getItemId() == R.id.mListaProductos){
            Intent intent_listaProductos = new Intent(MainActivity.this, RecyclerProductos.class);
            startActivity(intent_listaProductos);
        }

        return super.onOptionsItemSelected(item);

    }
}