package lola.giovannini.lola;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by giovannini on 10/17/14.
 */
public class Personnage {
    String CLASS_NAME = "Personnage";

    MainActivity main;

    String nom, race, alignement, sexe, peau, cheveux, yeux, religion;
    int niveau, expérience, malusXP, age, poids, taille, bonusTaille;
    int[] bba;
    int levelUpClass;
    /*Caractéristiques*/
    Caractéristiques caractéristiques;
    int valeurInitiative;
    /*Liste d'armes*/
    List<Arme> armes;
    /*Liste d'armures*/
    List<Armure> armures;
    /*Liste d'objets*/
    List<Objet> objets;
    /*Liste de particularités de race*/
    List<Particularité> particularitéRaces;
    /*Liste de particularités de classe*/
    List<Classe> classes;
    List<Particularité> allClassParts;
    /*Liste de dons*/
    List<Don> dons;
    /*Liste de compétences*/
    List<Compétence> compétences;
    int competencesPoints;
    int pointCaractéristiques;
    int pointDon;
    /*Map Bonus divers*/
    Map<String, Integer> divers;
    /*Argent*/
    int richesses[] = new int[4];

    JSONObject obj;

    public Personnage(){
    }

    public Personnage(JSONObject json, MainActivity main){
        this.main = main;

        this.obj = json;
        this.compétences = new ArrayList<Compétence>();
        this.classes = new ArrayList<Classe>();
        this.armes = new ArrayList<Arme>();
        this.armures = new ArrayList<Armure>();
        this.objets = new ArrayList<Objet>();
        this.divers = new HashMap<String, Integer>();
        this.dons = new ArrayList<Don>();
        this.particularitéRaces = new ArrayList<Particularité>();
        this.allClassParts = new ArrayList<Particularité>();

        //Log.d("Personnage", "Le parsing commence.");
        parse(this.obj);
        //Log.d("Personnage", "Le parsing s'est terminé.");
    }

