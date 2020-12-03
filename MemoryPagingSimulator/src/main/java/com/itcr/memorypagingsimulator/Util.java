/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author lopez
 */
public class Util {
    
    public static <E extends Enum<E>> List<String> getEnumAsStringArray(Class<E> e){
        List<String> retValue = Arrays.asList( e.getEnumConstants() )
                .stream()
                .map(elem -> elem.name())
                .collect(Collectors.toList());
        return retValue;
    }
    
}
