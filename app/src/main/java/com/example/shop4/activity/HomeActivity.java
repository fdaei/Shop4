package com.example.shop4.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.shop4.R;
import com.example.shop4.fragments.CategoryFragment;
import com.example.shop4.fragments.HomeFragment;
import com.example.shop4.fragments.ProfileFragment;
import com.example.shop4.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView =findViewById(R.id.bottom_navigation);
        HomeFragment homeFragment=new HomeFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,homeFragment);
        fragmentTransaction.commit();

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_home:
                        HomeFragment homeFragment = new HomeFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout , homeFragment);
                        fragmentTransaction.commit();
                        break;

                    case R.id.nav_category:
                        CategoryFragment categoryFragment = new CategoryFragment();
                        FragmentTransaction fragmentTransaction_cat = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction_cat.replace(R.id.frameLayout , categoryFragment);
                        fragmentTransaction_cat.commit();
                        break;

                    case R.id.nav_search:
                        SearchFragment searchFragment = new SearchFragment();
                        FragmentTransaction fragmentTransaction_search = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction_search.replace(R.id.frameLayout , searchFragment);
                        fragmentTransaction_search.commit();
                        break;

                    case R.id.nav_profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        FragmentTransaction fragmentTransaction_profile = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction_profile.replace(R.id.frameLayout , profileFragment);
                        fragmentTransaction_profile.commit();
                        break;

                }

            }
        });
    }
}