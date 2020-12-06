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
import java.util.ArrayList;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            ArrayList<Process> processes = new ArrayList<>();
            Frames frames = new Frames(10);
            Pages pages = new Pages(50);
            AlgorithmController testAlg = new AlgorithmController(processes, this.conf);
            Process testProcess = new Process(2, 15, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 5);
            testAlg.addProcess(new Process(0, 20, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 2));
            testAlg.addProcess(new Process(1, 10, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 2));
            testAlg.addProcess(testProcess);
            testAlg.addProcess(new Process(3, 3, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 0));
            testAlg.addProcess(new Process(4, 7, conf.varResSetSizeUpperLimit, conf.varResSetSizeLowerLimit, 3));           
            testAlg.allocatePages();
            System.out.println("Config: " + this.conf);
            System.out.println("Page: " + testProcess.getPageTable());
            ArrayList<Page> fetchedPages = new DemandFetchPolicy().fetch(testAlg.pages, testAlg.frames, testProcess, 0);
            System.out.println("Fetched the pages "+fetchedPages);
        } catch (AlgorithmController.InsuficientMemoryException ex) {
            ex.printStackTrace();
        } catch (FetchPolicy.IllegalReferenceException ex) {
            ex.printStackTrace();
        } catch (AlgorithmController.LoadControlExcededException ex) {
            ex.printStackTrace();
        }
    }
}
