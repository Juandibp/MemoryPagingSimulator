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
public class PreFetchPolicy extends FetchPolicy {

    @Override
    public ArrayList<Page> fetch(Pages pages, Frames frames, Process process, int pageId, GlobalConfig conf) throws IllegalReferenceException {
        int globalId = this.getGlobalPageId(process, pageId);
        ArrayList<Page> retVal = new ArrayList<>();
        int pageAmount = conf.residentSetSize == GlobalConfig.ResidentSetSizeSetting.FIXED ? process.getFrameSpace() : conf.varResSetSizeLowerLimit ;
        if(pageAmount >= process.getPagesRequired()){
            process.getPageTable().forEach(page -> {page.reference(); retVal.add(page);});
        } else {
            int lower = (pageId+pageAmount) >= process.getPagesRequired() ? pageId : process.getPagesRequired() - pageAmount;
            for(int i = lower; i < lower+pageAmount;i++){
                retVal.add(process.getPageTable().get(i));
                process.getPageTable().get(i).reference();                    
            }
        }
        for (int i = 0; i < retVal.size(); i++) {
            if(retVal.get(i).isIsAllocated()){
                retVal.remove(i); i--;
            }
        }
        return retVal;
    }
    
}
