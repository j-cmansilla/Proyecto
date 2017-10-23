/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Harry
 */
public class Amigos extends javax.swing.JFrame {

    /**
     * Creates new form Amigos
     */
    public Amigos() {
        initComponents();

        
    }
    // Options
    //Search    1
    //Friends   2
    //Admin     3
    private int options = 0;
    private boolean viewOrDelete = true;
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    private final String DEFAULT_BITACORA_DIRECTORY = "Bitacora.txt";
    private final String DEFAULT_USER_DIRECTORY = "Usuario.txt";
    static String user1, user2;
//    public void setUser(String userp) {this.user = userp;}
//    public String getUser() {return this.user;}
    
    public boolean findUser(String user) throws FileNotFoundException
    {
        ArrayList result = getSearchData(user);
        if(!result.isEmpty())
        {
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            for (int i = 0; i < result.size(); i++) {                
                String [] data = result.get(i).toString().split(Pattern.quote("|"));
                model.addRow(new Object[]{data[0],data[1],data[2]});                
            }
            return true;
        }               
        else
        {
            return false;
        }
    }
    
    public boolean fillTable(ArrayList friends) throws FileNotFoundException
    {
        ManejadorDeUsuarios objUsuarios=new ManejadorDeUsuarios();
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        String loggerU=objUsuarios.getUserLogin();
        boolean flagFriends = false;
        
        for (int i = 0; i < friends.size(); i++) {
            String [] user = friends.get(i).toString().split(Pattern.quote("|"));
            if(loggerU.equals(user[1])){
                Usuario result = objUsuarios.getUserData(user[0]);
                if(result!=null){
                    model.addRow(new Object[]{result.getUsuario(),result.getNombre(),result.getApellido()});
                    flagFriends=true;
                }                
            }
            else
            {
                Usuario result = objUsuarios.getUserData(user[1]);
                if(result!=null){
                    model.addRow(new Object[]{result.getUsuario(),result.getNombre(),result.getApellido()}); 
                    flagFriends=true;
                }                
            }
        }  
        return flagFriends;
    }
    
