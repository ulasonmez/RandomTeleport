package me.blume.randomteleport;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.blume.randomteleport.commands.ActivateTeleportation;
import me.blume.randomteleport.commands.DisableTeleportation;

public class Main extends JavaPlugin{
	public static BukkitTask task;
	@Override
	public void onEnable() {
		getCommand("teleportstart").setExecutor(new ActivateTeleportation(this));
		getCommand("teleportstop").setExecutor(new DisableTeleportation(this));
		loadConfig();
	}
	@Override
	public void onDisable() {
		
	}
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

}
