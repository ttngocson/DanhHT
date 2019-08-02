package ntu.edu.vn.ttngocson.danhht.BankContent.models;

public class Bank {
    String name;
    float lsn, lst;

    public Bank(String name, float lsn, float lst) {
        this.name = name;
        this.lsn = lsn;
        this.lst = lst;
    }

    public Bank() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLsn() {
        return lsn;
    }

    public void setLsn(float lsn) {
        this.lsn = lsn;
    }

    public float getLst() {
        return lst;
    }

    public void setLst(float lst) {
        this.lst = lst;
    }
}
