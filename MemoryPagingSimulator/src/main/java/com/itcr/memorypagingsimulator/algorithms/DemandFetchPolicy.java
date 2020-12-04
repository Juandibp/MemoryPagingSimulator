/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
import com.itcr.memorypagingsimulator.algorithms.models.Pages;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import java.util.ArrayList;

/**
 *
 * @author lopez
 */
public class DemandFetchPolicy extends FetchPolicy{
    
    @Override
    public ArrayList<Page> fetch(Pages pages, Frames frames, Process process, int pageId) throws IllegalReferenceException {
        Page page = null;
        for(Page p : process.getPageTable()){
            if(p.getId() == pageId){
                page = p;
                break;
            }
        }
        if(page == null)
            throw new IllegalReferenceException();
            
        page.reference();
        if(page.isIsAllocated()){
            return null;
        }
        ArrayList<Page> retVal = new ArrayList<>();
        retVal.add(page);
        return retVal;
    }    

}
