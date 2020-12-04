/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.GlobalConfig;
import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Pages;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import java.util.ArrayList;

/**
 *
 * @author lopez
 */
public class AlgorithmController {
    
    Pages pages;
    Frames frames;
    ReplacementPolicy replacementPolicy;
    ArrayList<Process> processes;
    GlobalConfig conf;
    int pageFaultCount = 0;

    public AlgorithmController(ArrayList<Process> processes, GlobalConfig conf) {
        this.processes = processes;
        this.conf = conf;
        this.setConfig();
    }
    
    
    public void reference(int processId, int pageReferenced){
        //check loadControl and block process if necesary
        //  return
        //fetch the page/pages with corresponding policy
        //  fetch should check if said page can be broud by this process
        //  fetch should check first if pages already in memory
        //  if pages where brought then page fault ocurred
        //  therefore call placement policy
        //placementPolicy should return List<Page> or null,
        //  if returned null empty the returned processes 
        //  should replace others
        //replacementPolicy has to receive as parameters
        //  the pages to place, replacement scope, resident set size
        //  and cleaning policy as well (might not be called)
        //  returns nothing, always does its job
    }
    
    //setting up stuff all the way down
    
    public void setConfig() {
        this.setFetchPolicy();
        this.setPlacementPolicy();
        this.setReplacementPolicy();
        this.setResidentSetSize();
        this.setReplacementScope();
        this.setCleaningPolicy();
    }

    public void setConfig(GlobalConfig conf) {
        this.conf = conf;
        this.setConfig();
    }

    
    public void setFetchPolicy(){
        switch (this.conf.fetchPolicy){
            case DEMAND:
                break;
            case PRE_PAGING:
                break;
        }
    }
    
    public void setPlacementPolicy() {
        switch (this.conf.placementPolicy){
            case FIRST_AVAILABLE:
                break;
            case NEXT_AVAILABLE:
                break;
        }
    }    
    
    public void setReplacementPolicy() {
        switch (this.conf.replacementPolicy){
            case LRU:
                this.replacementPolicy = new LRUPageReplacement();
                break;
            case FIFO : 
                this.replacementPolicy = new FIFOPageReplacement();
                break;
            case LFU:
                this.replacementPolicy = new LFUPageReplacement();
                break;
            case MRU:
                this.replacementPolicy = new MRUPageReplacement();
                break;
            default: //SECOND_CHANCE
                this.replacementPolicy = new SecondChancePageReplacement();
                break;
        }
    }
    
    public void setResidentSetSize() {
        switch (this.conf.residentSetSize){
            case FIXED:
                break;
            case VARIABLE:
                break;
        }
    }
    
    public void setReplacementScope() {
        switch (this.conf.replacementScope){
            case GLOBAL:
                break;
            case LOCAL:
                break;
        }
    }
    
    public void setCleaningPolicy() {
        switch(this.conf.cleaningPolicy) {
            case DEMAND:
                break;
            case PRE_CLEANING:
                break;
        }
    }
         
}
