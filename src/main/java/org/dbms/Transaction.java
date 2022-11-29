package org.dbms;

import java.util.Date;

public class Transaction {
    private int id;
    private int pID;
    private int sID;
    private Date date;

    public Transaction(int id, int pID, int sID, Date date) {
        this.id = id;
        this.pID = pID;
        this.sID = sID;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
