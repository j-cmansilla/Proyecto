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
public class LocalMessageManejador {
    private final String DEFAULT_DES_DIR = "C:\\MEIA\\Desc_";
    private final String DEFAULT_BITACORA_MENSAJES_DIRECTORY = "BitacoraMensajes.txt";
    private final String DEFAULT_MENSAJES_DIRECTORY = "Mensajes.txt";
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    
    REO reorganize = new REO();
    public void CrearArchivos(){
        try{
            File archivoMensajes = new File(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY);
            File descriptorMensajes = new File(DEFAULT_DES_DIR+DEFAULT_MENSAJES_DIRECTORY);
            File archivoBitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
            File descriptorBitacora = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
            archivoMensajes.createNewFile();
            descriptorMensajes.createNewFile();
            archivoBitacora.createNewFile();
            descriptorBitacora.createNewFile();
        }catch(IOException e){
            
        }
    }
    
    public void agregarEnApilo(boolean isFull,LocalMessage message) throws IOException{
        ArchivoSecuencial archivo = new ArchivoSecuencial();
        archivo.crearArchivoBitacora(new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY));
      //Usuario(credenciales[0], credenciales[4], credenciales[5], credenciales[1], Integer.parseInt(credenciales[2]), credenciales[6], credenciales[9], credenciales[8], credenciales[7], credenciales[10], Integer.parseInt(credenciales[3]));
         String mensaje = message.getUsuario()+"|"+message.getUsuarioAmigo()+"|"+message.getFecha()+"|"+message.getMensaje()+"|"+message.getTipoDeMensaje()+"|"+message.getEstatus()+System.getProperty("line.separator");
         //tring user = usuario.getUsuario()+"|"+usuario.getNombre()+"|"+usuario.getApellido()+"|"+usuario.getPassword()+"|"+usuario.Rol()+"|"+usuario.getFechaDeNacimiento()+"|"+usuario.getCorreo()+"|"+usuario.getTelefono()+"|"+usuario.getFotografia()+"|"+usuario.getDescripcion()+"|"+"1"+System.getProperty("line.separator");
         reorganize.CheckForREO(message.getUsuario());
         
