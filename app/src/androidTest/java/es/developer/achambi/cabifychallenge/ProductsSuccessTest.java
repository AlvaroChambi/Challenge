package es.developer.achambi.cabifychallenge;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ProductsSuccessTest extends BaseAutomationTest{
    @Test
    public void testRequestFailed() {
        onView( withId(R.id.products_recycler_view) ).check(matches(isDisplayed()));
    }
}
