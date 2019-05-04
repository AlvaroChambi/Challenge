package es.developer.achambi.cabifychallenge.core.products.data;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("name")
    private String productName;
    @SerializedName("price")
    private double productPrice;
    @SerializedName("code")
    private String productCode;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
