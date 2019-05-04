package es.developer.achambi.cabifychallenge.core.products.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductsList;
import es.developer.achambi.cabifychallenge.core.ui.DataState;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsRepository {
    private ProductsRetrofitService retrofitService;
    private LiveData<DataState<ArrayList<Product>>> cachedProducts;

    public ProductsRepository( ProductsRetrofitService retrofitService ) {
        this.retrofitService = retrofitService;
    }

    public LiveData<DataState<ArrayList<Product>>> getProducts() {
        if( cachedProducts != null ) {
            return cachedProducts;
        }
        final MutableLiveData<DataState<ArrayList<Product>>> data = new MutableLiveData<>();
        cachedProducts = data;
        DataState<ArrayList<Product>> productsData = new DataState<>();
        retrofitService.getProducts().enqueue(new Callback<ProductsList>() {
            @Override
            public void onResponse(Call<ProductsList> call, Response<ProductsList> response) {
                if(response.isSuccessful()) {
                    productsData.setData( response.body().getProducts() );
                    productsData.setValue(DataState.Value.SUCCESS);
                    data.setValue(productsData);
                } else {
                    productsData.setException(new Exception());
                    productsData.setValue(DataState.Value.ERROR);
                    data.setValue(productsData);
                }
            }

            @Override
            public void onFailure(Call<ProductsList> call, Throwable t) {
                productsData.setException(new Exception(t));
                productsData.setValue(DataState.Value.ERROR);
                data.setValue(productsData);
            }
        });
        return data;
    }
}
