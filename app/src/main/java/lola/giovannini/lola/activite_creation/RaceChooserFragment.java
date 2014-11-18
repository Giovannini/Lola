package lola.giovannini.lola.activite_creation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/18/14.
 */
public class RaceChooserFragment extends Fragment{

    ImageView image;
    TextView description;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View race = inflater.inflate(R.layout.activity_creation_fragment_race, container, false);

        image = (ImageView) race.findViewById(R.id.activity_creation_fragment_race_image_ImageView);
        description = (TextView) race.findViewById(R.id
                .activity_creation_fragment_race_description_TextView);

        image.setImageResource(((RaceActivity)getActivity()).getImage());
        description.setText(((RaceActivity)getActivity()).getDescription());

        return race;
    }
}
