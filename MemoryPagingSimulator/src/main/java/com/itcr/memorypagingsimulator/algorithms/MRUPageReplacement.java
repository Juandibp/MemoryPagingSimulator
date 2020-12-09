/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.GlobalConfig;
import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import com.itcr.memorypagingsimulator.algorithms.models.Reference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author juand
 */
public class MRUPageReplacement extends ReplacementPolicy{
    	
    @Override
    protected void allocate() {
        LinkedList<Integer> queue = new LinkedList<>();
        Frames past = new Frames(frames);
        for(Reference r: references){
            int ref = r.getReference();
            Frames f = r.getFrames();
            f.copyAll(past);
            queue.remove(new Integer(ref));
            queue.addFirst(ref);
            System.out.println("MRUAlloc.allocate( )" + ref + " " + f.contains(ref));
            if(!f.contains(ref)){
                faults++;
                if(f.thereIsAnEmptyFrame()){
                    //f.set(f.getEmptyFrame(), ref);
                }else{
                    int victim = queue.removeLast();
                    //f.swap(victim, ref);
                }
            }

            past.copyAll(f);

            }

    }

    @Override
    public ArrayList<Page> replace(GlobalConfig conf, List<Page> pagesToPlace, List<Page> pagesJustPlaced, Frames frames, Process proc) {
        List<Page> scope;
        
        //appart from implementing scope we dont want to replace the pages 
        //that were just put into main memory... that would be bad :c
        
        if(conf.replacementScope == GlobalConfig.ReplacementScopeSetting.GLOBAL){
            scope = frames.getFrames()
                    .stream()
                    .filter(p -> !pagesJustPlaced.stream().anyMatch(pp -> pp.getId() == p.getId()))
                    .collect(Collectors.toList());
        } else {
            scope = frames.getFrames()
                    .stream()
                    .filter(p -> (!pagesJustPlaced.stream().anyMatch(pp -> pp.getId() == p.getId())) && 
                                   proc.getPageList().stream().anyMatch(pp -> pp.getId() == p.getId()))
                    .collect(Collectors.toList());
        }
        List<Page> sortedByReferenceTime = scope.stream()
                .filter(p -> !pagesToPlace.stream().anyMatch(pp -> p.getId() == pp.getId()))
                .sorted((b,a) -> frames.getReferenceTimes().get(a.getId()) - frames.getReferenceTimes().get(b.getId()))
                .collect(Collectors.toList());
        ArrayList<Page> returnValue = new ArrayList<>();

        for(Page p : pagesToPlace){
            if(sortedByReferenceTime.isEmpty())
                //by this point the referenced page should be in memory
                //we can just... leave I guess
                break;
            if(!scope.stream().anyMatch(elem -> p.getId() == elem.getId())){
                frames.placePage(p, this.findIndex(sortedByReferenceTime.remove(0), frames));
            }
        }
        
        return returnValue;        
    }

}
