/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i.Administration;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.control.cell.CheckBoxListCell;
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
    
    //public static void main(String[] a) throws FileNotFoundException //PARA PROBARLO*********** 
            //Volver funcion en la ultima version ********************************************** 
    public boolean Main() throws FileNotFoundException, IOException
    {
        UserMenu UM = new UserMenu();
        ManejadorDeUsuarios MDU = new ManejadorDeUsuarios();
        Usuario MainUser = MDU.getUserData(User);
        Utilities Utilidades = new Utilities();
        String[] chAdmin = { "Modificar Perfil", "Desactivar cuenta", "Ingresar nuevo usuario", "Buscar Usuario", "Desactivar Un usuario", "Modificar el numero maximo para Reorganizar", "Realizar Back-Up" };
        String[] choices = { "Modificar Perfil", "Desactivar cuenta", };
        
        if(MainUser.Rol()==1)
        {
            choices = chAdmin;
        }
            String input = (String) JOptionPane.showInputDialog(null, "Escoja una opcion",
                    "Escoja una opcion :)", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
            if(input == choices[0]){
                ChangeProfile CP2 = new ChangeProfile();
                CP2.show();
                return true;
            }
            else if (input.equals(choices[1]))
            {
                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Seguro desea desactivar la cuenta?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    MainUser.setEstatus(0);
                } 
                MDU.SetUserData(MainUser);
                Login regresar=new Login();
                regresar.show();
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
               Object result = JOptionPane.showInputDialog(frame, "Ingrese el usuario que desea buscar:");
               String usuario = (String) result;
               Usuario user = MDU.getUserData(usuario);
               if(user==null)
               {
                   JOptionPane.showMessageDialog(null,"El usuario no existe");
                   return false;
               }
               PerfilUsuario PU = new PerfilUsuario();
               PU.setFlagOptions(false);
               PU.setUsuario(user);
               PU.show();
            }
            else if(input.equals(choices[4]))
            {
               JFrame frame = new JFrame(); 
               Object result = JOptionPane.showInputDialog(frame, "Ingrese el usuario que desea desactivar:");
               String usuario = (String) result;
               Usuario user = MDU.getUserData(usuario);
               if(user==null)
               {
                   JOptionPane.showMessageDialog(null,"El usuario no existe");
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
                    Object result = JOptionPane.showInputDialog(frame, "Ingrese el numero maximo para Reorganizar:");
                    try {
                        NewMax = Integer.parseInt(result.toString());
                        f = false;
                        if(NewMax < 0)
                        {
                            JOptionPane.showMessageDialog(null,"Inserte un numero positivo!");
                            f =true;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Inserte un numero!");
                    }
                    
                }
                Utilidades.ChangeMaxReorg(NewMax,User);  
            }else if(input.equals(choices[6]))
            {
               JFrame frame = new JFrame(); 
               Object result = JOptionPane.showInputDialog(frame, "Ingrese el path donde desea guardar ");
              
               //VALIDAR ESTO*********************************************************************************
               Utilidades.createBackUp((String) result, User);
            }
            return  false;
    }
 }
