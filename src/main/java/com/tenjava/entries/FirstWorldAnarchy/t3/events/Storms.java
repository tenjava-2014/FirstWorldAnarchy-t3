/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.FirstWorldAnarchy.t3.events;

/**
 *
 * @author family
 */
public enum Storms {
    METEOR_SHOWER("storms.meteor_shower"), ACID_RAIN("storms.acid_rain"), TORNADOS("storms.tornados"), EARTHQUAKES("storms.earthquakes");
    
    private String alias;
    
    private Storms(String alias) {
        this.alias = alias;
    }
    
    public String getAlias() {
        return alias;
    }
    
    public static void startMeteorShower() {
        
    }
    
    public static void startAcidRain() {
        
    }
    
    public static void startTornado() {
        
    }
    
    public static void startEarthquake() {
        
    }
    
}
