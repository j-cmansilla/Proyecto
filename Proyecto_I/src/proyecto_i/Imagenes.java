/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.awt.Dimension;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Harry
 */
public class Imagenes extends javax.swing.JFrame {

    /**
     * Creates new form Imagenes
     */
    ManejadorDeUsuarios objUsuarios = new ManejadorDeUsuarios();
    ManejadorDeAmigos objAmigos = new ManejadorDeAmigos();
    ManejadorDeImagenes objImagenes = new ManejadorDeImagenes();
    JComboBox friendsBox;
    
    public Imagenes() throws FileNotFoundException {
        initComponents();
        ArrayList friends = objAmigos.allFriends(objUsuarios.getUserLogin());
        friendsBox = new JComboBox(friends.toArray());
        jMenuBar1.add(friendsBox);
        String loggedUser = objUsuarios.getUserLogin();
        uploadImages(loggedUser);
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
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        jScrollPane1.setViewportView(jPanel1);

        jMenu1.setText("Show");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        String friend = friendsBox.getSelectedItem().toString();
        try {
            uploadImages(friend);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Imagenes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu1MouseClicked

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
            java.util.logging.Logger.getLogger(Imagenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Imagenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Imagenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Imagenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Imagenes().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Imagenes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void uploadImages(String loggedUser) throws FileNotFoundException{
        ArrayList objPaths = objImagenes.getPaths(loggedUser);
        if (objPaths.isEmpty()) {
            JOptionPane.showMessageDialog(null, "User doesn't have images uploaded.");
        }else{
            jPanel1.removeAll();
            for (int i = 0; i < objPaths.size(); i++) {
                ImageIcon imageI = new ImageIcon(objPaths.get(i).toString());
                Image image = imageI.getImage();
                int w = imageI.getIconWidth();
                int h = imageI.getIconHeight();
                ArrayList size = resizeP(h, w);
                Image newImage = image.getScaledInstance((int)Double.parseDouble(size.get(1).toString()), (int)Double.parseDouble(size.get(0).toString()), Image.SCALE_SMOOTH);            
                imageI = new ImageIcon(newImage);
                JLabel picture = new JLabel(imageI);
                jPanel1.add(picture);
            }
            revalidate();
            repaint();
        }        
    }
    
    public ArrayList resizeP(double h, double w){
        if(h>300 || w>300){
            return resizeP(h*0.9, w*0.9);
        }
        else{
            ArrayList size = new ArrayList();
            size.add(String.valueOf(h));
            size.add(String.valueOf(w));
            return size;
        }
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
