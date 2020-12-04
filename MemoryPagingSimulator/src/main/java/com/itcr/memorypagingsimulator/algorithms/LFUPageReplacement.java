/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.algorithms.models.Frames;
import com.itcr.memorypagingsimulator.algorithms.models.Reference;
import java.util.LinkedList;

/**
 *
 * @author juand
 */
public class LFUPageReplacement extends AbstractPageReplacement{
    	
    @Override
    protected void allocate() {
        LinkedList<Integer> queue = new LinkedList<>();
        Frames past = new Frames(frames);
        for(Reference r: references){
            int ref = r.getReference();
            Frames f = r.getFrames();
            f.copyAll(past);
            queue.remove(new Integer(ref));
            queue.addLast(ref);
            System.out.println("LRUAlloc.allocate( )" + ref + " " + f.contains(ref));
            if(!f.contains(ref)){
                faults++;
                if(f.thereIsAnEmptyFrame()){
                    f.set(f.getEmptyFrame(), ref);
                }else{
                    int victim = queue.removeFirst();
                    f.swap(victim, ref);
                }
            }

            past.copyAll(f);

            }

    }
}
