package com.eua.SalesTrackingApp.models;

/**
 * Created by rubymobile on 2/2/16.
 */
public class User {
    private String UsuarioEmail;

    private String UsuarioNombre;

    private String UsuarioId;

    public void setEmail(String email){
        this.UsuarioEmail = email;
    }

    public void setName(String name){
        this.UsuarioNombre = name;
    }

    public void setId(String id){
        this.UsuarioId = id;
    }

    public String getEmail(){
        return this.UsuarioEmail;
    }

    public String getName(){
        return this.UsuarioNombre;
    }
    public String getId(){
        return this.UsuarioId;
    }
}