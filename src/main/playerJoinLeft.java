package main;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import main.pluginMain;

public class playerJoinLeft implements Listener {

	pluginMain pl;

	public playerJoinLeft(pluginMain pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPlayedBefore()) {
			pl.getConfig().set("Players." + p.getName() + ".money", "0");
			pl.saveConfig();
		}
	}
}
