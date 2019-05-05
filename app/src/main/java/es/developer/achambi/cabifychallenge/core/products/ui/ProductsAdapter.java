package es.developer.achambi.cabifychallenge.core.products.ui;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.databinding.ProductsItemLayoutBinding;
import es.developer.achambi.cabifychallenge.core.products.ui.viewmodel.ProductPresentation;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    public interface OnAddProductListener {
        void onProductAddSelected(ProductPresentation productPresentation);
    }
    private ArrayList<ProductPresentation> products;
    private OnAddProductListener listener;

    public ProductsAdapter() {
        this.products = new ArrayList<>();
    }

    public void setData(ArrayList<ProductPresentation> products) {
        this.products = products;
    }

    public void setListener(OnAddProductListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.products_item_layout, viewGroup, false);
        ProductsItemLayoutBinding binding = DataBindingUtil.bind(root);
        ViewHolder viewHolder = new ViewHolder(binding);
        root.findViewById(R.id.add_product_image).setOnClickListener((view) -> {
            int position = viewHolder.getAdapterPosition();
            if(position != NO_POSITION && listener != null) {
                listener.onProductAddSelected(products.get(position));
            }
        });
        return viewHolder;
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
