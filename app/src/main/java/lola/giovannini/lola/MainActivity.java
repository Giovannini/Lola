package lola.giovannini.lola;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends FragmentActivity {

    String CLASS_NAME = "MainActivity";
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    TabPagerAdapter tabAdapter;
    ActionBar actionBar;

    Personnage perso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createJSON();

        tabAdapter = new TabPagerAdapter(getSupportFragmentManager(), this.perso);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar = getActionBar();
                        actionBar.setSelectedNavigationItem(position);
                    }
                }
        );
        mViewPager.setAdapter(tabAdapter);

        actionBar = getActionBar();
        //enables tabs on action bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        //Add new tabs
        actionBar.addTab(actionBar.newTab().setText("Général").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Competences").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Equipement").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Combat").setTabListener(tabListener));
        for (Classe c : this.perso.getClasses()) {
            actionBar.addTab(actionBar.newTab().setText(c.getNom()).setTabListener(tabListener));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Personnage getPerso() {
        return this.perso;
    }

    public String getSelectedTabTitle() {
        return actionBar.getSelectedTab().getText().toString();
    }

    private void createJSON() {
        File persoDir = getApplicationContext().getDir("Lola", Context.MODE_PRIVATE);
        File persoFile = new File(persoDir, "perso.json");
        InputStream is = null;
        if ((persoDir.exists() && persoFile.exists())) {
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

            } catch (Exception e) {
                Log.e("MainActivity", "Erreur lors de la création du fichier perso.json:\n" + e.getMessage());
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
