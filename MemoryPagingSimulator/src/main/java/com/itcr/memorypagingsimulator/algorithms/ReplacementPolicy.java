/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms;

import com.itcr.memorypagingsimulator.algorithms.models.Reference;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author juand
 */
public interface ReplacementPolicy extends Callable<List<Reference>>{
    public List<Reference> allocateReferences();

	// This function returns the number of page faults counted.
    public int faults();

    public double faultRate();

    public void clearStats();

    public void setParams(int[] references, int frames);
}
