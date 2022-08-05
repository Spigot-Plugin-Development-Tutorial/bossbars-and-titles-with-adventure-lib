package me.kodysimpson.adventurebossbarsandtitles.listeners;

import me.kodysimpson.adventurebossbarsandtitles.AdventureBossBarsAndTitles;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private final AdventureBossBarsAndTitles plugin;

    public BlockBreakListener(AdventureBossBarsAndTitles plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (plugin.getBlocksBroken().containsKey(player)){
            plugin.getBlocksBroken().put(player, plugin.getBlocksBroken().get(player) + 1);
            player.sendMessage("You have broken " + plugin.getBlocksBroken().get(player) + " blocks.");
        } else {
            plugin.getBlocksBroken().put(player, 1);
            player.sendMessage("You have broken your first block. Nice.");
        }
    }

}
