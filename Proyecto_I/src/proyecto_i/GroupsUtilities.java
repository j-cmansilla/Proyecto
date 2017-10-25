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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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
    public List<String> GetUnique(List<String> A, List<String> B)
    {
        List<String> Temp = new ArrayList<String>();
        String [] DATAindex = new String[8];

        for(int i =0; i< B.size(); i++)
        {
             DATAindex = B.get(i).split(Pattern.quote(" Desc: "));
             Temp.add(DATAindex[0]);
        }
        
            for(int j =0; j< Temp.size(); j++)
            {
                if(!A.contains(Temp.get(j)))
                    A.add(Temp.get(j));
            }
        
        return A;
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
     public List<String> GetGroups(String MainUser) throws FileNotFoundException
     {
        List<String> Groupslst = new ArrayList<>();
        List<String> GroupslstAdmin = new ArrayList<>();
        File Index = new File(DEFAULT_INDEXGROUPS);
        try (Scanner IndexScanner = new Scanner(Index)) {
            String currentLine = readLine(IndexScanner);
            String [] DATAindex = new String[8];
            while(currentLine!=null)
            {
                DATAindex = currentLine.split(Pattern.quote("|"));
                if(Integer.parseInt(DATAindex[6]) == 1 && DATAindex[4].equals(MainUser))
                {
                    Groupslst.add(DATAindex[3]);
                }
                if(Integer.parseInt(DATAindex[6]) == 1 && DATAindex[2].equals(MainUser))
                {
                    GroupslstAdmin.add(DATAindex[3]);
                }
                currentLine = readLine(IndexScanner);
            }
            Set<String> hs = new HashSet<>();
            hs.addAll(GroupslstAdmin);
            GroupslstAdmin.clear();
            GroupslstAdmin.addAll(hs);
            for(int i =0; i<GroupslstAdmin.size();i++)
            {
                Groupslst.add(GroupslstAdmin.get(i));
            }
            IndexScanner.close();
        }
        return  Groupslst;
     }
     
     public int GetMembersCount(String MainUser, String Group) throws FileNotFoundException
     {
        int k=0;
        File Index = new File(DEFAULT_INDEXGROUPS);
        try (Scanner IndexScanner = new Scanner(Index)) {
            String currentLine = readLine(IndexScanner);
            String [] DATAindex = new String[8];
            while(currentLine!=null)
            {        //  0          1          2   3   4          5                6
                //Registro | Posicion |  Llave 1,2,3  |  Siguiente        | estatus -> INDEX
                DATAindex = currentLine.split(Pattern.quote("|"));
                if(Integer.parseInt(DATAindex[6]) == 1 && (DATAindex[2]+DATAindex[3]).equals(MainUser+Group))
                {
                    k++;
                }
                currentLine = readLine(IndexScanner);
            }
            IndexScanner.close();
        }
        return k;
     }
     //***************************************************
     private final String DEFAULT_INDEXGROUPS = "C:\\MEIA\\IndiceGrupos.txt";
     
     public void DeleteGroup(String MainUser, String Group) throws FileNotFoundException, IOException
     {
         MantenimientoAsociacionAmigosGrupo MAAG = new MantenimientoAsociacionAmigosGrupo();
         List<String> Temp = new ArrayList<>();
         File source = new File(DEFAULT_INDEXGROUPS);
         String[] DATAindex = new String[8];
         Scanner IndexScanner = new Scanner(source);
//            String currentLine = readLine(IndexScanner);
            //  0          1          2   3   4          5                6
            //Registro | Posicion |  Llave 1,2,3  |  Siguiente        | estatus -> INDEX
            while(IndexScanner.hasNextLine())
            {
                DATAindex = IndexScanner.nextLine().split(Pattern.quote("|"));
                if((DATAindex[2]+DATAindex[3]).equals(MainUser+Group))
                {
                    Temp.add(DATAindex[2]+"|"+DATAindex[3]+"|"+DATAindex[4]);
                }
//                currentLine = readLine(IndexScanner);
            }
            IndexScanner.close();
        
        
         for(int i =0; i<Temp.size();i++)
         {
           DATAindex = Temp.get(i).split(Pattern.quote("|"));
           MAAG.DeleteFriend(DATAindex[0], DATAindex[1], DATAindex[2]);
         }
     }
     
}
