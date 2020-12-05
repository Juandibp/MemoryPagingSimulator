/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.GlobalConfig;
import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
import com.itcr.memorypagingsimulator.algorithms.models.Pages;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import java.util.ArrayList;

/**
 *
 * @author lopez
 */
public class AlgorithmController {
    
    public Pages pages;
    public Frames frames;
    public ReplacementPolicy replacementPolicy;
    public FetchPolicy fetchPolicy;
    public ArrayList<Process> processes;
    public GlobalConfig conf;
    int pageIdCount = 0;
    int pageFaultCount = 0;

    public AlgorithmController(ArrayList<Process> processes, GlobalConfig conf) {
        this.processes = processes;
        this.conf = conf;
        this.setConfig();
    }
    
    
    public void reference(int processId, int pageReferenced){

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
    
    public void addProcess(Process process) throws InsuficientMemoryException, LoadControlExcededException {
        boolean loadControlExceded = false;
        Process removedProcess = null;
        if(processes.size() > conf.loadControl){
            loadControlExceded = true;
            if(process.getPriority() <= processes.get(0).getPriority()){         //processes of same priority follow find it keep it
                throw new LoadControlExcededException("Se superó el nivel de multiprogramación. Algunos procesos no serán admitidos.");
            }
            removedProcess = processes.remove(0);
        }
        int pageCount = 0;
        for(Process p : this.processes){
            pageCount += p.getPagesRequired();
        }
        pageCount += process.getPagesRequired();
        if(pageCount > conf.secondaryMemoryPages){
            if(removedProcess != null){
                this.processes.add(0, removedProcess);
            }
            throw new InsuficientMemoryException("No hay suficiente memoria para este proceso: " + process.getId());
        }
        this.processes.add(process);
        //sort processes by priority
        this.processes.sort((a,b) -> a.getPriority() - b.getPriority());
        if(loadControlExceded) 
            throw new LoadControlExcededException("Se superó el nivel de multiprogramación. Algunos procesos no serán admitidos.");
    }
    
    public void allocatePages() {
        for(Process process: processes){
            ArrayList<Page> pageTable = new ArrayList<>();
            for(int i = 0; i<process.getPagesRequired() ; i++ ){
                Page newPage = new Page(this.pageIdCount, this.pageIdCount * this.conf.pageSize, this.conf.pageSize);
                pageTable.add(newPage);
                this.pages.addPage(newPage);
                this.pageIdCount += 1;
            }
            process.setPageTable(pageTable);
        }
    }
    
    //setting up stuff all the way down
    
    public void setConfig() {
        this.pages = new Pages(conf.secondaryMemoryPages);
        this.frames = new Frames(conf.primaryMemoryFrames);
        this.processes = new ArrayList<>();
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
                this.fetchPolicy = new DemandFetchPolicy();
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
         
    //exceptions
    public class InsuficientMemoryException extends Exception {
        public InsuficientMemoryException(String message){ super(message); }
    }
    
    public class LoadControlExcededException extends Exception {
        public LoadControlExcededException(String message){ super(message); }
    }
}
