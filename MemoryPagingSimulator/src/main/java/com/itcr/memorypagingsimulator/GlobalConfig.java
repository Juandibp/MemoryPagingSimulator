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
    int loadControl;

    public GlobalConfig(
            FetchPolicySetting fetchPolicy, 
            PlacementPolicySetting placementPolicy, 
            ReplacementPolicySetting replacementPolicy, 
            ResidentSetSizeSetting residentSetSize, 
            ReplacementScopeSetting ReplacementScope, 
            int loadControl) {
        this.fetchPolicy = fetchPolicy;
        this.placementPolicy = placementPolicy;
        this.replacementPolicy = replacementPolicy;
        this.residentSetSize = residentSetSize;
        this.replacementScope = ReplacementScope;
        this.loadControl = loadControl;
    }
    
    public GlobalConfig() {
        this(
                FetchPolicySetting.DEMAND,
                PlacementPolicySetting.FIRST_AVAILABLE,
                ReplacementPolicySetting.FIFO,
                ResidentSetSizeSetting.FIXED,
                ReplacementScopeSetting.GLOBAL,
                5
        );
    }
    
    public GlobalConfig cloneConfig(){
        return new GlobalConfig(
            this.fetchPolicy,
            this.placementPolicy,
            this.replacementPolicy,
            this.residentSetSize ,
            this.replacementScope,
            this.loadControl
        );
    }
    
    
    
    
        
}