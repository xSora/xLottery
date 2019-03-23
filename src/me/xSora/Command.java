package me.xSora;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmd, String arg, String[] args) {
		Player p = (Player)cs;
		if(args.length == 0) {
			LotteryGUI.SetStats(p);
			LotterySystem.LoadLottery();
			p.openInventory(LotteryGUI.LotteryGUI);
			return true;
		}
		if(args.length == 1) {
			String prefix = args[0];
			if(Main.isTesting) {
				if(prefix.equalsIgnoreCase("admin")) {
					if(p.isOp()) {
						//p.openInventory(LotteryGUI.LotteryAdminGUI);
						LotterySystem.ChooseWinner(true);	
					}else {
						p.sendMessage(Messages.COMMAND_USAGE());
					}
				}
			}
		}
		if(args.length > 1) {
			p.sendMessage(Messages.COMMAND_USAGE());
		}
		return true;
	}
}