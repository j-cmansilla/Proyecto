/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Jose Mansilla
 */
public class Proyecto_I {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        // TODO code application logic here
        Singleton.getInstancia().conexion();
        File directorio = new File("c:\\MEIA");
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        ManejadorDeGrupos manejadorG = new ManejadorDeGrupos();
        ManejadorDeAmigos manejadorA = new ManejadorDeAmigos();
        MantenimientoAsociacionAmigosGrupo manAAG = new MantenimientoAsociacionAmigosGrupo();
        ManejadorDeImagenes manejadorI = new ManejadorDeImagenes();
        LocalMessageManejador locMeMa = new LocalMessageManejador();
        Middleware m = new Middleware();
        if (!directorio.exists()) {
            directorio.mkdirs();
            manejador.CrearArchivos();
            manejadorG.CrearArchivos();
            manejadorA.CrearArchivos();
            manAAG.CrearArchivos();
            manejadorI.CrearArchivos();
            m.CrearArchivos();
            locMeMa.CrearArchivos();
        }
        
        if(directorio.isDirectory() && directorio.list().length == 0) {
            manejador.CrearArchivos();
            manejadorG.CrearArchivos();
            manejadorA.CrearArchivos();
            manAAG.CrearArchivos();
            manejadorI.CrearArchivos();
            m.CrearArchivos();
            locMeMa.CrearArchivos();
        } 

        Login login = new Login();
        login.show();
    }
    
}
