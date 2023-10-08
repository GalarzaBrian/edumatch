package com.example.edumatch.retrofit.model;

//         "firstName":"Danito",
//         "lastName":"Vargas",
//         "email":"admin@mail",
//         "password":"password",
//         "roleId": 1,
//         "photo":"lafoto"
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private int roleId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
