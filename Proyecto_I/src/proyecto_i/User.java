/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Jose Mansilla
 */
public class User {

    
    public User(){
        
    }
    
    public boolean ValidateUserAndPassword(String user, String pass) throws FileNotFoundException, IOException{
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        manejador.UserExists(user,pass);
        return false;
    }
    
    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    private void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    private void setUserName(String userName) {
        this.userName = userName;
    }
    
    private String pass;
    private String userName;
    private boolean esAdmin;
    
    
    //Constructor
    public User(String pass, String userName,boolean esAdmin){
        setPass(pass);
        setUserName(userName);
        setEsAdmin(esAdmin);
    }

    private void setEsAdmin(boolean esAdmin){
        this.esAdmin = esAdmin;
    }
    
    /**
     * @return the esAdmin
     */
    public boolean isEsAdmin() {
        return esAdmin;
    }
}
