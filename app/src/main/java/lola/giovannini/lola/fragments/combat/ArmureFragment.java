package lola.giovannini.lola.fragments.combat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import lola.giovannini.lola.Arme;
import lola.giovannini.lola.Armure;
import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/6/14.
 */
public class ArmureFragment extends Fragment implements View.OnClickListener{
    String CLASS_NAME = "CombatFragment";
    /*Personnage*/
    Personnage perso;
    /*Armures*/
    LinearLayout layout_armure, armure_nom;
    /*TextView*/
    TextView bouton_add_armure;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View armure = inflater.inflate(R.layout.frag_combat_armure, container, false);
        perso = ((MainActivity) getActivity()).getPerso();

        initViews(armure);
        getArmuresInfos();

        Log.i("CombatFragment", "Le fragment Armure est créé.");
        return armure;
    }

    public void initViews(View armure) {
        layout_armure = (LinearLayout) armure.findViewById(R.id.layout_armure);
        armure_nom = (LinearLayout) armure.findViewById(R.id.layout_armure_nom);
        bouton_add_armure = (TextView) armure.findViewById(R.id.ajoutArmureBouton);
        bouton_add_armure.setOnClickListener(this);
    }

    private void getArmuresInfos() {
        List<Armure> armures = perso.getArmures();
        for (Armure a : armures) {
            ajoutArmureVue(a);
        }
    }

    private void removeArmor(Armure a) {
        Personnage perso = ((MainActivity) getActivity())
                .getPerso();
        int i = perso.getArmures().indexOf(a);
        perso.getArmures().remove(i);

        try {
            JSONArray armures = perso.getObj().getJSONArray
                    ("Armures");
            armures.remove(i);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".ajoutArmeVue()",
                    "Erreur JSON lors de la suppression d'une " +
                            "arme.");
        }
        armure_nom.removeAllViews();
        getArmuresInfos();
    }


    private void ajoutArmure(Armure a) {
        perso.addArmor(a);

        ajoutArmureVue(a);
    }

    private void ajoutArmureVue(final Armure a) {
        Context context = getActivity().getApplicationContext();

        TextView tv1 = new TextView(context);
        tv1.setText(a.getNom());
        tv1.setTextSize(16.0f);
        tv1.setPadding(10, 21, 10, 17);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = getActivity();

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt_armure_detail, null);
                TextView nom_armure = (TextView) promptsView.findViewById(R.id
                        .armure_detail_prompt_nom);
                nom_armure.setText("Nom: " + a.getNom());
                TextView bonus_ca_armure = (TextView) promptsView.findViewById(R.id
                        .armure_detail_prompt_bonus_ca);
                bonus_ca_armure.setText("Bonus CA: " + a.getCa());
                TextView dex_max_armure = (TextView) promptsView.findViewById(R.id
                        .armure_detail_prompt_dex_max);
                dex_max_armure.setText("Dextérité max: " + a.getDex());
                TextView penalite_armure = (TextView) promptsView.findViewById(R.id
                        .armure_detail_prompt_penalite);
                penalite_armure.setText("Pénalité: " + a.getPénalité());
                TextView sorts_armure = (TextView) promptsView.findViewById(R.id
                        .armure_detail_prompt_sorts);
                sorts_armure.setText("% sorts: " + a.getSorts());
                TextView deplacement_armure = (TextView) promptsView.findViewById(R.id
                        .armure_detail_prompt_deplacement);
                deplacement_armure.setText("Déplacements: " + a.getDéplacement());
                TextView poids_armure = (TextView) promptsView.findViewById(R.id
                        .armure_detail_prompt_poids);
                poids_armure.setText("Poids: " + a.getPoids());

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("Éditer",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        openPromptEdit(a);
                                    }
                                })
                        .setNegativeButton("Retirer",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        removeArmor(a);
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        armure_nom.addView(tv1);
    }

    @Override
    public void onClick(View v) {
        if (v == bouton_add_armure){
            openPromptAddArmor();
        }
    }

    public void openPromptAddArmor(){
        final Context context = getActivity();

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_ajout_armure, null);
        final EditText nom_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_nom);
        final EditText classearmure_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_ca);
        final EditText dexterite_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_dex);
        final EditText penalite_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_penalite);
        final EditText sorts_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_sorts);
        final EditText deplacements_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_deplacements);
        final EditText poids_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_poids);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Ajouter",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (! nom_armure.getText().equals("")){
                                    Armure a = new Armure(
                                            nom_armure.getText().toString(),
                                            classearmure_armure.getText().toString(),
                                            dexterite_armure.getText().toString(),
                                            penalite_armure.getText().toString(),
                                            sorts_armure.getText().toString(),
                                            deplacements_armure.getText().toString(),
                                            poids_armure.getText().toString());
                                    ajoutArmure(a);
                                }else{
                                    Toast.makeText(context, "Le nom de l'armure n'a pas été " +
                                                    "rempli. Ajout annulé",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void openPromptEdit(final Armure a)  {
        final Context context = getActivity();

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_ajout_armure, null);
        final EditText nom_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_nom);
        nom_armure.setText(a.getNom());
        final EditText classearmure_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_ca);
        classearmure_armure.setText(a.getCa());
        final EditText dexterite_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_dex);
        dexterite_armure.setText(a.getDex());
        final EditText penalite_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_penalite);
        penalite_armure.setText(a.getPénalité());
        final EditText sorts_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_sorts);
        sorts_armure.setText(a.getSorts());
        final EditText deplacements_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_deplacements);
        deplacements_armure.setText(a.getDéplacement());
        final EditText poids_armure = (EditText) promptsView.findViewById(R.id
                .armure_prompt_poids);
        poids_armure.setText(a.getPoids());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Fin de l'édition",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (! nom_armure.getText().equals("")){
                                    a.setNom(nom_armure.getText().toString());
                                    a.setCa(classearmure_armure.getText().toString());
                                    a.setDex(dexterite_armure.getText().toString());
                                    a.setPénalité(penalite_armure.getText().toString());
                                    a.setSorts(sorts_armure.getText().toString());
                                    a.setDéplacement(deplacements_armure.getText().toString());
                                    a.setPoids(poids_armure.getText().toString());

                                    perso.getMain().saveJson(perso.getObj());

                                    armure_nom.removeAllViews();
                                    getArmuresInfos();
                                }else{
                                    Toast.makeText(context, "Le nom de l'armure n'a pas été " +
                                                    "rempli. Édition annulée",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
