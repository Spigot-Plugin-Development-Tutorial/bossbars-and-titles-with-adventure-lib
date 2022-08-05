package me.kodysimpson.adventurebossbarsandtitles.commands;

import me.kodysimpson.adventurebossbarsandtitles.AdventureBossBarsAndTitles;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class CheckCommand implements CommandExecutor {

    private final AdventureBossBarsAndTitles plugin;

    public CheckCommand(AdventureBossBarsAndTitles plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player p){
            if (this.plugin.getBlocksBroken().containsKey(p)){
                showMyTitle(this.plugin.adventure().sender(p), this.plugin.getBlocksBroken().get(p));
            }
        } else {
            sender.sendMessage("You must be a player to use this command.");
        }

        return true;
    }

    //gotten from https://docs.adventure.kyori.net/title.html
    private void showMyTitle(Audience target, int blocksBroken) {
        final Component mainTitle = Component.text("Blocks Broken", NamedTextColor.WHITE);
        final Component subtitle = Component.text(blocksBroken, NamedTextColor.GOLD);

        // Creates a simple title with the default values for fade-in, stay on screen and fade-out durations
        final Title title = Title.title(mainTitle, subtitle, Title.Times.times(Duration.ofMillis(500), Duration.ofMillis(3000), Duration.ofMillis(1000)));

        // Send the title to your audience
        target.showTitle(title);
    }

}
