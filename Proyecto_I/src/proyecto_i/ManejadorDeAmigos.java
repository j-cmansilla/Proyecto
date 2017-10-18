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

/**
 *
 * @author Harry
 */
public class ManejadorDeAmigos {
    
    private final String DEFAULT_DES_DIR = "C:\\MEIA\\Desc_";
    private final String DEFAULT_BITACORA_LISTA_DIRECTORY = "BitacoraLista_Amigos.txt";
    private final String DEFAULT_LISTA_AMIGOS_DIRECTORY = "Lista_Amigos.txt";
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    
    public void CrearArchivos(){
        try{
            File aLista_Amigos = new File(DEFAULT_DIRECTORY+DEFAULT_LISTA_AMIGOS_DIRECTORY);
            File aDescAmigos = new File(DEFAULT_DES_DIR+DEFAULT_LISTA_AMIGOS_DIRECTORY);
            File aBitacoraAmigos = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_LISTA_DIRECTORY);
            File aDescBitacoraAmigos = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_LISTA_DIRECTORY);
            aLista_Amigos.createNewFile();
            aDescAmigos.createNewFile();
            aBitacoraAmigos.createNewFile();
            aDescBitacoraAmigos.createNewFile();
        }catch(IOException e){
            
        }
    }
    
    public boolean updateBitacora(Usuario usuario, Usuario usuario_amigo) throws FileNotFoundException, IOException{
        File desBitacora = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_LISTA_DIRECTORY);
        boolean isFull = false;
        REO objREO=new REO();
        
        if (!desBitacora.exists()){
            return false;
        } 
        
        Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_BITACORA_LISTA_DIRECTORY);
        File archivo = new File(scanner.nextLine());
        scanner = new Scanner(archivo);
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        String newRequest = usuario.getUsuario()+"|"+usuario_amigo.getUsuario() + "|" + "0" + "|" + zdt.toString() + "|"+"usuario_transaccion"+"|"+"0"+System.getProperty("line.separator");
        String update="";

        if (!scanner.hasNextLine()) {
            update = usuario.getUsuario()+System.getProperty("line.separator")+usuario_amigo.getUsuario() + 
                    System.getProperty("line.separator") + zdt.toString() + System.getProperty("line.separator")+
                    "1"+System.getProperty("line.separator")+"3";
        }else{
            ArrayList lista = new ArrayList();
                while(scanner.hasNextLine()){
                    lista.add(scanner.nextLine());
                }
                scanner.close();
                if(Integer.parseInt(lista.get(4).toString())+1 < objREO.countLines(DEFAULT_DIRECTORY+DEFAULT_BITACORA_LISTA_DIRECTORY))
                {
                    isFull = true;
                    for (int i = 0; i < lista.size(); i++) {
                        switch(i){
                            case 0: update = update+usuario.getUsuario()+System.getProperty("line.separator");
                                break;
                            case 1: update = update+usuario_amigo.getUsuario()+System.getProperty("line.separator");
                                break;
                            case 2: update = update+zdt.toString()+System.getProperty("line.separator");
                                break;
                            case 3: update = update+(Integer.parseInt(lista.get(3).toString())+1)+System.getProperty("line.separator");
                                break;
                            case 4: update = update+lista.get(4)+System.getProperty("line.separator");
                                break;
                        }                        
                    }                    
                }
                else{
                    for (int i = 0; i < lista.size(); i++) {
                        switch(i){
                            case 2: update = update+zdt.toString()+System.getProperty("line.separator");
                                break;
                            case 3: update = update+(Integer.parseInt(lista.get(3).toString())+1)+System.getProperty("line.separator");
                                break;
                            default: update = update+lista.get(i)+System.getProperty("line.separator");
                                break;
                        }                        
                    }  
                }
        }
        Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_BITACORA_LISTA_DIRECTORY), "utf-8"));
                writer.write(update);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
            agregarEnApilo(isFull, newRequest);
        return true;
    }
    
    public void agregarEnApilo(boolean isFull, String request) throws IOException{
        ArchivoSecuencial.crearArchivoBitacora(new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_LISTA_DIRECTORY));
      
        if (isFull) {
            ordenarMaster();
            Writer writer = null;
            pasarDatosAlMaster(request.split("\\|")[0], request.split("\\|")[1], request.split("\\|")[3]);
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_BITACORA_LISTA_DIRECTORY), "utf-8"));
                writer.write(request);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            } 
        }else{ 
            ArchivoSecuencial.agregarUsuario(request);
        }
        ArchivoSecuencial.cerrar();
    }
    
    private boolean pasarDatosAlMaster(String userName, String friend_userName, String count) throws FileNotFoundException, IOException{
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        
        File descriptorDelMaster = new File(DEFAULT_DES_DIR+DEFAULT_LISTA_AMIGOS_DIRECTORY);
        if (!descriptorDelMaster.exists()) {
            return false;
        }
        Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_LISTA_AMIGOS_DIRECTORY);
        File archivo = new File(scanner.nextLine());
        scanner = new Scanner(archivo);
        
        String newDescriptor = "";
        if (!scanner.hasNextLine()){
            newDescriptor = userName+System.getProperty("line.separator")+ friend_userName + 
                    System.getProperty("line.separator") + zdt.toString()+ System.getProperty("line.separator") + count;

        }
        else{
            ArrayList lista = new ArrayList();
            while(scanner.hasNextLine()){
                lista.add(scanner.nextLine());
            }
            scanner.close();
            lista.set(0, userName);
            lista.set(1, friend_userName);
            lista.set(2, zdt.toString());
            lista.set(3, count);
            for (int i = 0; i < lista.size(); i++) {
                newDescriptor = newDescriptor + lista.get(i)+System.getProperty("line.separator");
            }
        } 
        Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_LISTA_AMIGOS_DIRECTORY), "utf-8"));
                writer.write(newDescriptor);
                return true;
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
        return false; 
    }
    
    public void ordenarMaster() throws FileNotFoundException {
        boolean ordenado=false;
        ArrayList lista = retornarLista();
        int cuentaIntercambios=0;

        while(!ordenado){
            for(int i=0;i<lista.size()-1;i++){
                if (lista.get(i).toString().split("\\|")[0].compareTo(lista.get(i+1).toString().split("\\|")[0])>0){

                    String aux=lista.get(i).toString();
                    lista.set(i, lista.get(i+1).toString());
                    lista.set(i+1, aux);
                    cuentaIntercambios++;
                }
            }

            if (cuentaIntercambios==0){
                ordenado=true;
            }
            cuentaIntercambios=0;
        }
        llenarMaster(lista);
    }
    
    private ArrayList retornarLista() throws FileNotFoundException{
        File bitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_LISTA_DIRECTORY);
        File master = new File(DEFAULT_DIRECTORY+DEFAULT_LISTA_AMIGOS_DIRECTORY);
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en la bitacora
        if (bitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_BITACORA_LISTA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                listaARetornar.add(line);
            }
            scanner.close();
        }
        if (master.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_LISTA_AMIGOS_DIRECTORY);
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
    
    private void llenarMaster(ArrayList lista){
        String newString = "";
        for (int i = 0; i < lista.size(); i++) {
            newString = newString+lista.get(i)+System.getProperty("line.separator");
        }
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_LISTA_AMIGOS_DIRECTORY), "utf-8"));
            writer.write(newString);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
    }
    
}
