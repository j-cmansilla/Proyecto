/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import org.apache.commons.io.FileUtils;

/**
 *
 * @author sebas
 */
public class MantenimientoAsociacionAmigosGrupo {
    private final String DEFAULT_INDEXGROUPS = "C:\\MEIA\\IndiceGrupos.txt";
    private final String DEFAULT_DESC_INDEXGROUPS = "C:\\MEIA\\Desc_IndiceGrupos.txt";
    private final String DEFAULT_FOLDER_BLOCKS ="C:\\MEIA\\Blocks";
    private final String DEFAULT_DESC_BLOCKS ="C:\\MEIA\\Blocks\\Desc_";
    private final String DEFAULT_TEMP = "C:\\MEIA\\tempIndex.txt";
    Utilities Utilidades = new Utilities();
    REO reorganize = new REO();
    GroupsUtilities GUtilities = new GroupsUtilities();
    private int MaxPerBlock = 3; // max lines in a sigle block
    private int INDEXnumber;// first item in the index
    private int INDEXcount; //number of elements
    private int NumberOfBlocks; 
    private int NumberOFUsers;
    
    public void CrearArchivos() throws IOException{
            File indexGroups = new File(DEFAULT_INDEXGROUPS);
            File descIndexGroups = new File(DEFAULT_DESC_INDEXGROUPS); 
            File folderBlocks = new File(DEFAULT_FOLDER_BLOCKS);
            indexGroups.createNewFile();
            descIndexGroups.createNewFile();
            folderBlocks.mkdirs();
    }
    
    private void NewBlock(int blockNumber, String Friendline, String MainUser) throws IOException
    { 
        File descblock = new File(DEFAULT_DESC_BLOCKS + "GRUPO" + blockNumber + ".txt");
        File Block = new File(DEFAULT_FOLDER_BLOCKS + "\\GRUPO" + blockNumber +".txt");
        Block.createNewFile();
        descblock.createNewFile();
        try (FileWriter writer = new FileWriter(DEFAULT_FOLDER_BLOCKS + "\\GRUPO" + blockNumber +".txt")) {
            writer.write(Friendline +String.format("%n"));
            writer.close();
        }
        setDescBlock(MainUser, blockNumber,true);
    }
    private void setDescBlock(String MainUser, int blockNumber, boolean isNEW) throws FileNotFoundException, IOException
    {
        /*autor
         *fecha de creacio
         *fecha de ultima modificacion
         *cantidad
         *maximo*/
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        List<String> lines = new ArrayList<>();
        File BlockDesc = new File(DEFAULT_DESC_BLOCKS + "GRUPO" + blockNumber +".txt");
        String currentLine ="";
        try (Scanner IndexDescScanner = new Scanner(BlockDesc)) {
            
            for (int i = 0; i < 2; i++) {
                if (!IndexDescScanner.hasNextLine()) {
                    currentLine = null;
                }else{
                    currentLine =IndexDescScanner.nextLine();
                }
            }
            IndexDescScanner.close();
        }
        lines.add(MainUser);
        if(isNEW)
            lines.add(dateFormat.format(date) + "[America/Guatemala]");
        else
            lines.add(currentLine);
        lines.add(dateFormat.format(date) + "[America/Guatemala]");
        int a = GUtilities.GetNumberofLines(DEFAULT_FOLDER_BLOCKS + "\\GRUPO" + blockNumber +".txt");
        lines.add(String.valueOf(a));
        lines.add(String.valueOf(MaxPerBlock));
        Utilidades.LlenarArchivo(DEFAULT_DESC_BLOCKS + "GRUPO" + blockNumber +".txt", lines);
    }
    
    private void setDescINDEX(String MainUser, int IndexNumber, boolean isNEW) throws FileNotFoundException
    {   /*
            * username
            * creation date
            * last modification date
            * NUMBER OF BLOCKS
            * INDEX NUMBER
        */
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        List<String> lines = new ArrayList<>();
        File IndexDesc = new File(DEFAULT_DESC_INDEXGROUPS);
        String currentLine ="";
        try (Scanner IndexDescScanner = new Scanner(IndexDesc)) {
            
            for (int i = 0; i < 2; i++) {
                if (!IndexDescScanner.hasNextLine()) {
                    currentLine = null;
                }else{
                    currentLine =IndexDescScanner.nextLine();
                }
            }
            IndexDescScanner.close();
        }
        lines.add(MainUser);
        if(isNEW)
            lines.add(dateFormat.format(date) + "[America/Guatemala]");
        else
            lines.add(currentLine);
        lines.add(dateFormat.format(date) + "[America/Guatemala]");
        int a = new File(DEFAULT_FOLDER_BLOCKS).listFiles().length;
        lines.add(String.valueOf(a/2));
        lines.add(String.valueOf(IndexNumber));
        Utilidades.LlenarArchivo(DEFAULT_DESC_INDEXGROUPS, lines);
    }

