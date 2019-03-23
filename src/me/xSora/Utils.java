package me.xSora;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {
	
	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
    public static Player getPlayerByUuid(UUID uuid) {
        for(Player p : Bukkit.getServer().getOnlinePlayers())
            if(p.getUniqueId().equals(uuid)){
                return p;
            }
        throw new IllegalArgumentException();
    }
    
    public static boolean isUUIDOnline(UUID uuid) {
        for(Player p : Bukkit.getServer().getOnlinePlayers())
            if(p.getUniqueId().equals(uuid)){
                return true;
            }else {
            	return false;
            }
        throw new IllegalArgumentException();
    }
    
	public static boolean HasBoughtTicket(Player p) {
		String UUID = p.getUniqueId().toString();
		if(LotterySystem.lottery.containsKey(UUID)) {
			return true;
		}else {
			return false;
		}
	}

}
