package com.example.sqliteandroidstudiojava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteandroidstudiojava.databse.SqliteDatabase;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.nameEditText)
    EditText mNameEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.scoreEditText)
    EditText  mScoreEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.episodeEditText)
    EditText  mEpisodeEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.descriptionEditText)
    EditText  mDescriptionEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.urlEditText)
    EditText mUrlEditText;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.animeButton)
    Button mAnimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.bind(this);

        int id= Objects.requireNonNull(getIntent().getExtras()).getInt("id");
        SqliteDatabase mDatabase = new SqliteDatabase(this);
        Anime mAnime = mDatabase.getAnime(id);

        mNameEditText.setText(mAnime.getName());

        mScoreEditText.setText(String.valueOf(mAnime.getScore()));

        mEpisodeEditText.setText(String.valueOf(mAnime.getEpisode()));

        mDescriptionEditText.setText(mAnime.getDescription());

        mUrlEditText.setText(mAnime.getUrl());

        mAnimeButton.setOnClickListener(v -> {
            String name = mNameEditText.getText().toString();
            int score = Integer.parseInt(mScoreEditText.getText().toString());
            int episode = Integer.parseInt(mEpisodeEditText.getText().toString());
            String description = mDescriptionEditText.getText().toString();
            String url = mUrlEditText.getText().toString();

            Anime mNewAnime = new Anime(name, score, episode, description, url);
            mDatabase.updateAnime(mNewAnime,id);

            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}
