package main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import main.pluginMain;
import main.playerJoinLeft;

public class balance implements CommandExecutor {

	pluginMain plugin;

	public balance(pluginMain plugin) {
		this.plugin = plugin;
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		Player player = (Player) sender;
		int money = plugin.getConfig().getInt("Players." + player.getName() + ".Money");

		if (cmd.getName().equalsIgnoreCase("money")) {
			if (args.length == 0) {
				player.sendMessage("§9Money> §7Your balance is §d" + money + "$§7.");
				player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			} else {
					OfflinePlayer t = Bukkit.getPlayer(args[0]);
					int moneyt = plugin.getConfig().getInt("Players." + t.getName() + ".Money");
					player.sendMessage("§9Money> §7" + t.getName() + "'s balance is §d" + moneyt + "$§7.");
					player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

			}
		}

		if (cmd.getName().equalsIgnoreCase("givemoney")) {
			if (player.isOp()) {
				if (args.length < 2) {
					player.sendMessage("§9Money> §7Type: §a/givemoney [Player] [Amount]");
				} else {
					OfflinePlayer t = Bukkit.getPlayer(args[0]);
					int moneyt = plugin.getConfig().getInt("Players." + t.getName() + ".Money");
					if (isInt(args[1])) {
						int amount = Integer.parseInt(args[1]);
						if (t.isOnline()) {
							Player tt = Bukkit.getPlayer(t.getName());
							plugin.getConfig().set("Players." + t.getName() + ".Money", moneyt + amount);
							plugin.saveConfig();
							player.sendMessage("§9Money> §7You have been §bGiven §d$ to §e" + t.getName()
									+ "§7, amount §d" + amount + "$");
							tt.sendMessage("§9Money> §e" + player.getName() + " §7has been given money to you §d"
									+ amount + "$§7.");
							tt.playSound(tt.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1.6F);
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						} else {
							plugin.getConfig().set("Players." + t.getName() + ".Money", moneyt + amount);
							plugin.saveConfig();
							player.sendMessage("§9Money> §7You have been §bGiven §d$ to §e" + t.getName()
									+ "§7, amount §d" + amount + "$");
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						}
					} else {
						player.sendMessage("§9Money> §c" + args[1] + " is not number.");
					}
				}
			} else {

			}
		}

		if (cmd.getName().equalsIgnoreCase("takemoney")) {
			if (player.isOp()) {
				if (args.length < 2) {
					player.sendMessage("§9Money> §7Type: §a/takemoney [Player] [Amount]");
				} else {
					OfflinePlayer t = Bukkit.getPlayer(args[0]);
					int moneyt = plugin.getConfig().getInt("Players." + t.getName() + ".Money");
					if (isInt(args[1])) {
						int amount = Integer.parseInt(args[1]);
						if (t.isOnline()) {
							Player tt = Bukkit.getPlayer(t.getName());
							plugin.getConfig().set("Players." + t.getName() + ".Money", moneyt - amount);
							plugin.saveConfig();
							player.sendMessage("§9Money> §7You have been taken money of §e" + t.getName()
									+ "§7, amount §d" + amount + "$");
							tt.sendMessage("§9Money> §e" + player.getName() + " §7has been taken your money §d" + amount
									+ "$§7.");
							tt.playSound(tt.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1.6F);
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						} else {
							plugin.getConfig().set("Players." + t.getName() + ".Money", moneyt - amount);
							plugin.saveConfig();
							player.sendMessage("§9Money> §7You have been taken money of §e" + t.getName()
									+ "§7, amount §d" + amount + "$");
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						}
					} else {
						player.sendMessage("§9Money> §c" + args[1] + " is not number.");
					}
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("paymoney")) {
			if (player.isOp()) {
				if (args.length < 2) {
					player.sendMessage("§9Money> §7Type: §a/paymoney [Player] [Amount]");
				} else {
					Player target = (Player) Bukkit.getPlayer(args[0]);
					int moneyt = plugin.getConfig().getInt("Players." + target.getName() + ".Money");
					if (target != null) {
						if (isInt(args[1])) {
							int amount = Integer.parseInt(args[1]);
							if (amount < 100) {
								player.sendMessage("§9Money> §cSorry, §7You must to pay §aStart at §d100$§7!");
								player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);

							} else {
								if (money >= amount) {
									plugin.getConfig().set("Players." + player.getName() + ".Money", money - amount);
									plugin.getConfig().set("Players." + target.getName() + ".Money", moneyt + amount);
									plugin.saveConfig();
									player.sendMessage("§9Money> §7You have been §bPaid §7to §e" + target.getName()
											+ "§7, amount §d" + amount + "$");
									target.sendMessage("§9Money> §e" + player.getName() + " §7has been paid to you §d"
											+ amount + "$§7.");
									target.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1.6F);
									player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
								} else {
									player.sendMessage("§9Money> §cSorry, §7You don't have enough §d$§7 to pay!");
									player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
								}
							}
						} else {
							player.sendMessage("§9Money> §c" + args[1] + " is not number.");
						}
					} else {
						player.sendMessage("§9Money> §7Player " + args[0] + " §cis not online§7.");
					}
				}
			} else {

			}
		}

		int cash = plugin.getConfig().getInt("Players." + player.getName() + ".Cash");
		if (cmd.getName().equalsIgnoreCase("cash")) {
			if (args.length == 0) {
				player.sendMessage("§9Cash> §7Your Cash is §d" + cash + " Sn§7.");
				player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			} else {
				if (player.isOp()) {
					OfflinePlayer t = Bukkit.getPlayer(args[0]);
					int casht = plugin.getConfig().getInt("Players." + t.getName() + ".Cash");
					player.sendMessage("§9Cash> §7" + t.getName() + "'s cash is §d" + casht + " Sn§7.");
					player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				} else {

				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("givecash")) {
			if (player.isOp()) {
				if (args.length < 2) {
					player.sendMessage("§9Cash> §7Type: §a/givecash [Player] [Amount]");
				} else {
					OfflinePlayer t = Bukkit.getPlayer(args[0]);
					int moneyt = plugin.getConfig().getInt("Players." + t.getName() + ".Cash");
					if (isInt(args[1])) {
						int amount = Integer.parseInt(args[1]);
						if (t.isOnline()) {
							Player tt = Bukkit.getPlayer(t.getName());
							int casht = plugin.getConfig().getInt("Players." + t.getName() + ".Cash");
							plugin.getConfig().set("Players." + t.getName() + ".Cash", casht + amount);
							plugin.saveConfig();
							player.sendMessage("§Cash> §7You have been §bGiven §dCash to §e" + t.getName()
									+ "§7, amount §d" + amount + "$");
							tt.sendMessage("§9Cash> §e" + player.getName() + " §7has been given money to you §d"
									+ amount + "$§7.");
							tt.playSound(tt.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1.6F);
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						} else {
							int casht = plugin.getConfig().getInt("Players." + t.getName() + ".Cash");
							plugin.getConfig().set("Players." + t.getName() + ".Cash", casht + amount);
							plugin.saveConfig();
							player.sendMessage("§9Cash> §7You have been §bGiven §dCash to §e" + t.getName()
									+ "§7, amount §d" + amount + "$");
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						}
					} else {
						player.sendMessage("§9Money> §c" + args[1] + " is not number.");
					}
				}
			} else {

			}
		}
		if (cmd.getName().equalsIgnoreCase("takecash")) {
			if (player.isOp()) {
				if (args.length < 2) {
					player.sendMessage("§9Money> §7Type: §a/takecash [Player] [Amount]");
				} else {
					OfflinePlayer t = Bukkit.getPlayer(args[0]);
					int casht = plugin.getConfig().getInt("Players." + t.getName() + ".Cash");
					if (isInt(args[1])) {
						int amount = Integer.parseInt(args[1]);
						if (t.isOnline()) {
							Player tt = Bukkit.getPlayer(t.getName());
							plugin.getConfig().set("Players." + t.getName() + ".Cash", casht - amount);
							plugin.saveConfig();
							player.sendMessage("§9Cash> §7You have been taken cash of §e" + t.getName()
									+ "§7, amount §d" + amount + "$");
							tt.sendMessage("§9Cash> §e" + player.getName() + " §7has been taken your money §d" + amount
									+ "$§7.");
							tt.playSound(tt.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1.6F);
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						} else {
							plugin.getConfig().set("Players." + t.getName() + ".Cash", casht - amount);
							plugin.saveConfig();
							player.sendMessage("§9Cash> §7You have been taken cash of §e" + t.getName()
									+ "§7, amount §d" + amount + "$");
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						}
					} else {
						player.sendMessage("§9Money> §c" + args[1] + " is not number.");
					}
				}

			}
		}
		return true;
	}

}