package omkar.com.budgetmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import omkar.com.budgetmanager.R;
import omkar.com.budgetmanager.api.ApiService;
import omkar.com.budgetmanager.api.ApiServiceFactory;
import omkar.com.budgetmanager.data.Spend;
import omkar.com.budgetmanager.data.SpendListResponse;
import omkar.com.budgetmanager.view.SpendAdapater;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainSpend extends AppCompatActivity implements Callback<SpendListResponse>, View.OnClickListener {
    private ListView spendsListView;
    private SpendAdapater spendAdapater;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ApiService apiService;
    private FloatingActionButton fab_newSpends;

    private TextView sTotal;

    private List<Spend> spend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spend_main);

        spendAdapater = new SpendAdapater();
        spendsListView = findViewById(R.id.ListView_Spends);
        spendsListView.setAdapter(spendAdapater);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        apiService = ApiServiceFactory.createApiService();
        fab_newSpends = findViewById(R.id.fab_newSpends);
        fab_newSpends.setOnClickListener(this);

        sTotal = findViewById(R.id.totalSpend);



        fetchSpends();
    }

    public void fetchSpends(){
        swipeRefreshLayout.setRefreshing(true);
        apiService.getAllSpends().enqueue(this);
    }

    public void total(){
        int total = 0;
        for(Spend spend :spend){
            total = total + Integer.parseInt(spend.getAmount());
        }
        sTotal.setText("Total:"+String.valueOf(total));

//        Intent intent = new Intent(this,MainActivity.class);
//        intent.putExtra("spendTotal",total);
//        startActivity(intent);

        SharedPreferences sharedPreferences = getSharedPreferences("TotalValues",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sTotal",total);
        editor.apply();
    }

    @Override
    public void onResponse(Call<SpendListResponse> call, Response<SpendListResponse> response) {
        swipeRefreshLayout.setRefreshing(false);
        spendAdapater.setSpends(response.body().getSpends());
        spend = response.body().getSpends();
        total();
    }



    @Override
    public void onFailure(Call<SpendListResponse> call, Throwable t) {
        swipeRefreshLayout.setRefreshing(false);
        t.printStackTrace();

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, NewSpendActivity.class);
        startActivityForResult(i, 1);
    }

//    public void setupSpends(){
//        Spend s1 = new Spend();
//        s1.setAmount("200");
//        s1.setDate("02/04/2019");
//        s1.setReason("spent money on grocessories");
//
//        Spend s2 = new Spend();
//        s2.setReason("Spent money for travelling for going and coming to Future Park");
//        s2.setDate("03/04/2019");
//        s2.setAmount("70");
//
//        ArrayList<Spend> spendList = new ArrayList<>();
//        spendList.add(s1);
//        spendList.add(s2);
//
//        spendAdapater.setSpends(spendList);
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
