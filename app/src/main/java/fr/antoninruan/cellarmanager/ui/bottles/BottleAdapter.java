package fr.antoninruan.cellarmanager.ui.bottles;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import fr.antoninruan.cellarmanager.MainActivity;
import fr.antoninruan.cellarmanager.ModifyBottleActivity;
import fr.antoninruan.cellarmanager.R;
import fr.antoninruan.cellarmanager.model.Bottle;

public class BottleAdapter extends RecyclerView.Adapter<BottleAdapter.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.bottle_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bottle bottle = MainActivity.getBottlesSortedBy(MainActivity.SortCriteria.NAME).get(position);
        holder.imageView.setImageResource(R.drawable.logo);
        switch (bottle.getType()) {
            case ROUGE:
                holder.imageView.setColorFilter(Color.RED);
                break;
            case BLANC:
                holder.imageView.setColorFilter(ContextCompat.getColor(holder.linearLayout.getContext(), R.color.blanc));
                break;
            case ROSE:
                holder.imageView.setColorFilter(ContextCompat.getColor(holder.linearLayout.getContext(), R.color.rose));
                break;
            case CHAMPAGNE:
                holder.imageView.setColorFilter(ContextCompat.getColor(holder.linearLayout.getContext(), R.color.champagne));
                break;
            case AUTRES:
                break;
        }
        holder.name.setText(bottle.getName());
        String info = bottle.getDomain() + ", " + bottle.getEdition() + ", " + bottle.getYear();
        holder.info.setText(info);
        holder.linearLayout.setOnClickListener((view) -> {
            Intent intent = new Intent(holder.linearLayout.getContext(), ModifyBottleActivity.class);
            intent.setData(Uri.parse(String.valueOf(bottle.getId())));
            holder.linearLayout.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return MainActivity.getBottles().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView imageView;
        protected TextView name;
        protected TextView info;
        protected LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.name = itemView.findViewById(R.id.name);
            this.info = itemView.findViewById(R.id.info);
            this.linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
