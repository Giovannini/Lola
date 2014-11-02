package lola.giovannini.lola;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by giovannini on 10/17/14.
 */
public class CompetencesFragment extends Fragment {
    Personnage perso;
    LinearLayout layoutL, layoutR, layoutB;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View competences = inflater.inflate(R.layout.competences_frag, container, false);
        perso = ((MainActivity) getActivity()).getPerso();

        layoutL = (LinearLayout) competences.findViewById(R.id.compLayoutLeft);
        layoutR = (LinearLayout) competences.findViewById(R.id.compLayoutRight);
        layoutB = (LinearLayout) competences.findViewById(R.id.compLayoutButton);

        instanciateComp();
        Log.i("CompétencesFragment", "Ce fragment est créé.");
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
            TextView tvr = new TextView(context);
            Button b = new Button(context);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCompetencePoint(v, comp_name);
                }
            });
            if (perso.getCompetencesPoints() > 0){
                b.setBackgroundColor(Color.parseColor("#2f8b31"));
            }else{
                b.setBackgroundColor(Color.parseColor("#2f2f2f"));
            }
            SpannableString content = new SpannableString(c.getNom());
            tvr.setText(""+c.getTotal());
            if(c.getTotal() >= perso.getNiveau()){
                tvl.setTextColor(Color.parseColor("#2f8b31"));
                tvr.setTextColor(Color.parseColor("#2f8b31"));
            }else{
                tvl.setTextColor(Color.parseColor("#8e282b"));
                tvr.setTextColor(Color.parseColor("#8e282b"));
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
        Log.i("CompetenceFragment.addCompetencePoint()",
                "Points = " + this.perso.getCompetencesPoints());
        if (this.perso.getCompetencesPoints() > 0) {
            int i = 0;
            List<Compétence> competences = perso.getCompétences();
            while (!competences.get(i).getNom().equals(nom))
                ++i;
            Compétence c = competences.get(i);
            c.addPoint();
            layoutB.removeAllViews();
            layoutL.removeAllViews();
            layoutR.removeAllViews();
            instanciateComp();
        }
    }
}
