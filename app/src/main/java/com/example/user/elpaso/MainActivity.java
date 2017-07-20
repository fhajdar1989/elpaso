package com.example.user.elpaso;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SliderLayout slider= (SliderLayout) findViewById(R.id.slider);

        final Transformation transformation = new RoundedCornersTransformation(30, 30);

        slider.addSlider(new BaseSliderView(this) {
            @Override
            public View getView() {
                ImageView sl=new ImageView(MainActivity.this);
                Picasso.with(MainActivity.this)
                        .load("http://photos2.meetupstatic.com/photos/event/1/0/8/4/600_458044228.jpeg")
                        .transform(transformation)
                        .resize(1000, 700)
                        .into(sl);
                return sl ;
            }
        });

        slider.addSlider(new BaseSliderView(this) {
            @Override
            public View getView() {
                ImageView sl=new ImageView(MainActivity.this);
                Picasso.with(MainActivity.this)
                        .load("http://www.danceplace.com/index/image/EL+Paso+Latin+American+Dance+Studio-Annandale-Australia/4032.gif")
                        .transform(transformation)
                        .resize(1000, 700)
                        .into(sl);
                return sl ;
            }
        });

        slider.addSlider(new BaseSliderView(this) {
            @Override
            public View getView() {
                ImageView sl=new ImageView(MainActivity.this);
                Picasso.with(MainActivity.this)
                        .load("http://copingmag.com/cwc/images/ncsd_gallery/2013/2434/71-2-el-paso-tx__photo_450_450.jpg")
                        .transform(transformation)
                        .resize(1000, 700)
                        .into(sl);
                return sl ;
            }
        });

        slider.addSlider(new BaseSliderView(this) {
            @Override
            public View getView() {
                ImageView sl=new ImageView(MainActivity.this);
                Picasso.with(MainActivity.this)
                        .load("https://i.ytimg.com/vi/yD7Tr6p8HQo/hqdefault.jpg")
                        .transform(transformation)
                        .resize(1000, 700)
                        .into(sl);
                return sl ;
            }
        });

        slider.addSlider(new BaseSliderView(this) {
            @Override
            public View getView() {
                ImageView sl=new ImageView(MainActivity.this);
                Picasso.with(MainActivity.this)
                        .load("https://i.ytimg.com/vi/BN5nXotHZjM/maxresdefault.jpg")
                        .transform(transformation)
                        .resize(1000, 700)
                        .into(sl);
                return sl ;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

         if(id==R.id.facebook){
            //startActivity(new Intent(this, DetailsActivity.class));
            String url = "https://www.facebook.com/SalsaElPasoSarajevo/?fref=ts";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_onama) {
            if (dialog == null) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alert_label_editor, null);
                dialogBuilder.setView(dialogView);
                dialogBuilder.setTitle("O nama");
                dialogBuilder.setPositiveButton("NATRAG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);

                dialog = dialogBuilder.create();
            }
            dialog.show();
        } else if (id == R.id.nav_muzika) {
            startActivity(new Intent(this, MusicActivity.class));
        } else if (id == R.id.nav_galerija) {
            startActivity(new Intent(this, AlbumsActivity.class));
        } else if (id == R.id.nav_desavanja) {
            startActivity(new Intent(this, EventsActivity.class));
        } else if (id == R.id.nav_grupe) {
            startActivity(new Intent(this, GroupsActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(43.8561783, 18.3771591);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("El Paso"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f));
    }
}
