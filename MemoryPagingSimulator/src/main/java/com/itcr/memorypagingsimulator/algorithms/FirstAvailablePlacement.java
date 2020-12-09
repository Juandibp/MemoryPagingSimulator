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
public class FirstAvailablePlacement extends PlacementPolicy{

    @Override
    public PagesPlaceReplace place(ArrayList<Page> pages, Frames frames, GlobalConfig conf, Process proc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PagesPlaceReplace retVal = new PagesPlaceReplace();
        List<Page> RAM = frames.getFrames();
        
        for(int i = 0; i<RAM.size(); i++){
            if(pages != null && pages.size() > 0){    
                if(RAM.get(i) == null){
                    RAM.set(i, pages.get(0));
                    Page tempPage = pages.remove(0);
                    retVal.pagesPlaced.add(tempPage);
                    proc.allocatePage(tempPage, i);
                    
                }
            }
            else{
                retVal.pagesReplace = pages;
                return retVal;
            }
        }
        retVal.pagesReplace = pages;
        return retVal;
    }
    
}
