package com.example.user.elpaso;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GroupsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Grupe");

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_groups);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_groups);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        GroupsAdapter groupsAdapter = new GroupsAdapter();
        viewPager.setAdapter(groupsAdapter);
    }

    class GroupsAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Početna";
                case 1: return "Srednja";
                case 2: return "Napredna";
            }
            return "";
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = GroupsActivity.this.getLayoutInflater().inflate(R.layout.item_groups, container, false);

            TextView text = (TextView) view.findViewById(R.id.tv_text);
            if (position == 0) {
                text.setText("Početna grupa je grupa koji se prvi put susreću sa salom i uče tek osnovne korake salse.\n\nTermini trening su:\nPonedjeljak: 20:00 do 21:00\nSrijeda: 20:00 do 21:00");
            } else if (position == 1) {
                text.setText("Srednja grupa je grupa koji su prošli osnovne korake i spremni su da počnu učiti naprednije korake.\n\nTermini trening su:\nUtorak: 20:00 do 21:00\nČetvrtak: 20:00 do 21:00");
            } else if (position == 2) {
                text.setText("Napredna grupa je grupa zamišljena za plesače koji žele učiti zahtjevnije figure i kombinacije i dodatno unaprijediti svoj ples.\n\nTermini trening su:\nPonedjeljak: 19:00 do 20:00\nSrijeda: 19:00 do 20:00");
            }

            view.setTag(position);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(container.findViewWithTag(position));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return true;
    }
    //AIzaSyAUlV7R5Eo-HSZ_eOnykF5ZjphlV9NbNsQ
}
