package es.developer.achambi.cabifychallenge;

import android.content.Context;

import java.util.ArrayList;

public class ProductPresentationBuilder {
    public static ArrayList<ProductPresentation> buildPresentation(
            Context context, ArrayList<Product> products) {
        ArrayList<ProductPresentation> presentations = new ArrayList<>();
        for (Product product: products) {
            presentations.add( buildPresentation( context, product ) );
        }
        return presentations;
    }

    static ProductPresentation buildPresentation( Context context, Product product ) {
        return new ProductPresentation(
                product.getProductName(),
                buildFormattedPrice( context,
                        String.valueOf( product.getProductPrice() ) )
        );
    }

    private static String buildFormattedPrice( Context context, String price ) {
        return context.getString( R.string.product_price, price );
    }
}
