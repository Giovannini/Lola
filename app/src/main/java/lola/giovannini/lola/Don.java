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

    public Don(){

    }

    public Don(JSONObject o){
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
}
