package com.example.user.elpaso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosActivity extends AppCompatActivity {

    private GridView photosListView;
    private PhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        photosListView = (GridView) findViewById(R.id.lv_photos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Galerija");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https:/graph.facebook.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<Photos> call = api.getPhotos(getIntent().getStringExtra("albumId"));
        call.enqueue(new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                if (response.body() != null) {
                    photosAdapter = new PhotosAdapter(response.body().data);
                    photosListView.setAdapter(photosAdapter);
                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                t.printStackTrace();
            }
        });

        photosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PhotosActivity.this, PhotoViewActivity.class);
                intent.putExtra("url", photosAdapter.getUrl(position));
                startActivity(intent);
            }
        });
    }

    class PhotosAdapter extends BaseAdapter {

        List<Photos.PhotoData> photos;

        PhotosAdapter(List<Photos.PhotoData> photos) {
            this.photos = photos;
        }

        @Override
        public int getCount() {
            return photos.size();
        }

        @Override
        public Object getItem(int position) {
            return photos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = PhotosActivity.this.getLayoutInflater().inflate(R.layout.item_gallery, parent, false);
            Photos.PhotoData photo = photos.get(position);

            ImageView image = (ImageView) convertView.findViewById(R.id.iv_image);

            Picasso.with(PhotosActivity.this)
                    .load(photo.source)
                    .resize(1000, 600)
                    .centerCrop()
                    .into(image);

            return convertView;
        }

        public String getUrl(int position) {
            return photos.get(position).source;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return true;
    }
}
