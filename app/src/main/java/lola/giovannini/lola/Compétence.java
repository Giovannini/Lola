package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by giovannini on 10/21/14.
 */
public class Compétence {
    String nom;
    int total, rang;
    boolean cc;
    Personnage perso;
    JSONObject o;

    public Compétence() {
    }

    public Compétence(JSONObject o, Personnage perso){
        this.perso = perso;
        this.o = o;
        try {
            this.setNom(o.getString("nom"));
            this.setCc(o.getInt("cc") == 1);
            this.setRang(o.getInt("rang"));
            this.setTotal(this.getRang() + o.getInt("divers") + perso.getCaractéristiques()
                    .getModificateur(o.getString("carac")));
        }catch(JSONException e){
            Log.e("Compétence", e.getMessage());
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public boolean isCc() {
        return cc;
    }

    public void setCc(boolean cc) {
        this.cc = cc;
    }

    public boolean addPoint(){
        if ((cc && rang < perso.getNiveau() + 3) || (!cc && rang < (perso.getNiveau() + 3)/2)){
            rang++;
            perso.useCompetencePoint();
            try {
                this.o.put("rang", this.rang);
            }catch(JSONException e){
                Log.e("Compétences.addPoint()", e.getMessage());
            }
            return true;
        }else {
            return false;
        }
    }
}
