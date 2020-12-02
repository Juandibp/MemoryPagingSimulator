/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator;

/**
 *
 * @author lopez
 */
public class GlobalConfig {
    
    String setting1;
    String setting2;
    
    public GlobalConfig(String s1, String s2){
        this.setting1 = s1;
        this.setting2 = s2;
    }
    
    public Object clone() throws CloneNotSupportedException { 
        return super.clone(); 
    } 
    
}
