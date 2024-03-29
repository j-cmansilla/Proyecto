/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i.Administration;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proyecto_i.CrearUsuario;
import proyecto_i.Login;
import proyecto_i.ManejadorDeAmigos;
import proyecto_i.ManejadorDeUsuarios;
import proyecto_i.MantenimientoAsociacionAmigosGrupo;
import proyecto_i.PerfilUsuario;
import proyecto_i.REO;
import proyecto_i.Usuario;
import proyecto_i.Utilities;
import java.util.ArrayList;
import java.util.regex.Pattern;
import proyecto_i.GroupsUtilities;


/**
 *
 * @author sebas
 */
public class UserMenu {
    private String User = "";
    public void setUsuario(String user) {
        this.User = user;
    }
    public boolean FLAGDEactive = true;
    public boolean  flagHideProfile = false;
    //public static void main(String[] a) throws FileNotFoundException //PARA PROBARLO*********** 
            //Volver funcion en la ultima version ********************************************** 
    public boolean Main() throws FileNotFoundException, IOException
    {
        UserMenu UM = new UserMenu();
        ManejadorDeUsuarios MDU = new ManejadorDeUsuarios();
        Usuario MainUser = MDU.getUserData(User);
        Utilities Utilidades = new Utilities();
        String[] chAdmin = { "Modify Profile", "Deactivate Account", "Sign in New User", "Search User", "Deactivate a User", "Modify Max. Number to Re-organize", "Back-Up" ,"Reorganize"};
        String[] choices = { "Modify Profile", "Deactivate Account", };
        
        if(MainUser.Rol()==1)
        {
            choices = chAdmin;
        }
            String input = (String) JOptionPane.showInputDialog(null, "Choose an option:",
                    "Choose an Option :)", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
            
            if(input.equals(choices[0])){
                ChangeProfile CP2 = new ChangeProfile();
                CP2.show();
                flagHideProfile = true;
                return true;
            }
            else if (input.equals(choices[1]))
            {   if(MainUser.Rol()!=1)
                {
                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to deactivate the account?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    MainUser.setEstatus(0);
                    ManejadorDeUsuarios MDUU = new ManejadorDeUsuarios();
                    MDUU.ActualizarDEs(MainUser);
                    JOptionPane.showMessageDialog(null, "This will be your last session, after you log-out you won't be able to sign-in.");
                    flagHideProfile = true;
                    GroupsUtilities GU = new GroupsUtilities();
                    GU.DeleteUser(MainUser.getUsuario());
                    GU.deleteMainUser(MainUser.getUsuario());
                } 
   
                MDU.SetUserData(MainUser);
                Login regresar=new Login();
                regresar.show();
                ManejadorDeAmigos objAmigos = new ManejadorDeAmigos(); 
                ArrayList requestsToDelete = objAmigos.getUserRequestToDelete(MainUser.getUsuario());
                ArrayList finalDel = new ArrayList();
                    for (int i = 0; i < requestsToDelete.size(); i++) {
                        String [] request = requestsToDelete.get(i).toString().split(Pattern.quote("|"));
                        String updateRequest = request[0]+"|"+request[1] + "|0|" + request[3] + "|"+request[4] + "|0*" + System.getProperty("line.separator");
                        finalDel.add(updateRequest);
                    }
                objAmigos.updateBM(finalDel);
                return true;
                 }
                 JOptionPane.showMessageDialog(null, "You can't deactivate your account.");
                
            }
            else if (input.equals(choices[2]))
            {
                CrearUsuario crearUsuario = new CrearUsuario();
                crearUsuario.SETFLAG(false);
                crearUsuario.show();
            }
            else if(input.equals(choices[3]))
            {
               JFrame frame = new JFrame(); 
               Object result = JOptionPane.showInputDialog(frame, "Enter the username desired:");
               String usuario = (String) result;
               Usuario user = MDU.getUserData(usuario);
               if(user==null)
               {
                   JOptionPane.showMessageDialog(null,"The user doesn't exist.");
                   return false;
               }
               PerfilUsuario PU = new PerfilUsuario();
               PU.setFlagOptions(false);
               PU.setUsuario(user);
               PU.SetDATA();
               PU.show();
            }
            else if(input.equals(choices[4]))
            {
               JFrame frame = new JFrame(); 
               Object result = JOptionPane.showInputDialog(frame, "Enter the user you want to deactivate:");
               String usuario = (String) result;
               Usuario user = MDU.getUserData(usuario);
               if(user==null)
               {
                   JOptionPane.showMessageDialog(null,"The user doesn't exist.");
                   return false;
               }
               user.setEstatus(0);
               MDU.SetUserData(user);
            }
            else if (input.equals(choices[5]))
            {
                int NewMax = 5;
                boolean f = true;
                while(f)
                {
                    JFrame frame = new JFrame(); 
                    Object result = JOptionPane.showInputDialog(frame, "Enter the Max. Number to re-organize:");
                    try {
                        if (result != null) {
                            NewMax = Integer.parseInt(result.toString());
                            f = false;
                            if(NewMax < 0)
                            {
                                JOptionPane.showMessageDialog(null,"Insert a positive number!");
                                f =true;
                            } 
                        }else{
                            f=false;
                        }                        
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Insert a number!");
                    }
                    
                }
                Utilidades.ChangeMaxReorg(NewMax,User);  
            }else if(input.equals(choices[6]))
            {
               JFrame frame = new JFrame(); 
               Object result = JOptionPane.showInputDialog(frame, "Enter the path where you want to save.");

               Utilidades.createBackUp((String) result, User);
            }
            else if(input.equals(choices[7]))
            {
                REO reorganize = new REO();
                reorganize.Reorganize(MainUser.getUsuario());
                MantenimientoAsociacionAmigosGrupo MAAG = new MantenimientoAsociacionAmigosGrupo();
                MAAG.ReoIndex(MainUser.getUsuario());
                ManejadorDeAmigos objManejadorDeAmigos = new ManejadorDeAmigos();
                objManejadorDeAmigos.update();
                reorganize.ReoGhost();
       
            }
            
            return  false;
    }
 }
