/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.sirhectorin.xlottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author black
 */
public class ChatListener implements Listener{
    private static final ArrayList<UUID> writing = new ArrayList<>();
    
    public static void addWritter(UUID pid){
        writing.add(pid);
    }
    
    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        UUID pid = p.getUniqueId();
        boolean register = writing.indexOf(pid) != -1;
        if(register) {
            e.setCancelled(true);
            String Message = e.getMessage();
            if(Message.equalsIgnoreCase(Messages.CANCEL()) || Message.isEmpty()) {
                writing.remove(pid);
                p.sendMessage(Messages.ADD_LOTTERY_CANCELLED());
            }else{
                List<Integer> nums;
                try{
                    nums = Arrays.stream(Message.split(" ")).distinct()
                            .map(s -> Integer.parseInt(s))
                            .filter(n -> n <= FileManager.config.getInt("Lottery.MaxNumber") && n >= 0)
                            .collect(Collectors.toList());
                }catch(NumberFormatException ex){
                    p.sendMessage(Messages.ADD_LOTTERY_NUMBER(Messages.CANCEL()));
                    return;
                }
                int totalprice = nums.size()* FileManager.config.getInt("Lottery.Price");
                
                //if(Utils.isInt(Message)) {
                if(totalprice <= Main.econ.getBalance(p)){
                    LotterySystem.AddLotteryPlayer(p, new ArrayList<Integer>(nums));
                    int OldPool = FileManager.config.getInt("Lottery.CurrentPool");
                    int ToAdd = nums.size() * ((FileManager.config.getInt("Lottery.Price") - FileManager.config.getInt("Lottery.Fee")));
                    FileManager.config.set("Lottery.CurrentPool", (OldPool + ToAdd));
                    writing.remove(pid);
                    FileManager.Save(FileManager.cFile.lottery);
                } else {
                    p.sendMessage(Messages.LOTTERY_NOT_ENOUG_MONEY(totalprice));
                }
                /*}else {
                        p.sendMessage(Messages.LOTTERY_ADD_FAILED_NOTVALID(Message));
                        FileManager.Save(FileManager.cFile.lottery);
                }*/
            }
        }
    }
}
