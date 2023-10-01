package com.example.edumatch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ProjectCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);


        // variables de los campos del formulario
        String name_project = getString(R.string.nombre_project);
        String descripcion = getString(R.string.descripcion);
        String mail = getString(R.string.email);


        Button btn_cancela = (Button) findViewById(R.id.btn_cancelar);
        Button btn_publica = (Button) findViewById(R.id.btn_publicar);


        // Muestra una ventana de dialogo cuando se presiona publicar
        btn_publica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventanaMensaje();


            }
        });
    }


    private void ventanaMensaje() {
        new AlertDialog.Builder(this)
                .setTitle("Proyecto")
                .setMessage("Proyecto Cargado Correctamente")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Log.d("Mensaje", "Cargo todo Ok");
                    }
                })
                .show();
    }
}


//      hay que habilitar cuando se tenga armado el home
//      btn_cancela.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//                // deberia ir home
//            }
//        });

