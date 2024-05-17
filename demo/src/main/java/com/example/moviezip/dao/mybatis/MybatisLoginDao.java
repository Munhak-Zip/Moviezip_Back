package com.example.moviezip.dao.mybatis;

import com.example.moviezip.dao.LoginDao;
import com.example.moviezip.dao.mybatis.mapper.LoginMapper;
import com.example.moviezip.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisLoginDao implements LoginDao {
    //LoginDao 재정의 및 구현
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User userLogin(String userId, String password) throws DataAccessException {
        return loginMapper.userLogin(userId, password);
    }
}
