package omkar.com.budgetmanager.api;

import omkar.com.budgetmanager.data.ReceiveListResponse;
import omkar.com.budgetmanager.data.ReceiveRequest;
import omkar.com.budgetmanager.data.ReceiveResponse;
import omkar.com.budgetmanager.data.SpendListResponse;
import omkar.com.budgetmanager.data.SpendRequest;
import omkar.com.budgetmanager.data.SpendResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("receiving")
    Call<ReceiveListResponse> getAllReceives();

    @POST("receiving_up")
    Call<ReceiveResponse> createReceive(@Body ReceiveRequest body);

    @GET("spending")
    Call<SpendListResponse> getAllSpends();

    @POST("spending_up")
    Call<SpendResponse> createSpend(@Body SpendRequest body);
}
