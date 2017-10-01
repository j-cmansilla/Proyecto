/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author sebas
 */
public class REO {
    private final String DEFAULT_USER_DIRECTORY = "Usuario.txt"; //Master
    private final String DEFAULT_BITACORA_DIRECTORY = "Bitacora.txt"; //Apilo
    private final String DEFAULT_TEMP_DIRECTORY = "Temp.txt";
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    private final String DEFAULT_DES_DIR = "C:\\MEIA\\Desc_";
    
    Utilities Utilidades = new Utilities();
    
    public void Reorganize() throws IOException
    {
        File TemporalFile = new File(DEFAULT_DIRECTORY + DEFAULT_TEMP_DIRECTORY);
        TemporalFile.createNewFile();
        FileWriter writerTEMP = new FileWriter(DEFAULT_DIRECTORY + DEFAULT_TEMP_DIRECTORY); 
        //writerTEMP.write(String.format("%n"));
        
        
    }
    
    
    
    private void CleanLastStep() throws FileNotFoundException, IOException
    {
        FileOutputStream writer = new FileOutputStream(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
        writer.write(("").getBytes());
        writer.close();
        Path path = Paths.get(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
        Files.delete(path);
        new File(DEFAULT_DIRECTORY + DEFAULT_TEMP_DIRECTORY).renameTo(new File(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY));
        
        
    }
}
