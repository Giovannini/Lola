package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giovannini on 11/8/14.
 */
public class ClasseRoublard extends Classe {
    Personnage p;
    JSONObject obj;
    int image;

    int[][] bonusBBA;
    int[] bonusRéflexes;
    int[] bonusVigueur;
    int[] bonusVolonté;

    Particularité[][] allParts;
    List<Particularité> parts;

    public ClasseRoublard(JSONObject o, Personnage p){
        super("Roublard", o, p);
        this.obj = o;
        this.p = p;
        try {
            this.niveau = o.getInt("Niveau");
        }catch (JSONException e){
            Log.e("ClasseRoublard: ", "Erreur JSON en lisant le niveau.\n" + e.getMessage());
        }
        this.parts = new ArrayList<Particularité>();
        this.points_de_compétence_par_niveau = 8;
        initAllParts();
        initBonusBBA();
        initRéflexes();
        initVigVol();

        initParts();
        image = R.drawable.roublard;
    }

    private void initAllParts(){
        Particularité[][] p = {
                {//1
                        new Particularité("Attaque sournoise +1d6", "Lorsqu’un roublard attaque son adversaire " +
                                "dans une situation où ce dernier est incapable de se défendre efficacement, il peut lui infliger des dégâts supplémentaires en touchant un point sensible. L’attaque du roublard inflige des dégâts supplémentaires lorsque sa cible se trouve dans un cas de figure lui ôtant son bonus de Dextérité à la CA (qu’elle en ait un ou pas) ou qu’elle est prise en tenaille par le roublard et un compagnon de celui-ci. Si le roublard obtient un coup critique sur une attaque sournoise, ces dégâts supplémentaires ne sont pas multipliés. Une attaque à distance ne peut être une attaque sournoise que si la cible se trouve à 9 m (6 cases) ou moins. S’il utilise une arme qui inflige des dégâts non létaux (comme une matraque, un fouet ou une attaque à mains nues), le personnage peut porter une attaque sournoise infligeant elle aussi des dégâts non létaux. Il ne peut pas choisir cette option avec une arme occasionnant normalement des dégâts létaux (même en acceptant une pénalité de -4 au jet d’attaque). Le roublard ne peut tenter une attaque sournoise que contre un adversaire qu’il peut observer suffisamment pour en repérer les points faibles et dont il peut toucher les organes vitaux. Les attaques sournoises sont impossibles lorsque la cible bénéficie d’un camouflage."),
                        new Particularité("Recherche des pièges", "Les roublards ajoutent la moitié de leur " +
                                "niveau (au minimum +1) aux tests de Perception destinés à repérer des pièges" +
                                " ainsi qu’aux tests de Sabotage. Seul un roublard peut utiliser la compétence Sabotage pour désarmer les pièges magiques.")
                },
                {//2
                        new Particularité("Esquive totale", "L’agilité phénoménale d’un roublard de niveau 2 " +
                                "lui permet d’esquiver les attaques magiques ou inhabituelles. S’il réussit un jet de Réflexes contre une attaque dont les dégâts devraient être réduits de moitié en cas de jet de Réflexes réussi, il évite l’attaque et ne subit pas le moindre dégât. Le roublard bénéficie de cet avantage uniquement s’il porte une armure légère ou aucune armure. Un roublard sans défense ne bénéficie pas des avantages de l’esquive totale."),
                        new Particularité("Talent de roublard", "")
                },
                {//3
                        new Particularité("Attaque sournoise +2d6", ""),
                        new Particularité("Sens des pièges +1", "À partir du niveau 3, le roublard acquiert un sens intuitif lui permettant d’éviter les dangers des pièges, ce qui se traduit par un bonus de +1 aux jets de Réflexes effectués pour éviter les pièges et par un bonus d’esquive de +1 à la CA contre les attaques déclenchées par des pièges. Ces bonus passent à +2 lorsque le roublard atteint le niveau 6, à +3 lorsqu’il atteint le niveau 9, à +4 lorsqu’il atteint le niveau 12, à +5 lorsqu'il atteint le niveau 15 et enfin à +6 lorsqu’il atteint le niveau 18. Les bonus de sens des pièges provenant de classes différentes se cumulent entre eux.")
                },
                {//4
                        new Particularité("Esquive instinctive", "Au niveau 4, " +
                                "le roublard gagne la capacité de réagir aux dangers avant même que ses sens les perçoivent. Il ne peut pas être pris au dépourvu. De plus, il conserve son bonus de Dextérité à la classe d’armure lorsqu’il est attaqué par un adversaire invisible. La règle normale s’applique cependant lorsqu’il est immobilisé : dans ce cas, il perd son bonus de Dextérité à la classe d’armure. Un roublard disposant de cette capacité perd également son bonus de Dextérité à la classe d’armure si un adversaire parvient à réaliser une feinte en combat à son encontre. Si un roublard possède déjà la capacité d’esquive instinctive (grâce à une autre classe par exemple), il gagne à la place la capacité d’esquive instinctive supérieure."),
                        new Particularité("Talent de roublard", "")
                },
                {//5
                        new Particularité("Attaque sournoise +3d6", "")
                },
                {//6
                        new Particularité("Talent de roublard", ""),
                        new Particularité("Sens des pièges +2", "")
                },
                {//7
                        new Particularité("Attaque sournoise +4d6", "")
                },
                {//8
                        new Particularité("Esquive instinctive supérieure", "À partir du niveau 8, " +
                                "un roublard ne peut plus être pris en tenaille. Un autre roublard ne peut donc pas utiliser ses capacités pour lui porter une attaque sournoise en le prenant en tenaille, à moins de posséder au moins quatre niveaux de roublard de plus que lui. Si le personnage dispose déjà de la capacité d’esquive instinctive (voir plus haut) grâce à une autre classe, les niveaux de toutes les classes qui lui donnent cette capacité se cumulent pour déterminer le nombre de niveaux de roublard qu’un attaquant doit posséder pour pouvoir le prendre en tenaille."),
                        new Particularité("Talent de roublard", "")
                },
                {//9
                        new Particularité("Attaque sournoise +5d6", ""),
                        new Particularité("Sens des pièges +3", "")
                },
                {//10
                        new Particularité("Talent de maître roublard", "")
                },
                {//11
                        new Particularité("Attaque sournoise +6d6", "")
                },
                {//12
                        new Particularité("Talent de maître roublard", ""),
                        new Particularité("Sens des pièges +4", "")
                },
                {//13
                        new Particularité("Attaque sournoise +7d6", "")
                },
                {//14
                        new Particularité("Talent de maître roublard", "")
                },
                {//15
                        new Particularité("Attaque sournoise +8d6", ""),
                        new Particularité("Sens des pièges +5", "")
                },
                {//16
                        new Particularité("Talent de maître roublard", "")
                },
                {//17
                        new Particularité("Attaque sournoise +9d6", "")
                },
                {//18
                        new Particularité("Talent de maître roublard", ""),
                        new Particularité("Sens des pièges +4", "")
                },
                {//19
                        new Particularité("Attaque sournoise +9d6", "")
                },
                {//20
                        new Particularité("Talent de maître roublard", ""),
                        new Particularité("Coup de maître", "Lorsque le roublard atteint le niveau 20, ses attaques sournoises deviennent incroyablement létales. Chaque fois qu’il inflige des dégâts d’attaque sournoise, il peut choisir un des trois effets suivants : la cible peut être endormie pendant 1d4 heures, paralysée pendant 2d6 rounds ou tuée. Quel que soit l’effet choisi, la cible bénéficie d’un jet de Vigueur pour annuler l’effet additionnel. Le DD de ce jet de sauvegarde est égal à 10 + la moitié du niveau du roublard + le modificateur d’Intelligence du roublard. Une créature prise pour cible par un coup de maître ne peut plus être affectée par un autre coup de maître du même roublard au cours des vingt-quatre heures qui suivent, et ceci qu’elle ait réussi son jet de sauvegarde ou non. Les créatures qui sont immunisées aux attaques sournoises le sont également aux coups de maître.")
                }
        };

        this.allParts = p;
    }
    private void initBonusBBA(){
        int[][] b = {{0}, {1}, {2}, {3}, {3}, {4}, {5}, {6, 1}, {6, 1}, {7, 2},
                {8, 3}, {9, 4}, {9, 4}, {10, 5}, {11, 6, 1}, {12, 7, 2}, {12, 7, 2},
                {13, 8, 3}, {14, 9, 4}, {15, 10, 5}};
        this.bonusBBA = b;
    }
    private void initRéflexes(){
        int[] r = {2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12};
        this.bonusRéflexes = r;
    }
    private void initVigVol(){
        int[] v = {0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6};
        this.bonusVigueur = v;
        this.bonusVolonté = v;
    }

