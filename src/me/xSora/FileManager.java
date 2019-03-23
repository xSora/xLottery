package me.xSora;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager{
	
	enum cFile {
		config,
		language,
		lottery
	}
	//Config Manager//
	
	private static File configFile;
	private static File BconfigFile;
	public static YamlConfiguration config;
	
	public static File lotteryFile;
	public static YamlConfiguration lottery;
	
	private static File languageFile;
	private static File BlanguageFile;
	public static YamlConfiguration language;
	
	//TODO Fix Files
	
	
	public static void LoadConfigStuff() {
	    configFile = new File(Main.plugin.getDataFolder(), "config.yml");
	    
	    lotteryFile = new File(Main.plugin.getDataFolder(), "lottery.yml");
	    
	    languageFile = new File(Main.plugin.getDataFolder(), "language.yml");
	    
	    BconfigFile = new File(Main.plugin.getDataFolder(), "config.yml.BACKUP");
	    //BlotteryFile = new File(Main.plugin.getDataFolder(), "lottery.yml.BACKUP");
	    BlanguageFile = new File(Main.plugin.getDataFolder(), "language.yml.BACKUP");
	    
	    config = YamlConfiguration.loadConfiguration(configFile);
	    lottery = YamlConfiguration.loadConfiguration(lotteryFile);
	    language = YamlConfiguration.loadConfiguration(languageFile);
	    CheckFiles();
	    
	}
	
	private static void CheckFiles() {
	    
		String PluginVersion = Main.PluginVersion;
		if(!configFile.exists()) {
			CreateFile(cFile.config);
		}else {
			if(!config.getString("Version").equals(PluginVersion)) {
				System.err.println("WARNING! Your Config File Version is NOT UP TO DATE, This MAY cause Errors!");
			}
			
		}
		if(!languageFile.exists()) {
			CreateFile(cFile.language);
		}else {
			if(!language.getString("Version").equals(PluginVersion)) {
				System.err.println("WARNING! Your Language File Version is NOT UP TO DATE, This MAY cause Errors!");
			}
		}
		
	}
	
	@SuppressWarnings("incomplete-switch")
	public static void Delete(cFile f) {
		switch(f) {
		case config:
			configFile.renameTo(BconfigFile);
			CreateFile(cFile.config);
			break;
		case language:
			languageFile.renameTo(BlanguageFile);
			CreateFile(cFile.config);
			break;
		}
	}
	
	public static void Save(cFile f) {
		switch(f) {
			case config:
			try {
				config.save(configFile);
			} catch (IOException e) {
				System.err.println("Error Trying to save config.yml !");
				e.printStackTrace();
			}
				break;
				
			case language:
				try {
				language.save(languageFile);
			} catch (IOException e) {
				System.err.println("Error Trying to save language.yml !");
				e.printStackTrace();
			}
				break;
				
			case lottery:
			try {
				lottery.save(lotteryFile);
			} catch (IOException e) {
				System.err.println("Error Trying to save lottery.yml !");
				e.printStackTrace();
			}
				break;
		}
	}

	@SuppressWarnings("incomplete-switch")
	public static void CreateFile(cFile f) {
		switch(f) {
			case config:
				config.set("Version", Main.PluginVersion);
				config.set("Lottery.Drawing", 720);		//Drawing in 720 Minutes
				config.set("Lottery.NextDrawing", 720);
				config.set("Lottery.Price", 1000);
				config.set("Lottery.Fee", 200);
				config.set("Lottery.CurrentPool", 0);
				config.set("Lottery.MaxNumber", 999999);
				config.set("Lottery.LastWinner", null);
				config.set("Lottery.LastWonAmount", 0);
				config.set("Lottery.LastWonNumber", 0);
				Save(cFile.config);
				break;
				//
			case language:
				language.set("Version", Main.PluginVersion);
				language.set("COMMAND_USAGE", "&cUsage: /lottery");
				language.set("JOIN_LOTTERY", "&aJoin Lottery (Price: #VALUE#)");
				language.set("BUY_NEW_TICKET", "&bBuy New Ticket");
				language.set("CURRENT_LOTTERY_TICKET", "&6Current Lottery Ticket: #VALUE#");
				language.set("NO_LOTTERY_TICKET", "&cYou do not own a Lottery Ticket!");
				language.set("GLOBAL_LOTTERY_STATS", "&6Global Lottery Stats");
				language.set("ADD_LOTTERY_NUMBER", "&6Please Enter your Lottery Number, Type cancel to Cancel!");
				language.set("NEXT_LOTTERY_DRAWING", "&eNext Lottery Drawing in #VALUE# Minutes");
				language.set("ADD_LOTTERY_CANCELLED", "&6Buying Lottery Ticket Cancelled!");
				language.set("CURRENT_WIN_POOL", "&eCurrent Win Pool: #VALUE#");
				language.set("LOTTERY_LAST_WINNER", "&eLast Lottery Winner: #VALUE#");
				language.set("LOTTERY_LAST_WON_AMOUNT", "&eLast Won Amount: #VALUE#");
				language.set("LOTTERY_LAST_WON_NUMBER", "&eLast Won Number: #VALUE#");
				
				language.set("LOTTERY_NOT_ENOUG_MONEY", "&cNot Enough Money, Required: #VALUE#");
				
				language.set("LOTTERY_ADD_SUCCESSFULL", "&6'#VALUE#' was Successfully Registered!");
				language.set("LOTTERY_ADD_FAILED_TOOBIG", "&6'#VALUE#' is too Big!");
				language.set("LOTTERY_ADD_FAILED_NOTVALID", "&6'#VALUE#' is not a Valid Number");
				
				language.set("LOTTERY_NO_WINNER", "&cLottery has no Winners. Next Roll in #VALUE# Minutes!");
				language.set("LOTTERY_WINNERS_BROADCAST", "&6#VALUE# WON IN THE LOTTERY! Congratulations!");
				language.set("LOTTERY_WINNERS_PRIVATE", "&6You Won #VALUE# in the Lottery, Congratulations!");
				language.set("LOTTERY_ROLLED_NUMBER_BROADCAST", "&6Rolled Number is: #VALUE#");
				language.set("LOTTERY_OFFLINE_PLAYER_MAIL", "&aCongratulations &b#PLAYERNAME#&a, Your Number &c#NUMBER#&a won the Lottery, you were awarded &c#AMOUNT# &a$.");
				
				Save(cFile.language);
				break;
				//
		}
	}
}
