/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.sirhectorin.xlottery;

import java.util.ArrayList;
import java.util.UUID;
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
            if(Message.equalsIgnoreCase(Messages.CANCEL()) || Message.equalsIgnoreCase("")) {
                writing.remove(pid);
                p.sendMessage(Messages.ADD_LOTTERY_CANCELLED());
            }else{
                if(Utils.isInt(Message)) {
                    int lottoid = Integer.parseInt(Message);
                    if(lottoid <= FileManager.config.getInt("Lottery.MaxNumber")){
                            LotterySystem.AddLotteryPlayer(p, lottoid);
                            int OldPool = FileManager.config.getInt("Lottery.CurrentPool");
                            int ToAdd = (FileManager.config.getInt("Lottery.Price") - FileManager.config.getInt("Lottery.Fee"));
                            FileManager.config.set("Lottery.CurrentPool", (OldPool + ToAdd));
                            writing.remove(pid);
                            FileManager.Save(FileManager.cFile.lottery);
                            p.sendMessage(Messages.LOTTERY_ADD_SUCCESSFULL(Message));
                            Main.econ.withdrawPlayer(p, FileManager.config.getInt("Lottery.Price"));
                    }else {
                            p.sendMessage(Messages.LOTTERY_ADD_FAILED_TOOBIG(Message));
                            FileManager.Save(FileManager.cFile.lottery);
                    }
                }else {
                        p.sendMessage(Messages.LOTTERY_ADD_FAILED_NOTVALID(Message));
                        FileManager.Save(FileManager.cFile.lottery);
                }
            }
        }
    }
}
