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
import es.developer.achambi.cabifychallenge.core.checkout.ui.CheckoutActivity;
import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductPresentation;
import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductsViewModel;
import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.core.selected.SelectedProductsAdapter;
import es.developer.achambi.cabifychallenge.core.selected.viewmodel.SelectedProductsViewModel;
import es.developer.achambi.cabifychallenge.core.ui.LoadingBackground;

/**
 * In order to handle activity/fragment life cycles i'm using android ViewModel and LiveData. The
 * products data will persist through activity recreations, but due to time constraints this screen
 * won't persist it's state if the application is being recreated. In order to implement this
 * feature the savedInstanceState should be used trying to keep there the minimal required data to
 * restore the view state.
 */
public class ProductsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private LoadingBackground loadingBackground;
    private SelectedProductsAdapter selectedProductsAdapter;
    private SelectedProductsViewModel selectedProductsViewModel;

    static ProductsFragment getInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProductsAdapter();
        selectedProductsAdapter = new SelectedProductsAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.products_fragment_layout, container, false);
        recyclerView = root.findViewById(R.id.products_recycler_view);
        RecyclerView selectedRecyclerView = root.findViewById(R.id.selected_products_recyclerview);
        root.findViewById(R.id.checkout_button).setOnClickListener((view) -> checkoutSelected());
        adapter.setListener(this::onProductAddSelected);
        selectedProductsAdapter.setOnDeleteProductListener(this::onDeleteProductSelected);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        selectedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        selectedRecyclerView.setAdapter(selectedProductsAdapter);
        loadingBackground = root.findViewById(R.id.loading_background);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductsViewModel productsViewModel = ViewModelProviders.of(this,
                ProductsAssembler.getViewModelFactory()).get(ProductsViewModel.class);
        selectedProductsViewModel = ViewModelProviders.of(this,
                ProductsAssembler.getSelectedFactory()).get(SelectedProductsViewModel.class);
        loadingBackground.startLoading();

        productsViewModel.getProducts().observe(this, products -> {
            loadingBackground.displayState(products.dataStatePresentation);
            adapter.setData(products.productsPresentations);
            recyclerView.setAdapter( adapter ); });
        selectedProductsViewModel.getSelectedProducts().observe(this, products -> {
            selectedProductsAdapter.setData(products);
        });
    }

    private void checkoutSelected() {
        startActivity(CheckoutActivity.getStartIntent(this.getActivity(),
                selectedProductsViewModel.getRawSelectedProducts()));
    }

    private void onProductAddSelected(ProductPresentation productPresentation) {
        selectedProductsViewModel.addProduct(productPresentation.code).observe(
                this, products -> selectedProductsAdapter.setData(products));
    }

    private void onDeleteProductSelected(String code) {
        selectedProductsViewModel.deleteProduct(code).observe(
                this, products -> selectedProductsAdapter.setData(products));
    }
}
