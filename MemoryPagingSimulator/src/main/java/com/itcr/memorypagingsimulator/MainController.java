/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator;

import com.formdev.flatlaf.FlatDarkLaf;
import com.itcr.memorypagingsimulator.algorithms.AlgorithmController;
import com.itcr.memorypagingsimulator.algorithms.FetchPolicy;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import java.awt.Color;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author juand
 */
public class MainController {

    private MainWindow mainWindow;
    private ConfigDialog configDialog;
    private AlgorithmController algorithController;
    private ProcessFileChooserDialog processFileChooserDialog;
    private ReferenceFileChooserDialog referenceFileChooserDialog;
    private GlobalConfig conf;
    private int currentReferenceIndex = 0;
    private boolean processFileLoaded = false;
    private boolean referenceFileLoaded = false;
    
    public MainController(){
        this.conf = new GlobalConfig();
        this.mainWindow = new MainWindow(this);
        this.configDialog = new ConfigDialog(this.mainWindow, true, conf);
        this.processFileChooserDialog = new ProcessFileChooserDialog(this.mainWindow, true, this);
        this.referenceFileChooserDialog = new ReferenceFileChooserDialog(this.mainWindow, true, conf);
    }
    
    public static void main(String args[]) {        
        FlatDarkLaf.install();
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIDefaults uiDefaults = UIManager.getDefaults();
            uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.gray));
            uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainController().startUI();
            }
        });
    }
    
    public void startUI(){
        this.mainWindow.setVisible(true);
        this.openConfig();
    }
    
    public void openConfig(){
        this.configDialog.setVisible(true);
    }
    
    public void openProcessLoader(){
        this.processFileChooserDialog.setVisible(true);
    }
    
    public void openReferenceLoader(){
        this.referenceFileChooserDialog.setVisible(true);
    }

    public GlobalConfig getConf() {
        return conf;
    }
    
    public void updateConfig(GlobalConfig newConfig){
        this.conf = newConfig;
    }
    
    public void loadProcesses(ArrayList<Process> processes){
        this.algorithController = new AlgorithmController(new ArrayList<>(), conf);
        boolean hasShownError = false;
        for(Process p : processes){
            try {
                this.algorithController.addProcess(p);
            } catch (AlgorithmController.InsuficientMemoryException ex) {
                if(!hasShownError){
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
                    hasShownError = true;
                }
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AlgorithmController.LoadControlExcededException ex) {
                if(!hasShownError){
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
                    hasShownError = true;
                }                
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.algorithController.allocatePages();
    }
    
    public void printConfig(){
        System.err.println("this.conf.toString(): " + this.conf.toString());
    }
    
    public void executeReferences(){
        //TODO check if stuff is loaded
        System.out.println(this.algorithController);
        
        this.referenceFileChooserDialog.getReferences().forEach(ref -> {
            try {
                this.algorithController.reference(ref.getValue0(), ref.getValue1(), ref.getValue2());
            } catch (AlgorithmController.InvalidProcessIdException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FetchPolicy.IllegalReferenceException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.displaymemory();
    }

    public void executeReferences(int until){
        
    }
    
    public void displaymemory(){
        new MemoryStateDialog(this.mainWindow, false, this.algorithController.frames).setVisible(true);
        new MemoryStateDialog(this.mainWindow, false, this.algorithController.pages).setVisible(true);
    }
}
