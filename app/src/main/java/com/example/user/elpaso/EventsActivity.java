package com.example.user.elpaso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsActivity extends AppCompatActivity {
    private EventsAdapter eventsAdapter;
    private ListView eventsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventsListView = (ListView) findViewById(R.id.lv_events);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dešavanja");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https:/graph.facebook.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<Events> call = api.getEvents();
        call.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                if (response.body() != null) {
                    eventsAdapter = new EventsAdapter(response.body().data);
                    eventsListView.setAdapter(eventsAdapter);
                }
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                t.printStackTrace();
            }
        });

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/events/" + eventsAdapter.events.get(position).id));
                startActivity(viewIntent);
            }
        });
    }

    class EventsAdapter extends BaseAdapter {

        List<Events.Data> events;

        EventsAdapter(List<Events.Data> events) {
            this.events = events;
        }

        @Override
        public int getCount() {
            return events.size();
        }

        @Override
        public Object getItem(int position) {
            return events.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = EventsActivity.this.getLayoutInflater().inflate(R.layout.item_event, parent, false);
            Events.Data event = events.get(position);

            TextView name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView description = (TextView) convertView.findViewById(R.id.tv_description);
            ImageView picture = (ImageView) convertView.findViewById(R.id.iv_picture);

            name.setText(event.name);
            Picasso.with(EventsActivity.this)
                    .load("https://graph.facebook.com/" + event.id + "/picture")
                    .into(picture);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String descriptionText = "";
            if (event.place != null) descriptionText += "Lokacija: " + event.place.name + "\n";
            descriptionText += "Datum: " + dateFormat.format(event.start_time) + "\nVrijeme: " + timeFormat.format(event.start_time);

            description.setText(descriptionText);

            return convertView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return true;
    }
}
