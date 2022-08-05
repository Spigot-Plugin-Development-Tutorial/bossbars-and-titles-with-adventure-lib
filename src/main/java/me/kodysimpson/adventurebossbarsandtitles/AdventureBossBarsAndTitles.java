package me.kodysimpson.adventurebossbarsandtitles;

import me.kodysimpson.adventurebossbarsandtitles.commands.CheckCommand;
import me.kodysimpson.adventurebossbarsandtitles.commands.TestCommand;
import me.kodysimpson.adventurebossbarsandtitles.listeners.BlockBreakListener;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;

public final class AdventureBossBarsAndTitles extends JavaPlugin {

    private HashMap<Player, Integer> blocksBroken = new HashMap<>();

    private BukkitAudiences adventure;

    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {
        this.adventure = BukkitAudiences.create(this);
        getCommand("test").setExecutor(new TestCommand(this));
        getCommand("check").setExecutor(new CheckCommand(this));
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
    }

    @Override
    public void onDisable() {
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    public HashMap<Player, Integer> getBlocksBroken() {
        return blocksBroken;
    }
}
