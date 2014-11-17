package lola.giovannini.lola.activite_main.races;

import java.util.ArrayList;
import java.util.List;

import lola.giovannini.lola.activite_main.Particularité;
import lola.giovannini.lola.activite_main.Personnage;

/**
 * Created by giovannini on 11/17/14.
 */
public class RaceNain extends Race{
    String description;
    List<Particularité> particularites;
    String taille;

    public RaceNain(Personnage perso) {
        super("Nain", perso);
        setDescription();
        initCaracs();
        this.taille = "M";
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
        this.description = "Les nains forment une race stoïque et sévère enfouie dans des cités " +
                "sculptées au cœur des montagnes. C’est une race fermement décidée à combattre " +
                "les déprédations de monstres sauvages tels que les orques et les gobelins. La " +
                "réputation des nains les dépeint comme des artisans austères et dénués d’humour " +
                "qui ne s’intéressent qu’à la terre. L’histoire des nains explique peut-être le " +
                "tempérament renfermé de la majorité d’entre eux : après tout, " +
                "ils habitent au sein de montagnes reculées ou dans de dangereux royaumes " +
                "souterrains où ils sont constamment en guerre avec les géants, " +
                "les gobelins et bien d’autres monstres encore.";
    }

    private void initCaracs(){
        this.particularites = new ArrayList<Particularité>();
        particularites.add(new Particularité(
                "Taille moyenne",
                "Les nains sont des créatures de taille M, ce qui ne leur apporte aucun bonus ni " +
                        "malus de taille.",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Lentement mais sûrement",
                "Les nains possèdent une vitesse de déplacement de base de 6 mètres (4 cases) " +
                        "mais celle-ci n’est pas modifiée par leur armure ni par la charge qu’ils" +
                        " portent.",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Vision dans le noir",
                "Les nains peuvent voir dans le noir jusqu’à une distance de 18 mètres (12 cases).",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Avidité",
                "Les nains gagnent un bonus racial de +2 aux tests d’Estimation visant à " +
                        "déterminer le prix d’objets non-magiques comportant des métaux ou des " +
                        "pierres précieuses.",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Connaissance de la pierre",
                "Les nains reçoivent un bonus racial de +2 aux tests de Perception pour remarquer" +
                        " les irrégularités dans les constructions en pierre, " +
                        "comme les pièges ou les portes cachées dans les murs ou les sols en " +
                        "pierre. Il suffit qu’ils s’approchent à moins de 3 mètres (2 cases) d’un" +
                        " tel élément pour bénéficier d’un test pour les remarquer et ceci qu’ils" +
                        " les recherchent activement ou non.",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Entraînement défensif",
                "Les nains gagnent un bonus d’esquive de +4 à la CA contre les monstres du " +
                        "sous-type géant.",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Haine",
                "Les nains gagnent un bonus racial de +1 aux jets d’attaque contre les créatures " +
                        "humanoïdes des sous-types orque et gobelinoïde grâce à une formation " +
                        "spéciale contre ces ennemis jurés.",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Robuste",
                "Les nains gagnent un bonus racial de +2 aux jet de sauvegarde contre le poison, " +
                        "les sorts et les pouvoirs magiques.",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Stabilité",
                "Les nains reçoivent un bonus racial de +4 à leur degré de manœuvres défensives " +
                        "(DMD) lorsqu’ils se tiennent sur le sol et qu’ils tentent de résister à " +
                        "une bousculade ou à un croc-en-jambe.",
                "",
                this.getPerso()
        ));
        particularites.add(new Particularité(
                "Armes familières",
                "Les nains sont formés au maniement des haches d’arme, " +
                        "des pics de guerre lourds et des marteaux de guerre. Ils considèrent " +
                        "toutes les armes dont le nom comporte le mot « nain » comme des armes de" +
                        " guerre.",
                "",
                this.getPerso()
        ));
    }

    public String getTaille(){
        return taille;
    }
}
