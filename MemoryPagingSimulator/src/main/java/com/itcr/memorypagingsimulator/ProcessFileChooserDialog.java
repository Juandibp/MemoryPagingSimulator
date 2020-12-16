/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author lopez
 */
public class ProcessFileChooserDialog extends javax.swing.JDialog {
    
    private Dimension initialSize;
    private File loadedFile;
    private ArrayList<Process> loadedProcesses;
    private MainController controller;
    private GlobalConfig conf;

    /**
     * Creates new form ProcessFileChooserDialog
     */
    public ProcessFileChooserDialog(java.awt.Frame parent, boolean modal, MainController controller) {
        super(parent, modal);
        initComponents();
        this.initialSize = this.getSize();
        this.loadedProcesses = new ArrayList<>();
        this.conf = controller.getConf();
        this.controller = controller;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        processFileChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select process definition file");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        processFileChooser.setDialogType(javax.swing.JFileChooser.CUSTOM_DIALOG);
        processFileChooser.setApproveButtonText("Load process definition file");
        processFileChooser.setApproveButtonToolTipText("");
        processFileChooser.setDialogTitle("");
        processFileChooser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        processFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processFileChooserActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Select the file that contains the processes definition:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(processFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(processFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void processFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processFileChooserActionPerformed
        this.loadedFile = this.processFileChooser.getSelectedFile();
        if(!this.loadedProcesses.isEmpty() 
                && javax.swing.JOptionPane.showConfirmDialog(null, "You already have processses loaded. Loading a new file will reset the program's state. Do you want to proceed?") == 0){
            this.loadedProcesses.clear();
            this.readProcesses();        
        } else if (this.loadedProcesses.isEmpty()) {
            this.readProcesses();
        }
    }//GEN-LAST:event_processFileChooserActionPerformed

    public void readProcesses() {
        try {
            this.loadedProcesses = new ArrayList<>();
            FileReader reader = new FileReader(this.loadedFile);
            BufferedReader buffedReader = new BufferedReader(reader);
            boolean varSetSize = conf.residentSetSize == GlobalConfig.ResidentSetSizeSetting.VARIABLE;
            for(String line = buffedReader.readLine(); line != null ; line = buffedReader.readLine()){
                if(line.startsWith("--")) continue;
                String[] values = line.split(",");
//                int id, int pagesRequired, int frameSpace, int frameSpaceLowerLimit, int priority
                if(varSetSize){
                    loadedProcesses.add(new Process(
                            Integer.parseInt(values[0].strip()),
                            Integer.parseInt(values[1].strip()),
                            conf.varResSetSizeUpperLimit,
                            conf.varResSetSizeLowerLimit,
                            Integer.parseInt(values[2].strip())
                    ));
                } else {
                    loadedProcesses.add(new Process(
                            Integer.parseInt(values[0].strip()),
                            Integer.parseInt(values[1].strip()),
                            Integer.parseInt(values[2].strip()),
                            conf.varResSetSizeLowerLimit,
                            Integer.parseInt(values[3].strip())
                    ));
                }
            }
            
            System.out.println(this.loadedProcesses);
            this.setVisible(false);
            this.controller.loadProcesses(loadedProcesses);

        } catch(FileNotFoundException ex) {
            showMessageDialog(null, "No existe el archivo seleccionado. Intente de nuevo.", "Error de archivo", javax.swing.JOptionPane.ERROR_MESSAGE);
            this.loadedFile = null;
        } catch(IndexOutOfBoundsException ex) {
            showMessageDialog(null, "El formato de archivo es incorrecto. Por favor reviselo segun la guia.", "Error de archivo", javax.swing.JOptionPane.ERROR_MESSAGE);
            this.loadedFile = null;
        } catch (IOException ex) {
            showMessageDialog(null, "Ocurrio un error de lectura. Puede que algunos procesos se hayan cargado.", "Error de archivo", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex){
            showMessageDialog(null, "El formato de archivo es incorrecto. Por favor reviselo segun la guia.", "Error de archivo", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<Process> getLoadedProcesses() {
        return this.loadedProcesses;
    }
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if(this.initialSize != null) this.setSize(initialSize);
    }//GEN-LAST:event_formComponentResized

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ProcessFileChooserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ProcessFileChooserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ProcessFileChooserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ProcessFileChooserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ProcessFileChooserDialog dialog = new ProcessFileChooserDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFileChooser processFileChooser;
    // End of variables declaration//GEN-END:variables
}
