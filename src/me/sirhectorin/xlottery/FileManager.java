package me.sirhectorin.xlottery;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager{	//Manages all Files
	
	enum cFile {
		config,
		language,
		lottery
	}
	
	private static File configFile;
	public static YamlConfiguration config;
	
	private static File lotteryFile;
	public static YamlConfiguration lottery;
	
	private static File languageFile;
	public static YamlConfiguration language;
	
	
	public static void LoadConfigStuff() {
	    configFile = new File(Main.plugin.getDataFolder(), "config.yml");
	    
	    lotteryFile = new File(Main.plugin.getDataFolder(), "lottery.yml");
	    
	    languageFile = new File(Main.plugin.getDataFolder(), "language.yml");
	    
	    config = YamlConfiguration.loadConfiguration(configFile);
	    lottery = YamlConfiguration.loadConfiguration(lotteryFile);
	    language = YamlConfiguration.loadConfiguration(languageFile);
	    CheckFiles();
	    
	}
	
	private static void CheckFiles() {
		if(!configFile.exists()) {
			CreateFile(cFile.config);
			
		}
		if(!languageFile.exists()) {
			CreateFile(cFile.language);
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

	@SuppressWarnings("incomplete-switch") //We do not need to create an lottery.yml because it is created in the LotterySystem.java !
	public static void CreateFile(cFile f) {
		switch(f) {
			case config:
				config.set("Lottery.Drawing", 720);		//New Drawing
				config.set("Lottery.NextDrawing", 720);
				config.set("Lottery.Price", 1000);
				config.set("Lottery.Fee", 200);
				config.set("Lottery.CurrentPool", 0);
				config.set("Lottery.MaxNumber", 999999);
				config.set("Lottery.LastWonAmount", 0);
				config.set("Lottery.LastWonNumber", 0);
				Save(cFile.config);
				break;
				//
			case language:
				language.set("GUI_TITLE", "&6Lottery");
				language.set("COMMAND_USAGE", "&cUsage: /lottery");
				language.set("JOIN_LOTTERY", "&aJoin Lottery (Price: #VALUE# per ticket)");
				language.set("BUY_NEW_TICKET", "&bBuy More Tickets");
				language.set("CURRENT_LOTTERY_TICKET", "&6Current Lottery Tickets:");
				language.set("NO_LOTTERY_TICKET", "&cYou do not own a Lottery Ticket!");
				language.set("GLOBAL_LOTTERY_STATS", "&6Global Lottery Stats");
				language.set("ADD_LOTTERY_NUMBER", "&6Please Enter your Lottery Numbers separated by spaces, type &c#VALUE# &6to Cancel!");
				language.set("NEXT_LOTTERY_DRAWING", "&eNext Lottery Drawing in #VALUE# Minutes");
				language.set("ADD_LOTTERY_CANCELLED", "&6Buying Lottery Ticket Cancelled!");
				language.set("CURRENT_WIN_POOL", "&eCurrent Win Pool: #VALUE#");
				language.set("LOTTERY_LAST_WINNER", "&eLast Lottery Winner: #VALUE#");
				language.set("LOTTERY_LAST_WON_AMOUNT", "&eLast Won Amount: #VALUE#");
				language.set("LOTTERY_LAST_WON_NUMBER", "&eLast Won Number: #VALUE#");
				
				language.set("LOTTERY_NOT_ENOUG_MONEY", "&cNot Enough Money, Required: #VALUE#");
				
				language.set("LOTTERY_ADD_SUCCESSFULL", "&6Good Luck! Your Numbers: #VALUE#!");
				language.set("LOTTERY_ADD_FAILED_TOOBIG", "&6'#VALUE#' is too Big!");
				language.set("LOTTERY_ADD_FAILED_NOTVALID", "&6'#VALUE#' is not a Valid Number");
				
				language.set("LOTTERY_NO_WINNER", "&cLottery has no Winners. Acumulated poll: &a#POOL# &6Next Roll in #VALUE# Minutes!");
				language.set("LOTTERY_WINNERS_BROADCAST", "&6#VALUE# WON IN THE LOTTERY! Congratulations!");
				language.set("LOTTERY_WINNERS_PRIVATE", "&6You Won #VALUE# in the Lottery, Congratulations!");
				language.set("LOTTERY_ROLLED_NUMBER_BROADCAST", "&6Rolled Number is: #VALUE#");
				language.set("LOTTERY_OFFLINE_PLAYER_MAIL", "&aCongratulations &b#PLAYERNAME#&a, Your Number &c#NUMBER#&a won the Lottery, you were awarded &c#AMOUNT# &a$.");
				language.set("CANCEL", "cancel");
                                language.set("ONE_MINUTE_LEFT", "&6Only 1 minute left to roll the lottery");
                                
				Save(cFile.language);
				break;
		}
	}
}
