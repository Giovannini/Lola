package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by giovannini on 10/20/14.
 */
public class Armure {
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
            Log.e("Armure", e.getMessage());
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public String getDex() {
        return dex;
    }

    public void setDex(String dex) {
        this.dex = dex;
    }

    public String getPénalité() {
        return pénalité;
    }

    public void setPénalité(String pénalité) {
        this.pénalité = pénalité;
    }

    public String getSorts() {
        return sorts;
    }

    public void setSorts(String sorts) {
        this.sorts = sorts;
    }

    public String getDéplacement() {
        return déplacement;
    }

    public void setDéplacement(String déplacement) {
        this.déplacement = déplacement;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public JSONObject getObj() {
        return obj;
    }

    public void setObj(JSONObject obj) {
        this.obj = obj;
    }
}
