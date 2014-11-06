package lola.giovannini.lola.fragments.combat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import lola.giovannini.lola.Arme;
import lola.giovannini.lola.Armure;
import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;
import lola.giovannini.lola.fragments.general.PersoDetailsFragment;

public class CombatFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener{
    String CLASS_NAME = "CombatFragment";
    /*Fragment variables*/
    boolean arme_bouton_visible     = false;
    boolean armure_bouton_visible   = false;
    /*Personnage*/
    Personnage perso;
    /*Armures*/
    LinearLayout layout_armure, armure_nom, armure_ca, armure_dex, armure_pénalité, armure_sorts,
            armure_poids, armure_bouton;
    Button bouton_initiative, bouton_soin, bouton_degat;
    /*TextView*/
    TextView initiativeValue, pvValue, bba_melee, bba_distance, ca_valeur;
    TextView bouton_add_armure;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View combat = inflater.inflate(R.layout.frag_combat, container, false);
        combat.setOnClickListener(this);
        perso = ((MainActivity) getActivity()).getPerso();

        initViews(combat);
        getArmeFragment();
        getArmureFragment();
        getPVInfos();
        getBbaInfos();
        getCaInfos();

        setButtonsAction();

        Log.i("CombatFragment", "Ce fragment est créé.");
        return combat;
    }

    private void getArmeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ArmeFragment armeFragment = new ArmeFragment();
        fragmentTransaction.add(R.id.combatContainerArme, armeFragment, "Arme");
        fragmentTransaction.commit();
    }

    private void getArmureFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ArmureFragment armeFragment = new ArmureFragment();
        fragmentTransaction.add(R.id.combatContainerArmure, armeFragment, "Armure");
        fragmentTransaction.commit();
    }

    public void initViews(View combat){
        bouton_initiative   = (Button) combat.findViewById(R.id.buttonInitiative);
        bouton_soin         = (Button) combat.findViewById(R.id.healButton);
        bouton_degat        = (Button) combat.findViewById(R.id.buttonDegat);

        initiativeValue     = (TextView) combat.findViewById(R.id.initiativeValue);
        pvValue             = (TextView) combat.findViewById(R.id.PVTextView);
        bba_melee           = (TextView) combat.findViewById(R.id.bba_melee_value);
        bba_distance        = (TextView) combat.findViewById(R.id.bba_distance_value);
        ca_valeur           = (TextView) combat.findViewById(R.id.valeurCA);
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
            ca += perso.getBonusTaille();
            if(perso.getDivers().containsKey("ca")) {
                ca += perso.getDivers().get("ca");
            }
        }
        ca_valeur.setText(""+ca);
    }

    @Override
    public void onClick(View v) {
        if (v == this.bouton_initiative) {
            final Context context = getActivity();

            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.prompt_initiative, null);
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
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == layout_armure && !armure_bouton_visible){
            armure_bouton.setVisibility(View.VISIBLE);
            bouton_add_armure.setVisibility(View.VISIBLE);
            armure_bouton_visible = true;
        }else{
            System.out.println("rien");
        }
        return false;
    }
}