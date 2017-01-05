package main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import main.balance;
import main.playerJoinLeft;

public class pluginMain extends JavaPlugin {

	balance bal = null;
	

	public void onEnable() {
		bal = new balance(this);
		Bukkit.broadcastMessage(ChatColor.DARK_GREEN+""+ChatColor.STRIKETHROUGH+"---------------------------------------");
		Bukkit.broadcastMessage(ChatColor.GREEN+"Hi, "+ChatColor.YELLOW+"I'm PondJa."+ChatColor.AQUA+" The Main Creator of This plugin");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(ChatColor.WHITE+""+ChatColor.BOLD+"Plugin Topic: "+ChatColor.AQUA+ChatColor.BOLD+"Economy");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(ChatColor.AQUA+"Adviser Teacher: AJ. Jurarat Seeya");
		Bukkit.broadcastMessage(ChatColor.GREEN+"Co-Adviser Teacher: AJ. Nutthapong Seenaj");
		Bukkit.broadcastMessage(ChatColor.YELLOW+"Member: "+ChatColor.GOLD+"Palapon Soontornpas , Thanyalak Kaewkah , Netiporn Thaitawatkul , Phichayanan Thisongmuang , Suphisara Yaworn");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"Have a nice day :)");
		Bukkit.broadcastMessage(ChatColor.DARK_GREEN+""+ChatColor.STRIKETHROUGH+"---------------------------------------");
		regCmds();
		regEvents();
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(ChatColor.BLUE + "Economy System is" + ChatColor.GREEN + ChatColor.BOLD + " enabled.");
			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_TOUCH, 1, 1);
		}
		
		
	}

	public void onDisable() {
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(ChatColor.BLUE + "Economy System is" + ChatColor.RED + ChatColor.BOLD + " disabled");
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 0);
		}

	}

	public void regCmds() {

		getCommand("money").setExecutor(bal);
		getCommand("givemoney").setExecutor(bal);
		getCommand("takemoney").setExecutor(bal);
		getCommand("paymoney").setExecutor(bal);
		getCommand("cash").setExecutor(bal);
		getCommand("givecash").setExecutor(bal);
		getCommand("takecash").setExecutor(bal);
	}

	public void regEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new playerJoinLeft(this), this);
	}
}
