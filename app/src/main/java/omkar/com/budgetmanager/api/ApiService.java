package omkar.com.budgetmanager.api;

import okhttp3.ResponseBody;
import omkar.com.budgetmanager.data.ReceiveListResponse;
import omkar.com.budgetmanager.data.ReceiveRequest;
import omkar.com.budgetmanager.data.ReceiveResponse;
import omkar.com.budgetmanager.data.SpendListResponse;
import omkar.com.budgetmanager.data.SpendRequest;
import omkar.com.budgetmanager.data.SpendResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @GET("receiving")
    Call<ReceiveListResponse> getAllReceives();

//    @Headers("Content-Type: application/json")
//    @POST("receiving")
//    Call<ReceiveResponse> createReceive(@Body ReceiveRequest body);

    @GET("spending")
    Call<SpendListResponse> getAllSpends();

//    @Headers("Content-Type: applications/json")
//    @POST("spending")
//    Call<SpendResponse> createSpend(@Body SpendRequest body);
    @FormUrlEncoded
    @POST("spending")
    Call<ResponseBody> spending(
            @Field("date") String date,
            @Field("reason") String reason,
            @Field("amount") String amount
    );

    @FormUrlEncoded
    @POST("receiving")
    Call<ResponseBody> receiving(
            @Field("date") String date,
            @Field("from_reason") String from_reason,
            @Field("amount") String amount
    );
}
