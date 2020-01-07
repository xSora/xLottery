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
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author black
 */
public class ChatListener implements Listener{
    
    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        UUID pid = p.getUniqueId();
        boolean register = LotterySystem.isWritter(pid);
        if(register) {
            e.setCancelled(true);
            String Message = e.getMessage();
            if(Message.equalsIgnoreCase(Messages.CANCEL()) || Message.isEmpty()) {
                LotterySystem.delWritter(pid);
                p.sendMessage(Messages.ADD_LOTTERY_CANCELLED());
            }else{
                
                LotterySystem.pay4Tickets(p, Message.split(" "), true);
                /*}else {
                        p.sendMessage(Messages.LOTTERY_ADD_FAILED_NOTVALID(Message));
                        FileManager.Save(FileManager.cFile.lottery);
                }*/
            }
        }
    }
}
