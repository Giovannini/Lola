package lola.giovannini.lola.activite_creation;

/**
 * Created by giovannini on 11/18/14.
 */
public class NameGenerator {
    String race, classe, nom, sexe;

    public NameGenerator(String race, String classe, String sexe) {
        this.race = race;
        this.classe = classe;
        this.sexe = sexe;
        generateName();
    }

    public void generateName(){
        if (race.equals("Halfelin")){
            if(sexe.equals("Masculin")) {
                String[] choice = {"Gorme","Rime","Rewilh","Rarder","Sonas","Doco Boffin",
                        "Feras","Fastar","Affert","Raffolk Knone","Marme","Giles Gysby", "Amert",
                        "Gery","Gyles","Folke","Folke Nesor","Heobo","Hobso","Adard"};
                int aleat = (int) Math.random() * 20;
                nom = choice[aleat];
            }
            else{
                String[] choice = {"Cely Basi","Tanta","Suse","Endel","Elin Seve","Ellan Burrow",
                        "Bridger","Jessa","Avell","Donna Gamge","Amor Gamgee","Diamay Ginsi",
                        "Bela","Elyn Bysshey","Cela","Ennel","Marey Male","Namay Ginsi", "Rida",
                        "Alian Banksi"};
                int aleat = (int) Math.random() * 20;
                nom = choice[aleat];
            }
        }else if (race.equals("Nain")){
            if(sexe.equals("Masculin")) {
                String[] choice = {"Imlin", "Ahar", "Aghad", "Gamin", "Turi", "Alim", "Bali", "Ukrar",
                        "Glukhor", "Banarv", "Dwali", "Gedu", "Zuri", "Banarv", "Thrasanz", "Thakilm",
                        "Undin", "Thali", "Gimli", "Aghar"};
                int aleat = (int) Math.random() * 20;
                nom = choice[aleat];
            }
            else{
                String[] choice = {"Khatelch","Gili","Urdud","Disanz","Thakar","Bifar","Duri",
                        "Kurdu","Bali","Khuda","Thrinain","Kurda","Frinarv","Dainarv","Arim",
                        "Urdur","Zigam","Bori","Kurdu","Ukas"};
                int aleat = (int) Math.random() * 20;
                nom = choice[aleat];
            }
        }


    }
}
