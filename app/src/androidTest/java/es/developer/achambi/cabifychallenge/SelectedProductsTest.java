package es.developer.achambi.cabifychallenge;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static es.developer.achambi.cabifychallenge.matcher.RecyclerViewMatcher.withRecyclerView;
import static org.hamcrest.core.AllOf.allOf;

public class SelectedProductsTest extends BaseAutomationTest {
    @Test
    public void addProductToSelected() {
        onView( allOf(
                isDescendantOfA(withRecyclerView(R.id.products_recycler_view).atPosition(0) ),
                withId(R.id.add_product_image) )).perform(click());

        onView( withRecyclerView(R.id.selected_products_recyclerview).atPosition(0) )
            .check( matches( hasDescendant(withText("Cabify Voucher")) ));
        onView( withRecyclerView(R.id.selected_products_recyclerview).atPosition(0) )
                .check( matches( hasDescendant(withText("x1")) ));
    }

    @Test
    public void deleteProductFromSelected() {
        onView( allOf(
                isDescendantOfA(withRecyclerView(R.id.products_recycler_view).atPosition(0) ),
                withId(R.id.add_product_image) )).perform(click());
        onView( allOf(
                isDescendantOfA(withRecyclerView(R.id.products_recycler_view).atPosition(1) ),
                withId(R.id.add_product_image) )).perform(click());

        onView( allOf(
                isDescendantOfA(withRecyclerView(R.id.selected_products_recyclerview).atPosition(0) ),
                withId(R.id.remove_product_button) )).perform(click());

        onView( withRecyclerView(R.id.selected_products_recyclerview).atPosition(0) )
                .check( matches( hasDescendant(withText("Cabify Voucher")) ));
        onView( withRecyclerView(R.id.selected_products_recyclerview).atPosition(0) )
                .check( matches( hasDescendant(withText("x1")) ));
    }
}
