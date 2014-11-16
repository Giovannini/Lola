package lola.giovannini.lola.activite_overview.objets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Objet;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/7/14.
 */
public class ObjetFragment extends Fragment implements View.OnClickListener{
    String CLASS_NAME = "ObjetFragment";

    LinearLayout container, ajoutObjet;
    View objet;

    Personnage perso;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        objet = inflater.inflate(R.layout.frag_objets_liste, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();
        this.container = (LinearLayout) objet.findViewById(R.id.objectContainer);
        this.ajoutObjet = (LinearLayout) objet.findViewById(R.id.layoutObjetTitle);
        this.ajoutObjet.setOnClickListener(this);

        getObjets();

        //Log.i(CLASS_NAME, "Le fragment Objets est créé.");
        return objet;
    }

    private void getObjets(){
        List<Objet> objets = perso.getObjets();

        for (int i=0, fini=objets.size();i<fini;i++){
            Context context = getActivity().getApplicationContext();
            final Objet o = objets.get(i);

            String champNom = o.getNom();
            if (o.getQuantite() > 1){
                champNom += " x" + o.getQuantite();
            }

            LinearLayout l = new LinearLayout(context);
            l.setOrientation(LinearLayout.VERTICAL);

            TextView tvnom = new TextView(context);
            tvnom.setText(champNom);
            tvnom.setTextSize(14.0f);
            tvnom.setPadding(10, 21, 10, 17);
            tvnom.setTextColor(Color.parseColor("#222222"));

            if (i%2 == 1) {
                tvnom.setBackgroundColor(Color.parseColor("#dddddd"));
            }

            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Context context = getActivity();


                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.prompt_object_detail, null);
                    TextView nom_objet = (TextView) promptsView.findViewById(R.id
                            .prompt_object_detail_nom);
                    nom_objet.setText(o.getNom());
                    TextView poids_objet = (TextView) promptsView.findViewById(R.id
                            .prompt_object_detail_poids);
                    poids_objet.setText("Poids: " + o.getPoids());
                    TextView emplacement_objet = (TextView) promptsView.findViewById(R.id
                            .prompt_object_detail_emplacement);
                    emplacement_objet.setText("Emplacement: " + o.getEmplacement());
                    TextView description_objet = (TextView) promptsView.findViewById(R.id
                            .prompt_object_detail_description);
                    description_objet.setText(o.getNote());

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);
                    alertDialogBuilder.setView(promptsView);

                    alertDialogBuilder
                            .setCancelable(true)
                            .setPositiveButton("Ajouter",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Personnage perso = ((MainActivity) getActivity())
                                                    .getPerso();
                                            int i = 0;
                                            while (!perso.getObjets().get(i).getNom().equals(o
                                                    .getNom())) {
                                                i++;
                                            }
                                            perso.getObjets().get(i).add();
                                            perso.getMain().saveJson(perso.getObj());

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
                                            if (perso.getObjets().get(i).getQuantite() == 0){
                                                perso.getObjets().remove(i);
                                            }

                                            try {
                                                JSONArray objets = perso.getObj().getJSONArray
                                                        ("Equipement");
                                                objets.remove(i);
                                            }catch (JSONException e){
                                                Log.e("EquipementFragment.getObjets()",
                                                        "Erreur JSON lors de la suppression d'un " +
                                                                "objet.");
                                            }

                                            container.removeAllViews();
                                            getObjets();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            });

            l.addView(tvnom);
            container.addView(l);
        }

    }

    @Override
    public void onClick(View v) {
        final Context context = getActivity();
        LayoutInflater li;
        View promptsView;

        if (v == ajoutObjet){
            li = LayoutInflater.from(context);
            promptsView = li.inflate(R.layout.prompt_object_add, null);

            final EditText tvnom = (EditText) promptsView.findViewById(R.id.ajout_objet_nom);
            final EditText tve = (EditText) promptsView.findViewById(R.id.ajout_objet_emplacement);
            final EditText tvp= (EditText) promptsView.findViewById(R.id.ajout_objet_poids);
            final EditText tvnote = (EditText) promptsView.findViewById(R.id.ajout_objet_note);
            final EditText tvb = (EditText) promptsView.findViewById(R.id.ajout_objet_bonus);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            alertDialogBuilder.setView(promptsView);

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
        return true;
    }
}
