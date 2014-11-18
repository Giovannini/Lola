package lola.giovannini.lola.activite_creation.classe;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import lola.giovannini.lola.R;
import lola.giovannini.lola.activite_creation.race.MyRacePagerAdapter;

/**
 * Created by giovannini on 11/18/14.
 */
public class ClasseActivity extends FragmentActivity implements View.OnClickListener{

    String CLASS_NAME = "ClasseActivity";

    String[] classes = {"Roublard", "Maître des ombres"};
    int[] images = {R.drawable.classe_roublard, R.drawable.classe_mdombres};
    String[] description = {
            "Pour ceux qui subsistent grâce à leur vivacité d’esprit, " +
                    "la vie est une aventure sans fin. Ces roublards qui semblent toujours sentir" +
                    " le danger à l’avance comptent sur leur ruse, leur habileté et leur charme " +
                    "pour tourner le destin à leur avantage. Comme on ne sait jamais à quoi " +
                    "s’attendre, ils se préparent à toutes les éventualités en acquérant de " +
                    "nombreuses compétences et en s’entraînant à devenir de fins manipulateurs, " +
                    "d’agiles acrobates, des ombres discrètes ou encore des experts dans des " +
                    "dizaines d’autres professions ou domaines. Les voleurs et les joueurs, " +
                    "les beaux parleurs et les diplomates, les bandits et les chasseurs de " +
                    "primes, les explorateurs et les enquêteurs : toutes ces occupations rentrent" +
                    " dans la catégorie des roublards, tout comme d’innombrables autres " +
                    "professions nécessitant un esprit vif, une certaine habileté au combat ou un" +
                    " bonne étoile. Beaucoup de roublards préfèrent les villes et les nombreuses " +
                    "opportunités que la civilisation leur offre mais certains adoptent une vie " +
                    "de nomade, visitent de lointaines contrées, " +
                    "rencontrent des peuples exotiques et affrontent des dangers incroyables tout" +
                    " en recherchant des trésors qui le sont tout autant. En fin de compte, " +
                    "tous ceux qui désirent façonner leur destin et leur vie à leur convenance " +
                    "pourraient être considérés comme des roublards.",
            "Les peuples civilisés ont toujours eu peur de la nuit. Ils se barricadaient derrière des portes ou se rassuraient autour des feux lorsque les ombres s'allongeaient, éprouvant une méfiance légitime envers les créatures qui rôdaient dans l'obscurité. Pourtant, depuis longtemps déjà, certains ont appris que la meilleure façon de vaincre un ennemi était de le comprendre. Ainsi naquirent les premiers maîtres des ombres.\n" +
                    "\n" +
                    "Ils existent à la frontière de la lumière et des ténèbres, " +
                    "et y tissent les ombres pour devenir les artistes à moitié invisibles de " +
                    "l'imposture. Ils ne sont liés par aucune moralité, " +
                    "ni aucune tradition et rassemblent tous ceux qui ont compris la valeur de " +
                    "l'obscurité. Depuis les ombres, les lanceurs de sorts peuvent utiliser leur " +
                    "magie en toute sécurité et s'en aller rapidement, " +
                    "tandis que les combattants apprécient le facteur de surprise dont ils " +
                    "bénéficient quand ils attaquent leurs adversaires. Certains prennent même au" +
                    " pied de la lettre l'appellation de « maître des ombres » et deviennent des " +
                    "artistes et des danseurs inquiétants et mystérieux. Mais plus souvent qu'à " +
                    "son tour, un maître des ombres cédera à la tentation d'utiliser ses talents" +
                    " dans les arts de la tromperie et de l'infiltration et consacrera sa vie au " +
                    "vol."
    };

    int number = classes.length;
    FragmentStatePagerAdapter myPagerAdapter;
    ViewPager mViewPager;
    Button ok_button, next_button, last_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_race);

        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.themecolor));
        getActionBar().setBackgroundDrawable(colorDrawable);

        ok_button   = (Button) findViewById(R.id.activity_creation_race_choice_Button);
        ok_button.setOnClickListener(this);
        next_button = (Button) findViewById(R.id.activity_creation_race_next_Button);
        next_button.setOnClickListener(this);
        last_button = (Button) findViewById(R.id.activity_creation_race_previous_Button);
        last_button.setOnClickListener(this);
        /*
         * ViewPager and its adapters use support library
         * fragments, so use getSupportFragmentManager.
         */
        myPagerAdapter = new MyClassePagerAdapter(getSupportFragmentManager(), classes.length);

        mViewPager = (ViewPager) findViewById(R.id.activity_creation_race_pager);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(myPagerAdapter);
    }

    public int getImage(int i){
        return images[i];
    }

    public String getDescription(int i){
        return description[i];
    }


    @Override
    public void onClick(View v) {
        if (v == ok_button){

        }else if(v == next_button){
            mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % number);
        }else if(v == last_button){
            mViewPager.setCurrentItem((mViewPager.getCurrentItem() + number - 1) % number);
        }
    }
}
