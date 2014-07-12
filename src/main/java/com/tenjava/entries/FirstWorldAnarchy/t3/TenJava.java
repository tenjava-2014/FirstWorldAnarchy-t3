package com.tenjava.entries.FirstWorldAnarchy.t3;

import com.tenjava.entries.FirstWorldAnarchy.t3.events.EventHandlers;
import com.tenjava.entries.FirstWorldAnarchy.t3.events.RandomEvent;
import com.tenjava.entries.FirstWorldAnarchy.t3.events.Storms;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TenJava extends JavaPlugin {

    private static TenJava instance;

    private class RandomEventChecker extends BukkitRunnable {

        @Override
        public void run() {
            int random = (int) Math.floor(Math.random() * 100 + 1);
            if (random <= getConfig().getInt("random_percent") && !Storms.isStormInProgress()) {
                getServer().getPluginManager().callEvent(new RandomEvent());
            }
        }
    }

    private class DamagePlayerFromStorm extends BukkitRunnable {

        @Override
        public void run() {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Storms.isStormInProgress()) {
                    if (Storms.getCurrentStorm() == Storms.ACID_RAIN) {
                        if (playerInRain(player)) {
                            if (player.getHealth() > 0d) {
                                player.damage(0.2);
                                player.setHealth(player.getHealth() - 0.2);
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventHandlers(), this);
        new RandomEventChecker().runTaskTimer(this, 0, getConfig().getInt("random_interval") * 1200);
        new DamagePlayerFromStorm().runTaskTimer(this, 0, 20);
    }

    public static TenJava getInstance() {
        return instance;
    }

    public boolean playerInRain(Player p) {
        Location location = p.getEyeLocation();
        List<Integer> materials = new ArrayList<Integer>();
        for (int i = location.getBlockY() + 1; i <= location.getWorld().getMaxHeight(); i++) {
            materials.add(location.getBlock().getType() == Material.AIR ? 0 : 1);
        }
        if (p.getWorld().hasStorm() && materials.contains(1)) return false;
        if (p.getWorld().hasStorm() && !materials.contains(1)) return true;
        if (!p.getWorld().hasStorm()) return false;
        return false;
    }

}
