package com.example.moviezip.service;

import com.example.moviezip.domain.Movie;

import java.util.List;

public interface UserService {
    public void updatePassword(Long id, String newPassword);
    public void updateNickname(Long id, String newNickname);

    public void deleteUser(Long id);

    public List<Movie> searchMoviesByTitle(String keyword);
}
