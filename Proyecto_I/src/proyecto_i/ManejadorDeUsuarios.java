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
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Jose Mansilla
 */
public class ManejadorDeUsuarios {
    
    private final String DEFAULT_USER_DIRECTORY = "Usuario.txt";
    private final String DEFAULT_BITACORA_DIRECTORY = "Bitacora.txt";
    private final String DEFAULT_TEMP_DIRECTORY = "Temp.txt";
    private final String DEFAULT_BACKUP_BITACORA_DIRECTORY = "bitacora_backup.txt";
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    private final String DEFAULT_DES_DIR = "C:\\MEIA\\Desc_";
    private final String DEFAULT_LOGIN_USER_DIRECTORY = "C:\\MEIA\\UsuarioLogueado.txt";
    private final String DEFAULT_PROFILE_PICTURES = "IMAGES";
       
    REO reorganize = new REO();
    
    public void agregarEnApilo(boolean isFull,Usuario usuario) throws IOException{
        ArchivoSecuencial archivo = new ArchivoSecuencial();
        archivo.crearArchivoBitacora(new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY));
      //Usuario(credenciales[0], credenciales[4], credenciales[5], credenciales[1], Integer.parseInt(credenciales[2]), credenciales[6], credenciales[9], credenciales[8], credenciales[7], credenciales[10], Integer.parseInt(credenciales[3]));
         String user = usuario.getUsuario()+"|"+usuario.getNombre()+"|"+usuario.getApellido()+"|"+usuario.getPassword()+"|"+usuario.Rol()+"|"+usuario.getFechaDeNacimiento()+"|"+usuario.getCorreo()+"|"+usuario.getTelefono()+"|"+usuario.getFotografia()+"|"+usuario.getDescripcion()+"|"+"1"+System.getProperty("line.separator");
         reorganize.CheckForREO(usuario.getUsuario());
        if (isFull) {
            Writer writer = null;
            pasarDatosAlMaster(user.split("\\|")[0]);
            //Reorganizar reorganizar = new Reorganizar();
            //reorganizar.reordenarMaster();
            REO reorganizar = new REO();
            //reorganizar.Reorganize(user.split("\\|")[0]);
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY), "utf-8"));
                writer.write(user);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            } 
        }else{ 
            archivo.agregarUsuario(user);
        }
        archivo.cerrar();
    }
    
    public void CrearArchivos(){
        try{
            File archivoUsuarios = new File(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY);
            File descriptorUsuarios = new File(DEFAULT_DES_DIR+DEFAULT_USER_DIRECTORY);
            File archivoBitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY);
            File descriptorBitacora = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_DIRECTORY);
            File bitacoraBackup = new File(DEFAULT_DIRECTORY+DEFAULT_BACKUP_BITACORA_DIRECTORY);
            File CarpetaFotos = new File(DEFAULT_DIRECTORY + DEFAULT_PROFILE_PICTURES);
            archivoUsuarios.createNewFile();
            descriptorUsuarios.createNewFile();
            archivoBitacora.createNewFile();
            descriptorBitacora.createNewFile();
            bitacoraBackup.createNewFile();
            CarpetaFotos.mkdirs();
        }catch(IOException e){
            
        }
    }
    
    public int validarUsuario(String user, String password) throws FileNotFoundException{
        File bitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY);
        File usuarios = new File(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY);
        //Buscarlo en la bitacora
        if (bitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split("\\|");
                if (user.equals(credenciales[0]) && password.equals(credenciales[3]) && Integer.parseInt(credenciales[10]) == 1) {
                    scanner.close();
                    return 1;
                }
                if (user.equals(credenciales[0]) && password.equals(credenciales[3]) && Integer.parseInt(credenciales[10]) == 0) {
                    return 2;
                }
                if (user.equals(credenciales[0]) && !password.equals(credenciales[3])) {
                    scanner.close();
                    return -1;
                }
            }
            scanner.close();
        }
        //buscarlo en el master
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split("|");
                if (user.equals(credenciales[0]) && password.equals(credenciales[3]) && Integer.parseInt(credenciales[10]) == 1) {
                    scanner.close();
                    return 1;
                }
                if (user.equals(credenciales[0]) && password.equals(credenciales[3]) && Integer.parseInt(credenciales[10]) == 0) {
                    return 2;
                }
                if (user.equals(credenciales[0]) && !password.equals(credenciales[3])) {
                    scanner.close();
                    return -1;
                }
            }
            scanner.close();
        }
        return 0;
    }
    
     
    
    public void llenarBitacora(String userName, ZonedDateTime zdt, Usuario usuario) throws FileNotFoundException, IOException, Exception{
        File desBitacora = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_DIRECTORY);
        boolean isFull = false;
        String newUser="";
        if (desBitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_BITACORA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            if (!scanner.hasNextLine()) {
                newUser = userName+System.getProperty("line.separator")+zdt.toString()+System.getProperty("line.separator")+"modified"+System.getProperty("line.separator")+"1"+System.getProperty("line.separator")+"0"+System.getProperty("line.separator")+"3";
                usuario.setRol(1);
            }else{
                usuario.setRol(0);
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
                            newUser = newUser+userName+System.getProperty("line.separator");
                        }else{
                            if (i == 2) {
                            newUser = newUser+zdt.toString()+System.getProperty("line.separator");
                            }else{
                                if (i == 4) {
                                    newUser = newUser+"0"+System.getProperty("line.separator");
                                }else{
                                    if (i == 3) {
                                        newUser = newUser+"1"+System.getProperty("line.separator");
                                    }else{  
                                        newUser = newUser+lista.get(i)+System.getProperty("line.separator");
                                    }
                                }
                            }
                        }
                    } 
                }else{
                  for (int i = 0; i < lista.size(); i++) {
                        if (i == 2) {
                            newUser = newUser+zdt.toString()+System.getProperty("line.separator");
                        }else{
                            if (i == 3) {
                                int newCount = Integer.parseInt(lista.get(i).toString());
                                newCount++;
                                newUser = newUser+newCount+System.getProperty("line.separator");
                            }else{  
                                newUser = newUser+lista.get(i)+System.getProperty("line.separator");
                            }
                        }
                    }  
                }
            }
            Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_BITACORA_DIRECTORY), "utf-8"));
                writer.write(newUser);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
            agregarEnApilo(isFull, usuario);
            //llenarBitacora(isFull, retornarUsuarioParaBitacora(usuario));
        }
    }
    
    
    
    private boolean pasarDatosAlMaster(String userName) throws FileNotFoundException, IOException{
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        File descriptorDelMaster = new File(DEFAULT_DES_DIR+DEFAULT_USER_DIRECTORY);
        if (!descriptorDelMaster.exists()) return false;
        Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_USER_DIRECTORY);
        File archivo = new File(scanner.nextLine());
        scanner = new Scanner(archivo);
        String newDescriptor = "";
        if (!scanner.hasNextLine()){
            newDescriptor = userName+System.getProperty("line.separator")+zdt.toString()+System.getProperty("line.separator")+"Sin modificacion"+System.getProperty("line.separator")+"|"+System.getProperty("line.separator")+"userName"+System.getProperty("line.separator")+"Ascendente"+System.getProperty("line.separator")+"1"+System.getProperty("line.separator")+"0";
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
                        new FileOutputStream(DEFAULT_DES_DIR+DEFAULT_USER_DIRECTORY), "utf-8"));
                writer.write(newDescriptor);
                return true;
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
        return false; 
    }
    
    public int tamanioDeBitacora() throws FileNotFoundException{
        File descriptorDelMaster = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_DIRECTORY);
        Scanner scanner = new Scanner(DEFAULT_DES_DIR+DEFAULT_BITACORA_DIRECTORY);
        File archivo = new File(scanner.nextLine());
        scanner = new Scanner(archivo);
        ArrayList lista = new ArrayList();
        while(scanner.hasNextLine()){
            lista.add(scanner.nextLine());
        }
        scanner.close();
        return Integer.parseInt(lista.get(10).toString());
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
            new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY), "utf-8"));
            writer.write(newString);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
    }
    
    private ArrayList retornarLista() throws FileNotFoundException{
        File bitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY);
        File master = new File(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY);
        ArrayList listaARetornar = new ArrayList();
        //Buscarlo en la bitacora
        if (bitacora.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                listaARetornar.add(line);
            }
            scanner.close();
        }
        if (master.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY);
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
    
    public byte[] cifra(String sinCifrar) throws Exception {
	final byte[] bytes = sinCifrar.getBytes("UTF-8");
	final Cipher aes = obtieneCipher(true);
	final byte[] cifrado = aes.doFinal(bytes);
	return cifrado;
    }

    private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
	final String frase = "JBook";
	final MessageDigest digest = MessageDigest.getInstance("SHA");
	digest.update(frase.getBytes("UTF-8"));
	final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

	final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
	if (paraCifrar) {
		aes.init(Cipher.ENCRYPT_MODE, key);
	} else {
		aes.init(Cipher.DECRYPT_MODE, key);
	}

	return aes;
    }
    
    public void CloseSession(){
        setUserToLogin("");
    }
    
    public String getUserLogin() throws FileNotFoundException{
        File archivoUsuarios = new File(DEFAULT_LOGIN_USER_DIRECTORY);
        if (archivoUsuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_LOGIN_USER_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(",");
                return (credenciales[0]);
            }
            scanner.close();
        }
        
        return "";
    }
    
    public void setUserToLogin(String userName){
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(DEFAULT_LOGIN_USER_DIRECTORY), "utf-8"));
            writer.write(userName+System.getProperty("line.separator"));
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
    }
    
    public Usuario getUserData(String user) throws FileNotFoundException{
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
        Usuario result;
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(Pattern.quote("|"));
                if (user.equals(credenciales[0]) && credenciales[10].equals("1")) {
                    result = new Usuario(credenciales[0], credenciales[1], credenciales[2], credenciales[3], Integer.parseInt(credenciales[4]), credenciales[5], credenciales[6], credenciales[7], credenciales[8], credenciales[9], Integer.parseInt(credenciales[10]));
                    scanner.close();
                    return result;
                }
            }
            scanner.close();
        }
        usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
                            
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(Pattern.quote("|"));
                if (user.equals(credenciales[0]) && credenciales[10].equals("1")) {
                    result = new Usuario(credenciales[0], credenciales[1], credenciales[2], credenciales[3], Integer.parseInt(credenciales[4]), credenciales[5], credenciales[6], credenciales[7], credenciales[8], credenciales[9], Integer.parseInt(credenciales[10]));
                    scanner.close();
                    return result;
                }
            }
            scanner.close();
        }
        return null;        
    }
    //**********************************************************************************************************
 private String readLine(Scanner reader) {
        if (reader.hasNextLine())
            return reader.nextLine();
        else
            return null;
  }
 private void WriteInTEMP_Ca(String usuario) throws IOException
    {
        String Userline = usuario + System.lineSeparator();
      Files.write(Paths.get(DEFAULT_TEMP_DIRECTORYC), Userline.getBytes(), StandardOpenOption.APPEND);
    }
 public void ActualizarDEs (Usuario user) throws FileNotFoundException, IOException{
        if (versiExiste(user,USER_PATH )) { ///master
             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        List<String> lines = new ArrayList<String>();
        //
        Path path = Paths.get(DESC_USER_PATH);
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> linesa;
            linesa = Files.readAllLines(path,charset);
        
       String  CreationDate = linesa.get(1);
       int acctive = Integer.parseInt(linesa.get(3)) -1 ;
       int DEacctive = Integer.parseInt(linesa.get(4)) +1;
       
        //
        File Master = new File(DESC_USER_PATH);
        Scanner MasterScanner = new Scanner(Master);
        String currentLine = "AD";
         if (!MasterScanner.hasNextLine()) {
           currentLine = null;
         }
        else{
        currentLine =MasterScanner.nextLine();
        }
         MasterScanner.close();
        lines.add(currentLine);
        lines.add(CreationDate);
        lines.add(dateFormat.format(date) + "[America/Guatemala]");
        lines.add(String.valueOf(acctive));
        lines.add(String.valueOf(DEacctive));
        
        Utilities Utilidades = new Utilities();
        Utilidades.LlenarArchivo(DESC_USER_PATH, lines);
        }
        else if(versiExiste(user,LOGBOOK_PATH )){ //apilo
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        List<String> lines = new ArrayList<String>();
        //
        Path path = Paths.get(DESC_LOGBOOK_PATH);
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> linesa;
            linesa = Files.readAllLines(path,charset);
        
       String  CreationDate = linesa.get(1);
       int acctive = Integer.parseInt(linesa.get(3))-1;
       int DEacctive = Integer.parseInt(linesa.get(4))+1;
       String max = linesa.get(5);
        //
        File Master = new File(DESC_LOGBOOK_PATH);
        Scanner MasterScanner = new Scanner(Master);
        String currentLine = "AD";
         if (!MasterScanner.hasNextLine()) {
           currentLine = null;
         }
        else{
        currentLine =MasterScanner.nextLine();
        }
         MasterScanner.close();
        lines.add(currentLine);
        lines.add(CreationDate);
        lines.add(dateFormat.format(date) + "[America/Guatemala]");
        lines.add(String.valueOf(acctive));
        lines.add(String.valueOf(DEacctive));
        lines.add(max);
        Utilities Utilidades = new Utilities();
        Utilidades.LlenarArchivo(DESC_LOGBOOK_PATH, lines);
        }
    }
 private void WriteInTEMP(Usuario usuario) throws IOException
    {
        String Userline =  usuario.getUsuario()+"|"+usuario.getNombre()+"|"+
                usuario.getApellido()+"|"+usuario.getPassword()+"|"+usuario.Rol()+"|"+
                usuario.getFechaDeNacimiento()+"|"+usuario.getCorreo()+"|"+usuario.getTelefono()+"|"+
                usuario.getFotografia()+"|"+usuario.getDescripcion()+"|"+usuario.getEstatus()+System.getProperty("line.separator");
      Files.write(Paths.get(DEFAULT_TEMP_DIRECTORYC), Userline.getBytes(), StandardOpenOption.APPEND);
    }
  private final String DEFAULT_TEMP_DIRECTORYC = "C:\\MEIA\\Temp.txt";
    private final String USER_PATH = "C:\\MEIA\\Usuario.txt";              //MASTER
    private final String LOGBOOK_PATH = "C:\\MEIA\\Bitacora.txt";          //APILO
    private final String DESC_USER_PATH = "C:\\MEIA\\Desc_Usuario.txt"; 
    private final String DESC_LOGBOOK_PATH = "C:\\MEIA\\Desc_Bitacora.txt"; 
    
    public void SetUserData(Usuario newUser) throws FileNotFoundException, IOException
    {
        boolean flasdf = true;
        if(versiExiste(newUser,USER_PATH ))
        {
            File tempFile = new File(DEFAULT_TEMP_DIRECTORYC);
        tempFile.createNewFile();
         File inputFile = new File(USER_PATH);
        File Master = new File(USER_PATH);
        Scanner MasterScanner = new Scanner(Master);
        String currentLine;
         if (!MasterScanner.hasNextLine()) {
           currentLine = null;
         }
        else{
        currentLine =MasterScanner.nextLine();
        }
        while((currentLine) != null) {
            String [] credenciales = currentLine.split(Pattern.quote("|"));
                if (!newUser.getUsuario().equals(credenciales[0]))
                {
                    WriteInTEMP_Ca(currentLine);
            }
             currentLine = readLine(MasterScanner);
        }
        WriteInTEMP(newUser);
        
        if(tempFile.renameTo(inputFile))
            {
                try (FileOutputStream writer = new FileOutputStream(DEFAULT_TEMP_DIRECTORYC)) {
            writer.write(("").getBytes());
            writer.close();
        }
            } 
        MasterScanner.close();
        String newFilePath = inputFile.getAbsolutePath();
        Path path = Paths.get(inputFile.getAbsolutePath());
        Files.delete(path);
        File newFile=new File(newFilePath);
        FileUtils.moveFile(tempFile, newFile);
        try (FileOutputStream writer = new FileOutputStream(DEFAULT_TEMP_DIRECTORYC)) {
            writer.write(("").getBytes());
            writer.close();}
        }
        else if (versiExiste(newUser, LOGBOOK_PATH))
        {flasdf = false;
            File tempFile = new File(DEFAULT_TEMP_DIRECTORYC);
        tempFile.createNewFile();
            File inputFile = new File(LOGBOOK_PATH);
        File Master = new File(LOGBOOK_PATH);
        Scanner MasterScanner = new Scanner(Master);
        String currentLine;
         if (!MasterScanner.hasNextLine()) {
           currentLine = null;
         }
        else{
        currentLine =MasterScanner.nextLine();
        }
        while((currentLine) != null) {
            String [] credenciales = currentLine.split(Pattern.quote("|"));
                if (!newUser.getUsuario().equals(credenciales[0]))
                {
                    WriteInTEMP_Ca(currentLine);
            }
             currentLine = readLine(MasterScanner);
        }
        WriteInTEMP(newUser);
        
            if(tempFile.renameTo(inputFile))
            {
                try (FileOutputStream writer = new FileOutputStream(DEFAULT_TEMP_DIRECTORYC)) {
            writer.write(("").getBytes());
            writer.close();
        }
            }
            String newFilePath = inputFile.getAbsolutePath();
        Path path = Paths.get(inputFile.getAbsolutePath());
        MasterScanner.close();
        Files.delete(path);
        File newFile=new File(newFilePath);
        FileUtils.moveFile(tempFile, newFile);
            MasterScanner.close();
            try (FileOutputStream writer = new FileOutputStream(DEFAULT_TEMP_DIRECTORYC)) {
            writer.write(("").getBytes());
            writer.close();
        }
        }
        
        if(flasdf)
        {
            new File(DEFAULT_TEMP_DIRECTORYC).renameTo(new File(USER_PATH));
        }
        else
        {
            new File(DEFAULT_TEMP_DIRECTORYC).renameTo(new File(LOGBOOK_PATH));
        }
        
        /*
        String NewstrUser = retornarUsuarioParaBitacora(newUser);
        Utilities objUtilities = new Utilities();
        if(Index>0)
        {
            objUtilities.removeLine(newUser.getUsuario(), NewstrUser, Index);
            return;
        }
        else if (Index==0) 
        {              
            objUtilities.removeLine(newUser.getUsuario(), NewstrUser, Index);
        }
        Index = -1 *Index;*/
        
          
    }
    
    private String retornarUsuarioParaBitacora(Usuario usuario){
        return usuario.getUsuario()+"|"+usuario.getNombre()+"|"+usuario.getApellido()+"|"+usuario.getPassword()+"|"+usuario.Rol()+"|"+usuario.getFechaDeNacimiento()+"|"+usuario.getCorreo()+"|"+usuario.getTelefono()+"|"+usuario.getFotografia()+"|"+usuario.getDescripcion()+"|"+usuario.getEstatus();
       // return usuario.getUsuario()+"|"+usuario.getPassword()+"|"+usuario.Rol()+"|"+usuario.getEstatus()+"|"+usuario.getNombre()+"|"+usuario.getApellido()+"|"+usuario.getFechaDeNacimiento()+"|"+usuario.getFotografia()+"|"+usuario.getTelefono()+"|"+usuario.getCorreo()+"|"+usuario.getDescripcion();
    }
    
    public boolean versiExiste(Usuario user, String patth) throws FileNotFoundException
    {
        File usuarios = new File(patth);
        Usuario result;
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(patth);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(Pattern.quote("|"));
                if (user.getUsuario().equals(credenciales[0])) {
                    result = new Usuario(credenciales[0], credenciales[1], credenciales[2], credenciales[3], Integer.parseInt(credenciales[4]), credenciales[5], credenciales[6], credenciales[7], credenciales[8], credenciales[9], Integer.parseInt(credenciales[10]));
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        }
        return false;
    }

    private  int getIndexUser(String strUser) throws FileNotFoundException 
    {
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
        int count = 0;
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(Pattern.quote("|"));
                if (strUser.equals(credenciales[0])) {
                    scanner.close();
                    return count ;
                }
                count++;
            }
            scanner.close();
        }
        count = 0;
        usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
        if (usuarios.exists()) {
           Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(Pattern.quote("|"));
                if (strUser.equals(credenciales[0])) {
                    scanner.close();
                    return count ;
                }
                count--;
            }
            scanner.close();  
        }
        return 0;
    }
    
}


