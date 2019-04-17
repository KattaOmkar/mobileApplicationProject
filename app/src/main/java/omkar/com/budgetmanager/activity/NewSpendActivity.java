package omkar.com.budgetmanager.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import omkar.com.budgetmanager.R;
import omkar.com.budgetmanager.api.ApiService;
import omkar.com.budgetmanager.api.ApiServiceFactory;
import omkar.com.budgetmanager.data.Receive;
import omkar.com.budgetmanager.data.Spend;
import omkar.com.budgetmanager.data.SpendRequest;
import omkar.com.budgetmanager.data.SpendResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewSpendActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mDisplayDate, Reason, Amount;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button btSpend;
    private ApiService apiService;

    private List<Spend> spend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_spend);

        setContentView(R.layout.activity_new_spend);
        mDisplayDate = findViewById(R.id.dateSpend);
        Reason = findViewById(R.id.reasonSpend);
        Amount = findViewById(R.id.amountSpend);
        btSpend = findViewById(R.id.btSpend);



        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NewSpendActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(String.valueOf(NewSpendActivity.this), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = year + "/" + month + "/" + day;
                mDisplayDate.setText(date);
            }
        };



        btSpend.setOnClickListener(this);

        apiService = ApiServiceFactory.createApiService();

    }

    @Override
    public void onClick(View v){
        submitSpend();
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }


    public void submitSpend()
    {

        String date = mDisplayDate.getText().toString();

        Log.d(String.valueOf(this),"value of date is :" + date);
        final String reason = Reason.getText().toString();
        Log.d(String.valueOf(this),"value of reason is:" + reason );
        String amount = Amount.getText().toString();
        Log.d(String.valueOf(this),"value of amount is:" + amount);

        if (date.isEmpty()){
            mDisplayDate.setError("Please select date");
            mDisplayDate.requestFocus();

        }
        if (reason.isEmpty()){
            Reason.setError("Please enter a Reason for spending");
            Reason.requestFocus();
        }

        if (amount.isEmpty()){
            Amount.setError("Please enter the amount spended");
            Amount.requestFocus();
        }

        if (amount.equals(0)){
            Amount.setError("Amount value can't be null");
            Amount.requestFocus();
        }

//        Spend spend = new Spend();
//        spend.setAmount(amount);
//        spend.setDate(date);
//        spend.setReason(reason);
//
//        SpendRequest spendRequest = new SpendRequest();
//        spendRequest.setSpend(spend);
//
//        apiService.createSpend(spendRequest).enqueue(this);

        Call<ResponseBody> call = ApiServiceFactory
                .getInstance()
                .createApiService()
                .spending(date,reason,amount);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    Toast.makeText(NewSpendActivity.this,s,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NewSpendActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }




}

