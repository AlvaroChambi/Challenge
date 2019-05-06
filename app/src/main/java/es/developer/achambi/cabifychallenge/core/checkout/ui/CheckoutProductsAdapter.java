package es.developer.achambi.cabifychallenge.core.checkout.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.core.checkout.ui.viewmodel.CheckoutProductPresentation;
import es.developer.achambi.cabifychallenge.databinding.CheckoutItemProductsBinding;

public class CheckoutProductsAdapter extends RecyclerView.Adapter<CheckoutProductsAdapter.ViewHolder> {
    private ArrayList<CheckoutProductPresentation> products;

    public void setData(ArrayList<CheckoutProductPresentation> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkout_item_products, parent, false);
        CheckoutItemProductsBinding binding = DataBindingUtil.bind(root);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setProduct(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CheckoutItemProductsBinding binding;
        ViewHolder(@NonNull CheckoutItemProductsBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
