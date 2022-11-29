package org.dbms;

public class Part {
    private int id;
    private String name;
    private int price;
    private int mID;
    private int cID;
    private int warrantyPeriod;
    private int availableQuantity;

    public Part(int id, String name, int price, int mID, int cID, int warrantyPeriod, int availableQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mID = mID;
        this.cID = cID;
        this.warrantyPeriod = warrantyPeriod;
        this.availableQuantity = availableQuantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
