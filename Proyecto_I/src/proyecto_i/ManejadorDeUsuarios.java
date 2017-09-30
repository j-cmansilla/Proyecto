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
import java.nio.file.Files;
import java.security.MessageDigest;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import javax.swing.JOptionPane;

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
    
    public void CrearArchivos(){
        try{
            File archivoUsuarios = new File(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY);
            File descriptorUsuarios = new File(DEFAULT_DES_DIR+DEFAULT_USER_DIRECTORY);
            File archivoBitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY);
            File descriptorBitacora = new File(DEFAULT_DES_DIR+DEFAULT_BITACORA_DIRECTORY);
            File bitacoraBackup = new File(DEFAULT_DIRECTORY+DEFAULT_BACKUP_BITACORA_DIRECTORY);
            archivoUsuarios.createNewFile();
            descriptorUsuarios.createNewFile();
            archivoBitacora.createNewFile();
            descriptorBitacora.createNewFile();
            bitacoraBackup.createNewFile();
        }catch(IOException e){
            
        }
    }
    
    public boolean usuarioExistente(String user, String password) throws FileNotFoundException{
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
                if (user.equals(credenciales[0]) && password.equals(credenciales[1])) {
                    JOptionPane.showMessageDialog(null, "El usuario: "+user+" y su pass son correctos!");
                    scanner.close();
                    return true;
                }
            }
        }
        //buscarlo en el master
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split("|");
                if (user.equals(credenciales[0]) && password.equals(credenciales[1])) {
                    JOptionPane.showMessageDialog(null, "El usuario: "+user+" y su pass son correctos!");
                    scanner.close();
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "El usuario: "+user+" no existe en el contexto actual!");
        return false;
    }
    
    public void llenarBitacora(String userName, ZonedDateTime zdt, Usuario usuario) throws FileNotFoundException{
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
            llenarBitacora(isFull, retornarUsuarioParaBitacora(usuario));
        }
    }
    
    private String retornarUsuarioParaBitacora(Usuario usuario){
        return usuario.getUsuario()+"|"+usuario.getPassword()+"|"+usuario.Rol()+"|"+usuario.getEstatus()+"|"+usuario.getNombre()+"|"+usuario.getApellido()+"|"+usuario.getFechaDeNacimiento()+"|"+usuario.getFotografia()+"|"+usuario.getTelefono()+"|"+usuario.getCorreo()+"|"+usuario.getDescripcion();
    }
    
    private void llenarBitacora(boolean isFull, String newUser) throws FileNotFoundException{
        if (isFull) {
            
           Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY), "utf-8"));
                writer.write(newUser);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            } 
        }else{
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_BITACORA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            try{
                FileWriter Escribir = new FileWriter(archivo,true);
                BufferedWriter bw = new BufferedWriter(Escribir);
                bw.write(newUser+System.getProperty("line.separator"));
                //setUserToLogin(user, pass, "false");
                bw.close();
                Escribir.close();
            }catch(IOException e){

            }
        }
    }
    
    public byte[] cifra(String sinCifrar) throws Exception {
	final byte[] bytes = sinCifrar.getBytes("UTF-8");
	final Cipher aes = obtieneCipher(true);
	final byte[] cifrado = aes.doFinal(bytes);
	return cifrado;
    }

    public String descifra(byte[] cifrado) throws Exception {
	final Cipher aes = obtieneCipher(false);
	final byte[] bytes = aes.doFinal(cifrado);
	final String sinCifrar = new String(bytes, "UTF-8");
	return sinCifrar;
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
    
    public boolean UsuarioCorrecto(String user, String pass) throws FileNotFoundException{
        File archivoUsuarios = new File(DEFAULT_DIRECTORY);
        if (archivoUsuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split("|");
                if (user.equals(credenciales[0]) && pass.equals(credenciales[1])) {
                    JOptionPane.showMessageDialog(null, "El usuario: "+user+" y su pass son correctos!");
                    
                    return true;
                }
            }
        }else{
            Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DIRECTORY), "utf-8"));
                writer.write("");
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
        }
        return false;
    }
    
    public boolean UserExists(String user, String pass) throws FileNotFoundException, IOException, Exception{
        //Validate if the file exists
        
        File archivoUsuarios = new File(DEFAULT_DIRECTORY);
        if (archivoUsuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(",");
                if (user.equals(credenciales[0]) && pass.equals(credenciales[1])) {
                    JOptionPane.showMessageDialog(null, "El usuario: "+user+" y su pass son correctos!");
                    
                    return true;
                }
            } 
            try{
                FileWriter Escribir = new FileWriter(archivo,true);
                BufferedWriter bw = new BufferedWriter(Escribir);
                bw.write(user+","+pass+","+"false"+System.getProperty("line.separator"));
                JOptionPane.showMessageDialog(null, user+" creado correctamente!");
                
                bw.close();
                Escribir.close();
            }catch(IOException e){

            }
        }else{
            Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DIRECTORY), "utf-8"));
                writer.write(user+","+pass+","+"true"+System.getProperty("line.separator"));
                JOptionPane.showMessageDialog(null, "El usuario: "+user+" ha sido creado!");
                
                return true;
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
        }
        return false;
    }
    
    public Usuario getUserData(String user) throws FileNotFoundException{
        File usuarios = new File(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY);
        Usuario result;
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY+DEFAULT_USER_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split("|");
                if (user.equals(credenciales[0])) {
                    result = new Usuario(credenciales[0], credenciales[4], credenciales[5], credenciales[1], Integer.parseInt(credenciales[2]), credenciales[6], credenciales[9], credenciales[8], credenciales[7], credenciales[10], Integer.parseInt(credenciales[3]));
                    scanner.close();
                    return result;
                }
            }
        }
        return null;        
    }
    //********************************************************************************************
    public void SetUserData(Usuario newUser) throws FileNotFoundException  //pending test*********
    {
        int Index = getIndexUser(newUser.getUsuario());
        String NewstrUser = retornarUsuarioParaBitacora(newUser);
        if(Index>0) //DEFAULT_USER_DIRECTORY
        {
           
            return;
        }//else DEFAULT_BITACORA_DIRECTORY
        Index = -1 *Index;
          
    }
    
    private  int getIndexUser(String strUser) throws FileNotFoundException //pending test*********
    {
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
        int count = 0;
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split("|");
                if (strUser.equals(credenciales[0])) {
                    scanner.close();
                    return count ;
                }
                count++;
            }
        }
        count = 0;
        usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
        if (usuarios.exists()) {
           Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split("|");
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


