package es.developer.achambi.cabifychallenge;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChallengeApplication extends Application {
    private static final String ENDPOINT = "https://api.myjson.com";
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = buildRetrofitClient();

        ProductsAssembler.setViewModelFactory( new ProductsViewModelFactory(this,
                new ProductsRepository( retrofit.create(ProductsRetrofitService.class) ) ) );
    }

    private Retrofit buildRetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
