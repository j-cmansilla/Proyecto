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
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Mansilla
 */
public class ManejadorDeUsuarios {
    
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\users.txt";
    
    public boolean UserExists(String user, String pass) throws FileNotFoundException, IOException{
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
}


