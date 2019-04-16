package omkar.com.budgetmanager.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.56.1/final/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService createApiService(){
        return retrofit.create(ApiService.class);
    }
}
