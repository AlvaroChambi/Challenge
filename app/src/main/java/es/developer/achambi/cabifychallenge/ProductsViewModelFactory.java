package es.developer.achambi.cabifychallenge;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

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
