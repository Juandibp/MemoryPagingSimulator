/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.GlobalConfig;
import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Page;
import com.itcr.memorypagingsimulator.algorithms.models.Reference;
import com.itcr.memorypagingsimulator.algorithms.models.Process;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.javatuples.Pair;

/**
 *
 * @author juand
 */
public class FIFOPageReplacement extends ReplacementPolicy {

    
    private int currentIndex =0; //to be used in GLOBAL resident set
    private List<Pair<Integer, Integer>> processIndex = new ArrayList<>();

    @Override
    public ArrayList<Page> replace(GlobalConfig conf, List<Page> pagesToPlace, List<Page> pagesJustPlaced, Frames frames, Process proc) {
        ArrayList<Page> retValue = new ArrayList<>();
        if(conf.replacementScope == GlobalConfig.ReplacementScopeSetting.GLOBAL){
            for(int i = 0 ; i < pagesToPlace.size() ; i++){
                retValue.add(frames.getFrames().get(currentIndex));
                frames.placePage(pagesToPlace.get(i), currentIndex);
                //TODO update pageTable
                currentIndex++;
                if(currentIndex == conf.secondaryMemoryPages){
                    currentIndex = 0;
                }
            }
        } else {
            List<Page> scope = frames.getFrames().stream()
                    .filter(f -> f!=null && proc.getPageList().stream().anyMatch(p -> p.getId() == f.getId()))
                    .collect(Collectors.toList());
            Pair<Integer, Integer> pIndex = processIndex.stream()
                    .filter(elem -> elem!=null && elem.getValue0() == proc.getId())
                    .findFirst().orElse(Pair.with(proc.getId(), 0));
            
            for(int i = 0 ; i < pagesToPlace.size() ; i++){
                System.out.println("com.itcr.memorypagingsimulator.algorithms.FIFOPageReplacement.replace()");
                retValue.add(scope.get(pIndex.getValue1()));
                //TODO update pageTable
                int index = this.findIndex(scope.get(pIndex.getValue1()), frames);
                frames.placePage(pagesToPlace.get(i), index);
                proc.allocatePage(pagesToPlace.get(i), index);
                
                pIndex = pIndex.setAt1(pIndex.getValue1()+1);
                if(pIndex.getValue1() == scope.size()){
                    pIndex.setAt1(0);
                }
            }
            
            if(!processIndex.contains(pIndex))
                processIndex.add(pIndex);
        }
        return retValue;
    }

}
