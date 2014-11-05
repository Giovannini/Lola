package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Classe {
    String nom;
    int niveau;
    int points_de_compétence_par_niveau;
    Map<String, String> particularités;

    Personnage perso;
    JSONObject obj;

    public Classe() {
        particularités = new HashMap<String, String>();
    }

    public Classe(JSONObject o, Personnage p){
        this();
        this.obj = o;
        this.perso = p;
        try{
            int niveau = o.getInt("Niveau");
            if (niveau > 0) {//Si le personnage possède des caractéristiques de la classe
                this.nom = (o.getString("Nom"));
                this.niveau = niveau;
                this.points_de_compétence_par_niveau = o.getInt("pcpn");
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

    public void addNiveau(){
        this.niveau++;
        try {
            this.obj.put("Niveau", this.niveau);
            perso.addPointCompetence(this.points_de_compétence_par_niveau
                                        + perso.getCaractéristiques().getModificateur("int"));
            if (this.niveau % 3 == 0){
                //Gain d'un don
            }
            if(this.niveau % 4 == 0){
                //gain d'un point de caractéristique
                this.perso.addPointCaractéristique();
            }
            //La sauvegarde se fait plus tard, dans la classe Personnage.
        }catch (JSONException e){
            Log.e("Class.addNiveau()", "Erreur JSON en ajoutant un niveau.");
        }
    }
}
