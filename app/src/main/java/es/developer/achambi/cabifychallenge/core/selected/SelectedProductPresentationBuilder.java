package es.developer.achambi.cabifychallenge.core.selected;

import android.content.Context;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.core.products.data.Product;

public class SelectedProductPresentationBuilder {
    public static SelectedProductPresentation buildPresentation(Context context, Product product) {
        return new SelectedProductPresentation(
                product.getProductCode(),
                product.getProductName(),
                formatQuantity(context, product.getQuantity()) );
    }

    public static ArrayList<SelectedProductPresentation> buildPresentation(
            Context context, ArrayList<Product> products ) {
        ArrayList<SelectedProductPresentation> presentations = new ArrayList<>();
        for(Product product: products) {
            presentations.add( buildPresentation(context, product) );
        }
        return presentations;
    }

    private static String formatQuantity(Context context, int quantity) {
        return context.getString(R.string.quantity_format, String.valueOf(quantity));
    }
}