    private void parse(JSONObject obj){

        try {
            this.setNom(obj.getString("Nom"));
            this.setRace(obj.getString("Race"));

            this.setAlignement(obj.getString("Alignement"));
            this.setNiveau(obj.getInt("Niveaux"));
            this.setExpérience(obj.getInt("XP"));
            this.setMalusXP(obj.getInt("MalusXP"));
            this.setSexe(obj.getString("Sexe"));
            this.setAge(obj.getInt("Age"));
            this.setPoids(obj.getInt("Poids"));
            this.setTaille(obj.getInt("Taille"));
            this.setPeau(obj.getString("Peau"));
            this.setCheveux(obj.getString("Cheveux"));
            this.setYeux(obj.getString("Yeux"));
            this.setReligion(obj.getString("Religion"));

            this.levelUpClass = obj.getInt("levelupclass");

            /* Objets */
            JSONArray objets = obj.getJSONArray("Equipement");
            for (int i=0, fini=objets.length();i<fini;i++){
                JSONObject o = objets.getJSONObject(i);
                Objet objet = new Objet(o, divers);
                this.objets.add(objet);
            }

            /*Richesse*/
            JSONObject jsonRic = obj.getJSONObject("Richesse");
            richesses[0] = jsonRic.getInt("Platine");
            richesses[1] = jsonRic.getInt("Or");
            richesses[2] = jsonRic.getInt("Argent");
            richesses[3] = jsonRic.getInt("Bronze");
            /*Caractéristiques*/
            JSONObject jsonCar = obj.getJSONObject("Caracteristique");
            this.pointCaractéristiques = obj.getInt("pointsCaractéristiques");
            Caractéristiques caractéristiques = new Caractéristiques(jsonCar);
            this.caractéristiques = caractéristiques;

            /* Classes */
            JSONArray classes = obj.getJSONArray("Classes");
            for (int i=0;i<classes.length();i++) {
                JSONObject json = classes.getJSONObject(i);
                System.out.println("Traitement de la classe "+ json.getString("Nom"));
                Classe c = null;
                if (json.getString("Nom").equals("Roublard")){
                    System.out.println("Création de la classe Roublard");
                    c = new ClasseRoublard(json, this);
                    this.classes.add(c);
                }else if (json.getString("Nom").equals("Maître des ombres")){
                    /**TODO
                     * Maître des ombres
                     */
                }
                this.allClassParts.addAll(c.getParticularités());
            }

            this.bonusTaille = obj.getInt("bonusTaille");
            System.out.println("Bonus taille = " + this.bonusTaille);
            for (Classe c : this.getClasses()){
                if (this.bba == null)
                    this.bba = c.getBonusBBA();
                else{
                    for (int i = 0, fini = c.getBonusBBA().length; i<fini;i++){
                        this.bba[i] += c.getBonusBBA()[i];
                    }
                }
            }
            for (int i = 0, fini = this.bba.length; i<fini;i++) {
                System.out.println("Av: this.bba[i] = " + this.bba[i]);
                this.bba[i] += bonusTaille;
                System.out.println("Ap: this.bba[i] = " + this.bba[i]);
            }
            this.valeurInitiative = obj.getInt("initiative");

            /* Race */
            JSONArray race = obj.getJSONArray("Race");
            for (int i=0;i<race.length();i++){
                Particularité pr = new Particularité(race.getJSONObject(i), this);
                particularitéRaces.add(pr);
            }

            /* Dons */
            this.pointDon = obj.getInt("pointDon");
            JSONArray dons = obj.getJSONArray("Dons");
            for (int i = 0; i<dons.length();i++){
                Don d = new Don(dons.getJSONObject(i));
                this.dons.add(d);
            }

            /* Armes */
            JSONArray armes = obj.getJSONArray("Armes");
            for (int i = 0; i< armes.length();i++){
                JSONObject o = armes.getJSONObject(i);
                Arme a = new Arme(o);
                this.armes.add(a);
            }

            /* Armures */
            JSONArray armures = obj.getJSONArray("Armures");
            for (int i = 0;i<armures.length();i++){
                JSONObject o = armures.getJSONObject(i);
                Armure a = new Armure(o);
                this.armures.add(a);
            }

            /* Compétences */
            competencesPoints = obj.getInt("pointCompetences");
            JSONArray competences = obj.getJSONArray("Compétences");
            for (int i = 0; i<competences.length();i++){
                JSONObject o = competences.getJSONObject(i);
                Compétence c = new Compétence(o, this);
                this.compétences.add(c);
            }

        }catch (JSONException e){
            Log.e("Personnage.parse()", e.getMessage());
        }

    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public String getRace() {
        return race;
    }

    private void setRace(String race) {
        this.race = race;
    }

    public String getAlignement() {
        return alignement;
    }

    private void setAlignement(String alignement) {
        this.alignement = alignement;
    }

    public String getSexe() {
        return sexe;
    }

    private void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPeau() {
        return peau;
    }

    private void setPeau(String peau) {
        this.peau = peau;
    }

    public String getCheveux() {
        return cheveux;
    }

    private void setCheveux(String cheveux) {
        this.cheveux = cheveux;
    }

    public String getYeux() {
        return yeux;
    }

    private void setYeux(String yeux) {
        this.yeux = yeux;
    }

    public String getReligion() {
        return religion;
    }

    private void setReligion(String religion) {
        this.religion = religion;
    }

    public int getNiveau() {
        return niveau;
    }

    private void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void levelUp(){
        this.niveau++;
        this.addLevelUpClassPoint();
        if (this.niveau % 3 == 0){
            this.pointDon ++;
        }
        if(this.niveau % 4 == 0){
            //gain d'un point de caractéristique
            this.addPointCaractéristique();
        }

        try {
            this.obj.put("Niveaux", this.niveau);
            this.obj.put("pointDon", this.pointDon);
            this.obj.put("levelupclass", this.levelUpClass);
        } catch (JSONException e) {
            Log.e("Personnage.levelUp()", e.getMessage());
        }
    }

    public int getExpérience() {
        return expérience;
    }

    private void setExpérience(int expérience) {
        this.expérience = expérience;
    }

    public void addXP(int xp){
        this.expérience += xp;
        try {
            this.obj.put("XP", this.expérience);
        }catch (JSONException e){
            Log.e("Personnage.setExperience", e.getMessage());
        }
        while(this.expérience > getXPForLevelUp()){
            levelUp();
        }
        main.saveJson(this.getObj());
    }

    public int getXPForLevelUp(){
        int result = 0;
        for (int i = this.niveau; i>0;i--){
            result+=i;
        }
        return 1000*result;
    }

    public int getMalusXP() {
        return malusXP;
    }

    public void setMalusXP(int malusXP) {
        this.malusXP = malusXP;
    }

    public void addMalusXP(int xp){
        this.malusXP += xp;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public int getPoids() {
        return poids;
    }

    private void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    private void setTaille(int taille) {
        this.taille = taille;
    }

    public int[] getBba() {
        return this.bba;
    }

    public int getBonusTaille() {
        return bonusTaille;
    }

    public void setBonusTaille(int bonusTaille) {
        this.bonusTaille = bonusTaille;
    }

    /*Caractéristiques*/
    public int getValeurInitiative() {
        return valeurInitiative;
    }

    public void setValeurInitiative(int valeurInitiative) {
        try {
            this.getObj().put("initiative", valeurInitiative);
            main.saveJson(this.getObj());
        }catch (JSONException e){
            Log.e("Personnage", "setValeurInitiative:\n" + e.getMessage());
        }
        this.valeurInitiative = valeurInitiative;
    }

    /*Argent*/
    public void addRichesses(int platine, int or, int argent, int bronze){
        try {
            JSONObject richesses = this.obj.getJSONObject("Richesse");
            this.richesses[0] += platine;
            this.richesses[1] += or;
            this.richesses[2] += argent;
            this.richesses[3] += bronze;
            richesses.put("Platine", this.richesses[0]);
            richesses.put("Or", this.richesses[1]);
            richesses.put("Argent", this.richesses[2]);
            richesses.put("Bronze", this.richesses[3]);
            main.saveJson(this.obj);
        }catch (JSONException e){
            Log.e("Personnage", "Problèmes lors de l'ajout de richesses:\n" + e.getMessage());
        }
    }



    public JSONObject getObj(){
        return this.obj;
    }

    public List<Compétence> getCompétences(){
        return this.compétences;
    }

    public List<Classe> getClasses(){
        return this.classes;
    }

    public List<Arme> getArmes() {
        return armes;
    }

    public List<Armure> getArmures() {
        return armures;
    }

    public List<Objet> getObjets() {
        return objets;
    }

    public Caractéristiques getCaractéristiques() {
        return caractéristiques;
    }

    public Map<String, Integer> getDivers() {
        return divers;
    }

    public int getRichesse(int i){
        if (i<4)
            return this.richesses[i];
        else
            return 0;
    }

    public void addObjet(Objet o){
        try {
            JSONArray objets = obj.getJSONArray("Equipement");
            objets.put(o.getJson());
            main.saveJson(this.obj);
        }catch (JSONException e){
            Log.e("Personnage.addObjet()", e.getMessage());
        }
        this.getObjets().add(o);
    }

    public int getCompetencesPoints() {
        return competencesPoints;
    }

    public void addPointCompetence(int p){
        this.competencesPoints += p;
        try {
            this.obj.put("pointCompetences", this.competencesPoints);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".addPointCompetences()", "Erreur JSON en ajoutant des points de " +
                    "compétence.");
        }
    }
    public void useCompetencePoint(){
        this.competencesPoints--;
        try {
            this.obj.put("pointCompetences", this.competencesPoints);
        }catch(JSONException e){
            Log.e("Personnage.useCompetencePoint", e.getMessage());
        }
    }

    public void removeWeapon(int i) {
        String nom = this.armes.get(i).getNom();
        this.armes.remove(i);
        try {
            JSONArray armes = obj.getJSONArray("Armes");
            armes.remove(i);
            main.saveJson(this.obj);
            Log.i(CLASS_NAME + ".removeWeapon()", nom + " removed.");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".removeWeapon()", "Problem removing the weapon " + nom + ":\n" +
                    e.getMessage());
        }
    }

