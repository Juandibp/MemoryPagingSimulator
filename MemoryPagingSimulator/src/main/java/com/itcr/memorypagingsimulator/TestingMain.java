/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator;

import com.itcr.memorypagingsimulator.algorithms.*;
import java.util.ArrayList;
import com.itcr.memorypagingsimulator.algorithms.models.Process;

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
        new TestingMain().execTest();
    }
    
    public void execTest(){
        this.repPolicy = new FIFOPageReplacement();
        this.processes.add(new Process(0, 20, 2));
        this.processes.add(new Process(1, 10, 2));
        this.processes.add(new Process(2, 15, 5));
        this.processes.add(new Process(3, 3, 0));
        this.processes.add(new Process(4, 7, 3));
        this.algController = new AlgorithmController(this.processes, conf);
        this.repPolicy.setParams(new int[] {5,3,4,8,4,2}, 10);
    }
}
