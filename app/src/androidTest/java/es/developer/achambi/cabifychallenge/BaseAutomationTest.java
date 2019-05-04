package es.developer.achambi.cabifychallenge;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import es.developer.achambi.cabifychallenge.core.products.ui.ProductsActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public abstract class BaseAutomationTest {
    private IdlingResource idlingResource;
    @Rule
    public ActivityTestRule<ProductsActivity> customTestRule =
            new ActivityTestRule(ProductsActivity.class){
                @Override
                protected void beforeActivityLaunched() {
                    onBeforeActivityLaunched();
                    super.beforeActivityLaunched();
                }
            };

    protected void onBeforeActivityLaunched(){};

    @Before
    public void setup() {
        idlingResource = OkHttp3IdlingResource.create("OKHttp", ChallengeApplication.client);
        Espresso.registerIdlingResources(idlingResource);
    }

    @After
    public void tearDown() {
        Espresso.unregisterIdlingResources(idlingResource);
    }
}
