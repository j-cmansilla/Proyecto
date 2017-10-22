/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
        if(MaxforReorganize == countLines(LOGBOOK_PATH))
        {
            Reorganize(MainUser);
            return true;
        }
        MantenimientoAsociacionAmigosGrupo MAAG = new MantenimientoAsociacionAmigosGrupo();
        MAAG.ReoIndex(MainUser);
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
        String line1;
        String line2;
        if (!MasterScanner.hasNextLine()) {
           line1 = null;
         }
        else{
        line1 =MasterScanner.nextLine();
        }
        if (!LogBookScanner.hasNextLine()) {
           line2 = null;
         }
        else{
        line2 = LogBookScanner.nextLine();
        }
         while (line1 != null || line2 != null) {
            if (line1 == null) {                 //from file2 
                try{
                WriteInTEMP(getUser(line2));
               }
               catch (Exception e)
                       {
                           WriteInTEMP_Ca(line2);
                       }
                line2 = readLine(LogBookScanner);
                
            } else if (line2 == null) {          //from file1 
               try{
                WriteInTEMP(getUser(line1));
               }
               catch (Exception e)
                       {
                           WriteInTEMP_Ca(line1);
                       }
                line1 = readLine(MasterScanner);
                
            } else if (getUserName(line1).compareToIgnoreCase(getUserName(line2)) <= 0) {
                                                //from file1 
               try{
                WriteInTEMP(getUser(line1));
               }
               catch (Exception e)
                       {
                           WriteInTEMP_Ca(line1);
                       }
                line1 = readLine(MasterScanner);
                
            } else {                             //from file2 
                try{
                WriteInTEMP(getUser(line2));
               }
               catch (Exception e)
                       {
                           WriteInTEMP_Ca(line2);
                       }
                line2 = readLine(LogBookScanner);
            }
      }
        MasterScanner.close();
        LogBookScanner.close();
        
        CleanLastStep(MainUser);
        DeleteInactive();
        ReorganizeMaster();
    }
    
    private void DeleteInactive() throws IOException
    {
        File inputFile = new File(USER_PATH);
        File tempFile = new File(DEFAULT_TEMP_DIRECTORY);
        tempFile.createNewFile();
        
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
            if(CheckStatus(currentLine)) {
               WriteInTEMP_Ca(currentLine);
            }
             currentLine = readLine(MasterScanner);
        }
        MasterScanner.close();
        tempFile.renameTo(inputFile);
        ActiveAccounts =  getCountlinesFile(USER_PATH);
        ChangeDESCBIT();
        try (FileOutputStream writer = new FileOutputStream(DEFAULT_TEMP_DIRECTORY)) {
            writer.write(("").getBytes());
            writer.close();
        }
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
        try {
             Usuario user = getUser(line);
             return user.getEstatus() != 0;
        } catch (Exception e) {
            String [] credenciales = line.split(Pattern.quote("|"));
            if(Integer.parseInt(credenciales[10])!= 0) {
                return true;
            } 
            return false;
        }  
    }
    private String getUserName(String ScanerLine)
    {
        Usuario user = getUser(ScanerLine);
        if(user != null)
            return user.getUsuario();
        String [] credenciales = ScanerLine.split(Pattern.quote("|"));
        return (credenciales[0]);
    }
    private Usuario getUser(String ScanerLine)
    {
        try {
                 String [] credenciales = ScanerLine.split(Pattern.quote("|"));
        return new Usuario(credenciales[0], credenciales[1], credenciales[2], credenciales[3], Integer.parseInt(credenciales[4]), credenciales[5], credenciales[6], credenciales[7], credenciales[8], credenciales[9], Integer.parseInt(credenciales[10]));
    
       } catch (Exception e) {
           return  null;
        }
   }
        
    public void ReorganizeMaster() throws FileNotFoundException{
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        manejador.ordenarMaster();
    }
    
    private void WriteInTEMP(Usuario usuario) throws IOException
    {
        String Userline =  usuario.getUsuario()+"|"+usuario.getNombre()+"|"+
                usuario.getApellido()+"|"+usuario.getPassword()+"|"+usuario.Rol()+"|"+
                usuario.getFechaDeNacimiento()+"|"+usuario.getCorreo()+"|"+usuario.getTelefono()+"|"+
                usuario.getFotografia()+"|"+usuario.getDescripcion()+"|"+usuario.getEstatus()+System.getProperty("line.separator");
      Files.write(Paths.get(DEFAULT_TEMP_DIRECTORY), Userline.getBytes(), StandardOpenOption.APPEND);
    }
    private void WriteInTEMP_Ca(String usuario) throws IOException
    {
        String Userline = usuario + System.lineSeparator();
      Files.write(Paths.get(DEFAULT_TEMP_DIRECTORY), Userline.getBytes(), StandardOpenOption.APPEND);
    }
   
    private void CleanLastStep(String User) throws FileNotFoundException, IOException
    {
        try (FileOutputStream writer = new FileOutputStream(LOGBOOK_PATH)) {
            writer.write(("").getBytes());
            writer.close();
        }
        Path path = Paths.get(USER_PATH);
        Files.delete(path);
        new File(DEFAULT_TEMP_DIRECTORY).renameTo(new File(USER_PATH));
        
        List<String> lines = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        
        lines.add(User);
        lines.add(CreationDate);
        lines.add(dateFormat.format(date) + "[America/Guatemala]");
        lines.add("1");
        lines.add("0");
        lines.add(String.valueOf(MaxforReorganize));
        Utilidades.LlenarArchivo(DESC_LOGBOOK_PATH, lines);
        
        lines.remove(lines.size() - 1);
        lines.remove(lines.size() - 1);
        lines.remove(lines.size() - 1);
        lines.add(String.valueOf(countLines(USER_PATH)));
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
    
    private void ChangeDESCBIT() throws IOException
    {
        int acctive = countLines(USER_PATH);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        List<String> lines = new ArrayList<String>();
        
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
        lines.add("0");
        Utilidades.LlenarArchivo(DESC_USER_PATH, lines);
    }
    
    public int countLines(String filename) throws IOException {
    InputStream is = new BufferedInputStream(new FileInputStream(filename));
    try {
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        boolean empty = true;
        while ((readChars = is.read(c)) != -1) {
            empty = false;
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n') {
                    ++count;
                }
            }
        }
        return (count == 0 && !empty) ? 1 : count;
    } finally {
        is.close();
    }
}
}
