package com.example.admin.cineskip;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class moviesRecycle extends AppCompatActivity implements View.OnClickListener{

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_recycle);

        selectButton = (Button) findViewById(R.id.selectButton);
        selectButton.setOnClickListener((View.OnClickListener) this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Movies");
        mDatabasetitle = FirebaseDatabase.getInstance().getReference().child("Movies");
        mDatabase.keepSynced(true);

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
