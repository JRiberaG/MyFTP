package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import managers.ClientManager;
import managers.ServerManager;
import threads.TransferClient;
import threads.TransferServer;

/**
 * TODO:
 *      - Test to send the whole file instead of byte to byte
 *      - Comment
 *      - Add few things like signature, version, etc.
 *      - Clean the code (delete the useless comments, group with methods, etc.)
 *      - Screenshots and update readme
 * 
 */
public class JFrameMain extends javax.swing.JFrame {
    
    private static final int DEF_PORT = 5000;
    public static ServerManager sm;
    public static ClientManager cm;
    public static TransferServer ts;
    public static TransferClient tc;
    
    private static boolean asServer;
    public static String strResult = "";
    
    private static JFileChooser openFileChooser;

    public JFrameMain() {
        initComponents();
        
        setAsServer();
        
        configFileChooser();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroupRole = new javax.swing.ButtonGroup();
        panelRole = new javax.swing.JPanel();
        rbServer = new javax.swing.JRadioButton();
        rbClient = new javax.swing.JRadioButton();
        panelConnection = new javax.swing.JPanel();
        lblIP = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        etIP = new javax.swing.JTextField();
        btnDisconnect = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();
        fetPort = new javax.swing.JFormattedTextField();
        lblErrIP = new javax.swing.JLabel();
        lblErrPort = new javax.swing.JLabel();
        panelTransfer = new javax.swing.JPanel();
        lblSelectFiles = new javax.swing.JLabel();
        etPath = new javax.swing.JTextField();
        btnChooser = new javax.swing.JButton();
        scrollList = new javax.swing.JScrollPane();
        listFiles = new javax.swing.JList<>();
        btnClearTransfer = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        lblPath = new javax.swing.JLabel();
        lblErrPath = new javax.swing.JLabel();
        lblErrFiles = new javax.swing.JLabel();
        panelResults = new javax.swing.JPanel();
        btnClearResults = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyFTP");
        setMinimumSize(new java.awt.Dimension(750, 620));
        setPreferredSize(new java.awt.Dimension(750, 620));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRole.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Role", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        panelRole.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rbGroupRole.add(rbServer);
        rbServer.setSelected(true);
        rbServer.setText("Server");
        rbServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbServerActionPerformed(evt);
            }
        });
        panelRole.add(rbServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 27, -1, -1));

        rbGroupRole.add(rbClient);
        rbClient.setText("Client");
        rbClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbClientActionPerformed(evt);
            }
        });
        panelRole.add(rbClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        getContentPane().add(panelRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 220, 70));

        panelConnection.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Connection config", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        panelConnection.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIP.setText("Server IP:");
        panelConnection.add(lblIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        lblPort.setText("Port:");
        panelConnection.add(lblPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        etIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etIPActionPerformed(evt);
            }
        });
        panelConnection.add(etIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 170, -1));

        btnDisconnect.setText("Disconnect");
        btnDisconnect.setEnabled(false);
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisconnectActionPerformed(evt);
            }
        });
        panelConnection.add(btnDisconnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        btnConnect.setBackground(java.awt.SystemColor.activeCaption);
        btnConnect.setText("Connect");
        btnConnect.setNextFocusableComponent(etPath);
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });
        panelConnection.add(btnConnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 120, -1));

        fetPort.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        fetPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fetPortActionPerformed(evt);
            }
        });
        panelConnection.add(fetPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 80, -1));

        lblErrIP.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblErrIP.setForeground(java.awt.Color.red);
        lblErrIP.setText("!");
        lblErrIP.setToolTipText("This field can't be empty!");
        panelConnection.add(lblErrIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 20, 30));

        lblErrPort.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblErrPort.setForeground(java.awt.Color.red);
        lblErrPort.setText("!");
        lblErrPort.setToolTipText("This field can't be empty!");
        panelConnection.add(lblErrPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 20, -1));

        getContentPane().add(panelConnection, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 290, 150));

        panelTransfer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Transfer options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        panelTransfer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSelectFiles.setText("Select the file(s) to transfer:");
        panelTransfer.add(lblSelectFiles, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        etPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etPathActionPerformed(evt);
            }
        });
        panelTransfer.add(etPath, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, -1));

        btnChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooserActionPerformed(evt);
            }
        });
        panelTransfer.add(btnChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 47, -1, 30));

        scrollList.setViewportView(listFiles);

        panelTransfer.add(scrollList, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 210, 120));

        btnClearTransfer.setText("Clear");
        btnClearTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTransferActionPerformed(evt);
            }
        });
        panelTransfer.add(btnClearTransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 105, -1));

        btnSend.setBackground(java.awt.SystemColor.activeCaption);
        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });
        panelTransfer.add(btnSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 105, -1));

        lblPath.setText("Path of the file(s):");
        panelTransfer.add(lblPath, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        lblErrPath.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblErrPath.setForeground(java.awt.Color.red);
        lblErrPath.setText("!");
        lblErrPath.setToolTipText("That's not a valid path! If this machine is acting as server, this field can be empty.");
        panelTransfer.add(lblErrPath, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 46, 20, 30));

        lblErrFiles.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblErrFiles.setForeground(java.awt.Color.red);
        lblErrFiles.setText("!");
        lblErrFiles.setToolTipText("That's not a valid path! If this machine is acting as server, this field can be empty.");
        panelTransfer.add(lblErrFiles, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 20, 30));

        getContentPane().add(panelTransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 260, 310));

        panelResults.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Results", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        panelResults.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnClearResults.setText("Clear");
        btnClearResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearResultsActionPerformed(evt);
            }
        });
        panelResults.add(btnClearResults, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 120, -1));

        txtArea.setEditable(false);
        txtArea.setBackground(new java.awt.Color(51, 51, 51));
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        txtArea.setForeground(new java.awt.Color(204, 204, 204));
        txtArea.setRows(5);
        jScrollPane2.setViewportView(txtArea);

        panelResults.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 380, 390));

        getContentPane().add(panelResults, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 400, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbServerActionPerformed
        setAsServer();
    }//GEN-LAST:event_rbServerActionPerformed

    private void rbClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbClientActionPerformed
        setAsClient();
    }//GEN-LAST:event_rbClientActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        connect();
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        if (asServer) {
            try {
                activatePanelConnection();
                clearTransfer();
                sm.close();
                btnDisconnect.setEnabled(false);

                deactivatePanelTransfer();
                activatePanelRole();
                etIP.setEnabled(false);
                lblIP.setEnabled(false);

                btnConnect.requestFocus();
            } catch (IOException ex) {
                strResult += "Status: There was a problem closing the server.\n";
                txtArea.setText(strResult);
            }
        } else {
            try {
                activatePanelConnection();
                clearTransfer();
                cm.close();
                strResult += "Status: Connection closed.\n";
                txtArea.setText(strResult);
                activatePanelRole();
                deactivatePanelTransfer();
                clearTransfer();
                
                btnConnect.requestFocus();
            } catch (IOException ex) {
                strResult += "Status: There was a problem closing the connection.\n";
                txtArea.setText(strResult);
            }
        }
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void btnChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooserActionPerformed
        int returnValue = openFileChooser.showOpenDialog(this);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            etPath.setText(openFileChooser.getSelectedFile().getAbsolutePath());
            if(!asServer){
                addFiles(new File(etPath.getText()));
            }
        }
    }//GEN-LAST:event_btnChooserActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        boolean pathOk;
        if (asServer) {
            pathOk = true;
            try {
                if (!etPath.getText().isEmpty()) {
                    if (!new File(etPath.getText()).isDirectory()){
                        lblErrPath.setVisible(true);
                        pathOk = false;
                    } else {
                        lblErrPath.setVisible(false);
                        sm.setPath(etPath.getText());
                    }
                }

                if (pathOk) {
                    ts.setReceive(true);
                    
                    deactivatePanelTransfer();
                }
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
            txtArea.setText(strResult);
        } else {
            pathOk = false;
            if (!etPath.getText().isEmpty()) {
                lblErrPath.setVisible(false);
                File file = new File(etPath.getText());
                if (!file.isDirectory()){
                    lblErrPath.setVisible(true);
                    clearFiles();
                } else {
                    lblErrPath.setVisible(false);
                    int numSelected = listFiles.getSelectedIndices().length;
                    int []selectedIndexes = listFiles.getSelectedIndices();
                    if (numSelected > 0) {
                        lblErrFiles.setVisible(false);

                        try {
                            ArrayList<String> filenames = new ArrayList<>();
                            for (Integer i : selectedIndexes) {
                                filenames.add(listFiles.getModel().getElementAt(i));
                            }
                            tc = new TransferClient(numSelected, filenames, etPath.getText());
                            tc.start();
                            
                            deactivatePanelTransfer();
                            
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                    } else {
                        lblErrFiles.setVisible(true);
                        clearFiles();
                    }
                }
            } else {
                lblErrPath.setVisible(true);
                clearFiles();
            }
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnClearTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTransferActionPerformed
        clearTransfer();
    }//GEN-LAST:event_btnClearTransferActionPerformed

    private void etPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etPathActionPerformed
        File file = new File(etPath.getText());
        if (file.isDirectory()) {
            lblErrPath.setVisible(false);
            addFiles(file);
        } else {
            lblErrPath.setVisible(true);
            clearFiles();
        }
    }//GEN-LAST:event_etPathActionPerformed

    private void btnClearResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearResultsActionPerformed
        strResult = "";
        txtArea.setText(strResult);
    }//GEN-LAST:event_btnClearResultsActionPerformed

    private void etIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etIPActionPerformed
        connect();
    }//GEN-LAST:event_etIPActionPerformed

    private void fetPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fetPortActionPerformed
        connect();
    }//GEN-LAST:event_fetPortActionPerformed

    // <editor-fold defaultstate="collapsed" desc="Main & Variables">
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
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMain().setVisible(true);
            }
        });
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton btnChooser;
    private javax.swing.JButton btnClearResults;
    private static javax.swing.JButton btnClearTransfer;
    private static javax.swing.JButton btnConnect;
    private static javax.swing.JButton btnDisconnect;
    private static javax.swing.JButton btnSend;
    private static javax.swing.JTextField etIP;
    private static javax.swing.JTextField etPath;
    private static javax.swing.JFormattedTextField fetPort;
    private javax.swing.JScrollPane jScrollPane2;
    static javax.swing.JLabel lblErrFiles;
    private javax.swing.JLabel lblErrIP;
    static javax.swing.JLabel lblErrPath;
    private javax.swing.JLabel lblErrPort;
    private static javax.swing.JLabel lblIP;
    static javax.swing.JLabel lblPath;
    private static javax.swing.JLabel lblPort;
    static javax.swing.JLabel lblSelectFiles;
    private static javax.swing.JList<String> listFiles;
    private javax.swing.JPanel panelConnection;
    private javax.swing.JPanel panelResults;
    private static javax.swing.JPanel panelRole;
    private static javax.swing.JPanel panelTransfer;
    private static javax.swing.JRadioButton rbClient;
    private javax.swing.ButtonGroup rbGroupRole;
    private static javax.swing.JRadioButton rbServer;
    private javax.swing.JScrollPane scrollList;
    public static javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Sets">
    private void setAsServer() {
        cm = null;
        
        asServer = true;
        
        deactivatePanelTransfer();
        activatePanelResults();
        clearFields();
        
        fetPort.setText(String.valueOf(DEF_PORT));
        lblIP.setEnabled(false);
        etIP.setEnabled(false);
        lblSelectFiles.setEnabled(false);
        listFiles.setEnabled(false);
        btnSend.setEnabled(false);
        btnClearTransfer.setEnabled(false);
        
        btnConnect.setText("New connection");
        btnSend.setText("Receive");
        lblPath.setText("Path (to download the files):");
        
        fetPort.requestFocus();
    }
    
    private void setAsClient() {
        sm = null;
        
        asServer = false;
        
        deactivatePanelTransfer();
        activatePanelResults();
        clearFields();
        
        fetPort.setText(String.valueOf(DEF_PORT));
        lblIP.setEnabled(true);
        etIP.setEnabled(true);
        
        btnConnect.setText("Connect");
        btnSend.setText("Send");
        lblPath.setText("Path (upload files):");
        
        etIP.requestFocus();
    }
    
    public static void setForNewConnection(boolean isServer) {
        if (isServer){
            btnConnect.requestFocus();
            lblIP.setEnabled(false);
            etIP.setEnabled(false);
        } else {
            etIP.requestFocus();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Activaters/Deactivaters">
    private void deactivatePanelRole() {
        panelRole.setEnabled(false);
        rbServer.setEnabled(false);
        rbClient.setEnabled(false);
    }

    public static void activatePanelRole() {
        panelRole.setEnabled(true);
        rbServer.setEnabled(true);
        rbClient.setEnabled(true);
    }
    
    public static void activatePanelConnection() {
        lblIP.setEnabled(true);
        lblPort.setEnabled(true);
        etIP.setEnabled(true);
        fetPort.setEnabled(true);
        btnConnect.setEnabled(true);
        btnDisconnect.setEnabled(false);
    }
    
    private static void deactivatePanelConnection() {
        lblIP.setEnabled(false);
        lblPort.setEnabled(false);
        etIP.setEnabled(false);
        fetPort.setEnabled(false);
        btnConnect.setEnabled(false);
        btnDisconnect.setEnabled(true);
    }
    
    public static void deactivatePanelTransfer() {
        panelTransfer.setEnabled(false);
        
        etPath.setEnabled(false);
        btnChooser.setEnabled(false);
        listFiles.setEnabled(false);
        
        lblSelectFiles.setEnabled(false);
        lblPath.setEnabled(false);
        
        btnSend.setEnabled(false);
        btnClearTransfer.setEnabled(false);
        clearTransfer();
    }
    
    private void activatePanelTransfer() {
        panelTransfer.setEnabled(true);

        etPath.setEnabled(true);
        btnChooser.setEnabled(true);
        listFiles.setEnabled(true);

        lblSelectFiles.setEnabled(true);
        lblPath.setEnabled(true);

        btnSend.setEnabled(true);
        btnClearTransfer.setEnabled(true);
        
    }
    
    private void deactivatePanelResults() {
        panelResults.setEnabled(false);
        btnClearResults.setEnabled(false);
        txtArea.setEnabled(false);
    }
    
    private void activatePanelResults() {
        panelResults.setEnabled(true);
        btnClearResults.setEnabled(true);
        txtArea.setEnabled(true);
    }
    // </editor-fold>

    private void clearFields() {
        lblErrIP.setVisible(false);
        lblErrPort.setVisible(false);
        lblErrPath.setVisible(false);
        etIP.setText("");
        fetPort.setText("");
        clearTransfer();
        
        strResult = "";
        txtArea.setText(strResult);
    }
    
    private void configFileChooser() {
        openFileChooser = new JFileChooser();
        openFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    private void addFiles(File dir) {
        DefaultListModel model = new DefaultListModel();
        ArrayList<File> files = new ArrayList<>(Arrays.asList(dir.listFiles()));
        for (File f : files) {
            if (f.isFile()) {
                model.addElement(f.getName());
            }
        }
        listFiles.setModel(model);
    }

    private static void clearTransfer() {
        etPath.setText("");
        clearFiles();
        lblErrPath.setVisible(false);
        lblErrFiles.setVisible(false);
    }

    private static void clearFiles() {
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        listFiles.setModel(model);
    }

    private void connect() {
        if (!fetPort.getText().isEmpty()) {
            lblErrPort.setVisible(false);
            
            if (asServer){
                if (ts != null) {
                    ts = null; 
                }
                ts = new TransferServer(Integer.parseInt(fetPort.getText()));
                ts.start();


                // Deactivate some panels
                deactivatePanelRole();
                deactivatePanelConnection();

                // Enable some components of panelTransfer
                panelTransfer.setEnabled(true);
                lblPath.setEnabled(true);
                etPath.setEnabled(true);
                btnChooser.setEnabled(true);
                btnSend.setEnabled(true);
                btnClearTransfer.setEnabled(true);

                btnChooser.requestFocus();
//                } catch (IOException ex) {
//                    strResult += "Status: Couldn't connect with that port. Try a different one.\n";
//                    txtArea.setText(strResult);
//                }
            } else {
                if (!etIP.getText().isEmpty()){
                    lblErrIP.setVisible(false);
                    try {
                        cm = new ClientManager(etIP.getText(), Integer.parseInt(fetPort.getText()));
                        
                        deactivatePanelRole();
                        deactivatePanelConnection();
                        activatePanelTransfer();
                        activatePanelResults();
                        
                        etPath.requestFocus();
                        
                    } catch (IOException ex) {
                        strResult += "Status: Couldn't connect. Check whether the IP and the port are corrects.\n";
                        txtArea.setText(strResult);
                    }
                } else {
                    lblErrIP.setVisible(true);
                }
            }
        } else {
            lblErrPort.setVisible(true);
        }
    }
}
