package me.blume.randomteleport.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.blume.randomteleport.Main;
import net.md_5.bungee.api.ChatColor;

public class ActivateTeleportation implements CommandExecutor{
	@SuppressWarnings("unused")
	private Main plugin;
	public ActivateTeleportation(Main plugin) {
		this.plugin=plugin;
	}
	Location randomLoc,checkLoc,checkLocDown;
	int x,y,z;
	Random rand = new Random();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("teleportstart")) {
			if(plugin.getConfig().getInt("Teleport")== 0) {
				plugin.getConfig().set("Teleport",1);
				plugin.saveConfig();
				sender.sendMessage(ChatColor.GREEN+"Teleportation started.");

				if(plugin.getConfig().getInt("Teleport")==1) {
					Main.task=Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
						@Override
						public void run() {

							x=rand.nextInt(150000);
							z=rand.nextInt(150000);
							y=rand.nextInt(256);


							for(Player player : Bukkit.getOnlinePlayers()) {
								
								while(true) {
									checkLoc = new Location(player.getWorld(),x,y,z);
									checkLocDown =  new Location(player.getWorld(),x,y-1,z);
									if(checkLocDown.getBlock().getType()!=Material.AIR && checkLoc.getBlock().getType()==Material.AIR){
										break;
									}
									y=rand.nextInt(256);
								}
								randomLoc = new Location(player.getWorld(),x,y,z);
								player.teleport(randomLoc);
							}


						}
					}, 20*60, 20L*60);
				}
				return false;

			}
			else {sender.sendMessage("Its already started");}
		}
		return false;
	}

}
