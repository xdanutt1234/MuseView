/**
 * Activitatea pentru căutarea muzeelor și afișarea rezultatelor.
 */
package com.myapp.museview;

import android.app.appsearch.SearchResult;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchAdapterMuseum.OnButtonClickListener{
    EditText editTextSearch;
    RecyclerView recyclerViewResults;
    SearchAdapterMuseum searchAdapterMuseum;
    ImageView logo;
    /**
     * Metodă apelată atunci când butonul de detaliu este apăsat în adapter.
     *
     * @param museum Muzeul asociat butonului apăsat.
     */
    @Override
    public void onButtonClick(Museum museum)
    {
        showDetails(museum);
    }
    /**
     * Metodă pentru afișarea detaliilor unui muzeu.
     *
     * @param museum Muzeul pentru care se afișează detaliile.
     */
    private void showDetails(Museum museum)
    {
        Intent intent = new Intent(this, MuseumDetailsActivity.class);
        intent.putExtra("id",museum.getId());
        intent.putExtra("name",museum.getName());
        intent.putExtra("description",museum.getDescription());
        intent.putExtra("image",museum.getImage());
        intent.putExtra("map",museum.getLocation());
        startActivity(intent);
    }
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearch = findViewById(R.id.editTextSearch);
        recyclerViewResults = findViewById(R.id.recyclerViewResults);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewResults.setLayoutManager(layoutManager);
        searchAdapterMuseum = new SearchAdapterMuseum();
        searchAdapterMuseum.setOnButtonClickListener(this);
        recyclerViewResults.setAdapter(searchAdapterMuseum);
        logo = findViewById(R.id.logo_search);


        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch(s.toString());
                if(recyclerViewResults.getAdapter().getItemCount()==0)
                    logo.setVisibility(View.VISIBLE);
                else
                    logo.setVisibility(View.INVISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    /**
     * Metodă pentru efectuarea căutării.
     *
     * @param query Textul de căutare introdus de utilizator.
     */
    private void performSearch(String query)
    {   Log.d("SearchActivity", "Performing search for query: " + query);
        searchAdapterMuseum.ClearList();
        List<Museum> list = performActualSearch(query);

        searchAdapterMuseum.setSearchResults(list);
        searchAdapterMuseum.notifyDataSetChanged();


    }
    /**
     * Metodă pentru căutarea efectivă în baza de date a muzeelor.
     *
     * @param query Textul de căutare introdus de utilizator.
     * @return Lista muzeelor găsite conform căutării.
     */
    private List<Museum> performActualSearch(String query)
    {   Log.d("SearchActivity", "Performing actual search for query: " + query);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        List<Museum> list = new ArrayList<>();

        cursor = db.query(
            "MUSEUM",
                new String[]{"IDMUSEUM","MUSEUMNAME","MUSEUMDESCRIPTION","MAPLOCATION", "IMAGENAME"},
                "MUSEUMNAME LIKE ?",
                new String[]{"%" + query + "%"},
                null,
                null,
                null
        );
        list.clear();
        while (cursor.moveToNext())
        {
            list.add(new Museum(cursor.getInt(cursor.getColumnIndexOrThrow("idMuseum")), cursor.getString(cursor.getColumnIndexOrThrow("museumName")), cursor.getString(cursor.getColumnIndexOrThrow("museumDescription")),cursor.getString(cursor.getColumnIndexOrThrow("mapLocation")),cursor.getString(cursor.getColumnIndexOrThrow("imageName"))));
        }
        /*for(Museum x : list)
        {
            Log.d("Museum in actualSearch", x.toString());
        }*/

        return list;
    }
}
