/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
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
    private GlobalConfig conf;
    
    public MainController(){
        this.conf = new GlobalConfig();
        this.mainWindow = new MainWindow(this);
        this.configDialog = new ConfigDialog(this.mainWindow, true, conf);
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
    
    public void updateConfig(GlobalConfig newConfig){
        this.conf = newConfig;
    }
    
    public void printConfig(){
        System.err.println("this.conf.toString(): " + this.conf.toString());
    }

}
