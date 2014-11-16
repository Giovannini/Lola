package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by giovannini on 10/22/14.
 */
public class Caractéristiques {
    String CLASS_NAME = "Caractéristiques";
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
            Log.e(CLASS_NAME, e.getMessage());
        }
    }

    public int getForce() {
        return force;
    }

    public void ajoutForce(){
        this.force ++;
        try {
            obj.put("Force", this.force);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".ajoutForce()",
                    "Erreur JSON lors de l'ajout d'un point de FOR.");
        }
    }
    public void retireForce(){
        this.force --;
        try {
            obj.put("Force", this.force);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".retireForce()",
                    "Erreur JSON lors du retranchement d'un point de FOR.");
        }
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getDextérité() {
        return dextérité;
    }

    public void ajoutDexterite(){
        this.dextérité ++;
        try {
            obj.put("Dextérité", this.dextérité);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".ajoutDexterite()",
                    "Erreur JSON lors de l'ajout d'un point de DEX.");
        }
    }
    public void retireDexterite(){
        this.dextérité --;
        try {
            obj.put("Dextérité", this.dextérité);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".retireDexterite()",
                    "Erreur JSON lors du retranchement d'un point de DEX.");
        }
    }

    public void setDextérité(int dextérité) {
        this.dextérité = dextérité;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void ajoutIntelligence(){
        this.intelligence ++;
        try {
            obj.put("Intelligence", this.intelligence);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".ajoutIntelligence()",
                    "Erreur JSON lors de l'ajout d'un point de INT.");
        }
    }
    public void retireIntelligence(){
        this.intelligence --;
        try {
            obj.put("Intelligence", this.intelligence);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".retireIntelligence()",
                    "Erreur JSON lors du retranchement d'un point de INT.");
        }
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSagesse() {
        return sagesse;
    }

    public void ajoutSagesse(){
        this.sagesse ++;
        try {
            obj.put("Sagesse", this.sagesse);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".ajoutSagesse()",
                    "Erreur JSON lors de l'ajout d'un point de SAG.");
        }
    }
    public void retireSagesse(){
        this.sagesse --;
        try {
            obj.put("Intelligence", this.sagesse);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".retireSagesse()",
                    "Erreur JSON lors du retranchement d'un point de SAG.");
        }
    }

    public void setSagesse(int sagesse) {
        this.sagesse = sagesse;
    }

    public int getConstitution() {
        return constitution;
    }

    public void ajoutConstitution(){
        this.constitution ++;
        try {
            obj.put("Constitution", this.constitution);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".ajoutConstitution()",
                    "Erreur JSON lors de l'ajout d'un point de CON.");
        }
    }
    public void retireConstitution(){
        this.constitution --;
        try {
            obj.put("Constitution", this.constitution);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".retireConstitution()",
                    "Erreur JSON lors du retranchement d'un point de CON.");
        }
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getCharisme() {
        return charisme;
    }

    public void ajoutCharisme(){
        this.charisme ++;
        try {
            obj.put("Charisme", this.charisme);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".ajoutCharisme()",
                    "Erreur JSON lors de l'ajout d'un point de CHA.");
        }
    }
    public void retireCharisme(){
        this.charisme --;
        try {
            obj.put("Charisme", this.charisme);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".retireCharisme()",
                    "Erreur JSON lors du retranchement d'un point de CHA.");
        }
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
                Log.e(CLASS_NAME + ".addPv", e.getMessage());
            }
        }
    }

    public void removePv(){
        if(this.pv > -10) {
            this.pv--;
            try {
                obj.put("Pv", this.pv);
            } catch (JSONException e) {
                Log.e(CLASS_NAME + ".removePv", e.getMessage());
            }
        }
    }

    public int getPvmax() {
        return pvmax;
    }

    public void setPvmax(int pvmax) {
        this.pvmax = pvmax;
    }

    public int getModificateur(int caract){
        int result = (caract - 10)/2;
        if (caract < 10)
            result = (caract - 11)/2;
        return result;
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
