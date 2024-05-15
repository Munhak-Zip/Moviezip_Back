package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisReviewDao;
import com.example.moviezip.dao.mybatis.MybatisUserDao;
import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import com.example.moviezip.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl{
    private final MybatisUserDao mybatisUserDao;

//    public UserServiceImpl(MybatisMypageDao mybatisMypageDao) {
//        this.mybatisUserDao = mybatisUserDao;
//    }
    public UserServiceImpl(MybatisUserDao mybatisUserDao) {
    this.mybatisUserDao = mybatisUserDao;
}
    public User getUserById(Long id) {
        return mybatisUserDao.getUserById(id);
    }

    public void updatePassword(Long id, String newPassword) {
        mybatisUserDao.updatePassword(id, newPassword);
    }

    public void updateNickname(Long id, String newNickname) { mybatisUserDao.updateNickname(id, newNickname); }

    public void deleteUser(Long id) { mybatisUserDao.deleteUser(id); }

    public List<Movie> searchMoviesByTitle(String keyword) {

        return mybatisUserDao.searchMovieByKeyword(keyword);
    }
}
