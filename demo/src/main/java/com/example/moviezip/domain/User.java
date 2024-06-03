package com.example.moviezip.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class User{
    private Long id;
    private String userId;
    private String password;
    private String nickname;
    private String hint;
}
