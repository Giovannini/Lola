package lola.giovannini.lola;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CombatFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener{
    String CLASS_NAME = "CombatFragment";
    /*Fragment variables*/
    boolean arme_bouton_visible = false;
    /*Personnage*/
    Personnage perso;
    /*Armes*/
    LinearLayout layout_arme, arme_nom, arme_bonus, armes_dommages, armes_critique, arme_portée,
            arme_bouton;
    /*Armures*/
    LinearLayout layout_armure, armure_nom, armure_ca, armure_dex, armure_pénalité, armure_sorts,
            armure_poids;
    /*Boutons*/
    Button bouton_initiative, bouton_soin, bouton_degat;
    /*TextView*/
    TextView initiativeValue, pvValue, bba_melee, bba_distance, ca_valeur;
    TextView bouton_add_arme;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View combat = inflater.inflate(R.layout.combat_frag, container, false);
        combat.setOnClickListener(this);
        perso = ((MainActivity) getActivity()).getPerso();

        initViews(combat);
        getArmesInfos();
        getArmuresInfos();
        getPVInfos();
        getBbaInfos();
        getCaInfos();

        setButtonsAction();

        Log.i("CombatFragment", "Ce fragment est créé.");
        return combat;
    }

    public void initViews(View combat){
        layout_arme         = (LinearLayout) combat.findViewById(R.id.layout_armes);
        layout_arme.setOnLongClickListener(this);
        arme_bouton         = (LinearLayout) combat.findViewById(R.id.layout_arme_buttons);
        arme_bouton.setVisibility(View.GONE);
        arme_nom            = (LinearLayout) combat.findViewById(R.id.layout_arme_nom);
        arme_bonus          = (LinearLayout) combat.findViewById(R.id.layout_arme_bonus);
        armes_dommages      = (LinearLayout) combat.findViewById(R.id.layout_arme_dommages);
        armes_critique      = (LinearLayout) combat.findViewById(R.id.layout_arme_critique);
        arme_portée         = (LinearLayout) combat.findViewById(R.id.layout_arme_portee);

        layout_armure         = (LinearLayout) combat.findViewById(R.id.layout_armures);
        armure_nom          = (LinearLayout) combat.findViewById(R.id.layout_armure_nom);
        armure_ca           = (LinearLayout) combat.findViewById(R.id.layout_armure_ca);
        armure_dex          = (LinearLayout) combat.findViewById(R.id.layout_armure_dex);
        armure_pénalité     = (LinearLayout) combat.findViewById(R.id.layout_armure_menalite);
        armure_sorts        = (LinearLayout) combat.findViewById(R.id.layout_armure_sorts);
        armure_poids        = (LinearLayout) combat.findViewById(R.id.layout_armure_poids);

        bouton_initiative   = (Button) combat.findViewById(R.id.buttonInitiative);
        bouton_soin         = (Button) combat.findViewById(R.id.healButton);
        bouton_degat        = (Button) combat.findViewById(R.id.buttonDegat);

        bouton_add_arme     = (TextView) combat.findViewById(R.id.ajoutArmeBouton);
        bouton_add_arme.setOnClickListener(this);

        initiativeValue     = (TextView) combat.findViewById(R.id.initiativeValue);
        pvValue             = (TextView) combat.findViewById(R.id.PVTextView);
        bba_melee           = (TextView) combat.findViewById(R.id.bba_melee_value);
        bba_distance        = (TextView) combat.findViewById(R.id.bba_distance_value);
        ca_valeur           = (TextView) combat.findViewById(R.id.valeurCA);
    }

    private void getArmuresInfos(){
        /*Armures*/
        List<Armure> armures = perso.getArmures();
        for(Armure a : armures){
            TextView tv1 = new TextView(getActivity().getApplicationContext());
            tv1.setText(a.getNom());
            armure_nom.addView(tv1);

            TextView tv2 = new TextView(getActivity().getApplicationContext());
            tv2.setText(a.getCa());
            armure_ca.addView(tv2);

            TextView tv3 = new TextView(getActivity().getApplicationContext());
            tv3.setText(a.getDex());
            armure_dex.addView(tv3);

            TextView tv4 = new TextView(getActivity().getApplicationContext());
            tv4.setText(a.getPénalité());
            armure_pénalité.addView(tv4);

            TextView tv5 = new TextView(getActivity().getApplicationContext());
            tv5.setText(a.getSorts());
            armure_sorts.addView(tv5);

            TextView tv6 = new TextView(getActivity().getApplicationContext());
            tv6.setText(a.getPoids());
            armure_poids.addView(tv6);
        }
    }

    private void getArmesInfos(){
        /* Armes */
        List<Arme> armes = perso.getArmes();
        for(Arme a : armes){
            ajoutArmeVue(a);
        }
    }

    private void removeWeapon(View v) {
        int i = 1;
        /* Find the index of the view */
        while (arme_bouton.getChildAt(i) != v){
            i++;
        }
        /* Remove weapon from display */
        perso.removeWeapon(i-1);

        arme_nom.removeViewAt(i);
        armes_dommages.removeViewAt(i);
        armes_critique.removeViewAt(i);
        arme_portée.removeViewAt(i);
        arme_bonus.removeViewAt(i);
        arme_bouton.removeViewAt(i);
    }

    private void addWeapon(String nom, String dommages, String critiques, String portee,
                           String bonus){
        Arme a = new Arme(nom, dommages, critiques, portee, bonus);
        perso.addWeapon(a);

        ajoutArmeVue(a);
    }

    private void getPVInfos(){
        pvValue.setText("" + perso.getCaractéristiques().getPv());
    }

    private void setButtonsAction(){
        bouton_soin.setOnClickListener(this);
        bouton_degat.setOnClickListener(this);
        bouton_initiative.setOnClickListener(this);
    }

    private void getBbaInfos(){
        int bba = perso.getBba();
        int bonusTaille = perso.getBonusTaille();
        int melee = bba + perso.getCaractéristiques().getModificateur("for") + bonusTaille;
        int distance = bba + perso.getCaractéristiques().getModificateur("dex") + bonusTaille;

        bba_melee.setText(""+melee);
        bba_distance.setText(""+distance);
    }

    private void getCaInfos(){
        int ca = 10;
        for (Armure a : perso.getArmures()){
            ca += Integer.parseInt(a.getCa());
            ca += perso.getCaractéristiques().getModificateur("dex");
            ca += perso.bonusTaille;
            if(perso.getDivers().containsKey("ca")) {
                ca += perso.getDivers().get("ca");
            }
        }
        ca_valeur.setText(""+ca);
    }

    @Override
    public void onClick(View v) {
        if (!arme_bouton_visible) {
            if (v == this.bouton_initiative) {
                final Context context = getActivity();

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.initiative_prompt, null);
                final NumberPicker picker = (NumberPicker) promptsView.findViewById(R.id.initiativePicker);
                picker.setMinValue(1);
                picker.setMaxValue(20);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Calculer",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //result.setText(userInput.getText());
                                        Personnage perso = ((MainActivity) getActivity())
                                                .getPerso();
                                        int initiative = picker.getValue() + perso
                                                .getCaractéristiques().getModificateur("dex");
                                        perso.setValeurInitiative(initiative);
                                        ((TextView) (getActivity().findViewById(R.id
                                                .initiativeValue))).setText("" + initiative);
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            } else if (v == this.bouton_degat) {
                if (perso.getCaractéristiques().getPv() > -10) {
                    perso.getCaractéristiques().removePv();
                    pvValue.setText("" + perso.getCaractéristiques().getPv());
                    try {
                        perso.getObj().put("Pv", perso.getCaractéristiques().getPv());
                        File persoDir = getActivity().getApplicationContext().getDir("Lola",
                                Context.MODE_PRIVATE);
                        File persoFile = new File(persoDir, "perso.json");
                        FileOutputStream fos = new FileOutputStream(persoFile);
                        fos.write(perso.getObj().toString().getBytes());
                        fos.close();
                    } catch (JSONException e) {
                        Log.e("CombatFragment", e.getMessage());
                    } catch (IOException e) {
                        Log.e("CombatFragment", e.getMessage());
                    }
                }
            } else if (v == bouton_soin) {
                if (perso.getCaractéristiques().getPv() < perso.getCaractéristiques().getPvmax()) {
                    perso.getCaractéristiques().addPv();
                    pvValue.setText("" + perso.getCaractéristiques().getPv());
                    try {
                        perso.getObj().put("Pv", perso.getCaractéristiques().getPv());
                        File persoDir = getActivity().getApplicationContext().getDir("Lola",
                                Context.MODE_PRIVATE);
                        File persoFile = new File(persoDir, "perso.json");
                        FileOutputStream fos = new FileOutputStream(persoFile);
                        fos.write(perso.getObj().toString().getBytes());
                        fos.close();
                    } catch (JSONException e) {
                        Log.e("CombatFragment", e.getMessage());
                    } catch (IOException e) {
                        Log.e("CombatFragment", e.getMessage());
                    }
                }
            }
        }else{
            Log.d("CombatFragment.onLongClick()", "hihi");
            if (v == bouton_add_arme){
                final Context context = getActivity();

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.ajout_arme_prompt, null);
                final EditText nom = (EditText) promptsView.findViewById(R.id.arme_prompt_nom);
                final EditText dommages = (EditText) promptsView.findViewById(R.id
                        .arme_prompt_dommages);
                final EditText critique = (EditText) promptsView.findViewById(R.id
                        .arme_prompt_critique);
                final EditText portee = (EditText) promptsView.findViewById(R.id
                        .arme_prompt_portee);
                final EditText bonus = (EditText) promptsView.findViewById(R.id.arme_prompt_bonus);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("Ajouter",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if ((!nom.getText().toString().equals(""))
                                                && (!dommages.getText().toString().equals(""))
                                                && (!portee.getText().toString().equals(""))
                                                && (!critique.getText().toString().equals(""))) {
                                            addWeapon(nom.getText().toString(),
                                                    dommages.getText().toString(),
                                                    portee.getText().toString(),
                                                    critique.getText().toString(),
                                                    bonus.getText().toString());
                                        }
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }else if (v != layout_arme){
                arme_bouton.setVisibility(View.GONE);
                arme_bouton_visible = false;
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == layout_arme){
            arme_bouton.setVisibility(View.VISIBLE);
            arme_bouton_visible = true;
        }
        return false;
    }

    private void ajoutArmeVue(Arme a){
        Context context = getActivity().getApplicationContext();

        TextView tv1 = new TextView(context);
        tv1.setText(a.getNom());
        arme_nom.addView(tv1);

        TextView tv2 = new TextView(context);
        tv2.setText(a.getBonus());
        arme_bonus.addView(tv2);

        TextView tv3 = new TextView(context);
        tv3.setText(a.getDommages());
        armes_dommages.addView(tv3);

        TextView tv4 = new TextView(context);
        tv4.setText(a.getCritiques());
        armes_critique.addView(tv4);

        TextView tv5 = new TextView(context);
        tv5.setText(a.getPortée());
        arme_portée.addView(tv5);

        TextView del = new TextView(context);
        del.setText("-");
        del.setGravity(Gravity.CENTER_HORIZONTAL);
        del.setLayoutParams((new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)));
        /**TODO frame the text maybe?*/
        del.setBackgroundColor(Color.parseColor("#8e282b"));
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeWeapon(v);
            }
        });
        arme_bouton.addView(del);
    }
}