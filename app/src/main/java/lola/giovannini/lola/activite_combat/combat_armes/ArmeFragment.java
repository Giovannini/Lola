package lola.giovannini.lola.activite_combat.combat_armes;

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
import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/6/14.
 */
public class ArmeFragment extends Fragment implements View.OnClickListener{
    String CLASS_NAME = "ArmeFragment";

    /*Personnage*/
    Personnage perso;
    /*Armes*/
    LinearLayout layout_arme, arme_nom, arme_bouton;
    /*TextView*/
    TextView bouton_add_arme;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View arme = inflater.inflate(R.layout.frag_combat2_arme, container, false);
        perso = ((MainActivity) getActivity()).getPerso();

        initViews(arme);
        getArmesInfos();

        //Log.i(CLASS_NAME, "Le fragment Arme est créé.");
        return arme;
    }

    public void initViews(View combat) {
        layout_arme = (LinearLayout) combat.findViewById(R.id.layout_arme);
        arme_nom = (LinearLayout) combat.findViewById(R.id.layout_arme_nom);

        bouton_add_arme = (TextView) combat.findViewById(R.id.ajoutArmeBouton);
        bouton_add_arme.setOnClickListener(this);
    }

    private void getArmesInfos(){
        /* Armes */
        List<Arme> armes = perso.getArmes();
        for(Arme a : armes){
            ajoutArmeVue(a);
        }
    }

    private void ajoutArme(Arme a){
        perso.addWeapon(a);

        ajoutArmeVue(a);
    }

    private void retireArme(final Arme a) {
        Personnage perso = ((MainActivity) getActivity())
                .getPerso();
        int i = perso.getArmes().indexOf(a);
        perso.getArmes().remove(i);

        try {
            JSONArray armes = perso.getObj().getJSONArray
                    ("Armes");
            armes.remove(i);
        }catch (JSONException e){
            Log.e(CLASS_NAME + ".ajoutArmeVue()",
                    "Erreur JSON lors de la suppression d'une " +
                            "arme.");
        }
        arme_nom.removeAllViews();
        getArmesInfos();
    }

    private void ajoutArmeVue(final Arme a){
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
                View promptsView = li.inflate(R.layout.prompt_arme_detail, null);
                TextView nom_arme = (TextView) promptsView.findViewById(R.id
                        .arme_detail_prompt_nom);
                nom_arme.setText("Nom: " + a.getNom());
                TextView attaque_arme = (TextView) promptsView.findViewById(R.id
                        .arme_detail_prompt_attaque);
                attaque_arme.setText("Attaque: " + a.getBonus());
                TextView dommages_arme = (TextView) promptsView.findViewById(R.id
                        .arme_detail_prompt_dommages);
                dommages_arme.setText("Dommages: " + a.getDommages());
                TextView critique_arme = (TextView) promptsView.findViewById(R.id
                        .arme_detail_prompt_critique);
                critique_arme.setText("Critique: " + a.getCritiques());
                TextView portee_arme = (TextView) promptsView.findViewById(R.id
                        .arme_detail_prompt_portee);
                portee_arme.setText("Portée: " + a.getPortée());
                TextView taille_arme = (TextView) promptsView.findViewById(R.id
                        .arme_detail_prompt_taille);
                taille_arme.setText("Taille: " + "");
                TextView type_arme = (TextView) promptsView.findViewById(R.id
                        .arme_detail_prompt_type);
                type_arme.setText("Type: " + "");
                TextView poids_arme = (TextView) promptsView.findViewById(R.id
                        .arme_detail_prompt_poids);
                poids_arme.setText("Poids: " + "");

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
                                        retireArme(a);
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        arme_nom.addView(tv1);
    }

    @Override
    public void onClick(View v) {
        if (v == bouton_add_arme){
            openPromptAddArme();
        }
    }

    public void openPromptAddArme(){
        final Context context = getActivity();

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_ajout_arme, null);
        final EditText nom_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_nom);
        final EditText attaque_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_bonus);
        final EditText dommages_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_dommages);
        final EditText critique_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_critique);
        final EditText portee_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_portee);
        final EditText taille_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_taille);
        final EditText type_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_type);
        final EditText poids_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_poids);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Ajouter",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (! nom_arme.getText().equals("")){
                                    Arme a = new Arme(
                                            nom_arme.getText().toString(),
                                            attaque_arme.getText().toString(),
                                            dommages_arme.getText().toString(),
                                            critique_arme.getText().toString(),
                                            portee_arme.getText().toString(),
                                            taille_arme.getText().toString(),
                                            type_arme.getText().toString(),
                                            poids_arme.getText().toString());
                                    ajoutArme(a);
                                }else{
                                    Toast.makeText(context, "Le nom de l'arme n'a pas été " +
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

    public void openPromptEdit(final Arme a)  {
        final Context context = getActivity();

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_ajout_arme, null);
        final EditText nom_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_nom);
        nom_arme.setText(a.getNom());
        final EditText attaque_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_bonus);
        attaque_arme.setText(a.getBonus());
        final EditText dommages_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_dommages);
        dommages_arme.setText(a.getDommages());
        final EditText critique_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_critique);
        critique_arme.setText(a.getCritiques());
        final EditText portee_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_portee);
        portee_arme.setText(a.getPortée());
        final EditText taille_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_taille);
        taille_arme.setText(a.getTaille());
        final EditText type_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_type);
        type_arme.setText(a.getType());
        final EditText poids_arme = (EditText) promptsView.findViewById(R.id
                .arme_prompt_poids);
        poids_arme.setText(a.getPoids());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Fin de l'édition",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (! nom_arme.getText().equals("")){
                                    a.setNom(nom_arme.getText().toString());
                                    a.setBonus(attaque_arme.getText().toString());
                                    a.setDommages(dommages_arme.getText().toString());
                                    a.setCritiques(critique_arme.getText().toString());
                                    a.setPortée(portee_arme.getText().toString());
                                    a.setTaille(taille_arme.getText().toString());
                                    a.setType(type_arme.getText().toString());
                                    a.setPoids(poids_arme.getText().toString());

                                    perso.getMain().saveJson(perso.getObj());

                                    arme_nom.removeAllViews();
                                    getArmesInfos();
                                }else{
                                    Toast.makeText(context, "Le nom de l'arme n'a pas été " +
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
