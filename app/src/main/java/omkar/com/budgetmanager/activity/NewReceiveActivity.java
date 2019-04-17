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
import omkar.com.budgetmanager.data.ReceiveRequest;
import omkar.com.budgetmanager.data.ReceiveResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewReceiveActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mDisplayDate, Reason, Amount;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button btReceive;
    private ApiService apiService;

    private List<Receive> receive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_receive);
        mDisplayDate = findViewById(R.id.dateReceive);
        Reason = findViewById(R.id.reasonReceive);
        Amount = findViewById(R.id.amountReceive);
        btReceive = findViewById(R.id.btreceive);

        btReceive.setOnClickListener(this);

        apiService = ApiServiceFactory.createApiService();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NewReceiveActivity.this,
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
                Log.d(String.valueOf(NewReceiveActivity.this), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = year + "/" + month + "/" + day;
                mDisplayDate.setText(date);
            }
        };

    }

    @Override
    public void onClick(View v) {
        String date = mDisplayDate.getText().toString();
        String from_reason = Reason.getText().toString();
        String amount = Amount.getText().toString();

        if (date.isEmpty()){
            mDisplayDate.setError("Please select a date of receiving");
            mDisplayDate.requestFocus();
        }

        if (from_reason.isEmpty()){
            Reason.setError("Please enter a reason for receiving");
            Reason.requestFocus();

        }

        if (amount.isEmpty()){
            Amount.setError("Please enter the amount recieved");
            Amount.requestFocus();
        }

        if (Amount.equals(0)){
            Amount.setError("Please enter the valid amount of recieved");
            Amount.requestFocus();
        }

//        Receive receive = new Receive();
//        receive.setAmount(amount);
//        receive.setDate(date);
//        receive.setFrom_reason(reason);
//
//        ReceiveRequest receiveRequest = new ReceiveRequest();
//        receiveRequest.setReceive(receive);
//
//        apiService.createReceive(receiveRequest).enqueue(this);

        Call<ResponseBody> call = ApiServiceFactory
                .getInstance()
                .createApiService()
                .receiving(date,from_reason,amount);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    Toast.makeText(NewReceiveActivity.this,s,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NewReceiveActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();





    }


}
