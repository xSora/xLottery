package me.xSora;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.xSora.FileManager.cFile;
import net.milkbowl.vault.economy.Economy;


public class Main extends JavaPlugin{
	public static Main plugin;
	{
		plugin = this;
	}
	/*
	 * TODO
	 * - Fix Last won Number
	 * 
	 */
	
	public final String PluginName = "xLottery";
    public static Economy econ = null;
	
	public void onEnable() {
		//COMMANDS
		this.getCommand("lottery").setExecutor(new Command());
		
		//EVENTS
		getServer().getPluginManager().registerEvents(new LotteryGUI(), this);
		
		//LOAD AFTER START
		FileManager.LoadConfigStuff();
		setupEconomy();
		LotterySystem.LoadLottery();
		
		new BukkitRunnable()
		{
		    @Override
		    public void run()
		    {
		    	int CurrentCounter = FileManager.config.getInt("Lottery.NextDrawing");
		    	int NewCounter = CurrentCounter -= 1;
		    	FileManager.config.set("Lottery.NextDrawing", NewCounter);
		    	FileManager.Save(cFile.lottery);
		    	if(CurrentCounter <= 0) {
		    		LotterySystem.ChooseWinner();
		    	}
		    }
		}.runTaskTimer(this, 0L, 20L*60);
		
	}
	
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

}
