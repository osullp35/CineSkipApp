package com.example.admin.cineskip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class movieSelection extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    MovieTimes movieTimes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_times);

        listView = (ListView) findViewById(R.id.ListView);

        movieTimes = new MovieTimes();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("MovieTimes");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.movies_info,R.id.moviesInfo, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {

                    movieTimes = ds.getValue(MovieTimes.class);
                    list.add(movieTimes.getName().toString() + "  " +movieTimes.getTime2().toString() + "  " +movieTimes.getTime3().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
