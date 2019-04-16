package omkar.com.budgetmanager.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpendListResponse {
    @SerializedName("count")
    private int count;

    @SerializedName("spends")
    private List<Spend> spends;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Spend> getSpends() {
        return spends;
    }

    public void setSpends(List<Spend> spends) {
        this.spends = spends;
    }
}
