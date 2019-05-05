package es.developer.achambi.cabifychallenge.core.checkout;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.products.data.Product;

public class CheckoutProductsViewModel extends AndroidViewModel {
    private CheckoutProductRepository respository;
    private LiveData<CheckoutProductPresentations> products;

    public CheckoutProductsViewModel(@NonNull Application application,
                                     CheckoutProductRepository respository) {
        super(application);
        this.respository = respository;
    }

    public LiveData<CheckoutProductPresentations> getProducts(
            ArrayList<Product> baseProducts) {
        if(products != null) {
            return products;
        }
        products = Transformations.map( respository.getCheckoutProducts(baseProducts), data ->
                CheckoutProductPresentationBuilder.buildPresentations(getApplication(),
                        data));
        return products;
    }
}
