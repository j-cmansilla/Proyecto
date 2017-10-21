/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

/**
 *
 * @author Jose Mansilla
 */
public class Grupo {

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
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
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the miembros
     */
    public int getMiembros() {
        return miembros;
    }

    /**
     * @param miembros the miembros to set
     */
    public void setMiembros(int miembros) {
        this.miembros = miembros;
    }

    /**
     * @return the fechaDeTransaccion
     */
    public String getFechaDeTransaccion() {
        return fechaDeTransaccion;
    }

    /**
     * @param fechaDeTransaccion the fechaDeTransaccion to set
     */
    public void setFechaDeTransaccion(String fechaDeTransaccion) {
        this.fechaDeTransaccion = fechaDeTransaccion;
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
    
    public Grupo(){
        
    }
    
    public Grupo(Usuario usuario, String grupo, String descripcion, int miembros, String fechaDeTransaccion, int estatus){
        this.usuario = usuario;
        this.grupo = grupo;
        this.descripcion = descripcion;
        this.miembros = miembros;
        this.fechaDeTransaccion = fechaDeTransaccion;
        this.estatus = estatus;
    }
    
    public Usuario usuario;
    private String grupo;
    public String descripcion; 
    private int miembros; 
    private String fechaDeTransaccion;
    private int estatus;
}