    private void getINDEXnumber() throws FileNotFoundException, IOException
    {   /* username
         * creation date
         * last modification date
         * NUMBER OF BLOCKS
         * INDEX NUMBER*/
        File IndexDesc = new File(DEFAULT_DESC_INDEXGROUPS);
        String currentLine ="";
         try (Scanner IndexDescScanner = new Scanner(IndexDesc)) {
            for (int i = 0; i < 5; i++) {
                if (IndexDescScanner.hasNextLine()) {
                    currentLine =IndexDescScanner.nextLine();
                    if(i==3)
                        NumberOfBlocks = Integer.parseInt(currentLine);
                    if(i==4)
                        INDEXnumber = Integer.parseInt(currentLine); 
                }
            }
            IndexDescScanner.close();
        }      
        INDEXcount = GUtilities.GetNumberofLines(DEFAULT_INDEXGROUPS);
    }
    
    private void getdescBlock(int blockNumber) throws FileNotFoundException
    {   /*autor
         *fecha de creacio
         *fecha de ultima modificacion
         *cantidad
         *maximo*/
        File BlockDesc = new File(DEFAULT_DESC_BLOCKS + "GRUPO" + blockNumber +".txt");
        String currentLine ="";
         try (Scanner IndexDescScanner = new Scanner(BlockDesc))
         {
             for (int i = 0; i < 5; i++) {
                if (IndexDescScanner.hasNextLine()) {
                    currentLine =IndexDescScanner.nextLine();
                    if(i==3)
                        NumberOFUsers = Integer.parseInt(currentLine);
                    if(i==4)
                        MaxPerBlock = Integer.parseInt(currentLine); 
                }
            }
            IndexDescScanner.close();
         }
        
    }
    private String AddToBlock(String MainUser, String Group, String UserFriend) throws IOException
    {   
        getdescBlock(NumberOfBlocks);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
        if(NumberOFUsers<MaxPerBlock)
        {
            int position = GUtilities.GetNumberofLines(DEFAULT_FOLDER_BLOCKS + "\\GRUPO" + NumberOfBlocks +".txt");
            String b = MainUser+"|"+Group+"|"+UserFriend+"|"+dateFormat.format(date) + "[America/Guatemala]|1";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEFAULT_FOLDER_BLOCKS + "\\GRUPO" + NumberOfBlocks +".txt", true))) {
                writer.newLine();
                writer.append(b);
                writer.close();
            }
            GUtilities.CleanEmptySpace(DEFAULT_FOLDER_BLOCKS + "\\GRUPO" + NumberOfBlocks +".txt");
            setDescBlock(MainUser, NumberOfBlocks, false); 
            return String.valueOf(NumberOfBlocks)+"."+String.valueOf(position+1);
        }
        NumberOfBlocks ++;
        NewBlock(NumberOfBlocks, MainUser+"|"+Group+"|"+UserFriend+"|"+dateFormat.format(date) + "[America/Guatemala]|1", MainUser);
        return String.valueOf(NumberOfBlocks)+"."+String.valueOf(1);
    } 
    private String readLine(Scanner reader) {
        if (reader.hasNextLine())
            return reader.nextLine();
        else
            return null;
    }
    private void ChangeOneLine(int lineNumber, String newLine) throws FileNotFoundException, IOException
    {
        Path path = Paths.get(DEFAULT_INDEXGROUPS);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, newLine);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
    private void ChangeOneLineBlocks(int lineNumber, String newLine, int blockNumber) throws FileNotFoundException, IOException
    {
        Path path = Paths.get(DEFAULT_FOLDER_BLOCKS + "\\GRUPO" + blockNumber +".txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, newLine);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
    
    //*************************************************************************************
    //Registro | Posicion |  Llave 1,2,3  |  Siguiente        | estatus -> INDEX
    //Usuario  | grupo    | Usuario_amigo | Fecha_transaccion | estatus -> BLOCK
    public void AddNewFriend(String MainUser, String Group, String UserFriend) throws    FileNotFoundException, IOException
    {   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        File block = new File(DEFAULT_FOLDER_BLOCKS+"\\GRUPO1.txt");
        if(block.exists())
        {
            getINDEXnumber();
            int indextochange =0;
            int newIndexNumber = INDEXnumber;
            int Next = INDEXnumber;
            int RegCount = INDEXcount + 1;
            File Index = new File(DEFAULT_INDEXGROUPS);
            Scanner Indexscanner = new Scanner(Index);
            //****************
            boolean flag = true;
            String currentLine = "";
            String currentKey = "";
            int AIndex;
            int Apointer =0;
            int NEWpointer =0;
            String [] DATAindex = new String[8];
            String [] prevDATAindex = new String[8];
            int j=0;
            while(flag)
            {
                for (int i = 0; i < Next; i++) {
                    currentLine = readLine(Indexscanner);
                }
                Indexscanner = new Scanner(Index);
                prevDATAindex = DATAindex;
                DATAindex = currentLine.split(Pattern.quote("|"));
                currentKey = DATAindex[2]+DATAindex[3]+DATAindex[4];
                Next = Integer.parseInt(DATAindex[5]);
                AIndex = Integer.parseInt(DATAindex[0]);
                if(currentKey.compareTo(MainUser+Group+UserFriend) > 0 &&j==0) //(because current > new) 
                {  // at the beginning
                    indextochange = Integer.parseInt(DATAindex[0]);
                    Apointer = Integer.parseInt(DATAindex[5]); //same position
                    NEWpointer = AIndex; 
                    newIndexNumber = RegCount;
                    flag = false;
                }
                else if(currentKey.compareTo(MainUser+Group+UserFriend) > 0) //(because current > new) 
                { //when is in the middle
                    Apointer = RegCount;
                    NEWpointer = AIndex;
                    indextochange = Integer.parseInt(prevDATAindex[0]);
                    flag = false;
                }
                else if(Next == 0) //checked
                {   // is the last one
                    indextochange = Integer.parseInt(DATAindex[0]);
                    Apointer = RegCount;
                    NEWpointer = 0;
                    newIndexNumber = INDEXnumber;
                    flag = false;
                }
                j++;
            }
            String c = prevDATAindex[0] +"|"+prevDATAindex[1]+"|"+prevDATAindex[2]+"|"+prevDATAindex[3]+"|"+prevDATAindex[4]+"|"+ String.valueOf(Apointer) +"|"+prevDATAindex[6];    
            String a = DATAindex[0] +"|"+DATAindex[1]+"|"+DATAindex[2]+"|"+DATAindex[3]+"|"+DATAindex[4]+"|"+ String.valueOf(Apointer) +"|"+DATAindex[6];
            if(indextochange==Integer.parseInt(DATAindex[0]))        
                ChangeOneLine(indextochange, a);
            else
                ChangeOneLine(indextochange, c);
            
            String Position = AddToBlock(MainUser, Group, UserFriend);
            String b =String.valueOf(RegCount)+"|"+ Position +"|"+MainUser+"|"+Group+"|"+UserFriend+"|"+NEWpointer+"|1";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEFAULT_INDEXGROUPS, true))) {
                        writer.append(b);
                        writer.close();
                    }
            Indexscanner.close();
            setDescINDEX(MainUser,newIndexNumber,false);
        }
        else
        {
            FileWriter writer = new FileWriter(DEFAULT_INDEXGROUPS);
            writer.write("1|1.1|"+MainUser+"|"+Group+"|"+UserFriend+"|0|1");
            writer.close();
            NewBlock(1, MainUser+"|"+Group+"|"+UserFriend+"|"+dateFormat.format(date) + "[America/Guatemala]|1", MainUser);
            setDescINDEX(MainUser,1,true);
        }
    }
    public void DeleteFriend(String MainUser, String Group, String UserFriend) throws IOException
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        getINDEXnumber();
        File Index = new File(DEFAULT_INDEXGROUPS);
        Scanner IndexScanner = new Scanner(Index);
        String Key = MainUser+Group+UserFriend;
        
        int Next = INDEXnumber;
        boolean flag = true;
        String currentLine = "";
        String [] DATAindex = new String[8];
        String [] prevDATAindex = new String[8];
        int AIndex = 0;
        int prevIndex =0;
        int prevNext =0;
        String[] Block=new String[8];
        int j =0;
        while(flag)
        {
            
            for (int i = 0; i < Next; i++) {
                currentLine = readLine(IndexScanner);
            }
            prevDATAindex = DATAindex;
            DATAindex = currentLine.split(Pattern.quote("|"));
            String currentKey = DATAindex[2]+DATAindex[3]+DATAindex[4];
            Next = Integer.parseInt(DATAindex[5]);
            AIndex = Integer.parseInt(DATAindex[0]);
            Block = DATAindex[1].split(Pattern.quote("."));
            if(Key.equals(currentKey))
            {       //  0          1          2   3   4          5                6
                    //Registro | Posicion |  Llave 1,2,3  |  Siguiente        | estatus -> INDEX
                if(j==0)//At the beginning
                {
                    int a =0;
                    boolean flag2 = true;
                    while(flag2)
                    {
                        IndexScanner = new Scanner(Index);
                        for (int i = 0; i < Next; i++) {
                            currentLine = readLine(IndexScanner);
                        }
                        prevDATAindex = currentLine.split(Pattern.quote("|")); //Next line
                        if(Integer.parseInt(prevDATAindex[6])!=0)
                        {
                            a = Integer.parseInt(prevDATAindex[0]);
                            flag2 =false;
                        }
                        else if(currentLine == null)
                            flag2 = false;
                    }
                    setDescINDEX(MainUser, a,false);
                }
                else//At the end or In the middle
                {
                    prevNext = Integer.parseInt(DATAindex[5]);
                    ChangeOneLine(Integer.parseInt(prevDATAindex[0]), prevDATAindex[0] +"|"+prevDATAindex[1]+"|"+prevDATAindex[2]+"|"+prevDATAindex[3]+"|"+prevDATAindex[4]+"|"+prevNext+"|"+prevDATAindex[6]);
                }
                
                ChangeOneLine(AIndex, DATAindex[0] +"|"+DATAindex[1]+"|"+DATAindex[2]+"|"+DATAindex[3]+"|"+DATAindex[4]+"|"+DATAindex[5] +"|0");
                ChangeOneLineBlocks(Integer.parseInt(Block[1]),DATAindex[2]+"|"+DATAindex[3]+"|"+DATAindex[4]+"|"+dateFormat.format(date) + "[America/Guatemala]|0", Integer.parseInt(Block[0]));
                flag =false;
            }
            IndexScanner = new Scanner(Index);
            j++;
            if(currentLine == null)
                flag = false;
        }
        IndexScanner.close();
    }
    //************************************************************************************************************
                //  0          1          2   3   4          5                6
                //Registro | Posicion |  Llave 1,2,3  |  Siguiente        | estatus -> INDEX
                //  0             1         2               3                   4
                //Usuario  | grupo    | Usuario_amigo | Fecha_transaccion | estatus -> BLOCK
    public void ReoIndex(String MainUser) throws IOException
    {
        File folder = new File(DEFAULT_FOLDER_BLOCKS);
        if(folder.isDirectory() && folder.list().length != 0)
        {
        getINDEXnumber();
        getdescBlock(1); //get max per block
        File source = new File(DEFAULT_INDEXGROUPS);
        File dest = new File(DEFAULT_TEMP);
        dest.createNewFile();
        FileUtils.copyFile(source, dest);
        source.delete();
        source.createNewFile();
        FileUtils.cleanDirectory(folder); 
        setDescINDEX(MainUser,1,false);
        //****
        Scanner Temp = new Scanner(dest);
        String currentLine ="";
        boolean flag = true;
        int Next = INDEXnumber;
        String [] DATAindex = new String[8];
        while(flag)
        {
            for(int i =0; i<Next;i++)
            {
                currentLine = readLine(Temp);
            }
            DATAindex = currentLine.split(Pattern.quote("|"));
            Next =Integer.parseInt(DATAindex[5]);
            if(Integer.parseInt(DATAindex[6])!=0)
            {
                AddNewFriend(DATAindex[2], DATAindex[3], DATAindex[4]);
            }
            if(Next == 0)
                flag = false;
            Temp = new Scanner(dest);
        }
        Temp.close();
        dest.delete();
        }
    }
}
   
    

