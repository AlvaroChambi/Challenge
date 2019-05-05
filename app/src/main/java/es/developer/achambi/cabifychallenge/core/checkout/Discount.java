package es.developer.achambi.cabifychallenge.core.checkout;

public class Discount {
    public enum Type {
        TWO_FOR_ONE,
        THREE_OR_MORE
    }
    private Type type;
    private String productCode;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
