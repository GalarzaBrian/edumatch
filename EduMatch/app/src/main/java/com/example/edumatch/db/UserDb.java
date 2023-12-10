package com.example.edumatch.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDb  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ispc";
    private static final int DATABASE_VERSION = 1;

    // Sentencia SQL para crear la tabla 'project'
    private static final String CREATE_TABLE_PROJECT =
            "CREATE TABLE project (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT, fecha_finalizacion DATETIME, fecha_creacion DATETIME, email TEXT);";

    // Sentencia SQL para crear la tabla 'usuarios'
    private static final String CREATE_TABLE_USUARIOS =
            "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT);";

    // Sentencia SQL para crear la tabla 'project_user'
    private static final String CREATE_TABLE_PROJECT_USER =
            "CREATE TABLE project_user (projectId INTEGER, userId INTEGER, PRIMARY KEY (projectId, userId), FOREIGN KEY (projectId) REFERENCES project(id), FOREIGN KEY (userId) REFERENCES usuarios(id));";

    public UserDb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PROJECT);
        sqLiteDatabase.execSQL(CREATE_TABLE_USUARIOS);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROJECT_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS project");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuarios");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS project_user");
        onCreate(sqLiteDatabase);
    }
}
