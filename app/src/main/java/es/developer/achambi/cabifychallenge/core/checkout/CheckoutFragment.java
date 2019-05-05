package es.developer.achambi.cabifychallenge.core.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.developer.achambi.cabifychallenge.R;

public class CheckoutFragment extends Fragment {
    private RecyclerView recyclerView;
    private CheckoutProductsAdapter adapter;
    static CheckoutFragment getInstance() {
        return new CheckoutFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CheckoutProductsAdapter();
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
        ArrayList<CheckoutProductPresentation> list = new ArrayList<>();
        CheckoutProductPresentation presentation = new CheckoutProductPresentation(
                "Voucher", "x2", "6,50€", "13€",
                "6,50€", true
        );
        CheckoutProductPresentation presentation2 = new CheckoutProductPresentation(
                "Voucher", "x2", "6,50€", "13€",
                "6,50€", false
        );
        list.add(presentation);
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
    }
}
