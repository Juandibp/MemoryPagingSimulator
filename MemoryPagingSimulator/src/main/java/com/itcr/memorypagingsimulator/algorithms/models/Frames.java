/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.javatuples.Pair;

/**
 * Represents main memory
 * 
 * @author juand
 */
public class Frames implements Iterable<Page>{
    
    /** Los numeros son los ids de las paginas */
    private List<Page> frames;
    private List<Pair<Integer, Integer>> referenceTimes;
    private int timer = 0;
    
    public Frames(int numberOfFrames) {
        this.frames = new ArrayList<>(numberOfFrames);
        this.referenceTimes = new ArrayList<>(numberOfFrames);
        
        for (int i = 0; i < numberOfFrames; i++) {
            frames.add(null);
            referenceTimes.add(null);
        }
    }
    
    public void deallocateFrames() {
        int len = frames.size();
        for (int i = 0; i < len; i++) {
                frames.set(i, null);
        }
    }
    
    public boolean contains(int reference) {
        return indexOf(reference) > -1;
    }

    public int indexOf(int reference) {
        return frames.indexOf(reference);
    }
    
    public boolean thereIsAnEmptyFrame() {
        return contains(-1);
    }

    public void copyAll(Frames f) {
        frames.clear();
        frames = new ArrayList<>(f.frames);
    }

    public int getEmptyFrame() {
        return indexOf(-1);
    }
    
    public void placePage(Page p, int index){
        this.frames.set(index, p);
        this.referenceTimes.set(index, Pair.with(index, timer));
        timer++;
    }
    
    public void reference(Page p){
        this.referenceTimes.set(p.getId(), Pair.with(p.getId(), timer));
        timer++;
    }

    public void reference(int pageId){
        this.referenceTimes.set(pageId, Pair.with(pageId, timer));
        timer++;
    }

    public List<Pair<Integer, Integer>> getReferenceTimes() {
        return referenceTimes;
    }

    @Override
    public Iterator<Page> iterator() {
        return frames.iterator();
    }

    public List<Page> getFrames() {
        return frames;
    }

    @Override
    public String toString() {
        StringBuilder retVal= new StringBuilder("Frames{referenceTimes=" + referenceTimes + ", timer=" + timer +", frames={");
        for(Page p:this.frames){
            retVal.append("\n\t"+p+",");
        }
        retVal.append("}}");
        return retVal.toString();
    }
    
    

}
