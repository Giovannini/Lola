package lola.giovannini.lola.classes;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lola.giovannini.lola.Particularité;
import lola.giovannini.lola.Personnage;


public abstract class Classe {
    String CLASS_NAME = "Classe";

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
        this.niveau++;
        p.useLevelUpClassPoint();
        try {
            this.obj.put("Niveau", this.niveau);
            addPointCompetences();
            //La sauvegarde se fait plus tard, dans la classe Personnage.
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".addNiveau()", "Erreur JSON en ajoutant un niveau.");
        }
        initParts();
    }

    public void addPointCompetences(){
        p.addPointCompetence(this.getPoints_de_compétence_par_niveau()
                + p.getCaractéristiques().getModificateur("int"));
    }
    public abstract List<Particularité> getParticularités();

    public abstract int getNiveau();

    public abstract int[] getBonusBBA();
    public abstract int getBonusRéflexes();
    public abstract int getBonusVigueur();
    public abstract int getBonusVolonté();
    public abstract JSONObject getObj();
    public abstract int getImage();
    public abstract int getPoints_de_compétence_par_niveau();
    public abstract List<Particularité> getTalents();
    public abstract void initParts();
}
