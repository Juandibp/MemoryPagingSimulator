/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
import com.itcr.memorypagingsimulator.algorithms.models.Pages;
import java.util.ArrayList;

/**
 *
 * @author lopez
 */
public class DemandCleaning extends CleaningPolicy {

    @Override
    public void clean(ArrayList<Page> replacementPages, Frames frames, Pages pages) {
        if(replacementPages == null || replacementPages.isEmpty()) 
            return;
        
        replacementPages.forEach(p -> {
            p.setDirty(false);
            pages.addPage(p);
        });
        
    }
    
}
