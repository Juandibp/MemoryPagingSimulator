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
    public ArrayList<Page> fetch(Pages pages, Frames frames, Process process, int pageId, GlobalConfig conf, boolean writeOperation) throws IllegalReferenceException {
        if(process.getPageTable().get(pageId) != null){
            //no pageFault
            Page p = frames.getFrames().get(process.getPageTable().get(pageId));
            p.reference(writeOperation);
            frames.reference(process.getPageTable().get(pageId));
            return null;
        } else {
            //pageFault
            ArrayList<Page> retVal = new ArrayList<>();
            int pageAmount = conf.residentSetSize == GlobalConfig.ResidentSetSizeSetting.FIXED ? process.getFrameSpace() : conf.varResSetSizeLowerLimit ;
            if(pageAmount >= process.getPagesRequired()){
                //theres more space for this process than neede
                //bring all the pages
                for (int i = 0; i < process.getPageList().size(); i++) {
                    //bring every page that isnt in main memory
                    if(process.getPageTable().get(i) == null) {
                        Page p = pages.getPage(process.getPageId(i)).clonePage();
                        if(i == pageId){        //this is the page originaly referenced 
                            p.reference(writeOperation);
                            frames.reference(p);
                        } 
                        retVal.add(p);
                    }
                }
            } else {
                //theres not enough space for all the pages
                int lower = (pageId+pageAmount) >= process.getPagesRequired() ? process.getPagesRequired() - pageAmount : pageId;
                for(int i = lower; i < lower+pageAmount;i++){
                    //bring the amount of pages that arent in main memory after the one referenced
                    if(process.getPageTable().get(i) == null) {
                        Page p = pages.getPage(process.getPageId(i)).clonePage();
                        if(i == pageId){//this is the page originaly referenced 
                            p.reference(writeOperation);
                            frames.reference(p);
                        } 
                        retVal.add(p);
                    }
                }
            }

            return retVal;
        }
        
        
//        int globalId = this.getGlobalPageId(process, pageId);
//        ArrayList<Page> retVal = new ArrayList<>();
//        int pageAmount = conf.residentSetSize == GlobalConfig.ResidentSetSizeSetting.FIXED ? process.getFrameSpace() : conf.varResSetSizeLowerLimit ;
//        if(pageAmount >= process.getPagesRequired()){
//            process.getPageList().forEach(page -> {
//                //TODO esto tenia que cambiarlo
//                Page p = page.clonePage();
//                p.reference();
//                retVal.add(p);
//            });
//        } else {
//            int lower = (pageId+pageAmount) >= process.getPagesRequired() ? pageId : process.getPagesRequired() - pageAmount;
//            for(int i = lower; i < lower+pageAmount;i++){
//                retVal.add(process.getPageList().get(i));
//                process.getPageList().get(i).reference();                    
//            }
//        }
//        for (int i = 0; i < retVal.size(); i++) {
//            if(retVal.get(i).isIsAllocated()){
//                retVal.remove(i); i--;
//            }
//        }
//        return retVal;
    }
    
}
