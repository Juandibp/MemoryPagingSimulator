/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.GlobalConfig;
import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
import com.itcr.memorypagingsimulator.algorithms.models.PagesPlaceReplace;
import java.util.ArrayList;
import java.util.List;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
/**
 *
 * @author juand
 */
public class NextAvailablePlacement extends PlacementPolicy{
    public int lastIndex = 0;
    @Override
    public PagesPlaceReplace place(ArrayList<Page> pages, Frames frames, GlobalConfig conf, Process proc) {
        PagesPlaceReplace retVal = new PagesPlaceReplace();
        
        List<Page> RAM = frames.getFrames();
        int pageLimit;
        if(conf.residentSetSize == GlobalConfig.ResidentSetSizeSetting.FIXED){
            pageLimit = proc.getFrameSpace();
        } else {
            pageLimit = conf.varResSetSizeUpperLimit;
        }
        
        for(int i=lastIndex+1; i != lastIndex; i++){
            
            if(i >= RAM.size()){
                i=0;
            }
            
            //if limit reached or there arent pages to place: cant place no more
            long a = proc.getPageTable().stream().filter(n -> n != null).count();
            if(pages != null && pages.size() > 0
                    && proc.getPageTable().stream().filter(n -> n != null).count() < pageLimit){    
                if(RAM.get(i-1) == null){
                    RAM.set(i-1, pages.get(0));
                    Page tempPage = pages.remove(0);
                    retVal.pagesPlaced.add(tempPage);
                    proc.allocatePage(tempPage, i-1);
                }
            }
            else{
                lastIndex = i-1;
                break;
            }
        }
        retVal.pagesReplace = pages;
        return retVal;
    }
    
}
