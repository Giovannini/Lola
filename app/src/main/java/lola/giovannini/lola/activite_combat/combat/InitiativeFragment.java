package lola.giovannini.lola.activite_combat.combat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/7/14.
 */
public class InitiativeFragment extends Fragment implements View.OnClickListener{
    String CLASS_NAME = "InitiativeFragment";

    /*Personnage*/
    Personnage perso;
    /*Button*/
    ImageView bouton_initiative;
    /*TextView*/
    TextView initiativeValue;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View initiative = inflater.inflate(R.layout.frag_combat1_initiative, container, false);
        perso = ((MainActivity) getActivity()).getPerso();

        initViews(initiative);

        Log.i(CLASS_NAME, "Le fragment Initiative est créé.");
        return initiative;
    }

    public void initViews(View combat) {
        bouton_initiative   = (ImageView) combat.findViewById(R.id.buttonInitiative);
        bouton_initiative.setOnClickListener(this);

        initiativeValue     = (TextView) combat.findViewById(R.id.initiativeValue);
    }

    @Override
    public void onClick(View v){
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
        }
    }
}
