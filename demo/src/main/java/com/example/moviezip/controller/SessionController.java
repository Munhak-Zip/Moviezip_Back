package com.example.moviezip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SessionController {

    // Controller에서 세션 만료 시 401 상태 코드를 반환하는 엔드포인트 추가

        @GetMapping("/session-expired")
        public void sessionExpired(HttpServletResponse response) throws IOException {
            System.out.println("세션만료");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session expired");
        }
    }
