/**
 * Adapter pentru afișarea recenziilor într-un RecyclerView.
 */
package com.myapp.museview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<MuseumReview> reviews;

    public ReviewAdapter(List<MuseumReview> reviews) {
        this.reviews = reviews;
    }

    /**
     * Metodă apelată când un nou ViewHolder trebuie creat.
     *
     * @param parent   Grupul în care ViewHolder va fi plasat.
     * @param viewType Tipul de vizualizare a elementului în RecyclerView.
     * @return Un ViewHolder care conține o reprezentare a elementului.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item_layout, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Metodă apelată pentru a lega datele recenziei la un ViewHolder specific.
     *
     * @param holder   ViewHolder la care datele trebuie să fie legate.
     * @param position Poziția elementului în listă.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MuseumReview review = reviews.get(position);

        // Bind the review data to the ViewHolder views
        //Log.d("USERNAME",review.username);
        Log.d("RATING",Integer.toString(review.getRating()));
        //Log.d("COMMENT",review.getComment());
        holder.userName.setText(review.username);
        holder.ratingView.setRating(review.getRating());
        holder.commentTextView.setText(review.getComment());

    }

    /**
     * Metodă care furnizează numărul total de elemente din setul de date.
     *
     * @return Numărul total de recenzii.
     */
    @Override
    public int getItemCount() {
        return reviews.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RatingBar ratingView;
        public TextView commentTextView;
        public TextView userName;

        /**
         * Constructor pentru ViewHolder.
         *
         * @param itemView Vizualizarea elementului în RecyclerView.
         */
        public ViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userTextView);
            ratingView = itemView.findViewById(R.id.ratingBarListActivity);
            commentTextView = itemView.findViewById(R.id.commentTextView);
            // Initialize other views as needed
        }
    }

}
