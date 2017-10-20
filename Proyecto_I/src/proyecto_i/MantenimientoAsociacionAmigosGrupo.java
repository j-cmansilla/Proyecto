/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sebas
 */
public class MantenimientoAsociacionAmigosGrupo {
    private final String DEFAULT_INDEXGROUPS = "C:\\MEIA\\IndiceGrupos.txt";
    private final String DEFAULT_DESC_INDEXGROUPS = "C:\\MEIA\\Desc_InficeGrupos.txt";
    private final String DEFAULT_FOLDER_BLOCKS ="C:\\MEIA\\Blocks";
    
    Utilities Utilidades = new Utilities();
    REO reorganize = new REO();
    
    public void CrearArchivos() throws IOException{
            File indexGroups = new File(DEFAULT_INDEXGROUPS);
            File descIndexGroups = new File(DEFAULT_DESC_INDEXGROUPS); 
            File folderBlocks = new File(DEFAULT_FOLDER_BLOCKS);
            indexGroups.createNewFile();
            descIndexGroups.createNewFile();
            folderBlocks.mkdirs();
    }
    
    private void NewBlock(int blockNumber, String Friend) throws IOException
    {
        File Block = new File(DEFAULT_FOLDER_BLOCKS + "\\GRUPO" + blockNumber +".txt");
        Block.createNewFile();
        try (FileWriter writer = new FileWriter(DEFAULT_FOLDER_BLOCKS + "\\GRUPO" + blockNumber +".txt")) {
            writer.write(Friend +String.format("%n"));
        }
    }
    
    private void setDescINDEX(String MainUser, int IndexNumber) throws FileNotFoundException
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
            lines.add(MainUser);
            for (int i = 0; i < 2; i++) {
                if (!IndexDescScanner.hasNextLine()) {
                    currentLine = null;
                }else{
                    currentLine =IndexDescScanner.nextLine();
                }
            }
            IndexDescScanner.close();
        }
        lines.add(currentLine);
        lines.add(dateFormat.format(date) + "[America/Guatemala]");
        int a = new File(DEFAULT_FOLDER_BLOCKS).listFiles().length;
        lines.add(String.valueOf(a));
        lines.add(String.valueOf(IndexNumber));
        Utilidades.LlenarArchivo(DEFAULT_DESC_INDEXGROUPS, lines);
    }
    
    private int INDEXnumber;
    private int NumberOfBlocks;
    private void getINDEXnumber() throws FileNotFoundException
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
    }
    //*************************************************************************************
    //Registro | Posicion |  Llave 1,2,3  |  Siguiente        | estatus -> INDEX
    //Usuario  | grupo    | Usuario_amigo | Fecha_transaccion | estatus -> BLOCK
    public void addNEWFriend(String MainUser, String Group, String UserFriend) throws FileNotFoundException, IOException
    {   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        File block = new File(DEFAULT_FOLDER_BLOCKS+"\\GRUPO1.txt");
        if(block.exists())
        {
            getINDEXnumber();
        }
        else
        {
            NewBlock(1, MainUser+"|"+Group+"|"+UserFriend+"|"+dateFormat.format(date) + "[America/Guatemala]|1");
            setDescINDEX(MainUser,1);
        }
    }
   
    
}
