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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author sebas
 */
public class Middleware {
    ManejadorDeUsuarios MDU = new ManejadorDeUsuarios();
    LocalMessageManejador LMM = new LocalMessageManejador();
    Singleton S = new Singleton();
    GroupsUtilities GUtilities = new GroupsUtilities();

    public boolean checkUserExist(String usuario) throws FileNotFoundException
    {
        Usuario user = MDU.getUserData(usuario);
        return (user!=null) ? true : false;
    }
    
    public void ReceiveMessage(String message,String receptor, String emitter) throws IOException, Exception
    {//(String usuario, String usuarioAmigo, String fecha, String mensaje, int tipoDeMensaje, int estatus)
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        LocalMessage m = new LocalMessage(emitter, receptor,zdt.toString(), message, 1, 1);
        LMM.llenarBitacora(emitter, zdt, m);
        saveMessage(m);
    }
    
    public boolean SendMessage(int group,String Message, String Receptor, String Emitter) throws SQLException, Exception
    {//int grupoEmisor,int grupoReceptor, String emisor, String receptor, String mensaje
        S.Insert(7, group, Emitter, Receptor, Message);
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        LocalMessage m = new LocalMessage(Emitter, Receptor,zdt.toString(), Message, 1, 1);
        LMM.llenarBitacora(Emitter, zdt, m);
        saveMessage(m);
        return true;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   private final String DEFAULT_MESSAGEGROUPS ="C:\\MEIA\\MessageGroups.txt";
    public void CrearArchivos() throws IOException{
            File mess = new File(DEFAULT_MESSAGEGROUPS);
            mess.createNewFile();
    }
   
   public void saveMessage(LocalMessage newmessage) throws IOException
    {    // #grupo, usuario_emisor, usuario_receptor, fecha, mensaje.
        //String usuario, String usuarioAmigo, String fecha, String mensaje, int tipoDeMensaje, int estatus
        CrearArchivos();
        File MessageGroupsfile = new File(DEFAULT_MESSAGEGROUPS);
        MessageGroupsfile.createNewFile();
        String b = newmessage.getUsuario()+"|"+newmessage.getUsuarioAmigo()+"|"+newmessage.getFecha()+"|"+newmessage.getMensaje()+ "|1|1";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEFAULT_MESSAGEGROUPS, true))) {
                writer.newLine();
                writer.append(b);
                writer.close();
            }
        GUtilities.CleanEmptySpace(DEFAULT_MESSAGEGROUPS);
    }
    public List<LocalMessage> getUserMessageGroups(String user, String user2) throws FileNotFoundException
    {   //              0             1                   2             3          4              5             
        //(String usuario, String usuarioAmigo, String fecha, String mensaje, int tipoDeMensaje, int estatus)
        List<LocalMessage> resultMessage = new ArrayList<LocalMessage>();
        File Messagefile = new File(DEFAULT_MESSAGEGROUPS);
        String currentLine ="";
        String [] DATAMessage = new String[8];
         try (Scanner IndexDescScanner = new Scanner(Messagefile)) {
           while(IndexDescScanner.hasNextLine())
           {
               currentLine =IndexDescScanner.nextLine();
               DATAMessage = currentLine.split(Pattern.quote("|"));
                if(DATAMessage[0].equals(user)&&DATAMessage[1].equals(user2))
                {             
                    LocalMessage m  = new LocalMessage(DATAMessage[0], DATAMessage[1], DATAMessage[2], DATAMessage[3], Integer.parseInt(DATAMessage[4]), Integer.parseInt(DATAMessage[5]));
                    resultMessage.add(m);
                }
            }
            IndexDescScanner.close();
        }  
        return  resultMessage;
    }
    
    public List<String> GetMessage(List<LocalMessage> mss)
    {
        List<String> resultMessage = new ArrayList<String>();
        for (int i = 0; i < mss.size(); i++) {
            resultMessage.add("From: "+mss.get(i).getUsuario()+ "  To: " + mss.get(i).getUsuarioAmigo()+" Message: "+mss.get(i).getMensaje());
        }
        return resultMessage;
    }
    
    public List<String> GetFriends (String user) throws FileNotFoundException
    {
         //(String usuario, String usuarioAmigo, String fecha, String mensaje, int tipoDeMensaje, int estatus)
        List<String> resultMessage = new ArrayList<String>();
        File Messagefile = new File(DEFAULT_MESSAGEGROUPS);
        String currentLine ="";
        String [] DATAMessage = new String[8];
         try (Scanner IndexDescScanner = new Scanner(Messagefile)) {
           while(IndexDescScanner.hasNextLine())
           {
               currentLine =IndexDescScanner.nextLine();
               DATAMessage = currentLine.split(Pattern.quote("|"));
                if(DATAMessage[0].equals(user))
                {             
                    resultMessage.add(DATAMessage[1]);
                }
                if(DATAMessage[1].equals(user))
                {             
                    resultMessage.add(DATAMessage[0]);
                }
            }
            IndexDescScanner.close();
        }  
        // add elements to al, including duplicates
        Set<String> hs = new HashSet<>();
        hs.addAll(resultMessage);
        resultMessage.clear();
        resultMessage.addAll(hs);
        return  resultMessage;
    }
}
