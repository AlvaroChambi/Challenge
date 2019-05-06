package es.developer.achambi.cabifychallenge.core.checkout.ui.viewmodel;

public class CheckoutProductPresentation {
    public final String name;
    public final String quantity;
    public final String unitPrice;
    public final String totalPrice;
    public final String discountedPrice;
    public final boolean hasDiscount;

    public CheckoutProductPresentation(String name, String quantity, String unitPrice,
                                       String totalPrice, String discountedPrice,
                                       boolean hasDiscount) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.discountedPrice = discountedPrice;
        this.hasDiscount = hasDiscount;
    }
}
