package lola.giovannini.lola.activite_main;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Compétence {
    String CLASS_NAME = "Compétence";

    String nom;
    int total, rang;
    boolean cc;
    Personnage perso;
    JSONObject o;

    public Compétence(JSONObject o, Personnage perso){
        this.perso = perso;
        this.o = o;
        try {
            this.setNom(o.getString("nom"));
            this.setCc(o.getInt("cc") == 1);
            this.setRang(o.getInt("rang"));
            int divers = 0;
            if (perso.getDivers().containsKey(this.nom))
                divers = perso.getDivers().get(this.nom);
            this.setTotal(this.getRang() + divers + perso.getCaractéristiques()
                    .getModificateur(o.getString("carac")));
        }catch(JSONException e){
            Log.e(CLASS_NAME, e.getMessage());
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
            total++;//Only for display
            try {
                this.o.put("rang", this.rang);
            }catch(JSONException e){
                Log.e(CLASS_NAME + ".addPoint()", e.getMessage());
            }
            perso.useCompetencePoint();
            return true;
        }else {
            return false;
        }
    }
}
