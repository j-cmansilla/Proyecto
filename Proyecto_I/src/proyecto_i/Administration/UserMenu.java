/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i.Administration;

import java.io.FileNotFoundException;
import javafx.scene.control.cell.CheckBoxListCell;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proyecto_i.CrearUsuario;
import proyecto_i.Login;
import proyecto_i.ManejadorDeUsuarios;
import proyecto_i.PerfilUsuario;
import proyecto_i.Usuario;


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
            //Volver metodo en la ultima version ********************************************** 
    public void Main() throws FileNotFoundException
    {
        UserMenu UM = new UserMenu();
        ManejadorDeUsuarios MDU = new ManejadorDeUsuarios();
        Usuario MainUser = MDU.getUserData(User);
        
        String[] chAdmin = { "Modificar Perfil", "Desactivar cuenta", "Ingresar nuevo usuario", "Buscar Usuario", "Desactivar Un usuario" };
        String[] choices = { "Modificar Perfil", "Desactivar cuenta", };
        if(MainUser.Rol()==1)
        {
            choices = chAdmin;
        }
            String input = (String) JOptionPane.showInputDialog(null, "Escoja una opcion",
                    "Escoja una opcion :)", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
            if(input == choices[0]){
                ChangeProfile CP = new ChangeProfile();
                CP.show();
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
                crearUsuario.show();
            }
            else if(input.equals(choices[3]))
            {
               JFrame frame = new JFrame(); 
               Object result = JOptionPane.showInputDialog(frame, "Ingrese el usuario que desea buscar:");
               String usuario = (String) result;
               Usuario user = MDU.getUserData(usuario);
               
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
               user.setEstatus(0);
               MDU.SetUserData(user);
            }
            
        
        
    }
 }
