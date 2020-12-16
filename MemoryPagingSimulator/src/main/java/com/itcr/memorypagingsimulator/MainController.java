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
import static javax.swing.JOptionPane.showMessageDialog;
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
    private MemoryStateDialog mainMemoryDisplay;
    private MemoryStateDialog secondaryMemoryDisplay;
    private ProcessDisplayDialog processDisplay;
    
    public MainController(){
        this.conf = new GlobalConfig();
        this.mainWindow = new MainWindow(this);
        this.configDialog = new ConfigDialog(this.mainWindow, true, conf);
        this.processFileChooserDialog = new ProcessFileChooserDialog(this.mainWindow, true, this);
        this.referenceFileChooserDialog = new ReferenceFileChooserDialog(this.mainWindow, true, conf);
        this.mainMemoryDisplay = new MemoryStateDialog(this.mainWindow, false, true);
        this.secondaryMemoryDisplay = new MemoryStateDialog(this.mainWindow, false, false);
        this.processDisplay = new ProcessDisplayDialog(this.mainWindow, false);
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
        this.resetState();
    }
    
    public void loadProcesses(ArrayList<Process> processes){
        boolean hasShownError = false;
        try{
            this.algorithController = new AlgorithmController(new ArrayList<>(), conf);
        }catch( IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
            this.openConfig();
            this.loadProcesses(processes);
            return;
        }
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
        showMessageDialog(null, "Processes loaded.");
    }
    
    public void printConfig(){
        System.err.println("this.conf.toString(): " + this.conf.toString());
    }
    
    public void executeReferences(){
        if(!canRead()) return;
        if(this.currentReferenceIndex == 0){
            this.resetState();
        }
        var references = this.referenceFileChooserDialog.getReferences();
        for(int i = this.currentReferenceIndex; 
                i < references.size();  
                i++){
            var ref = references.get(i);
            try {
                this.algorithController.reference(ref.getValue0(), ref.getValue1(), ref.getValue2());
            } catch (AlgorithmController.InvalidProcessIdException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FetchPolicy.IllegalReferenceException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.currentReferenceIndex = 0;
        this.displaymemory();
    }

    public void executeReferences(int until){
        if(!canRead()) return;
        if(this.currentReferenceIndex == 0){
            this.resetState();
        }
        var references = this.referenceFileChooserDialog.getReferences();
        for(int i = this.currentReferenceIndex; 
                i < this.currentReferenceIndex + until && i < references.size();  
                i++){
            var ref = references.get(i);
            try {
                this.algorithController.reference(ref.getValue0(), ref.getValue1(), ref.getValue2());
            } catch (AlgorithmController.InvalidProcessIdException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FetchPolicy.IllegalReferenceException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.currentReferenceIndex += until;
        this.displaymemory();
        if(this.currentReferenceIndex >= references.size()){
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Already read all references in the loaded file. Next time you try it will start right at the beginning.", 
                "Warning",javax.swing.JOptionPane.INFORMATION_MESSAGE );
            this.currentReferenceIndex = 0;
        }
    }
    
    public boolean canRead() {
        boolean processesEmpty = this.algorithController == null || this.algorithController.processes.isEmpty();
        boolean referencesEmpty= this.referenceFileChooserDialog.getReferences().isEmpty();
        if(!processesEmpty
                 && !referencesEmpty)
            return true;
        javax.swing.JOptionPane.showMessageDialog(null, 
                "You need to load "+(processesEmpty? "the processes definition file " : "" )
                        + (processesEmpty && referencesEmpty ?"and " :"")
                        + (referencesEmpty ?"the references definition file ":"")
                        + "in order to proceed. Please load the files required."
                , "Warning",javax.swing.JOptionPane.WARNING_MESSAGE );
        return false;
    }
    
    public void displaymemory(){
        this.mainMemoryDisplay.displayMemory(this.algorithController.frames);
        this.secondaryMemoryDisplay.displayMemory(this.algorithController.pages);
        this.mainMemoryDisplay.setVisible(true);
        this.secondaryMemoryDisplay.setVisible(true);
    }
    
    public void resetState(){
        this.currentReferenceIndex = 0;
        this.algorithController = null;
        this.processFileChooserDialog.readProcesses();
    }
    
    public void displayProcess(int id){
        if(this.algorithController == null || this.algorithController.processes.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "There aren't processes loaded right now. Try loading a process definition file.", 
                "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
            return;
        };
        Process p = this.algorithController.processes.stream().filter(p1 -> p1.getId() == id).findFirst().orElse(null);
        if(p != null){
            this.processDisplay.setProcess(p, this.conf.residentSetSize == GlobalConfig.ResidentSetSizeSetting.FIXED);
            this.processDisplay.setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "There's no process with the specified id. Please try again.", 
                "Error",javax.swing.JOptionPane.ERROR_MESSAGE );
        }
    }
}
