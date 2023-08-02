package com.example.sqliteandroidstudiojava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteandroidstudiojava.databse.SqliteDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.nameEditText)
    EditText mNameEditText;

    @BindView(R.id.scoreEditText)
    EditText mScoreEditText;

    @BindView(R.id.episodeEditText)
    EditText mEpisodeEditText;

    @BindView(R.id.descriptionEditText)
    EditText mDescriptionEditText;

    @BindView(R.id.urlEditText)
    EditText mUrlEditText;

    @BindView(R.id.animeButton)
    Button mAnimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);

        SqliteDatabase dataBase = new SqliteDatabase(this);

        mAnimeButton.setOnClickListener(v -> {
            String name = mNameEditText.getText().toString();
            int score = Integer.parseInt(mScoreEditText.getText().toString());
            int episode = Integer.parseInt(mEpisodeEditText.getText().toString());
            String description = mDescriptionEditText.getText().toString();
            String url = mUrlEditText.getText().toString();

            Anime mNewAnime = new Anime(name, score, episode, description, url);
            dataBase.newAnime(mNewAnime);

            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}
