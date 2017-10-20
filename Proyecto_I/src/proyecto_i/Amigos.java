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
import javax.swing.table.TableModel;

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
    private boolean searchOrList = true;
    private boolean viewOrDelete = true;
    private final String DEFAULT_DIRECTORY = "C:\\MEIA\\";
    private final String DEFAULT_BITACORA_DIRECTORY = "Bitacora.txt";
    private final String DEFAULT_USER_DIRECTORY = "Usuario.txt";
    
//    public void setUser(String userp) {this.user = userp;}
//    public String getUser() {return this.user;}
    
    public boolean findUser(String user) throws FileNotFoundException
    {
        Usuario result = getSearchData(user);
        if(result!=null)
        {
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{result.getUsuario(),result.getNombre(),result.getApellido()});
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
    
    public Usuario getSearchData(String user) throws FileNotFoundException
    {
        File usuarios = new File(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
        Usuario result;
                    
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(DEFAULT_DIRECTORY + DEFAULT_BITACORA_DIRECTORY);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] credenciales = line.split(Pattern.quote("|"));
                if ((credenciales[0].equals(user) || credenciales[1].equals(user) || credenciales[2].equals(user)) && credenciales[10].equals("1")) {
                    result = new Usuario(credenciales[0], credenciales[1], credenciales[2], credenciales[3], Integer.parseInt(credenciales[4]), credenciales[5], credenciales[6], credenciales[7], credenciales[8], credenciales[9], Integer.parseInt(credenciales[10]));
                    scanner.close();
                    return result;
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
                if ((credenciales[0].equals(user) || credenciales[1].equals(user) || credenciales[2].equals(user)) && credenciales[10].equals("1")) {
                    result = new Usuario(credenciales[0], credenciales[1], credenciales[2], credenciales[3], Integer.parseInt(credenciales[4]), credenciales[5], credenciales[6], credenciales[7], credenciales[8], credenciales[9], Integer.parseInt(credenciales[10]));
                    scanner.close();
                    return result;
                }
            }
            scanner.close();
        }
        return null;     
    }
   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();

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

        jToggleButton1.setText("Eliminar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        ManejadorDeUsuarios objUsuarios=new ManejadorDeUsuarios();
        ManejadorDeAmigos objAmigos=new ManejadorDeAmigos();
        
        int index = jTable1.getSelectedRow();
        TableModel model=jTable1.getModel();
        String username=model.getValueAt(index, 0).toString();
        
        Usuario user=null;
        try {
            user = objUsuarios.getUserData(username);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(searchOrList){            
            try {
                int selection = JOptionPane.showConfirmDialog(null, "Do you want to send a friend request to " + username, "Confirmation", JOptionPane.YES_NO_OPTION);
                if(selection==JOptionPane.YES_OPTION){
                    Usuario actual=objUsuarios.getUserData(objUsuarios.getUserLogin());
                    Usuario result=getSearchData(username);
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
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Amigos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            if (jToggleButton1.isSelected()) {
                try {
                    String log=objUsuarios.getUserLogin();
                    ArrayList requests=objAmigos.getUserRequest(log);
                    String key1=log + "|" + user.getUsuario();
                    String key2=log + "|" + user.getUsuario();
                    ArrayList cleaned = objAmigos.cleanRequests(requests, key1);
                    if (cleaned == null) {
                        cleaned = objAmigos.cleanRequests(requests, key2);
                    }
                    String [] toUpdate = cleaned.get(0).toString().split(Pattern.quote("|"));
                    
                     
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
            }                 
        }        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    public void hORs(boolean flag){
        jToggleButton1.show(flag);
        searchOrList = !flag;
        viewOrDelete = !flag;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
