package fr.antoninruan.cellarmanager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.antoninruan.cellarmanager.model.Bottle;
import fr.antoninruan.cellarmanager.model.Spot;

public class MainActivity extends AppCompatActivity {

    private static HashMap<Integer, Bottle> bottles = new HashMap<>();
    private static List<Spot> spots = new ArrayList<>();

    private static int lastBottleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_bottles, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        registerBottle("{\"0\":{\"name\":\"Clos des Confréries\",\"region\":\"Bordeaux\",\"edition\":\"\",\"domain\":\"E.A.R.L René LAFON\",\"comment\":\"\",\"year\":\"2015\",\"consume_year\":\"2016\",\"type\":\"ROUGE\"},\"1\":{\"name\":\"Clos des Confréries\",\"region\":\"Bordeaux\",\"edition\":\"\",\"domain\":\"E.A.R.L René LAFON\",\"comment\":\"\",\"year\":\"2014\",\"consume_year\":\"2014\",\"type\":\"ROUGE\"},\"2\":{\"name\":\"Chateau COLIN-LAMOTHE\",\"region\":\"Bordeaux\",\"edition\":\"Cuvée Jean-Edouard LAFON\",\"domain\":\"E.A.R.L René LAFON\",\"comment\":\"\",\"year\":\"2012\",\"consume_year\":\"1980\",\"type\":\"ROUGE\"},\"3\":{\"name\":\"Sancerre (Régis)\",\"region\":\"Sancerre\",\"edition\":\"Michel GIRARD et Fils\",\"domain\":\"Michel GIRARD et Fils\",\"comment\":\"\",\"year\":\"2018\",\"consume_year\":\"1980\",\"type\":\"ROSE\"},\"4\":{\"name\":\"Clos des Confréries\",\"region\":\"Bordeaux\",\"edition\":\"\",\"domain\":\"E.A.R.L. René LAFON\",\"comment\":\"\",\"year\":\"2018\",\"consume_year\":\"1980\",\"type\":\"ROSE\"},\"5\":{\"name\":\"Médoc\",\"region\":\"Bordeaux\",\"edition\":\"\",\"domain\":\"\",\"comment\":\"\",\"year\":\"2014\",\"consume_year\":\"2014\",\"type\":\"ROUGE\"},\"6\":{\"name\":\"Blaye\",\"region\":\"Bordeaux\",\"edition\":\"\",\"domain\":\"Chateau la Chapelle du Couvent\",\"comment\":\"Magnum\",\"year\":\"2016\",\"consume_year\":\"2016\",\"type\":\"ROUGE\"},\"7\":{\"name\":\"\",\"region\":\"Bordeaux\",\"edition\":\"Baron de Pierre\",\"domain\":\"Maison Bouey\",\"comment\":\"\",\"year\":\"2012\",\"consume_year\":\"2012\",\"type\":\"ROUGE\"},\"8\":{\"name\":\"\",\"region\":\"Champagne\",\"edition\":\"Rosé\",\"domain\":\"D. Massin\",\"comment\":\"Rosé\",\"year\":\"1980\",\"consume_year\":\"1980\",\"type\":\"CHAMPAGNE\"},\"9\":{\"name\":\"Volnay\",\"region\":\"Bourgogne\",\"edition\":\"1er cru Clos des Chênes\",\"domain\":\"Reyane & Pascal Bouley \",\"comment\":\"\",\"year\":\"2006\",\"consume_year\":\"2006\",\"type\":\"ROUGE\"},\"10\":{\"name\":\"Volnay\",\"region\":\"Bourgogne\",\"edition\":\"1er Cru Clos des Chênes\",\"domain\":\"Reyane & Pascal Bouley\",\"comment\":\"\",\"year\":\"2005\",\"consume_year\":\"2005\",\"type\":\"ROUGE\"},\"11\":{\"name\":\"Chambolle Musigny\",\"region\":\"Bourgogne\",\"edition\":\"1er Cru Les Charmes\",\"domain\":\"Christian Clerget\",\"comment\":\"\",\"year\":\"2005\",\"consume_year\":\"2005\",\"type\":\"ROUGE\"},\"12\":{\"name\":\"Echezeaux\",\"region\":\"Bourgogne\",\"edition\":\"Grand Cru\",\"domain\":\"Coquard-Loison-Fleurot\",\"comment\":\"\",\"year\":\"2007\",\"consume_year\":\"2007\",\"type\":\"ROUGE\"},\"13\":{\"name\":\"Rully\",\"region\":\"Bourgogne\",\"edition\":\"La Chatalienne\",\"domain\":\"Laborbe-Juillot\",\"comment\":\"\",\"year\":\"2004\",\"consume_year\":\"2004\",\"type\":\"ROUGE\"},\"14\":{\"name\":\"Volnay\",\"region\":\"Bourgogne\",\"edition\":\"Grand Vin de Bourgogne\",\"domain\":\"Reyane & Pascal Bouley\",\"comment\":\"\",\"year\":\"2008\",\"consume_year\":\"2008\",\"type\":\"ROUGE\"},\"15\":{\"name\":\"Volnay\",\"region\":\"Bourgogne\",\"edition\":\"1er Cru Clos des Chênes\",\"domain\":\"Reyane & Pascal Bouley\",\"comment\":\"\",\"year\":\"2008\",\"consume_year\":\"2008\",\"type\":\"ROUGE\"},\"16\":{\"name\":\"Nuits-Saint-Georges\",\"region\":\"Bourgogne\",\"edition\":\"Les Chaignots\",\"domain\":\"Chauvenet-Chopin\",\"comment\":\"\",\"year\":\"2008\",\"consume_year\":\"2008\",\"type\":\"ROUGE\"},\"17\":{\"name\":\"Vosnes-Romanée\",\"region\":\"Bourgogne\",\"edition\":\"1er Cru\",\"domain\":\"Coquard-Loison-Fleurot\",\"comment\":\"\",\"year\":\"2008\",\"consume_year\":\"2008\",\"type\":\"ROUGE\"},\"18\":{\"name\":\"Mercurey\",\"region\":\"Bourgogne\",\"edition\":\"1er Cru Clos de Paradis\",\"domain\":\"Pierre Virot\",\"comment\":\"\",\"year\":\"2004\",\"consume_year\":\"2004\",\"type\":\"ROUGE\"},\"19\":{\"name\":\"Vosnes-Romanée\",\"region\":\"Bourgogne\",\"edition\":\"Les Violettes\",\"domain\":\"Christian Clerget\",\"comment\":\"\",\"year\":\"2005\",\"consume_year\":\"2005\",\"type\":\"ROUGE\"},\"20\":{\"name\":\"Champagne\",\"region\":\"Champagne\",\"edition\":\"Cuvée de Réserve\",\"domain\":\"\",\"comment\":\"\",\"year\":\"2020\",\"consume_year\":\"2020\",\"type\":\"CHAMPAGNE\"},\"21\":{\"name\":\"Chinon\",\"region\":\"Touraine\",\"edition\":\"Cuvée du Père Léonce\",\"domain\":\"Angelliaume\",\"comment\":\"\",\"year\":\"2006\",\"consume_year\":\"2006\",\"type\":\"ROUGE\"},\"22\":{\"name\":\"Fixin\",\"region\":\"Bourgogne\",\"edition\":\"Les Petits Crais\",\"domain\":\"Huguenot Père & Fils\",\"comment\":\"\",\"year\":\"2003\",\"consume_year\":\"2003\",\"type\":\"ROUGE\"},\"23\":{\"name\":\"Chambolle-Musigny\",\"region\":\"Bourgogne\",\"edition\":\"1er Cru Les Chatelots\",\"domain\":\"Hervé Sigaut\",\"comment\":\"\",\"year\":\"2003\",\"consume_year\":\"2003\",\"type\":\"ROUGE\"},\"24\":{\"name\":\"Haut-Médoc\",\"region\":\"Bordeaux\",\"edition\":\"Cru Bourgeois\",\"domain\":\"Larose-Trintaudon\",\"comment\":\"\",\"year\":\"2005\",\"consume_year\":\"2005\",\"type\":\"ROUGE\"},\"25\":{\"name\":\"Champagne\",\"region\":\"Champagne\",\"edition\":\"Prestige\",\"domain\":\"D. Massin\",\"comment\":\"\",\"year\":\"2020\",\"consume_year\":\"2020\",\"type\":\"CHAMPAGNE\"},\"26\":{\"name\":\"Montagne Saint-Emilion\",\"region\":\"Bordeaux\",\"edition\":\"Haut-Bonneau\",\"domain\":\"Htiers Marchand\",\"comment\":\"\",\"year\":\"2003\",\"consume_year\":\"2003\",\"type\":\"ROUGE\"},\"27\":{\"name\":\"Saint-Emilion Grand Cru\",\"region\":\"Bordeaux\",\"edition\":\"Grande Arche\",\"domain\":\"Union des producteurs\",\"comment\":\"\",\"year\":\"2015\",\"consume_year\":\"2015\",\"type\":\"ROUGE\"},\"28\":{\"name\":\"Côte de Blaye (Première Côtes)\",\"region\":\"Bordeaux\",\"edition\":\"Haut-Bertinerie\",\"domain\":\"Bantegnies & Fils\",\"comment\":\"\",\"year\":\"1980\",\"consume_year\":\"1980\",\"type\":\"ROUGE\"},\"29\":{\"name\":\"Haut-Médoc (Cru Bourgeois)\",\"region\":\"Bordeaux\",\"edition\":\"D'Arcin\",\"domain\":\"Chateau d'Arcin\",\"comment\":\"\",\"year\":\"2002\",\"consume_year\":\"2002\",\"type\":\"ROUGE\"},\"30\":{\"name\":\"Médoc\",\"region\":\"Bordeaux\",\"edition\":\"Layauga\",\"domain\":\"Layauga\",\"comment\":\"\",\"year\":\"2002\",\"consume_year\":\"2002\",\"type\":\"ROUGE\"},\"31\":{\"name\":\"Saint-Estèphe\",\"region\":\"Bordeaux\",\"edition\":\"Mac Carthy\",\"domain\":\"H. Duboscq & Fils\",\"comment\":\"\",\"year\":\"2004\",\"consume_year\":\"2004\",\"type\":\"ROUGE\"},\"32\":{\"name\":\"Haut-Médoc \",\"region\":\"Bordeaux\",\"edition\":\"Cru Bourgeois\",\"domain\":\"Larose-Trintaudon\",\"comment\":\"\",\"year\":\"2015\",\"consume_year\":\"2015\",\"type\":\"ROUGE\"},\"33\":{\"name\":\"Lalande de Pomerol\",\"region\":\"Bordeaux\",\"edition\":\"La Vallière\",\"domain\":\"S.A.R.L. Dubost\",\"comment\":\"\",\"year\":\"2010\",\"consume_year\":\"2010\",\"type\":\"ROUGE\"},\"34\":{\"name\":\"Pomerol\",\"region\":\"Bordeaux\",\"edition\":\"Lafleur du Roy\",\"domain\":\"S.A.R.L. Dubost\",\"comment\":\"\",\"year\":\"2010\",\"consume_year\":\"2010\",\"type\":\"ROUGE\"},\"35\":{\"name\":\"Cahors Malbec\",\"region\":\"Sud Ouest\",\"edition\":\"Réserve Edward\",\"domain\":\"La Garde\",\"comment\":\"\",\"year\":\"2015\",\"consume_year\":\"2015\",\"type\":\"ROUGE\"},\"36\":{\"name\":\"Coteaux du Quercy\",\"region\":\"Sud-Ouest\",\"edition\":\"\",\"domain\":\"La Garde\",\"comment\":\"\",\"year\":\"2015\",\"consume_year\":\"2015\",\"type\":\"ROUGE\"},\"37\":{\"name\":\"Bergerac\",\"region\":\"Sud-Ouest\",\"edition\":\"Les Miaudoux\",\"domain\":\"Famille Cuisset\",\"comment\":\"Bio\",\"year\":\"2017\",\"consume_year\":\"2017\",\"type\":\"ROUGE\"},\"38\":{\"name\":\"Bergerac\",\"region\":\"Sud-Ouest\",\"edition\":\"La Jauberthie\",\"domain\":\"Ryman S.A.\",\"comment\":\"\",\"year\":\"2015\",\"consume_year\":\"2015\",\"type\":\"ROUGE\"},\"39\":{\"name\":\"Bergerac\",\"region\":\"Sud-Ouest\",\"edition\":\"Pécharmant\",\"domain\":\"Les Costes\",\"comment\":\"Bio\",\"year\":\"2015\",\"consume_year\":\"2015\",\"type\":\"ROUGE\"},\"40\":{\"name\":\"0Vouvray\",\"region\":\"Touraine\",\"edition\":\"\",\"domain\":\"\",\"comment\":\"\",\"year\":\"2020\",\"consume_year\":\"2020\",\"type\":\"AUTRES\"},\"41\":{\"name\":\"Madiran\",\"region\":\"Sud-Ouest\",\"edition\":\"\",\"domain\":\"Seigneurie de Crouseilles\",\"comment\":\"\",\"year\":\"2014\",\"consume_year\":\"2014\",\"type\":\"ROUGE\"},\"42\":{\"name\":\"Saint-Mont\",\"region\":\"Sud-Ouest\",\"edition\":\"\",\"domain\":\"Saint-Go\",\"comment\":\"\",\"year\":\"2014\",\"consume_year\":\"2014\",\"type\":\"ROUGE\"},\"43\":{\"name\":\"Médoc\",\"region\":\"Bordeaux\",\"edition\":\"\",\"domain\":\"Chateau Des Merles\",\"comment\":\"De papa\",\"year\":\"2003\",\"consume_year\":\"2003\",\"type\":\"ROUGE\"},\"44\":{\"name\":\"The Stand\",\"region\":\"Californie\",\"edition\":\"\",\"domain\":\"Tooth & Nail Paso Robles\",\"comment\":\"\",\"year\":\"2015\",\"consume_year\":\"2015\",\"type\":\"ROUGE\"},\"45\":{\"name\":\"Rabble\",\"region\":\"Californie\",\"edition\":\"\",\"domain\":\"Tooth & Nail Paso Robles\",\"comment\":\"\",\"year\":\"2015\",\"consume_year\":\"2015\",\"type\":\"ROUGE\"},\"46\":{\"name\":\"Sancerre (Régis)\",\"region\":\"Sancerre\",\"edition\":\"Michel GIRARD et Fils\",\"domain\":\"Michel GIRARD et Fils\",\"comment\":\"\",\"year\":\"2016\",\"consume_year\":\"2018\",\"type\":\"ROUGE\"},\"47\":{\"name\":\"Sancerre (Régis)\",\"region\":\"Sancerre\",\"edition\":\"Michel GIRARD et Fils\",\"domain\":\"Michel GIRARD et Fils\",\"comment\":\"\",\"year\":\"2017\",\"consume_year\":\"2018\",\"type\":\"ROUGE\"},\"48\":{\"name\":\"Sancerre (Régis)\",\"region\":\"Sancerre\",\"edition\":\"Michel GIRARD et Fils\",\"domain\":\"Michel GIRARD et Fils\",\"comment\":\"\",\"year\":\"2018\",\"consume_year\":\"2018\",\"type\":\"ROUGE\"}}");
    }

    public static HashMap<Integer, Bottle> getBottles() {
        return bottles;
    }

    public static List<Spot> getSpots() {
        return spots;
    }

    public static int nextBottleId() {
        return lastBottleId ++;
    }

    private void registerBottle(String s) {
        try {
            JsonObject bottles = JsonParser.parseString(s).getAsJsonObject();
            for(Map.Entry<String, JsonElement> entry : bottles.entrySet()) {
                int id = Integer.valueOf(entry.getKey());
                Bottle bottle = Bottle.fromJson(id, entry.getValue().getAsJsonObject());
                MainActivity.bottles.put(id, bottle);
                if(MainActivity.lastBottleId < id)
                    MainActivity.lastBottleId = id;
            }
        } catch (JsonSyntaxException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static List<Bottle> getBottlesSortedBy(SortCriteria criteria) {
        List<Bottle> bottles = Arrays.asList(MainActivity.bottles.values().toArray(new Bottle[]{}));
        bottles.sort((b1, b2) -> {
            switch (criteria) {
                case NAME:
                    return b1.getName().compareTo(b2.getName());
                case DOMAIN:
                    return b1.getDomain().compareTo(b2.getDomain());
                case YEAR:
                    return b2.getYear() - b1.getYear();
                case EDITION:
                    return b1.getEdition().compareTo(b2.getEdition());
                case CONSUME_YEAR:
                    return b2.getConsumeYear() - b1.getConsumeYear();
                default:
                    break;
            }
            return 0;
        });
        return bottles;
    }

    public enum SortCriteria {

        NAME,
        DOMAIN,
        EDITION,
        YEAR,
        CONSUME_YEAR

    }

}