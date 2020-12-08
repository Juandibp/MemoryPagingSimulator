/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.GlobalConfig;
import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import com.itcr.memorypagingsimulator.algorithms.models.Reference;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juand
 */
public abstract class ReplacementPolicy {
    protected List<Reference> references;
    protected List<Integer> refs;
    protected int faults;
    protected int frames;
    
    protected abstract void allocate();
    
    public abstract ArrayList<Page> replace(GlobalConfig conf, List<Page> pagesToPlace, Frames frames, Process processId);
    
    public List<Reference> call() throws Exception{
        return allocateReferences();
    }
    
    public int faults(){
        return faults;
    }
    public double faultRate() {
        return (faults * 1.0 / references.size() * 100);
    }

    public void clearStats() {
        faults = 0;
    }
    
    public void setParams(int[] references, int frames) {
        faults = 0;
        this.frames = frames;
        this.refs = new ArrayList<>(references.length);
        this.references = new ArrayList<Reference>(references.length);
        for (int i : references) {
            refs.add(i);
            this.references.add(new Reference(i, frames));
        }

        faults = 0;
    }

    public List<Reference> allocateReferences() {
        allocate();
        return references;
    }

}
