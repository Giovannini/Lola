package lola.giovannini.lola.activite_main.activite_overview.general;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import lola.giovannini.lola.activite_main.Don;
import lola.giovannini.lola.activite_main.MainActivity;
import lola.giovannini.lola.activite_main.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/6/14.
 */
public class DonsFragment extends Fragment {
    String CLASS_NAME = "DonsFragment";
    Personnage perso;

    LinearLayout ll;

    Don[] DONS_POSSIBLES = {
        new Don("Souplesse du serpent",
                "Le personnage bénéficie d’un bonus d’esquive de +4 à la CA contre les attaques " +
                        "d’opportunité déclenchées par un déplacement dans un espace contrôlé (ou" +
                        " lorsqu’il en sort). Il perd automatiquement ce bonus s’il perd son " +
                        "bonus de Dextérité à la CA.",
                "Dex 13, Esquive"),
        new Don("Attaque en finesse",
                "Lorsqu’il utilise une arme légère, une chaine cloutée, un fouet, " +
                        "une lame elfique incurvée ou une rapière (adaptée à une créature de sa " +
                        "catégorie de taille), le personnage peut choisir d’appliquer son bonus " +
                        "de Dextérité à ses jets d’attaque plutôt que son bonus de Force.Si le " +
                        "personnage utilise un bouclier, le malus d’armure aux tests imposé par " +
                        "celui-ci s’applique aux jets d’attaque.",
                ""),
        new Don("Agilité dimensionnelle",
                "vantage. Le personnage peut effectuer toutes les actions qui lui restent après " +
                        "avoir lancé porte dimensionnelle ou fait un pas chassé. Il gagne aussi " +
                        "un bonus de +4 aux tests de Concentration quand il lance un sort de " +
                        "téléportation.",
                "Pas chassé ou porte dimensionnelle"),
        new Don("Doigt de fée",
                "Le personnage obtient un bonus de +2 sur tous ses tests d’Escamotage et de " +
                        "Sabotage. Si le personnage a 10 rangs ou plus dans l’une de ces " +
                        "compétences, le bonus ajouté à cette compétence augmente de +4. ",
                "")
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View dons = inflater.inflate(R.layout.frag_overview_dons, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();
        ll = (LinearLayout) dons.findViewById(R.id.layout_don);

        retrieveData();
        System.out.println(perso.getPointDon());

        //Log.i(CLASS_NAME, "Le fragment Dons est créé.");
        return dons;
    }

    private void retrieveData() {
         /*Caractéristiques*/
        List<Don> dons = perso.getDons();
        final Context context = getActivity();
        for(final Don don : dons){
            ll.addView(createDonTV(context, don));
        }
        addButtonAddDon();
    }

    private void addButtonAddDon(){
        if (perso.getPointDon() > 0){
            TextView tv = new TextView(getActivity());
            tv.setText("Ajout d'un don");
            tv.setTextColor(Color.parseColor("#466C79"));
            tv.setBackgroundResource(R.drawable.textlinebottom);
            tv.setRight(2);
            tv.setLeft(2);
            tv.setBottom(2);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Context context = getActivity();

                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.prompt_ajout_don, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setView(promptsView);
                    alertDialogBuilder
                            .setCancelable(true);
                    final AlertDialog alertDialog = alertDialogBuilder.create();

                    final LinearLayout layout = (LinearLayout) promptsView.findViewById(R.id
                            .layoutListDons);
                    for (final Don d : DONS_POSSIBLES){
                        TextView tv = new TextView(context);
                        tv.setText(d.getNom());
                        tv.setPadding(13, 5, 0, 5);
                        tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                perso.ajoutDon(d);
                                ll.removeAllViews();
                                perso.useDonPoint();

                                retrieveData();
                                alertDialog.dismiss();
                            }
                        });
                        layout.addView(tv);
                    }
                    alertDialog.show();
                }
            });
            ll.addView(tv);
        }
    }

    private TextView createDonTV(final Context context, final Don don){
        TextView tv = new TextView(context);
        tv.setText(don.getNom());
        tv.setTextColor(Color.parseColor("#222222"));
        tv.setTextSize(14.0f);
        tv.setPadding(13,23,5,19);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt_don, null);
                TextView nom_don = (TextView) promptsView.findViewById(R.id
                        .prompt_don_nom);
                nom_don.setText(don.getNom());
                TextView description_don = (TextView) promptsView.findViewById(R.id
                        .prompt_don_description);
                description_don.setText(don.getEffet());

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
        return tv;
    }

}
