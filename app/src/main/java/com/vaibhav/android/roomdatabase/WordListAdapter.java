package com.vaibhav.android.roomdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mLayoutInfrator;
    private List<Word> mWords;

    public WordListAdapter(Context context) {
        mLayoutInfrator = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInfrator.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null) {
            Word word = mWords.get(position);
            holder.wordViewItem.setText(word.getWord());
        } else {
            holder.wordViewItem.setText("No word");
        }
    }

    @Override
    public int getItemCount() {
        return mWords != null ? mWords.size() : 0;
    }

    public void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordViewItem;

        WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordViewItem = itemView.findViewById(R.id.textView);
        }
    }

}
