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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Harry
 */
public class ManejadorDeAmigos {
    
    private final String DEFAULT_DES_DIR = "C:\\MEIA\\Desc_";
    private final String DEFAULT_BITACORA_LISTA_DIRECTORY = "BitacoraLista_Amigos.txt";
    private final String DEFAULT_LISTA_AMIGOS_DIRECTORY = "Lista_Amigos.txt";
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    private final String DEFAULT_TEMP_BA = "C:\\MEIA\\TempBA.txt";
    private final String DEFAULT_TEMP_LA = "C:\\MEIA\\TempLA.txt";
    
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
    
    public boolean updateBitacora(Usuario usuario, Usuario usuario_amigo, int typeCase) throws FileNotFoundException, IOException{
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
        String newRequest = "";
        if(typeCase == 0){
            newRequest = usuario.getUsuario()+"|"+usuario_amigo.getUsuario() + "|" + "0" + "|" + zdt.toString() + "|"+usuario.getUsuario()+"|"+"0"+System.getProperty("line.separator");
        }else if (typeCase == 1) {
            newRequest = usuario.getUsuario()+"|"+usuario_amigo.getUsuario() + "|" + "1" + "|" + zdt.toString() + "|"+usuario.getUsuario()+"|"+"1"+System.getProperty("line.separator");
        }
        
        String update="";
        int count = 0;
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
                if(Integer.parseInt(lista.get(4).toString()) <= objREO.countLines(DEFAULT_DIRECTORY+DEFAULT_BITACORA_LISTA_DIRECTORY))
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
                count = Integer.parseInt(lista.get(3).toString()) + 1;
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
            agregarEnApilo(isFull, newRequest, count);
        return true;
    }
    
    public void agregarEnApilo(boolean isFull, String request, int count) throws IOException{
        ArchivoSecuencial.crearArchivoBitacora(new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_LISTA_DIRECTORY));
      
