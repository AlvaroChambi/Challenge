package es.developer.achambi.cabifychallenge.core.products.data;


import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductsList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsRetrofitService {
    @GET("/bins/4bwec")
    Call<ProductsList> getProducts();
}
