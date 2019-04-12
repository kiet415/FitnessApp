package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    /*private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;*/
    private ViewPager viewPager;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        final BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        /*toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);*/


        bottomNav.setSelectedItemId(R.id.navigation_home);


        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //new HomeFragment()).commit();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNav.getMenu().getItem(0).setChecked(false);
                }
                bottomNav.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNav.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    //Fragment selectedFragment = null;

                    switch(menuItem.getItemId()) {
                        case R.id.navigation_activity:
                            //selectedFragment = new ActivityFragment();
                            viewPager.setCurrentItem(0);
                            return true;
                        //break;
                        case R.id.navigation_home:
                            //selectedFragment = new HomeFragment();
                            viewPager.setCurrentItem(1);
                            return true;
                        //break;
                        case R.id.navigation_profile:
                            //selectedFragment = new ProfileFragment();
                            viewPager.setCurrentItem(2);
                            return true;
                        //break;
                    }

                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    //selectedFragment).commit();

                    return false;
                }
            };

    public static class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    //Log.d("NAV BAR", "Activity");
                    return ActivityFragment.newInstance();
                case 1:
                    //Log.d("NAV BAR", "Home");
                    return HomeFragment.newInstance();
                case 2:
                    //Log.d("NAV BAR", "Profile");
                    return ProfileFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
