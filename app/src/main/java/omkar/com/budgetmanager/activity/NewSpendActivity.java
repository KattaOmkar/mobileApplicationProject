package omkar.com.budgetmanager.activity;

import android.app.DatePickerDialog;
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

import java.util.Calendar;
import java.util.List;

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

public class NewSpendActivity extends AppCompatActivity implements View.OnClickListener, Callback<SpendResponse> {
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

        btSpend.setOnClickListener(this);

        apiService = ApiServiceFactory.createApiService();

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

    }

    @Override
    public void onClick(View v){
        submitSpend();
    }


    public void submitSpend()
    {

        String date = mDisplayDate.getText().toString();
        String reason = Reason.getText().toString();
        String amount = Amount.getText().toString();

        Spend spend = new Spend();
        spend.setAmount(amount);
        spend.setDate(date);
        spend.setReason(reason);

        SpendRequest spendRequest = new SpendRequest();
        spendRequest.setSpend(spend);

        apiService.createSpend(spendRequest).enqueue(this);

    }

    @Override
    public void onResponse(Call<SpendResponse> call, Response<SpendResponse> response) {
//        ArrayList<String> partners = response.body();

//        spend = (List<Spend>) response.body().getSpend();
        Toast.makeText(this,
                "Created Spend With ID = "+response.body().getSpend().getID(),
                Toast.LENGTH_SHORT).show();

        Log.i("get Response",String.valueOf(response.body().getSpend()));
    }

    @Override
    public void onFailure(Call<SpendResponse> call, Throwable t) {
        Toast.makeText(this,"Unable to add Spend to the Spend List",Toast.LENGTH_LONG).show();


    }
}

