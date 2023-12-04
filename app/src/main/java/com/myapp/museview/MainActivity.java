package com.myapp.museview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buton_start = findViewById(R.id.buton_start);
        //populate();
        SessionManager session = new SessionManager(getApplicationContext());
        buton_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!session.isLoggedIn())
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                else
                    startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });


    }
    private void populate()
    {

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.debugCreateMuseum("Museum of Palaeontology","\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla iaculis quam iaculis fringilla tincidunt. Nulla facilisi. Vestibulum ac arcu lacus. Phasellus diam dui, fringilla vitae volutpat id, bibendum a mauris. Donec non eros eu risus gravida facilisis et in arcu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Duis metus libero, consectetur et feugiat mattis, ornare sit amet leo. Nulla non venenatis velit.\n" +
                        "\n" +
                        "Mauris vitae posuere augue, ac varius nibh. Mauris rutrum urna id lobortis hendrerit. Cras rutrum orci at consequat blandit. Integer hendrerit, ipsum et tristique consectetur, tellus risus iaculis metus, vitae elementum nunc mauris ut mauris. Pellentesque vitae bibendum libero, vel consequat diam. Sed ac eros vitae ipsum pharetra suscipit. Curabitur blandit molestie sapien. Nulla erat enim, placerat et sapien sit amet, pretium ullamcorper dui. Donec mi tortor, varius varius gravida in, commodo non arcu. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam in imperdiet massa. Phasellus at nulla purus. Cras sodales orci vel ante commodo tristique. Etiam erat diam, vehicula ut ante venenatis, porta sagittis elit. Cras viverra at erat ac tristique. Sed non urna condimentum, fringilla odio sed, semper mauris.",
                "",
                "museum1");
        databaseHelper.debugCreateMuseum("Museum of Art","\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla iaculis quam iaculis fringilla tincidunt. Nulla facilisi. Vestibulum ac arcu lacus. Phasellus diam dui, fringilla vitae volutpat id, bibendum a mauris. Donec non eros eu risus gravida facilisis et in arcu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Duis metus libero, consectetur et feugiat mattis, ornare sit amet leo. Nulla non venenatis velit.\n" +
                        "\n" +
                        "Mauris vitae posuere augue, ac varius nibh. Mauris rutrum urna id lobortis hendrerit. Cras rutrum orci at consequat blandit. Integer hendrerit, ipsum et tristique consectetur, tellus risus iaculis metus, vitae elementum nunc mauris ut mauris. Pellentesque vitae bibendum libero, vel consequat diam. Sed ac eros vitae ipsum pharetra suscipit. Curabitur blandit molestie sapien. Nulla erat enim, placerat et sapien sit amet, pretium ullamcorper dui. Donec mi tortor, varius varius gravida in, commodo non arcu. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam in imperdiet massa. Phasellus at nulla purus. Cras sodales orci vel ante commodo tristique. Etiam erat diam, vehicula ut ante venenatis, porta sagittis elit. Cras viverra at erat ac tristique. Sed non urna condimentum, fringilla odio sed, semper mauris.",
                "",
                "museum2");
        databaseHelper.debugCreateMarker("Ceva","Ceva",0.5f,0.5f,1,"");
        databaseHelper.debugCreateMarker("Altceva","Altceva",0.2f,0.6f,1,"");
        databaseHelper.close();
        SessionManager sessionManager = new SessionManager(this);
        sessionManager.endSession();

    }

}