    protected void initParts(){
        this.parts.clear();
        for (int i = 0; i<this.niveau;i++){
            Log.i("ClassRoublard: ", "Particularités de niveau " + (i + 1));
            Particularité[] ps = this.allParts[i];
            for (Particularité p : ps){
                Log.i("ClasseRoublard.initParts()",
                        "Ajout de la particulatité " + p.getNom());
                if (p.getNom().contains("Attaque sournoise") && i > 0){
                    /*
                     * Modifier le nom de l'attaque sournoise pour lui rajouter un dé
                     * On sait qu'il s'agit du premier élément ajouté dans la liste.
                    */
                    Log.i("ClasseRoublard.initParts()",
                            p.getNom() + " remplacera " + this.parts.get(0).getNom());
                    this.parts.get(0).setNom(p.getNom());
                }else if (p.getNom().contains("Sens des pièges") && i > 2){
                    System.out.println("Bah oui!");
                    /*
                     * Modifier le nom de sens des pièges pour lui rajouter un point.
                    */
                    int k = 0;
                    while (! this.parts.get(k).getNom().contains("Sens des pièges")){
                        k++;
                    }
                    Log.i("ClasseRoublard.initParts()",
                            p.getNom() + " remplacera " + this.parts.get(k).getNom());
                    this.parts.get(k).setNom(p.getNom());
                }else if (p.getNom().equals("Talent de roublard")){
                    //Choix d'un talent parmis une liste.
                }else if (p.getNom().equals("Talent de maître roublard")){
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
}
