package es.developer.achambi.cabifychallenge;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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
