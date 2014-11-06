package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by giovannini on 11/6/14.
 */
public class ParticularitéRace {
    String nom, description;
    JSONObject obj;

    public ParticularitéRace(){

    }

    public ParticularitéRace(JSONObject o, Personnage p){
        this.obj = o;
        try
        {
            this.nom = o.getString("nom");
            this.description = o.getString("description");
            parseBonus(o.getString("bonus"), p);
        }catch(JSONException e){
            Log.e("ParticularitéRace", "Erreur JSON lors de la création d'une particularité de " +
                    "race.");
        }
    }

    private void parseBonus(String bonus, Personnage p) {
        if (! bonus.equals("")){
            String[] parts = bonus.split(";");
            for(String part: parts){
                String[] splitted = part.split(":");
                if (p.getDivers().containsKey(splitted[0])){
                    p.getDivers().put(
                            splitted[0],
                            p.getDivers().get(splitted[0]) + Integer.parseInt(splitted[1])
                    );
                }else{
                    p.getDivers().put(splitted[0], Integer.parseInt(splitted[1]));
                }
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public JSONObject getObj() {
        return obj;
    }
}
