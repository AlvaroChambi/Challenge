package es.developer.achambi.cabifychallenge.core.products.ui.viewmodel;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.ui.DataStatePresentation;

public class ProductsPresentation {
    public final ArrayList<ProductPresentation> productsPresentations;
    public final DataStatePresentation dataStatePresentation;

    ProductsPresentation(ArrayList<ProductPresentation> productsPresentations,
                                DataStatePresentation dataStatePresentation) {
        this.productsPresentations = productsPresentations;
        this.dataStatePresentation = dataStatePresentation;
    }
}
