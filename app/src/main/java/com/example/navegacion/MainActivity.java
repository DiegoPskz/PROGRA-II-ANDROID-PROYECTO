package com.example.navegacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnpersona;
    private Button btnproducto;
    private Button btnordenes;
    private Button btninformes;
    private Button btnconfiguracion;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        event();
        firebaseAuth = FirebaseAuth.getInstance();
        iniciarSesion("paszkiewiczdiego28@gmail.com", "Diego2822*");

    }

    private void bindUI() {
        btnpersona = findViewById(R.id.btnpersona);
        btnproducto = findViewById(R.id.btnproducto);
        btnordenes = findViewById(R.id.btnordenes);
        btninformes = findViewById(R.id.btninformes);
        btnconfiguracion = findViewById(R.id.btnconfiguracion);
    }

    private void event() {
       btnpersona.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, PersonaActivity.class);
               startActivity(intent);
           }
       });

        btnproducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductoActivity.class);
                startActivity(intent);
            }
        });

        btnordenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrdenesActivity.class);
                startActivity(intent);
            }
        });
        btninformes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InformeActivity.class);
                startActivity(intent);
            }
        });

        btnconfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfiguracionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarSesion(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                MainActivity.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                }
        ).addOnFailureListener(e -> {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Ocurrió un error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

}