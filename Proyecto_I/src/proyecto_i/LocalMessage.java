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
public class LocalMessage {

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usuarioAmigo
     */
    public String getUsuarioAmigo() {
        return usuarioAmigo;
    }

    /**
     * @param usuarioAmigo the usuarioAmigo to set
     */
    public void setUsuarioAmigo(String usuarioAmigo) {
        this.usuarioAmigo = usuarioAmigo;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the tipoDeMensaje
     */
    public int getTipoDeMensaje() {
        return tipoDeMensaje;
    }

    /**
     * @param tipoDeMensaje the tipoDeMensaje to set
     */
    public void setTipoDeMensaje(int tipoDeMensaje) {
        this.tipoDeMensaje = tipoDeMensaje;
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
    private String usuario; 
    private String usuarioAmigo; 
    private String fecha; 
    private String mensaje;
    private int tipoDeMensaje;
    private int estatus;
    public  LocalMessage(String usuario, String usuarioAmigo, String fecha, String mensaje, int tipoDeMensaje, int estatus){
        setUsuario(usuario);
        setUsuarioAmigo(usuarioAmigo);
        setMensaje(mensaje);
        setFecha(fecha);
        setTipoDeMensaje(tipoDeMensaje);
        setEstatus(estatus);
    }
}
