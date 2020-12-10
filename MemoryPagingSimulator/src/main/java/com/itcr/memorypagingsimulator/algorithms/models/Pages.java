/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms.models;

import java.util.ArrayList;

/**
 * representss secondary memory
 * 
 * @author lopez
 */
public class Pages {
    
    private int maxPages;
    private ArrayList<Page> pageList = new ArrayList<>();
    
    public Page getPage(int id){
        return pageList.stream().filter(p -> p.getId() == id)
                .findFirst().orElse(null);
    }

    public void addPage(Page page){
        pageList.set(page.getId(), page);
    }
    
    public void replacePage(Page p, int index){
        pageList.set(index, p);
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public ArrayList<Page> getPageList() {
        return pageList;
    }

    public void setPageList(ArrayList<Page> pageList) {
        this.pageList = pageList;
    }

    public Pages(int maxPages) {
        this.maxPages = maxPages;
        this.pageList = new ArrayList<>(maxPages);
        for (int i = 0; i < maxPages; i++) {
            this.pageList.add(null);           
        }
    }
}
