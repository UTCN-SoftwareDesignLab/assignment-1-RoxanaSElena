package model;

public class Client {

    private int id;
    private String name;
    private int cardNb;
    private String address;
    private String CNP;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNb() { return cardNb; }

    public void setCardNo(int cardNb) {
        this.cardNb = cardNb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
}
