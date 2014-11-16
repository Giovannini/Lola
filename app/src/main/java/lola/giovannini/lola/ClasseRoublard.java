package lola.giovannini.lola;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giovannini on 11/8/14.
 */
public class ClasseRoublard extends Classe {
    String CLASS_NAME = "ClasseRoublard";
    Personnage p;
    JSONObject obj;
    int image;
    int nbTalents;

    int[][] bonusBBA;
    int[] bonusRéflexes;
    int[] bonusVigueur;
    int[] bonusVolonté;

    Particularité[][] allParts;
    List<Particularité> parts;
    List<Particularité> talents;

    public ClasseRoublard(JSONObject o, Personnage p){
        super("Roublard", o, p);
        this.obj = o;
        this.p = p;
        try {
            this.niveau = o.getInt("Niveau");
        }catch (JSONException e){
            Log.e(CLASS_NAME + ": ", "Erreur JSON en lisant le niveau.\n" + e.getMessage());
        }
        this.parts = new ArrayList<Particularité>();
        this.points_de_compétence_par_niveau = 8;
        initAllParts();
        initBonusBBA();
        initRéflexes();
        initVigVol();

        initParts();
        initTalents();
        image = R.drawable.roublard;
    }

    private void initTalents(){
        this.talents = new ArrayList<Particularité>();
        talents.add(new Particularité(
                "Astuce de ninja",
                "Un roublard qui prend ce talent peut choisir une astuce de ninja. Le roublard ne peut pas choisir une astuce de ninja qui porte le même nom qu’un talent de roublard. Il peut choisir une astuce qui demande des points de ki mais ne pourra pas l’utiliser à moins de posséder le talent réserve de ki. Un roublard peut choisir ce talent à plusieurs reprises."
        ));
        talents.add(new Particularité(
                "Attaque sanglante",
                "Ce talent permet au roublard de faire saigner la victime de son attaque sournoise. Chaque round, la cible subit un nombre de points de dégâts égal au nombre de dés d’attaque sournoise du roublard (par exemple 4 points de saignement dans le cas d’une attaque sournoise à +4d6). Ces dégâts se produisent chaque round au début du tour de la cible. Le saignement peut être arrêté par un test de Premiers secours (DD 15) ou par n’importe quel effet qui permet de récupérer des points de vie perdus. Les dégâts de saignement infligés par ce talent ne sont pas cumulatifs. Ils ignorent toutes les réductions de dégâts."
        ));
        talents.add(new Particularité(
                "Beau parleur",
                "Une fois par jour, le roublard peut lancer deux dés au cours d’un test de Bluff et choisir le meilleur résultat des deux. Il doit décider d’utiliser ce talent avant le test de Bluff . Le roublard gagne une utilisation quotidienne supplémentaire de ce talent par tranche de 5 niveaux de roublard qu’il possède. "
        ));
        talents.add(new Particularité(
                "Botte secrète",
                "Ce talent permet au roublard de gagner un don de combat supplémentaire."
        ));
        talents.add(new Particularité(
                "Brouiller les pistes",
                "Quand un individu raconte un évènement, le roublard fait un test opposé de " +
                        "Diplomatie pour habilement ponctuer le cours de l’histoire de commentaires ou d’affirmations, troublant ainsi la capacité du conteur à se rappeler des détails spécifiques ou exacts. Si le roublard réussit, la cible ne réalise pas que les interjections du roublard l’ont embrouillée. Néanmoins, si le roublard échoue, la cible a droit à un test de Psychologie (DD égal au test de Diplomatie raté du roublard) pour s’apercevoir que celui-ci a délibérément tenté d’embrouiller son récit. " +
                        "Tous les roublards qui répondent aux conditions requises indiquées peuvent prendre ce nouveau talent de roublard, mais ils sont plus courants chez les kitsune."
        ));
        talents.add(new Particularité(
                "Contacts au marché noir",
                "Grâce à ce talent, le roublard peut obtenir de meilleurs objets magiques grâce à" +
                        " ses contacts au marché noir. Il considère toutes les villes comme d’une" +
                        " taille de plus qu’elles ne le sont réellement quand il s’agit de " +
                        "déterminer la valeur de base maximale (en pièces d’or) des objets à " +
                        "vendre, ainsi que le nombre d’objets magiques mineurs, " +
                        "moyens et majeurs à disposition. Si la ville est déjà une capitale, " +
                        "il a accès à tous les objets mineurs et moyens et à 3d8 objets majeurs. " +
                        "Un test de Diplomatie réussi lui permet de faire comme si la ville était" +
                        " de deux tailles de plus qu’elle ne l’est. Si la ville est déjà une " +
                        "capitale et que le roublard réussit son test, " +
                        "il a accès à la totalité des objets magiques. Si la ville est déjà une " +
                        "grande cité et qu’il réussit son test, il a accès à tous les objets " +
                        "mineurs et moyens et à 3d8 objets majeurs. " +
                        "Un test réussi lui permet aussi de vendre des objets volés sur le marché" +
                        " noir. S’il rate le test de 5 ou plus, il fait peur aux acteurs du " +
                        "marché et traite la ville normalement pendant 1 semaine. De plus, " +
                        "les chefs du marché noir risquent d’avertir les autorités de sa " +
                        "présence, en guise de représailles pour avoir perturbé le marché ou pour" +
                        " détourner l’attention de leurs activités illicites. " +
                        "Le DD du test dépend de la taille de l’agglomération et figure dans la table ci-contre."
        ));
        talents.add(new Particularité(
                "Défense Offensive",
                "Lorsqu’un roublard disposant de ce talent porte contre une créature une attaque de corps à corps qui inflige des dégâts d’attaque sournoise, il gagne un bonus d’esquive de +1 à la CA pour chaque dé d’attaque sournoise utilisé contre cette créature pendant 1 round."
        ));
        talents.add(new Particularité(
                "Discret et rapide",
                "Ce talent permet au roublard d’utiliser la compétence de Discrétion en se déplaçant à sa vitesse normale (non réduite) sans pénalité."
        ));
        talents.add(new Particularité(
                "Faux ami",
                "Un roublard qui possède ce talent reçoit un bonus de +4 à ses tests de Bluff " +
                        "pour convaincre quelqu’un qu’il n’a jamais rencontré ou connaît mal qu’ils se sont déjà rencontrés ou se connaissent bien. " +
                        "Tous les roublards qui répondent aux conditions requises indiquées peuvent prendre ce nouveau talent de roublard, mais ils sont plus courants chez les kitsune."
        ));
        talents.add(new Particularité(
                "Oeil de l'archer",
                "Un roublard disposant de ce talent peut appliquer ses dégâts d’attaque sournoise aux attaques à distance visant des cibles situées dans un rayon de 9 mètres (6 c) et bénéficiant d’un camouflage simple. Les cibles qui bénéficient d’un camouflage total restent immunisées aux attaques sournoises."
        ));
        talents.add(new Particularité(
                "Sournois",
                "Un roublard qui dispose de ce talent gagne un bonus de circonstances de +4 aux tests d’Escamotage quand il essaie de dissimuler une arme. De plus, s’il fait une attaque sournoise pendant le round de surprise à l’aide d’une arme cachée dont son adversaire ignore l’existence, il n’a pas besoin de lancer les dés de dégâts supplémentaires : l’attaque sournoise inflige automatiquement le maximum. Chaque jour, il peut utiliser ce talent un nombre de fois égal à son modificateur de Charisme (0 au minimum)."
        ));
        talents.add(new Particularité(
                "Subtilisation au combat",
                "Un roublard disposant de ce talent gagne Science de la subtilisation comme don supplémentaire."
        ));

        for (Particularité p : parts) {
            if(talents.contains(p))
                talents.remove(p);
        }
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

    public void initParts(){
        this.nbTalents = 0;
        this.parts.clear();
        for (int i = 0; i<this.niveau;i++){

            Particularité[] ps = this.allParts[i];
            for (Particularité p : ps){
                if (p.getNom().contains("Attaque sournoise") && i > 0){
                    /*
                     * Modifier le nom de l'attaque sournoise pour lui rajouter un dé
                     * On sait qu'il s'agit du premier élément ajouté dans la liste.
                    */
                    /*Log.i(CLASS_NAME + ".initParts()",
                            p.getNom() + " remplacera " + this.parts.get(0).getNom());*/
                    this.parts.get(0).setNom(p.getNom());
                }else if (p.getNom().contains("Sens des pièges") && i > 2) {
                    /*
                     * Modifier le nom de sens des pièges pour lui rajouter un point.
                    */
                    int k = 0;
                    while (!this.parts.get(k).getNom().contains("Sens des pièges")) {
                        k++;
                    }
                    /*Log.i(CLASS_NAME + ".initParts()",
                            p.getNom() + " remplacera " + this.parts.get(k).getNom());*/
                    this.parts.get(k).setNom(p.getNom());
                }else if(p.getNom().contains("Talent de")) {
                    this.nbTalents++;
                }else{
                /*
                 * Les talents sont aussi ajoutés à la liste. On pourra spécifier celui
                 * que l'on souhaite en cliquant sur un "Talent" vide dans le menu.
                 */
                    this.parts.add(p);
                }
            }
        }
        try {
            JSONArray talents = p.getObj().getJSONArray("Talents");
            int l = talents.length();
            for (int i = 0; i < this.nbTalents; i++) {
                Particularité talent;
                if (i<l) {
                    JSONObject t = talents.getJSONObject(i);
                     talent = new Particularité(
                            t.getString("nom"),
                            t.getString("description"));
                    /*Log.d(CLASS_NAME, "Talent ajouté: " + talent.getNom());*/
                }else{
                    talent = new Particularité("Talent", "");
                    /*Log.d(CLASS_NAME, "Talent vide ajouté.");*/
                }
                this.parts.add(talent);
            }
        }catch (JSONException e) {
            Log.e(CLASS_NAME + ".initParts()",
                    "Erreur JSON en lisant les talents.\n" + e.getMessage());
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
        return this.talents;
    }
}