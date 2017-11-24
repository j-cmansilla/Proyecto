/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author sebas
 */
public class ViewMesaggeGroups extends javax.swing.JFrame {

    /**
     * Creates new form ViewMesaggeGroups
     */
    public ViewMesaggeGroups() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btnSendMessagetoGroups = new javax.swing.JButton();
        btnexit2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaMensajes = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jFriendList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("Message from Groups");

        btnSendMessagetoGroups.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSendMessagetoGroups.setText("Send Message to Group");
        btnSendMessagetoGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMessagetoGroupsActionPerformed(evt);
            }
        });

        btnexit2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnexit2.setText("Exit");
        btnexit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexit2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Friends:");

        listaMensajes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaMensajesValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listaMensajes);

        jFriendList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFriendListMouseClicked(evt);
            }
        });
        jFriendList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jFriendListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jFriendList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSendMessagetoGroups, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnexit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(jLabel3)))
                        .addGap(0, 149, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 195, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(btnSendMessagetoGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                .addComponent(btnexit2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(77, 77, 77)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                        .addComponent(jScrollPane3))
                    .addGap(0, 20, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String User;
    Middleware M = new Middleware();
    public  void setData(String user) throws FileNotFoundException
    {
       List<String> mdsdf =  M.GetFriends(user);
                 DefaultListModel model = new DefaultListModel();
            for(String f : mdsdf) {
                model.addElement(f);}
        jFriendList.setModel(model);
    }
    public  void setMessages(String user, String user2) throws FileNotFoundException
    {
        List<LocalMessage> messssss = M.getUserMessageGroups(user, user2);
        List<String> mdsdf = M.GetMessage(messssss);
         DefaultListModel model = new DefaultListModel();
            for(String f : mdsdf) {
                model.addElement(f);}
        listaMensajes.setModel(model);
    }
    
    private void btnexit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexit2ActionPerformed
        // TODO add your handling code here:
        this.hide();
    }//GEN-LAST:event_btnexit2ActionPerformed


    
    private void btnSendMessagetoGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessagetoGroupsActionPerformed
        // TODO add your handling code here:
        MessagetoGroups MG = new MessagetoGroups();
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        MG.show();
        try {
            MG.User = manejador.getUserLogin();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewMesaggeGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSendMessagetoGroupsActionPerformed

    private void listaMensajesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaMensajesValueChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_listaMensajesValueChanged

    private void jFriendListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jFriendListValueChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jFriendListValueChanged

    private void jFriendListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFriendListMouseClicked
        try {
            // TODO add your handling code here:
            String selectedChat = jFriendList.getSelectedValue();
            setMessages(User, selectedChat);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewMesaggeGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jFriendListMouseClicked

    
   
    
   
    
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
            java.util.logging.Logger.getLogger(ViewMesaggeGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewMesaggeGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewMesaggeGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewMesaggeGroups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewMesaggeGroups().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendMessagetoGroups;
    private javax.swing.JButton btnexit2;
    private javax.swing.JList<String> jFriendList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaMensajes;
    // End of variables declaration//GEN-END:variables
}
