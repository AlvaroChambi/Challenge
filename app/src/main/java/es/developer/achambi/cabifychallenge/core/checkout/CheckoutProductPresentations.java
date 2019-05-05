package es.developer.achambi.cabifychallenge.core.checkout;

import java.util.ArrayList;

public class CheckoutProductPresentations {
    public final ArrayList<CheckoutProductPresentation> presentations;
    public final String total;

    public CheckoutProductPresentations(ArrayList<CheckoutProductPresentation> presentations,
                                        String total) {
        this.presentations = presentations;
        this.total = total;
    }
}
