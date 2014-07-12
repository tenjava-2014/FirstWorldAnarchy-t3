/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.FirstWorldAnarchy.t3.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 *
 * @author family
 */
public class EventHandlers implements Listener {
    
    @EventHandler
    public void onRandomEvent(RandomEvent e) {
        int random = (int) Math.floor(Math.random() * 3 + 1);
        Storms storm;
        switch (random) {
            case 1:
                storm = Storms.METEOR_SHOWER;
                break;
            case 2:
                storm = Storms.ACID_RAIN;
                break;
            case 3:
                storm = Storms.EARTHQUAKES;
               break;
            default:
                storm = Storms.METEOR_SHOWER;
                break;
        }
        Storms.startCorrespondingStorm(storm.getAlias());
        
    }
}
