package com.example.edumatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edumatch.retrofit.Constants;
import com.example.edumatch.retrofit.interfaces.UserApi;
import com.example.edumatch.retrofit.model.ErrorResponse;
import com.example.edumatch.retrofit.model.RegisterRequest;
import com.example.edumatch.retrofit.model.RegisterResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEdt;
    private EditText dniEdt;
    private EditText passwordEdt;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEdt = findViewById(R.id.EtfCorreo);
        dniEdt = findViewById(R.id.EtfDni);
        passwordEdt = findViewById(R.id.EtfPassword);
        registerButton = findViewById(R.id.RegisterButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailEdt.getText().toString();
                String dniString=dniEdt.getText().toString();
                Long dni = Long.parseLong(dniString);
                String password=passwordEdt.getText().toString();
                Long roleId =1L;

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserApi userApi = retrofit.create(UserApi.class);

                RegisterRequest registerRequest = new RegisterRequest(dni,email, password,roleId);


                Call<RegisterResponse> call = userApi.createUser(registerRequest);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if(response.isSuccessful()){

                            RegisterResponse registerResponse = response.body();

                            SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Jwt", registerResponse.jwt);
                            editor.putString("MailUsuario", registerResponse.email);
                            editor.apply();

                            Toast.makeText(RegisterActivity.this, "Usuario creado satisfactoriamente", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                            startActivity(intent);

                        } else{

                            int statusCode = response.code();
                            String errorMessage = "Ocurrio un error";

                            try {
                                Converter<ResponseBody, ErrorResponse> errorConverter =
                                        retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
                                ErrorResponse error = errorConverter.convert(response.errorBody());
                                errorMessage = error.getMessage();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(RegisterActivity.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        Log.e("hubo un error: ",t.getMessage().toString());
                    }
                });
            }
        });



        TextView textView = findViewById(R.id.tvLogin);

        // Obtén el texto del TextView
        String texto = textView.getText().toString();

        // Crea una instancia de SpannableString para el texto
        SpannableString spannableString = new SpannableString(texto);

        // Encuentra la posición del enlace en el texto
        int inicioEnlace = texto.indexOf("Entrar");
        int finEnlace = inicioEnlace + "Entrar".length();

        // Crea un ClickableSpan para el enlace
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

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