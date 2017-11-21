/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.FileNotFoundException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Mansilla
 */
public class MensajesLocales extends javax.swing.JFrame {

    /**
     * Creates new form MensajesLocales
     */
    public MensajesLocales() throws FileNotFoundException {
        initComponents();
        listaDeAmigos = new ArrayList();
        listaDeMensajesPrivados = new ArrayList();
        llenarAmigos();
    }
    
    String userWhoSendMessage;
    String userWhoRecievesMessage;
    ArrayList listaDeAmigos;
    ArrayList listaDeMensajesPrivados;
    
    private void llenarAmigos() throws FileNotFoundException{
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        ManejadorDeAmigos manejadorA = new ManejadorDeAmigos();
        String userLogued = manejador.getUserLogin();
        userWhoSendMessage = userLogued;
        ArrayList listaAmigos = manejadorA.getFriendsList(userLogued);
        listaDeAmigos = listaAmigos;
        DefaultListModel modeloAmigos = new DefaultListModel();
        modeloAmigos.addElement("Public chat");
        for (int i = 0; i < listaAmigos.size(); i++) {
            if (listaAmigos.get(i).toString().split("\\|")[0].equals(userLogued)) {
                modeloAmigos.addElement(listaAmigos.get(i).toString().split("\\|")[1]);
            }else{
                modeloAmigos.addElement(listaAmigos.get(i).toString().split("\\|")[0]);
            }
        }
        jFriendList.setModel(modeloAmigos);
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
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jFriendList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        txtMessage = new javax.swing.JTextField();
        btnSendMessage = new javax.swing.JButton();
        comboTypeOfMessage = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaMensajes = new javax.swing.JList<>();
        btnDeleteMessage = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("Messages");

        jInternalFrame1.setVisible(true);

        jFriendList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jFriendListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jFriendList);

        jLabel1.setText("Friends:");

        btnSendMessage.setText("Send");
        btnSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMessageActionPerformed(evt);
            }
        });

        comboTypeOfMessage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Public", "Private" }));

        jLabel2.setText("Message Type:");

        listaMensajes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaMensajesValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listaMensajes);

        btnDeleteMessage.setText("Delete Message");
        btnDeleteMessage.setEnabled(false);
        btnDeleteMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMessageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboTypeOfMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(4, 4, 4))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSendMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTypeOfMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(btnDeleteMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrame1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(241, 241, 241))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInternalFrame1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFriendListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jFriendListValueChanged
        // TODO add your handling code here:
        llenarMensajes();
    }//GEN-LAST:event_jFriendListValueChanged

    private void llenarMensajes(){
        DefaultListModel modeloMensajes = new DefaultListModel();
        String selectedChat = jFriendList.getSelectedValue();
        LocalMessageManejador manejador = new LocalMessageManejador();
        //txtMessageArea.setText("");
        userWhoRecievesMessage = selectedChat;
        String messageToShow = "";
        ArrayList listaMensajes = new ArrayList();
        if (!selectedChat.equals("Public chat")) {
            try {
                txtMessage.enable();
                btnSendMessage.setEnabled(true);
                listaMensajes = manejador.getPrivateMessages(userWhoSendMessage, userWhoRecievesMessage);
                listaDeMensajesPrivados = listaMensajes;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MensajesLocales.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < listaMensajes.size(); i++) {
               messageToShow = listaMensajes.get(i).toString().split("\\|")[0]+": "+listaMensajes.get(i).toString().split("\\|")[3];
               modeloMensajes.addElement(messageToShow);
               //txtMessageArea.setText(txtMessageArea.getText()+System.getProperty("line.separator")+messageToShow); 
            }
        }else{
            try {
                txtMessage.disable();
                btnSendMessage.setEnabled(false);
                listaMensajes = manejador.getPublicMessages();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MensajesLocales.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < listaMensajes.size(); i++) {
                if (userExistsInList(listaMensajes.get(i).toString().split("\\|")[1])) {
                    modeloMensajes.addElement(messageToShow);
                    messageToShow = listaMensajes.get(i).toString().split("\\|")[0]+" => "+listaMensajes.get(i).toString().split("\\|")[1]+": "+listaMensajes.get(i).toString().split("\\|")[3];
                    //txtMessageArea.setText(txtMessageArea.getText()+System.getProperty("line.separator")+messageToShow); 
                }
            }
        }
        this.listaMensajes.setModel(modeloMensajes);
    }
    
    private boolean userExistsInList(String userFriend){
        for (int i = 0; i < listaDeAmigos.size(); i++) {
            if (listaDeAmigos.get(i).toString().split("\\|")[1].equals(userFriend)) {
               return true;
            }
        }
        return false;
    }
    
    private void btnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessageActionPerformed
        // TODO add your handling code here:
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        LocalMessage newMessage = new LocalMessage(userWhoSendMessage, userWhoRecievesMessage, zdt.toString(), txtMessage.getText(), comboTypeOfMessage.getSelectedIndex(), 1);
        LocalMessageManejador manejador = new LocalMessageManejador();
        String messageToShow = userWhoSendMessage+":"+txtMessage.getText();
        //txtMessageArea.setText(txtMessageArea.getText()+System.getProperty("line.separator")+messageToShow);
        txtMessage.setText("");
        try {
            manejador.llenarBitacora(userWhoSendMessage, zdt, newMessage);
        } catch (Exception ex) {
            Logger.getLogger(MensajesLocales.class.getName()).log(Level.SEVERE, null, ex);
        }
        llenarMensajes();
    }//GEN-LAST:event_btnSendMessageActionPerformed

    private void btnDeleteMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMessageActionPerformed
        // TODO add your handling code here:
        LocalMessageManejador manejador = new LocalMessageManejador();
        //manejador.deleteMessage(listaDeMensajesPrivados.get(listaMensajes.getSelectedValue()));
    }//GEN-LAST:event_btnDeleteMessageActionPerformed

    
    
    private void listaMensajesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaMensajesValueChanged
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, listaMensajes.getSelectedIndex()+"...."+listaDeMensajesPrivados.size());
        if (listaDeMensajesPrivados.isEmpty()) return;
        JOptionPane.showMessageDialog(null, listaDeMensajesPrivados.get(listaMensajes.getSelectedIndex()).toString().split("\\|")[1]);
        if (!listaMensajes.getSelectedValue().equals("Public chat") && listaDeMensajesPrivados.get(listaMensajes.getSelectedIndex()).toString().split("\\|")[1].equals(userWhoSendMessage)) {
            btnDeleteMessage.setEnabled(true);
        }else{
            btnDeleteMessage.setEnabled(false);
        }
    }//GEN-LAST:event_listaMensajesValueChanged

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
            java.util.logging.Logger.getLogger(MensajesLocales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MensajesLocales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MensajesLocales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MensajesLocales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MensajesLocales().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MensajesLocales.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteMessage;
    private javax.swing.JButton btnSendMessage;
    private javax.swing.JComboBox<String> comboTypeOfMessage;
    private javax.swing.JList<String> jFriendList;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaMensajes;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}