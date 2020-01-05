package me.sirhectorin.xlottery;

public class Messages {
	public static String COMMAND_USAGE() {
            return Utils.c(FileManager.language.getString("COMMAND_USAGE"));
	}

	public static String JOIN_LOTTERY(int Price) {
            return Utils.c(FileManager.language.getString("JOIN_LOTTERY"))
                .replace("#VALUE#", ""+Price);
	}
	
	public static String BUY_NEW_TICKET() {
            return Utils.c(FileManager.language.getString("BUY_NEW_TICKET"));
	}
        
        public static String CANCEL() {
            return Utils.c(FileManager.language.getString("CANCEL"));
	}
	
	public static String CURRENT_LOTTERY_TICKET(int LottoID) {
            return Utils.c(FileManager.language.getString("CURRENT_LOTTERY_TICKET"))
                    .replace("#VALUE#", ""+LottoID);
	}
	
	public static String LOTTERY_NOT_ENOUG_MONEY(int Price) {
            return Utils.c(FileManager.language.getString("LOTTERY_NOT_ENOUG_MONEY"))
                    .replace("#VALUE#", ""+Price);
	}
	
	public static String NO_LOTTERY_TICKET() {
            return Utils.c(FileManager.language.getString("NO_LOTTERY_TICKET"));
	}
	
	public static String GLOBAL_LOTTERY_STATS() {
		return Utils.c(FileManager.language.getString("GLOBAL_LOTTERY_STATS"));
	}
        
        public static String ONE_MINUTE_LEFT() {
		return Utils.c(FileManager.language.getString("ONE_MINUTE_LEFT"));
	}

	public static String NEXT_LOTTERY_DRAWING(int Minutes) {
            return Utils.c(FileManager.language.getString("NEXT_LOTTERY_DRAWING"))
                    .replace("#VALUE#", ""+Minutes);
	}

	public static String ADD_LOTTERY_NUMBER(String val) {
		return Utils.c(FileManager.language.getString("ADD_LOTTERY_NUMBER").replace("#VALUE#", val));
	}
	
	public static String ADD_LOTTERY_CANCELLED() {
		return Utils.c(FileManager.language.getString("ADD_LOTTERY_CANCELLED"));
	}

	public static String CURRENT_WIN_POOL(int Amount) {
            return Utils.c(FileManager.language.getString("CURRENT_WIN_POOL"))
                .replace("#VALUE#", ""+Amount);
	}
	
	public static String LOTTERY_LAST_WINNER(String Winner) {
            return Utils.c(FileManager.language.getString("LOTTERY_LAST_WINNER"))
                .replace("#VALUE#", Winner);
	}
	
	public static String LOTTERY_LAST_WON_AMOUNT(int Amount) {
            return Utils.c(FileManager.language.getString("LOTTERY_LAST_WON_AMOUNT"))
                .replace("#VALUE#", ""+Amount);
	}
	
	public static String LOTTERY_LAST_WON_NUMBER(int Amount) {
            return Utils.c(FileManager.language.getString("LOTTERY_LAST_WON_NUMBER"))
                .replace("#VALUE#", ""+Amount);
	}
	
	public static String LOTTERY_ADD_SUCCESSFULL(String Number) {
            return Utils.c(FileManager.language.getString("LOTTERY_ADD_SUCCESSFULL"))
                .replace("#VALUE#", Number);
	}
	
	public static String LOTTERY_ADD_FAILED_TOOBIG(String Number) {
            return Utils.c(FileManager.language.getString("LOTTERY_ADD_FAILED_TOOBIG"))
                .replace("#VALUE#", Number);
	}
	
	public static String LOTTERY_ADD_FAILED_NOTVALID(String Number) {
            return Utils.c(FileManager.language.getString("LOTTERY_ADD_FAILED_NOTVALID"))
                .replace("#VALUE#", Number);
	}
	
	public static String LOTTERY_NO_WINNERS(long accum, int NextRoll) {
            return Utils.c(FileManager.language.getString("LOTTERY_NO_WINNERS"))
                .replace("#VALUE#", ""+NextRoll).replace("#POOL#", ""+accum);
	}
	
	public static String LOTTERY_WINNERS_BROADCAST(String Winners) {
            return Utils.c(FileManager.language.getString("LOTTERY_WINNERS_BROADCAST"))
                .replace("#VALUE#", Winners);
	}
	
	public static String LOTTERY_ROLLED_NUMBER_BROADCAST(int Number) {
            return Utils.c(FileManager.language.getString("LOTTERY_ROLLED_NUMBER_BROADCAST"))
                .replace("#VALUE#", ""+Number);
	}
	
	public static String LOTTERY_WINNERS_PRIVATE(int Amount) {
            return Utils.c(FileManager.language.getString("LOTTERY_WINNERS_PRIVATE"))
                .replace("#VALUE#", ""+Amount);
	}
	
	public static String LOTTERY_OFFLINE_PLAYER_MAIL(String Name, int Amount, int Number) {
		return Utils.c(FileManager.language.getString("LOTTERY_OFFLINE_PLAYER_MAIL"))
                    .replace("#NUMBER#", ""+Number).replace("#AMOUNT#", ""+Amount)
                    .replace("#PLAYERNAME#", Name);
	}
	
}
