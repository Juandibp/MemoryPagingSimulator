/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.GlobalConfig;
import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
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
    public ArrayList<Page> place(ArrayList<Page> pages, Frames frames, GlobalConfig conf, Process proc) {
        List<Page> RAM = frames.getFrames();
        for(int i=lastIndex+1; i != lastIndex; i++){
            
            if(i >= RAM.size()){
                i=0;
            }
            
            if(pages != null && pages.size() > 0){    
                if(RAM.get(i) == null){
                    RAM.set(i, pages.get(0));
                    Page tempPage = pages.remove(0);
                    
                    proc.allocatePage(tempPage, i);
                }
            }
            else{
                lastIndex = i;
                break;
            }
        }
        return pages;
    }
    
}
