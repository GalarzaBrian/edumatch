package com.example.edumatch.retrofit.model;

public class UserProfileResponse {
    public int dni;
    public String mail;
    public String nombre;
    public String about;

    public UserProfileResponse(int dni, String mail, String nombre, String about) {
        this.dni = dni;
        this.mail = mail;
        this.nombre = nombre;
        this.about = about;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
