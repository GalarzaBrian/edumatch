package com.example.edumatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactoActivity extends AppCompatActivity {

    EditText etEmail, etAsunto, etMensajes;
    Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        etEmail = findViewById(R.id.etEmail);
        etAsunto = findViewById(R.id.etAsunto);
        etMensajes = findViewById(R.id.etMensajes);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = etEmail.getText().toString();
                String asunto = etAsunto.getText().toString();
                String mensaje = etMensajes.getText().toString();

                if(correo.equals(""))
                {
                    Toast.makeText(ContactoActivity.this,"ingresa un correo", Toast.LENGTH_LONG).show();
                }


                else if(asunto.equals(""))
                {
                    Toast.makeText(ContactoActivity.this,"ingresa el asunto", Toast.LENGTH_LONG).show();
                }


                else if(mensaje.equals(""))
                {
                    Toast.makeText(ContactoActivity.this, "ingresa un mensaje", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // Defino mi intent y hago uso del objeto ACTION_SEND
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL,
                            new String[]{correo});
                    intent.putExtra(Intent.EXTRA_SUBJECT,asunto);
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje);
                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent, "Elije un cliente de correo:"));
                }
            }
        });
    }
}