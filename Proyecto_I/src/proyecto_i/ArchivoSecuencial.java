/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Jose Mansilla
 */
public class ArchivoSecuencial {
    private static RandomAccessFile flujo;
    private static int numeroDeRegistros;
    private static final int TAMANIO_REGISTROS  = 160;
    
    public static int getNumeroDeRegistros(){
        return numeroDeRegistros;
    }
    
    public static void crearArchivoBitacora(File archivo) throws FileNotFoundException, IOException{
        if (archivo.exists() && !archivo.isFile()) {
            
        }
        flujo = new RandomAccessFile(archivo, "rw");
        numeroDeRegistros = (int)Math.ceil((double)flujo.length()/(double)TAMANIO_REGISTROS);
    }
    
    public static void cerrar() throws IOException{
        flujo.close();
    }
    
    public static boolean setUsuario(int i, String usuario) throws IOException{
        if (i>=0 && i<=numeroDeRegistros) {
            if (usuario.length() > TAMANIO_REGISTROS) {
                
            }else{
                flujo.seek(flujo.length());
                flujo.writeBytes(usuario);
                return true;
            }
        }
        return false;
    }
    
    public static void agregarUsuario(String usuario) throws IOException{
        if (setUsuario(numeroDeRegistros, usuario)) {
            numeroDeRegistros++;
        }
    }
}
