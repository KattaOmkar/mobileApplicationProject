package omkar.com.budgetmanager.data;

import com.google.gson.annotations.SerializedName;

public class Spend {
    @SerializedName("ID")
    private String ID;

    @SerializedName("date")
    private String date;

    @SerializedName("reason")
    private String reason;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
