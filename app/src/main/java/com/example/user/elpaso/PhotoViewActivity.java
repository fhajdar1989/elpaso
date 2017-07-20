package com.example.user.elpaso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        ImageView image = (ImageView) findViewById(R.id.iv_image);

        Picasso.with(this)
                .load(getIntent().getStringExtra("url"))
                .into(image);
    }
}
