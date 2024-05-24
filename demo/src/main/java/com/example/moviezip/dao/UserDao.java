package com.example.moviezip.dao;

import com.example.moviezip.domain.Interest;
import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDao {
    public User getUserById(Long id) throws DataAccessException;

    //비번 변경
    public void updatePassword(Long id, String newPassword) throws DataAccessException;

    public User findUser(String hint, String nickname) throws DataAccessException;

    public void updateNickname(Long id, String newNickname) throws DataAccessException;

    public User existingNickname(String nickname) throws DataAccessException;

    public void deleteUser(Long id) throws DataAccessException;

    public List<User> findAllUser() throws DataAccessException;

    public void addInterest(Interest interest) throws DataAccessException;

    public Interest findInterest(Long id) throws DataAccessException;

    public void updateInterest(Long id, String genre) throws DataAccessException;

    public void addUser(User user) throws DataAccessException;

    public User findAllUserInterest(Long id) throws DataAccessException;


}
