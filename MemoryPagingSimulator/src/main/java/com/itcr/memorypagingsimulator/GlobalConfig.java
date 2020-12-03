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
    
    enum FetchPolicySetting {DEMAND, PRE_PAGING}
    enum PlacementPolicySetting {FIRST_AVAILABLE, NEXT_AVAILABLE}
    enum ReplacementPolicySetting {LRU, FIFO, LFU, MRU,SECOND_CHANCE}
    enum ResidentSetSizeSetting {FIXED, VARIABLE}
    enum ReplacementScopeSetting {GLOBAL, LOCAL}
    enum CleaningPolicySetting {DEMAND, PRE_CLEANING}
    
    /***/
    FetchPolicySetting fetchPolicy;
    /***/
    PlacementPolicySetting placementPolicy;
    /***/
    ReplacementPolicySetting replacementPolicy;
    /***/
    ResidentSetSizeSetting residentSetSize;
    /***/
    ReplacementScopeSetting replacementScope;
    /***/
    CleaningPolicySetting cleaningPolicy; 
    /** Amount in processes that can be in main memory at the same time. */
    int loadControl;
    /** How many addresses can be referenced in a page */
    int pageSize;
    /** Amount of frames in main memory */
    int primaryMemoryFrames;
    /** Amount of pages that can exist in total */
    int secondaryMemoryPages;

    public GlobalConfig(
            FetchPolicySetting fetchPolicy, 
            PlacementPolicySetting placementPolicy, 
            ReplacementPolicySetting replacementPolicy,
            ResidentSetSizeSetting residentSetSize, 
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
            this.replacementScope,
            this.cleaningPolicy,
            this.loadControl,
            this.pageSize,
            this.primaryMemoryFrames,
            this.secondaryMemoryPages
        );
    }
    
    
    
    
        
}