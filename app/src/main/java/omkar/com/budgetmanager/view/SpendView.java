package omkar.com.budgetmanager.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import omkar.com.budgetmanager.R;
import omkar.com.budgetmanager.data.Spend;

public class SpendView extends FrameLayout {
    private TextView sdateTv;
    private TextView sreasonTv;
    private TextView samountTv;

    public SpendView(Context context){
        super(context);
        initViews();
    }
    public void initViews(){
        inflate(getContext(), R.layout.view_spend,this);
        sdateTv =  findViewById(R.id.Sdate);
        sreasonTv = findViewById(R.id.Sreason);
        samountTv = findViewById(R.id.Samount);

    }

    public void setSpend(Spend spend){
        String date = spend.getDate();
        String reason = spend.getReason();
        String amount = spend.getAmount();

        sdateTv.setText(date);
        sreasonTv.setText(reason);
        samountTv.setText(amount);

    }
}