    public void addWeapon(Arme a) {
        try {
            this.getArmes().add(a); //Ajout de l'arme à la liste des armes du personnage.

            this.getObj().getJSONArray("Armes").put(a.getObj());//Ajout de l'arme au fichier JSON.
            main.saveJson(this.obj);//Sauvegarde du JSON

            Log.i(CLASS_NAME + ".addWeapon()", "Arme " + a.getNom() + " ajoutée.");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".addWeapon()", "Problem removing the weapon " + nom + ":\n" + e
                    .getMessage());
        }
    }

    public void removeArmor(int i) {
        String nom = this.armures.get(i).getNom();
        this.armures.remove(i);
        try {
            JSONArray armures = obj.getJSONArray("Armures");
            armes.remove(i);
            main.saveJson(this.obj);
            Log.i(CLASS_NAME + ".removeArmor()", nom + " removed.");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".removeArmor()", "Problem removing the armor " + nom + ":\n" + e
                    .getMessage());
        }
    }

    public void addArmor(Armure a) {
        try {
            this.getArmures().add(a); //Ajout de l'arme à la liste des armes du personnage.

            this.getObj().getJSONArray("Armures").put(a.getObj());//Ajout de l'arme au fichier JSON.
            main.saveJson(this.obj);//Sauvegarde du JSON

            Log.i(CLASS_NAME + ".addArmor()",
                    "Armure " + a.getNom() + " ajoutée.");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".addArmor()",
                    "Problem removing the armor " + nom + ":\n" + e.getMessage());
        }
    }

    public int getPointCaractéristiques() {
        return pointCaractéristiques;
    }

    public void addPointCaractéristique(){
        this.pointCaractéristiques++;
        try {
            //Ajout du point de caractéristiques dans le fichier JSON.
            this.getObj().put("pointsCaractéristiques",this.pointCaractéristiques);
            //Sauvegarde du JSON plus tard dans le programme.

            Log.i(CLASS_NAME + ".addPointCaractéristique()",
                    "Ajout d'un point de caractéristiques.");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".addPointCaractéristique()",
                    "Erreur JSON lors de l'ajout d'un point de caractéristiques:\n" + e
                            .getMessage());
        }
    }

    public void usePointCaracteristique(){
        this.pointCaractéristiques--;
        try {
            //Notification de l'utilisation dans le fichier JSON.
            this.getObj().put("pointsCaractéristiques", this.pointCaractéristiques);
            main.saveJson(this.obj);//Sauvegarde du JSON

            Log.i(CLASS_NAME + ".usePointCaractéristique()",
                    "Utilisation d'un point de caractéristiques.");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".usePointCaractéristique()",
                    "Erreur JSON lors de l'utilisation d'un point de caractéristiques:\n"
                    + e.getMessage());
        }
    }

    public List<Don> getDons() {
        return dons;
    }

    public int getPointDon() {
        return pointDon;
    }

    public void ajoutDon(Don d) {
        try {
            this.getDons().add(d); //Ajout du don à la liste des dons du personnage.

            this.getObj().getJSONArray("Dons").put(d.getObj());//Ajout de l'arme au fichier JSON.
            main.saveJson(this.obj);//Sauvegarde du JSON

            Log.i(CLASS_NAME + ".ajoutDon()", "Don " + d.getNom() + " ajouté.");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".ajoutDon()", "Problem adding the gift " + nom + ":\n" + e
                    .getMessage());
        }
    }

    public void useDonPoint(){
        this.pointDon--;
        try {
            //Notification de l'utilisation dans le fichier JSON.
            this.getObj().put("pointDon", this.pointDon);
            main.saveJson(this.obj);//Sauvegarde du JSON

            Log.i(CLASS_NAME + ".useDonPoint()", "Utilisation d'un point de don.");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".useDonPoint()",
                    "Erreur JSON lors de l'utilisation d'un point de don:\n" + e.getMessage());
        }
    }

    public void addLevelUpClassPoint(){
        this.levelUpClass++;
        try {
            this.obj.put("levelupclass", this.levelUpClass);
            main.saveJson(this.obj);
        }catch (JSONException e){
            Log.e("Personnage.addLevelUpClassPoint", "Erreur JSON à l'a jout d'un point LU.\n" +
                    e.getMessage());
        }
        Log.d("Personnage.addLevelUpClassPoint", "Ajout d'un point LU: " + this.levelUpClass);
    }

    public void useLevelUpClassPoint(){
        this.levelUpClass--;
        try {
            this.obj.put("levelupclass", this.levelUpClass);
            main.saveJson(this.obj);
        }catch (JSONException e){
            Log.e("Personnage.addLevelUpClassPoint", "Erreur JSON au retranchement d'un point LU" +
                    ".\n" + e.getMessage());
        }
        Log.d("Personnage.addLevelUpClassPoint", "Retranchement d'un point LU: " + this
                .levelUpClass);
    }

    public int getLevelUpClass() {
        return levelUpClass;
    }

    public List<Particularité> getParticularitéRaces() {
        return particularitéRaces;
    }

    public List<Particularité> getAllClassParts() {
        return allClassParts;
    }

    public void multiclassage(String nomClasse){
        JSONObject o = new JSONObject();
        try {
            o.put("Nom", nomClasse);
            o.put("Niveau", 1);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".multiclassage()",
                    "Erreur JSON lors de la création de l'objet de classe.\n" + e.getMessage());
        }
        if (nomClasse.toLowerCase().equals("maître des ombres")){
            Classe newClass = new ClasseMaîtreDesOmbres(o, this);
            this.classes.add(newClass);
            newClass.addPointCompetences();
            try {
                this.obj.getJSONArray("Classes").put(o);
                main.saveJson(this.obj);
            }catch (JSONException e){
                Log.e(CLASS_NAME + "multiclassage()",
                        "Erreur JSON lors de l'ajout de la nouvelle classe au fichier JSON.\n" +
                                e.getMessage());
            }
        }
    }

    public MainActivity getMain() {
        return main;
    }

    public void saveJSON(){
        this.main.saveJson(this.obj);
    }
}
