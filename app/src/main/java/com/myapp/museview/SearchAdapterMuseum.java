/**
 * Adapter pentru afișarea rezultatelor căutării muzeelor într-un RecyclerView.
 */
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

    /**
     * Metodă pentru golirea listei de rezultate a căutării.
     */
    public void ClearList()
    {
        searchResults.clear();
    }

    /**
     * Metodă pentru afișarea conținutului listei de rezultate în loguri.
     */
    public void LogList()
    {
        for(Museum x : searchResults)
        {
            Log.d("LogList",x.toString());
        }
    }
    /**
     * Interfață pentru gestionarea evenimentului de apăsare a unui buton în listă.
     */
    public interface OnButtonClickListener{
        void onButtonClick(Museum museum);
    }
    OnButtonClickListener onButtonClickListener;

    /**
     * Metodă pentru setarea listener-ului pentru evenimentul de apăsare a butonului.
     *
     * @param listener Listener-ul pentru evenimentul de apăsare a butonului.
     */
    public void setOnButtonClickListener(OnButtonClickListener listener)
    {
        this.onButtonClickListener = listener;
    }
    public SearchAdapterMuseum()
    {
        this.searchResults = new ArrayList<>();
    }

    /**
     * Metodă pentru setarea listei de rezultate a căutării.
     *
     * @param searchResults Lista de muzeelor rezultate din căutare.
     */
    public void setSearchResults(List<Museum> searchResults)
    {
        this.searchResults = searchResults;
    }

    /**
     * Metodă pentru crearea unui nou ViewHolder.
     *
     * @param parent   Grupul părinte la care se adaugă noul ViewHolder.
     * @param viewType Tipul de view.
     * @return Un nou ViewHolder.
     */


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new SearchViewHolder(view);
    }

    /**
     * Metodă pentru legarea datelor la ViewHolder.
     *
     * @param holder   ViewHolder-ul la care se leagă datele.
     * @param position Poziția elementului în listă.
     */
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
    /**
     * Metodă pentru obținerea numărului de elemente din listă.
     *
     * @return Numărul de elemente din listă.
     */


    @Override
    public int getItemCount() {
        return searchResults.size();
    }



    /**
     * ViewHolder pentru afișarea datelor în RecyclerView.
     */
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
        /**
         * Metodă pentru legarea datelor la ViewHolder.
         *
         * @param result            Muzeul asociat datelor.
         * @param buttonClickListener Listener pentru evenimentul de apăsare a butonului.
         */
        public void bind(Museum result,View.OnClickListener buttonClickListener)
        {   Log.d("bind",result.toString());
            associatedMuseum = result;
            museum_name.setText(result.getName());
            //museum_description.setText(result.getDescription());
            detailsButton.setOnClickListener(buttonClickListener);
        }
    }
}
