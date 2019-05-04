package es.developer.achambi.cabifychallenge;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ProductsViewModel extends AndroidViewModel {
    private LiveData<ArrayList<ProductPresentation>> products;
    private ProductsRepository repository;

    public ProductsViewModel( @NonNull Application application, ProductsRepository repository ) {
        this(application);
        this.repository = repository;
    }

    public ProductsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<ProductPresentation>> getProducts() {
        if( products != null ) {
            return products;
        }
        products = Transformations.map(repository.getProducts(),
                data -> ProductPresentationBuilder.buildPresentation(getApplication(), data));
        return products;
    }
}
