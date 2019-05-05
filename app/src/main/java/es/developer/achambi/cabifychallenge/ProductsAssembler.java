package es.developer.achambi.cabifychallenge;

import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductsViewModelFactory;
import es.developer.achambi.cabifychallenge.core.selected.viewmodel.SelectedProductsViewModelFactory;

public class ProductsAssembler {
    private static ProductsViewModelFactory factory;
    private static SelectedProductsViewModelFactory selectedFactory;

    public static SelectedProductsViewModelFactory getSelectedFactory() {
        return selectedFactory;
    }

    public static void setSelectedFactory(
            SelectedProductsViewModelFactory selectedFactory) {
        ProductsAssembler.selectedFactory = selectedFactory;
    }

    public static void setViewModelFactory(ProductsViewModelFactory factory ) {
        ProductsAssembler.factory = factory;
    }

    public static ProductsViewModelFactory getViewModelFactory() {
        return factory;
    }
}
