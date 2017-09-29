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
import proyecto_i.ManejadorDeUsuarios;
import proyecto_i.Usuario;


/**
 *
 * @author sebas
 */
public class UserMenu {
    public boolean Admin;
    public  boolean Status = true;
    public void setAdmin(int ad) {
        this.Admin = ad != 0;
    }
   /* public void setStatus(int ad) {
        this.Status = ad != 0;
    }*/
    
    public static void main(String[] a) throws FileNotFoundException //PARA PROBARLO*********** 
            //Volver metodo en la ultima version ********************************************** 
    {
        UserMenu UM = new UserMenu();
        ManejadorDeUsuarios MDU = new ManejadorDeUsuarios();
        String[] chAdmin = { "Modificar Perfil", "Desactivar cuenta", "Ingresar nuevo usuario", "Buscar Usuario", "Desactivar Un usuario" };
        String[] choices = { "Modificar Perfil", "Desactivar cuenta", };
        if(UM.Admin)
        {
            choices = chAdmin;
        }
            String input = (String) JOptionPane.showInputDialog(null, "Escoja una opcion",
                    "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
            if(input == choices[0]){
                ChangeProfile CP = new ChangeProfile();
                CP.show();
            }
            else if (input.equals(choices[1]))
            {
                //UM.setStatus(0);
                UM.Status = false;
            }
            else if (input.equals(choices[2]))
            {
                CrearUsuario crearUsuario = new CrearUsuario();
                crearUsuario.show();
            }
            else if(input.equals(choices[3]))
            {
               JFrame frame = new JFrame(); 
               Object result = JOptionPane.showInputDialog(frame, "Enter printer name:");
               String usuario = (String) result;
               Usuario user = MDU.getUserData(usuario);
               
               //Mostrar perfil usuario ***************************************************************
               
            }
            else if(input.equals(choices[4]))
            {
               JFrame frame = new JFrame(); 
               Object result = JOptionPane.showInputDialog(frame, "Enter printer name:");
               String usuario = (String) result;
               Usuario user = MDU.getUserData(usuario);
               user.setEstatus(0);
               //Reemplazar usuario en el apilo o master...  ***************************************************
            }
            
        
        
    }
 }
