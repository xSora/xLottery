package me.xSora;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.xSora.FileManager.cFile;


public class LotteryGUI implements Listener{
	public static Inventory LotteryGUI = Bukkit.createInventory(null, 9, "§6Lottery");
	
	
	private static ItemStack is_JoinLottery;
	private static ItemStack is_GlobalLotteryStats;
	
	public static void SetStats(Player p) {
		
		  is_JoinLottery = new ItemStack(Material.DIAMOND, 1);
		  ItemMeta meta_JoinLottery = is_JoinLottery.getItemMeta();
		  meta_JoinLottery.setDisplayName(Messages.JOIN_LOTTERY(FileManager.config.getInt("Lottery.Price")));
		  ArrayList<String> lore_JoinLottery = new ArrayList<String>();
		  if(Utils.HasBoughtTicket(p)) {
			  meta_JoinLottery.setDisplayName(Messages.BUY_NEW_TICKET());
			  int lottoid = LotterySystem.lottery.get(p.getUniqueId().toString());
			  lore_JoinLottery.add(Messages.CURRENT_LOTTERY_TICKET(lottoid));
		  }else {
			  meta_JoinLottery.setDisplayName(Messages.JOIN_LOTTERY(FileManager.config.getInt("Lottery.Price")));
			  lore_JoinLottery.add(Messages.NO_LOTTERY_TICKET());
		  }
		  
		  meta_JoinLottery.setLore(lore_JoinLottery);
		  is_JoinLottery.setItemMeta(meta_JoinLottery);
		
		is_GlobalLotteryStats = new ItemStack(Material.PAPER, 1);
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
		  
		
		  LotteryGUI.setItem(0, is_JoinLottery);
		  LotteryGUI.setItem(8, is_GlobalLotteryStats);
		
		
	}
	//getName deprecated
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		ItemStack clicked = e.getCurrentItem();
		InventoryView inventory = e.getView();
		if(inventory.getTitle().equals("§6Lottery")) {
			if(clicked == null) {
				return;
			}else {
				if(clicked.getItemMeta().getDisplayName().equals(is_JoinLottery.getItemMeta().getDisplayName())) {
					if(Main.econ.getBalance(p) >= FileManager.config.getInt("Lottery.Price")){
						Main.econ.withdrawPlayer(p, FileManager.config.getInt("Lottery.Price"));
						p.sendMessage(Messages.ADD_LOTTERY_NUMBER());
						FileManager.lottery.set("REGISTER."+p.getUniqueId().toString(), true);
						FileManager.Save(cFile.lottery);
					}else {
						p.sendMessage(Messages.LOTTERY_NOT_ENOUG_MONEY(FileManager.config.getInt("Lottery.Price")));
						e.setCancelled(true);
						p.closeInventory();
					}
				}
				e.setCancelled(true);
				p.closeInventory();
			}
		}
	}
	
	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		boolean register = FileManager.lottery.getBoolean("REGISTER."+p.getUniqueId().toString());
		if(register) {
			e.setCancelled(true);
			String Message = e.getMessage();
			if(Message.equalsIgnoreCase("cancel")) {
				FileManager.lottery.set("REGISTER."+p.getUniqueId().toString(), false);
				p.sendMessage(Messages.ADD_LOTTERY_CANCELLED());
			}else {
				if(Utils.isInt(Message)) {
					int lottoid = Integer.parseInt(Message);
					if(lottoid < FileManager.config.getInt("Lottery.MaxNumber")){
						LotterySystem.AddLotteryPlayer(p, lottoid);
						int OldPool = FileManager.config.getInt("Lottery.CurrentPool");
						int ToAdd = (FileManager.config.getInt("Lottery.Price") - FileManager.config.getInt("Lottery.Fee"));
						FileManager.config.set("Lottery.CurrentPool", (OldPool + ToAdd));
						FileManager.lottery.set("REGISTER."+p.getUniqueId().toString(), false);
						FileManager.Save(cFile.lottery);
						p.sendMessage(Messages.LOTTERY_ADD_SUCCESSFULL(Message));
					}else {
						p.sendMessage(Messages.LOTTERY_ADD_FAILED_TOOBIG(Message));
						FileManager.Save(cFile.lottery);
					}
				}else {
					p.sendMessage(Messages.LOTTERY_ADD_FAILED_NOTVALID(Message));
					FileManager.Save(cFile.lottery);
				}
			}
		}
	}
	
}
