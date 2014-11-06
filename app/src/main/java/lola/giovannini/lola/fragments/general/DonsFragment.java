package lola.giovannini.lola.fragments.general;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import lola.giovannini.lola.Don;
import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/6/14.
 */
public class DonsFragment extends Fragment {
    Personnage perso;

    LinearLayout ll;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View dons = inflater.inflate(R.layout.frag_dons, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();
        ll = (LinearLayout) dons.findViewById(R.id.layout_don);

        retrieveData();

        Log.i("DonsFragment", "Le fragment Dons est créé.");
        return dons;
    }

    private void retrieveData() {
         /*Caractéristiques*/
        List<Don> dons = perso.getDons();
        final Context context = getActivity();
        for(final Don don : dons){
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

            ll.addView(tv);
        }
    }


}
