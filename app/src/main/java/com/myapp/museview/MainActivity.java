
/**
 * MainActivity este activitatea principală a aplicației, care servește ca punct de intrare în aplicație.
 * Utilizatorii pot naviga către alte activități, precum LoginActivity sau MenuActivity, în funcție de starea sesiunii.
 * De asemenea, această activitate conține o metodă de populare a bazei de date în scopuri de dezvoltare.
 *
 * Utilizare:
 * Această activitate este lansată automat la pornirea aplicației și furnizează butoane pentru navigare.
 * Pentru a utiliza această activitate, puteți folosi metoda populate() pentru a adăuga date de testare în baza de date.
 *
 * @author Vladu Marian-Dumitru
 * @version 1.0
 */
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

    /**
     * Metodă utilizată pentru a popula baza de date cu date de testare în scopuri de dezvoltare.
     * A se folosi cu precauție, deoarece aceasta poate modifica datele existente din baza de date.
     */
    private void populate()
    {

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.debugCreateMuseum("Timișoara Art Museum",
                        "The Timișoara Art Museum is housed in a building from the 18th century known as the Baroque Palace. The museum has 8 art collections: Contemporary Art, European Graphic Art, Decorative Art, European Art, The Baba (1906 – 1997) Collection, Interwar Painting from Banat Area, 19th Century Painting from Banat Area, Old Art from Banat Area. The contemporary art collection centers around the different artistic directions of art in Timișoara.",
                "map1",
                "museum1");
        databaseHelper.debugCreateMuseum("National Museum of Banat","The National Museum of Banat (Romanian: Muzeul Național al Banatului; abbreviated MNaB) is a museum in Timișoara, Romania, headquartered in Huniade Castle. It was founded in 1872 by the Society of History and Archeology of Banat (Romanian: Societatea de Istorie și Arheologie din Banat) on the initiative of the prefect of the then Temes County Zsigmond Ormós [hu]. It hosts the largest collection of archeological objects in Banat. The ground floor houses the 6,200-year-old Parța Neolithic Sanctuary. The museum includes departments for archeology, history and natural sciences. The museum also has a laboratory for conservation and preservation of objects of cultural heritage and history.",
                "",
                "museum2");

        databaseHelper.debugCreateMarker("Punct de interes 1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse gravida sagittis justo, sit amet sodales libero tincidunt ac. Aliquam et libero nunc. Donec ut dapibus tellus. Vivamus non ante lobortis, finibus magna sed, commodo augue. Phasellus hendrerit sagittis rutrum. Suspendisse nisl massa, ornare consequat fermentum sit amet, consequat sit amet nisi. Curabitur dictum sodales volutpat.",
                0.5f,0.5f,
                1,
                "");
        databaseHelper.debugCreateMarker("Punct de interes 2","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse gravida sagittis justo, sit amet sodales libero tincidunt ac. Aliquam et libero nunc. Donec ut dapibus tellus. Vivamus non ante lobortis, finibus magna sed, commodo augue. Phasellus hendrerit sagittis rutrum. Suspendisse nisl massa, ornare consequat fermentum sit amet, consequat sit amet nisi. Curabitur dictum sodales volutpat.",0.2f,0.6f,1,"");
        databaseHelper.debugCreateMarker("Punct de interes 3",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse gravida sagittis justo, sit amet sodales libero tincidunt ac. Aliquam et libero nunc. Donec ut dapibus tellus. Vivamus non ante lobortis, finibus magna sed, commodo augue. Phasellus hendrerit sagittis rutrum. Suspendisse nisl massa, ornare consequat fermentum sit amet, consequat sit amet nisi. Curabitur dictum sodales volutpat.",
                0.2f,0.48f,
                1,
                "");
        databaseHelper.debugCreateMarker("Punct de interes 4",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse gravida sagittis justo, sit amet sodales libero tincidunt ac. Aliquam et libero nunc. Donec ut dapibus tellus. Vivamus non ante lobortis, finibus magna sed, commodo augue. Phasellus hendrerit sagittis rutrum. Suspendisse nisl massa, ornare consequat fermentum sit amet, consequat sit amet nisi. Curabitur dictum sodales volutpat.",
                0.55f,0.2f,
                1,
                "");
        databaseHelper.debugCreateMarker("Punct de interes 5",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse gravida sagittis justo, sit amet sodales libero tincidunt ac. Aliquam et libero nunc. Donec ut dapibus tellus. Vivamus non ante lobortis, finibus magna sed, commodo augue. Phasellus hendrerit sagittis rutrum. Suspendisse nisl massa, ornare consequat fermentum sit amet, consequat sit amet nisi. Curabitur dictum sodales volutpat.",
                0.74f,0.5f,
                1,
                "");
        databaseHelper.debugCreateMarker("Punct de interes 6",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse gravida sagittis justo, sit amet sodales libero tincidunt ac. Aliquam et libero nunc. Donec ut dapibus tellus. Vivamus non ante lobortis, finibus magna sed, commodo augue. Phasellus hendrerit sagittis rutrum. Suspendisse nisl massa, ornare consequat fermentum sit amet, consequat sit amet nisi. Curabitur dictum sodales volutpat.",
                0.525f,0.73f,
                1,
                "");
        databaseHelper.debugCreateMuseum("Banat Village Museum","The Banat Village Museum (Romanian: Muzeul Satului Bănățean) is an open-air ethnographic museum in northeastern Timișoara, at the edge of the Green Forest. Spread over an area of 17 ha, the museum is designed as a traditional Banat village and includes peasant households belonging to various ethnic groups in Banat (Romanians, Slovaks, Swabians, Ukrainians, Hungarians, etc.), buildings with social function of the traditional village (town hall, school, church, etc.), folk art installations and workshops",
                "",
                "museum3");
        databaseHelper.debugCreateMuseum("Communist Consumers Museum","The Communist Consumers Museum is designed as a typical Romanian apartment from that era, with a living room, children's room, kitchen and hallways. It displays on shelves, in closets and drawers most of everything that Romanians could buy before the revolution of 1989, from toys to vinyls, TV and radio sets to bicycles, kitchen attire and much, much more. After traveling through time in the museum, one can also enjoy a nice relaxing time in the upstairs bar: Scart Loc Lejer.",
                "",
                "museum4");
        databaseHelper.debugCreateMuseum("Museum of Tools (Uneltelor)","",
                "",
                "museum5");

        databaseHelper.close();
        SessionManager sessionManager = new SessionManager(this);
        sessionManager.endSession();

    }

}