package com.example.micha.roomwordsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by micha on 4/12/2018.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private List<Word> words;
    private LayoutInflater inflater;

    public WordListAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycleview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if(words != null){
            holder.text.setText(words.get(position).getWord());
        }
        else{
            holder.text.setText("No Words to Display");
        }
    }

    @Override
    public int getItemCount() {
        if(words != null){
            return words.size();
        }
        else return 0;
    }

    static class WordViewHolder extends RecyclerView.ViewHolder{
        private final TextView text;
        public WordViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView);
        }
    }

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }
}
