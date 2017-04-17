package org.lema.notasapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.lema.notasapp.R;
import org.lema.notasapp.ui.adapter.FeedAdapter;
import org.lema.notasapp.domain.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewFeed;
    private DatabaseReference databaseReference;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        preencheReferencias();

        preparaToolbar();

        this.databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String, Post>> t =
                        new GenericTypeIndicator<HashMap<String, Post>>() {};

                HashMap posts = dataSnapshot.getValue(t);
                atualizaLista(posts);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feed, menu);

        return true;
    }

    private void preencheReferencias() {
        mRecyclerViewFeed = (RecyclerView) findViewById(R.id.rv_feed);
        //mToolbar = (Toolbar) findViewById(R.id.toolbar_feed);
    }

    public void atualizaLista(HashMap<String, Post> hashmap) {
        List<Post> posts;

        if(hashmap == null) // se nao tiver posts ou caso fique vazia
            posts = new ArrayList<>();
        else {
            posts = new ArrayList<>(hashmap.values());


            preencheLista(posts);

        }
    }

    public void preencheLista(List<Post> posts)    {

        if(posts != null || !posts.isEmpty()) {
            mRecyclerViewFeed.setAdapter(new FeedAdapter(this, posts));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerViewFeed.setLayoutManager(layoutManager);
        }
    }


    private void preparaToolbar(){
       /* mToolbar.setTitle("Notícias");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }
}
