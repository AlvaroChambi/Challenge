package es.developer.achambi.cabifychallenge.core.products.ui.viewmodel;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import es.developer.achambi.cabifychallenge.core.products.data.ProductsRepository;

public class ProductsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private ProductsRepository repository;
    private Application application;

    public ProductsViewModelFactory(Application application, ProductsRepository repository) {
        this.application = application;
        this.repository = repository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new ProductsViewModel(application ,repository);
    }
}
