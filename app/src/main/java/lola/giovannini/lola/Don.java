package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by giovannini on 11/5/14.
 */
public class Don {
    String nom, effet, conditions;
    JSONObject obj;

    public Don(){

    }

    public Don(String nom, String effet, String conditions){
        this.nom = nom;
        this.effet = effet;
        this.conditions = conditions;

        this.obj = new JSONObject();
        try {
            obj.put("nom", nom);
            obj.put("effet", effet);
            obj.put("conditions", conditions);
        }catch (JSONException e){
            Log.e("Don",
                    "Erreur JSON lors de l'ajout du don:\n" + e.getMessage());
        }
    }

    public Don(JSONObject o){
        this.obj = o;
        try {
            nom = o.getString("nom");
            effet = o.getString("effet");
            conditions = o.getString("conditions");
        }catch (JSONException e){
            Log.e("Don",
                    "Erreur JSON lors de la création du don:\n" + e.getMessage());
        }
    }

    public Map<String, String> getConditions(){
        Map<String,String> conds = new HashMap<String, String>();
        String[] parsedConds = this.conditions.split(";");
        for (String cond : parsedConds){
            String[] parsedCond = cond.split(":");
            conds.put(parsedCond[0], parsedCond[1]);
        }
        return conds;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public JSONObject getObj() {
        return obj;
    }
}
