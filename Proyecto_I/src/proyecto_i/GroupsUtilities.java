/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.File;
import java.io.FileNotFoundException;
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
    // private final String DEFAULT_TEMP_DIRECTORY = "Temp.txt";
    //private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    //private final String DEFAULT_DES_DIR = "C:\\MEIA\\Desc_";
    
    
    //Usuario | grupo | descripcion | miembros | Fecha_transaccion | estatus -> GROUPS
    public List<String> GetUserGroupsAdministrate(String MainUser) throws FileNotFoundException
    {
        List<String> Groups = new ArrayList<String>();
        File Master = new File(DEFAULT_GROUPMASTER_DIRECTORY);
        File Loogbook = new File(DEFAULT_BITACORA_GROUP_DIRECTORY);
        Scanner MasterScanner = new Scanner(Master);
        Scanner LoogbookScanner = new Scanner(Loogbook);
        String[]DATA;
        String currentline = readLine(MasterScanner);
        while(currentline!= null)
        {
            DATA = currentline.split(Pattern.quote("|"));
            if(DATA[0] == MainUser&& DATA[5]==String.valueOf(1))
                Groups.add(currentline);
            currentline = readLine(MasterScanner);
        }
        currentline = readLine(LoogbookScanner);
        while(currentline!= null)
        {
            DATA = currentline.split(Pattern.quote("|"));
            if(DATA[0] == MainUser && DATA[5]==String.valueOf(1))
                Groups.add(currentline);
            currentline = readLine(LoogbookScanner);
        }
        return Groups;
    }
    
    private String readLine(Scanner reader) {
        if (reader.hasNextLine())
            return reader.nextLine();
        else
            return null;
    }
    
}
