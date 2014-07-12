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
        int random = (int) Math.floor(Math.random() * 100 + 1);
        int[] percents = {
            TenJava.getInstance().getConfig().getInt("storms.meteor_shower"),
            TenJava.getInstance().getConfig().getInt("storms.tornado"),
            TenJava.getInstance().getConfig().getInt("storms.acid_rain"),
            TenJava.getInstance().getConfig().getInt("storms.earthquake")
        };
        
    }
}
