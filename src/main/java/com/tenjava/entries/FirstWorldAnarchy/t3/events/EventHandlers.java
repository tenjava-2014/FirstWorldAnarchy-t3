/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.FirstWorldAnarchy.t3.events;

import com.tenjava.entries.FirstWorldAnarchy.t3.TenJava;
import java.util.Collections;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 *
 * @author family
 */
public class EventHandlers implements Listener {
    
    @EventHandler
    public void onRandomEvent(RandomEvent e) {
        int random = (int) Math.floor(Math.random() * 4 + 1);
        Storms storm;
        switch (random) {
            case 1:
                storm = Storms.METEOR_SHOWER;
                Storms.startMeteorShower();
                break;
            case 2:
                storm = Storms.TORNADOS;
                Storms.startTornado();
                break;
            case 3:
                storm = Storms.ACID_RAIN;
                Storms.startAcidRain();
               break;
            case 4:
                storm = Storms.EARTHQUAKES;
                Storms.startEarthquake();
                break;
        }
        
    }
}
