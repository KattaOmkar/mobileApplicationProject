package omkar.com.budgetmanager.data;

import com.google.gson.annotations.SerializedName;

public class SpendResponse {
    @SerializedName("spend")
    private Spend spend;

    public Spend getSpend() {
        return spend;
    }

    public void setSpend(Spend spend) {
        this.spend = spend;
    }
}
