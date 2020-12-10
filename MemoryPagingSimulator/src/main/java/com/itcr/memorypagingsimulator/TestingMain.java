/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator;

import com.itcr.memorypagingsimulator.algorithms.*;
import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
import com.itcr.memorypagingsimulator.algorithms.models.Pages;
import com.itcr.memorypagingsimulator.algorithms.models.PagesPlaceReplace;
import java.util.ArrayList;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author lopez
 */
public class TestingMain {

    GlobalConfig conf = new GlobalConfig();
    ArrayList<Process> processes = new ArrayList<>();
    ReplacementPolicy repPolicy;
    AlgorithmController algController; 
    
    public static void main(String[] args){
        new TestingMain().execTest(args);
    }
    
    public void execTest(String[] args){
        this.repPolicy = new FIFOPageReplacement();
        this.processes.add(new Process(0, 20, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 3));
        this.processes.add(new Process(1, 10, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 2));
        this.processes.add(new Process(2, 15, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 5));
        this.processes.add(new Process(3, 3, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 0));
        this.processes.add(new Process(4, 7, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 3));
        this.algController = new AlgorithmController(this.processes, conf);
        if(args.length != 0)
            if(args[0].equals("gTest"))
                runGtest();
    }
    
    public void runGtest(){
        try {
//            this.conf.replacementScope = GlobalConfig.ReplacementScopeSetting.LOCAL;
            this.conf.primaryMemoryFrames = 10;
            this.conf.secondaryMemoryPages = 50;
            this.conf.placementPolicy = GlobalConfig.PlacementPolicySetting.NEXT_AVAILABLE;
            ArrayList<Process> processes = new ArrayList<>();
            AlgorithmController testAlg = new AlgorithmController(processes, this.conf);
            
            testAlg.addProcess(new Process(0, 15, 2, conf.varResSetSizeLowerLimit, 2));
            testAlg.addProcess(new Process(1, 5, 2, conf.varResSetSizeLowerLimit, 2));
            Process testProcess = new Process(2, 20, 3, conf.varResSetSizeLowerLimit, 5);
            testAlg.addProcess(testProcess);
            testAlg.addProcess(new Process(3, 3, 1, conf.varResSetSizeLowerLimit, 0));
            testAlg.addProcess(new Process(4, 7, 2, conf.varResSetSizeLowerLimit, 3));           
            testAlg.allocatePages();
            Frames frames = testAlg.frames;
            Pages pages = testAlg.pages;
            
            System.out.println(testProcess);
            System.out.println("Config: " + this.conf);
            System.out.println("Test process Pages: " + testProcess.getPageTable());     
            System.out.println(this.processes);
            
            System.out.println("--------------------- FIRST REFERENCE -------------------");
            testAlg.reference(0, 0, true);
            testAlg.reference(1, 0, false);
            testAlg.reference(2, 0, true);
            testAlg.reference(3, 0, false);
            testAlg.reference(2, 1, false);
            testAlg.reference(2, 2, true);
            testAlg.reference(2, 3, true);
            
            System.out.println("Frames state: " + frames);
            System.out.println("2's page ids: " + testProcess.getPageTable());
            
            
            
        } catch (AlgorithmController.InsuficientMemoryException ex) {
            ex.printStackTrace();
        } catch (FetchPolicy.IllegalReferenceException ex) {
            ex.printStackTrace();
        } catch (AlgorithmController.LoadControlExcededException ex) {
            ex.printStackTrace();
        } catch (AlgorithmController.InvalidProcessIdException ex) {
            Logger.getLogger(TestingMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
