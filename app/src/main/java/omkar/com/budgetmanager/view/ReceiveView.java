package omkar.com.budgetmanager.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import omkar.com.budgetmanager.R;
import omkar.com.budgetmanager.data.Receive;

public class ReceiveView extends FrameLayout {
    private TextView dateTv;
    private TextView reasonTv;
    private TextView amountTv;

    public ReceiveView(Context context){
        super(context);
        initViews();

    }
    public void initViews(){
        inflate(getContext(), R.layout.view_receive,this);
        dateTv =  findViewById(R.id.date);
        reasonTv = findViewById(R.id.reason);
        amountTv = findViewById(R.id.amount);

    }

    public void setReceive(Receive receive){
        String date = receive.getDate();
        String reason = receive.getFrom_reason();
        String amount = receive.getAmount();

        dateTv.setText(date);
        reasonTv.setText(reason);
        amountTv.setText(amount);

    }
}
