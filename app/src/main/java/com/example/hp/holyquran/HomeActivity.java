package com.example.hp.holyquran;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.hp.holyquran.Base.BaseActivity;

import static com.example.hp.holyquran.R.id.fragment_container;


public class HomeActivity extends BaseActivity {

 BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              int id=item.getItemId();
            android.support.v4.app.Fragment fragment = null;
            if(id == R.id.navigation_quran){

                fragment=new QuranFragments();

            }
            else if(id == R.id.navigation_sebha){
                fragment=new sebhaFragment();}
                else if(id==R.id.navigation_ahadeth){
                fragment=new Ahadeth();
            }else if(id==R.id.navigation_head){
                fragment=new ListenFragment();
            }
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container,fragment).commit();

          return true;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation =findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_quran);


    }

}
