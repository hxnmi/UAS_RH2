package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

public class Users {
    String email;
    String password;
    String Fname;
    String Lname;

    public Users(){

    }

    public String getFname(){
        return Fname;
    }

    public void SetFName(String fname){
        this.Fname = fname;
    }

    public String getLname(){
        return Lname;
    }

    public void SetLName(String lname){
        this.Lname = lname;
    }

    public String getEmail(){
        return email;
    }

    public void SetEmail(String Email){
        this.email = Email;
    }

    public String getPassword(){
        return password;
    }

    public void SetPassword(String Password){
        this.password = Password;
    }
}