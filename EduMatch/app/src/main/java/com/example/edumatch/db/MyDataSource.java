package com.example.edumatch.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.edumatch.retrofit.model.ProjectResponse;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class MyDataSource {

    private SQLiteDatabase database;
    private UserDb dbHelper;

    public MyDataSource(Context context) {
        dbHelper = new UserDb(context);
    }

    public void open() throws SQLException {
        // Abre la base de datos para escritura
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        // Cierra la base de datos
        dbHelper.close();
    }

    // Métodos para manipular la tabla 'project'
    public long insertProject(String nombre, String descripcion, String email) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("descripcion", descripcion);
       values.put("email",email);
        // values.put("fecha_finalizacion", fechaFinalizacion);
        values.put("fecha_creacion", getOffsetDateTime().toString());
        return database.insert("project", null, values);
    }

    // Métodos para manipular la tabla 'usuarios'
    public long insertUsuario(String email) {
        ContentValues values = new ContentValues();
        values.put("email", email);
        return database.insert("usuarios", null, values);
    }

    // Métodos para manipular la tabla 'project_user'
    public void insertProjectUser(long projectId, long userId) {
        ContentValues values = new ContentValues();
        values.put("projectId", projectId);
        values.put("userId", userId);
        database.insert("project_user", null, values);
    }

    public Cursor getProjectsForUser(long userId) {
        // Ejemplo de consulta para obtener proyectos asociados a un usuario
        String query = "SELECT project.* FROM project " +
                "INNER JOIN project_user ON project.id = project_user.projectId " +
                "WHERE project_user.userId = ?";
        return database.rawQuery(query, new String[]{String.valueOf(userId)});
    }

    // Otros métodos para realizar actualización y eliminación (Update y Delete) según sea necesario

    public List<ProjectResponse> getAllProjects() {
        List<projectModel> projectResponse = new ArrayList<>();
        List<ProjectResponse> projects = new ArrayList<>();
        // Realiza una consulta para obtener todos los proyectos
        Cursor cursor = database.query("project", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Crea objetos Project a partir de los datos del cursor
                projectModel project = new projectModel();
                //project.setId(cursor.getInt(cursor.getColumnIndex("id")));
                project.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                project.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
                project.setFechaFinalizacion(cursor.getString(cursor.getColumnIndexOrThrow("fecha_finalizacion")));

                ProjectResponse projectResponses = new ProjectResponse();
                projectResponses.setEmail("email");
                projectResponses.setEndDate(cursor.getString(cursor.getColumnIndexOrThrow("fecha_finalizacion")));
                projectResponses.setName(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                projectResponses.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));

                // Agrega el proyecto a la lista
                projects.add(projectResponses);
            } while (cursor.moveToNext());

            // Cierra el cursor
            cursor.close();
        }

        return projects;
    }
    public OffsetDateTime getOffsetDateTime() {
        OffsetDateTime offsetDateTime = null;
        Instant instant = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            instant = Instant.now();
        }

        // Especifica la zona horaria deseada (por ejemplo, UTC+0)
        ZoneOffset offset = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            offset = ZoneOffset.UTC;
        }

        // Crea un objeto OffsetDateTime utilizando la fecha y hora actual y la zona horaria
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            offsetDateTime = OffsetDateTime.ofInstant(instant, offset);
        }
    return offsetDateTime;
    };
}