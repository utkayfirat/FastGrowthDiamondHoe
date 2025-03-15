package com.rp.fastGrowthDiamondHoe;

import com.rp.fastGrowthDiamondHoe.FastGrowthDiamondHoe;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;

public class GrowthAction implements Listener {
    private FastGrowthDiamondHoe plugin;

    public GrowthAction(FastGrowthDiamondHoe fastGrowthDiamondHoe) {
        plugin = fastGrowthDiamondHoe;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().toString().contains("RIGHT_CLICK") && event.getClickedBlock() != null) {
            Block block = event.getClickedBlock();
            if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_HOE) {
                if (isCropBlock(block.getType())) {
                    if (block.getBlockData() instanceof Ageable) {
                        Ageable cropState = (Ageable) block.getBlockData();
                        if (cropState.getAge() < cropState.getMaximumAge()) {
                            cropState.setAge(cropState.getAge() + 1);
                            block.setBlockData(cropState);
                            if(cropState.getAge() == cropState.getMaximumAge()) {
                                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(0, 255, 0), 1.0F);
                                block.getWorld().spawnParticle(Particle.DUST, block.getLocation().add(0.5, 0.5, 0.5), 5, 0.2, 0.2, 0.2, 0.1, dustOptions);
                            }else{
                                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(255, 0, 0), 1.0F);
                                block.getWorld().spawnParticle(Particle.DUST, block.getLocation().add(0.5, 0.5, 0.5), 5, 0.2, 0.2, 0.2, 0.1, dustOptions);
                            }
                            //player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "The crop has been grown.");
                        } else {
                            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(0, 255, 0), 1.0F);
                            block.getWorld().spawnParticle(Particle.DUST, block.getLocation().add(0.5, 0.5, 0.5), 5, 0.2, 0.2, 0.2, 0.1, dustOptions);
                            //player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "This crop has already ripened.");
                        }
                    }
                }

            }
        }
    }

    private boolean isCropBlock(Material material) {
        return material == Material.WHEAT || material == Material.CARROTS || material == Material.POTATOES ||
                material == Material.BEETROOTS || material == Material.MELON_STEM || material == Material.PUMPKIN_STEM ||
                material == Material.NETHER_WART || material == Material.SUGAR_CANE || material == Material.CACTUS;
    }
}
