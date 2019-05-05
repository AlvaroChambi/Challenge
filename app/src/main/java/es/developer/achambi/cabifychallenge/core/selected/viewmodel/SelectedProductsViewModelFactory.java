package es.developer.achambi.cabifychallenge.core.selected.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.developer.achambi.cabifychallenge.core.products.data.ProductsRepository;

public class SelectedProductsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private ProductsRepository repository;
    private Application application;

    public SelectedProductsViewModelFactory(ProductsRepository repository,
                                            Application application) {
        this.repository = repository;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new SelectedProductsViewModel(application, repository);
    }
}
