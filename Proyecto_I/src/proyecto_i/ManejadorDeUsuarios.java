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
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Mansilla
 */
public class ManejadorDeUsuarios {
    
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\Usuario.txt";
    private final String DEFAULT_LOGIN_USER_DIRECTORY = "C:\\MEIA\\UsuarioLogueado.txt";
    
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
        setUserToLogin("", "", "");
    }
    
    public User getUserLogin() throws FileNotFoundException{
        File archivoUsuarios = new File(DEFAULT_LOGIN_USER_DIRECTORY);
        if (archivoUsuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_LOGIN_USER_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(",");
                boolean isAdmin = false;
                if (credenciales[2].equals("true")) {
                    isAdmin = true;
                }
                return new User(credenciales[0], credenciales[1], isAdmin);
            }
        }    
        return new User();
    }
    
    public void setUserToLogin(String userName, String pass, String isAdmin){
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(DEFAULT_LOGIN_USER_DIRECTORY), "utf-8"));
            writer.write(userName+","+pass+","+isAdmin+System.getProperty("line.separator"));
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
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
                    setUserToLogin(credenciales[0], credenciales[1], credenciales[2]);
                    return true;
                }
            } 
            try{
                FileWriter Escribir = new FileWriter(archivo,true);
                BufferedWriter bw = new BufferedWriter(Escribir);
                bw.write(user+","+pass+","+"false"+System.getProperty("line.separator"));
                JOptionPane.showMessageDialog(null, user+" creado correctamente!");
                setUserToLogin(user, pass, "false");
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
                setUserToLogin(user, pass, "true");
                return true;
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (IOException ex) {/*ignore*/}
            }
        }
        return false;
    }
}


