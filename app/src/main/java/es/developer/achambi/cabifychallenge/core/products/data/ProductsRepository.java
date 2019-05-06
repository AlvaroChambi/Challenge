package es.developer.achambi.cabifychallenge.core.products.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductsList;
import es.developer.achambi.cabifychallenge.core.ui.DataState;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Given the scope of the project the repositories are accessing the data directly through the
 * retrofit client, but another level of abstraction to access the data will be preferred
 */
public class ProductsRepository {
    private ProductsRetrofitService retrofitService;
    private LiveData<DataState<ArrayList<Product>>> cachedProducts;
    private LiveData<ArrayList<Product>> selectedProducts;

    public ProductsRepository( ProductsRetrofitService retrofitService ) {
        this.retrofitService = retrofitService;
        MutableLiveData<ArrayList<Product>> data = new MutableLiveData<>();
        data.postValue(new ArrayList<>());
        selectedProducts = data;
    }

    public LiveData<ArrayList<Product>> addProduct(String code) {
        MutableLiveData<ArrayList<Product>> mutable = new MutableLiveData<>();
        mutable.setValue( selectedProducts.getValue() );
        selectedProducts = mutable;
        for(Product product: cachedProducts.getValue().getData()) {
            if(product.getProductCode().equals(code)) {
                product.setQuantity( product.getQuantity() + 1 );
                mutable.getValue().remove(product);
                mutable.getValue().add(product);
                return mutable;
            }
        }
        return selectedProducts;
    }

    public LiveData<ArrayList<Product>> deleteProduct(String code) {
        MutableLiveData<ArrayList<Product>> mutable = new MutableLiveData<>();
        mutable.setValue( selectedProducts.getValue() );
        ArrayList<Product> list = selectedProducts.getValue();
        selectedProducts = mutable;
        list.remove(findSelectedProduct(code));
        mutable.setValue(list);
        return selectedProducts;
    }

    private Product findSelectedProduct(String code) {
        Product result = null;
        for(Product product : selectedProducts.getValue()) {
            if(product.getProductCode().equals(code)) {
                result = product;
            }
        }
        return result;
    }

    public LiveData<ArrayList<Product>> getSelectedProducts() {
        if(selectedProducts != null) {
            return selectedProducts;
        }
        MutableLiveData<ArrayList<Product>> products = new MutableLiveData<>();
        selectedProducts = products;
        ArrayList<Product> selectedProducts = new ArrayList<>();
        DataState dataState = cachedProducts.getValue();
        if(dataState == null) {
            return products;
        }
        for(Product product: cachedProducts.getValue().getData()) {
            if(product.getQuantity() > 0) {
                selectedProducts.add(product);
            }
        }
        products.setValue(selectedProducts);
        return products;
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
