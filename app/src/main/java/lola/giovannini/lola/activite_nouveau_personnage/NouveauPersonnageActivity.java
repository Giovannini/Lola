package lola.giovannini.lola.activite_nouveau_personnage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import lola.giovannini.lola.R;
import lola.giovannini.lola.activite_creation.race.RaceActivity;
import lola.giovannini.lola.activite_main.MainActivity;

/**
 * Created by giovannini on 11/17/14.
 */
public class NouveauPersonnageActivity extends Activity implements View.OnClickListener{
    String CLASS_NAME = "NouveauPersonnageActivity";

    LinearLayout persoChooser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_personnage);

        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.themecolor));
        getActionBar().setBackgroundDrawable(colorDrawable);

        persoChooser = (LinearLayout) findViewById(R.id.persoChooserLinearLayout);

        fillPersoChooserWithPerso();
    }

    private void fillPersoChooserWithPerso(){
        File persoDir = getApplicationContext().getDir("Lola", Context.MODE_PRIVATE);
        int i = 0;

        if (persoDir.exists()) {
            File[] files = persoDir.listFiles();
            for (File file : files){
                final String name = file.getName();
                if (name.contains(".json")){
                    Log.d(CLASS_NAME, "File found: " + name);
                    TextView tv = new TextView(this);
                    tv.setText(file.getName().substring(0, name.length() - 5));
                    tv.setTextSize(16.0f);
                    tv.setPadding(10, 21, 10, 17);
                    if (i%2 == 0)
                        tv.setBackgroundColor(Color.parseColor("#dddddd"));
                    else
                        tv.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(NouveauPersonnageActivity.this,
                                    MainActivity.class);
                            i.putExtra("file", name);
                            startActivity(i);
                        }
                    });
                    persoChooser.addView(tv);
                }
            }
        }
        TextView tv = new TextView(this);
        tv.setText("+");
        tv.setTextSize(16.0f);
        tv.setPadding(10, 21, 10, 17);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NouveauPersonnageActivity.this, RaceActivity.class);
                startActivity(i);
            }
        });
        tv.setBackgroundColor(Color.parseColor("#555555"));
        persoChooser.addView(tv);
    }

    @Override
    public void onClick(View v) {

    }
}
