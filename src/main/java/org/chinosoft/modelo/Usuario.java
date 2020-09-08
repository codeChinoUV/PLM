package org.chinosoft.modelo;

import java.util.List;

public class Usuario {
    private String usuario;
    private String contrasena;
    private String tipo;
    private boolean activo;
    private Persona persona;

    public Usuario(String usuario, String tipo, Persona persona, boolean activo) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.persona = persona;
        this.activo = activo;
    }

    public Usuario(String usuario, String contrasena, String tipo) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public Usuario(){

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
