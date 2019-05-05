package es.developer.achambi.cabifychallenge;

import android.app.Application;

import es.developer.achambi.cabifychallenge.core.products.data.ProductsRepository;
import es.developer.achambi.cabifychallenge.core.products.data.ProductsRetrofitService;
import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductsViewModelFactory;
import es.developer.achambi.cabifychallenge.core.selected.viewmodel.SelectedProductsViewModelFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChallengeApplication extends Application {
    private static final String ENDPOINT = "https://api.myjson.com";
    private static Retrofit retrofit;
    public static OkHttpClient client;
    public static ChallengeApplication application;

    public static ChallengeApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = buildRetrofitClient();
        application = this;
        ProductsRepository productsRepository =
                new ProductsRepository( retrofit.create(ProductsRetrofitService.class) );
        ProductsAssembler.setViewModelFactory( new ProductsViewModelFactory(this,
                productsRepository ) );
        ProductsAssembler.setSelectedFactory( new SelectedProductsViewModelFactory(
                productsRepository, this));
    }

    private Retrofit buildRetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
