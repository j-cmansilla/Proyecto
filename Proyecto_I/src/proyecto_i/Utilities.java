/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
//import org.apache.commons.io.FileUtils;

/**
 *
 * @author Harry
 */
public class Utilities {
    private final String DEFAULT_BACKUP_DIRECTORY = "MEIA_Backup";
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    private final String DEFAULT_BACKUP_BITACORA_DIRECTORY = "bitacora_backup.txt";
    
    public void createBackUp(String newdirectory, String user){
        try 
        {
            File directory = new File(newdirectory + DEFAULT_BACKUP_DIRECTORY);
            if (!directory.exists()) 
            {
                directory.mkdirs();
            }
            File actualDirectory = new File(DEFAULT_DIRECTORY);
            //FileUtils.copyDirectory(actualDirectory, directory);
            
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
    
}
