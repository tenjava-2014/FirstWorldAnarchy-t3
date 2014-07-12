/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenjava.entries.FirstWorldAnarchy.t3.events;

import com.tenjava.entries.FirstWorldAnarchy.t3.TenJava;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.FallingSand;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 *
 * @author family
 */
public enum Storms {

    METEOR_SHOWER("storms.meteor_shower"), ACID_RAIN("storms.acid_rain"), TORNADOS("storms.tornados"), EARTHQUAKES("storms.earthquakes");

    private String alias;

    private static boolean stormInProgress;
    private static Storms currentStorm = null;

    public static boolean isStormInProgress() {
        return stormInProgress;
    }

    public static Storms getCurrentStorm() {
        return currentStorm;
    }

    private static void setCurrentStorm(Storms storm) {
        currentStorm = storm;
    }

    private static void setStormInProgress(boolean stormInProgress) {
        Storms.stormInProgress = stormInProgress;
    }

    private Storms(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public static void startMeteorShower() {
        Bukkit.broadcastMessage(ChatColor.DARK_RED + "A meteor shower has started! Take cover!");
        setStormInProgress(true);
        setCurrentStorm(METEOR_SHOWER);
        // ----------------------------------------------
        // Storm Code
        Bukkit.getServer().getScheduler().runTaskTimer(TenJava.getInstance(), new BukkitRunnable() {
            private int count = 300;
            @Override
            public void run() {
                int random = (int) Math.floor(Math.random() * 100 + 1);
                if (random  <= 25) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        Fireball ball = (Fireball) player.getWorld().spawnEntity(player.getLocation().add(0, 50, 0), EntityType.FIREBALL);
                        ball.setVelocity(new Vector(0, -3, 0));
                    }
                }
                count--;
            }
        }, 0, 2);
        // ----------------------------------------------
        // End Storm Code
        
        Bukkit.getServer().getScheduler().runTaskLater(TenJava.getInstance(), new Runnable() {
            @Override public void run() {
                setStormInProgress(false);
                setCurrentStorm(null);
            }
        }, TenJava.getInstance().getConfig().getInt("storm_duration") * 1200);
    }

    public static void startAcidRain() {
        Bukkit.broadcastMessage(ChatColor.BLUE + "It's raining acid! Take cover!");
        setStormInProgress(true);
        setCurrentStorm(ACID_RAIN);
        // -------------------------------------------------
        // Storm Code
        for (World world : Bukkit.getServer().getWorlds()) {
            world.setStorm(true);
        }
        // -------------------------------------------------
        // End Storm Code
        Bukkit.getServer().getScheduler().runTaskLater(TenJava.getInstance(), new Runnable() {
            @Override public void run() {
                setStormInProgress(false);
                setCurrentStorm(null);
            }
        }, TenJava.getInstance().getConfig().getInt("storm_duration") * 1200);
    }

    public static void startTornado() {
        Bukkit.broadcastMessage(ChatColor.GRAY + "A tornado has spawned!");
        setStormInProgress(true);
        setCurrentStorm(TORNADOS);
        // -------------------------------------------------
        // Storm Code
        Bukkit.getServer().getScheduler().runTaskTimer(TenJava.getInstance(), new BukkitRunnable() {
            private int count = 300;
            @Override public void run () {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setVelocity(new Vector(Math.floor(Math.random() * 2 + 1), Math.floor(Math.random() * 2 + 1), Math.floor(Math.random() * 2 + 1)));
                    player.playSound(player.getLocation(), Sound.PORTAL, 1, 1.5f);
                }
                count--;
                if (count <= 0) cancel();
            }
        }, 0, 40);
        // -------------------------------------------------
        // End Storm Code
        Bukkit.getServer().getScheduler().runTaskLater(TenJava.getInstance(), new Runnable() {
            @Override public void run() {
                setStormInProgress(false);
                setCurrentStorm(null);
                Bukkit.broadcastMessage(ChatColor.GREEN + "Tornado has ended!");
            }
        }, TenJava.getInstance().getConfig().getInt("storm_duration") * 1200);
    }

    public static void startEarthquake() {
        Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "An earthquake has started! Take cover!");
        setStormInProgress(true);
        setCurrentStorm(EARTHQUAKES);
        // ----------------------------------------------
        // Storm Code
        Bukkit.getServer().getScheduler().runTaskTimer(TenJava.getInstance(), new BukkitRunnable() {
            private int count = 300;
            @Override
            public void run() {
                int random = (int) Math.floor(Math.random() * 100 + 1);
                if (random  <= 25) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        FallingBlock block = (FallingBlock) player.getWorld().spawnEntity(player.getLocation(), EntityType.FALLING_BLOCK);
                    }
                }
                count--;
            }
        }, 0, 2);
        // ----------------------------------------------
        // End Storm Code
        Bukkit.getServer().getScheduler().runTaskLater(TenJava.getInstance(), new Runnable() {
            @Override public void run() {
                setStormInProgress(false);
                setCurrentStorm(null);
            }
        }, TenJava.getInstance().getConfig().getInt("storm_duration") * 1200);
        
    }

    public static void startCorrespondingStorm(String configName) {
        if (configName.equalsIgnoreCase(METEOR_SHOWER.getAlias()) && TenJava.getInstance().getConfig().getBoolean(METEOR_SHOWER.getAlias())) {
            startMeteorShower();
        } else if (configName.equalsIgnoreCase(TORNADOS.getAlias()) && TenJava.getInstance().getConfig().getBoolean(TORNADOS.getAlias())) {
            startTornado();
        } else if (configName.equalsIgnoreCase(ACID_RAIN.getAlias()) && TenJava.getInstance().getConfig().getBoolean(ACID_RAIN.getAlias())) {
            startAcidRain();
        } else if (configName.equalsIgnoreCase(EARTHQUAKES.getAlias()) && TenJava.getInstance().getConfig().getBoolean(EARTHQUAKES.getAlias())) {
            startEarthquake();
        } else {
            startMeteorShower();
        }
    }
}
