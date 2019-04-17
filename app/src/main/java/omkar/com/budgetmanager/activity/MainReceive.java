package omkar.com.budgetmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import omkar.com.budgetmanager.R;
import omkar.com.budgetmanager.api.ApiService;
import omkar.com.budgetmanager.api.ApiServiceFactory;
import omkar.com.budgetmanager.data.Receive;
import omkar.com.budgetmanager.data.ReceiveListResponse;
import omkar.com.budgetmanager.data.ReceiveResponse;
import omkar.com.budgetmanager.view.ReceiveAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainReceive extends AppCompatActivity implements View.OnClickListener, Callback<ReceiveListResponse> {
    private ListView receivesListView;
    private ReceiveAdapter receiveAdapter;
    private FloatingActionButton newReceive;
    private TextView total;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ApiService apiService;
    private List<Receive> receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_main);

        total = findViewById(R.id.totalReceived);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        newReceive = findViewById(R.id.fab_newReceives);
        apiService = ApiServiceFactory.createApiService();

        receiveAdapter = new ReceiveAdapter();
        receivesListView = findViewById(R.id.ListView_Receives);
        receivesListView.setAdapter(receiveAdapter);

        newReceive.setOnClickListener(this);



        fetchReceives();
    }

    @Override
    public void onClick(View v) {
//        Intent rec = new Intent(this,NewReceiveActivity.class);
//        startActivity(rec);
        Intent i = new Intent(this, NewReceiveActivity.class);
        startActivityForResult(i, 1);

    }

    public void fetchReceives() {
        swipeRefreshLayout.setRefreshing(true);
        apiService.getAllReceives().enqueue(this);

        Integer tot =0 ;

//        ReceiveListResponse receiveListResponse = new ReceiveListResponse();
//        Integer size = receiveListResponse.getCount();
//
//
//        Receive receive = new Receive();
//
//          for(int i = 0; i<receiveAdapter.getCount() ; i++){
//
//        total.setText(String.valueOf(tot));



    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void total(){
        int tot =0;
        for (Receive receive :receive){
            tot = tot + Integer.parseInt(receive.getAmount());

        }
        total.setText("Total:"+String.valueOf(tot));

        SharedPreferences sharedPreferences = getSharedPreferences("TotalValues",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("rTotal",tot);
        editor.apply();
    }

    @Override
    public void onResponse(Call<ReceiveListResponse> call, Response<ReceiveListResponse> response) {
        receive = response.body().getReceives();
        swipeRefreshLayout.setRefreshing(false);
        receiveAdapter.setReceives(response.body().getReceives());
        total();
    }





    @Override
    public void onFailure(Call<ReceiveListResponse> call, Throwable t) {

            swipeRefreshLayout.setRefreshing(false);
            t.printStackTrace();




    }

//    public void setupReceives() {
//        Receive receive = new Receive();
//        receive.setDate("01/04/2019");
//        receive.setReason("monthly Books");
//        receive.setAmount("5000");
//
//        Receive r2 = new Receive();
//        r2.setDate("01/05/2019");
//        r2.setAmount("5000");
//        r2.setReason("monthly expenses");
//
//        Receive r3 = new Receive();
//        r3.setReason("graduation party!. Parents gave extra money to enjoy the graduation party.");
//        r3.setAmount("1000");
//        r3.setDate("14/06/2019");
//
//        ArrayList<Receive> receiveList = new ArrayList<>();
//        receiveList.add(receive);
//        receiveList.add(r2);
//        receiveList.add(r3);
//
//
//
//        receiveAdapter.setReceives(receiveList);
//
//
//    }
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    if (requestCode == 1) {

        if(resultCode == Activity.RESULT_OK){
            String result=data.getStringExtra("result");
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        }
    }
}
}
