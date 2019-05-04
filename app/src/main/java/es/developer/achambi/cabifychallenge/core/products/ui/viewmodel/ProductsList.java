package es.developer.achambi.cabifychallenge.core.products.ui.viewmodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.products.data.Product;

public class ProductsList {
    @SerializedName("products")
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
