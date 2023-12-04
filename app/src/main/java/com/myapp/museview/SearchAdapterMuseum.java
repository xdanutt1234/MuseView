package com.myapp.museview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapterMuseum extends RecyclerView.Adapter<SearchAdapterMuseum.SearchViewHolder> {
    private List<Museum> searchResults;

    public void ClearList()
    {
        searchResults.clear();
    }
    public void LogList()
    {
        for(Museum x : searchResults)
        {
            Log.d("LogList",x.toString());
        }
    }
    public interface OnButtonClickListener{
        void onButtonClick(Museum museum);
    }
    OnButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener listener)
    {
        this.onButtonClickListener = listener;
    }
    public SearchAdapterMuseum()
    {
        this.searchResults = new ArrayList<>();
    }
    public void setSearchResults(List<Museum> searchResults)
    {
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Museum result = searchResults.get(position);

        View.OnClickListener buttonClickListener = v -> {
            if (onButtonClickListener != null)
            {
                onButtonClickListener.onButtonClick(holder.associatedMuseum);
            }
        };

        holder.bind(result, buttonClickListener);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }



    public static class SearchViewHolder extends RecyclerView.ViewHolder
    {
        private TextView musem_id;
        private TextView museum_name;
        private TextView museum_description;
        private Button detailsButton;
        private Museum associatedMuseum;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            museum_name = itemView.findViewById(R.id.textViewResult);
            //museum_description = itemView.findViewById(R.id.descriptionMuseum);
            detailsButton = itemView.findViewById(R.id.buttonDescription);
        }
        public void bind(Museum result,View.OnClickListener buttonClickListener)
        {   Log.d("bind",result.toString());
            associatedMuseum = result;
            museum_name.setText(result.getName());
            //museum_description.setText(result.getDescription());
            detailsButton.setOnClickListener(buttonClickListener);
        }
    }
}
