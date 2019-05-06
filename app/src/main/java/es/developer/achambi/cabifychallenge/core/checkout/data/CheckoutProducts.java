package es.developer.achambi.cabifychallenge.core.checkout.data;

import java.util.ArrayList;

public class CheckoutProducts {
    private ArrayList<CheckoutProduct> products;
    private float total;

    public CheckoutProducts() {
        products = new ArrayList<>();
    }

    public ArrayList<CheckoutProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<CheckoutProduct> products) {
        this.products = products;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
