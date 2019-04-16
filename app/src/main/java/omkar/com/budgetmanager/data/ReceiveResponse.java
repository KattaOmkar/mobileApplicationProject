package omkar.com.budgetmanager.data;

import com.google.gson.annotations.SerializedName;

public class ReceiveResponse {
    @SerializedName("receive")
    private Receive receive;

    public Receive getReceive() {
        return receive;
    }

    public void setReceive(Receive receive) {
        this.receive = receive;
    }
}
