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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import proyecto_i.Administration.UserMenu;

/**
 *
 * @author Sebas
 */
public class PerfilUsuario extends javax.swing.JFrame {

    /**
     * Creates new form PerfilUsuario
     * @throws java.io.FileNotFoundException
     */
    public PerfilUsuario() throws FileNotFoundException, IOException {
        initComponents();
        
        
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        String loggedUser = manejador.getUserLogin();
        txtUsuarioLogueado.setText(loggedUser);
        
        ManejadorDeAmigos manejadorA = new ManejadorDeAmigos();
        ArrayList requests = manejadorA.getUserRequest(loggedUser);
        
        ArrayList confirmed = new ArrayList();
        for (int i = 0; i < requests.size(); i++) {
            String [] request = requests.get(i).toString().split(Pattern.quote("|"));
            int selection = JOptionPane.showConfirmDialog(null, "You have a friend request from: " + request[0] + "\nDo you want to accept it?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(selection==JOptionPane.YES_OPTION){
                request[2]="1";
                request[5]="1";
                String updateRequest = request[0]+"|"+request[1] + "|" + request[2] + "|" + request[3] + "|"+request[4]+"|"+request[5]+System.getProperty("line.separator");
                confirmed.add(updateRequest);
                if(manejadorA.updateBM(confirmed)){
                    ////////////////////////VERIFICAR/////////////////////////////
                    JOptionPane.showMessageDialog(null, "You're now friends with " + request[0]);
                }
            }
            else if(selection==JOptionPane.NO_OPTION){
                String updateRequest = requests.get(i).toString() + "*" + System.getProperty("line.separator");
                confirmed.add(updateRequest);
                if(manejadorA.updateBM(confirmed)){
                    ////////////////////////VERIFICAR/////////////////////////////
                    JOptionPane.showMessageDialog(null, "Request from " + request[0] + " declined.");
                }
            }            
        }    
        
        Usuario loggedUserA = manejador.getUserData(manejador.getUserLogin());
        if(loggedUserA.Rol() == 1){
            jMenuItem3.show();
        }else{
            jMenuItem3.show(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabelImage = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        btnGroups = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        btnGroups2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        btnSendMessagetoGroups = new javax.swing.JButton();
        btnSendMessagetoGroups1 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        txtUsuarioLogueado = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Profile");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setText("jTextField2");

        jTextField3.setEditable(false);
        jTextField3.setText("jTextField3");

        btnGroups.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGroups.setText("Options");
        btnGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGroupsActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setText("jTextField4");

        jLabel3.setText("Phone Number:");

        jLabel6.setText("E-Mail:");

        jTextField5.setEditable(false);
        jTextField5.setText("jTextField5");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel7.setText("Description:");

        btnGroups2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGroups2.setText("Groups");
        btnGroups2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGroups2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Groups:");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "You dont have groups" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        btnSendMessagetoGroups.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSendMessagetoGroups.setText("Send Message to Group");
        btnSendMessagetoGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMessagetoGroupsActionPerformed(evt);
            }
        });

