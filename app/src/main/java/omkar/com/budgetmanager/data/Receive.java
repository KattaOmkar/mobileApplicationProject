package omkar.com.budgetmanager.data;

public class Receive {
    private String ID;
    private String date;
    private String from_reason;
    private String amount;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom_reason() {
        return from_reason;
    }

    public void setFrom_reason(String from_reason) {
        this.from_reason = from_reason;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
