package com.example.sqliteandroidstudiojava;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteandroidstudiojava.databse.SqliteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.item_list)
    RecyclerView mRecyclerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    AnimeAdapter mAnimeAdapter;

    GridLayoutManager mLayoutManager;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Recycler();


        mFab.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAnimeAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAnimeAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    public void Recycler() {

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager(this, 2);
        } else {
            mLayoutManager = new GridLayoutManager(this, 1);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAnimeAdapter = new AnimeAdapter(new ArrayList<>());

        Content();
    }

    private void Content() {

        SqliteDatabase mDatabase = new SqliteDatabase(this);
        List<Anime> mAnime = mDatabase.listAnime();


        if (mAnime.size() > 0) {
            mAnimeAdapter = new AnimeAdapter(mAnime);
        } else {
            ArrayList<Anime> animeEmpty = new ArrayList<>();
            mAnimeAdapter.addItems(animeEmpty);
        }

        mRecyclerView.setAdapter(mAnimeAdapter);

    }

}
