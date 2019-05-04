package es.developer.achambi.cabifychallenge.core.products.ui.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import es.developer.achambi.cabifychallenge.core.products.data.ProductsRepository;

import static es.developer.achambi.cabifychallenge.core.ui.DataStatePresentationBuilder.buildPresentation;

public class ProductsViewModel extends AndroidViewModel {
    private LiveData<ProductsPresentation> products;
    private ProductsRepository repository;

    public ProductsViewModel( @NonNull Application application, ProductsRepository repository ) {
        this(application);
        this.repository = repository;
    }

    public ProductsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ProductsPresentation> getProducts() {
        if( products != null ) {
            return products;
        }

        products = Transformations.map(repository.getProducts(), data -> new ProductsPresentation(
                ProductPresentationBuilder.buildPresentation(getApplication(), data),
                buildPresentation(getApplication(), data)));
        return products;
    }
}
