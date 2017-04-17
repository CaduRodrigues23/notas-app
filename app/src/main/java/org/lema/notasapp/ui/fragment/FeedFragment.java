package org.lema.notasapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.lema.notasapp.R;
import org.lema.notasapp.ui.adapter.FeedAdapter;
import org.lema.notasapp.domain.model.Post;
import org.lema.notasapp.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by igor on 04/04/17.
 */

public class FeedFragment extends Fragment {

    private RecyclerView mRecyclerViewFeed;
    private DatabaseReference databaseReference;

    public FeedFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_feed, container, false);
        mRecyclerViewFeed = (RecyclerView) view.findViewById(R.id.rv_feed);

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

        return view;
    }

    public void atualizaLista(HashMap<String, Post> hashmap) {
        List<Post> posts;

        if(hashmap == null) // se nao tiver posts ou caso fique vazia
            posts = new ArrayList<>();
        else
            posts = new ArrayList<>(hashmap.values());

        Collections.sort(posts, new Comparator<Post>() {
                    @Override
                    public int compare(Post o1, Post o2) {
                        if (o1.getDataPostagem() == null || o2.getDataPostagem() == null)
                            return 0;
                        return o1.getDataPostagem().compareTo(o2.getDataPostagem());
                    }
                });
        Collections.reverse(posts);
        preencheLista(posts);

    }

    public void preencheLista(List<Post> posts)    {
        mRecyclerViewFeed.setAdapter(new FeedAdapter(getActivity(), posts));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewFeed.setLayoutManager(layoutManager);

        ((MainActivity)getActivity()).ocultarCarregando();
    }

}
