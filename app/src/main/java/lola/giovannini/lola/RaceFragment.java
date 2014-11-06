package lola.giovannini.lola;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by giovannini on 11/5/14.
 */
class CustomAdapter extends ArrayAdapter<String> {
    private Activity context;
    ArrayList<String> elements;

    public CustomAdapter(Activity context, int resource, ArrayList<String> elements) {
        super(context, resource, elements);
        this.context = context;
        this.elements = elements;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.element_spinner, parent, false);
        }

        String current = elements.get(position);
        TextView name = (TextView) row.findViewById(R.id.spinnerElement);
        //name.setText(current.charAt(1));
        //name.setGravity(View.TEXT_ALIGNMENT_CENTER);

        return row;
    }


}

public class RaceFragment extends Fragment {
    Personnage perso;
    Spinner spinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View race = inflater.inflate(R.layout.frag_race, container, false);

        spinner = (Spinner) race.findViewById(R.id.spinner);
        final String[] arraySpinner = {"Okok", "Lala"};
        ArrayList<String> elements = new ArrayList<String>();
        elements.add("Okok");
        elements.add("Lala");
        CustomAdapter adapter = new CustomAdapter(getActivity(),
                R.layout.element_spinner,elements);
        spinner.setAdapter(adapter);

        Log.i("OverviewFragment", "Le fragment Race est créé.");
        return race;
    }


}


