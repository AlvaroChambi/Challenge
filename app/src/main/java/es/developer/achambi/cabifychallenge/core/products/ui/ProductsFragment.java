package es.developer.achambi.cabifychallenge.core.products.ui;

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

import es.developer.achambi.cabifychallenge.ProductsAssembler;
import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductsViewModel;
import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.core.ui.LoadingBackground;

public class ProductsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private ProductsViewModel productsViewModel;
    private LoadingBackground loadingBackground;

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
        loadingBackground = root.findViewById(R.id.loading_background);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productsViewModel = ViewModelProviders.of(this,
                ProductsAssembler.getViewModelFactory()).get(ProductsViewModel.class);
        loadingBackground.startLoading();
        productsViewModel.getProducts().observe(this, products -> {
            loadingBackground.displayState(products.dataStatePresentation);
            adapter.setData(products.productsPresentations);
            recyclerView.setAdapter( adapter ); });
    }
}
