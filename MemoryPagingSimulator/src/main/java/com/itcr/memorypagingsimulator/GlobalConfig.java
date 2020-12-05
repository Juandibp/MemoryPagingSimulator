/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator;

/**
 *
 * @author lopez
 */
public class GlobalConfig {
    
    public enum FetchPolicySetting {DEMAND, PRE_PAGING}
    public enum PlacementPolicySetting {FIRST_AVAILABLE, NEXT_AVAILABLE}
    public enum ReplacementPolicySetting {LRU, FIFO, LFU, MRU,SECOND_CHANCE}
    public enum ResidentSetSizeSetting {FIXED, VARIABLE}
    public enum ReplacementScopeSetting {GLOBAL, LOCAL}
    public enum CleaningPolicySetting {DEMAND, PRE_CLEANING}
    
    /***/
    public FetchPolicySetting fetchPolicy;
    /***/
    public PlacementPolicySetting placementPolicy;
    /***/
    public ReplacementPolicySetting replacementPolicy;
    /***/
    public ResidentSetSizeSetting residentSetSize;
    /***/
    public int varResSetSizeLowerLimit;
    /***/
    public int varResSetSizeUpperLimit;
    /***/
    public ReplacementScopeSetting replacementScope;
    /***/
    public CleaningPolicySetting cleaningPolicy; 
    /** Amount in processes that can be in main memory at the same time. */
    public int loadControl;
    /** How many addresses can be referenced in a page */
    public int pageSize;
    /** Amount of frames in main memory */
    public int primaryMemoryFrames;
    /** Amount of pages that can exist in total */
    public int secondaryMemoryPages;

    public GlobalConfig(
            FetchPolicySetting fetchPolicy, 
            PlacementPolicySetting placementPolicy, 
            ReplacementPolicySetting replacementPolicy, 
            ResidentSetSizeSetting residentSetSize, 
            int varResSetSizeLowerLimit, 
            int varResSetSizeUpperLimit, 
            ReplacementScopeSetting replacementScope, 
            CleaningPolicySetting cleaningPolicy, 
            int loadControl, 
            int pageSize, 
            int primaryMemoryFrames, 
            int secondaryMemoryPages) {
        this.fetchPolicy = fetchPolicy;
        this.placementPolicy = placementPolicy;
        this.replacementPolicy = replacementPolicy;
        this.residentSetSize = residentSetSize;
        this.varResSetSizeLowerLimit = varResSetSizeLowerLimit;
        this.varResSetSizeUpperLimit = varResSetSizeUpperLimit;
        this.replacementScope = replacementScope;
        this.cleaningPolicy = cleaningPolicy;
        this.loadControl = loadControl;
        this.pageSize = pageSize;
        this.primaryMemoryFrames = primaryMemoryFrames;
        this.secondaryMemoryPages = secondaryMemoryPages;
    }


    
    public GlobalConfig() {
        this(
                FetchPolicySetting.DEMAND,
                PlacementPolicySetting.FIRST_AVAILABLE,
                ReplacementPolicySetting.FIFO,
                ResidentSetSizeSetting.FIXED,
                3,
                20,
                ReplacementScopeSetting.GLOBAL,
                CleaningPolicySetting.DEMAND,
                5,
                2000,
                500,
                3000
        );
    }
    
    public GlobalConfig cloneConfig(){
        return new GlobalConfig(
            this.fetchPolicy,
            this.placementPolicy,
            this.replacementPolicy,
            this.residentSetSize ,
            this.varResSetSizeLowerLimit,
            this.varResSetSizeUpperLimit,
            this.replacementScope,
            this.cleaningPolicy,
            this.loadControl,
            this.pageSize,
            this.primaryMemoryFrames,
            this.secondaryMemoryPages
        );
    }
    
    public void setConfig(GlobalConfig c){
        this.fetchPolicy = c.fetchPolicy;
        this.placementPolicy = c.placementPolicy;
        this.replacementPolicy = c.replacementPolicy;
        this.residentSetSize = c.residentSetSize;
        this.varResSetSizeLowerLimit = c.varResSetSizeLowerLimit;
        this.varResSetSizeUpperLimit = c.varResSetSizeUpperLimit;
        this.replacementScope = c.replacementScope;
        this.cleaningPolicy = c.cleaningPolicy;
        this.loadControl = c.loadControl;
        this.pageSize = c.pageSize;
        this.primaryMemoryFrames = c.primaryMemoryFrames;
        this.secondaryMemoryPages = c.secondaryMemoryPages;
    }

    @Override
    public String toString() {
        return "GlobalConfig{" + "fetchPolicy=" + fetchPolicy + ", placementPolicy=" + placementPolicy + ", replacementPolicy=" + replacementPolicy + ", residentSetSize=" + residentSetSize + ", replacementScope=" + replacementScope + ", cleaningPolicy=" + cleaningPolicy + ", loadControl=" + loadControl + ", pageSize=" + pageSize + ", primaryMemoryFrames=" + primaryMemoryFrames + ", secondaryMemoryPages=" + secondaryMemoryPages + '}';
    }
}