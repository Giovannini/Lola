package lola.giovannini.lola.activite_main;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by giovannini on 10/20/14.
 */
public class Armure {
    String CLASS_NAME = "Armure";

    String nom, ca, dex, pénalité, sorts, déplacement, poids;
    JSONObject obj;

    public Armure() {
    }

    public Armure(String nom, String poids, String ca, String dex,
                  String pénalité, String sorts, String déplacement){
        this.nom = nom;
        this.ca = ca;
        this.dex = dex;
        this.pénalité = pénalité;
        this.poids = poids;
        this.sorts = sorts;
        this.déplacement = déplacement;
        try {
            JSONObject o = new JSONObject();
            o.put("nom", nom);
            o.put("ca", ca);
            o.put("dex", dex);
            o.put("penalite", pénalité);
            o.put("sorts", sorts);
            o.put("deplacement", déplacement);
            o.put("poids", poids);

            this.obj = o;
        }catch (JSONException e){
            Log.e(CLASS_NAME, "Erreur JSON lors de la création d'une armure.");
        }
    }

    public Armure(JSONObject o){
        this.obj = o;
        try {
            this.setNom(o.getString("nom"));
            this.setCa(o.getString("ca"));
            this.setDex(o.getString("dex"));
            this.setPénalité(o.getString("penalite"));
            this.setSorts(o.getString("sorts"));
            this.setDéplacement(o.getString("deplacements"));
            this.setPoids(o.getString("poids"));
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

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
        try{
            this.obj.put("ca", ca);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setCa()", e.getMessage());
        }
    }

    public String getDex() {
        return dex;
    }

    public void setDex(String dex) {
        this.dex = dex;
        try{
            this.obj.put("dex", dex);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setDex()", e.getMessage());
        }
    }

    public String getPénalité() {
        return pénalité;
    }

    public void setPénalité(String pénalité) {
        this.pénalité = pénalité;
        try{
            this.obj.put("penalite", pénalité);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setPénalité()", e.getMessage());
        }
    }

    public String getSorts() {
        return sorts;
    }

    public void setSorts(String sorts) {
        this.sorts = sorts;
        try{
            this.obj.put("sorts", sorts);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setSorts()", e.getMessage());
        }
    }

    public String getDéplacement() {
        return déplacement;
    }

    public void setDéplacement(String déplacement) {
        this.déplacement = déplacement;
        try{
            this.obj.put("deplacements", déplacement);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".setDéplacement()", e.getMessage());
        }
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
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
