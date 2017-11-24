/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Harry
 */
public class ManejadorDeImagenes {
    
    private final String DEFAULT_IMAGENES_DIRECTORY = "imagen_usuario.txt";
    private final String DEFAULT_TEMP_IMAGENES_DIRECTORY = "temp_imagen_usuario.txt";
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    private final String DEFAULT_DES_IMAGE_DIRECTORY = "C:\\MEIA\\imagenes\\Desc_";
    private final String DEFAULT_IMAGE_DIRECTORY = "C:\\MEIA\\imagenes\\";
    File imgs = new File(DEFAULT_IMAGE_DIRECTORY+DEFAULT_IMAGENES_DIRECTORY);
    
    public void CrearArchivos(){
        try{
            File directory = new File(DEFAULT_IMAGE_DIRECTORY);
            if (!directory.exists()) 
            {
                directory.mkdirs();
            }
            File aImages = new File(DEFAULT_IMAGE_DIRECTORY+DEFAULT_IMAGENES_DIRECTORY);
            File aDescImages = new File(DEFAULT_DES_IMAGE_DIRECTORY+DEFAULT_IMAGENES_DIRECTORY);

            aImages.createNewFile();
            aDescImages.createNewFile();
            
        }catch(IOException e){
            
        }
    }
    
    public void SaveImage(String Path, String user) throws IOException
    {
        File entrada = new File(Path);
        File salida = new File(DEFAULT_IMAGE_DIRECTORY+entrada.getName());
        if (salida.exists()) {
            salida = new File(DEFAULT_IMAGE_DIRECTORY+ FilenameUtils.removeExtension(entrada.getName()) + "(1)." + FilenameUtils.getExtension(entrada.getName()));
        }
        Files.copy(entrada.toPath(), salida.toPath());
        String node = createNode(user, salida.toPath().toString());
        ArrayList BTree = ReadBinaryTree();
        ArrayList toWrite;
        if(BTree.isEmpty()){
            toWrite = addNode(BTree, null, node, 0);
        }else{
            toWrite = addNode(BTree, BTree.get(0).toString(), node, 0);
        }
        writeTree(toWrite);
    }
    
    public ArrayList ReadBinaryTree() throws FileNotFoundException{        
        Scanner scanner = new Scanner(DEFAULT_IMAGE_DIRECTORY+DEFAULT_IMAGENES_DIRECTORY);
        
        ArrayList BTree = new ArrayList();
        File archivo = new File(scanner.nextLine());
        scanner = new Scanner(archivo);
        while(scanner.hasNextLine()){
            BTree.add(scanner.nextLine());
        }  
        scanner.close();
        return BTree;
    }
    
    public String createNode(String user, String Path){
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );        
        String node = "-1|-1|" + user + "|" + Path + "|" + zdt + "|" + "1";
        return node;
    }
    
    public ArrayList addNode(ArrayList BTree, String actualNode, String node, int position){
        if (!BTree.isEmpty()) {
            String[] toCompare = actualNode.split("\\|");
            String[] nodeI = node.split("\\|");
        
            String key1 = toCompare[2]+toCompare[3];
            String key2 = nodeI[2]+nodeI[3];
        
            if(key1.compareTo(key2)>0){
                if(toCompare[0].equals("-1")){
                    toCompare[0]=Integer.toString(BTree.size());
                    String newNode = toCompare[0] + "|" + toCompare[1] + "|" + toCompare[2] + "|" + toCompare[3] + "|" + toCompare[4] + "|" + toCompare[5];
                    BTree.set(position, newNode);
                    BTree.add(node);
                    return BTree;
                }     
                return addNode(BTree, BTree.get(Integer.parseInt(toCompare[0])).toString(), node, Integer.parseInt(toCompare[0]));
            }
            else if(key1.compareTo(key2)<0){
                if(toCompare[1].equals("-1")){
                    toCompare[1]=Integer.toString(BTree.size());
                    String newNode = toCompare[0] + "|" + toCompare[1] + "|" + toCompare[2] + "|" + toCompare[3] + "|" + toCompare[4] + "|" + toCompare[5];
                    BTree.set(position, newNode);
                    BTree.add(node);
                    return BTree;
                }
                return addNode(BTree, BTree.get(Integer.parseInt(toCompare[1])).toString(), node, Integer.parseInt(toCompare[1]));
            }
        }else{
            BTree.add(node);
            return BTree;
        }      
        
        return null;

    }
    
    public void writeTree(ArrayList BTree) throws UnsupportedEncodingException, FileNotFoundException, IOException{
        Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(DEFAULT_IMAGE_DIRECTORY+DEFAULT_TEMP_IMAGENES_DIRECTORY), "utf-8"));
        
        File tempFile = new File(DEFAULT_IMAGE_DIRECTORY+DEFAULT_TEMP_IMAGENES_DIRECTORY);
        tempFile.createNewFile();
        
        for (int i = 0; i < BTree.size(); i++) {
            writer.write(BTree.get(i).toString() + System.getProperty("line.separator"));
        }
        writer.close();
        
        String newFilePath = imgs.getAbsolutePath();
        Path path = Paths.get(imgs.getAbsolutePath());
        Files.delete(path);
        File newFile = new File(newFilePath);
        FileUtils.moveFile(tempFile, newFile);
        
        ManejadorDeUsuarios objUsuarios = new ManejadorDeUsuarios();
        Writer writerD = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(DEFAULT_DES_IMAGE_DIRECTORY+DEFAULT_IMAGENES_DIRECTORY), "utf-8"));
        String[] pathStrings = BTree.get(BTree.size()-1).toString().split("\\|");
        writerD.write(objUsuarios.getUserLogin() + System.getProperty("line.separator") + pathStrings[3] + System.getProperty("line.separator") + Integer.toString(BTree.size()));
        writerD.close();
    }
    
    public ArrayList getPaths(String user) throws FileNotFoundException{
        ArrayList BTree = ReadBinaryTree();
        ArrayList paths = new ArrayList();
        for (int i = 0; i < BTree.size(); i++) {
            String line = BTree.get(i).toString();
            String[] contents = line.split("\\|");
            if (contents[2].equals(user)) {
                paths.add(contents[3]);
            }
        }
        return paths;
    }
}
