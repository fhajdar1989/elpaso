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

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_groups);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_groups);

        tabLayout.setupWithViewPager(viewPager);

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
                text.setText("Lala");
            } else if (position == 1) {
                text.setText("Lalajfsadkl f");
            } else if (position == 2) {
                text.setText("Lalajfkj sdlfj laksdjfjsdj fsdlkj f\njfkljsd fkl\njf lasd");
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
