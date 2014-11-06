package lola.giovannini.lola;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import lola.giovannini.lola.fragments.ClasseFragment;
import lola.giovannini.lola.fragments.combat.CombatFragment;
import lola.giovannini.lola.fragments.CompetencesFragment;
import lola.giovannini.lola.fragments.EquipementFragment;
import lola.giovannini.lola.fragments.general.OverviewFragment;


public class MainActivity extends FragmentActivity {

    String CLASS_NAME = "MainActivity";

    MyPagerAdapter myPagerAdapter;
    ViewPager mViewPager;

    Personnage perso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#466C79"));
        getActionBar().setBackgroundDrawable(colorDrawable);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(myPagerAdapter);

        createJSON();
    }

    public Personnage getPerso() {
        return this.perso;
    }

    private void createJSON() {
        File persoDir = getApplicationContext().getDir("Lola", Context.MODE_PRIVATE);
        File persoFile = new File(persoDir, "perso.json");
        InputStream is = null;
        /*******HERE**********/
        /*******HERE**********/
        /*******HERE**********/
        /*******HERE**********/
        if (!(persoDir.exists() && persoFile.exists())) {
            Log.d(CLASS_NAME + ".createJson()", "Le fichier json existe déjà.");
            try {
                is = new FileInputStream(persoFile);
                retrieveJSON(is);
            } catch (Exception e) {
                Log.e(CLASS_NAME + ".createJson()",
                        "Erreur lors de la lecture du fichier perso.json:\n" + e.getMessage());
            }
        } else {
            FileOutputStream fos = null;
            try {
                persoDir.mkdirs();
                persoFile.createNewFile();

                fos = new FileOutputStream(persoFile);
                is = getBaseContext().getAssets().open("perso.json");
                retrieveJSON(is);
                fos.write(this.perso.getObj().toString().getBytes());
                fos.close();

            } catch (IOException e) {
                Log.e("MainActivity", "Erreur IO lors de la création du fichier perso.json:\n" + e
                        .getMessage());
            } catch (JSONException e) {
                Log.e("MainActivity", "Erreur JSON lors de la création du fichier perso.json:\n" + e
                        .getMessage());
            }
        }
    }

    private void retrieveJSON(InputStream is) throws JSONException {
        String json = null;
        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "utf-8");
        } catch (IOException e) {
            Log.e(CLASS_NAME + ".retrieveJson()", "e1: " + e.getMessage());
        }

        JSONObject obj = new JSONObject(json);
        this.perso = new Personnage(obj, this);
    }

    public void saveJson(JSONObject o) {
        File persoDir = getApplicationContext().getDir("Lola", Context.MODE_PRIVATE);
        File persoFile = new File(persoDir, "perso.json");
        try {
            FileOutputStream fos = new FileOutputStream(persoFile);
            fos.write(o.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            Log.e(CLASS_NAME + ".saveJson()", e.getMessage());
        }
        Log.i(CLASS_NAME + ".saveJson()", "Json saved.");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
