/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.GlobalConfig;
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
    public ArrayList<Page> fetch(Pages pages, Frames frames, Process process, int pageId, GlobalConfig conf) throws IllegalReferenceException {
        int globalPageId = this.getGlobalPageId(process, pageId);
        Page page = process.getPageList().get(pageId);            
        page.reference();
        if(page.isIsAllocated()){
            return null;
        }
        ArrayList<Page> retVal = new ArrayList<>();
        retVal.add(page);
        return retVal;
    }    

}
