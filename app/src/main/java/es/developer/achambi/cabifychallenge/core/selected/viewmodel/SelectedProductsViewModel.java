package es.developer.achambi.cabifychallenge.core.selected.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.products.data.ProductsRepository;
import es.developer.achambi.cabifychallenge.core.selected.SelectedProductPresentation;
import es.developer.achambi.cabifychallenge.core.selected.SelectedProductPresentationBuilder;

public class SelectedProductsViewModel extends AndroidViewModel {
    private ProductsRepository repository;
    private LiveData<ArrayList<SelectedProductPresentation>> selectedProducts;

    public SelectedProductsViewModel(@NonNull Application application,
                                     ProductsRepository repository) {
        super(application);
        this.repository = repository;
    }

    public LiveData<ArrayList<SelectedProductPresentation>> getSelectedProducts() {
        if( selectedProducts != null ) {
            return selectedProducts;
        }
        selectedProducts = Transformations.map(repository.getSelectedProducts(), data ->
                SelectedProductPresentationBuilder.buildPresentation(getApplication(), data));
        return selectedProducts;
    }

    public LiveData<ArrayList<SelectedProductPresentation>> addProduct(String code) {
        selectedProducts =
                Transformations.map(repository.addProduct(code), data ->
                SelectedProductPresentationBuilder.buildPresentation(getApplication(), data));
        return selectedProducts;
    }

    public LiveData<ArrayList<SelectedProductPresentation>> deleteProduct(String code) {
        selectedProducts =
                Transformations.map(repository.deleteProduct(code), data ->
                SelectedProductPresentationBuilder.buildPresentation(getApplication(), data));
        return selectedProducts;
    }
}
