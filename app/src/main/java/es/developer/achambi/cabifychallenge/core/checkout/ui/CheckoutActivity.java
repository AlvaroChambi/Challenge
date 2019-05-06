package es.developer.achambi.cabifychallenge.core.checkout.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.core.products.data.Product;

public class CheckoutActivity extends AppCompatActivity {
    public static Intent getStartIntent(Context context, ArrayList<Product> products) {
        Intent intent = new Intent(context, CheckoutActivity.class);
        intent.putExtras(CheckoutFragment.getFragmentParams(products));
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        setTitle(R.string.checkout_activity_title);
        if(savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add(R.id.base_fragment_frame,
                    CheckoutFragment.getInstance(getIntent().getExtras())).commit();
        }
    }
}
