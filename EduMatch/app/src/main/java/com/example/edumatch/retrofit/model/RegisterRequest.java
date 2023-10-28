package com.example.edumatch.retrofit.model;

//         "firstName":"Danito",
//         "lastName":"Vargas",
//         "email":"admin@mail",
//         "password":"password",
//         "roleId": 1,
//         "photo":"lafoto"
public class RegisterRequest {

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    private Long dni;
    private String email;
    private String password;
     private Long roleId;

    public RegisterRequest(Long dni, String email, String password, Long roleId) {
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
