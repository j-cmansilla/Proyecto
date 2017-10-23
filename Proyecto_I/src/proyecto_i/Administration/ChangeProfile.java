/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i.Administration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import proyecto_i.CrearUsuario;
import proyecto_i.ManejadorDeUsuarios;
import proyecto_i.PerfilUsuario;
import proyecto_i.Usuario;

/**
 *
 * @author sebas
 */
public class ChangeProfile extends javax.swing.JFrame {

    /**
     * Creates new form ChangeProfile
     */
    public ChangeProfile() {
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        btnSelectPicture = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtDate = new org.jdesktop.swingx.JXDatePicker();
        jProgressBar1 = new javax.swing.JProgressBar();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();

        setTitle("Modify Profile");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("Modify Profile");

        jLabel1.setText("PASSWORD:");

        jLabel2.setText("E-MAIL:");

        jLabel4.setText("DATE OF BIRTH:");

        jLabel5.setText("PHONE NUMBER:");

        jLabel6.setText("PICTURE:");

        jLabel7.setText("DESCRIPTION:");

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        btnSelectPicture.setText("Select");
        btnSelectPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPictureActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);

        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });

        jLabel8.setText("RE-ENTER PASSWORD:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 149, Short.MAX_VALUE))
                            .addComponent(btnSelectPicture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField4)
                            .addComponent(txtDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3)
                            .addComponent(jPasswordField2)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelectPicture)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPictureActionPerformed

        // TODO add your handling code here:
        SelectPicture();
    }//GEN-LAST:event_btnSelectPictureActionPerformed

    private void SelectPicture(){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Image","PNG");
        fc.setFileFilter(filtro);
        int respuesta = fc.showOpenDialog(this);
        File archivoElegido = fc.getSelectedFile();
        if (archivoElegido == null) {
            
        }else{
            Photo = (archivoElegido.getPath());
            JOptionPane.showMessageDialog(null, "Picture selected!");
        }
    }
    //Password
    
    private int pBar = 0;
    private String Password, Desc, Mail, User, Name, LastName, date, Photo;
    private int Number, Admin;
    private int[] Settings = {6,3,2,1,2,4,6,3};
    private int CheckPass(String pass)
    {
        int points = pass.length() * Settings[1];
        int[] countCase = getUpLowCase(pass);
        int[] countNL = getLNCount(pass);
        points += Settings[2]*countCase[0];
        points += countNL[0] + Settings[3];
        points += countNL[1]+ Settings[4];
        points += countNL[2] * (pass.length() + Settings[5]);
        
        if(countNL[1]==0 && countNL[2]==0)
        {
            points-= Settings[6];
        }
        if(countNL[0]==0 && countNL[2]==0)
        {
            points-= Settings[7];
        } 
        return points;
    }
    private int [] getLNCount(String str)
    {
        int[] res = new int[3];
        char[] array = str.toCharArray();
        for (int j = 0; j < array.length; j++) {
            if ((array[j] + 'a' - 97 >= 65 && array[j] + 'a' - 97 <= 90)|| (array[j] + 'a' - 97 >= 97 && array[j] + 'a' - 97 <= 122)) {

            res[0]++;
            } 
            else if(array[j] >=48 && array[j]<=57) //numbers
            {
                res[1]++;
            }
            else //symbols
            {
                res[2]++;
            }
        }
        
        return  res;
    }
    private int []getUpLowCase(String str)
    {
        int[] res = new int[2];
        for (int k = 0; k < str.length(); k++) 
        {
            if (Character.isUpperCase(str.charAt(k))) res[0]++;
            if (Character.isLowerCase(str.charAt(k))) res[1]++;
        }
        return res;
    }
    private String Result(int points)
    {
        if (points < 26)
        {
            pBar=25;
            return  "Contraseña insegura.";
        }
        else if(points <36)
        {
            pBar=35;
            return "Contraseña poco segura." ;
        }
        else if(points < 51)
        {
            pBar=50;
            return "Contraseña segura.";
        }
        else {
            pBar=100;
            return "Contraseña muy segura.";
        }
    }
    public boolean CU = true;
    public String PASSVER(String STRA,String STRB)
    {
        if(STRA.length()< Settings[0])
        {
            CU = false;
            return ("Debe ingresar una contraseña con mas de 6 caracteres");
        }
        if(STRA.compareTo(STRB) != 0)
        {
            CU = false;
             return ("Las contraseñas no concuerdan");
        }
        if (CheckPass(STRA)<=25)
        {
            CU = false;
            return "La contraseña no cumple los requisitos mínimos de seguridad!";
        }
        return (Result(CheckPass(STRA)) + "\nPuntuación: " +CheckPass(STRA));
        
      
    }
    public boolean DoPassVerification()
     {
          jTextField1.setText("");
        if(jPasswordField1.getText().length()< Settings[0])
        {
            jTextField1.setText("Debe ingresar una contraseña con mas de 6 caracteres");
            return false;
        }//"".equals(jTextField3.getText())
        if(!jPasswordField1.getText().equals(jPasswordField2.getText()))
        {
             jTextField1.setText("Las contraseñas no concuerdan");
            return false;
        }
        
        jTextField1.setText(Result(CheckPass(jPasswordField1.getText())) + "\nPuntuación: " +CheckPass(jPasswordField1.getText()));
        Password = jPasswordField1.getText();
        jProgressBar1.setValue(pBar);
        return  true;
     }
    //End Password
    
    public boolean CheckJText()
    {
        if(!jTextField2.getText().contains("@"))
        {
            jTextField2.setText("");
            return false;
        }
        Mail = jTextField2.getText();
        try {
           Number =  Integer.parseInt(jTextField4.getText());
        } catch (NumberFormatException e) {
            jTextField4.setText("");
            return false;
        }
        if((jTextField3.getText()).length()<1){
            return false;
        }
        Desc = jTextField3.getText();
            try{
                date = txtDate.getDate().toString();
            }catch(Exception e){
                date = "";
                return false;
            }
        return  true;
    }
     
     
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        String userLoged = "";
        Usuario user = null;
        try {
            userLoged = manejador.getUserLogin();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChangeProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
             user = manejador.getUserData(userLoged);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChangeProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
       if (DoPassVerification())
       {
           if(CheckJText())
           {
               CrearUsuario CU = new CrearUsuario();
               Usuario usuario;
               try {
                   usuario = new Usuario(user.getUsuario(), user.getNombre(), user.getApellido(), Password, Admin, date, Mail, Integer.toString(Number), CU.SaveImageMEIA(Photo) , Desc, 1);
               } catch (IOException ex) {
                   Logger.getLogger(ChangeProfile.class.getName()).log(Level.SEVERE, null, ex);
                       usuario = new Usuario(user.getUsuario(), user.getNombre(), user.getApellido(), Password, Admin, date, Mail, Integer.toString(Number), Photo , Desc, 1);
               }
                ManejadorDeUsuarios MDU = new ManejadorDeUsuarios();
               try {
                   MDU.SetUserData(usuario);
                   PerfilUsuario perfil = new PerfilUsuario();
                   perfil.setUsuario(usuario);
                   perfil.SetDATA();
                   perfil.show();
                   this.dispose();
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(ChangeProfile.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
                   Logger.getLogger(ChangeProfile.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           else
               JOptionPane.showMessageDialog(null, "Fill all the fields!");
       }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

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
            java.util.logging.Logger.getLogger(ChangeProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeProfile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelectPicture;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private org.jdesktop.swingx.JXDatePicker txtDate;
    // End of variables declaration//GEN-END:variables
}
