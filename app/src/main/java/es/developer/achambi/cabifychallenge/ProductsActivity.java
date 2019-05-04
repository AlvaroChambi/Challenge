package es.developer.achambi.cabifychallenge;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

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
