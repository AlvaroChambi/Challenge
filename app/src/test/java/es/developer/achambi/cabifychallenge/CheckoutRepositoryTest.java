package es.developer.achambi.cabifychallenge;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.checkout.data.CheckoutProductRepository;
import es.developer.achambi.cabifychallenge.core.checkout.data.CheckoutProducts;
import es.developer.achambi.cabifychallenge.core.products.data.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * When the data logic increases it's complexity unit test for the data transformation are being
 * added
 */
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

        CheckoutProducts checkoutProducts = repository.buildCheckoutProducts( products );

        assertNull(checkoutProducts.getProducts().get(0).getDiscountedTotal());
        assertEquals( 30f, checkoutProducts.getProducts().get(0).getTotal(), 0.1);
    }

    @Test
    public void testProductsWithTwoForOneDiscount(){
        Product product = new Product();
        product.setProductCode("VOUCHER");
        product.setProductPrice(5f);
        product.setQuantity(5);
        products.add( product );

        CheckoutProducts checkoutProducts = repository.buildCheckoutProducts( products );

        assertEquals( 15f, checkoutProducts.getProducts().get(0).getDiscountedTotal(), 0.1);
        assertEquals( 25f, checkoutProducts.getProducts().get(0).getTotal(), 0.1);
    }

    @Test
    public void testProductsWithTwoForOneDiscountNotReachingLimits(){
        Product product = new Product();
        product.setProductCode("VOUCHER");
        product.setProductPrice(5f);
        product.setQuantity(1);
        products.add( product );

        CheckoutProducts checkoutProducts = repository.buildCheckoutProducts( products );

        assertNull(checkoutProducts.getProducts().get(0).getDiscountedTotal());
        assertEquals( 5f, checkoutProducts.getProducts().get(0).getTotal(), 0.1);
    }

    @Test
    public void testProductsWithThreeOrMoreDiscount() {
        Product product = new Product();
        product.setProductCode("TSHIRT");
        product.setProductPrice(20f);
        product.setQuantity(4);
        products.add( product );

        CheckoutProducts checkoutProducts = repository.buildCheckoutProducts( products );

        assertEquals( 76f, checkoutProducts.getProducts().get(0).getDiscountedTotal(), 0.1 );
        assertEquals( 80f, checkoutProducts.getProducts().get(0).getTotal(), 0.1 );
    }

    @Test
    public void testProductsWithThreeOrMoreDiscountNotReachingLimits() {
        Product product = new Product();
        product.setProductCode("TSHIRT");
        product.setProductPrice(20f);
        product.setQuantity(2);
        products.add( product );

        CheckoutProducts checkoutProducts = repository.buildCheckoutProducts( products );

        assertNull(checkoutProducts.getProducts().get(0).getDiscountedTotal());
        assertEquals( 40f, checkoutProducts.getProducts().get(0).getTotal(), 0.1);
    }
}
