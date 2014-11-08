package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public abstract class Classe {
    String nom;
    int niveau;
    Personnage p;
    int points_de_compétence_par_niveau;

    JSONObject obj;

    public Classe() {
    }

    public Classe(String nom, JSONObject o, Personnage p){
        this.nom = nom;
        this.obj = o;
        this.p = p;
    }

    public String getNom() {
        return nom;
    }

    public void addNiveau() {
        System.out.println("Classe.addNiveau()");
        this.niveau++;
        p.useLevelUpClassPoint();
        try {
            this.obj.put("Niveau", this.niveau);
            p.addPointCompetence(this.points_de_compétence_par_niveau
                    + p.getCaractéristiques().getModificateur("int"));

            //La sauvegarde se fait plus tard, dans la classe Personnage.
        }catch (JSONException e){
            Log.e("Class.addNiveau()", "Erreur JSON en ajoutant un niveau.");
        }
        initParts();
    }

    public abstract List<Particularité> getParticularités();

    public abstract int getNiveau();

    public abstract int[] getBonusBBA();
    public abstract int getBonusRéflexes();
    public abstract int getBonusVigueur();
    public abstract int getBonusVolonté();
    public abstract JSONObject getObj();
    public abstract int getImage();

    protected abstract void initParts();
}
