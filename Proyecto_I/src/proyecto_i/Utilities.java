/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
//import org.apache.commons.io.FileUtils;

/**
 *
 * @author Harry
 */
public class Utilities {
    private final String DEFAULT_BACKUP_DIRECTORY = "\\MEIA_Backup";
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    private final String DEFAULT_BACKUP_BITACORA_DIRECTORY = "bitacora_backup.txt";
    private final String DEFAULT_USER_DIRECTORY = "Usuario.txt";
    
    public void createBackUp(String newdirectory, String user){
        try 
        {
            File directory = new File(newdirectory + DEFAULT_BACKUP_DIRECTORY);
            if (!directory.exists()) 
            {
                directory.mkdirs();
            }
            File actualDirectory = new File(DEFAULT_DIRECTORY);
           // FileUtils.copyDirectory(actualDirectory, directory);
            
            File updateBitacora = new File(DEFAULT_DIRECTORY+DEFAULT_BACKUP_BITACORA_DIRECTORY);
            if(updateBitacora.exists())
            {
                ZoneId zonedId = ZoneId.of( "America/Guatemala" );
                ZonedDateTime zdt = ZonedDateTime.now( zonedId );
            
                Writer writer = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(DEFAULT_DIRECTORY+DEFAULT_BACKUP_BITACORA_DIRECTORY), "utf-8"));
                writer.write(newdirectory+DEFAULT_BACKUP_BITACORA_DIRECTORY+"|"+user+"|"+zdt.toString());
                writer.close();
            }    
                     
            
        } catch (Exception e) {
        }
    }
    
    
    //Modificar el descriptor **************************************
    private final String DEFAULT_DES_DIR = "C:\\MEIA\\Desc_";
    private final String DEFAULT_BITACORA_DIRECTORY = "Bitacora.txt";
    
    public void ChangeMaxReorg(int NumberMax,String User) throws IOException
    {
        Path path = Paths.get(DEFAULT_DES_DIR + DEFAULT_BITACORA_DIRECTORY);
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = Files.readAllLines(path,charset);
        lines.remove(lines.size() - 1);
        lines.add(String.valueOf(NumberMax));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        lines.set(2, dateFormat.format(date) + "[America/Guatemala]"); 
        lines.set(0,User);
   
        LlenarArchivo(DEFAULT_DES_DIR + DEFAULT_BITACORA_DIRECTORY, lines);
        
    }
    
    public boolean LlenarArchivo(String strPath,List<String> lines)
    {
            try
            {
                FileWriter writer = new FileWriter(strPath); 
                for(String str: lines) {
                    writer.write(str +String.format("%n"));  
                }
                writer.close();
                return true;
            }
            catch(IOException ex)
            {
                return false;
            } 
        
    }
     
    public void removeLine(String lineContent, String newLine, int op) throws IOException
    {
        File fileUser = new File(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
        File fileBitacora = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
        File temp = new File(DEFAULT_DIRECTORY + "_temp_");
        try {
            if (op == 0) 
            {
                //Bitacora
                PrintWriter outB = new PrintWriter(new FileWriter(temp));
                Files.lines(fileBitacora.toPath())
                    .filter(line -> !line.contains(lineContent))
                    .forEach(outB::println);
                outB.write(newLine);
                outB.flush();
                outB.close();
                temp.renameTo(fileBitacora);
            }
            
            if (op > 0) 
            {
                //User
                PrintWriter outU = new PrintWriter(new FileWriter(temp));
                Files.lines(fileUser.toPath())
                    .filter(line -> !line.contains(lineContent))
                    .forEach(outU::println);
                outU.write(newLine);
                outU.flush();
                outU.close();
                temp.renameTo(fileUser);
            }            
        } catch (Exception e) {
        }
        
    }
}
