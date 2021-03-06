package me.sirhectorin.xlottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.sirhectorin.xlottery.FileManager.cFile;

public class LotterySystem {
    public static Map<String, ArrayList<Integer>> lottery = new HashMap<String, ArrayList<Integer>>();
    public static List<String> Winners = new ArrayList<String>();
    private static final ArrayList<UUID> writing = new ArrayList<>();
    //Lottery Stuff

    public static void LoadLottery() {
        if(FileManager.lottery.getConfigurationSection("Lottery") != null) {
            for (String key : FileManager.lottery.getConfigurationSection("Lottery").getKeys(false)) {
                lottery.put(key, (ArrayList<Integer>) FileManager.lottery.get("Lottery."+key));
            }
        }
    }

    public static void SaveLottery() {
        for (String key : lottery.keySet()) {
            FileManager.lottery.set("Lottery."+key, lottery.get(key));
        }
        FileManager.Save(cFile.config);
    }

    public static void AddLotteryPlayer(Player p, ArrayList<Integer> Numbers) {
        String UUID = p.getUniqueId().toString();
        List<Integer> ls = lottery.get(UUID);
        int mult;
        if(ls != null){
            Numbers = Numbers.stream().filter( n-> !ls.contains(n)).collect(Collectors 
                            .toCollection(ArrayList::new));
            Numbers.addAll(ls);
            mult = Numbers.size() - ls.size();
        }else
            mult = Numbers.size();
    
        lottery.put(UUID, Numbers);
        Main.econ.withdrawPlayer(p, mult * FileManager.config.getInt("Lottery.Price"));
        
        SaveLottery();
        p.sendMessage(Messages.LOTTERY_ADD_SUCCESSFULL(Numbers.stream()
                            .map(Object::toString).collect(Collectors.joining(", "))));
    }

    public static void ChooseWinner() {
        LoadLottery();
        Random rand = new Random();
        int WinningNumber = rand.nextInt(FileManager.config.getInt("Lottery.MaxNumber")+1);
        Bukkit.broadcastMessage(Messages.LOTTERY_ROLLED_NUMBER_BROADCAST(WinningNumber));

        //Get all Winners
        for (Entry<String, ArrayList<Integer>> entry : lottery.entrySet()) {
            if (entry.getValue().indexOf(WinningNumber) != -1) {
                Winners.add(entry.getKey());
            }
        }

        if(Winners.size() == 0) {
                Bukkit.broadcastMessage(Messages.LOTTERY_NO_WINNERS( 
                        FileManager.config.getLong("Lottery.CurrentPool")
                        ,FileManager.config.getInt("Lottery.Drawing")));
        }else {

            int WinAmount = FileManager.config.getInt("Lottery.CurrentPool");
            int HowManyWinners = Winners.size();
            int WinPerPlayer = 0;
            if(HowManyWinners == 1) {
                    WinPerPlayer = WinAmount;
            }else {
                    WinPerPlayer = (WinAmount / HowManyWinners);
            }

            for(int i = 0; i < Winners.size(); i++) {
                String WinnerName = null;
                OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(Winners.get(i)));
                if(op.isOnline()) {
                    Player p = Bukkit.getPlayer(UUID.fromString(Winners.get(i)));
                    WinnerName = p.getName();
                    //Online Winner
                            p.sendMessage(Messages.LOTTERY_WINNERS_PRIVATE(WinPerPlayer));
                            Main.econ.depositPlayer(p, WinPerPlayer);
                }else {
                    //Offline Winner
                    Main.plugin.getServer().dispatchCommand(Main.plugin.getServer().getConsoleSender(), "mail send "+op.getName()+ " "+Messages.LOTTERY_OFFLINE_PLAYER_MAIL(op.getName(), WinPerPlayer, WinningNumber));
                            Main.econ.depositPlayer(op, WinPerPlayer);
                    WinnerName = op.getName();
                }
                Bukkit.broadcastMessage(Messages.LOTTERY_WINNERS_BROADCAST(WinnerName));
                //Lottery Reset
            }
            SetWinnerDisplay(WinAmount, WinningNumber);
            FileManager.config.set("Lottery.CurrentPool", FileManager.config.getLong("Lottery.StartingPool"));
        }
        ClearLottery();
    }

    private static void SetWinnerDisplay(int WonAmount, int WonNumber) {

        FileManager.config.set("Lottery.LastWonAmount", WonAmount);
        FileManager.config.set("LastWonNumber", WonNumber);
    }

    private static void ClearLottery() {
        FileManager.lottery.set("Lottery", null);
        lottery.clear();
        Winners.clear();
        FileManager.Save(cFile.lottery);
        FileManager.config.set("Lottery.NextDrawing", FileManager.config.getInt("Lottery.Drawing"));
    }
    
    public static void pay4Tickets(Player p, String[] Message, boolean isWritting){
        UUID pid = p.getUniqueId();
        List<Integer> nums;
        
            
        nums = Arrays.stream(Message).distinct()
                .flatMap(may -> !may.contains("..") ? Stream.of(may) 
                           : 
                           Stream.of(Utils.generateRange(may))
                ).distinct()
                .map(s -> {
                    try{
                        return Integer.parseInt(s);
                    }catch(NumberFormatException ex){
                        //if (isWritting)
                        //    p.sendMessage(Messages.ADD_LOTTERY_NUMBER(Messages.CANCEL()));
                        //else
                            p.sendMessage(Messages.LOTTERY_ADD_FAILED_NOTVALID(s));
                        return -1;
                    }
                })
                .filter(n -> n <= FileManager.config.getInt("Lottery.MaxNumber") && n >= 0)
                .collect(Collectors.toList());
        if(nums.isEmpty())
            return;
        
        int totalprice = nums.size()* FileManager.config.getInt("Lottery.Price");
        
        if(totalprice <= Main.econ.getBalance(p)){
            LotterySystem.AddLotteryPlayer(p, new ArrayList<Integer>(nums));
            int OldPool = FileManager.config.getInt("Lottery.CurrentPool");
            int ToAdd = nums.size() * ((FileManager.config.getInt("Lottery.Price") - FileManager.config.getInt("Lottery.Fee")));
            FileManager.config.set("Lottery.CurrentPool", (OldPool + ToAdd));
            if(isWritting)
                writing.remove(pid);
            FileManager.Save(FileManager.cFile.lottery);
            Bukkit.getServer().broadcastMessage(Messages.NEW_POOL_UPDATE());
        } else {
            p.sendMessage(Messages.LOTTERY_NOT_ENOUG_MONEY(totalprice));
        }
    }
    public static void addWritter(UUID pid){
        writing.add(pid);
    }   
    public static void delWritter(UUID pid){
        writing.remove(pid);
    }
    public static boolean isWritter(UUID pid){
        return writing.contains(pid);
    }
	
}
