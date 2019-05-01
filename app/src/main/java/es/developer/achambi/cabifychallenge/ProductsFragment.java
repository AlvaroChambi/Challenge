package es.developer.achambi.cabifychallenge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {
    private ArrayList<Product> products;
    private RecyclerView recyclerView;
    private ProductsAdapter adapter;

    public static ProductsFragment getInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Product product = new Product();
        product.setProductCode(10);
        product.setProductName("Voucher");
        product.setProductPrice(5);

        products = new ArrayList<>();
        products.add(product);

        adapter = new ProductsAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.products_fragment_layout, container, false);
        recyclerView = root.findViewById(R.id.products_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter( adapter );
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setData( ProductPresentation.Builder.buildPresentation(getActivity(),
                products) );
    }
}
