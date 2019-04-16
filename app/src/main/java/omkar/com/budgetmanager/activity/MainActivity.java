package omkar.com.budgetmanager.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import omkar.com.budgetmanager.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button SpendingButton;
    private Button ReceivingButton;
    private TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpendingButton = findViewById(R.id.spend_button);
        ReceivingButton = findViewById(R.id.receive_button);
        balance = findViewById(R.id.balance);

        SpendingButton.setOnClickListener(this);
        ReceivingButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.spend_button:
                Intent i1 = new Intent(this, MainSpend.class);
                startActivity(i1);
                break;

            case R.id.receive_button:
                Intent i2 = new Intent(this, MainReceive.class);
                startActivity(i2);
                break;

        }

    }

    public void onResume() {

        super.onResume();

        int bal = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("TotalValues",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int rTotal = sharedPreferences.getInt("rTotal",0);
        int sTotal = sharedPreferences.getInt("sTotal",0);

        bal = rTotal-sTotal;

        balance.setText("Remaining Total:" + String.valueOf(bal));

        if(bal<=1000){
            balance.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }else {
            balance.setTextColor(getResources().getColor(android.R.color.background_dark

            ));
        }

    }
}
