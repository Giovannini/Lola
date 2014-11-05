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
    boolean arme_bouton_visible     = false;
    boolean armure_bouton_visible   = false;
    /*Personnage*/
    Personnage perso;
    /*Armes*/
    LinearLayout layout_arme, arme_nom, arme_bonus, armes_dommages, armes_critique, arme_portée,
            arme_bouton, armure_bouton;
    /*Armures*/
    LinearLayout layout_armure, armure_nom, armure_ca, armure_dex, armure_pénalité, armure_sorts,
            armure_poids;
    Button bouton_initiative, bouton_soin, bouton_degat;
    /*TextView*/
    TextView initiativeValue, pvValue, bba_melee, bba_distance, ca_valeur;
    TextView bouton_add_arme, bouton_add_armure;

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
        layout_armure.setOnLongClickListener(this);
        armure_bouton         = (LinearLayout) combat.findViewById(R.id.layout_armure_boutons);
        armure_bouton.setVisibility(View.GONE);
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
        bouton_add_arme.setVisibility(View.GONE);

        bouton_add_armure     = (TextView) combat.findViewById(R.id.ajoutArmureBouton);
        bouton_add_armure.setOnClickListener(this);
        bouton_add_armure.setVisibility(View.GONE);

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
            ajoutArmureVue(a);
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

    private void removeArmor(View v) {
        int i = 1;
        /* Find the index of the view */
        while (armure_bouton.getChildAt(i) != v){
            i++;
        }
        /* Remove weapon from display */
        perso.removeArmor(i - 1);

        armure_nom.removeViewAt(i);
        armure_poids.removeViewAt(i);
        armure_ca.removeViewAt(i);
        armure_dex.removeViewAt(i);
        armure_pénalité.removeViewAt(i);
        armure_sorts.removeViewAt(i);
    }

    private void addWeapon(String nom, String dommages, String critiques, String portee,
                           String bonus){
        Arme a = new Arme(nom, dommages, critiques, portee, bonus);
        perso.addWeapon(a);

        ajoutArmeVue(a);
    }

    private void addArmor(String nom, String poids, String ca, String dex,
                           String pénalité, String sorts, String déplacement){
        Armure a = new Armure(nom, poids, ca, dex,
                pénalité, sorts, déplacement);
        perso.addArmor(a);

        ajoutArmureVue(a);
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
        if (!arme_bouton_visible && !armure_bouton_visible) {
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
            if (v == bouton_add_arme && arme_bouton_visible){
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
            }else if (v == bouton_add_armure && armure_bouton_visible){
                final Context context = getActivity();

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.ajout_armure_prompt, null);
                final EditText nom = (EditText) promptsView.findViewById(R.id.armure_prompt_nom);
                final EditText ca = (EditText) promptsView.findViewById(R.id
                        .armure_prompt_ca);
                final EditText deplacements = (EditText) promptsView.findViewById(R.id
                        .armure_prompt_deplacements);
                final EditText dex = (EditText) promptsView.findViewById(R.id
                        .armure_prompt_dex);
                final EditText penalite = (EditText) promptsView.findViewById(R.id
                        .armure_prompt_penalite);
                final EditText poids = (EditText) promptsView.findViewById(R.id
                        .armure_prompt_poids);
                final EditText sorts = (EditText) promptsView.findViewById(R.id
                        .armure_prompt_sorts);

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
                                                && (!ca.getText().toString().equals(""))
                                                && (!poids.getText().toString().equals(""))) {
                                            addArmor(nom.getText().toString(),
                                                    poids.getText().toString(),
                                                    ca.getText().toString(),
                                                    dex.getText().toString(),
                                                    penalite.getText().toString(),
                                                    sorts.getText().toString(),
                                                    deplacements.getText().toString());
                                        }
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }else if (v != layout_arme && arme_bouton_visible){
                arme_bouton.setVisibility(View.GONE);
                bouton_add_arme.setVisibility(View.GONE);
                arme_bouton_visible = false;
            }else if (v != layout_armure && armure_bouton_visible){
                armure_bouton.setVisibility(View.GONE);
                bouton_add_armure.setVisibility(View.GONE);
                armure_bouton_visible = false;
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        System.out.println(v);
        System.out.println(layout_arme);
        System.out.println(layout_armure);
        System.out.println(arme_bouton_visible);
        System.out.println(armure_bouton_visible);
        if (v == layout_arme && !arme_bouton_visible){
            arme_bouton.setVisibility(View.VISIBLE);
            bouton_add_arme.setVisibility(View.VISIBLE);
            arme_bouton_visible = true;
        }else if (v == layout_armure && !armure_bouton_visible){
            armure_bouton.setVisibility(View.VISIBLE);
            bouton_add_armure.setVisibility(View.VISIBLE);
            armure_bouton_visible = true;
        }else{
            System.out.println("rien");
        }
        return false;
    }

    private void ajoutArmeVue(Arme a){
        Context context = getActivity().getApplicationContext();

        TextView tv1 = new TextView(context);
        tv1.setText(a.getNom());
        tv1.setTextSize(16.0f);
        tv1.setPadding(10, 21, 10, 17);
        arme_nom.addView(tv1);

        TextView tv2 = new TextView(context);
        tv2.setText(a.getBonus());
        tv2.setTextSize(16.0f);
        tv2.setPadding(10, 21, 10, 17);
        arme_bonus.addView(tv2);

        TextView tv3 = new TextView(context);
        tv3.setText(a.getDommages());
        tv3.setTextSize(16.0f);
        tv3.setPadding(10, 21, 10, 17);
        armes_dommages.addView(tv3);

        TextView tv4 = new TextView(context);
        tv4.setText(a.getCritiques());
        tv4.setTextSize(16.0f);
        tv4.setPadding(10, 21, 10, 17);
        armes_critique.addView(tv4);

        TextView tv5 = new TextView(context);
        tv5.setText(a.getPortée());
        tv5.setTextSize(16.0f);
        tv5.setPadding(10, 21, 10, 17);
        arme_portée.addView(tv5);

        TextView del = new TextView(context);
        del.setText("-");
        del.setGravity(Gravity.CENTER_HORIZONTAL);
        del.setLayoutParams((new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)));
        /**TODO frame the text maybe?*/
        del.setBackgroundColor(Color.parseColor("#8e282b"));
        del.setTextSize(22.0f);
        tv5.setPadding(10, 21, 10, 17);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeWeapon(v);
            }
        });
        arme_bouton.addView(del);
    }

    private void ajoutArmureVue(Armure a){
        Context context = getActivity().getApplicationContext();

        TextView tv1 = new TextView(context);
        tv1.setText(a.getNom());
        tv1.setTextSize(16.0f);
        tv1.setPadding(10, 21, 10, 17);
        armure_nom.addView(tv1);

        TextView tv2 = new TextView(context);
        tv2.setText(a.getCa());
        tv2.setTextSize(16.0f);
        tv2.setPadding(10, 21, 10, 17);
        armure_ca.addView(tv2);

        TextView tv3 = new TextView(context);
        tv3.setText(a.getDex());
        tv3.setTextSize(16.0f);
        tv3.setPadding(10, 21, 10, 17);
        armure_dex.addView(tv3);

        TextView tv4 = new TextView(context);
        tv4.setText(a.getPénalité());
        tv4.setTextSize(16.0f);
        tv4.setPadding(10, 21, 10, 17);
        armure_pénalité.addView(tv4);

        TextView tv5 = new TextView(context);
        tv5.setText(a.getSorts());
        tv5.setTextSize(16.0f);
        tv5.setPadding(10, 21, 10, 17);
        armure_sorts.addView(tv5);

        TextView tv6 = new TextView(context);
        tv6.setText(a.getPoids());
        tv6.setTextSize(16.0f);
        tv6.setPadding(10, 21, 10, 17);
        armure_poids.addView(tv6);

        TextView del = new TextView(context);
        del.setText("-");
        del.setGravity(Gravity.CENTER_HORIZONTAL);
        del.setLayoutParams((new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)));
        /**TODO frame the text maybe?*/
        del.setBackgroundColor(Color.parseColor("#8e282b"));
        del.setTextSize(22.0f);
        tv5.setPadding(10, 21, 10, 17);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeArmor(v);
            }
        });
        armure_bouton.addView(del);
    }
}