    public ArrayList getSearchData(String user) throws FileNotFoundException
    {
        ArrayList search = new ArrayList();
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
        String result;
        ManejadorDeUsuarios objManejadorDeUsuarios = new ManejadorDeUsuarios();
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(Pattern.quote("|"));
                if ((credenciales[0].equals(user) || credenciales[1].equals(user) || credenciales[2].equals(user)) && credenciales[10].equals("1") && !credenciales[0].equals(objManejadorDeUsuarios.getUserLogin())) {
                    result = credenciales[0] + "|" + credenciales[1] + "|" + credenciales[2];
                    search.add(result);
                }
            }
            scanner.close();
        }
        usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
                            
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_USER_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(Pattern.quote("|"));
                if ((credenciales[0].equals(user) || credenciales[1].equals(user) || credenciales[2].equals(user)) && credenciales[10].equals("1") && !credenciales[0].equals(objManejadorDeUsuarios.getUserLogin())) {
                    result = credenciales[0] + "|" + credenciales[1] + "|" + credenciales[2];
                    search.add(result);
                }
            }
            scanner.close();
        }
        return search;     
    }
    
    public boolean fillTableAdmin(ArrayList friends) throws FileNotFoundException
    {
        ManejadorDeUsuarios objUsuarios=new ManejadorDeUsuarios();
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        String loggerU=objUsuarios.getUserLogin();
        boolean flagFriends = false;
        
        for (int i = 0; i < friends.size(); i++) {
            String [] user = friends.get(i).toString().split(Pattern.quote("|"));
            String state = "";
            if(user[2].equals("1")){
                state = "Friends";
            }else{
                state = "Pending";
            }
            model.addRow(new Object[]{user[0],user[1],state});         
                        
        }  
        return flagFriends;
    }
   
    public void setColumnNames(){
        jTable1.getColumnModel().getColumn(0).setHeaderValue("User");
        jTable1.getColumnModel().getColumn(1).setHeaderValue("Friend");
        jTable1.getColumnModel().getColumn(2).setHeaderValue("State");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "Name", "Last Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        jToggleButton1.setText("Delete");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton1.setText("Create Friendship");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButton1)
                        .addComponent(jButton1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        ManejadorDeUsuarios objUsuarios=new ManejadorDeUsuarios();
        ManejadorDeAmigos objAmigos=new ManejadorDeAmigos();
        
        int index = jTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        String username=model.getValueAt(index, 0).toString();
        
        
        
        Usuario user=null;
        try {
            user = objUsuarios.getUserData(username);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        switch (options) {
            case 1:
                try {
                    if(objAmigos.areFriends(objUsuarios.getUserLogin(), username)){
                        JOptionPane.showMessageDialog(null, "You're already friends with " + username);
                        this.setVisible(false);
                        this.dispose();
                    }
                    else if (objAmigos.doesRequestExists(objUsuarios.getUserLogin(), username))
                    {
                        JOptionPane.showMessageDialog(null, "The request has already been sent.");
                        this.setVisible(false);
                        this.dispose();
                    }
                    else
                    {
                        int selection = JOptionPane.showConfirmDialog(null, "Do you want to send a friend request to " + username, "Confirmation", JOptionPane.YES_NO_OPTION);
                        if(selection==JOptionPane.YES_OPTION){
                            Usuario actual=objUsuarios.getUserData(objUsuarios.getUserLogin());
                            Usuario result=objUsuarios.getUserData(username);
                            try {
                                objAmigos.updateBitacora(actual, result);
                            } catch (IOException ex) {
                                Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ////////////////////////VERIFICAR/////////////////////////////
                            JOptionPane.showMessageDialog(null, "The request to " + username + " has been sent.");
                            this.setVisible(false);
                            this.dispose();                            
                        }
                    }
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            case 2:
                if (jToggleButton1.isSelected()) {
                    try {
                        String log=objUsuarios.getUserLogin();
                        ArrayList requests=objAmigos.getFriendsList(log);
                        String key1=log + "|" + user.getUsuario();
                        String key2=user.getUsuario() + "|" + log;
                        ArrayList cleaned = objAmigos.cleanRequests(requests, key1);
                        if (cleaned.isEmpty()) {
                            cleaned = objAmigos.cleanRequests(requests, key2);                        
                        }
                        String [] toUpdate = cleaned.get(0).toString().split(Pattern.quote("|"));
                        String updateRequest = toUpdate[0]+"|"+toUpdate[1] + "|0|" + toUpdate[3] + "|"+toUpdate[4] + "|0*" + System.getProperty("line.separator");
                        ArrayList finalL = new ArrayList();
                        finalL.add(updateRequest);
                        try {
                            if(objAmigos.updateBM(finalL)){
                                JOptionPane.showMessageDialog(null, "You're no longer friends with " + user.getUsuario());    
                                model.setRowCount(0);
                                
                                ArrayList amigos = objAmigos.getFriendsList(objUsuarios.getUserLogin());
                                if(!amigos.isEmpty()){
                                    fillTable(amigos);
                                }
                                else{
                                    this.show(false);
                                    this.dispose();
                                }            
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Error.");
                            }
                            
                        } catch (IOException ex) {
                            Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else{
                    PerfilUsuario PU;
                    try {
                        PU = new PerfilUsuario();
                        PU.setFlagOptions(false);
                        PU.setUsuario(user);
                        PU.SetDATA();
                        PU.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }   break;
            case 3:
                if (jToggleButton1.isSelected()) {
                    if(model.getValueAt(index, 2).toString().equals("Friends")){
                        JOptionPane.showMessageDialog(null, "You can't delete friendships, only pending requests.");
                    }else{
                        String usernameF=model.getValueAt(index, 1).toString();
                        String key = username + "|" + usernameF;
                        try {
                            ArrayList requests = objAmigos.getAllRequestsAdmin();
                            String [] toDelete = null;
                            String deleteRequest = "";
                            
                            for (int i = 0; i < requests.size(); i++) {
                                if(requests.get(i).toString().contains(key)){
                                    toDelete = requests.get(i).toString().split(Pattern.quote("|"));
                                    deleteRequest = toDelete[0]+"|"+toDelete[1] + "|0|" + toDelete[3] + "|"+toDelete[4] + "|0*" + System.getProperty("line.separator");
                                }
                            }
                            ArrayList finalL = new ArrayList();
                            finalL.add(deleteRequest);
                            try {
                                if(objAmigos.updateBM(finalL)){
                                    JOptionPane.showMessageDialog(null, "Pending Request Deleted.");
                                    model.setRowCount(0);
                                    ArrayList requestsU = objAmigos.getAllRequestsAdmin();
                                    if(!requestsU.isEmpty()){
                                        fillTableAdmin(requestsU);
                                    }
                                    else{
                                        this.show(false);
                                        this.dispose();
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Error.");
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }   break;
            case 4:        
                try {
                    jLabel1.setText(username + " - ");
                    ArrayList allUsers = objUsuarios.usersList();
                    ArrayList restUsers = null;
                    for (int i = 0; i < allUsers.size(); i++) {
                        if(!allUsers.get(i).toString().contains(username) && !objAmigos.areFriends(allUsers.get(i).toString(), username) && !objAmigos.doesRequestExists(allUsers.get(i).toString(), username)){
                            restUsers.add(allUsers.get(i));
                        }                  
                    }
                    user1 = username;
                    options = 5;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                }       
                
                break;
            case 5:
                user2 = username;
                int selection = JOptionPane.showConfirmDialog(null, "Do you want to make this two users friends? \n" + user1 + " - " + user2, "Confirmation", JOptionPane.YES_NO_OPTION);
                        if(selection==JOptionPane.YES_OPTION){
                            try {
                                Usuario actual = objUsuarios.getUserData(objUsuarios.getUserLogin());
                                Usuario result=objUsuarios.getUserData(username);
                                try {
                                    objAmigos.updateBitacora(actual, result);
                                } catch (IOException ex) {
                                    Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
                            }                           

                            ////////////////////////VERIFICAR/////////////////////////////
                            JOptionPane.showMessageDialog(null, "The request to " + username + " has been sent.");
                            this.setVisible(false);
                            this.dispose();                            
                        }
                break;

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ManejadorDeUsuarios objManejadorDeUsuarios = new ManejadorDeUsuarios();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        try {
            ArrayList users = objManejadorDeUsuarios.usersList();
            model.setColumnCount(1);
            model.setRowCount(0);
            jTable1.getColumnModel().getColumn(0).setHeaderValue("User");
            
            for (int i = 0; i < users.size(); i++) {
                model.addRow(new Object[]{users.get(i).toString()});                
            }
            
            hORs(4);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void hORs(int flag){
        switch (flag) {
            case 1:
                jToggleButton1.show(false);
                jButton1.show(false);
                jLabel1.show(false);
                options = 1;
                break;
            case 2:
                jToggleButton1.show(true);
                jButton1.show(false);
                jLabel1.show(false);
                options = 2;
                break;
            case 3:
                jToggleButton1.show(true);
                jButton1.show(true);
                jLabel1.show(false);
                options = 3;
                break;
            case 4:
                jToggleButton1.show(false);
                jButton1.show(false);
                jLabel1.show(true);
                options = 4;
                break;
        }     

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Amigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Amigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Amigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Amigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Amigos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
