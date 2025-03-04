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
public abstract class FetchPolicy {
    
    public abstract ArrayList<Page> fetch(
            Pages pages, Frames frames, Process process, int pageId, GlobalConfig conf, boolean writeOperation) throws IllegalReferenceException;
    
    public int getGlobalPageId(Process process, int id) throws IllegalReferenceException {
        if(id >= process.getPageTable().size()) throw new IllegalReferenceException();
        return process.getPageList().get(id).getId();
    }
    
    public class IllegalReferenceException extends Exception {}
}
