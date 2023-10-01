package com.example.edumatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView = findViewById(R.id.tvRegister);

        // Obtén el texto del TextView
        String texto = textView.getText().toString();

        // Crea una instancia de SpannableString para el texto
        SpannableString spannableString = new SpannableString(texto);

        // Encuentra la posición del enlace en el texto
        int inicioEnlace = texto.indexOf("Registrate");
        int finEnlace = inicioEnlace + "Registrate".length();

        // Crea un ClickableSpan para el enlace
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Acción al hacer clic en el enlace (abrir el navegador)
               //Uri uri = Uri.parse("http://www.ejemplo.com");
                //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(intent);
            }
        };

        // Aplica el ClickableSpan al texto enlazado
        spannableString.setSpan(clickableSpan, inicioEnlace, finEnlace, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Establece el texto en el TextView
        textView.setText(spannableString);

        // Habilita el movimiento del enlace (clickeable)
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    }
