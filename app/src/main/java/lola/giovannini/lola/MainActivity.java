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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends FragmentActivity implements ActionBar.OnNavigationListener{

    String CLASS_NAME = "MainActivity";
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    TabPagerAdapter tabAdapter;
    ActionBar actionBar;

    Personnage perso;

    /********************/
    TextView but1, but2;

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
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        /**TODO
         * Add list menu
         */
        final String[] dropdownValues = getResources().getStringArray(R.array.fragments);
        // Specify a SpinnerAdapter to populate the dropdown list.
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(actionBar.getThemedContext(),
                    android.R.layout.simple_spinner_item, android.R.id.text1,
                    dropdownValues);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(adapter, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("onCreateOptionsMenu");
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

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        Fragment fragment;
        // When the given dropdown item is selected, show its contents in the
        // container view.
        switch (position){
            case 0:
                fragment = new OverviewFragment();
                break;
            case 1:
                fragment = new CompetencesFragment();
                break;
            case 2:
                fragment = new EquipementFragment();
                break;
            case 3:
                fragment = new CombatFragment();
                break;
            case 4:
                fragment = new ClasseFragment();
                break;
            case 5:
                fragment = new ClasseFragment();
                break;
            default:
                fragment = null;
                break;
        }
        /*getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();*/
        return true;
    }
}
