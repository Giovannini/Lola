package lola.giovannini.lola.activite_main.activite_overview;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lola.giovannini.lola.activite_main.Compétence;
import lola.giovannini.lola.activite_main.MainActivity;
import lola.giovannini.lola.activite_main.Personnage;
import lola.giovannini.lola.R;


/**
 * Created by giovannini on 10/17/14.
 */
public class CompetencesFragment extends Fragment {
    String CLASS_NAME = "CompetencesFragment";
    Personnage perso;
    LinearLayout layoutL, layoutR, layoutB;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View competences = inflater.inflate(R.layout.frag_competences, container, false);
        perso = ((MainActivity) getActivity()).getPerso();

        layoutL = (LinearLayout) competences.findViewById(R.id.compLayoutLeft);
        layoutR = (LinearLayout) competences.findViewById(R.id.compLayoutRight);
        layoutB = (LinearLayout) competences.findViewById(R.id.compLayoutButton);

        if(perso.getCompetencesPoints() <= 0){
            layoutB.setVisibility(View.GONE);
        }

        instanciateComp();
        //Log.i(CLASS_NAME, "Le fragment Compétences est créé.");
        return competences;
    }

    private void instanciateComp(){
        List<Compétence> compétences = perso.getCompétences();
        Collections.sort(compétences, new Comparator() {
            @Override
            public int compare(Object lhs, Object rhs) {
                return (lhs.toString()).compareTo(rhs.toString());
            }
        });
        Context context = getActivity().getApplicationContext();
        for (final Compétence c : compétences){
            final String comp_name = c.getNom();
            TextView tvl = new TextView(context);
            tvl.setTextSize(14.0f);
            tvl.setPadding(13,23,5,19);
            TextView tvr = new TextView(context);
            tvr.setTextSize(16.0f);
            tvr.setPadding(13,21,5,17);

            TextView b = new TextView(context);
            b.setText("+");
            b.setTextSize(16.0f);
            b.setPadding(13,21,5,17);
            b.setTextColor(getResources().getColor(R.color.themecolor));
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCompetencePoint(v, comp_name);
                }
            });

            SpannableString content = new SpannableString(c.getNom());
            tvr.setText(""+c.getTotal());
            if(c.getTotal() >= perso.getNiveau()){
                tvl.setTextColor(getResources().getColor(R.color.themecolor));
                tvr.setTextColor(getResources().getColor(R.color.themecolor));
            }else{
                tvl.setTextColor(Color.parseColor("#222222"));
                tvr.setTextColor(Color.parseColor("#222222"));
            }

            if(c.isCc()){
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            }
            tvl.setText(content);

            layoutL.addView(tvl);
            layoutR.addView(tvr);
            layoutB.addView(b);
        }
    }

    private void addCompetencePoint(View v, String nom){
        //Log.i("CompetenceFragment.addCompetencePoint()","Points = " + this.perso.getCompetencesPoints());
        if (this.perso.getCompetencesPoints() > 0) {
            int i = 0;
            List<Compétence> competences = perso.getCompétences();
            while (! competences.get(i).getNom().equals(nom)) {
                ++i;
            }
            Compétence c = competences.get(i);
            if (c.addPoint()){
                /*Log.d("CompetencesFragment.addCompetencePoint()", "Point de compétence ajouté à " +
                        "la compétence " + c.getNom() + ".");
                Log.d("CompetencesFragment.addCompetencePoint()", "Nouveau niveau: " + c.getRang
                        () + ".");*/
            }
            perso.getMain().saveJson(perso.getObj());
            layoutB.removeAllViews();
            layoutL.removeAllViews();
            layoutR.removeAllViews();
            instanciateComp();
        }
    }
}
