package es.developer.achambi.cabifychallenge;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.checkout.CheckoutProduct;
import es.developer.achambi.cabifychallenge.core.checkout.CheckoutProductRepository;
import es.developer.achambi.cabifychallenge.core.products.data.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CheckoutRepositoryTest {
    private CheckoutProductRepository repository;
    private ArrayList<Product> products;

    @Before
    public void setUp() throws Exception {
        repository = new CheckoutProductRepository();
        products = new ArrayList<>();
    }

    @Test
    public void testProductWithoutDiscount(){
        Product product = new Product();
        product.setProductCode("MUG");
        product.setProductPrice(10f);
        product.setQuantity(3);
        products.add( product );

        ArrayList<CheckoutProduct> checkoutProducts = repository.getCheckoutProducts( products );

        assertNull(checkoutProducts.get(0).getDiscountedTotal());
        assertEquals( 30f, checkoutProducts.get(0).getTotal(), 0.1);
    }

    @Test
    public void testProductsWithTwoForOneDiscount(){
        Product product = new Product();
        product.setProductCode("VOUCHER");
        product.setProductPrice(5f);
        product.setQuantity(5);
        products.add( product );

        ArrayList<CheckoutProduct> checkoutProducts = repository.getCheckoutProducts( products );

        assertEquals( 15f, checkoutProducts.get(0).getDiscountedTotal(), 0.1);
        assertEquals( 25f, checkoutProducts.get(0).getTotal(), 0.1);
    }

    @Test
    public void testProductsWithTwoForOneDiscountNotReachingLimits(){
        Product product = new Product();
        product.setProductCode("VOUCHER");
        product.setProductPrice(5f);
        product.setQuantity(1);
        products.add( product );

        ArrayList<CheckoutProduct> checkoutProducts = repository.getCheckoutProducts( products );

        assertNull(checkoutProducts.get(0).getDiscountedTotal());
        assertEquals( 5f, checkoutProducts.get(0).getTotal(), 0.1);
    }

    @Test
    public void testProductsWithThreeOrMoreDiscount() {
        Product product = new Product();
        product.setProductCode("TSHIRT");
        product.setProductPrice(20f);
        product.setQuantity(4);
        products.add( product );

        ArrayList<CheckoutProduct> checkoutProducts = repository.getCheckoutProducts( products );

        assertEquals( 76f, checkoutProducts.get(0).getDiscountedTotal(), 0.1 );
        assertEquals( 80f, checkoutProducts.get(0).getTotal(), 0.1 );
    }

    @Test
    public void testProductsWithThreeOrMoreDiscountNotReachingLimits() {
        Product product = new Product();
        product.setProductCode("TSHIRT");
        product.setProductPrice(20f);
        product.setQuantity(2);
        products.add( product );

        ArrayList<CheckoutProduct> checkoutProducts = repository.getCheckoutProducts( products );

        assertNull(checkoutProducts.get(0).getDiscountedTotal());
        assertEquals( 40f, checkoutProducts.get(0).getTotal(), 0.1);
    }
}
