package es.developer.achambi.cabifychallenge.core.checkout.ui.viewmodel;

import android.content.Context;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.core.checkout.data.CheckoutProduct;
import es.developer.achambi.cabifychallenge.core.checkout.data.CheckoutProducts;

import static es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductPresentationBuilder.buildFormattedPrice;
import static es.developer.achambi.cabifychallenge.core.selected.SelectedProductPresentationBuilder.formatQuantity;

public class CheckoutProductPresentationBuilder {
    private static CheckoutProductPresentation buildPresentation(Context context,
                                                    CheckoutProduct checkoutProduct) {
        return new CheckoutProductPresentation(
                checkoutProduct.getProduct().getProductName(),
                formatQuantity(context, checkoutProduct.getProduct().getQuantity()),
                buildFormattedPrice(context,
                        String.valueOf(checkoutProduct.getProduct().getProductPrice())),
                buildFormattedPrice(context,
                        String.valueOf(checkoutProduct.getTotal())),
                buildFormattedPrice(context,
                        String.valueOf(checkoutProduct.getDiscountedTotal())),
                checkoutProduct.getDiscountedTotal() != null
        );
    }

    public static CheckoutProductPresentations buildPresentations(
            Context context, CheckoutProducts products ) {
        return new CheckoutProductPresentations(
                buildPresentation( context, products.getProducts() ),
                formatTotalPrice(context, products.getTotal()) );
    }

    private static String formatTotalPrice( Context context, float totalPrice ) {
        return context.getString(R.string.checkout_total_price, String.valueOf(totalPrice));
    }

    private static ArrayList<CheckoutProductPresentation> buildPresentation(
            Context context, ArrayList<CheckoutProduct> checkoutProducts) {
        ArrayList<CheckoutProductPresentation> result = new ArrayList<>();
        for(CheckoutProduct checkoutProduct : checkoutProducts) {
            result.add( buildPresentation( context, checkoutProduct ) );
        }
        return result;
    }
}
