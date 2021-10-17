package com.example.news;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.news.fragment.Business;
import com.example.news.fragment.Entertainment;
import com.example.news.fragment.Health;
import com.example.news.fragment.Home;
import com.example.news.fragment.Sports;
import com.example.news.fragment.Technology;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {


    ViewPager2 viewPager;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation, R.string.close_navigation);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPageAdapter(this));

        NavigationView navigationView = findViewById(R.id.navigationView);
        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.home_menu);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_menu:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.sports_menu:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.technology_menu:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.health_menu:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.business_menu:
                        viewPager.setCurrentItem(4);
                        break;
                    case R.id.entertainment_menu:
                        viewPager.setCurrentItem(5);
                        break;

                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });

        TabLayout tabLayout = findViewById(R.id.slidingTab);
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setIcon(R.drawable.ic_home);
                }
                if (position == 1) {
                    tab.setIcon(R.drawable.ic_sport);
                }
                if (position == 2) {
                    tab.setIcon(R.drawable.ic_technology);
                }
                if (position == 3) {
                    tab.setIcon(R.drawable.ic_health);
                }
                if (position == 4) {
                    tab.setIcon(R.drawable.ic_business);
                }
                if (position == 5) {
                    tab.setIcon(R.drawable.ic_film);
                }
            }
        }).attach();

    }


}


class MyPageAdapter extends FragmentStateAdapter {


    public MyPageAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            Fragment fragment = new Home();
            return fragment;
        }
        if (position == 1) {
            Fragment fragment = new Sports();
            return fragment;
        }
        if (position == 2) {
            Fragment fragment = new Technology();
            return fragment;
        }
        if (position == 3) {
            Fragment fragment = new Health();
            return fragment;
        }
        if (position == 4) {
            Fragment fragment = new Business();
            return fragment;
        }
        if (position == 5) {
            Fragment fragment = new Entertainment();
            return fragment;
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}


