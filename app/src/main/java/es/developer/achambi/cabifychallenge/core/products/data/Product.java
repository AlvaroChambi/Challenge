package es.developer.achambi.cabifychallenge.core.products.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    @SerializedName("name")
    private String productName;
    @SerializedName("price")
    private float productPrice;
    @SerializedName("code")
    private String productCode;

    private int quantity;

    protected Product(Parcel in) {
        productName = in.readString();
        productPrice = in.readFloat();
        productCode = in.readString();
        quantity = in.readInt();
    }

    public Product() {}

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeFloat(productPrice);
        dest.writeString(productCode);
        dest.writeInt(quantity);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productCode.equals(product.getProductCode());
    }
}
