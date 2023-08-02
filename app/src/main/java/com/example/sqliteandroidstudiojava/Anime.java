package com.example.sqliteandroidstudiojava;
public class Anime {

    private int id;
    private String name;
    private int score;
    private int episode;
    private String description;
    private String url;

    public Anime() {
    }

    public Anime(String name, int score, int episode, String description, String url) {
        this.name = name;
        this.score = score;
        this.episode = episode;
        this.description = description;
        this.url = url;
    }

    public Anime(int id, String name, int score, int episode, String description, String url) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.episode = episode;
        this.description = description;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
