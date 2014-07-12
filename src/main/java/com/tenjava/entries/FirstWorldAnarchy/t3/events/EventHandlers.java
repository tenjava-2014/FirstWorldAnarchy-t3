/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.FirstWorldAnarchy.t3.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author family
 */
public class EventHandlers implements Listener {
    
    @EventHandler
    public void onRandomEvent(RandomEvent e) {
        int random = (int) Math.floor(Math.random() * 4 + 1);
        Storms storm = Storms.METEOR_SHOWER;
        switch (random) {
            case 1:
                storm = Storms.METEOR_SHOWER;
                break;
            case 2:
                storm = Storms.TORNADOS;
                break;
            case 3:
                storm = Storms.ACID_RAIN;
               break;
            case 4:
                storm = Storms.EARTHQUAKES;
                break;
        }
        
        Storms.startCorrespondingStorm(storm.getAlias());
        
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (Storms.isStormInProgress()) {
            if (Storms.getCurrentStorm() == Storms.ACID_RAIN) {
                if (e.getPlayer().getWorld().getHighestBlockAt(e.getPlayer().getLocation()).getType() == Material.AIR) {
                    e.getPlayer().damage(0.2);
                }
            }
        }
    }
}
