package me.sirhectorin.xlottery;

import com.github.stefvanschie.inventoryframework.Gui;
import com.github.stefvanschie.inventoryframework.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.sirhectorin.xlottery.FileManager.cFile;


public class LotteryGUI{
    Gui lotteryGUI = new Gui(Main.plugin, 1, "�6Lottery");
    StaticPane pane = new StaticPane(0,0, 9, 1);
    //items
    private ItemStack is_JoinLottery = new ItemStack(Material.DIAMOND, 1);
    private ItemStack is_GlobalLotteryStats = new ItemStack(Material.PAPER, 1);;

    public LotteryGUI(Player p){
        LoadItems(p);
        lotteryGUI.addPane(pane);
        lotteryGUI.show(p);
    }

    public static Inventory LotteryGUI = Bukkit.createInventory(null, 9, "�6Lottery");

    public void LoadItems(Player p) {
        ItemMeta meta_JoinLottery = is_JoinLottery.getItemMeta();
        ArrayList<String> lore_JoinLottery = new ArrayList<String>();
        if(Utils.HasBoughtTicket(p)) {
            meta_JoinLottery.setDisplayName(Messages.BUY_NEW_TICKET());
            int lottoid = LotterySystem.lottery.get(p.getUniqueId().toString());
            lore_JoinLottery.add(Messages.CURRENT_LOTTERY_TICKET(lottoid));
        }else{
            meta_JoinLottery.setDisplayName(Messages.JOIN_LOTTERY(FileManager.config.getInt("Lottery.Price")));
            lore_JoinLottery.add(Messages.NO_LOTTERY_TICKET());
        }

        meta_JoinLottery.setLore(lore_JoinLottery);
        is_JoinLottery.setItemMeta(meta_JoinLottery);

        ItemMeta meta_GlobalLotteryStats = is_GlobalLotteryStats.getItemMeta();
        meta_GlobalLotteryStats.setDisplayName(Messages.GLOBAL_LOTTERY_STATS());
        ArrayList<String> lore_GlobalLotteryStats = new ArrayList<String>();
        lore_GlobalLotteryStats.add(Messages.CURRENT_WIN_POOL(FileManager.config.getInt("Lottery.CurrentPool")));
        lore_GlobalLotteryStats.add(Messages.NEXT_LOTTERY_DRAWING(FileManager.config.getInt("Lottery.NextDrawing")));
        if(FileManager.config.getString("Lottery.LastWonNumber") != null) {
                lore_GlobalLotteryStats.add(Messages.LOTTERY_LAST_WON_AMOUNT(FileManager.config.getInt("Lottery.LastWonAmount")));
                lore_GlobalLotteryStats.add(Messages.LOTTERY_LAST_WON_NUMBER(FileManager.config.getInt("Lottery.LastWonNumber")));
        }
        meta_GlobalLotteryStats.setLore(lore_GlobalLotteryStats);
        is_GlobalLotteryStats.setItemMeta(meta_GlobalLotteryStats);

        pane.addItem(new GuiItem(is_JoinLottery, ev -> invClickEv(ev)), 0, 0);
        pane.addItem(new GuiItem(is_GlobalLotteryStats, ev-> ev.setCancelled(true)), 8, 0);
        
    }

    public void invClickEv(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if(Main.econ.getBalance(p) >= FileManager.config.getInt("Lottery.Price")){
                ChatListener.addWritter(p.getUniqueId());
                p.sendMessage(Messages.ADD_LOTTERY_NUMBER(Messages.CANCEL()));
                FileManager.Save(cFile.lottery);
        }else {
                p.sendMessage(Messages.LOTTERY_NOT_ENOUG_MONEY(FileManager.config.getInt("Lottery.Price")));
                e.setCancelled(true);
                p.closeInventory();
        }
        p.closeInventory();
    }

    
	
}
