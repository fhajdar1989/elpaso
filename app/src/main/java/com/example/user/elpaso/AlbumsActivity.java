package com.example.user.elpaso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsActivity extends AppCompatActivity {

    private ListView photosListView;
    private AlbumsActivity.PhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        photosListView = (ListView) findViewById(R.id.lv_albums);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Albumi");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https:/graph.facebook.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<Album> call = api.getAlbums();
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if (response.body() != null) {
                    photosAdapter = new AlbumsActivity.PhotosAdapter(response.body().data);
                    photosListView.setAdapter(photosAdapter);
                }
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                t.printStackTrace();
            }
        });

        photosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AlbumsActivity.this, PhotosActivity.class);
                intent.putExtra("albumId", photosAdapter.getAlbumId(position));
                startActivity(intent);
            }
        });
    }

    class PhotosAdapter extends BaseAdapter {

        List<Album.SingleAlbum> photos;

        PhotosAdapter(List<Album.SingleAlbum> photos) {
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
            convertView = AlbumsActivity.this.getLayoutInflater().inflate(R.layout.item_photo, parent, false);
            Album.SingleAlbum photo = photos.get(position);

            final ImageView image = (ImageView) convertView.findViewById(R.id.iv_image);
            TextView name = (TextView) convertView.findViewById(R.id.tv_name);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https:/graph.facebook.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api api = retrofit.create(Api.class);
            Call<Photos.Photo> call = api.getPhoto(photo.cover_photo.id);
            call.enqueue(new Callback<Photos.Photo>() {
                @Override
                public void onResponse(Call<Photos.Photo> call, Response<Photos.Photo> response) {
                    if (response.body() != null) {
                        Picasso.with(AlbumsActivity.this)
                                .load(response.body().picture)
                                .resize(320, 200)
                                .into(image);
                    }
                }

                @Override
                public void onFailure(Call<Photos.Photo> call, Throwable t) {
                    t.printStackTrace();
                }
            });

            name.setText(photo.name);

            return convertView;
        }

        public String getAlbumId(int position) {
            return photos.get(position).id;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return true;
    }
}
