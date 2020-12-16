/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator;

import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
import com.itcr.memorypagingsimulator.algorithms.models.Pages;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author lopez
 */
public class MemoryStateDialog extends javax.swing.JDialog {

    private Frames frames;
    private Pages pages;
    /**
     * Creates new form MemoryStateDialog
     */
    
    public MemoryStateDialog(java.awt.Frame parent, boolean modal, boolean isMainMemory){
        super(parent, modal);
        initComponents();
        this.setTitle(isMainMemory ? "Memoria principal" : "Memoria secundaria" );
    }
    
    public MemoryStateDialog(java.awt.Frame parent, boolean modal, Frames frames) {
        super(parent, modal);
        initComponents();
        this.frames = frames;
        this.displayMemory(frames);
        this.setTitle("Memoria principal");
    }
    
    public MemoryStateDialog(java.awt.Frame parent, boolean modal, Pages frames) {
        super(parent, modal);
        initComponents();
        this.pages = frames;
        this.displayMemory(frames);
        this.setTitle("Memoria secundaria");
    }
    
    public void displayMemory(Pages frames){
        this.pages = frames;
        String[] columns = new String [] {"Nº", "Page ID", "References", "Is dirty"};
        List<Object[]> ls = new ArrayList<>();
        for(int i = 0 ; i < pages.getPageList().size() ; i++){
            Page p = pages.getPageList().get(i);
            if( p != null)
                ls.add(new Object[] {i, p.getId(), p.getReferenceCounter(), p.isDirty()});
            else
                ls.add(new Object[] {i, "-", "-", "-"});
        }
        this.memorytable.setModel(new JTable(ls.toArray(new Object[0][0]), columns).getModel());
    }
    
    public void displayMemory(Frames frames){
        this.frames = frames;
        String[] columns = new String [] {"Frame", "Page ID", "References", "Is dirty"};
        List<Object[]> ls = new ArrayList<>();
        for(int i = 0 ; i < frames.getFrames().size() ; i++){
            Page p = frames.getFrames().get(i);
            if( p != null)
                ls.add(new Object[] {i, p.getId(), p.getReferenceCounter(), p.isDirty()});
            else
                ls.add(new Object[] {"-", "-", "-", "-"});
        }
        this.memorytable.setModel(new JTable(ls.toArray(new Object[0][0]), columns).getModel());
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
        jScrollPane2 = new javax.swing.JScrollPane();
        memorytable = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estado de memoria");

        memorytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(memorytable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
//            java.util.logging.Logger.getLogger(MemoryStateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MemoryStateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MemoryStateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MemoryStateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                MemoryStateDialog dialog = new MemoryStateDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable memorytable;
    // End of variables declaration//GEN-END:variables
}
