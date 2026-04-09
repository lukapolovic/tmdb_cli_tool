package com.polovski.tmdbcli;

import java.time.LocalDate;

public class Movie {
    long id;
    boolean isAdultRated;
    String originalLanguage;
    String title;
    String overview;
    double popularity;
    LocalDate releaseDate;
    double rating;
    int ratingVotes;

    public Movie(int ratingVotes, double rating, LocalDate releaseDate,
                 double popularity, String overview, String title,
                 String originalLanguage, boolean isAdultRated, long id) {
        this.ratingVotes = ratingVotes;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.overview = overview;
        this.title = title;
        this.originalLanguage = originalLanguage;
        this.isAdultRated = isAdultRated;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAdultRated() {
        return isAdultRated;
    }

    public void setAdultRated(boolean adultRated) {
        isAdultRated = adultRated;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingVotes() {
        return ratingVotes;
    }

    public void setRatingVotes(int ratingVotes) {
        this.ratingVotes = ratingVotes;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
