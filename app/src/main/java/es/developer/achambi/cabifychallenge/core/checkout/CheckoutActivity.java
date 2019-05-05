package es.developer.achambi.cabifychallenge.core.checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import es.developer.achambi.cabifychallenge.R;

public class CheckoutActivity extends AppCompatActivity {
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CheckoutActivity.class);
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
                    CheckoutFragment.getInstance()).commit();
        }
    }
}
