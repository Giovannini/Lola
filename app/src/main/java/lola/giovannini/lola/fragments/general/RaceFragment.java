package lola.giovannini.lola.fragments.general;

import android.app.Activity;
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
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.ParticularitéRace;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/5/14.
 */
public class RaceFragment extends Fragment {
    Personnage perso;
    LinearLayout ll;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View race = inflater.inflate(R.layout.frag_race, container, false);

        perso = ((MainActivity) getActivity()).getPerso();
        ll = (LinearLayout) race.findViewById(R.id.layout_race);

        retrieveData();

        Log.i("OverviewFragment", "Le fragment Race est créé.");
        return race;
    }

    private void retrieveData(){
        final Context context = getActivity();
        List<ParticularitéRace> races = perso.getParticularitéRaces();
        for (final ParticularitéRace race : races){
            TextView tv = new TextView(context);
            tv.setText(race.getNom());
            tv.setTextColor(Color.parseColor("#222222"));
            tv.setTextSize(14.0f);
            tv.setPadding(13,23,5,19);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.prompt_don, null);
                    TextView nom_pr = (TextView) promptsView.findViewById(R.id
                            .prompt_don_nom);
                    nom_pr.setText(race.getNom());
                    TextView description_pr = (TextView) promptsView.findViewById(R.id
                            .prompt_don_description);
                    description_pr.setText(race.getDescription());

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

            ll.addView(tv);
        }
    }


}