        btnSendMessagetoGroups1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSendMessagetoGroups1.setText("Message from groups");
        btnSendMessagetoGroups1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMessagetoGroups1ActionPerformed(evt);
            }
        });

        txtUsuarioLogueado.setText("File");

        jMenu6.setText("Logout");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });
        txtUsuarioLogueado.add(jMenu6);

        jMenuItem4.setText("Refresh");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        txtUsuarioLogueado.add(jMenuItem4);

        jMenuBar2.add(txtUsuarioLogueado);

        jMenu2.setText("Friends");

        jMenuItem1.setText("Add Friends");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Friends List");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Friends & Requests");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar2.add(jMenu2);

        jMenu4.setText("Photos");

        jMenuItem5.setText("Upload");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setText("Show");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4))
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSendMessagetoGroups1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSendMessagetoGroups, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnGroups2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(btnSendMessagetoGroups, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendMessagetoGroups1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGroups2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    Usuario MainUser; 
    public void setUsuario(Usuario user) {
        this.MainUser = user;   
    }
    private  boolean FlagOptions = true;
    public void setFlagOptions(boolean fg) {
        this.FlagOptions = fg;   
    }
    public void SetDATA() throws FileNotFoundException
    {              //"C:\\MEIA\\FOTOS"
        if(!FlagOptions){
            //jButton2.enable(false);
            btnGroups.enable(false);
            btnGroups.setVisible(false);
        }
        ImageIcon image = new ImageIcon(MainUser.getFotografia());
        jLabelImage.setIcon(image);
        
        //jTextField1.setText(MainUser.getUsuario());
        jTextField2.setText(MainUser.getNombre() + " " +  MainUser.getApellido());
        jTextField3.setText(MainUser.getFechaDeNacimiento());
        jTextField4.setText(MainUser.getTelefono());
        jTextField5.setText(MainUser.getCorreo());
        jTextArea1.setText(MainUser.getDescripcion());
                                
        setGroupslst();
    }
    public void setGroupslst() throws FileNotFoundException
    {                          
        GroupsUtilities GU = new GroupsUtilities(); //AGREGAR GRUPOS VACIOS *****************************************
        List<String> GroupsOfUser = GU.GetUnique(GU.GetGroups(MainUser.getUsuario()),GU.GetUserGroupsAdministrate(MainUser.getUsuario()));
        DefaultListModel model = new DefaultListModel();
            for(String f : GroupsOfUser) {
                model.addElement(f);}
        if(!model.isEmpty())
            jList1.setModel(model);
    }
    
    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6ActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        try {
            // TODO add your handling code here:
            REO reorganize = new REO();
            
            reorganize.Reorganize(MainUser.getUsuario());
            MantenimientoAsociacionAmigosGrupo MAAG = new MantenimientoAsociacionAmigosGrupo();
            MAAG.ReoIndex(MainUser.getUsuario());
            
            
            ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
            manejador.CloseSession();
            ManejadorDeAmigos objManejadorDeAmigos = new ManejadorDeAmigos();
            reorganize.ReoGhost();
            
            
            this.hide();
            Login login = new Login();
            login.show();
            
            objManejadorDeAmigos.update();
            
            
        } catch (IOException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jMenu6MouseClicked

    private void btnGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroupsActionPerformed
        // TODO add your handling code here
        UserMenu UM = new UserMenu();
        UM.setUsuario(MainUser.getUsuario());
        try {
            if(UM.Main())
            {
                //this.hide();
            }
            if(UM.flagHideProfile)
            {
                this.hide();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGroupsActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        REO reorganize = new REO();
        
        try {
            reorganize.CheckForREO(MainUser.getUsuario());
        } catch (IOException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_formWindowClosing

    private void btnGroups2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroups2ActionPerformed
        try {
            // TODO add your handling code here:
            
            Groups groups = new Groups();
            groups.setGroupslst(MainUser.getUsuario());
            groups.show();
            setGroupslst();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGroups2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Amigos objAmigos=new Amigos();
        String user= JOptionPane.showInputDialog(null, "Search People");
//        objAmigos.setUser(user);
        try {
            boolean exists = objAmigos.findUser(user);
            if (exists) {
                objAmigos.hORs(1);        
                objAmigos.show();
            }
            else if((user == null)){
                //Do nothing, is to handle error. DONT DELETE THIS IF CONDITION.
            }
            else
            {
                JOptionPane.showMessageDialog(null, "The user doesn't exist.");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Amigos objAmigos=new Amigos();
        ManejadorDeAmigos objManejador = new ManejadorDeAmigos();
        ManejadorDeUsuarios objManejadorU = new ManejadorDeUsuarios();
        
        try {
            ArrayList amigos = objManejador.getFriendsList(objManejadorU.getUserLogin());
            if (objAmigos.fillTable(amigos)) {
                objAmigos.hORs(2);
                objAmigos.show();
            }
            else{
                JOptionPane.showMessageDialog(null, "You have no friends added.");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        try {
            // TODO add your handling code here:
            ShowMembers SM = new ShowMembers();
            SM.SetDATA(jList1.getSelectedValue(),MainUser.getUsuario());
            SM.show();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Amigos objAmigos = new Amigos();
        ManejadorDeAmigos objMAmigos = new ManejadorDeAmigos();
        
        try {
            ArrayList allRequests = objMAmigos.getAllRequestsAdmin();
            objAmigos.fillTableAdmin(allRequests);
            objAmigos.setColumnNames();
            objAmigos.hORs(3);
            objAmigos.show();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            this.hide();
            ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
            Usuario usuario = manejador.getUserData(MainUser.getUsuario());
            PerfilUsuario perfil = new PerfilUsuario();
            perfil.setUsuario(usuario);
            perfil.SetDATA();
            perfil.show();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnSendMessagetoGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessagetoGroupsActionPerformed
        // TODO add your handling code here:
        MessagetoGroups MG = new MessagetoGroups();
        MG.show();
    }//GEN-LAST:event_btnSendMessagetoGroupsActionPerformed

    private void btnSendMessagetoGroups1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessagetoGroups1ActionPerformed
        try {
            // TODO add your handling code here:
            ViewMesaggeGroups VMG = new ViewMesaggeGroups();
            ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
            VMG.SetData(manejador.getUserLogin());
            VMG.show();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSendMessagetoGroups1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        ManejadorDeImagenes objImagenes = new ManejadorDeImagenes();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        //FileNameExtensionFilter filtro = new FileNameExtensionFilter("Image","PNG");
        fileChooser.setFileFilter(new FileNameExtensionFilter(".jpg", "JPG"));
        fileChooser.setFileFilter(new FileNameExtensionFilter(".jpeg", "JPEG"));
        fileChooser.setFileFilter(new FileNameExtensionFilter(".png", "PNG"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        ManejadorDeUsuarios manejador = new ManejadorDeUsuarios();
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                objImagenes.SaveImage(selectedFile.getAbsolutePath(), manejador.getUserLogin());
            } catch (IOException ex) {
                Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(null, "Image Uploaded");
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        Imagenes objImages = null;
        try {
            objImages = new Imagenes();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        objImages.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed


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
            java.util.logging.Logger.getLogger(PerfilUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerfilUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerfilUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerfilUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PerfilUsuario().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(PerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGroups;
    private javax.swing.JButton btnGroups2;
    private javax.swing.JButton btnSendMessagetoGroups;
    private javax.swing.JButton btnSendMessagetoGroups1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JMenu txtUsuarioLogueado;
    // End of variables declaration//GEN-END:variables
}
