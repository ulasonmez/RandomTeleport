package me.blume.randomteleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.blume.randomteleport.Main;
import net.md_5.bungee.api.ChatColor;

public class DisableTeleportation implements CommandExecutor{
	private Main plugin;
	public DisableTeleportation(Main plugin) {
		this.plugin=plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("teleportstop")) {
			if(plugin.getConfig().getInt("Teleport")==1) {
				plugin.getConfig().set("Teleport", 0);
				plugin.saveConfig();
				sender.sendMessage(ChatColor.GREEN+"Teleportation stopped.");
				Main.task.cancel();
				return false;
			}
			else{
				sender.sendMessage("Its already off");
			}
		}
		return false;
	}

}
