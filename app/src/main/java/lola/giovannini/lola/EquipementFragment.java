package lola.giovannini.lola;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

/**
 * Created by giovannini on 10/17/14.
 */
public class EquipementFragment extends Fragment implements View.OnClickListener{
    LinearLayout container, ajoutObjet;
    Button gestionRichesse;
    TextView tvplatine, tvor, tvargent, tvbronze;
    View equipement;

    Personnage perso;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        equipement = inflater.inflate(R.layout.objects_frag, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();
        this.container = (LinearLayout) equipement.findViewById(R.id.objectContainer);
        this.ajoutObjet = (LinearLayout) equipement.findViewById(R.id.layoutObjetTitle);
        this.ajoutObjet.setOnClickListener(this);

        this.gestionRichesse = (Button) equipement.findViewById(R.id.buttonGestionRichesse);

        this.tvplatine = (TextView) equipement.findViewById(R.id.valeurPlatine);
        this.tvor = (TextView) equipement.findViewById(R.id.valeurOr);
        this.tvargent = (TextView) equipement.findViewById(R.id.valeurArgent);
        this.tvbronze = (TextView) equipement.findViewById(R.id.valeurBronze);

        gestionRichesse.setOnClickListener(this);

        getObjets();
        getRichesses();

        Log.i("EquipementFragment", "Ce fragment est créé.");
        return equipement;
    }

    private void getObjets(){
        Personnage perso = ((MainActivity) getActivity()).getPerso();
        List<Objet> objets = perso.getObjets();

        for (int i=0, fini=objets.size();i<fini;i++){
            Context context = getActivity().getApplicationContext();
            final Objet o = objets.get(i);

            String champNom = o.getNom();
            if (o.getQuantite() > 1){
                champNom += " x" + o.getQuantite();
            }

            LinearLayout bigl = new LinearLayout(context);
            bigl.setOrientation(LinearLayout.VERTICAL);

            LinearLayout l = new LinearLayout(context);
            l.setOrientation(LinearLayout.HORIZONTAL);
            Resources r = getResources();

            TextView tvnom = new TextView(context);
            tvnom.setText(champNom);
            tvnom.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                    r.getDisplayMetrics()));
            TextView tvequip = new TextView(context);
            tvequip.setText(o.getEmplacement());
            tvequip.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72,
                    r.getDisplayMetrics()));
            TextView tvpoids = new TextView(context);
            tvpoids.setText(o.getPoids() + "kg");
            tvpoids.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48,
                    r.getDisplayMetrics()));
            TextView note = null;
            if (! o.getNote().equals("")) {
                note = new TextView(context);
                note.setText(o.getNote());
                if (i%2 == 1)
                    note.setBackgroundColor(Color.parseColor("#976d55"));
                    note.setVisibility(View.GONE);
            }

            if (i%2 == 1) {
                tvnom.setBackgroundColor(Color.parseColor("#976d55"));
                tvequip.setBackgroundColor(Color.parseColor("#976d55"));
                tvpoids.setBackgroundColor(Color.parseColor("#976d55"));
            }

            bigl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((ViewGroup)v).getChildCount() > 1) {
                        TextView note = (TextView) ((ViewGroup) v).getChildAt(1);
                        if (note.getVisibility() == View.VISIBLE) {
                            note.setVisibility(View.GONE);
                        } else {
                            note.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });

            bigl.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View arg0) {
                    final Context context = getActivity();

                    // get prompts.xml view
                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.object_prompt, null);

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
                                            // get user input and set it to result
                                            // edit text
                                            //result.setText(userInput.getText());
                                            Personnage perso = ((MainActivity) getActivity())
                                                    .getPerso();
                                            int i = 0;
                                            while (!perso.getObjets().get(i).getNom().equals(o
                                                    .getNom())) {
                                                i++;
                                            }
                                            perso.getObjets().get(i).add();

                                            container.removeAllViews();
                                            getObjets();
                                        }
                                    })
                            .setNegativeButton("Retirer",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Personnage perso = ((MainActivity) getActivity())
                                                    .getPerso();
                                            int i = 0;
                                            while (!perso.getObjets().get(i).getNom().equals(o
                                                    .getNom())) {
                                                i++;
                                            }
                                            perso.getObjets().get(i).del();

                                            container.removeAllViews();
                                            getObjets();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                    return true;
                }
            });

            l.addView(tvnom);
            l.addView(tvequip);
            l.addView(tvpoids);

            bigl.addView(l);
            if (! o.getNote().equals(""))
                bigl.addView(note);
            container.addView(bigl);
        }

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
            promptsView = li.inflate(R.layout.richesse_prompt, null);

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
        }else if (v == ajoutObjet){
            li = LayoutInflater.from(context);
            promptsView = li.inflate(R.layout.object_add_prompt, null);

            final EditText tvnom = (EditText) promptsView.findViewById(R.id.ajout_objet_nom);
            final EditText tve = (EditText) promptsView.findViewById(R.id.ajout_objet_emplacement);
            final EditText tvp= (EditText) promptsView.findViewById(R.id.ajout_objet_poids);
            final EditText tvnote = (EditText) promptsView.findViewById(R.id.ajout_objet_note);
            final EditText tvb = (EditText) promptsView.findViewById(R.id.ajout_objet_bonus);

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

                                    String nom, emplacement, poids, note, bonus;
                                    int qt;

                                    nom = tvnom.getText().toString();
                                    emplacement = tve.getText().toString();

                                    if (!(nom.equals("")
                                            || emplacement.equals("")
                                            || !(isDouble(tvp.getText().toString())))) {
                                        poids = tvp.getText().toString();
                                        note = tvnote.getText().toString();
                                        bonus = tvb.getText().toString();
                                        qt = 1;
                                        Objet o = new Objet(nom, emplacement, poids, note, qt, bonus);

                                        perso.addObjet(o);
                                        container.removeAllViews();
                                        getObjets();
                                        //Popup the object is created
                                    }else{
                                        //popup the object isn't
                                    }
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
