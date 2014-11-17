package lola.giovannini.lola.activite_main.races;

import java.util.ArrayList;
import java.util.List;

import lola.giovannini.lola.activite_main.Particularité;
import lola.giovannini.lola.activite_main.Personnage;

/**
 * Created by giovannini on 11/17/14.
 */
public class RaceHalfelin extends Race{
    String description;
    List<Particularité> particularites;
    String taille;

    public RaceHalfelin(Personnage perso) {
        super("Halfelin", perso);
        setDescription();
        initCaracs();
        this.taille = "P";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public List<Particularité> getParticularites() {
        return this.particularites;
    }

    private void setDescription(){
        this.description = "Les halfelins sont des créatures optimistes, joyeuses, " +
                "inexplicablement chanceuses, dotées d’une passion pour les voyages, " +
                "petites en taille mais incroyablement fanfaronnes et curieuses. À la fois très " +
                "émotives et faciles à vivre, elles préfèrent éviter de s’énerver et se tenir " +
                "toujours prêtes à profiter de toutes les opportunités. Les halfelins ne se " +
                "laissent aller à des crises violentes ou émotionnelles que rarement, " +
                "et en tout les cas moins souvent que les autres races d'humeur plus changeante. " +
                "Ils ne perdent quasiment jamais leur sens de l’humour, " +
                "même face à un désastre imminent.\n" +
                "Ce sont des opportunistes invétérés. Comme leur physique ne leur permet pas " +
                "toujours de se défendre, ils ont appris à faire le dos rond ou à se faire tout " +
                "petits. Mais leur curiosité prend souvent le pas sur leur bon sens et les pousse" +
                " vers de mauvaises décisions ou des situations difficiles.\n" +
                "Cette même curiosité les incite à voyager et à explorer de nouveaux endroits et " +
                "à tenter de nouvelles expériences. Mais, malgré cela, " +
                "les halfelins possèdent un sens du foyer développé. Il n’est d’ailleurs pas rare" +
                " d’en voir certains dépenser plus que de raison pour améliorer le confort de " +
                "leur demeure.";
    }

    private void initCaracs(){
        this.particularites = new ArrayList<Particularité>();
        particularites.add(new Particularité(
                "Petite taille",
                "Les halfelins sont des créatures de taille P. Ils gagnent donc un bonus de " +
                        "taille de +1 à leur classe d’armure et aux jets d’attaque, " +
                        "un malus de -1 à leur bonus de manœuvres offensives (BMO) et à leur " +
                        "degré de manœuvres défensives (DMD), ainsi qu’un bonus de taille de +4 " +
                        "aux tests de Discrétion.",
                "CA:1;Discrétion:4",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Vitesse lente",
                "Les halfelins possèdent une vitesse de déplacement de base de 6 mètres (4 cases).",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Sans peur",
                "Les halfelins reçoivent un bonus racial de +2 à tous les jets de sauvegarde " +
                        "contre la terreur. Ce bonus se cumule avec celui provenant de la chance " +
                        "des halfelins.",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Chance des halfelins",
                "Les halfelins reçoivent un bonus racial de +1 à tous les jets de sauvegarde.",
                "SAUVVIG:1;SAUVREF:1;SAUVVOL:1",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Sens aiguisés",
                "Les halfelins reçoivent un bonus racial de +2 à tous les tests de Perception.",
                "Perception:2",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Bon équilibre",
                "Les halfelins reçoivent un bonus racial de +2 à tous les tests d’Acrobaties et " +
                        "d’Escalade.",
                "Escalade:2;Acrobatie:2",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Armes familières",
                "Les halfelins sont formés au maniement des frondes et considèrent toutes les " +
                        "armes dont le nom comporte le mot «halfelin» comme des armes de guerre.",
                "",
                this.getPerso()
        ));
    }

    public String getTaille(){
        return taille;
    }
}
