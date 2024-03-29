/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
public class Middleware {
    ManejadorDeUsuarios MDU = new ManejadorDeUsuarios();
    //Message message = new Message();
    public int GroupNumber =0;
    public String messagestr = "";
    public String Receptor="";
    
    public boolean checkUserExist(String usuario) throws FileNotFoundException
    {
        Usuario user = MDU.getUserData(usuario);
        return (user!=null) ? true : false;
    }
    
    public void ReceiveMessage(String message,String emitter ) throws IOException
    {
        saveMessage(CreateMessage(message,Integer.parseInt(emitter)));
    }
    
    public boolean SendMessage(int group,String Message)
    {
        //Singleton
        return true;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   private final String DEFAULT_MESSAGEGROUPS ="C:\\MEIA\\MessageGroups.txt";
    public void CrearArchivos() throws IOException{
            File mess = new File(DEFAULT_MESSAGEGROUPS);
            mess.createNewFile();
    }
   public Message CreateMessage(String message, int emitter)
   {
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date();
       Message m = new Message(emitter, message, message, dateFormat.format(date), message);
       return m;
   }
   
   public void saveMessage(Message newmessage) throws IOException
    {    // #grupo, usuario_emisor, usuario_receptor, fecha, mensaje.
        CrearArchivos();
        File MessageGroupsfile = new File(DEFAULT_MESSAGEGROUPS);
        MessageGroupsfile.createNewFile();
        String b = newmessage.getGroupNumber()+"|"+newmessage.getEmitter()+"|"+newmessage.getReceptor()+"|"+newmessage.getDate()+ "|" + newmessage.getMessage();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEFAULT_MESSAGEGROUPS, true))) {
                writer.newLine();
                writer.append(b);
                writer.close();
            }
    }
    public List<Message> getUserMessageGroups(String user) throws FileNotFoundException
    {   //    0      1                   2             3      4
        // #grupo, usuario_emisor, usuario_receptor, fecha, mensaje.
        List<Message> resultMessage = new ArrayList<Message>();
        File Messagefile = new File(DEFAULT_MESSAGEGROUPS);
        String currentLine ="";
        String [] DATAMessage = new String[8];
         try (Scanner IndexDescScanner = new Scanner(Messagefile)) {
           while(IndexDescScanner.hasNextLine())
           {
               currentLine =IndexDescScanner.nextLine();
               DATAMessage = currentLine.split(Pattern.quote("|"));
                if(DATAMessage[1].equals(user)||DATAMessage[2].equals(user))
                {
                    Message m = new Message(Integer.parseInt(DATAMessage[0]), DATAMessage[1], DATAMessage[2], DATAMessage[3], DATAMessage[4]);
                    resultMessage.add(m);
                }
            }
            IndexDescScanner.close();
        }  
        return  resultMessage;
    }
    
    public List<String> GetMessage(List<Message> mss)
    {
        List<String> resultMessage = new ArrayList<String>();
        for (int i = 0; i < mss.size(); i++) {
            resultMessage.add("Group"+ mss.get(i).getGroupNumber() + " From: "+mss.get(i).getEmitter() 
                    + " " + mss.get(i).getMessage()+" Date: "+mss.get(i).getDate());
        }
        return resultMessage;
    }
}
