package model;

import java.time.LocalDate;
import java.util.Date;

public class Account {

    private int id;
    private int idNb;
    private String type;
    private int amount;
    private Date dataOfCreation;
    private int idClient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNb() {
        return idNb;
    }

    public void setIdNb(int idNb) {
        this.idNb = idNb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDataOfCreation() {
        return dataOfCreation;
    }

    public void setDataOfCreation(Date dataOfCreation) {
        this.dataOfCreation = dataOfCreation;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
