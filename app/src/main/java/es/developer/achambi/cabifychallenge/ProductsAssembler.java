package es.developer.achambi.cabifychallenge;

public class ProductsAssembler {
    private static ProductsViewModelFactory factory;

    public static void setViewModelFactory( ProductsViewModelFactory factory ) {
        ProductsAssembler.factory = factory;
    }

    public static ProductsViewModelFactory getViewModelFactory() {
        return factory;
    }
}
