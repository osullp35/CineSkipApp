package com.example.admin.cineskip;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.cineskip.R.string.navigation_drawer_open;

public class moviesRecycle extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabasetitle;
    public String movietitle1;
    public List<String> movietitlelist = new ArrayList<String>();
    private ProgressDialog progressDialog;
    //private View movieView;
    private LinearLayout menuclick;
    private CardView cardView;
    private Button selectButton;

    //private GridLayoutManager layoutManager;

String movietitle;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_recycle);

        selectButton = (Button) findViewById(R.id.selectButton);
        selectButton.setOnClickListener((View.OnClickListener) this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Movies");
        mDatabasetitle = FirebaseDatabase.getInstance().getReference().child("Movies");
        mDatabase.keepSynced(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);


        progressDialog = new ProgressDialog(this);
        //cardView = findViewById(R.id.movieCard);
        //cardView.setOnClickListener(this);
        //mBlogList.setOnClickListener(this);
        //menuclick = (LinearLayout)findViewById(R.id.moviebutton);
        //menuclick.setOnClickListener(this);
           /* @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),qrTest.class);
                startActivity(i);
            }
        });*/

        //layoutManager = new GridLayoutManager(this, 2);

        mBlogList = (RecyclerView) findViewById(R.id.myRecycleView);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new GridLayoutManager(this,2));

        mDatabasetitle.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot datasnap : dataSnapshot.getChildren()) {
                    movietitle = datasnap.getKey().toString();
                    Log.d("movies = ", movietitle.toString());
                    movietitlelist.add(movietitle.substring(0, 1).toUpperCase() + movietitle.substring(1));
                    Log.d("movies = ", movietitlelist.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.selectionNav) {
            // Handle the camera action
            Intent i = new Intent(this,profileActivity.class);
            startActivity(i);

        } else if (id == R.id.ticketNav) {
            Intent i = new Intent(this,qrTest.class);
            startActivity(i);

        }
        else if (id == R.id.preOrderNav) {
            Intent i = new Intent(this,qrTest.class);
            startActivity(i);

        }
        else if (id == R.id.trailersNav) {
            Intent i = new Intent(this,movieTrailers.class);
            startActivity(i);

        } else if (id == R.id.logoutNav) {
            //firebaseAuth.signOut();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    protected void onStart(){
        progressDialog.setMessage("Retrieving Movies. . .");
        progressDialog.show();
        super.onStart();


        FirebaseRecyclerAdapter<Blog,BlogViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>
                (Blog.class,R.layout.cardview,BlogViewHolder.class,mDatabase) {
            @Override

            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                viewHolder.setTitle(movietitlelist.get(position).toString());
                viewHolder.setImage(getApplicationContext(), model.getImage());
                progressDialog.dismiss();


            }


        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);


    }

    @Override
    public void onClick(View view) {
        if (view == selectButton){
            Intent i = new Intent(this, timeSelection.class);
            startActivity(i);
        }
    }

    /*@Override
    public void onClick(View view) {
        if (view == mBlogList) {
            Intent i = new Intent(getApplicationContext(), qrTest.class);
            startActivity(i);
        }

    }*/


    /*@Override
    public void onClick(View view) {

            Intent i = new Intent(this, qrTest.class);
            startActivity(i);

    }*/



    /*@Override
    public void onClick(View view) {


    }*/


    public static class BlogViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener
    {
        View mView;

        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

        }

        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
            Log.d(title, "setTitle: ");
        }
        public void setImage(Context ctx,String image)
        {
            ImageView post_Image=(ImageView)mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_Image);
        }


        @Override
        public void onClick(View itemView) {
            Toast.makeText(itemView.getContext(),"hi", Toast.LENGTH_SHORT).show();
        }
            /*if (view == mView) {
                Intent i = new Intent(view.getContext(), qrTest.class);
                startActivity(i);
            }
        }*/
    }




}
