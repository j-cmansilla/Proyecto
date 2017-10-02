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
import proyecto_i.ManejadorDeUsuarios;
import proyecto_i.PerfilUsuario;
import proyecto_i.Usuario;
import proyecto_i.Utilities;


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
    //public static void main(String[] a) throws FileNotFoundException //PARA PROBARLO*********** 
            //Volver funcion en la ultima version ********************************************** 
    public boolean Main() throws FileNotFoundException, IOException
    {
        UserMenu UM = new UserMenu();
        ManejadorDeUsuarios MDU = new ManejadorDeUsuarios();
        Usuario MainUser = MDU.getUserData(User);
        Utilities Utilidades = new Utilities();
        String[] chAdmin = { "Modify Profile", "Deactivate Account", "Sign in New User", "Search User", "Deactivate a User", "Modify Max. Number to Re-organize", "Back-Up" };
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
                return true;
            }
            else if (input.equals(choices[1]))
            {
                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to deactivate the account?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    MainUser.setEstatus(0);
                    JOptionPane.showMessageDialog(null, "This will be your last session, after you log-out you won't be able to sign-in.");
                } 
                MDU.SetUserData(MainUser);
                Login regresar=new Login();
                regresar.show();
                return true;
                
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
            return  false;
    }
 }
