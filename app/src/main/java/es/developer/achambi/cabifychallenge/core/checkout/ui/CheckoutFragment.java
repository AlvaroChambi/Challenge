package es.developer.achambi.cabifychallenge.core.checkout.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.ProductsAssembler;
import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.core.checkout.ui.viewmodel.CheckoutProductsViewModel;
import es.developer.achambi.cabifychallenge.core.products.data.Product;

public class CheckoutFragment extends Fragment {
    private static final String PRODUCTS_EXTRA_KEY = "PRODUCTS_EXTRA_KEY";
    private RecyclerView recyclerView;
    private CheckoutProductsAdapter adapter;
    private ArrayList<Product> baseProducts;

    static Bundle getFragmentParams(ArrayList<Product> products) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PRODUCTS_EXTRA_KEY, products);
        return bundle;
    }

    static CheckoutFragment getInstance(Bundle args) {
        CheckoutFragment fragment = new CheckoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CheckoutProductsAdapter();
        baseProducts = getArguments().getParcelableArrayList(PRODUCTS_EXTRA_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.checkout_fragment_layout, container, false);
        recyclerView = root.findViewById(R.id.checkout_products_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CheckoutProductsViewModel viewModel = ViewModelProviders.of(this,
                ProductsAssembler.getCheckoutFactory()).get(CheckoutProductsViewModel.class);
        viewModel.getProducts(baseProducts).observe(this, products -> {
            Button total = view.findViewById(R.id.checkout_total_button);
            total.setText(products.total);
            adapter.setData(products.presentations);
            recyclerView.setAdapter(adapter);
        });
    }
}
