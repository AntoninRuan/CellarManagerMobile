package fr.antoninruan.cellarmanager.ui.bottles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.antoninruan.cellarmanager.ModifyBottleActivity;
import fr.antoninruan.cellarmanager.R;

import static android.app.Activity.RESULT_OK;

public class BottlesFragment extends Fragment {

    private BottlesViewModel bottlesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bottlesViewModel =
                new ViewModelProvider(this).get(BottlesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_bottles, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        BottleAdapter adapter = new BottleAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        FloatingActionButton add = root.findViewById(R.id.add);
        add.setOnClickListener((view) -> {

            Intent intent = new Intent(this.getContext(), ModifyBottleActivity.class);
            startActivityForResult(intent, 0);

        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {
                System.out.println(data.getDataString());
            }
        }
    }
}