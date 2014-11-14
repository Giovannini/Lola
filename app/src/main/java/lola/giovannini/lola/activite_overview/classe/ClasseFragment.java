package lola.giovannini.lola.activite_overview.classe;

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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lola.giovannini.lola.Classe;
import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Particularité;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;
import lola.giovannini.lola.fragments.MySpinnerAdapter;

/**
 * Created by giovannini on 10/18/14.
 */
public class ClasseFragment extends Fragment {
    String CLASS_NAME = "ClasseFragment";
    String name;
    Personnage perso;
    Classe classeCourante;

    Spinner spinner;
    ImageView image;
    LinearLayout containerNiveau;

    LinearLayout layout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View classe = inflater.inflate(R.layout.frag_classe, container, false);
        this.perso = ((MainActivity) getActivity()).getPerso();

        getAllViews(classe);

        spinner = (Spinner) classe.findViewById(R.id.classe_spinner);
        List<String> frag_names = new ArrayList<String>();
        for (Classe c : perso.getClasses()){
            frag_names.add(c.getNom().toUpperCase());
        }
        MySpinnerAdapter adapter = new MySpinnerAdapter(getActivity().getApplicationContext());
        adapter.update_frag_names(frag_names);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Classe newClasse = perso.getClasses().get(position);
                name = newClasse.getNom();
                changeClasse(newClasse);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        this.classeCourante = perso.getClasses().get(0);
        getClasse(this.classeCourante);

        Log.i(CLASS_NAME, "Le fragment Classe est créé.");
        return classe;
    }

    private void getAllViews(View classe){
        this.layout = (LinearLayout) classe.findViewById(R.id.layoutContainerListParts);
        this.image = (ImageView) classe.findViewById(R.id.classeImageView);
        this.containerNiveau = (LinearLayout) classe.findViewById(R.id.layoutClassContainerNiveau);
    }

    private void getClasse(Classe c){
        Log.i(CLASS_NAME, "Affichage de la classe " + c.getNom());
        final Context context = getActivity();

        TextView niveau = new TextView(context);
        niveau.setText("Niveau: " + c.getNiveau());
        containerNiveau.addView(niveau);
        ajoutBoutonLevelUp();
        this.image.setImageResource(c.getImage());

        List<Particularité> parts = c.getParticularités();

        for(final Particularité part : parts){
            TextView title = new TextView(context);
            final String nom = part.getNom();

            title.setText(nom);
            title.setTextColor(Color.parseColor("#222222"));
            title.setTextSize(14.0f);
            title.setPadding(13, 23, 5, 19);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nom.contains("Talent")){
                        popupTalent();
                    }else {
                        popupDescription(part);
                    }
                }
            });

            this.layout.addView(title);
        }
    }

    private void changeClasse(Classe c){
        Log.i(CLASS_NAME + "changeClasse()", "Changement de classe");
        this.classeCourante = c;
        this.layout.removeAllViews();
        this.containerNiveau.removeAllViews();
        getClasse(c);
    }

    public void ajoutBoutonLevelUp(){
        if (perso.getLevelUpClass() > 0) {
        //Si un point de LU a été généré, les boutons sont affichés
            final Context context = getActivity();
            TextView levelup = new TextView(context);
            levelup.setText("Level up");
            levelup.setTextColor(getResources().getColor(R.color.themecolor));
            levelup.setTextSize(14.0f);
            levelup.setPadding(13, 23, 5, 19);
            levelup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    classeCourante.addNiveau();
                    changeClasse(classeCourante);
                }
            });

            containerNiveau.addView(levelup);

            if (perso.getClasses().size() == 1){
                TextView multiclasse = new TextView(context);
                multiclasse.setText("Multiclassage");
                multiclasse.setTextColor(getResources().getColor(R.color.themecolor));
                multiclasse.setTextSize(14.0f);
                multiclasse.setPadding(13, 23, 5, 19);

                multiclasse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpMulticlasse();
                        changeClasse(classeCourante);
                    }
                });

                containerNiveau.addView(multiclasse);
            }
        }
    }

    private void popupDescription(Particularité p){
        final Context context = getActivity();
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_don, null);
        TextView nom_part = (TextView) promptsView.findViewById(R.id
                .prompt_don_nom);
        nom_part.setText(p.getNom());
        TextView description_part = (TextView) promptsView.findViewById(R.id
                .prompt_don_description);
        description_part.setText(p.getDescription());

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

    private void popUpMulticlasse(){
        final Context context = getActivity();
        LayoutInflater li = LayoutInflater.from(context);
        final View promptsView = li.inflate(R.layout.prompt_multiclassage, null);
        final RadioGroup rg = (RadioGroup) promptsView.findViewById(R.id
                .prompt_multiclassage_radioGroup);
        RadioButton rb_mdo = new RadioButton(context);
        rb_mdo.setText("Maître des ombres");
        rb_mdo.setSelected(true);
        rg.addView(rb_mdo);
        RadioButton rb_o = new RadioButton(context);
        rb_o.setText("Ombrageur");
        rg.addView(rb_o);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                RadioButton rb = (RadioButton) promptsView.findViewById(rg
                                        .getCheckedRadioButtonId());
                                String newClasseName = rb.getText().toString();
                                perso.multiclassage(newClasseName);
                                perso.useLevelUpClassPoint();

                                List<String> frag_names = new ArrayList<String>();
                                for (Classe c : perso.getClasses()){
                                    if(c.getNom().equals(newClasseName))
                                        classeCourante = c;
                                    frag_names.add(c.getNom().toUpperCase());
                                }
                                ((MySpinnerAdapter) spinner.getAdapter()).update_frag_names(frag_names);
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void popupTalent(){
        final Context context = getActivity();
        LayoutInflater li = LayoutInflater.from(context);
        final View promptsView = li.inflate(R.layout.prompt_multiclassage, null);
        final RadioGroup rg = (RadioGroup) promptsView.findViewById(R.id
                .prompt_multiclassage_radioGroup);
        for(Particularité p : classeCourante.getTalents()){
            RadioButton rb = new RadioButton(context);
            rb.setText(p.getNom());
            rg.addView(rb);
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                RadioButton rb = (RadioButton) promptsView.findViewById(rg
                                        .getCheckedRadioButtonId());
                                String newTalentName = rb.getText().toString();
                                int i = 0;
                                while( ! classeCourante.getTalents().get(i).getNom().equals
                                        (newTalentName))
                                    i++;

                                Particularité newTalent = classeCourante.getTalents().get(i);
                                JSONObject obj_talent = new JSONObject();
                                try {
                                    obj_talent.put("nom", newTalent.getNom());
                                    obj_talent.put("description", newTalent.getDescription());

                                    perso.getObj().getJSONArray("Talents").put(obj_talent);
                                    perso.saveJSON();
                                    classeCourante.initParts();
                                    changeClasse(classeCourante);
                                }catch (JSONException e){
                                    Log.e(CLASS_NAME + ".popupTalent()",
                                            "Erreur JSON lors de l'ajout d'un nouveau talent.\n"
                                                    + e.getMessage());
                                }
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}