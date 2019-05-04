package es.developer.achambi.cabifychallenge;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.databinding.ProductsItemLayoutBinding;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private ArrayList<ProductPresentation> products;

    public ProductsAdapter() {
        this.products = new ArrayList<>();
    }

    public void setData(ArrayList<ProductPresentation> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.products_item_layout, viewGroup, false);
        ProductsItemLayoutBinding binding = DataBindingUtil.bind(root);
        return new ViewHolder( binding );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.setProduct(products.get(i));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ProductsItemLayoutBinding binding;
        ViewHolder(@NonNull ProductsItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
