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

    public AlgorithmController(ArrayList<Process> processes, GlobalConfig conf) {
        this.processes = processes;
        this.conf = conf;
        this.setConfig();
    }
    
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
                break;
            case FIFO : 
                this.replacementPolicy = new FIFOPageReplacement();
                break;
            case LFU:
                break;
            case MRU:
                break;
            default: //SECOND_CHANCE
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
