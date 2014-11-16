package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by giovannini on 10/20/14.
 */
public class Arme {
    String CLASS_NAME = "Arme";

    String nom, bonus, dommages, critiques, portée, taille, type, poids;
    JSONObject obj;

    public Arme() {
    }

    public Arme(String nom, String dommages, String critiques, String portee,
                String bonus, String taille, String type, String poids){
        this.nom = nom;
        this.dommages = dommages;
        this.critiques = critiques;
        this.portée = portee;
        this.bonus = bonus;
        this.taille = taille;
        this.type = type;
        this.poids = poids;

        try {
            JSONObject o = new JSONObject();
            o.put("nom", nom);
            o.put("dommages", dommages);
            o.put("portée", portee);
            o.put("critique", critiques);
            o.put("bonus", bonus);
            o.put("taille", taille);
            o.put("type", type);
            o.put("poids", poids);

            this.obj = o;
        }catch (JSONException e){
            Log.e(CLASS_NAME, "Erreur lors de la création d'une arme.");
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
            Log.e(CLASS_NAME, e.getMessage());
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        try{
            this.obj.put("nom", nom);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setNom()", e.getMessage());
        }
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
        try{
            this.obj.put("bonus", bonus);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setBonus()", e.getMessage());
        }
    }

    public String getDommages() {
        return dommages;
    }

    public void setDommages(String dommages) {
        this.dommages = dommages;
        try{
            this.obj.put("dommages", dommages);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setDommages()", e.getMessage());
        }
    }

    public String getCritiques() {
        return critiques;
    }

    public void setCritiques(String critiques) {
        this.critiques = critiques;
        try{
            this.obj.put("critique", critiques);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setCritiques()", e.getMessage());
        }
    }

    public String getPortée() {
        return portée;
    }

    public void setPortée(String portée) {
        this.portée = portée;
        try{
            this.obj.put("portée", portée);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setPirtée()", e.getMessage());
        }
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
        try{
            this.obj.put("taille", taille);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setTaille()", e.getMessage());
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        try{
            this.obj.put("type", type);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setType()", e.getMessage());
        }
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids)  {
        this.poids = poids;
        try{
            this.obj.put("poids", poids);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setPoids()", e.getMessage());
        }
    }

    public JSONObject getObj() {
        return obj;
    }
}
