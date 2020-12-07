/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms.models;

import java.util.ArrayList;

/**
 *
 * @author lopez
 */
public class Process {
    
    private int id;
    private int pagesRequired;
    private int frameSpace;           //if variable acts as upper limit
    private int frameSpaceLowerLimit; //if fixed is equivalent to frameSpace
    private ArrayList<Integer> pageTable;
    private ArrayList<Page> pageList;
    private int priority;
    
    //el proceso maneja internamente ids de pagina segun el indice en su lista

    public Process(int id, int pagesRequired, int frameSpace, int frameSpaceLowerLimit, int priority) {
        this.id = id;
        this.pagesRequired = pagesRequired;
        this.frameSpace = frameSpace;
        this.frameSpaceLowerLimit = frameSpaceLowerLimit;
        this.pageTable = new ArrayList<>();
        for (int i = 0; i < pagesRequired; i++) {
            pageTable.add(null);
        }
        this.pageList = new ArrayList<>(pagesRequired);
        this.priority = priority;
    }
    
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPagesRequired() {
        return pagesRequired;
    }

    public void setPagesRequired(int pagesRequired) {
        this.pagesRequired = pagesRequired;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getPageTable() {
        return pageTable;
    }
    
    public void allocatePage(int localPageId, int mainMemoryLocation){
        this.pageTable.set(localPageId, mainMemoryLocation);
    }

    public void addPage(Page p, int index){
        this.pageList.set(index, p);
    }

    public ArrayList<Page> getPageList() {
        return pageList;
    }

    public void setPageList(ArrayList<Page> pageList) {
        this.pageList = pageList;
    }

    public int getFrameSpace() {
        return frameSpace;
    }

    public void setFrameSpace(int frameSpace) {
        this.frameSpace = frameSpace;
    }

    public int getFrameSpaceLowerLimit() {
        return frameSpaceLowerLimit;
    }

    public void setFrameSpaceLowerLimit(int frameSpaceLowerLimit) {
        this.frameSpaceLowerLimit = frameSpaceLowerLimit;
    }

    @Override
    public String toString() {
        return "Process{" + "id=" + id + ", pagesRequired=" + pagesRequired + ", frameSpace=" + frameSpace + ", frameSpaceLowerLimit=" + frameSpaceLowerLimit + ", pageTable=" + pageTable + ", pageList=" + pageList + ", priority=" + priority + '}';
    }
    
}
