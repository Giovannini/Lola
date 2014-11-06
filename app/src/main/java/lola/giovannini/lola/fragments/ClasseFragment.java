package lola.giovannini.lola.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lola.giovannini.lola.Classe;
import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 10/18/14.
 */
public class ClasseFragment extends Fragment {
    String name;

    LinearLayout layout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View classe = inflater.inflate(R.layout.frag_classe, container, false);

        this.name = /*((MainActivity) getActivity()).getSelectedTabTitle()*/"Roublard";

        this.layout = (LinearLayout) classe.findViewById(R.id.classLayoutPart);

        getParticularités();
        Log.i("ClasseFragment", "Ce fragment est créé.");
        return classe;
    }

    private void getParticularités(){
        Log.i("ClasseFragment" + this.name, "Lancement de la fonction getParticularités().");
        Personnage p = ((MainActivity) getActivity()).getPerso();
        Iterator<Classe> it = p.getClasses().iterator();

        while (it.hasNext()){
            Classe c = it.next();
            Log.i("ClasseFragment" + this.name, "Traitement de la classe " + c.getNom() + ".");
            if (c.getNom().equals(this.name)){
                Map<String, String> parts = c.getParticularités();
                List<String> keys = new ArrayList<String>(parts.keySet());
                Collections.sort(keys, new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return (lhs).compareTo(rhs);
                    }
                });

                for(String k : keys){
                    TextView title = new TextView(getActivity().getApplicationContext());
                    final TextView description = new TextView(getActivity().getApplicationContext());
                    final LinearLayout partLayout = new LinearLayout(getActivity()
                            .getApplicationContext());
                    partLayout.setOrientation(LinearLayout.VERTICAL);

                    title.setText(k);
                    title.setAllCaps(true);
                    title.setTypeface(null, Typeface.BOLD);
                    title.setTextColor(Color.rgb(50, 50, 50));

                    description.setText(parts.get(k));
                    description.setTextColor(Color.BLACK);
                    description.setVisibility(View.GONE);

                    partLayout.addView(title);
                    partLayout.addView(description);

                    partLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (description.getVisibility() == View.GONE){
                                description.setVisibility(View.VISIBLE);
                            }else if(description.getVisibility() == View.VISIBLE) {
                                description.setVisibility(View.GONE);
                            }
                        }
                    });

                    this.layout.addView(partLayout);
                }
            }
        }
    }
}