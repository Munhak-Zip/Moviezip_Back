package com.example.moviezip.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDto {
    private String nickname;
    private String hint;
}
