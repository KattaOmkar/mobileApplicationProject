package omkar.com.budgetmanager.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceiveListResponse {
    @SerializedName("count")
    private int count;

    @SerializedName("receives")
    private List<Receive> receives;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Receive> getReceives() {
        return receives;
    }

    public void setReceives(List<Receive> receives) {
        this.receives = receives;
    }
}
