package com.example.edumatch;
import com.example.edumatch.retrofit.model.ProjectResponse;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProjectDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        // Obtiene los datos del proyecto de Intent
        ProjectResponse proyecto = getIntent().getParcelableExtra("project");

        // Muestra los datos del proyecto en tu diseño XML
        TextView nombreTextView = findViewById(R.id.nombreProyectoTextView);
        TextView descripcionTextView = findViewById(R.id.descripcionProyectoTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);

        nombreTextView.setText(proyecto.getName());
        descripcionTextView.setText(proyecto.getDescription());
        emailTextView.setText(proyecto.getEmail());

        // Puedes mostrar otros detalles del proyecto según sea necesario
    }
}