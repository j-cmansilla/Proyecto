/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author sebas
 */
public class REO {
    
    private final String DEFAULT_TEMP_DIRECTORY = "C:\\MEIA\\Temp.txt";
    private final String USER_PATH = "C:\\MEIA\\Usuario.txt";              //MASTER
    private final String LOGBOOK_PATH = "C:\\MEIA\\Bitacora.txt";          //APILO
    private final String DESC_USER_PATH = "C:\\MEIA\\Desc_Usuario.txt"; 
    private final String DESC_LOGBOOK_PATH = "C:\\MEIA\\Desc_Bitacora.txt"; 
    
    Utilities Utilidades = new Utilities();
    private int MaxforReorganize = 1;
    private String CreationDate;
    private int ActiveAccounts = 0;
    
    public boolean CheckForREO(String MainUser) throws FileNotFoundException, IOException
    {
        setMaxFirst();
        if(MaxforReorganize > getCountlinesFile(LOGBOOK_PATH))
        {
            Reorganize(MainUser);
            return true;
        }
        return false;
    }
    
    public void Reorganize(String MainUser) throws IOException
    {
        File TemporalFile = new File(DEFAULT_TEMP_DIRECTORY);
        TemporalFile.createNewFile();
        setMaxFirst();
        File Master = new File(USER_PATH);
        Scanner MasterScanner = new Scanner(Master);
        File LogBook = new File(LOGBOOK_PATH);
        Scanner LogBookScanner = new Scanner(LogBook);
        //loquera
        String line1 = MasterScanner.nextLine();
        String line2 = LogBookScanner.nextLine();
         while (line1 != null || line2 != null) {
            if (line1 == null) {                 //from file2 
                WriteInTEMP(getUser(line2));
                line2 = readLine(LogBookScanner);
                
            } else if (line2 == null) {          //from file1 
                WriteInTEMP(getUser(line1));
                line1 = readLine(MasterScanner);
                
            } else if (getUserName(line1, MasterScanner).compareToIgnoreCase(getUserName(line2, LogBookScanner)) <= 0) {
                                                //from file1 
                WriteInTEMP(getUser(line1));
                line1 = readLine(MasterScanner);
                
            } else {                             //from file2 
                WriteInTEMP(getUser(line2));
                line2 = readLine(LogBookScanner);
            }
      }
        MasterScanner.close();
        LogBookScanner.close();
        
        DeleteInactive();
        CleanLastStep(MainUser);
    }
    
    private void DeleteInactive() throws IOException
    {
        File inputFile = new File(USER_PATH);
        File tempFile = new File(DEFAULT_TEMP_DIRECTORY);

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(CheckStatus(currentLine)) {
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }
        writer.close(); 
        reader.close(); 
        tempFile.renameTo(inputFile);
        ActiveAccounts =  getCountlinesFile(USER_PATH);
    }
    
     //*************************************************************************************************************
    private int getCountlinesFile(String path) throws IOException
    {
         BufferedReader reader = new BufferedReader(new FileReader(path));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
    private String readLine(Scanner reader) {
        if (reader.hasNextLine())
            return reader.nextLine();
        else
            return null;
  }
    private Boolean CheckStatus(String line)
    {
        Usuario user = getUser(line);
        return user.getEstatus() != 0;
    }
    private String getUserName(String ScanerLine,Scanner reader)
    {
        Usuario user = getUser(ScanerLine);
       // if(user.getEstatus()==0) //IDK if this works
         //   getUserName(readLine(reader), reader);
        return user.getUsuario();
    }
    private Usuario getUser(String ScanerLine)
    {
        String [] credenciales = ScanerLine.split(Pattern.quote("|"));
        Usuario user = new Usuario(credenciales[0], credenciales[4], credenciales[5], credenciales[1], 
                Integer.parseInt(credenciales[2]), credenciales[6], credenciales[9], credenciales[8], 
                credenciales[7], credenciales[10], Integer.parseInt(credenciales[3]));
        return user;
    }
    
    private void WriteInTEMP(Usuario usuario) throws IOException
    {
        String Userline = usuario.getUsuario()+"|"+usuario.getPassword()+"|"+usuario.Rol()+"|"
                +usuario.getEstatus()+"|"+usuario.getNombre()+"|"+usuario.getApellido()+"|"+
                usuario.getFechaDeNacimiento()+"|"+usuario.getFotografia()+"|"+usuario.getTelefono()+"|"+
                usuario.getCorreo()+"|"+usuario.getDescripcion();
        FileWriter writerTEMP = new FileWriter(DEFAULT_TEMP_DIRECTORY); 
        writerTEMP.write(Userline + String.format("%n"));
    }
   
    private void CleanLastStep(String User) throws FileNotFoundException, IOException
    {
        try (FileOutputStream writer = new FileOutputStream(LOGBOOK_PATH)) {
            writer.write(("").getBytes());
        }
        Path path = Paths.get(LOGBOOK_PATH);
        Files.delete(path);
        new File(DEFAULT_TEMP_DIRECTORY).renameTo(new File(USER_PATH));
        
        List<String> lines = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        
        lines.add(User);
        lines.add(CreationDate);
        lines.add(dateFormat.format(date) + "[America/Guatemala]");
        lines.add("0");
        lines.add("0");
        lines.add(String.valueOf(MaxforReorganize));
        Utilidades.LlenarArchivo(DESC_LOGBOOK_PATH, lines);
        
        lines.remove(lines.size() - 1);
        lines.remove(lines.size() - 1);
        lines.remove(lines.size() - 1);
        lines.add(String.valueOf(ActiveAccounts));
        lines.add("0");
        Utilidades.LlenarArchivo(DESC_USER_PATH, lines);
        
    }
   
    private void setMaxFirst() throws IOException {
        Path path = Paths.get(DESC_LOGBOOK_PATH);
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = Files.readAllLines(path,charset);
        MaxforReorganize = Integer.parseInt(lines.get(lines.size()-1));
        CreationDate = lines.get(1);
    }
}
