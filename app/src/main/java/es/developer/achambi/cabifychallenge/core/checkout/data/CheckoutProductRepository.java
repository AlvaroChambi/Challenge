package es.developer.achambi.cabifychallenge.core.checkout.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.products.data.Product;

public class CheckoutProductRepository {
    private static final Float TSHIRT_DISCOUNT_PRICE = 19f;
    private ArrayList<Discount> availableDiscounts;

    public CheckoutProductRepository() {
        //TODO Given the small scope of this challenge i didn't consider necessary more than
        // a static list of discounts, but the access to the available discounts could be moved to
        // a standalone data source to be fetched from a database or a from a network service
        availableDiscounts = new ArrayList<>();
        Discount discount0 = new Discount();
        discount0.setType(Discount.Type.TWO_FOR_ONE);
        discount0.setProductCode("VOUCHER");
        Discount discount1 = new Discount();
        discount1.setType(Discount.Type.THREE_OR_MORE);
        discount1.setProductCode("TSHIRT");
        availableDiscounts.add(discount0);
        availableDiscounts.add(discount1);
    }

    public LiveData<CheckoutProducts> getCheckoutProducts(ArrayList<Product> products) {
        final MutableLiveData<CheckoutProducts> mutableData = new MutableLiveData<>();
        CheckoutProducts list = buildCheckoutProducts(products);
        mutableData.setValue( list );
        list.setTotal(buildTotal(list.getProducts()));
        return mutableData;
    }

    public CheckoutProducts buildCheckoutProducts(ArrayList<Product> products) {
        CheckoutProducts list = new CheckoutProducts();
        for (Product product : products) {
            list.getProducts().add( buildCheckoutProduct( product ) );
        }
        return list;
    }

    private float buildTotal(ArrayList<CheckoutProduct> products) {
        float totalPrice = 0;
        for(CheckoutProduct product : products) {
            if(product.getDiscountedTotal() != null) {
                totalPrice += product.getDiscountedTotal();
            } else {
                totalPrice += product.getTotal();
            }
        }
        return totalPrice;
    }

    private CheckoutProduct buildCheckoutProduct( Product product ) {
        CheckoutProduct checkoutProduct = new CheckoutProduct();
        checkoutProduct.setProduct(product);
        for(Discount discount : availableDiscounts) {
            if( product.getProductCode().equals( discount.getProductCode() ) ) {
                checkoutProduct.setDiscount( discount );
            }
        }
        checkoutProduct.setTotal(buildBaseTotal(product));
        Float discountTotal = buildProductDiscountTotal(checkoutProduct);
        if(discountTotal != null) {
            checkoutProduct.setDiscountedTotal( discountTotal );
        }
        return checkoutProduct;
    }

    private Float buildBaseTotal( Product product ) {
        return product.getQuantity() * product.getProductPrice();
    }

    private Float buildProductDiscountTotal( CheckoutProduct checkoutProduct ) {
        Float result = null;
        if(checkoutProduct.getDiscount() == null) {
            return null;
        }
        switch (checkoutProduct.getDiscount().getType()) {
            case TWO_FOR_ONE:
                if( checkoutProduct.getProduct().getQuantity() >= 2 ) {
                    float price = checkoutProduct.getProduct().getProductPrice();
                    int discountPairs = checkoutProduct.getProduct().getQuantity() / 2;
                    int remaining = checkoutProduct.getProduct().getQuantity() - discountPairs * 2;
                    result = discountPairs * price
                            + remaining * price;
                }
                break;
            case THREE_OR_MORE:
                if( checkoutProduct.getProduct().getQuantity() >= 3 ) {
                    float price = TSHIRT_DISCOUNT_PRICE;
                    return price * checkoutProduct.getProduct().getQuantity();
                }
        }
        return result;
    }
}
