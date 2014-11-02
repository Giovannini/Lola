package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by giovannini on 10/20/14.
 */
public class Arme {
    String nom, bonus, dommages, critiques, portée;
    JSONObject obj;

    public Arme() {
    }

    public Arme(String nom, String dommages, String critiques, String portee,
                String bonus){
        this.nom = nom;
        this.dommages = dommages;
        this.critiques = critiques;
        this.portée = portee;
        this.bonus = bonus;

        try {
            JSONObject o = new JSONObject();
            o.put("nom", nom);
            o.put("dommages", dommages);
            o.put("portée", portee);
            o.put("critique", critiques);
            o.put("bonus", bonus);

            this.obj = o;
        }catch (JSONException e){
            Log.e("Arme()", "Erreur lors de la création d'une arme.");
        }
    }

    public Arme(JSONObject o){
        try {
            this.obj = o;
            this.setNom(o.getString("nom"));
            this.setDommages(o.getString("dommages"));
            this.setPortée(o.getString("portée"));
            this.setCritiques(o.getString("critique"));
            this.setBonus(o.getString("bonus"));
        }catch(JSONException e){
            Log.e("Arme", e.getMessage());
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getDommages() {
        return dommages;
    }

    public void setDommages(String dommages) {
        this.dommages = dommages;
    }

    public String getCritiques() {
        return critiques;
    }

    public void setCritiques(String critiques) {
        this.critiques = critiques;
    }

    public String getPortée() {
        return portée;
    }

    public void setPortée(String portée) {
        this.portée = portée;
    }

    public JSONObject getObj() {
        return obj;
    }
}
