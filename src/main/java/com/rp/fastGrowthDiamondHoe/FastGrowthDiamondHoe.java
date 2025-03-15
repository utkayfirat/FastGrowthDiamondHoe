package com.rp.fastGrowthDiamondHoe;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FastGrowthDiamondHoe extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new GrowthAction(this), this);
        getLogger().info("FastGrowthDiamondHoe plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("FastGrowthDiamondHoe plugin disabled.");
    }
}
