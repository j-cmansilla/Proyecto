/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import proyecto_i.Administration.ChangeProfile;
import static proyecto_i.MD5.crypt;
//import org.apache.commons.io.FileUtils;

/**
 *
 * @author Jose Mansilla
 */
public class CrearUsuario extends javax.swing.JFrame {
    JFileChooser fc = new JFileChooser();
    Utilities Util = new Utilities();
    /**
     * Creates new form CrearUsuario
     */
    public CrearUsuario() {
        initComponents();
        lblPicture.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtSecondName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPicture = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSelectPicture = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        btnCrearUsuario = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtPass1 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setTitle("Create User");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel1.setText("USER:");

        txtUser.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel2.setText("PASS:");

        txtPass.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("Register");

        jLabel4.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel4.setText("FIRST NAME:");

        txtName.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        txtSecondName.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel5.setText("LAST NAME:");

        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel7.setText("BIRTH DATE:");

        jLabel8.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel8.setText("E-MAIL:");

        txtEmail.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        lblPicture.setEnabled(false);
        lblPicture.setOpaque(true);

        jLabel9.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel9.setText("MAIN PICTURE:");

        btnSelectPicture.setText("Select");
        btnSelectPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPictureActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel10.setText("DESCRIPTION:");

        txtDescription.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        btnCrearUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCrearUsuario.setText("Create User");
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Exit");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel11.setText("PHONE:");

        txtPhone.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });

        txtPass1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtPass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPass1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel6.setText("PASS CONF:");

        jTextField1.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(lblPicture))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPass)
                            .addComponent(txtName)
                            .addComponent(txtUser)
                            .addComponent(txtSecondName)
                            .addComponent(txtPass1)
                            .addComponent(jTextField1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescription)
                                    .addComponent(btnSelectPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhone)
                                    .addComponent(txtEmail)
                                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(216, 216, 216))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(5, 5, 5)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(btnSelectPicture))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrearUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblPicture))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void btnSelectPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPictureActionPerformed

        // TODO add your handling code here:
        SelectPicture();
    }//GEN-LAST:event_btnSelectPictureActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed

        try{                                                
            // TODO add your handling code here:
            
            String date;
            try{
                date = txtDate.getDate().toString();
            }catch(Exception e){
                date = "";
            }
            if(!Util.isInteger(txtPhone.getText()))
            {
                JOptionPane.showMessageDialog(null, "The phonenumber isnt a number!");
                txtPhone.setText("");
            }
            else
            if (txtUser.getText().equals("") || txtName.getText().equals("") || txtPass.getText().equals("") || txtSecondName.getText().equals("") || date.equals("") || txtEmail.getText().equals("")|| txtPhone.getText().equals("") || lblPicture.getText().equals("") || txtDescription.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the fields!");
            }
            else{
                //VALIDATE PASSWORD
                ChangeProfile CP = new ChangeProfile();
                jTextField1.setText(CP.PASSVER(txtPass.getText(), txtPass1.getText()));
               /* JOptionPane.showMessageDialog(null, DeterminarPuntuacion(txtPass.getText()));
                if (CP.CheckPass(txtPass.getText())<=25) {
                    JOptionPane.showMessageDialog(null, "La contraseña no cumple los requisitos mínimos de seguridad! "+txtPass.getText());
               */
                if (CP.CU) {      
              // }else{
                   if (!validateEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(null, "El correo no coincide con un correo correcto!");
                        txtEmail.setText("");
                    }else{
                       ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
                       if (manejador.validarUsuario(txtUser.getText(), "randomPassword!") == 0) {
                           try {
                               Iniciar();
                           } catch (Exception ex) {
                               Logger.getLogger(CrearUsuario.class.getName()).log(Level.SEVERE, null, ex);
                           }
                            JOptionPane.showMessageDialog(null, "User "+txtUser.getText()+" created!");
                       }else{
                          JOptionPane.showMessageDialog(null, "User "+txtUser.getText()+" is alredy in use!");
                          txtUser.setText("");
                          return;
                       }
                        if(flagForADMIN)
                        {
                            Login returnL=new Login();
                            returnL.show();  
                        }
                        this.dispose();
                    } 
                }
            }
        }catch(FileNotFoundException ex){
            Logger.getLogger(CrearUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrearUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private boolean flagForADMIN = true;
    public void SETFLAG(boolean flag)
    {
        flagForADMIN = flag;
    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if(flagForADMIN)
        {
            this.hide();
            Login regresar=new Login();
            regresar.show();
            
        }else{
            this.dispose();
        }
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void txtPass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPass1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPass1ActionPerformed

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed

    
    
    private void Iniciar() throws FileNotFoundException, IOException, Exception{
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        ZoneId zonedId = ZoneId.of( "America/Guatemala" );
        ZonedDateTime zdt = ZonedDateTime.now( zonedId );
        
        Usuario usuario = new Usuario(txtUser.getText(), txtName.getText(), txtSecondName.getText(), crypt(txtPass.getText()),-1, txtDate.getDate().toString(), txtEmail.getText(), txtPhone.getText(), SaveImageMEIA(lblPicture.getText()), txtDescription.getText(),1);
        manejador.llenarBitacora(txtUser.getText(), zdt, usuario);
        //String user = txtUser.getText()+"|"+txtName.getText()+"|"+txtSecondName.getText()+"|"+txtPass.getText()+"|"+"-1"+"|"+txtDate.getDate().toString()+"|"+txtEmail.getText()+"|"+txtPhone.getText()+"|"+SaveImageMEIA(lblPicture.getText())+"|"+txtDescription.getText()+"|"+"1";
        //manejador.llenarBitacora(usuario,zdt);
        
    }
    public String SaveImageMEIA(String originalPath) throws IOException
    {
        String[] vgsolution;
        try {     
            BufferedImage img = null;
            vgsolution = originalPath.split(Pattern.quote("."));
            img = ImageIO.read(new File(originalPath));  
            File outputfile = new File("C:\\MEIA\\IMAGES\\" +txtUser.getText() +"."+ vgsolution[1]);
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            return  originalPath;
        }
      return "C:\\MEIA\\IMAGES\\" +txtUser.getText()+"."+ vgsolution[1];
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
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearUsuario().setVisible(true);
            }
        });
    }
    
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
    private void SelectPicture(){
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Image","PNG");
        fc.setFileFilter(filtro);
        int respuesta = fc.showOpenDialog(this);
        File archivoElegido = fc.getSelectedFile();
        if (archivoElegido == null) { 
        }else{
            lblPicture.setText(archivoElegido.getPath());
            JOptionPane.showMessageDialog(null, "Picture selected!");
        }
    }
