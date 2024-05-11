package com.example.moviezip.service;

public interface UserService {
    public void updatePassword(Long id, String newPassword);
    public void updateNickname(Long id, String newNickname);

    public void deleteUser(Long id);
}
