package com.tenjava.entries.FirstWorldAnarchy.t3;

import com.tenjava.entries.FirstWorldAnarchy.t3.events.RandomEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TenJava extends JavaPlugin {

    private class RandomEventChecker extends BukkitRunnable {

        @Override
        public void run() {
            int random = (int) Math.floor(Math.random() * 100 + 1);
            if (random <= getConfig().getInt("random_percent")) {
                getServer().getPluginManager().callEvent(new RandomEvent());
            }
        }
        
    }
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        new RandomEventChecker().runTaskTimer(this, 0, getConfig().getInt("random_delay"));
    }

    
    
}
