package com.example.moviezip.dao;

import com.example.moviezip.domain.User;
import org.springframework.dao.DataAccessException;

public interface LoginDao {
    User userLogin(String userId, String password) throws DataAccessException;
}
