/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author Jose Mansilla
 */
public class Usuario {

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    private void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    private void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the isAdmin
     */
    public int Rol() {
        return rol;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setRol(int rol) {
        this.rol = rol;
    }

    /**
     * @return the fechaDeNacimiento
     */
    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    /**
     * @param fechaDeNacimiento the fechaDeNacimiento to set
     */
    private void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    private void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    private void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the fotografia
     */
    public String getFotografia() {
        return fotografia;
    }

    /**
     * @param fotografia the fotografia to set
     */
    private void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the estatus
     */
    public int getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    
    public Usuario(){
        
    }
    
    public Usuario(String usuario, String nombre, String apellido, String password, int rol, String fechaDeNacimiento, String correo, String telefono, String fotografia, String descripcion, int estatus){
        setUsuario(usuario);
        setNombre(nombre);
        setApellido(apellido);
        setPassword(password);
        setRol(rol);
        setFechaDeNacimiento(fechaDeNacimiento);
        setCorreo(correo);
        setTelefono(telefono);
        setFotografia(fotografia);
        setDescripcion(descripcion);
        setEstatus(estatus);
    }
    
    
    private String usuario; 
    private String nombre; 
    private String apellido; 
    private String password; 
    private int rol;
    private String fechaDeNacimiento; 
    private String correo;
    private String telefono;
    private String fotografia;
    private String descripcion;
    private int estatus;
}