/*
    private int DeterminarPuntuacion(String password){
        int puntuacion = 0;
        if (determinarPuntuacion[0] > password.length()) {
            return 0;
        }else{
            puntuacion = puntuacion + (determinarPuntuacion[1]*password.length());
            puntuacion = puntuacion + (determinarPuntuacion[2]*NumeroDeMayusculas(password));
            puntuacion = puntuacion + (determinarPuntuacion[3]*NumeroDeLetras(password));
            puntuacion = puntuacion + (determinarPuntuacion[4]*NumeroDeNumeros(password));
            puntuacion = puntuacion + (determinarPuntuacion[5]+(NumeroDeSimbolos(password)*password.length()));
            if (NumeroDeLetras(password) == password.length()) {
                puntuacion = puntuacion - determinarPuntuacion[6];
            }
            if (NumeroDeNumeros(password) == password.length()) {
                puntuacion = puntuacion - determinarPuntuacion[7];
            }
        }
        return puntuacion;
    }
    */
    /*
    private int NumeroDeSimbolos(String password){
        return password.length() - NumeroDeLetras(password)-NumeroDeNumeros(password);
    }
    */
    /*
    private int NumeroDeNumeros(String password){
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            for (int j = 0; j < num.length(); j++) {
                if (password.charAt(i) == num.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }*/
    
   /* private int NumeroDeLetras(String password){
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            for (int j = 0; j < mayu.length(); j++) {
                if (password.charAt(i) == mayu.charAt(j) || password.charAt(i) == minu.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }*/
    
   /* private int NumeroDeMayusculas(String password){
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            for (int j = 0; j < mayu.length(); j++) {
                if (mayu.charAt(j) == password.charAt(i)) {
                    count++;
                }
            }
        }
        return count;
    }*/
    
    /*String minu ="abcdefghijklmnopqrstuvwxyz";
    String mayu ="ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    String num = "0123456789";
    int [] determinarPuntuacion = {6,3,2,1,2,4,6,3};
    */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnSelectPicture;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblPicture;
    private org.jdesktop.swingx.JXDatePicker txtDate;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSecondName;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
