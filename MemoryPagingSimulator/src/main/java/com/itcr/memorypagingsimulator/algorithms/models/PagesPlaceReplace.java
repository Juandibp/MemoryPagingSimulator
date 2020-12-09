/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms.models;

import java.util.ArrayList;

/**
 *
 * @author lopez
 */
public class PagesPlaceReplace {
    
    public ArrayList<Page> pagesPlaced = new ArrayList<>();
    public ArrayList<Page> pagesReplace = new ArrayList<>();

    @Override
    public String toString() {
        return "PagesPlaceReplace{" + "\n\tpagesPlaced=" + pagesPlaced + ",\n\tpagesReplace=" + pagesReplace + "\n}";
    }
    
    
    
}
