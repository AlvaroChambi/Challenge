package es.developer.achambi.cabifychallenge;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProductsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private ProductsViewModel productsViewModel;

    public static ProductsFragment getInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProductsAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.products_fragment_layout, container, false);
        recyclerView = root.findViewById(R.id.products_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productsViewModel = ViewModelProviders.of(this,
                ProductsAssembler.getViewModelFactory()).get(ProductsViewModel.class);
        productsViewModel.getProducts().observe(this,
                products -> {
                    adapter.setData(products);
                    recyclerView.setAdapter( adapter );
                });
    }
}
