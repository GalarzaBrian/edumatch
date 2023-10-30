package com.example.edumatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    Button btnContacto;
    Button btnProfile;
    Button btnProyecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        //Declaramos el boton Contacto que nos enviara a dicha activity
        btnContacto = findViewById(R.id.btnContacto);
        btnProfile = findViewById(R.id.button_profile);
        Button btn_proyecto = (Button) findViewById(R.id.button_registrar_proyecto);
        btnProyecto = findViewById(R.id.button_mi_proyecto);


        btnProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent para iniciar ProjectDetailActivity
                Intent intent = new Intent(HomePageActivity.this, ProjectSearchActivity.class);
                startActivity(intent);
            }
        });

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, ContactoActivity.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

        btn_proyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, ProjectCreateActivity.class);
                startActivity(intent);

            }
        });
        //codigo para acceder a ContactoActivity
        btnContacto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, ContactoActivity.class);
                startActivity(intent);
            }
        });
    }
}

