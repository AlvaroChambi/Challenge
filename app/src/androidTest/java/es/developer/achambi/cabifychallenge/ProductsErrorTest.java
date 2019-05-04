package es.developer.achambi.cabifychallenge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Test;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.core.ui.DataState;
import es.developer.achambi.cabifychallenge.core.products.data.Product;
import es.developer.achambi.cabifychallenge.core.products.data.ProductsRepository;
import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductsViewModelFactory;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ProductsErrorTest extends BaseAutomationTest {
    private ProductsRepository errorRepository = new ProductsRepository(null){
        @Override
        public LiveData<DataState<ArrayList<Product>>> getProducts() {
            final MutableLiveData<DataState<ArrayList<Product>>> data = new MutableLiveData<>();
            DataState<ArrayList<Product>> productsData = new DataState<>();
            productsData.setException(new Exception());
            productsData.setValue(DataState.Value.ERROR);
            data.setValue( productsData );
            return data;
        }
    };

    @Override
    protected void onBeforeActivityLaunched() {
        ProductsAssembler.setViewModelFactory( new ProductsViewModelFactory(
                ChallengeApplication.getInstance(),
                errorRepository ) );
    }

    @Test
    public void testRequestFailed() {
        onView( withId(R.id.error_message_text) ).check( matches(isDisplayed()) );
    }
}
