package me.xSora;

public class Messages {
	public static String COMMAND_USAGE() {
		String FileMessage = FileManager.language.getString("COMMAND_USAGE");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		return ReplacedMessage;
	}


	public static String JOIN_LOTTERY(int Price) {
		String FileMessage = FileManager.language.getString("JOIN_LOTTERY");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+Price);
		return ValuedMessage;
	}
	
	
	public static String BUY_NEW_TICKET() {
		String FileMessage = FileManager.language.getString("BUY_NEW_TICKET");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		return ReplacedMessage;
	}
	
	public static String CURRENT_LOTTERY_TICKET(int LottoID) {
		String FileMessage = FileManager.language.getString("CURRENT_LOTTERY_TICKET");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+LottoID);
		return ValuedMessage;
	}
	
	public static String LOTTERY_NOT_ENOUG_MONEY(int Price) {
		String FileMessage = FileManager.language.getString("LOTTERY_NOT_ENOUG_MONEY");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+Price);
		return ValuedMessage;
	}
	
	public static String NO_LOTTERY_TICKET() {
		String FileMessage = FileManager.language.getString("NO_LOTTERY_TICKET");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		return ReplacedMessage;
	}
	
	public static String GLOBAL_LOTTERY_STATS() {
		String FileMessage = FileManager.language.getString("GLOBAL_LOTTERY_STATS");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		return ReplacedMessage;
	}

	public static String NEXT_LOTTERY_DRAWING(int Minutes) {
		String FileMessage = FileManager.language.getString("NEXT_LOTTERY_DRAWING");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+Minutes);
		return ValuedMessage;
	}

	public static String ADD_LOTTERY_NUMBER() {
		String FileMessage = FileManager.language.getString("ADD_LOTTERY_NUMBER");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		return ReplacedMessage;
	}
	
	public static String ADD_LOTTERY_CANCELLED() {
		String FileMessage = FileManager.language.getString("ADD_LOTTERY_CANCELLED");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		return ReplacedMessage;
	}

	public static String CURRENT_WIN_POOL(int Amount) {
		String FileMessage = FileManager.language.getString("CURRENT_WIN_POOL");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+Amount);
		return ValuedMessage;
	}
	
	public static String LOTTERY_LAST_WINNER(String Winner) {
		String FileMessage = FileManager.language.getString("LOTTERY_LAST_WINNER");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", Winner);
		return ValuedMessage;
	}
	
	public static String LOTTERY_LAST_WON_AMOUNT(int Amount) {
		String FileMessage = FileManager.language.getString("LOTTERY_LAST_WON_AMOUNT");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+Amount);
		return ValuedMessage;
	}
	
	public static String LOTTERY_LAST_WON_NUMBER(int Amount) {
		String FileMessage = FileManager.language.getString("LOTTERY_LAST_WON_NUMBER");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+Amount);
		return ValuedMessage;
	}
	
	public static String LOTTERY_ADD_SUCCESSFULL(String Number) {
		String FileMessage = FileManager.language.getString("LOTTERY_ADD_SUCCESSFULL");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", Number);
		return ValuedMessage;
	}
	
	public static String LOTTERY_ADD_FAILED_TOOBIG(String Number) {
		String FileMessage = FileManager.language.getString("LOTTERY_ADD_FAILED_TOOBIG");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", Number);
		return ValuedMessage;
	}
	
	public static String LOTTERY_ADD_FAILED_NOTVALID(String Number) {
		String FileMessage = FileManager.language.getString("LOTTERY_ADD_FAILED_NOTVALID");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", Number);
		return ValuedMessage;
	}
	
	public static String LOTTERY_NO_WINNERS(int NextRoll) {
		String FileMessage = FileManager.language.getString("LOTTERY_NO_WINNER");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+NextRoll);
		return ValuedMessage;
	}
	
	public static String LOTTERY_WINNERS_BROADCAST(String Winners) {
		String FileMessage = FileManager.language.getString("LOTTERY_WINNERS_BROADCAST");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", Winners);
		return ValuedMessage;
	}
	
	public static String LOTTERY_ROLLED_NUMBER_BROADCAST(int Number) {
		String FileMessage = FileManager.language.getString("LOTTERY_ROLLED_NUMBER_BROADCAST");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+Number);
		return ValuedMessage;
	}
	
	public static String LOTTERY_WINNERS_PRIVATE(int Amount) {
		String FileMessage = FileManager.language.getString("LOTTERY_WINNERS_PRIVATE");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage = ReplacedMessage.replace("#VALUE#", ""+Amount);
		return ValuedMessage;
	}
	
	public static String LOTTERY_OFFLINE_PLAYER_MAIL(String Name, int Amount, int Number) {
		String FileMessage = FileManager.language.getString("LOTTERY_OFFLINE_PLAYER_MAIL");
		String ReplacedMessage = FileMessage.replaceAll("&", "§");
		String ValuedMessage1 = ReplacedMessage.replace("#PLAYERNAME#", Name);
		String ValuedMessage2 = ValuedMessage1.replace("#AMOUNT#", ""+Amount);
		String ValuedMessage3 = ValuedMessage2.replace("#NUMBER#", ""+Number);
		return ValuedMessage3;
	}
	
}
