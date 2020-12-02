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
        for(Page p:pageList){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public void addPage(Page page){
       for(int i = 0; i < pageList.size(); i++){
            if(pageList.get(i) == null){
                pageList.set(i, page);
            }
        }
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
    }
}
