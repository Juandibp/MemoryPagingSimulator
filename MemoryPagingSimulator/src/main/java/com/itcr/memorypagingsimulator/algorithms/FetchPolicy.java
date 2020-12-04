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
public abstract class FetchPolicy {
    
    public abstract ArrayList<Page> fetch(
            Pages pages, Frames frames, Process process, int pageId) throws IllegalReferenceException;
    
    public class IllegalReferenceException extends Exception {}
}
