package es.developer.achambi.cabifychallenge.core.selected;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.R;
import es.developer.achambi.cabifychallenge.databinding.SelectedProductsItemLayoutBinding;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

public class SelectedProductsAdapter
        extends RecyclerView.Adapter<SelectedProductsAdapter.ViewHolder> {
    public interface OnDeleteProductListener {
        void onDeleteProductSelected(String code);
    }
    private SortedList<SelectedProductPresentation> selected;
    private OnDeleteProductListener listener;

    public SelectedProductsAdapter() {
        selected = new SortedList<>(SelectedProductPresentation.class,
                new SortedList.Callback<SelectedProductPresentation>() {
                    @Override
                    public int compare(SelectedProductPresentation o1, SelectedProductPresentation o2) {
                        return o1.code.compareTo(o2.code);
                    }

                    @Override
                    public void onChanged(int position, int count) {
                        notifyItemRangeChanged(position, count);
                    }

                    @Override
                    public boolean areContentsTheSame(SelectedProductPresentation oldItem, SelectedProductPresentation newItem) {
                        return oldItem.code.equals(newItem.code) && oldItem.quantity.equals(newItem.quantity);
                    }

                    @Override
                    public boolean areItemsTheSame(SelectedProductPresentation item1, SelectedProductPresentation item2) {
                        return item1.code.equals(item2.code);
                    }

                    @Override
                    public void onInserted(int position, int count) {
                        notifyItemRangeInserted(position, count);
                    }

                    @Override
                    public void onRemoved(int position, int count) {
                        notifyItemRangeRemoved(position, count);
                    }

                    @Override
                    public void onMoved(int fromPosition, int toPosition) {
                        notifyItemMoved(fromPosition, toPosition);
                    }
                });
    }

    public void setData(ArrayList<SelectedProductPresentation> selectedProducts) {
        selected.beginBatchedUpdates();
        selected.replaceAll( selectedProducts );
        selected.endBatchedUpdates();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selected_products_item_layout, parent, false);
        SelectedProductsItemLayoutBinding binding = DataBindingUtil.bind(root);
        ViewHolder viewHolder = new ViewHolder(binding);
        root.findViewById(R.id.remove_product_button).setOnClickListener((view -> {
            int position = viewHolder.getAdapterPosition();
            if( position != NO_POSITION && listener != null ) {
                listener.onDeleteProductSelected(selected.get(position).code);
            }
        }));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setSelected( selected.get(position) );
    }

    @Override
    public int getItemCount() {
        return selected.size();
    }

    public void setOnDeleteProductListener(OnDeleteProductListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        SelectedProductsItemLayoutBinding binding;

        public ViewHolder(@NonNull SelectedProductsItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
