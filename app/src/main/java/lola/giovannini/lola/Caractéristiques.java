package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by giovannini on 10/22/14.
 */
public class Caractéristiques {
    int force, dextérité, intelligence, sagesse, constitution, charisme, pv, pvmax;
    JSONObject obj;

    public Caractéristiques() {
    }

    public Caractéristiques(JSONObject o){
        this();
        this.obj = o;
        try {
            setPv(o.getInt("Pv"));
            setPvmax(o.getInt("PvMax"));
            setForce(o.getInt("Force"));
            setDextérité(o.getInt("Dextérité"));
            setConstitution(o.getInt("Constitution"));
            setIntelligence(o.getInt("Intelligence"));
            setSagesse(o.getInt("Sagesse"));
            setCharisme(o.getInt("Charisme"));
        }catch (JSONException e){
            Log.e("Caractéristiques", e.getMessage());
        }
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getDextérité() {
        return dextérité;
    }

    public void setDextérité(int dextérité) {
        this.dextérité = dextérité;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSagesse() {
        return sagesse;
    }

    public void setSagesse(int sagesse) {
        this.sagesse = sagesse;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getCharisme() {
        return charisme;
    }

    public void setCharisme(int charisme) {
        this.charisme = charisme;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void addPv(){
        if(this.pv < this.pvmax) {
            this.pv++;
            try{
                obj.put("Pv", this.pv);
            }catch(JSONException e){
                Log.e("Caractéristiques.addPv", e.getMessage());
            }
        }
    }

    public void removePv(){
        if(this.pv > -10)
            this.pv--;
    }

    public int getPvmax() {
        return pvmax;
    }

    public void setPvmax(int pvmax) {
        this.pvmax = pvmax;
    }

    public int getModificateur(int caract){
        return (caract - 10)/2;
    }

    public int getModificateur(String s){
        int result;
        if (s.equals("dex")){
            result = getModificateur(this.getDextérité());
        }else if (s.equals("int")){
            result = getModificateur(this.getIntelligence());
        }else if (s.equals("for")){
            result = getModificateur(this.getForce());
        }else if (s.equals("con")){
            result = getModificateur(this.getConstitution());
        }else if (s.equals("sag")){
            result = getModificateur(this.getSagesse());
        }else if (s.equals("cha")){
            result = getModificateur(this.getCharisme());
        }else{
            result = 0;
        }
        return result;
    }
}
