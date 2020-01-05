package me.sirhectorin.xlottery;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.sirhectorin.xlottery.FileManager.cFile;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class Main extends JavaPlugin implements CommandExecutor, Listener{
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
            getServer().getPluginManager().registerEvents(this, this);

            //EVENTS
            getServer().getPluginManager().registerEvents(new ChatListener(), this);

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
                    } else if(CurrentCounter == 1){
                        Bukkit.getServer().broadcastMessage(Messages.ONE_MINUTE_LEFT());
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
    
    @Override
    public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmd, String arg, String[] args) {
        Player p = (Player)cs;
        if (arg.equalsIgnoreCase("lottery") || arg.equalsIgnoreCase("lot")){
            if(args.length == 0) {
                LotterySystem.LoadLottery();
                new LotteryGUI(p);
                return true;
            }else{
                p.sendMessage(Messages.COMMAND_USAGE());
                return false;
            }
        }
        return true;
    }

}