        if (isFull) {
            ordenarMaster();
            Writer writer = null;
            pasarDatosAlMaster(request.split("\\|")[0], request.split("\\|")[1], count);
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
    
    private boolean pasarDatosAlMaster(String userName, String friend_userName, int count) throws FileNotFoundException, IOException{
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
                String[] toCompare=lista.get(i).toString().split("\\|");
                String[] toCompare2=lista.get(i+1).toString().split("\\|");
                String no1 = toCompare[0]+"|"+toCompare[1];
                String no2 = toCompare2[0]+"|"+toCompare2[1];
                if (no1.compareTo(no2)>0){

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
                if(!line.contains("*")){
                    listaARetornar.add(line);
                }                
            }
            scanner.close();
        }
        if (master.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_LISTA_AMIGOS_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(!line.contains("*")){
                    listaARetornar.add(line);
                }
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
    
    public ArrayList getUserRequest(String loggedUser) throws FileNotFoundException{
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
        ArrayList allRequests=new ArrayList();
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if (loggedUser.equals(request[1]) && request[2].equals("0") && !request[5].equals("0*")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
                            
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if (loggedUser.equals(request[1]) && request[2].equals("0") && !request[5].equals("0*")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        return allRequests;        
    }
    
    public boolean updateBM(ArrayList accepted) throws FileNotFoundException, IOException{
        boolean flag=false;
        for (int i = 0; i < accepted.size(); i++) 
        {
            String [] request = accepted.get(i).toString().split(Pattern.quote("|"));
            String key = request[0] + "|" + request[1];
            
            File requestsB = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
                    
            if (requestsB.exists()) 
            {
                Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
                File archivo = new File(scanner.nextLine());
                scanner = new Scanner(archivo);
                
                File tempFile = new File(DEFAULT_TEMP_BA);
                tempFile.createNewFile();
                
                
                while(scanner.hasNextLine())
                {
                    String line = scanner.nextLine() + System.getProperty("line.separator");
                    if (line.contains(key)) 
                    {   
                        String newLine =  accepted.get(i).toString();
                        Files.write(Paths.get(DEFAULT_TEMP_BA), newLine.getBytes(), StandardOpenOption.APPEND);
                        flag = true;
                    }
                    else
                    {
                        Files.write(Paths.get(DEFAULT_TEMP_BA), line.getBytes(), StandardOpenOption.APPEND);
                    }
                }
                scanner.close();
                
                String newFilePath = requestsB.getAbsolutePath();
                Path path = Paths.get(requestsB.getAbsolutePath());
                Files.delete(path);
                File newFile = new File(newFilePath);
                FileUtils.moveFile(tempFile, newFile);
            }
            
            File requestsLA = new File(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
            
            if (requestsLA.exists()) 
            {
                Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
                File archivo = new File(scanner.nextLine());
                scanner = new Scanner(archivo);
                
                File tempFile = new File(DEFAULT_TEMP_LA);
                tempFile.createNewFile();
                
                
                while(scanner.hasNextLine())
                {
                    String line = scanner.nextLine() + System.getProperty("line.separator");
                    if (line.contains(key)) 
                    {   
                        String newLine =  accepted.get(i).toString();
                        Files.write(Paths.get(DEFAULT_TEMP_LA), newLine.getBytes(), StandardOpenOption.APPEND);
                        flag = true;
                    }
                    else
                    {
                        Files.write(Paths.get(DEFAULT_TEMP_LA), line.getBytes(), StandardOpenOption.APPEND);
                    }
                }
                scanner.close();
                
                String newFilePath = requestsLA.getAbsolutePath();
                Path path = Paths.get(requestsLA.getAbsolutePath());
                Files.delete(path);
                File newFile = new File(newFilePath);
                FileUtils.moveFile(tempFile, newFile);
            }
        } 
        return flag;
    }  
    
    public ArrayList getFriendsList(String loggedUser) throws FileNotFoundException{
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
        ArrayList allRequests=new ArrayList();
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if ((loggedUser.equals(request[0]) || loggedUser.equals(request[1])) && request[2].equals("1")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
                            
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if ((loggedUser.equals(request[0]) || loggedUser.equals(request[1])) && request[2].equals("1")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        return allRequests;        
    }
    
    public ArrayList cleanRequests(ArrayList requests, String key){
        ArrayList cleaned = new ArrayList();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).toString().contains(key)) {
                cleaned.add(requests.get(i).toString());
            }        
        }
        return cleaned;
    }

    public boolean areFriends(String user1, String user2) throws FileNotFoundException{
        ArrayList friends = getFriendsList(user1);
        String key1 = user1 + "|" + user2;
        String key2 = user2 + "|" + user1;
        
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).toString().contains(key1)) {
                return true;
            }        
        }
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).toString().contains(key2)) {
                return true;
            }        
        }
        return false;        
    }
    
    public ArrayList allFriends(String usuario) throws FileNotFoundException{
        ArrayList friends = getFriendsList(usuario);
        ArrayList result = new ArrayList();
        
        for (int i = 0; i < friends.size(); i++) {
            String [] request = friends.get(i).toString().split(Pattern.quote("|"));
            if (request[0].equals(usuario)) {
                result.add(request[1]);
            }
            else{
                result.add(request[0]);
            }
        }
        
        return result;
    }
    
    public ArrayList getAllRequests(String loggedUser) throws FileNotFoundException{
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
        ArrayList allRequests=new ArrayList();
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if ((loggedUser.equals(request[0]) || loggedUser.equals(request[1])) && !request[5].equals("0*")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
                            
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if ((loggedUser.equals(request[0]) || loggedUser.equals(request[1])) && !request[5].equals("0*")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        return allRequests;        
    }
    
    public boolean doesRequestExists(String user1, String user2) throws FileNotFoundException{
        ArrayList friends = getAllRequests(user1);
        String key1 = user1 + "|" + user2;
        String key2 = user2 + "|" + user1;
        
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).toString().contains(key1)) {
                return true;
            }        
        }
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).toString().contains(key2)) {
                return true;
            }        
        }
        return false;        
    }
    
    public ArrayList getAllRequestsAdmin() throws FileNotFoundException{
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
        ArrayList allRequests=new ArrayList();
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if (!request[5].equals("0*")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
                            
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if (!request[5].equals("0*")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        return allRequests;        
    }
    
    public void update() throws IOException{
        try {
            ordenarMaster();
            
            File requestsB = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_LISTA_DIRECTORY);            
            Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_BITACORA_LISTA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            ArrayList lista = new ArrayList();
            while(scanner.hasNextLine()){
                lista.add(scanner.nextLine());
            }
            scanner.close();
            int count=Integer.parseInt(lista.get(3).toString()); //FIX this **********
            pasarDatosAlMaster(lista.get(0).toString(), lista.get(1).toString(), count);
            
            File tempFile = new File("C:\\MEIA\\TempBA.txt");
            try {
                tempFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String newFilePath = requestsB.getAbsolutePath();
            Path path = Paths.get(requestsB.getAbsolutePath());
            try {
                Files.delete(path);
                File newFile = new File(newFilePath);
                FileUtils.moveFile(tempFile, newFile);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList getUserRequestToDelete(String loggedUser) throws FileNotFoundException{
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
        ArrayList allRequests=new ArrayList();
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_LISTA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if ((loggedUser.equals(request[0]) || loggedUser.equals(request[1])) && !request[5].equals("0*")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
                            
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_LISTA_AMIGOS_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] request = line.split(Pattern.quote("|"));
                if ((loggedUser.equals(request[0]) || loggedUser.equals(request[1])) && !request[5].equals("0*")) {
                    allRequests.add(line);
                }
            }
            scanner.close();
        }
        return allRequests;        
    }
}
