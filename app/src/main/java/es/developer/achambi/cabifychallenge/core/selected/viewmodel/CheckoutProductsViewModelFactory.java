package es.developer.achambi.cabifychallenge.core.selected.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.developer.achambi.cabifychallenge.core.checkout.data.CheckoutProductRepository;
import es.developer.achambi.cabifychallenge.core.checkout.ui.viewmodel.CheckoutProductsViewModel;

public class CheckoutProductsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private CheckoutProductRepository respository;
    private Application application;

    public CheckoutProductsViewModelFactory(CheckoutProductRepository respository,
                                            Application application) {
        this.respository = respository;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new CheckoutProductsViewModel(application, respository);
    }
}
