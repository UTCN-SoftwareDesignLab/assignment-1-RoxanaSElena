package model.DTO;

public class TransferDTO {

    private String to;
    private String from;
    private int amount;

    public TransferDTO (String to, String from, int amount)
    {
        this.to = to;
        this.from = from;
        this.amount = amount;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
