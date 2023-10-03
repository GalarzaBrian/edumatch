package com.example.edumatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    Button btnContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        //Declaramos el boton Contacto que nos enviara a dicha activity
        btnContacto = findViewById(R.id.btnContacto);
        Button btn_proyecto = (Button) findViewById(R.id.button_registrar_proyecto);


        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, ContactoActivity.class);
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

