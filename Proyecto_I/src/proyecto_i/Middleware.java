/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.FileNotFoundException;

/**
 *
 * @author sebas
 */
public class Middleware {
    ManejadorDeUsuarios MDU = new ManejadorDeUsuarios();
    
    // #grupo, usuario_emisor, usuario_receptor, fecha, mensaje.
    public int GroupNumber =0;
    public String Message ="";
    
    public boolean checkUserExist(String usuario) throws FileNotFoundException
    {
        Usuario user = MDU.getUserData(usuario);
        return (user!=null) ? true : false;
    }
}
