package omkar.com.budgetmanager.data;

import com.google.gson.annotations.SerializedName;

public class Receive {

    @SerializedName("ID")
    private String ID;

    @SerializedName("date")
    private String date;

    @SerializedName("from_reason")
    private String from_reason;

    @SerializedName("amount")
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
