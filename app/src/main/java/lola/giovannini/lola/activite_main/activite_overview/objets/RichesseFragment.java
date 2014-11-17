package lola.giovannini.lola.activite_main.activite_overview.objets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import lola.giovannini.lola.activite_main.MainActivity;
import lola.giovannini.lola.activite_main.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/7/14.
 */
public class RichesseFragment extends Fragment implements View.OnClickListener {
    String CLASS_NAME = "RichesseFragment";

    ImageView gestionRichesse;
    TextView tvplatine, tvor, tvargent, tvbronze;
    View richesse;

    Personnage perso;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        richesse = inflater.inflate(R.layout.frag_objets_richesse, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();

        getViews(richesse);
        gestionRichesse.setOnClickListener(this);
        getRichesses();

        //Log.i(CLASS_NAME, "Le fragment Richesse est créé.");
        return richesse;
    }

    private void getViews(View v){
        gestionRichesse = (ImageView) v.findViewById(R.id.buttonGestionRichesse);
        tvplatine = (TextView) v.findViewById(R.id.valeurPlatine);
        tvor = (TextView) v.findViewById(R.id.valeurOr);
        tvargent = (TextView) v.findViewById(R.id.valeurArgent);
        tvbronze = (TextView) v.findViewById(R.id.valeurOr);
    }

    private void getRichesses(){
        this.tvplatine.setText(""+perso.getRichesse(0));
        this.tvor.setText(""+perso.getRichesse(1));
        this.tvargent.setText(""+perso.getRichesse(2));
        this.tvbronze.setText(""+perso.getRichesse(3));
    }

    @Override
    public void onClick(View v) {
        final Context context = getActivity();
        LayoutInflater li;
        View promptsView;

        if (v == this.gestionRichesse){

            li = LayoutInflater.from(context);
            promptsView = li.inflate(R.layout.prompt_richesse, null);

            final EditText tvp = (EditText) promptsView.findViewById(R.id.editPlatine);
            //tvp.setText("0");
            final EditText tvo = (EditText) promptsView.findViewById(R.id.editOr);
            //tvo.setText("0");
            final EditText tva= (EditText) promptsView.findViewById(R.id.editArgent);
            //tva.setText("0");
            final EditText tvb = (EditText) promptsView.findViewById(R.id.editBronze);
            //tvb.setText("0");

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(true)
                    .setPositiveButton("Ajouter",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Personnage perso = ((MainActivity) getActivity())
                                            .getPerso();
                                    int platine = 0;
                                    int or = 0;
                                    int argent = 0;
                                    int bronze = 0;
                                    if (! tvp.getText().toString().equals(""))
                                        platine = Integer.parseInt(tvp.getText().toString());
                                    if (! tvo.getText().toString().equals(""))
                                        or = Integer.parseInt(tvo.getText().toString());
                                    if (! tva.getText().toString().equals(""))
                                        argent = Integer.parseInt(tva.getText().toString());
                                    if (! tvb.getText().toString().equals(""))
                                        bronze = Integer.parseInt(tvb.getText().toString());

                                    perso.addRichesses(platine, or, argent, bronze);

                                    ((TextView) getActivity().findViewById(R.id.valeurPlatine))
                                            .setText(""+perso.getRichesse(0));
                                    ((TextView) getActivity().findViewById(R.id.valeurOr))
                                            .setText(""+perso.getRichesse(1));
                                    ((TextView) getActivity().findViewById(R.id.valeurArgent))
                                            .setText(""+perso.getRichesse(2));
                                    ((TextView) getActivity().findViewById(R.id.valeurBronze))
                                            .setText("" + perso.getRichesse(3));
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
    }
}
