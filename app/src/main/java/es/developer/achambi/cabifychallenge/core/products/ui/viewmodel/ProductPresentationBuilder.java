package es.developer.achambi.cabifychallenge.core.products.ui.viewmodel;

import android.content.Context;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.core.ui.DataState;
import es.developer.achambi.cabifychallenge.core.products.data.Product;

public class ProductPresentationBuilder {
    static ArrayList<ProductPresentation> buildPresentation(
            Context context, DataState<ArrayList<Product>> products) {
        if( products.getValue() == DataState.Value.ERROR ) {
            return new ArrayList<>();
        }
        ArrayList<ProductPresentation> presentations = new ArrayList<>();
        for (Product product: products.getData()) {
            presentations.add( buildPresentation( context, product ) );
        }
        return presentations;
    }

    private static ProductPresentation buildPresentation( Context context, Product product ) {
        return new ProductPresentation(
                product.getProductCode(),
                product.getProductName(),
                buildFormattedPrice( context,
                        String.valueOf( product.getProductPrice() ))
        );
    }

    public static String buildFormattedPrice( Context context, String price ) {
        return context.getString( R.string.product_price, price );
    }
}
