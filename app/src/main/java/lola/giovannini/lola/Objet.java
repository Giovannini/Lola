package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by giovannini on 10/21/14.
 */
public class Objet {
    String CLASS_NAME = "Objet";

    String nom, emplacement, poids, note, bonus;
    int quantite;
    JSONObject o;

    public Objet() {
        this.nom = "";
        this.emplacement = "";
        this.poids = "";
        this.quantite = 0;
        this.note = "";
        this.o = new JSONObject();
    }

    public Objet(String nom, String emplacement, String poids, String note, int quantite,
                 String bonus){
        this();
        this.setNom(nom);
        this.setEmplacement(emplacement);
        this.setPoids(poids);
        this.setQuantite(quantite);
        this.setNote(note);
        this.setBonus(bonus);
    }

    public Objet(JSONObject o, Map<String, Integer> divers){
        this.o = o;
        try {
            nom = o.getString("nom");
            emplacement = o.getString("emplacement");
            poids = o.getString("poids");
            if (o.has("qt")) {
                quantite = o.getInt("qt");
            } else {
                quantite = 1;
            }
            if (o.has("bonus")){
                String toParse = o.getString("bonus");
                String[] divs = toParse.split(";");
                for (String div : divs){
                    String[] result = div.split(":");
                    int value = Integer.parseInt(result[1]);
                    if(divers.containsKey(result[0]))
                        value += divers.get(result[0]);
                    divers.put(result[0], value);
                }
            }
            if (o.has("note")){
                this.note = o.getString("note");
            }else{
                this.note = "";
            }
            this.setNom(nom);
            this.setEmplacement(emplacement);
            this.setPoids(poids);
            this.setQuantite(quantite);
        }catch(JSONException e){
            Log.e(CLASS_NAME, e.getMessage());
        }
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
        try {
            this.o.put("nom", nom);
        }catch(JSONException e){
            Log.e(CLASS_NAME + ".setNom()", e.getMessage());
        }
    }

    public String getEmplacement() {
        return emplacement;
    }

    private void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
        try {
            this.o.put("emplacement", emplacement);
        }catch(JSONException e){
            Log.e(CLASS_NAME + ".setEmplacement()", e.getMessage());
        }
    }

    public String getPoids() {
        return poids;
    }

    private void setPoids(String poids) {
        this.poids = poids;
        try {
            this.o.put("poids", poids);
        }catch(JSONException e){
            Log.e(CLASS_NAME + ".setPoids()", e.getMessage());
        }
    }

    public int getQuantite() {
        return quantite;
    }

    private void setQuantite(int quantite) {
        this.quantite = quantite;
        try {
            this.o.put("qt", quantite);
        }catch(JSONException e){
            Log.e(CLASS_NAME + ".setQuantite()", e.getMessage());
        }
    }

    public void add(){
        this.quantite++;
        try {
            this.o.put("qt", this.quantite);
        }catch (JSONException e){
            Log.e(CLASS_NAME, e.getMessage());
        }
    }

    public void del(){
        this.quantite--;
        try {
            this.o.put("qt", this.quantite);
        }catch (JSONException e){
            Log.e(CLASS_NAME, e.getMessage());
        }
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
        try {
            this.o.put("note", note);
        }catch(JSONException e){
            Log.e(CLASS_NAME + ".setNote()", e.getMessage());
        }
    }

    public JSONObject getJson(){
        JSONObject o = new JSONObject();
        try {
            o.put("nom", this.getNom());
            o.put("emplacement", this.getEmplacement());
            o.put("poids", this.poids);
            o.put("note", this.note);
            o.put("qt", this.quantite);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".getJson()", e.getMessage());
        }
        return o;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
        try {
            this.o.put("bonus", bonus);
        }catch(JSONException e){
            Log.e(CLASS_NAME + ".setBonus()", e.getMessage());
        }
    }
}
