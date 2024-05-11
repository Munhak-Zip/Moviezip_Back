package com.example.moviezip.dao.mybatis;

import com.example.moviezip.dao.mybatis.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisReviewDao {
    @Autowired
    private ReviewMapper reviewMapper;
}
