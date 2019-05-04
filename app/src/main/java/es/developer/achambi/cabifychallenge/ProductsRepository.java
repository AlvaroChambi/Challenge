package es.developer.achambi.cabifychallenge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsRepository {
    private ProductsRetrofitService retrofitService;
    private LiveData<ArrayList<Product>> cachedProducts;

    public ProductsRepository( ProductsRetrofitService retrofitService ) {
        this.retrofitService = retrofitService;
    }

    public LiveData<ArrayList<Product>> getProducts() {
        if( cachedProducts != null ) {
            return cachedProducts;
        }
        final MutableLiveData<ArrayList<Product>> data = new MutableLiveData<>();
        cachedProducts = data;
        retrofitService.getProducts().enqueue(new Callback<ProductsList>() {
            @Override
            public void onResponse(Call<ProductsList> call, Response<ProductsList> response) {
                data.setValue(response.body().getProducts());
            }

            @Override
            public void onFailure(Call<ProductsList> call, Throwable t) {

            }
        });
        return data;
    }
}