        if (isFull) {
            Writer writer = null;
            ordenarMaster();
            pasarDatosAlMaster(mensaje.split("\\|")[0]);
            //Reorganizar reorganizar = new Reorganizar();
            //reorganizar.reordenarMaster();
            REO reorganizar = new REO();
            //reorganizar.Reorganize(user.split("\\|")[0]);
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY), "utf-8"));
                writer.write(mensaje);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            } 
        }else{ 
            archivo.agregarUsuario(mensaje);
        }
        archivo.cerrar();
    }
    
    public void llenarBitacora(String userName, ZonedDateTime zdt, LocalMessage message) throws FileNotFoundException, IOException, Exception{
        File desBitacora = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
        boolean isFull = false;
        String newMessage="";
        if (desBitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            if (!scanner.hasNextLine()) {
                newMessage = userName+System.getProperty("line.separator")+zdt.toString()+System.getProperty("line.separator")+"modified"+System.getProperty("line.separator")+"1"+System.getProperty("line.separator")+"0"+System.getProperty("line.separator")+"3";
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
                            newMessage = newMessage+userName+System.getProperty("line.separator");
                        }else{
                            if (i == 2) {
                            newMessage = newMessage+zdt.toString()+System.getProperty("line.separator");
                            }else{
                                if (i == 4) {
                                    newMessage = newMessage+"0"+System.getProperty("line.separator");
                                }else{
                                    if (i == 3) {
                                        newMessage = newMessage+"1"+System.getProperty("line.separator");
                                    }else{  
                                        newMessage = newMessage+lista.get(i)+System.getProperty("line.separator");
                                    }
                                }
                            }
                        }
                    } 
                }else{
                  for (int i = 0; i < lista.size(); i++) {
                        if (i == 2) {
                            newMessage = newMessage+zdt.toString()+System.getProperty("line.separator");
                        }else{
                            if (i == 3) {
                                int newCount = Integer.parseInt(lista.get(i).toString());
                                newCount++;
                                newMessage = newMessage+newCount+System.getProperty("line.separator");
                            }else{  
                                newMessage = newMessage+lista.get(i)+System.getProperty("line.separator");
                            }
                        }
                    }  
                }
            }
            Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_BITACORA_MENSAJES_DIRECTORY), "utf-8"));
                writer.write(newMessage);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
            agregarEnApilo(isFull, message);
            //llenarBitacora(isFull, retornarUsuarioParaBitacora(usuario));
        }
    }
    
    private boolean pasarDatosAlMaster(String userName) throws FileNotFoundException, IOException{
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        File descriptorDelMaster = new File(DEFAULT_DES_DIR+DEFAULT_MENSAJES_DIRECTORY);
        if (!descriptorDelMaster.exists()) return false;
        Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_MENSAJES_DIRECTORY);
        File archivo = new File(scanner.nextLine());
        scanner = new Scanner(archivo);
        String newDescriptor = "";
        if (!scanner.hasNextLine()){
            newDescriptor = userName+System.getProperty("line.separator")+zdt.toString()+System.getProperty("line.separator")+"Sin modificacion"+System.getProperty("line.separator")+"|"+System.getProperty("line.separator")+"userName-userFriend-date"+System.getProperty("line.separator")+"Ascendente"+System.getProperty("line.separator")+"1"+System.getProperty("line.separator")+"0";
        }else{
            ArrayList lista = new ArrayList();
            while(scanner.hasNextLine()){
                lista.add(scanner.nextLine());
            }
            scanner.close();
            lista.set(0, userName);
            lista.set(2, zdt.toString());
            ArrayList listaGrupos = retornarMaster();
            lista.set(6, listaGrupos.size());
            lista.set(7, "0");
            for (int i = 0; i < lista.size(); i++) {
                newDescriptor = newDescriptor + lista.get(i)+System.getProperty("line.separator");
            }
            //reorganizarMaster();
        } 
        Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_MENSAJES_DIRECTORY), "utf-8"));
                writer.write(newDescriptor);
                return true;
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
        return false; 
    }
    
    private void llenarMaster(ArrayList lista){
        String newString = "";
        for (int i = 0; i < lista.size(); i++) {
            newString = newString+lista.get(i)+System.getProperty("line.separator");
        }
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY), "utf-8"));
            writer.write(newString);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
    }
    
    public ArrayList getPublicMessages() throws FileNotFoundException{
        ArrayList listaBitacora = retornarListaBitacoraPublic();
        ArrayList listaMaster = retornarListaMasterPublic();
        ArrayList listaMensajes = new ArrayList();
        for (int i = 0; i < listaMaster.size(); i++) {
            listaMensajes.add(listaMaster.get(i));
        }
        for (int i = 0; i < listaBitacora.size(); i++) {
            listaMensajes.add(listaBitacora.get(i));
        }
        return listaMensajes;
    }
    
    public ArrayList getPrivateMessages(String user, String userFriend) throws FileNotFoundException{
        ArrayList listaBitacora = retornarListaBitacora(user, userFriend);
        ArrayList listaMaster = retornarListaMaster(user, userFriend);
        ArrayList listaMensajes = new ArrayList();
        for (int i = 0; i < listaMaster.size(); i++) {
            listaMensajes.add(listaMaster.get(i));
        }
        for (int i = 0; i < listaBitacora.size(); i++) {
            listaMensajes.add(listaBitacora.get(i));
        }
        return listaMensajes;
    }
    
    public ArrayList retornarListaBitacora(String user, String userFriend) throws FileNotFoundException{
        File bitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
        boolean ordenado=false;
        int cuentaIntercambios=0;
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en la bitacora
        if (bitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.split("\\|")[0].equals(user) && line.split("\\|")[1].equals(userFriend) && line.split("\\|")[5].equals("1") && line.split("\\|")[4].equals("1")) {
                    listaARetornar.add(line);
                }
                if (line.split("\\|")[0].equals(userFriend) && line.split("\\|")[1].equals(user) && line.split("\\|")[5].equals("1") && line.split("\\|")[4].equals("1")) {
                    listaARetornar.add(line);
                }
            }
            scanner.close();
        }
        while(!ordenado){
            for(int i=0;i<listaARetornar.size()-1;i++){
                if (listaARetornar.get(i).toString().split("\\|")[2].compareTo(listaARetornar.get(i+1).toString().split("\\|")[2])>0){
                    //Intercambiamos valores
                    String aux=listaARetornar.get(i).toString();
                    listaARetornar.set(i, listaARetornar.get(i+1).toString());
                    listaARetornar.set(i+1, aux);
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
        return listaARetornar;
    }
    
    private ArrayList retornarListaMaster(String user, String userFriend) throws FileNotFoundException{
        File master = new File(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY);
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en master
        if (master.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.split("\\|")[0].equals(user) && line.split("\\|")[1].equals(userFriend) && line.split("\\|")[5].equals("1") && line.split("\\|")[4].equals("1")) {
                    listaARetornar.add(line);
                }
                if (line.split("\\|")[0].equals(userFriend) && line.split("\\|")[1].equals(user) && line.split("\\|")[5].equals("1") && line.split("\\|")[4].equals("1")) {
                    listaARetornar.add(line);
                }
            }
            scanner.close();
        }
        return listaARetornar;
    }
    
    public ArrayList retornarListaBitacoraPublic() throws FileNotFoundException{
        File bitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
        boolean ordenado=false;
        int cuentaIntercambios=0;
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en la bitacora
        if (bitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.split("\\|")[5].equals("1") && line.split("\\|")[4].equals("0")) {
                    listaARetornar.add(line);
                }
            }
            scanner.close();
        }
        while(!ordenado){
            for(int i=0;i<listaARetornar.size()-1;i++){
                if (listaARetornar.get(i).toString().split("\\|")[2].compareTo(listaARetornar.get(i+1).toString().split("\\|")[2])>0){
                    //Intercambiamos valores
                    String aux=listaARetornar.get(i).toString();
                    listaARetornar.set(i, listaARetornar.get(i+1).toString());
                    listaARetornar.set(i+1, aux);
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
        return listaARetornar;
    }
    
    /*public LocalMessage returnMessage(String message) throws FileNotFoundException{
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        String key = manejador.getUserLogin() + groupName;
        Grupo grupoARetornar = null;
        ArrayList lista = retornarLista();
        for (int i = 0; i < lista.size(); i++) {
            String[] datosGrupo = lista.get(i).toString().split("\\|");
            if (key.equals(datosGrupo[0]+datosGrupo[1])) {
                if (Integer.parseInt(datosGrupo[5]) == 1) {
                    grupoARetornar = new Grupo(manejador.getUserData(datosGrupo[0]), datosGrupo[1], datosGrupo[2],Integer.parseInt(datosGrupo[3]), datosGrupo[4], Integer.parseInt(datosGrupo[5]));
                }
            }
        }
        return grupoARetornar;
    }*/
    
    private String returnMessage(String message){
        String [] messageSplited;
        messageSplited = message.split("\\|");
        messageSplited[5] = "0";
        String messageToReturn = "";
        for (int i = 0; i < 6; i++) {
            if (i == 5) {
                messageToReturn = messageToReturn+messageSplited[i];
            }else{ 
                messageToReturn = messageToReturn+messageSplited[i]+"|";
            }
        }
        return messageToReturn;
    }
    
    private void reWriteBitacora(ArrayList bitacora){
        String newBitacora = "";
        for (int i = 0; i < bitacora.size(); i++) {
            newBitacora = newBitacora+bitacora.get(i).toString()+System.getProperty("line.separator");
        }
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY), "utf-8"));
            writer.write(newBitacora);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
    }
    
    private void reWriteMaster(ArrayList master){
        String newMaster = "";
        for (int i = 0; i < master.size(); i++) {
            newMaster = newMaster+master.get(i).toString()+System.getProperty("line.separator");
        }
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY), "utf-8"));
            writer.write(newMaster);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
    }
    
    private void updateMessage(String userName) throws FileNotFoundException{
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        File descriptorDelMaster = new File(DEFAULT_DES_DIR+DEFAULT_MENSAJES_DIRECTORY);
        if (!descriptorDelMaster.exists()) return;
        Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_MENSAJES_DIRECTORY);
        File archivo = new File(scanner.nextLine());
        scanner = new Scanner(archivo);
        String newDescriptor = "";
        if (!scanner.hasNextLine()){
            newDescriptor = userName+System.getProperty("line.separator")+zdt.toString()+System.getProperty("line.separator")+"Sin modificacion"+System.getProperty("line.separator")+"|"+System.getProperty("line.separator")+"userName-userFriend-message"+System.getProperty("line.separator")+"Ascendente"+System.getProperty("line.separator")+"1"+System.getProperty("line.separator")+"0";
        }else{
            ArrayList lista = new ArrayList();
            while(scanner.hasNextLine()){
                lista.add(scanner.nextLine());
            }
            scanner.close();
            lista.set(0, userName);
            lista.set(2, zdt.toString());
            int count = Integer.parseInt(lista.get(7).toString());
            int previousCount = Integer.parseInt(lista.get(6).toString());
            previousCount--;
            count++;
            lista.set(7, count);
            lista.set(6, previousCount);
            for (int i = 0; i < lista.size(); i++) {
                newDescriptor = newDescriptor + lista.get(i)+System.getProperty("line.separator");
            }
            //reorganizarMaster();
        } 
        Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_MENSAJES_DIRECTORY), "utf-8"));
                writer.write(newDescriptor);
                return;
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
    }
    
    public ArrayList getListDesBitacora() throws FileNotFoundException{
        File Desbitacora = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en la bitacora
        if (Desbitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
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
    
    private void reWriteDesBitacora(ArrayList desBitacora){
        String newDesBitacora = "";
        for (int i = 0; i < desBitacora.size(); i++) {
            newDesBitacora = newDesBitacora+desBitacora.get(i).toString()+System.getProperty("line.separator");
        }
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_BITACORA_MENSAJES_DIRECTORY), "utf-8"));
            writer.write(newDesBitacora);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
    }
    
    public void updateBitacora() throws FileNotFoundException{
        ArrayList listaDesBitacora = getListDesBitacora();
        int count = Integer.parseInt(listaDesBitacora.get(4).toString());
        count++;
        int count2 = Integer.parseInt(listaDesBitacora.get(3).toString());
        count2--;
        listaDesBitacora.set(4, count);
        listaDesBitacora.set(3, count2);
        reWriteDesBitacora(listaDesBitacora);
    }
    
    public void deleteMessage(String message) throws FileNotFoundException{
        //Grupo grupo = returnGroup(groupName);
        String messageToDelete = returnMessage(message);
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        ArrayList listaBitacora = retornarBitacora();
        ArrayList listaMaster = retornarMaster();
        String [] messageSplited = messageToDelete.split("\\|");
        String key = messageSplited[0]+messageSplited[1]+messageSplited[2];
        //Buscar en bitacora
        for (int i = 0; i < listaBitacora.size(); i++) {
            String [] messageToDel = listaBitacora.get(i).toString().split("\\|");
            if (key.equals(messageToDel[0]+messageToDel[1]+messageToDel[2])){
                listaBitacora.set(i, messageToDelete);
                reWriteBitacora(listaBitacora);
                updateBitacora();
                return;
            }
        }
        //Buscar en Master
        for (int i = 0; i < listaMaster.size(); i++) {
            String [] messageToDel = listaMaster.get(i).toString().split("\\|");
            if (key.equals(messageToDel[0]+messageToDel[1]+messageToDel[2])){
                listaMaster.set(i, messageToDelete);
                reWriteMaster(listaMaster);
                updateMessage(manejador.getUserLogin());
                return;
            }
        }
    }
    
    private ArrayList retornarListaMasterPublic() throws FileNotFoundException{
        File master = new File(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY);
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en master
        if (master.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.split("\\|")[5].equals("1") && line.split("\\|")[4].equals("0")) {
                    listaARetornar.add(line);
                }
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
                if (lista.get(i).toString().split("\\|")[2].compareTo(lista.get(i+1).toString().split("\\|")[2])>0){
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

    private ArrayList retornarBitacora() throws FileNotFoundException{
       File bitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en la bitacora
        if (bitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
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
    
    private ArrayList retornarMaster() throws FileNotFoundException{
       File master = new File(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY);
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en la bitacora
        if (master.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY);
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
    
    private ArrayList retornarLista() throws FileNotFoundException{
        File bitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
        File master = new File(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY);
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en la bitacora
        if (bitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_BITACORA_MENSAJES_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                listaARetornar.add(line);
            }
            scanner.close();
        }
        if (master.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_MENSAJES_DIRECTORY);
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
    
}
