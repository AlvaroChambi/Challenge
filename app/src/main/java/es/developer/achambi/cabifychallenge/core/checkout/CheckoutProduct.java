package es.developer.achambi.cabifychallenge.core.checkout;


import es.developer.achambi.cabifychallenge.core.products.data.Product;

public class CheckoutProduct {
    private Product product;
    private Discount discount;
    private Float total;
    private Float discountedTotal;

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getDiscountedTotal() {
        return discountedTotal;
    }

    public void setDiscountedTotal(Float discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
