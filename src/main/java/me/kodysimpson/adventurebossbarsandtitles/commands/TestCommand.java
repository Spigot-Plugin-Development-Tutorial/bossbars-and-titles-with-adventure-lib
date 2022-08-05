package me.kodysimpson.adventurebossbarsandtitles.commands;

import me.kodysimpson.adventurebossbarsandtitles.AdventureBossBarsAndTitles;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {

    private final AdventureBossBarsAndTitles plugin;

    public TestCommand(AdventureBossBarsAndTitles plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Audience audience = this.plugin.adventure().sender(sender);

        //boss bar is from 0 to 1
        //the overlay is the type of bossbar, how it appears
        BossBar countDownBar = BossBar.bossBar(Component.text("Countdown: 10").color(NamedTextColor.WHITE), 1.0f, BossBar.Color.PINK, BossBar.Overlay.NOTCHED_10);
        audience.showBossBar(countDownBar);

        sender.sendMessage("Beginning countdown...");

        final int[] countDown = {10};
        new BukkitRunnable(){
            @Override
            public void run() {
                countDown[0]--;
                if (countDown[0] <= 0 || countDownBar.progress() - 0.1f <= 0.0f) {
                    audience.hideBossBar(countDownBar);//remove the bossbar
                    cancel(); //cancels the current task
                }
                countDownBar.progress(countDownBar.progress() - 0.1f);
                countDownBar.name(Component.text("Countdown: " + countDown[0]).color(TextColor.color(countDown[0] <= 5 ? NamedTextColor.RED : NamedTextColor.WHITE)));
                sender.sendMessage("Boss bar countdown complete.");
            }
        }.runTaskTimerAsynchronously(this.plugin, 0, 20);

        return true;
    }



}
