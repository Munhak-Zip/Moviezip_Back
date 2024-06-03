package com.example.moviezip.domain;

public class Interest extends User {
    private Long id;
    private String genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getInterestsAsString() {
        return String.join(", ", genre);
    }
}
