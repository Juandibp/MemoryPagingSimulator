/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms.models;

/**
 *
 * @author juand
 */
class Page {
    
    private int id;
    private int basePointer;
    private int size;
    private boolean isAllocated = false;

    public Page(int id, int basePointer, int size) {
        this.id = id;
        this.basePointer = basePointer;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Page{" + "id=" + id + ", basePointer=" + basePointer + ", size=" + size + '}';
    }

    public boolean isIsAllocated() {
        return isAllocated;
    }

    public void setIsAllocated(boolean isAllocated) {
        this.isAllocated = isAllocated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBasePointer() {
        return basePointer;
    }

    public void setBasePointer(int basePointer) {
        this.basePointer = basePointer;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
        
}
