/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author sebas
 */
public class GroupsUtilities {
    private final String DEFAULT_GROUPMASTER_DIRECTORY = "C:\\MEIA\\Grupos.txt";
    private final String DEFAULT_BITACORA_GROUP_DIRECTORY = "C:\\MEIA\\BitacoraGrupos.txt";
    private final String DEFAULT_TEMP = "C:\\MEIA\\tempBlock.txt";
    
    //Usuario | grupo | descripcion | miembros | Fecha_transaccion | estatus -> GROUPS
    public List<String> GetUserGroupsAdministrate(String MainUser) throws FileNotFoundException
    {
        List<String> Groups = new ArrayList<>();
        File Master = new File(DEFAULT_GROUPMASTER_DIRECTORY);
        File Loogbook = new File(DEFAULT_BITACORA_GROUP_DIRECTORY);
        Scanner MasterScanner = new Scanner(Master);
        Scanner LoogbookScanner = new Scanner(Loogbook);
        String[]DATA;
        String currentline = readLine(MasterScanner);
        ManejadorDeUsuarios MU = new ManejadorDeUsuarios();
        while(currentline!= null)
        {
            DATA = currentline.split(Pattern.quote("|"));
            if(DATA[0].equals(MainUser)&& Integer.parseInt(DATA[5])== 1)
                Groups.add(DATA[1] + " Desc: " + DATA[2]);
                //Groups.add(new Grupo( MU.getUserData(DATA[0]),DATA[1],DATA[2],Integer.parseInt(DATA[3]),DATA[4],Integer.parseInt(DATA[5])));
            currentline = readLine(MasterScanner);
        }
        currentline = readLine(LoogbookScanner);
        while(currentline!= null)
        {
            DATA = currentline.split(Pattern.quote("|"));
            if(DATA[0].equals(MainUser) && Integer.parseInt(DATA[5])==1)
                Groups.add(DATA[1] + " Desc: " + DATA[2]);
            currentline = readLine(LoogbookScanner);
        }
        return Groups;
    }
     private final String DEFAULT_INDEXGROUPS = "C:\\MEIA\\IndiceGrupos.txt";
    //Registro | Posicion |  Llave 1,2,3  |  Siguiente        | estatus -> INDEX
    public List<String> GetMembers(String MainUser, String GroupName) throws FileNotFoundException
    {
        List<String> Members = new ArrayList<>();
        File Index = new File(DEFAULT_INDEXGROUPS);
        Scanner IndexScanner = new Scanner(Index);
        String currentLine = readLine(IndexScanner);
        String [] DATAindex = new String[8];
        String key = MainUser + GroupName;
        while( currentLine != null)
        {
            DATAindex = currentLine.split(Pattern.quote("|"));
            if(key.equals(DATAindex[2] + DATAindex[3]) && Integer.parseInt(DATAindex[6])==1)
                Members.add(DATAindex[4]);
            currentLine = readLine(IndexScanner);
        }
         return Members;
    }
    private String readLine(Scanner reader) {
        if (reader.hasNextLine())
            return reader.nextLine();
        else
            return null;
    }
    public int GetNumberofLines(String Path) throws FileNotFoundException, IOException
    {
        File file =new File(Path);
        int linenumber = 0;
    	if(file.exists()){
            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);
            while (lnr.readLine() != null){
                linenumber++;
            }
            lnr.close();
        }
        return linenumber;
    }
    
    public ArrayList GetUniq(ArrayList A, ArrayList B)
    {
        List<String> RES= new ArrayList();
        for(int j =0; j< A.size();j++)
            {
                if(!B.contains(A.get(j)))
                {
                    RES.add((String) A.get(j));
                }
            }
        return (ArrayList) RES;
    }
    
    public void CleanEmptySpace(String path) throws FileNotFoundException, IOException
    {
         Scanner file;
         File temp = new File(DEFAULT_TEMP);
         temp.createNewFile();
         PrintWriter writer;
            file = new Scanner(new File(path));
            
            writer = new PrintWriter(DEFAULT_TEMP);

            while (file.hasNext()) {
                String line = file.nextLine();
                if (!line.isEmpty()) {
                    writer.write(line+System.lineSeparator());
                    
                }
            }
            file.close();
            writer.close();
            File b = new File(path);
            b.delete();
            new File(DEFAULT_TEMP).renameTo(new File(path));
    }
}
