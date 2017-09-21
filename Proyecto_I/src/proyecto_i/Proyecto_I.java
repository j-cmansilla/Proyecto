/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.File;

/**
 *
 * @author Jose Mansilla
 */
public class Proyecto_I {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File directorio = new File("c:\\MEIA");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        Login login = new Login();
        login.show();
    }
    
}
