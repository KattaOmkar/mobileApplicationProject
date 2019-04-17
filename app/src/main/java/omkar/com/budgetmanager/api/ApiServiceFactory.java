package omkar.com.budgetmanager.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {
    private  static ApiServiceFactory mInstance;
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.56.1/final/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static synchronized ApiServiceFactory getInstance(){
        if (mInstance == null){
            mInstance = new ApiServiceFactory();
        }
        return mInstance;
    }

    public static ApiService createApiService(){
        return retrofit.create(ApiService.class);
    }
}
