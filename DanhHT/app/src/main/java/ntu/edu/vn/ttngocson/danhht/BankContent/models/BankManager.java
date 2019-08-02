package ntu.edu.vn.ttngocson.danhht.BankContent.models;

import java.util.ArrayList;

public class BankManager {
    ArrayList<Bank> banks;
    private static BankManager manager;

    private BankManager(){
        banks = new ArrayList<>();
        banks.add(new Bank("CD FAST TRACK 31", Float.parseFloat(("31")), Float.parseFloat("2.58")));
        banks.add(new Bank("CD HOME APPLIANCE 54", Float.parseFloat(("54")), Float.parseFloat("4.50")));
        banks.add(new Bank("CD HIGH TECH 59", Float.parseFloat(("59")), Float.parseFloat("4.92")));
        banks.add(new Bank("CD COMBO 59", Float.parseFloat(("59")), Float.parseFloat("4.92")));
        banks.add(new Bank("CD HIGH RISK 66", Float.parseFloat(("66")), Float.parseFloat("5.5")));
        banks.add(new Bank("CD REFUND 63", Float.parseFloat(("63")), Float.parseFloat("5.25")));
        banks.add(new Bank("CD MILITARY 30", Float.parseFloat(("30")), Float.parseFloat("2.5")));
        banks.add(new Bank("CD MILITARY 55", Float.parseFloat(("55")), Float.parseFloat("4.58")));
        banks.add(new Bank("CD REFUND 66", Float.parseFloat(("66")), Float.parseFloat("5.5")));
    }

    public static BankManager getInstance(){
        if(manager == null){
            manager = new BankManager();
        }
        return manager;
    }

    public ArrayList<Bank> getBanks(){
        return banks;
    }

    public void createBank(Bank bank){
        banks.add(bank);
    }

    public void modBank(int position, Bank bank){
        banks.set(position, bank);
    }
    public void deleteBank(int position){
        banks.remove(position);
    }

}
