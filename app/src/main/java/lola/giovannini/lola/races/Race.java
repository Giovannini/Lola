package lola.giovannini.lola.races;

import org.json.JSONObject;

import java.util.List;

import lola.giovannini.lola.Particularité;
import lola.giovannini.lola.Personnage;

/**
 * Created by giovannini on 11/17/14.
 */
public abstract class Race {
    String nom;
    Personnage perso;

    public Race(String nom, Personnage perso) {
        this.nom = nom;
        this.perso = perso;
    }

    public String getNom(){
        return this.nom;
    }

    public Personnage getPerso() {
        return perso;
    }

    public abstract String getTaille();
    public abstract String getDescription();
    public abstract List<Particularité> getParticularites();
}
