package lola.giovannini.lola.activite_creation;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import lola.giovannini.lola.R;
import lola.giovannini.lola.activite_main.activite_combat.MyCombatPagerAdapter;
import lola.giovannini.lola.activite_main.activite_overview.MyOverviewPagerAdapter;

/**
 * Created by giovannini on 11/18/14.
 */
public class RaceActivity extends FragmentActivity {

    String CLASS_NAME = "RaceActivity";

    int[] images = {R.drawable.race_halfelin, R.drawable.race_nain};
    String[] description = {
            "Les halfelins sont des créatures optimistes, joyeuses, " +
                    "inexplicablement chanceuses, dotées d’une passion pour les voyages, " +
                    "petites en taille mais incroyablement fanfaronnes et curieuses. À la fois très " +
                    "émotives et faciles à vivre, elles préfèrent éviter de s’énerver et se tenir " +
                    "toujours prêtes à profiter de toutes les opportunités. Les halfelins ne se " +
                    "laissent aller à des crises violentes ou émotionnelles que rarement, " +
                    "et en tout les cas moins souvent que les autres races d'humeur plus changeante. " +
                    "Ils ne perdent quasiment jamais leur sens de l’humour, " +
                    "même face à un désastre imminent.\n" +
                    "Ce sont des opportunistes invétérés. Comme leur physique ne leur permet pas " +
                    "toujours de se défendre, ils ont appris à faire le dos rond ou à se faire tout " +
                    "petits. Mais leur curiosité prend souvent le pas sur leur bon sens et les pousse" +
                    " vers de mauvaises décisions ou des situations difficiles.\n" +
                    "Cette même curiosité les incite à voyager et à explorer de nouveaux endroits et " +
                    "à tenter de nouvelles expériences. Mais, malgré cela, " +
                    "les halfelins possèdent un sens du foyer développé. Il n’est d’ailleurs pas rare" +
                    " d’en voir certains dépenser plus que de raison pour améliorer le confort de " +
                    "leur demeure.",
            "Les nains forment une race stoïque et sévère enfouie dans des cités " +
                    "sculptées au cœur des montagnes. C’est une race fermement décidée à combattre " +
                    "les déprédations de monstres sauvages tels que les orques et les gobelins. La " +
                    "réputation des nains les dépeint comme des artisans austères et dénués d’humour " +
                    "qui ne s’intéressent qu’à la terre. L’histoire des nains explique peut-être le " +
                    "tempérament renfermé de la majorité d’entre eux : après tout, " +
                    "ils habitent au sein de montagnes reculées ou dans de dangereux royaumes " +
                    "souterrains où ils sont constamment en guerre avec les géants, " +
                    "les gobelins et bien d’autres monstres encore."
    };

    FragmentStatePagerAdapter myPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_race);

        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.themecolor));
        getActionBar().setBackgroundDrawable(colorDrawable);
        /*
         * ViewPager and its adapters use support library
         * fragments, so use getSupportFragmentManager.
         */
        myPagerAdapter = new MyRacePagerAdapter(getSupportFragmentManager(), images.length);
        mViewPager = (ViewPager) findViewById(R.id.activity_creation_race_pager);
        mViewPager.setAdapter(myPagerAdapter);
    }

    public int getNumber(){
        /**TODO
         * Cette fonction ne retourne pas ce que je veux.
         */
        return  mViewPager.getCurrentItem();
    }

    public int getImage(){
        return images[getNumber()];
    }

    public String getDescription(){
        return description[getNumber()];
    }


}
