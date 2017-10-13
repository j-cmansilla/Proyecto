/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Mansilla
 */
public class ManejadorDeGrupos {
    
    private final String DEFAULT_GROUP_DIRECTORY = "Grupos.txt";
    private final String DEFAULT_BITACORA_GROUP_DIRECTORY = "BitacoraGrupos.txt";
    private final String DEFAULT_TEMP_DIRECTORY = "Temp.txt";
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    private final String DEFAULT_DES_DIR = "C:\\MEIA\\Desc_";
    private final String DEFAULT_LOGIN_USER_DIRECTORY = "C:\\MEIA\\UsuarioLogueado.txt";
    
    REO reorganize = new REO();
    
    public void CrearArchivos(){
        try{
            File archivoGrupos = new File(DEFAULT_DIRECTORY+DEFAULT_GROUP_DIRECTORY);
            File archivoDescGrupos = new File(DEFAULT_DES_DIR+DEFAULT_GROUP_DIRECTORY);
            File archivoBitacoraGrupos = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_GROUP_DIRECTORY);
            File descriptorBitacoraGrupos = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_GROUP_DIRECTORY);
            archivoGrupos.createNewFile();
            archivoDescGrupos.createNewFile();
            archivoBitacoraGrupos.createNewFile();
            descriptorBitacoraGrupos.createNewFile();
        }catch(IOException e){
            
        }
    }
    
    private boolean llenarBitacora(Usuario usuario, String grupo_nombre, String descripcion) throws FileNotFoundException, IOException{
        File desBitacora = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_GROUP_DIRECTORY);
        boolean isFull = false;
        if (!desBitacora.exists())return false; 
        Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_BITACORA_GROUP_DIRECTORY);
        File archivo = new File(scanner.nextLine());
        scanner = new Scanner(archivo);
        String newGroup = "";
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        Grupo grupo = new Grupo(usuario, grupo_nombre, descripcion, 0, zdt.toString(), 0);
        if (!scanner.hasNextLine()) {
            newGroup = usuario.getNombre()+System.getProperty("line.separator")+zdt.toString()+System.getProperty("line.separator")+"not modified"+System.getProperty("line.separator")+"1"+System.getProperty("line.separator")+"0"+System.getProperty("line.separator")+"3";
        }else{
            ArrayList lista = new ArrayList();
                while(scanner.hasNextLine()){
                    lista.add(scanner.nextLine());
                }
                scanner.close();
                int count = Integer.parseInt(lista.get(3).toString()) + Integer.parseInt(lista.get(4).toString()); 
                int lenght = Integer.parseInt(lista.get(5).toString());
                if (count+1 > lenght) {
                    isFull = true;
                    for (int i = 0; i < lista.size(); i++) {
                        if (i==0) {
                            newGroup = newGroup+usuario.getUsuario()+System.getProperty("line.separator");
                        }else{
                            if (i == 2) {
                            newGroup = newGroup+zdt.toString()+System.getProperty("line.separator");
                            }else{
                                if (i == 4) {
                                    newGroup = newGroup+"0"+System.getProperty("line.separator");
                                }else{
                                    if (i == 3) {
                                        newGroup = newGroup+"1"+System.getProperty("line.separator");
                                    }else{  
                                        newGroup = newGroup+lista.get(i)+System.getProperty("line.separator");
                                    }
                                }
                            }
                        }
                    } 
                }else{
                  for (int i = 0; i < lista.size(); i++) {
                        if (i == 2) {
                            newGroup = newGroup+zdt.toString()+System.getProperty("line.separator");
                        }else{
                            if (i == 3) {
                                int newCount = Integer.parseInt(lista.get(i).toString());
                                newCount++;
                                newGroup = newGroup+newCount+System.getProperty("line.separator");
                            }else{  
                                newGroup = newGroup+lista.get(i)+System.getProperty("line.separator");
                            }
                        }
                    }  
                }
        }
        Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_BITACORA_GROUP_DIRECTORY), "utf-8"));
                writer.write(newGroup);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
            agregarEnApilo(isFull, grupo);
        return true;
    }
    
    private boolean pasarDatosAlMaster(String userName) throws FileNotFoundException, IOException{
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        File descriptorDelMaster = new File(DEFAULT_DES_DIR+DEFAULT_GROUP_DIRECTORY);
        if (!descriptorDelMaster.exists()) return false;
        Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_GROUP_DIRECTORY);
        File archivo = new File(scanner.nextLine());
        scanner = new Scanner(archivo);
        String newDescriptor = "";
        if (!scanner.hasNextLine()){
            newDescriptor = userName+System.getProperty("line.separator")+zdt.toString()+System.getProperty("line.separator")+"Sin modificacion"+System.getProperty("line.separator")+"|"+System.getProperty("line.separator")+"userName-group"+System.getProperty("line.separator")+"Ascendente"+System.getProperty("line.separator")+"1"+System.getProperty("line.separator")+"0";
        }else{
            ArrayList lista = new ArrayList();
            while(scanner.hasNextLine()){
                lista.add(scanner.nextLine());
            }
            scanner.close();
            lista.set(0, userName);
            lista.set(2, zdt.toString());
            for (int i = 0; i < lista.size(); i++) {
                newDescriptor = newDescriptor + lista.get(i)+System.getProperty("line.separator");
            }
            //reorganizarMaster();
        } 
        Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_GROUP_DIRECTORY), "utf-8"));
                writer.write(newDescriptor);
                return true;
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
        return false; 
    }
    
    private ArrayList retornarLista() throws FileNotFoundException{
        File bitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_GROUP_DIRECTORY);
        File master = new File(DEFAULT_DIRECTORY+DEFAULT_GROUP_DIRECTORY);
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en la bitacora
        if (bitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_BITACORA_GROUP_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                listaARetornar.add(line);
            }
            scanner.close();
        }
        if (master.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_GROUP_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                listaARetornar.add(line);
            }
            scanner.close();
        }
        return listaARetornar;
    }
    
    public void ordenarMaster() throws FileNotFoundException {
        boolean ordenado=false;
        ArrayList lista = retornarLista();
        int cuentaIntercambios=0;
         // Cuando sean archivos, no es necesario que el master tenga el 
         // tamaño de la bitacora 
        //Usamos un bucle anidado, saldra cuando este ordenado el array
        while(!ordenado){
            for(int i=0;i<lista.size()-1;i++){
                if (lista.get(i).toString().split("\\|")[0].compareTo(lista.get(i+1).toString().split("\\|")[0])>0){
                    //Intercambiamos valores
                    String aux=lista.get(i).toString();
                    lista.set(i, lista.get(i+1).toString());
                    lista.set(i+1, aux);
                    //indicamos que hay un cambio
                    cuentaIntercambios++;
                }
            }
            //Si no hay intercambios, es que esta ordenado.
            if (cuentaIntercambios==0){
                ordenado=true;
            }
            //Inicializamos la variable de nuevo para que empiece a contar de nuevo
            cuentaIntercambios=0;
        }
        llenarMaster(lista);
    }
    
    private void llenarMaster(ArrayList lista){
        String newString = "";
        for (int i = 0; i < lista.size(); i++) {
            newString = newString+lista.get(i)+System.getProperty("line.separator");
        }
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_GROUP_DIRECTORY), "utf-8"));
            writer.write(newString);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
    }
    
    public void agregarEnApilo(boolean isFull,Grupo grupo) throws IOException{
        ArchivoSecuencial archivo = new ArchivoSecuencial();
        archivo.crearArchivoBitacora(new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_GROUP_DIRECTORY));
      //Usuario(credenciales[0], credenciales[4], credenciales[5], credenciales[1], Integer.parseInt(credenciales[2]), credenciales[6], credenciales[9], credenciales[8], credenciales[7], credenciales[10], Integer.parseInt(credenciales[3]));
         String group = grupo.getUsuario().getUsuario()+"|"+grupo.getGrupo()+"|"+grupo.getDescripcion()+"|"+"0"+"|"+grupo.getFechaDeTransaccion()+"|"+"1"+System.getProperty("line.separator");
         
         if (isFull) {
            ordenarMaster();
            Writer writer = null;
            pasarDatosAlMaster(group.split("\\|")[0]);
            //Reorganizar reorganizar = new Reorganizar();
            //reorganizar.reordenarMaster();
            REO reorganizar = new REO();
            //reorganizar.Reorganize(user.split("\\|")[0]);
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_BITACORA_GROUP_DIRECTORY), "utf-8"));
                writer.write(group);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            } 
        }else{ 
            archivo.agregarUsuario(group);
        }
        archivo.cerrar();
    }
    
    public boolean crearGrupo(Usuario usuario, String grupo, String descripcion) throws FileNotFoundException, IOException{
        llenarBitacora(usuario, grupo, descripcion);
        return false;
    }
}
