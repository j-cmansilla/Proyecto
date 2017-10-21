/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.File;
import java.io.FileNotFoundException;
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
            if(key.equals(DATAindex[1] + DATAindex[2]))
                Members.add(DATAindex[3]);
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
    
}