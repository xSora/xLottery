package me.sirhectorin.xlottery;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
	
    public static String c(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    
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
    
    public static String[] generateRange(String range) {
        try{
            String[] temp = range.split("\\.\\.");
            long start = Integer.parseInt(temp[0]);
            long end = Integer.parseInt(temp[1]);
            
            long aux;
            if(start > end){
                aux = end;
                end = start;
                start = aux;
            }
            aux = FileManager.config.getLong("Lottery.MaxNumber");
            if(end > aux)
                end = aux;
            
            String[] result = new String[(int)(end - start) + 1];
            
            for (int i = 0 ; i < result.length; i++) {
                result[i] = String.valueOf(start++);
            }
            return result;
        }catch(NumberFormatException ex){
            return new String[]{range};
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

}
