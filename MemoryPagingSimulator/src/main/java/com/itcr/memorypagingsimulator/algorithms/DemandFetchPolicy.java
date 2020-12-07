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
        if(process.getPageTable().get(pageId) != null){
            //then its in a frame
            frames.getFrames().get(process.getPageTable().get(pageId)).reference();
            return null;
        } else {
            ArrayList<Page> retVal = new ArrayList<>();
            Page fetchedPage = process.getPageList().get(pageId).clonePage();
            fetchedPage.reference();
            retVal.add(fetchedPage);
            return retVal;
        }
    }    

}
