package com.example.moviezip.dao;

import com.example.moviezip.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface LoginDao {
    User userLogin(@Param("userId") String userId, @Param("password") String password);
}
