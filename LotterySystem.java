package me.xSora;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.xSora.FileManager.cFile;

public class LotterySystem {
	public static Map<String, Integer> lottery = new HashMap<String, Integer>();
	public static List<String> Winners = new ArrayList<String>();
	//Lottery Stuff
	
	public static void LoadLottery() {
		if(FileManager.lottery.getConfigurationSection("Lottery") != null) {
			for (String key : FileManager.lottery.getConfigurationSection("Lottery").getKeys(false)) {
				lottery.put(key, (Integer) FileManager.lottery.get("Lottery."+key));
			}
		}
	}
	
	public static void SaveLottery() {
		for (String key : lottery.keySet()) {
			FileManager.lottery.set("Lottery."+key, lottery.get(key));
		}
			FileManager.Save(cFile.config);
	}

	public static void AddLotteryPlayer(Player p, int Number) {
		String UUID = p.getUniqueId().toString();
		lottery.put(UUID, Number);
		SaveLottery();
	}
	
	public static void ChooseWinner() {
		LoadLottery();
		Random rand = new Random();
		int WinningNumber = rand.nextInt(FileManager.config.getInt("Lottery.MaxNumber"));
		Bukkit.broadcastMessage(Messages.LOTTERY_ROLLED_NUMBER_BROADCAST(WinningNumber));
		
		//Get all Winners
        for (Entry<String, Integer> entry : lottery.entrySet()) {
            if (entry.getValue().equals(WinningNumber)) {
                Winners.add(entry.getKey());
            }
        }
        
        if(Winners.size() == 0) {
        	Bukkit.broadcastMessage(Messages.LOTTERY_NO_WINNERS(FileManager.config.getInt("Lottery.NextDrawing")));
        }else {
        	
    		int WinAmount = FileManager.config.getInt("Lottery.CurrentPool");
    		int HowManyWinners = Winners.size();
    		int WinPerPlayer = 0;
    		if(HowManyWinners == 1) {
    			WinPerPlayer = WinAmount;
    		}else {
    			WinPerPlayer = (WinAmount / HowManyWinners);
    		}
    		
            for(int i = 0; i < Winners.size(); i++) {
            	
            	String WinnerName = null;
            	OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(Winners.get(i)));
            	if(op.isOnline()) {
            		Player p = Bukkit.getPlayer(UUID.fromString(Winners.get(i)));
            		WinnerName = p.getName();
            		//Online Winner
    				p.sendMessage(Messages.LOTTERY_WINNERS_PRIVATE(WinPerPlayer));
    				Main.econ.depositPlayer(p, WinPerPlayer);
            	}else {
            		//Offline Winner
            		Main.plugin.getServer().dispatchCommand(Main.plugin.getServer().getConsoleSender(), "mail send "+op.getName()+ " "+Messages.LOTTERY_OFFLINE_PLAYER_MAIL(op.getName(), WinPerPlayer, WinningNumber));
    				Main.econ.depositPlayer(op, WinPerPlayer);
            		WinnerName = op.getName();
            	}
            	Bukkit.broadcastMessage(Messages.LOTTERY_WINNERS_BROADCAST(WinnerName));
        		//Lottery Reset
        		}
        		SetWinnerDisplay(WinAmount, WinningNumber);
            	
        }
        ClearLottery();
       }
	
	private static void SetWinnerDisplay(int WonAmount, int WonNumber) {
		
		FileManager.config.set("Lottery.LastWonAmount", WonAmount);
		FileManager.config.set("LastWonNumber", WonNumber);
	}
	
	private static void ClearLottery() {
		FileManager.config.set("Lottery.CurrentPool", 0);
		FileManager.lottery.set("Lottery", null);
		FileManager.lottery.set("REGISTER", null);
		lottery.clear();
		Winners.clear();
		FileManager.Save(cFile.lottery);
		FileManager.config.set("Lottery.NextDrawing", FileManager.config.getInt("Lottery.Drawing"));
	}
	
}
