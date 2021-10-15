package com.morgan.sqlite_trabajo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.morgan.sqlite.models.Usuario;
import com.morgan.sqlite.sqlite.DBUsuarios;

public class LoginActivity extends AppCompatActivity {

    EditText eTextLoginEmail, eTextLoginClave;
    Button btnLogin, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eTextLoginEmail = findViewById(R.id.eTextLoginEmail);
        eTextLoginClave = findViewById(R.id.eTextLoginClave);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUsuarios db = new DBUsuarios(LoginActivity.this);
                //OBTENER LOS DATOS PARA PASARLOS AL LOGIN DESDE LA CLASE DBUSUARIOS
                String email = eTextLoginEmail.getText().toString();
                String clave = eTextLoginClave.getText().toString();

                Usuario userLog = db.login(email, clave);
                //USERLOG PUEDE SER NULL
                if (userLog == null){ //ERROR DE CREDENCIALES(EMAIL, CLAVE)
                    Toast.makeText(LoginActivity.this, "ERROR DE CREDENCIALES ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "BIENVENIDX "+userLog.getNombres(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, FormProducto.class);
                    startActivity(intent);
                }

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
            Intent intent_formCategoria = new Intent(LoginActivity.this, FormCategoria.class);
            startActivity(intent_formCategoria);
        }
        if (item.getItemId() == R.id.mFormProducto){
            Intent intent_formProducto = new Intent(LoginActivity.this, FormProducto.class);
            startActivity(intent_formProducto);
        }
        if (item.getItemId() == R.id.mLogin){
            Intent intent_login = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent_login);
        }
        if (item.getItemId() == R.id.mRegistrarse){
            Intent intent_registrarse = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent_registrarse);
        }
        if (item.getItemId() == R.id.mListaProductos){
            Intent intent_listaProductos = new Intent(LoginActivity.this, RecyclerProductos.class);
            startActivity(intent_listaProductos);
        }

        return super.onOptionsItemSelected(item);

    }

}