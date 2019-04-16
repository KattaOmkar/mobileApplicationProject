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
import omkar.com.budgetmanager.data.ReceiveRequest;
import omkar.com.budgetmanager.data.ReceiveResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewReceiveActivity extends AppCompatActivity implements View.OnClickListener, Callback<ReceiveResponse> {
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
        String reason = Reason.getText().toString();
        String amount = Amount.getText().toString();

        Receive receive = new Receive();
        receive.setAmount(amount);
        receive.setDate(date);
        receive.setFrom_reason(reason);

        ReceiveRequest receiveRequest = new ReceiveRequest();
        receiveRequest.setReceive(receive);

        apiService.createReceive(receiveRequest).enqueue(this);



    }

    @Override
    public void onResponse(Call<ReceiveResponse> call, Response<ReceiveResponse> response) {

        //receive = (List<Receive>) response.body().getReceive();
        Toast.makeText(this,
                "Created Spend With ID = "+response.body().getReceive().getID(),
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure(Call<ReceiveResponse> call, Throwable t) {
        Toast.makeText(this,"Unable to add Receive to the Spend List",Toast.LENGTH_LONG).show();
    }
}
