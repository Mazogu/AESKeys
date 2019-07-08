package com.example.encyrptedstorage.ui.decrypt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.encyrptedstorage.R;

import java.util.List;

public class DecryptAdapter extends RecyclerView.Adapter<DecryptAdapter.DecryptHolder> {

    private List<String> items;

    public DecryptAdapter(List<String> items){
        this.items = items;
    }

    public void addEntry(String string){
        items.add(string);
        notifyItemInserted(items.size()-1);
    }

    @NonNull
    @Override
    public DecryptHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.holder_decrypt, viewGroup, false);
        return new DecryptHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DecryptHolder decryptHolder, int i) {
        decryptHolder.text.setText(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class DecryptHolder extends RecyclerView.ViewHolder {
        private TextView text;
        public DecryptHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.decrypted_text);
        }
    }
}
