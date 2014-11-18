package lola.giovannini.lola.activite_creation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/17/14.
 */
public class CreationActivity extends Activity {
    String CLASS_NAME = "CreationActivity";

    Button race, classe, go;
    EditText nom, age, poids, taille;
    Spinner alignement1, alignement2, sexe, religion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_personnage);

        getViews();
        initSpinners();
    }

    private void getViews(){
        race = (Button) findViewById(R.id.activity_creation_race_Button);
        classe = (Button) findViewById(R.id.activity_creation_classe_Button);
        go = (Button) findViewById(R.id.activity_creation_go_Button);

        nom = (EditText) findViewById(R.id.activity_creation_nom_EditText);
        age = (EditText) findViewById(R.id.activity_creation_age_EditText);
        poids = (EditText) findViewById(R.id.activity_creation_poids_EditText);
        taille = (EditText) findViewById(R.id.activity_creation_taille_EditText);

        alignement1 = (Spinner) findViewById(R.id.activity_creation_alignement1_Spinner);
        alignement2 = (Spinner) findViewById(R.id.activity_creation_alignement2_Spinner);
        sexe = (Spinner) findViewById(R.id.activity_creation_sexe_EditText);
        religion = (Spinner) findViewById(R.id.activity_creation_religion_Spinner);
    }

    private void initSpinners(){
        String[] alignement1Items   = {"Bon", "Neutre", "Mauvais"};
        String[] alignement2Items   = {"Loyal", "Neutre", "Chaotique"};
        String[] sexeItems          = {"Féminin", "Masculin"};
        String[] religionItems      = {"Abadar", "Asmodéus", "Calistria", "Cayden Cailéan",
                "Desna", "Érastil", "Gorum", "Gozreh", "Iomédae","Irori", "Lamashtu", "Néthys",
                "Norgorber", "Pharasma", "Rovagug", "Sarenrae", "Shélyn", "Torag", "Urgathoa",
                "Zon-Kuthon"};

        ArrayAdapter alignement1Adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, alignement1Items);
        ArrayAdapter alignement2Adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, alignement2Items);
        ArrayAdapter sexeAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, sexeItems);
        ArrayAdapter religionAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, religionItems);

        alignement1.setAdapter(alignement1Adapter);
        alignement2.setAdapter(alignement2Adapter);
        sexe.setAdapter(sexeAdapter);
        religion.setAdapter(religionAdapter);
    }
}
