package com.example.edumatch;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.edumatch.retrofit.Constants;
import com.example.edumatch.retrofit.interfaces.ProjectApi;
import com.example.edumatch.retrofit.model.ProjectRequest;
import com.example.edumatch.retrofit.model.ProjectResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectCreateActivity extends AppCompatActivity {
     private String auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);


        /* variables de los campos del formulario
        String name_project = getString(R.string.nombre_project);
        String descripcion = getString(R.string.descripcion);
        String mail = getString(R.string.email);*/

        Button btn_cancela = (Button) findViewById(R.id.btn_cancelar);
        Button btn_publica = (Button) findViewById(R.id.btn_publicar);
        EditText nombre_project = findViewById(R.id.nombre_project);
        EditText descripcionEditText = findViewById(R.id.descripcion);
        EditText email = findViewById(R.id.email);

        // Muestra una ventana de diálogo cuando se presiona publicar
        btn_publica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventanaMensaje();

                // Recopila los datos del proyecto desde los campos de entrada del usuario
                String nombreProyecto = nombre_project.getText().toString();
                String descripcionProyecto = descripcionEditText.getText().toString();
                String emailUsuario = email.getText().toString();

                //Crea una instancia de ProjectResponse y establece los datos
                ProjectResponse proyecto = new ProjectResponse();
                proyecto.setName(nombreProyecto);
                proyecto.setDescription(descripcionProyecto);
                proyecto.setEmail(emailUsuario);

                // Crea una instancia de Retrofit y ProjectApi
                Retrofit retrofit = new Retrofit.Builder()
                        //modifico la base url para que tome el que viene de constants
                        .baseUrl(Constants.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ProjectApi projectApi = retrofit.create(ProjectApi.class);
                ProjectRequest projectRequest = new ProjectRequest(nombreProyecto, descripcionProyecto, emailUsuario );

                //modificacion del codigo por brian
                SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
                String jwtToken = sharedPreferences.getString("Jwt", null);
                Log.d("TAG", "token "+ jwtToken);
                jwtToken = "Bearer "+ jwtToken;


                if (jwtToken != null) {
                    Call<ProjectResponse> call = projectApi.createProject(jwtToken, proyecto);
                    call.enqueue(new Callback<ProjectResponse>() {
                        @Override
                        public void onResponse(Call<ProjectResponse> call, Response<ProjectResponse> response) {
                            if (response.isSuccessful()) {

                                ProjectResponse projectResponse = response.body();
                                // La solicitud se realizó con éxito, puedes mostrar un mensaje de confirmación
                                ventanaMensaje();
                            } else {
                                // La solicitud falló, muestra un mensaje de error si es necesario
                                // Log.e("Error", "Código de respuesta: " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<ProjectResponse> call, Throwable t) {
                            // La solicitud falló debido a un error de red, muestra un mensaje de error si es necesario
                            // Log.e("Error", "Error de red: " + t.getMessage());
                        }
                    });

                } else {
                    // El token JWT no se encontró en SharedPreferences
                }


                // Realiza la solicitud POST


                /*Call<ProjectResponse> call = projectApi.createProject( "baseurl", proyecto);

                call.enqueue(new Callback<ProjectResponse>() {
                    @Override
                    public void onResponse(Call<ProjectResponse> call, Response<ProjectResponse> response) {
                        if (response.isSuccessful()) {
                            // La solicitud se realizó con éxito, puedes mostrar un mensaje de confirmación
                            ventanaMensaje();
                        } else {
                            // La solicitud falló, muestra un mensaje de error si es necesario
                            // Log.e("Error", "Código de respuesta: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ProjectResponse> call, Throwable t) {
                        // La solicitud falló debido a un error de red, muestra un mensaje de error si es necesario
                        // Log.e("Error", "Error de red: " + t.getMessage());
                    }
                });

                Intent intent = new Intent(ProjectCreateActivity.this, ProjectsActivity.class);
                startActivity(intent);*/
            }
        });

        btn_cancela.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                // debería ir a la página principal
                Intent intent = new Intent(ProjectCreateActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }

    //Lanza Ventana De Dialogo Cuando se Presiona El boton Publicar
    private void ventanaMensaje() {
        new AlertDialog.Builder(this)
                .setTitle("EDU-MATCH")
                .setMessage("Proyecto Cargado Correctamente")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Log.d("Mensaje", "Cargo todo Ok");
                        Intent intent = new Intent(ProjectCreateActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}
