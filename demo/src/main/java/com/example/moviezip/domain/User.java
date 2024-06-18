package com.example.moviezip.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Getter
@Setter
@Data
public class User  {
    private Long id;
    private String userId;
    private String password;
    private String nickname;
    private String hint;

    public User() {
    }
    public User(Long id) {
        this.id = id;
    }
    public User( String userId, String password) {
        this.userId = userId;
        this.password = password;
    }


}
