package es.developer.achambi.cabifychallenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class ProductsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        if( savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add( R.id.base_fragment_frame,
                    ProductsFragment.getInstance() ).commit();
        }
    }
}
