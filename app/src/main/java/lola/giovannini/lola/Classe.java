package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by giovannini on 10/18/14.
 */
public class Classe {
    String nom;
    int niveau;
    Map<String, String> particularités;

    public Classe() {
        particularités = new HashMap<String, String>();
    }

    public Classe(JSONObject o){
        this();
        try{
            int niveau = o.getInt("Niveau");
            if (niveau > 0) {//Si le personnage possède des caractéristiques de la classe
                this.setNom(o.getString("Nom"));
                this.setNiveau(niveau);
                if (o.has("Part")) {
                    JSONObject parts = o.getJSONObject("Part");
                    Iterator<String> keys = parts.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        this.addParticularité(key, parts.getString(key));
                    }
                }
            }
        }catch(JSONException e){
            Log.e("Classe", e.getMessage());
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Map<String, String> getParticularités() {
        return particularités;
    }

    public void setParticularités(Map<String, String> particularités) {
        this.particularités = particularités;
    }

    public void addParticularité(String key, String value){
        this.particularités.put(key, value);
    }
}
