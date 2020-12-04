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
    private ArrayList<Page> pageTable;
    private int priority;
    
    //al proceso le toca traducir la direccion... 
    //el proceso maneja internamente ids de pagina segun el indice en su tabla

    public Process(int id, int pagesRequired, int priority) {
        this.id = id;
        this.pageTable = new ArrayList<>(pagesRequired);
        this.pagesRequired = pagesRequired;
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

    public ArrayList<Page> getPageTable() {
        return pageTable;
    }

    public void setPageTable(ArrayList<Page> pageTable) {
        this.pageTable = pageTable;
    }

    @Override
    public String toString() {
        return "Process{" + "id=" + id + ", pagesRequired=" + pagesRequired + ", pageTable=" + pageTable + ", priority=" + priority + '}';
    }
    
}
