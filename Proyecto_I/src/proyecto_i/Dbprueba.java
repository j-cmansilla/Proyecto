/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.PrintWriter;
import java.sql.*;

/**
 *
 * @author Derek
 */
public class Dbprueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // ESTO VA EN EL MAIN
        Singleton.getInstancia().conexion();
    }
}


