package es.developer.achambi.cabifychallenge;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * UI integration test avoiding any mock in order to the test the whole data flow
 */
public class ProductsSuccessTest extends BaseAutomationTest{
    @Test
    public void testRequestFailed() {
        onView(withId(R.id.discount_description_text)).check(matches(isDisplayed()));
        onView( withId(R.id.products_recycler_view) ).check(matches(isDisplayed()));
        onView(withId(R.id.checkout_button)).check(matches(isDisplayed()));
    }
}
