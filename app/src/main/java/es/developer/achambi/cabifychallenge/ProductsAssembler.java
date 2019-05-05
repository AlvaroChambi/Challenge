package es.developer.achambi.cabifychallenge;

import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductsViewModelFactory;
import es.developer.achambi.cabifychallenge.core.selected.viewmodel.CheckoutProductsViewModelFactory;
import es.developer.achambi.cabifychallenge.core.selected.viewmodel.SelectedProductsViewModelFactory;

public class ProductsAssembler {
    private static ProductsViewModelFactory factory;
    private static SelectedProductsViewModelFactory selectedFactory;
    private static CheckoutProductsViewModelFactory checkoutFactory;

    public static CheckoutProductsViewModelFactory getCheckoutFactory() {
        return checkoutFactory;
    }

    public static void setCheckoutFactory(CheckoutProductsViewModelFactory checkoutFactory) {
        ProductsAssembler.checkoutFactory = checkoutFactory;
    }

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
