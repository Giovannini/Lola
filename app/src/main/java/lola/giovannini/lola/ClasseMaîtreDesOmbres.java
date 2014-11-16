package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by giovannini on 11/8/14.
 */
public class ClasseMaîtreDesOmbres  extends Classe {
    String CLASS_NAME = "ClasseMaîtreDesOmbres";
    Personnage p;
    JSONObject obj;
    int image;

    int points_de_compétence_par_niveau;

    int[][] bonusBBA;
    int[] bonusRéflexes;
    int[] bonusVigueur;
    int[] bonusVolonté;

    Particularité[][] allParts;
    List<Particularité> parts;

    public ClasseMaîtreDesOmbres(){

    }

    public ClasseMaîtreDesOmbres(JSONObject o, Personnage p){
        super("Maître des ombres", o, p);
        this.obj = o;
        this.p = p;
        try {
            this.niveau = o.getInt("Niveau");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ": ", "Erreur JSON en lisant le niveau.\n" + e.getMessage());
        }
        this.parts = new ArrayList<Particularité>();
        this.points_de_compétence_par_niveau = 6;
        initAllParts();
        initBonusBBA();
        initRéflexes();
        initVigVol();

        initParts();
        image = R.drawable.mdombres;
    }

    private void initAllParts(){
        Particularité[][] p = {
                {//1
                        new Particularité("Discrétion totale", "Le maître des ombres peut, à partir du niveau 1, utiliser sa compétence de Discrétion même si on l'observe. Tant qu’il se trouve à moins de 3 m (2 cases) d'une ombre, il peut se cacher, quand bien même il se trouve à découvert, sans le moindre endroit où se dissimuler. Il ne peut pas se cacher dans sa propre ombre.")
                },
                {//2
                        new Particularité("Esquive totale", "L’agilité phénoménale d’un roublard de niveau 2 " +
                                "lui permet d’esquiver les attaques magiques ou inhabituelles. S’il réussit un jet de Réflexes contre une attaque dont les dégâts devraient être réduits de moitié en cas de jet de Réflexes réussi, il évite l’attaque et ne subit pas le moindre dégât. Le roublard bénéficie de cet avantage uniquement s’il porte une armure légère ou aucune armure. Un roublard sans défense ne bénéficie pas des avantages de l’esquive totale."),
                        new Particularité("Vision dans le noir", "Au niveau 2, le maître des ombres voit dans l’obscurité la plus totale, jusqu'à une distance de 18 m (12 cases). S'il bénéficie déjà de ce pouvoir, son champ de vision augmente de 9 m (6 cases)."),
                        new Particularité("Esquive instinctive", "Dès le niveau 2, le maître des ombres conserve son éventuel bonus de Dextérité à la CA et ne peut être pris au dépourvu, même s'il est attaqué par un adversaire invisible (il perd toujours ce bonus s’il est immobilisé ou s’il est victime d’une feinte). Si un maître des ombres possède déjà cette aptitude grâce à une autre classe, il obtient l'esquive instinctive supérieure au lieu de l’esquive instinctive.")
                },
                {//3
                        new Particularité("Talent de roublard", ""),
                        new Particularité("Ombres illusoires", "Quand le maître des ombres atteint le niveau 3, il peut créer des illusions visuelles. Ce pouvoir magique produit un effet similaire au sort image silencieuse et utilise le niveau de maître des ombres du personnage pour déterminer son niveau de lanceur. Ce pouvoir peut être utilisé une fois par jour par tranche de deux niveaux dans la classe. Le DD de ce pouvoir est basé sur le Charisme."),
                        new Particularité("Appel du compagnon d'ombre", "Au niveau 3, le personnage peut convoquer un compagnon ombre, qui est une créature morte-vivante. Contrairement aux ombres normales, le compagnon est du même alignement que le personnage et il ne peut créer de rejetons. Il reçoit un bonus de +4 sur ses jets de Volonté pour réduire les dommages causés par l'énergie positive et ne peut être ni repoussé ni contrôlé. L’ombre accompagne fidèlement le maître des ombres, avec qui elle peut communiquer intelligiblement. Elle a un nombre de points de vie égal à la moitié des points de vie totaux de son maître. Elle utilise le bonus de base à l'attaque, ainsi que les bonus aux jets de sauvegarde du maître des ombres. À part cela, cette ombre est identique à celle que l'on trouve dans le Bestiaire Pathfinder.\n" +
                                "\n" +
                                "Si le compagnon ombre est détruit (ou si le personnage le renvoie volontairement), son maître doit réussir un jet de Vigueur (DD 15). S'il le rate, il perd un niveau. Un jet de sauvegarde réussi annule cette perte de niveau. Une ombre détruite ou renvoyée ne peut pas être remplacée avant trente jours.")
                },
                {//4
                        new Particularité("Convocation d'ombre", "À parti du niveau 4, le maître des ombres peut créer des créatures et des effets à partir d'ombre brute. Ce pouvoir fonctionne comme convocation d'ombres. Le niveau du lanceur de sort effectif est celui du maître des ombres. Le personnage peut utiliser ce pouvoir une fois par jour au niveau 4, puis une fois supplémentaire tous les deux niveaux au-delà (2/jour au niveau 6, 3/jour au niveau 8 et 4/jour au niveau 10). Au niveau 10, cette faculté devient une convocation d'ombres suprême. Le DD de ce pouvoir est basé sur le Charisme."),
                        new Particularité("Téléportation par les ombres 12m", "Au niveau 4, le maître des ombres acquiert la faculté de se déplacer d’une zone d’ombre à une autre comme s’il employait le sort porte dimensionnelle. Ce pouvoir est toutefois plus limité que le sort, en ce sens que le personnage doit impérativement partir d’une zone d’ombre et apparaître au niveau d’une autre. Il peut parcourir jusqu’à 12 m (8 cases) par jour de cette manière, qu’il peut décomposer en quatre sauts (de 3 m (2 cases) chacun) s’il le souhaite. Tous les deux niveaux au-delà du niveau 4, la distance qu’il peut parcourir double (24 m (16 cases) au niveau 6, 48 (32 cases) au niveau 8 et 96 m (64 cases) au niveau 10). Il peut diviser ce total en autant de sauts qu’il le souhaite mais chaque saut, aussi court soit-il, compte pour 3 m (2 cases).")
                },
                {//5
                        new Particularité("Roulé-boulé", "À partir du niveau 5, le maître des ombres peut accompagner les coups mortels. Cette capacité est la même que le pouvoir spécial Roulé-boulé de la classe de roublard."),
                        new Particularité("Esquive instinctive supérieure", "Au niveau 5, le maître des ombres ne peut plus être pris en tenaille. Les roublards ne peuvent plus lui porter d’attaques sournoises de cette manière, à moins d’avoir au moins quatre niveaux de roublard de plus que le personnage n’a de niveaux de maître des ombres.\n" +
                                "\n" +
                                "Si un personnage possède déjà l’aptitude d’esquive instinctive grâce à une autre classe, les niveaux des différentes classes proposant cette aptitude se cumulent pour déterminer le niveau de roublard nécessaire pour le prendre en tenaille.")
                },
                {//6
                        new Particularité("Talent de roublard", ""),
                        new Particularité("Téléportation par les ombres 24m", "")
                },
                {//7
                        new Particularité("Esprit fuyant", "Ce pouvoir acquis au niveau 7 représente l’aisance avec laquelle le maître des ombres parvient à échapper aux effets magiques cherchant à le contrôler. Il fonctionne comme le pouvoir spécial Esprit fuyant de la classe de roublard.")
                },
                {//8
                        new Particularité("Téléportation par les ombres 48m", ""),
                        new Particularité("Pouvoir des ombres", "Une fois par jour au niveau 8, et une fois supplémentaire au niveau 10, le maître des ombres peut utiliser l'ombre brute pour infliger des dégâts à ses adversaires, de la même manière que le sort magie des ombres. Le DD de ce pouvoir est basé sur le Charisme.")
                },
                {//9
                        new Particularité("Talent de roublard", "")
                },
                {//10
                        new Particularité("Esquive extraordinaire", "À partir du niveau 10, ce pouvoir fonctionne comme l’esquive totale. Les dégâts infligés par une attaque autorisant un jet de Réflexes pour réduire les dégâts de moitié sont réduits de moitié même si le personnage rate son jet de Réflexes (s’il le réussit, il l’esquive totalement)."),
                        new Particularité("Téléportation par les ombres 96m", ""),
                        new Particularité("Maître des ombres", "Au niveau 10, lorsque le maître des ombres est dans une zone de pénombre, il bénéficie d'une réduction de dégâts de 10/-, et un bonus de chance de +2 sur tous ses jets de sauvegarde. De plus, s'il réussit à confirmer un coup critique sur un adversaire qui se trouve lui aussi dans une zone de pénombre, cet adversaire est aveuglé pendant 1d6 rounds. "),
                }
        };

        this.allParts = p;
    }
    private void initBonusBBA(){
        int[][] b = {{0}, {1}, {2}, {3}, {3}, {4}, {5}, {6}, {6}, {7}};
        this.bonusBBA = b;
    }
    private void initRéflexes(){
        int[] r = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        this.bonusRéflexes = r;
    }
    private void initVigVol(){
        int[] v = {0,  1, 1, 1, 2, 2, 2, 3, 3, 3};
        this.bonusVigueur = v;
        this.bonusVolonté = v;
    }

    public void initParts(){
        this.parts.clear();
        for (int i = 0; i<this.niveau;i++){
            System.out.println(this.allParts.length);
            Particularité[] ps = this.allParts[i];
            for (Particularité p : ps){
                if (p.getNom().contains("Téléportation par les ombres") && i > 3){
                    /*
                     * Modifier le nom de l'attaque sournoise pour lui rajouter un dé
                     * On sait qu'il s'agit du huitième élément ajouté dans la liste.
                    */
                    int k = 0;
                    while (! this.parts.get(k).getNom().contains("Téléportation par les ombres")){
                        k++;
                    }
                    this.parts.get(k).setNom(p.getNom());
                }else if (p.getNom().equals("Esquive instinctive") && i > 2){
                    if (this.p.getAllClassParts().contains(p)){
                        this.parts.add(new Particularité("Esquive instinctive supérieure",
                                "Au niveau 5, le maître des ombres ne peut plus être pris en " +
                                        "tenaille. Les roublards ne peuvent plus lui porter " +
                                        "d’attaques sournoises de cette manière, " +
                                        "à moins d’avoir au moins quatre niveaux de roublard de " +
                                        "plus que le personnage n’a de niveaux de maître des " +
                                        "ombres. Si un personnage possède déjà l’aptitude " +
                                        "d’esquive instinctive grâce à une autre classe, " +
                                        "les niveaux des différentes classes proposant cette " +
                                        "aptitude se cumulent pour déterminer le niveau de " +
                                        "roublard nécessaire pour le prendre en tenaille."));
                    }
                }else if (p.getNom().equals("Talent de roublard")){
                    //Choix d'un talent parmis une liste.
                }else{
                    this.parts.add(p);
                }
            }
        }
    }

    public JSONObject getObj() {
        return obj;
    }

    public List<Particularité> getParticularités() {
        return parts;
    }

    @Override
    public int getNiveau() {
        return niveau;
    }

    @Override
    public int[] getBonusBBA() {
        return this.bonusBBA[this.niveau - 1];
    }

    @Override
    public int getBonusRéflexes() {
        return this.bonusRéflexes[this.niveau-1];
    }

    @Override
    public int getBonusVigueur() {
        return this.bonusVigueur[this.niveau-1];
    }

    @Override
    public int getBonusVolonté() {
        return this.bonusVolonté[this.niveau-1];
    }

    @Override
    public int getImage() {
        return image;
    }

    @Override
    public int getPoints_de_compétence_par_niveau() {
        return points_de_compétence_par_niveau;
    }

    @Override
    public List<Particularité> getTalents() {
        return null;
    }


}
