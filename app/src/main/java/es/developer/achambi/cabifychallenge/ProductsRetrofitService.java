package es.developer.achambi.cabifychallenge;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsRetrofitService {
    @GET("/bins/4bwec")
    Call<ProductsList> getProducts();
}
